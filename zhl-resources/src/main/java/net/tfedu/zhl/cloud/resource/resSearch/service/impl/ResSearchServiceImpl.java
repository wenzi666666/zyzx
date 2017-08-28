package net.tfedu.zhl.cloud.resource.resSearch.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;
import net.tfedu.zhl.cloud.resource.navigation.dao.JSyscourseMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.resSearch.dao.ResSearchMapper;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.UserAreaInfo;

/**
 * 资源跨库检索的service
 * 
 * @author WeiCuicui
 *
 */
@Service("resSearchService")
public class ResSearchServiceImpl implements ResSearchService {

	@Resource
	ResSearchMapper resSearchMapper;
	@Resource
	FileFormatMapper fileFormatMapper;
	@Resource
	DistrictResMapper districtResMapper;
	@Resource
	ResTypeMapper resTypeMapper;

	@Resource
	JSyscourseMapper sysCourseMapper;

	@Resource
	JUserMapper jUserMapper;

	/**
	 * 跨库检索资源
	 */
	@Override
	public Pagination<ResSearchResultEntity> getResources(int fromFlag, List<Integer> sys_from, String searchKeyword,
			String format, int page, int perPage, long userId, int expire) {

		// 存放查询结果
		List<ResSearchResultEntity> list = new ArrayList<ResSearchResultEntity>();
		// 封装结果集
		PageInfoToPagination<ResSearchResultEntity> transfer = new PageInfoToPagination<ResSearchResultEntity>();

		long schoolId = 0;
		long districtId = 0;

		// 根据userId查询schoolId 和 districtId
		HashMap<String, Object> map = jUserMapper.getUserAreaInfo(userId);
		if (map != null) {
			districtId = (map.get("districtid") instanceof java.lang.String)
					? Long.parseLong(map.get("districtid").toString())
					: Long.parseLong(String.valueOf(map.get("districtid")));
			schoolId = (map.get("schoolid") instanceof java.lang.String)
					? Long.parseLong(map.get("schoolid").toString())
					: Long.parseLong(String.valueOf(map.get("schoolid")));
		}

		// 查询满足条件的全部资源
		if (fromFlag == -1) {

			// Page插件必须放在查询语句之前紧挨的第一个位置
			PageHelper.startPage(page, perPage);
			list = resSearchMapper.getAllResources(searchKeyword, format, sys_from, schoolId, districtId);
		} else if (fromFlag == 0) { // 系统资源

			// Page插件必须放在查询语句之前紧挨的第一个位置
			PageHelper.startPage(page, perPage);
			list = resSearchMapper.getAllSysResources(searchKeyword, format, sys_from);
		} else if (fromFlag == 3 || fromFlag == 4) { // 校本资源、区本资源

			// Page插件必须放在查询语句之前紧挨的第一个位置
			PageHelper.startPage(page, perPage);
			list = resSearchMapper.getAllDisResources(searchKeyword, fromFlag, format, schoolId, districtId);
		}

		// 判断资源是否为最新
		for (int i = 0; i < list.size(); i++) {

			// 最后更新日期
			Date date = list.get(i).getUpdateDT();
			// 得到当前日期的前多少天
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -expire);
			Date expireDate = calendar.getTime();
			// 比较
			if (date.getTime() >= expireDate.getTime())
				list.get(i).setNew(true);
		}
		// 将pageIn封装为自定义的pagination
		return transfer.transfer(list);
	}

	/**
	 * 查询资源格式
	 */
	@Override
	public List<String> getFileFormats(String searchKeyword, int fromFlag, List<Integer> sys_from, long userId) {

		List<String> resultList = new ArrayList<String>();

		long schoolId = 0;
		long districtId = 0;

		// 根据userId查询schoolId 和 districtId
		HashMap<String, Object> map = jUserMapper.getUserAreaInfo(userId);
		if (map != null) {
			districtId = (map.get("districtid") instanceof java.lang.String)
					? Long.parseLong(map.get("districtid").toString())
					: Long.parseLong(String.valueOf(map.get("districtid")));
			schoolId = (map.get("schoolid") instanceof java.lang.String)
					? Long.parseLong(map.get("schoolid").toString())
					: Long.parseLong(String.valueOf(map.get("schoolid")));
		}

		// 查询全部资源的格式
		if (fromFlag == -1) {

			resultList = resSearchMapper.getAllFileFormats(searchKeyword, sys_from, schoolId, districtId);

		} else if (fromFlag == 0) { // 系统资源的格式

			resultList = resSearchMapper.getSysFileFormats(searchKeyword, sys_from);
		} else if (fromFlag == 3 || fromFlag == 4) { // 校本资源、区本资源的格式

			resultList = resSearchMapper.getDisFileFormats(searchKeyword, fromFlag, schoolId, districtId);
		}

		resultList.add(0, "全部"); // 增加一个全部的链接

		return resultList;
	}

	@Override
	public Pagination<ResSearchResultEntity> querySysResource(List<Integer> sys_from, int respool, String searchKeyword,
			int page, int perPage, int expire) {
		// 封装结果集
		PageInfoToPagination<ResSearchResultEntity> transfer = new PageInfoToPagination<ResSearchResultEntity>();

		// Page插件必须放在查询语句之前紧挨的第一个位置
		PageHelper.startPage(page, perPage);

		List<ResSearchResultEntity> list = resSearchMapper.querySysResource(sys_from, searchKeyword, respool);

		// 判断资源是否为最新
		for (int i = 0; i < list.size(); i++) {

			// 最后更新日期
			Date date = list.get(i).getUpdateDT();
			// 得到当前日期的前多少天
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -expire);
			Date expireDate = calendar.getTime();
			// 比较
			if (date.getTime() >= expireDate.getTime())
				list.get(i).setNew(true);
		}

		return transfer.transfer(list);
	}

	@Override
	public List<Map<String, Object>> queryBatchResourceInfo(Long[] assetIds, Long[] sysResourceIds,
			Long[] districtResIds) {

		if ((null == assetIds || assetIds.length == 0) && (null == sysResourceIds || sysResourceIds.length == 0)
				&& (null == districtResIds || districtResIds.length == 0)) {
			return null;
		}

		List<Map<String, Object>> ls = resSearchMapper.queryAssets(assetIds);
		List<Map<String, Object>> ls2 = resSearchMapper.querySysResources(sysResourceIds);
		List<Map<String, Object>> ls3 = resSearchMapper.queryDistrcitResources(districtResIds);

		ls.addAll(ls2);
		ls.addAll(ls3);

		return ls;
	}

	@Override
	public List<Map<String, Object>> getAssetFileTypeAndFormat(Long userId, Integer isCollect, List<Long> courseIds) {

		List<Map<String, Object>> types = fileFormatMapper.getAssetResourceType(userId, isCollect, courseIds);
		List<Map<String, Object>> formats = fileFormatMapper.getAssetFileFormat(userId, isCollect, courseIds);

		return fillResultList(types, formats);
	}

	@Override
	public List<Map<String, Object>> getOtherSharedAssetTypeAndFormat(Long userId, Integer searchFlag, String tfcode,
			Long sysResType) {

		long schoolId = 0;
		long districtId = 0;

		UserAreaInfo info = jUserMapper.getUserAreaALLInfo(userId);
		if (null != info) {
			schoolId = info.getSchoolId();
			districtId = info.getDistrictId();

		}

		List<Map<String, Object>> types = fileFormatMapper.getOtherSharedAssetType(userId, searchFlag, tfcode,
				sysResType, schoolId, districtId);
		List<Map<String, Object>> formats = fileFormatMapper.getOtherSharedAssetFormat(userId, searchFlag, tfcode,
				sysResType, schoolId, districtId);

		return fillResultList(types, formats);
	}

	/**
	 * 将获取的结果用指定的结构返回
	 * 
	 * @param types
	 * @param formats
	 * @return
	 */
	protected List<Map<String, Object>> fillResultList(List<Map<String, Object>> types,
			List<Map<String, Object>> formats) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tMap = new HashMap<String, Object>();
		Map<String, Object> fMap = new HashMap<String, Object>();

		tMap.put("res_type", types);
		fMap.put("formats", formats);

		resultList.add(tMap);
		resultList.add(fMap);
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getSysResourceTypeAndFormat(Long typeId, String exceptPoolIds, Integer poolId,
			List<String> syscourseCodes, String sysFrom) {

		Long[] exceptIds = null;
		Long[] sysFromFlag = null;

		if (StringUtils.isEmpty(exceptPoolIds)) {
			String[] temp = exceptPoolIds.split(",");
			exceptIds = new Long[temp.length];
			for (int i = 0; i < temp.length; i++) {
				exceptIds[i] = Long.parseLong(temp[i]);
			}
		}

		if (StringUtils.isEmpty(sysFrom)) {
			String[] temp = sysFrom.split(",");
			sysFromFlag = new Long[temp.length];
			for (int i = 0; i < temp.length; i++) {
				sysFromFlag[i] = Long.parseLong(temp[i]);
			}
		}

		// 根据资源库获取对应的资源类型
		List<Long> typeIds = fileFormatMapper.getPoolType(null, poolId, exceptIds);

		List<Map<String, Object>> types = fileFormatMapper.getSysResourceType(poolId, syscourseCodes, sysFromFlag,
				typeIds);
		List<Map<String, Object>> formats = fileFormatMapper.getSysResourceFileFormat(poolId, syscourseCodes,
				sysFromFlag, typeIds);

		return fillResultList(types, formats);
	}

	@Override
	public List<Map<String, Object>> getDistrictResourceTypeAndFormat(Long userId, Integer resourceFromFlag,
			List<String> syscourseCodes, Long sysResType) {

		long schoolId = 0;
		long districtId = 0;

		UserAreaInfo info = jUserMapper.getUserAreaALLInfo(userId);
		if (null != info) {
			schoolId = info.getSchoolId();
			districtId = info.getDistrictId();

		}

		List<Map<String, Object>> types = fileFormatMapper.getDistrictResourceType(resourceFromFlag, syscourseCodes,
				sysResType, schoolId, districtId);
		List<Map<String, Object>> formats = fileFormatMapper.getDistrictResourceFormat(resourceFromFlag, syscourseCodes,
				sysResType, schoolId, districtId);

		return fillResultList(types, formats);
	}

	@Override
	public List<Map<String, Object>> queryAssetList(Long userId, Integer isCollect, List<Long> courseIds, Integer page,
			Integer perPage, Long mtype, String fileFormat) {

		PageHelper.startPage(page, perPage);
		// 这里不能放其它语句

		return fileFormatMapper.queryAssetList(userId, isCollect, courseIds, mtype, fileFormat);
	}

	@Override
	public List<Map<String, Object>> querySysResourceList(Long typeId, String exceptPoolIds, Integer poolId,
			List<String> syscourseCodes, String sysFrom, Integer page, Integer perPage, String fileFormat,
			Integer orderBy) {
		Long[] exceptIds = null;
		Long[] sysFromFlag = null;

		if (StringUtils.isEmpty(exceptPoolIds)) {
			String[] temp = exceptPoolIds.split(",");
			exceptIds = new Long[temp.length];
			for (int i = 0; i < temp.length; i++) {
				exceptIds[i] = Long.parseLong(temp[i]);
			}
		}

		if (StringUtils.isEmpty(sysFrom)) {
			String[] temp = sysFrom.split(",");
			sysFromFlag = new Long[temp.length];
			for (int i = 0; i < temp.length; i++) {
				sysFromFlag[i] = Long.parseLong(temp[i]);
			}
		}

		// 根据资源库获取对应的资源类型
		List<Long> typeIds = fileFormatMapper.getPoolType(null == typeId || 0 == typeId ? null : typeId.intValue(),
				poolId, exceptIds);

		// 获取指定的后缀
		List<String> fileExts = null;
		List<String> resCodes = null;

		if (StringUtils.isNotEmpty(fileFormat)) {
			fileExts = fileFormatMapper.getFileExtForFileFormat(fileFormat);
		}

		// 根据导航获取资源的code的范围
		resCodes = fileFormatMapper.getResCodesFromResnav(syscourseCodes);

		PageHelper.startPage(page, perPage);

		List<Map<String, Object>> result = fileFormatMapper.querySysResourceList(resCodes, typeIds, fileExts,
				sysFromFlag, orderBy);

		// 更新结果的资源类型
		resetMtype(result);

		return result;
	}

	

	@Override
	public Boolean ifSysResourceCollected(Long userId, Long resId) {

		return fileFormatMapper.ifSysResourceCollected(userId, resId);
	}

	@Override
	public List<Map<String, Object>> querySharedAssetList(Long userId, Long typeId, Integer searchFlag, String tfcode,
			Integer page, Integer perPage, String fileFormat, Integer orderBy) {

		long schoolId = 0;
		long districtId = 0;
		String schoolName ="";

		// 根据userId查询schoolId 和 districtId
		HashMap<String, Object> map = jUserMapper.getUserAreaInfo(userId);
		if (map != null) {
			districtId = (map.get("districtid") instanceof java.lang.String)
					? Long.parseLong(map.get("districtid").toString())
					: Long.parseLong(String.valueOf(map.get("districtid")));
			schoolId = (map.get("schoolid") instanceof java.lang.String)
					? Long.parseLong(map.get("schoolid").toString())
					: Long.parseLong(String.valueOf(map.get("schoolid")));
					
		}

		// 获取指定的后缀
		List<String> fileExts = null;
		List<Integer> typeIds =  null ; 

		if (StringUtils.isNotEmpty(fileFormat)) {
			fileExts = fileFormatMapper.getFileExtForFileFormat(fileFormat);
		}

		if(null != typeId && typeId > 0 ){
			typeIds  = resTypeMapper.getTypeIdsByOne(typeId.intValue());
		}

		PageHelper.startPage(page, perPage);


		List<Map<String, Object>> result = fileFormatMapper.querySharedAssetList(searchFlag, districtId, schoolId,
				tfcode + "%", typeIds, fileExts, orderBy);

		// 更新结果的资源类型
		resetMtype(result);

		// 是否已经被当前用户收藏
		ifSharedAssetCollected(userId,result);

		//补充共享人的信息 username truename schoolname
		supplementShareUserInfo(result);
		
		
		return result;

	}

	/**
	 * 补充共享人的信息 username truename schoolname
	 * @param result
	 */
	private void supplementShareUserInfo(List<Map<String, Object>> result) {
		
		if (null != result && result.size() > 0) {

			Map<String, Object> map = null;

			long  userId ;
			
			JUser user ; 

			for (Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
				map = iterator.next();
				//共享资源id
				userId = (Long) map.get("userid");
				
				user = jUserMapper.getUserById(userId);
				
				if(null != user && user.getId()> 0 ){
					
					map.put("username", user.getName());
					map.put("truename", user.getTruename());
					map.put("schoolname", user.getSchoolName());
					map.put("sname", user.getSchoolName());
					
				}
			}
		}
		
	}

	/**
	 * 共享资源是否已经被当前用户收藏
	 * @param userId
	 * @param result
	 */
	private void ifSharedAssetCollected(Long userId, List<Map<String, Object>> result) {
		
		if (null != result && result.size() > 0) {

			Map<String, Object> map = null;

			long resId ;

			for (Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
				map = iterator.next();
				//共享资源id
				resId = (Long) map.get("id");
				
				Boolean collected =  fileFormatMapper.ifSharedAssetCollected(userId, resId);
				
				map.put("collected", collected);

			}
		}
		
	}
	
	/**
	 * 设置资源类型
	 * 
	 * @param result
	 */
	private void resetMtype(List<Map<String, Object>> result) {

		if (null != result && result.size() > 0) {

			Map<String, Object> map = null;

			ResType type = null;

			for (Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
				map = iterator.next();
				int id = (Integer) map.get("typeid");
				type = resTypeMapper.selectByPrimaryKey(id);

				if (null != type) {
					map.put("mtype", type.getMtype());
				}

			}
		}

	}

	@Override
	public List<Map<String, Object>> queryDistrictResource(Long userId, Long mTypeId, Integer type, String tfcode,
			Integer curPage, Integer perPage, String resPattern, Integer orderBy) {
		long schoolId = 0;
		long districtId = 0;
		String schoolName ="";

		// 根据userId查询schoolId 和 districtId
		HashMap<String, Object> map = jUserMapper.getUserAreaInfo(userId);
		if (map != null) {
			districtId = (map.get("districtid") instanceof java.lang.String)
					? Long.parseLong(map.get("districtid").toString())
					: Long.parseLong(String.valueOf(map.get("districtid")));
			schoolId = (map.get("schoolid") instanceof java.lang.String)
					? Long.parseLong(map.get("schoolid").toString())
					: Long.parseLong(String.valueOf(map.get("schoolid")));
					
		}

		// 获取指定的后缀
		List<String> fileExts = null;
		List<Integer> typeIds =  null ; 

		if (StringUtils.isNotEmpty(resPattern)) {
			fileExts = fileFormatMapper.getFileExtForFileFormat(resPattern);
		}

		if(null != mTypeId && mTypeId > 0 ){
			typeIds = resTypeMapper.getTypeIdsByOne(mTypeId.intValue());
		}

		PageHelper.startPage(curPage, perPage);


		List<Map<String, Object>> result = fileFormatMapper.queryDistrictResList(type, districtId, schoolId,
				tfcode + "%", typeIds, fileExts, orderBy);

		// 更新结果的资源类型
		resetMtype(result);

		// 是否已经被当前用户收藏
		ifDistrictResCollected(userId,result);

		//补充共享人的信息 username truename schoolname
		supplementShareUserInfo(result);
		
		return result;
		
	}

	/**
	 * 区校资源是否已经被当前用户收藏
	 * @param userId
	 * @param result
	 */
	private void ifDistrictResCollected(Long userId, List<Map<String, Object>> result) {
		if (null != result && result.size() > 0) {

			Map<String, Object> map = null;

			long resId ;

			for (Iterator<Map<String, Object>> iterator = result.iterator(); iterator.hasNext();) {
				map = iterator.next();
				//资源资源id
				resId = (Long) map.get("id");
				
				String collected =  fileFormatMapper.ifDistrictResCollected(userId, resId);
				
				map.put("collected", collected);

			}
		}
	}

}
