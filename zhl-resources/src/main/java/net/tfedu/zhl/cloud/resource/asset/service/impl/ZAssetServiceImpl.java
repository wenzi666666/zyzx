package net.tfedu.zhl.cloud.resource.asset.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetSyscourseMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetValuateMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZTypeConvertMapper;
import net.tfedu.zhl.cloud.resource.asset.entity.ResourceReview;
import net.tfedu.zhl.cloud.resource.asset.entity.ReviewResultStatis;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetSyscourse;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetValuate;
import net.tfedu.zhl.cloud.resource.asset.entity.ZTypeConvert;
import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.asset.util.AssetTypeConvertConstant;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictsResNavMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictsResNav;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.sso.user.dao.JUserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service("zAssetService")
public class ZAssetServiceImpl implements ZAssetService {
	
	
	Logger logger = LoggerFactory.getLogger(ZAssetServiceImpl.class);

	
	@Autowired
	ResTypeMapper typeMapper;
	
	@Autowired
	FileFormatMapper formatMapper;
	
	/**
	 * 评论service
	 */
	@Autowired
	ZAssetValuateMapper reviewMapper;
	
	
	@Autowired
	ZAssetMapper assetMapper;
	
	
	@Autowired
	ZTypeConvertMapper convertMapper; 
	
	@Autowired
	JUserMapper userMapper;
	
	
	@Autowired
	DistrictResMapper districtMapper;
	
	@Autowired
	ZAssetSyscourseMapper asMapper;
	
	@Autowired
	DistrictsResNavMapper districtResNavMapper;
	
	@Override
	public List<FirstLevelResType> getAllFirstLevelResType() {
		// TODO Auto-generated method stub
		
		List<FirstLevelResType> list =typeMapper.getAllFirstLevelResType();
		FirstLevelResType e  = new FirstLevelResType();
		e.setId(0l);
		e.setCode("all");
		e.setMtype("全部");
		list.add(0, e);
		return list;
	}




	@Override
	public List<String> getAllFileFormat() {
		// TODO Auto-generated method stub
		List<String> list = formatMapper.getAllFileFormat();
		list.add(0,"全部");
		return list;
	}




	@Override
	public Pagination getMyReview(Long userId, Integer reviewType,Integer page,Integer perPage) {
		PageHelper.startPage(page, perPage);
		List<ResourceReview> list  = reviewMapper.getMyReview(userId, reviewType);
		Pagination p = new PageInfoToPagination().transfer(list);
		return p ;
	}




	@Override
	public void removeMyReview(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String id[] = ids.split(",");
			for (String string : id) {
				ZAssetValuate z = new ZAssetValuate();
				z.setId(Long.parseLong(string));
				z.setFlag(true);
				reviewMapper.updateByPrimaryKeySelective(z);
			}
		}
	}




	@Override
	public ReviewResultStatis getReviewStatis(Long userId) {
		/**
		 * 已经评价的资源数
		 */
		Integer reviewNumber  = reviewMapper.getReviewedNum(userId);
		
		/**
		 * 未评价的资源数
		 */
		Integer unReviewNumber = reviewMapper.getUnReviewedNum(userId);
		
		ReviewResultStatis obj = new ReviewResultStatis();
		obj.setReviewNumber(reviewNumber);
		obj.setUnReviewNumber(unReviewNumber);
		return obj;
	}




	@Override
	public void setTypeConvertSucceed(Long userId, String resPath) {
		ZTypeConvert obj = new ZTypeConvert();
		obj.setUserid(userId);
		obj.setRespath(resPath);
		convertMapper.insert(obj);
		
		assetMapper.updateAssetFinished(userId, resPath);
		
	}




	@Override
	public void addAssetBatch(List<ZAsset> list,List<String>codes,ArrayList<Integer> scope_list,String resServiceLocal, String currentResPath, String hostLocal) {
		long schoolid = 0 ;
		long districtid=  0;
		
		
		
		
		List<DistrictRes> dResList = new ArrayList();
		List<DistrictsResNav> dResNavList = new ArrayList();
		
		//如果需要共享到区校
		if(scope_list.contains(1)||scope_list.contains(2)){
			HashMap<String,Object> map =  userMapper.getUserAreaInfo(list.get(0).getUserid());
			if(map!=null){
				districtid =  (map.get("districtid") instanceof java.lang.String )
								?Long.parseLong(map.get("districtid").toString())
								:Long.parseLong(String.valueOf(map.get("districtid")));
				schoolid = (map.get("schoolid") instanceof java.lang.String )
						?Long.parseLong(map.get("schoolid").toString())
						:Long.parseLong(String.valueOf(map.get("schoolid")));
			}			
		}
		
		Date time = Calendar.getInstance().getTime();
		
		//检测是否
		for (int i=0;i<list.size();i++) {
			ZAsset asset = list.get(i);
			int scope = scope_list.get(i);
			int _scope = scope==1?3:4;
			long _scopeId = _scope==3?schoolid:districtid;
			
			//0：转码完成，1：未完成
			int isfinished = 1 ;
			//如果更新了文件且需要格式转换
			if(AssetTypeConvertConstant.isNeedConvert(asset.getAssetpath())){
				//检测是否已经完成格式转换
				ZTypeConvert obj =  convertMapper.getConvertRecord(asset.getUserid(), asset.getAssetpath());				
				if(null == obj){
					isfinished = 1 ;	
				}else{
					isfinished = 0 ;
				}				
			}else{
				isfinished = 0 ;
			}
			asset.setIsfinished(isfinished);
			
			if(scope>0){
				String assetPath =asset.getAssetpath();
				String tfcode = codes.get(i);
				//组装区本、校本路径
				assetPath =  assetPath.replaceAll("\\\\", "/");
				String _name = assetPath.substring(assetPath.lastIndexOf("/")+1,assetPath.length());
				String _path = AssetTypeConvertConstant.getAreaPathPrefix(scope, schoolid, districtid, tfcode);
				String _fullpath = _path+_name;
				
				//复制到目标目录
				ZhlResourceCenterWrap.copyFile(resServiceLocal, assetPath, _fullpath);
				
				String rescode = AssetTypeConvertConstant.getResCodeForDistrictRes(scope, tfcode);
				//复制到区本校本资源
				DistrictRes res = new DistrictRes();
				
				res.setScope(_scope);
				res.setScopeid(_scopeId);
				res.setFromflag(scope==1?3:4);
				res.setRescode(rescode);
				res.setCreatedt(time);
				//作者
				res.setAuthorid(asset.getUserid());
				res.setFname(_name);
				res.setFpath(_path);
				res.setFullpath(_fullpath);
				
				//创建者（后台管理员）
				res.setCreatorid(0l);
				res.setDes(asset.getAssetdesc());
				res.setMtype(asset.getUnifytypeid());
				res.setTitle(asset.getName());
				res.setKeyword(asset.getKeyword());	
				res.setRdes("");
				
				res.setTypelean("");
				res.setAuditopinion("");
				res.setAuthorfromflag(1);
				res.setAuthorunit("");
				res.setClicktimes(0);
				res.setCopyright("");
				res.setDisplayindex(0);
				res.setDisplaylevel(0);
				res.setDloadscore(0);
				res.setDloadtimes(0);
				res.setEditorid(0l);
				res.setEduplace("");
				//后缀
				String  fileext =assetPath.substring(assetPath.lastIndexOf(".") + 1, assetPath.length());
				res.setFileext(fileext);
				res.setFlag(false);
				res.setFsize(asset.getAssetsize());
				res.setFullpath(assetPath);
				res.setIsdwj(asset.getIswjb());
				res.setIsfinished(isfinished);
				res.setIslocal(0);
				res.setSuitterm("");
				res.setSctimes(0);
				//待审核
				res.setState(4);
				res.setCopyright("");
				res.setAuthorfromflag(1);
				
				res.setUploadscore(0);
				res.setUpdatedt(time);
				dResList.add(res);
				
				
				
				DistrictsResNav resNav = new DistrictsResNav();
				resNav.setFlag(false);
				resNav.setRescode(rescode);
				resNav.setStructcode(tfcode);				
				dResNavList.add(resNav);
				
				
				
			}
		}
		
		logger.debug("start  to save data to database ...... ");
		assetMapper.insertList(list);

		logger.debug("insert asset to database batch ");

		//批量添加资源的导航信息
		List<ZAssetSyscourse> asList = new ArrayList<ZAssetSyscourse>();
		for (int i=0;i<list.size();i++) {
			ZAsset asset = list.get(i);
			String  tfcode = codes.get(i);
			long resid= asset.getId();
			ZAssetSyscourse  as = new ZAssetSyscourse();
			as.setAssetid(resid);
			as.setTfcode(tfcode);
			as.setFlag(false);
			asList.add(as);
			
			//取消资源添加
			if(dResList.size()>0){
				dResList.get(i).setAssetid(resid);
			}
		}
		if(asList.size()>0){
			logger.debug("add asset_syscourse to database batch .....");
			asMapper.insertList(asList);
		}
		
		//如果需要共享到dResList
		if(scope_list.contains(1)||scope_list.contains(2)){
			//批量增加资源
			logger.debug("add districtRes to database batch .....");
			districtMapper.insertList(dResList);
			for (DistrictsResNav nav : dResNavList) {
				logger.debug("add districtResNav to database .....");
				districtResNavMapper.insertWithoutSyscourseId(nav);
			}
		}

	}




	@Override
	public void delAsset(String resIds) {
		if(StringUtils.isNotEmpty(resIds)){
			String[]ids = resIds.split(",");
			for (String id : ids) {
				
				ZAsset  z = new ZAsset();
				z.setId(Long.parseLong(id));
				z.setFlag(true);
				
				assetMapper.updateByPrimaryKeySelective(z);
			}
		}
	}




	@Override
	public void updateAsset(ZAsset asset, String resServiceLocal, String currentResPath, String hostLocal) {
		//获取资源、判断是否路径出现变化
		ZAsset temp = assetMapper.selectByPrimaryKey(asset.getId());
		if(!temp.getAssetpath().equals(asset.getAssetpath())){
			//0：转码完成，1：未完成
			int isfinished = 1 ;
			//如果更新了文件且需要格式转换
			if(AssetTypeConvertConstant.isNeedConvert(asset.getAssetpath())){
				//检测是否已经完成格式转换
				ZTypeConvert obj =  convertMapper.getConvertRecord(asset.getUserid(), asset.getAssetpath());				
				if(null == obj){
					isfinished = 1 ;	
				}else{
					isfinished = 0 ;
				}				
			}else{
				isfinished = 0 ;
			}
			asset.setIsfinished(isfinished);
		}
		assetMapper.updateByPrimaryKeySelective(asset);
	}




	@Override
	public Pagination queryMyAssets(Long userId, Long unifyTypeId,
			String fileFormat, Integer page, Integer perPage) {
		PageHelper.startPage(page, perPage);
		List<ZAsset> list  = assetMapper.queryMyAssets(userId, unifyTypeId, fileFormat);
		Pagination p = new PageInfoToPagination().transfer(list);		
		return p ;
	}




	@Override
	public List<FirstLevelResType> getResTypeForPersonalTab(String tabCode,
			Long userId) {
		//myPrepareRes,myUpload,myDownload,myView。不传递该参数时默认为myPrepareRes
		tabCode = tabCode==null?"myPrepareRes":tabCode.trim();
		if("myPrepareRes".equals(tabCode)){
			return assetMapper.getResTypeForMyPrepareRes(userId);
		}
		
		if("myUpload".equals(tabCode)){
			return assetMapper.getResTypeForMyUpload(userId);
		}
		if("myDownload".equals(tabCode)){
			return assetMapper.getResTypeForMyDownload(userId);
		}
		if("myView".equals(tabCode)){
			return assetMapper.getResTypeForMyView(userId);
		}
		
		return null;
	}


    /**
     * 分页获取我的资源评论
     * @param userId
     * @param reviewType
     * @param page
     * @param perPage
     * @return
     */
	@Override
	public Pagination getMyReviewComment(Long userId, Integer reviewType,Integer page,Integer perPage) {
		PageHelper.startPage(page, perPage);
		List<ResourceReview> list  = reviewMapper.getMyReviewComment(userId, reviewType);
		Pagination p = new PageInfoToPagination().transfer(list);
		return p ;
	}
	
	/**
	 * 批量复制    自建资源
	 * @param resIds
	 * @param tfcode
	 */
	@Override
	public void patchCopyAsset(List<Long> resIds,String tfcode){
		if(resIds != null || resIds.size() > 0)
			for(int i = 0; i < resIds.size();i++){
				long resId = resIds.get(i);
				assetMapper.copyAsset(resId, tfcode);
			}
	}
	
	/**
	 * 批量剪切    自建资源
	 * @param resIds
	 * @param tfcode 要剪切到的目标tfcode
	 */
	@Override
	public void patchCutAsset(List<Long> resIds,String tfcode){
		if(resIds != null || resIds.size() > 0)
			for(int i = 0; i < resIds.size();i++){
				long resId = resIds.get(i);
				//首先，将该资源从所有结点下删除
				assetMapper.delAsset(resId);
				//然后，复制资源到目标结点
				assetMapper.copyAsset(resId, tfcode);
			}
	}
}

