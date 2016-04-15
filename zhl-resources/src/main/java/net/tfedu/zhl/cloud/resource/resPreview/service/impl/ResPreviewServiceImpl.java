package net.tfedu.zhl.cloud.resource.resPreview.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetSyscourseMapper;
import net.tfedu.zhl.cloud.resource.navigation.dao.JUserDefaultMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resSearch.dao.ResSearchMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

/**
 * 资源预览的 serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("resPreviewService")
public class ResPreviewServiceImpl implements ResPreviewService {

    @Resource
    SysResourceMapper sysResourceMapper;
    @Resource
    DistrictResMapper districtResMapper;
    @Resource
    JUserDefaultMapper jUserDefaultMapper;

    @Resource
    ResTypeMapper resTypeMapper;
    
    @Resource
    ZAssetMapper  assetMapper;
    
    @Resource ResSearchMapper resSearchMapper;
    
    @Resource ZAssetSyscourseMapper zAssetSyscourseMapper;
    

    // 根据resId和fromFlag，查询资源具体信息
    @Override
    public ResPreviewInfo getResPreviewInfo(long resId, long userId,int fromFlag) {
        ResPreviewInfo info = null;
        if (fromFlag == 0) {// 系统资源
            info = sysResourceMapper.getSysResInfo(resId,userId);

        } else if (fromFlag == 1) {// 自建资源
        	info = assetMapper.getAssetPreviewInfo(resId);
        } else if (fromFlag == 3) {// 校本资源
            info = districtResMapper.getDisResInfo(fromFlag, resId,userId);

        } else if (fromFlag == 4) {// 区本资源
            info = districtResMapper.getDisResInfo(fromFlag, resId,userId);

        }

        return info;
    }

    // 对一个资源进行预览时，查询该资源所在目录
    @Override
    public List<List<ResNavEntity>> getAllResNavs(long resId, int fromFlag, String curTfcode) {
    	List<List<ResNavEntity>> info = new ArrayList<List<ResNavEntity>>();
        List<String> structCodes  = null;
        String resFrom = "";
        if (fromFlag == 0) {// 系统资源
        	resFrom = "系统资源";
            structCodes = sysResourceMapper.getAllRescodes(resId, curTfcode);
        } else if (fromFlag == 1) {// 自建资源
        	resFrom = "自建资源";
        	structCodes = assetMapper.getAssetNavs(resId, curTfcode);
        } else if (fromFlag == 3 || fromFlag == 4) {// 校本资源、区本资源
        	
            structCodes = districtResMapper.getAllDisRescodes(resId, curTfcode);
        }
        
        if(fromFlag == 3)
        	resFrom = "校本资源";
        if(fromFlag == 4)
        	resFrom = "区本资源";
		
        
       
        //将资源来源添加到结果集中
        ResNavEntity navEntity = new ResNavEntity();
        navEntity.setName(resFrom);
        
        
        if (structCodes != null) {
            for (int i = 0; i < structCodes.size(); i++) {
                List<ResNavEntity> navs = sysResourceMapper.getSysNav(structCodes.get(i));
                List<ResNavEntity> finalNavs = new ArrayList<ResNavEntity>();
                //获取结果集中的第一条和最后一条
                finalNavs.add(navEntity);
                
                if(navs.size() <= 1){
                	continue;
                } else {
                	finalNavs.add(navs.get(0));
                    finalNavs.add(navs.get(navs.size() - 1));
				}
                
                if (finalNavs != null)
                    info.add(finalNavs);
            }
        }
        return info;
    }

  
    
    /**
     * 系统资源推荐列表
     * @param tfcode
     * @param typeId
     * @param page
     * @param perPage
     * @param resId
     * @param poolId
     * @return
     */
    @Override
	public Pagination<ResRecommendationEntity> sysRecommendation(String tfcode,int typeId,long resId,long poolId,int page,int perPage,List<Integer> sys_from){
    	
        // 根据当前结点tfcode，以及sys_from，查询系统资源id
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sys_from", SysFrom.sys_from);
        map.put("pTfcode", tfcode);
        List<Long> resourceIds = resTypeMapper.getAllSysResIds(map);

        // 根据资源库id和父类型id，得到父类型的所有子类型及其自身
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("poolId", poolId);
        map1.put("typeId", typeId);
        List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool(poolId, typeId);
    	
    	
    	
    	// Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(page, perPage);

        // 查询系统资源
        List<ResRecommendationEntity> list = sysResourceMapper.getAllSysRes_Preview(sys_from, resourceIds, tfcode, typeIds,resId);
        
        for (int i = 0; i < list.size(); i++) {
        	//将 / 替换为 \
        	String thumbnailpath = list.get(i).getThumbnailpath();
        	if(thumbnailpath.indexOf("/") >= 0){
        	    thumbnailpath = thumbnailpath.replace("/", "\\");
        		list.get(i).setThumbnailpath(thumbnailpath);
        	}
        	
        }
        // 封装结果集
        PageInfoToPagination<ResRecommendationEntity> transfer = new PageInfoToPagination<ResRecommendationEntity>();

        return transfer.transfer(list);
    }
    
    /**
     * 区本、校本资源推荐列表
     * @param tfcode
     * @param typeId
     * @param fromFlag
     * @param resId
     * @param userId
     * @param page
     * @param perPage
     * @return
     */
    @Override
	public Pagination<ResRecommendationEntity> disRecommendation(String tfcode,int typeId,int fromFlag,long resId,long userId,int page,int perPage){
    	 // 根据父类型，查询所有的子类型
        List<Integer> typeIds = resTypeMapper.getDisResTypesByPMType(typeId);

        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
        
        // Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(page, perPage);

        // 查询资源
        List<ResRecommendationEntity> list = districtResMapper.selectDisRes_Preview(fromFlag, typeIds, tfcode, schoolId, districtId,resId);

        for (int i = 0; i < list.size(); i++) {
        	//将 / 替换为 \
        	String thumbnailpath = list.get(i).getThumbnailpath();
        	if(thumbnailpath.indexOf("/") >= 0){
        	    thumbnailpath = thumbnailpath.replace("/", "\\");
        		list.get(i).setThumbnailpath(thumbnailpath);
        	}
        }

        // 封装结果集
        PageInfoToPagination<ResRecommendationEntity> transfer = new PageInfoToPagination<ResRecommendationEntity>();

        return transfer.transfer(list);
    }
    
    /**
     * 资源检索结果的推荐列表
     * @param tfcode
     * @param fromFlag
     * @param resId
     * @param searchWord
     * @param page
     * @param perPage
     * @return
     */
    @Override
	public Pagination<ResRecommendationEntity> searchRecommendation(int fromFlag,long resId,long userId,String searchKeyword,List<Integer> sys_from,int page,int perPage){
    	// 存放查询结果
        List<ResRecommendationEntity> list = new ArrayList<ResRecommendationEntity>();
        // 封装结果集
        PageInfoToPagination<ResRecommendationEntity> transfer = new PageInfoToPagination<ResRecommendationEntity>();

        // 若输入的关键字为空，则返回为空
        if (searchKeyword == null || searchKeyword.length() == 0)
            return transfer.transfer(list);
        
        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
  
        // 查询满足条件的全部资源
        if (fromFlag == -1) {

            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);
            list = resSearchMapper.getAllResources_preview(searchKeyword, sys_from, schoolId, districtId,resId);
        } else if (fromFlag == 0) { // 系统资源

            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);
            list = resSearchMapper.getAllSysResources_preview(searchKeyword, sys_from,resId);
        } else if(fromFlag == 3 || fromFlag == 4){ // 校本资源、区本资源

        	
            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);
            list = resSearchMapper.getAllDisResources_preview(searchKeyword, fromFlag, schoolId, districtId,resId);
        }
        
   
        for (int i = 0; i < list.size(); i++) {
        	//将 / 替换为 \
        	String thumbnailpath = list.get(i).getThumbnailpath();
        	if(thumbnailpath.indexOf("/") >= 0){
        	    thumbnailpath = thumbnailpath.replace("/", "\\");
        		list.get(i).setThumbnailpath(thumbnailpath);
        	}
        }
        //将pageIn封装为自定义的pagination
        return transfer.transfer(list);
    }
    
    /**
     * 个人中心 - 我的上传  资源推荐（推荐 相同课程结点下的系统、区本、校本资源）
     * @param resId
     * @param sys_from
     * @param page
     * @param perPage
     * @return
     */
    @Override
	public Pagination<ResRecommendationEntity> myResByUploadRecommendation(long userId,long resId,List<Integer> sys_from,int page,int perPage){
    	
    	// 存放查询结果
        List<ResRecommendationEntity> list = new ArrayList<ResRecommendationEntity>();
        // 封装结果集
        PageInfoToPagination<ResRecommendationEntity> transfer = new PageInfoToPagination<ResRecommendationEntity>();

    	
    	//根据resId获取课程结点tfcode
    	String tfcode = zAssetSyscourseMapper.getFirstCourseByResId(resId);
    	
    	long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
    	
    	//查询tfcode下的系统资源、区本、校本资源
    	list = sysResourceMapper.getAllResByTfcode(resId, sys_from, tfcode, schoolId, districtId);
    	
        //将pageIn封装为自定义的pagination
        return transfer.transfer(list);
    }
  
}

