package net.tfedu.zhl.cloud.resource.asset.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.core.userlog.dao.JUserlogMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetValuateMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZTypeConvertMapper;
import net.tfedu.zhl.cloud.resource.asset.entity.ResourceReview;
import net.tfedu.zhl.cloud.resource.asset.entity.ReviewResultStatis;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.entity.ZTypeConvert;
import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.asset.util.AssetTypeConvertConstant;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.user.dao.JUserMapper;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service("zAssetService")
public class ZAssetServiceImpl implements ZAssetService {
	
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
				reviewMapper.deleteByPrimaryKey(Long.parseLong(string));
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
		
		//如果需要共享到区校
		if(scope_list.contains(1)||scope_list.contains(2)){
			HashMap<String,Long> map =  userMapper.getUserAreaInfo(list.get(0).getUserid());
			if(map!=null){
				districtid = map.get("districtid");
				schoolid = map.get("schoolid");
			}			
		}
		
		Date time = Calendar.getInstance().getTime();
		
		//检测是否
		for (int i=0;i<list.size();i++) {
			ZAsset asset = list.get(i);
			int scope = scope_list.get(i);
						
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
				res.setRescode(rescode);
				res.setCreatedt(time);
				res.setCreatorid(asset.getUserid());
				res.setAuthorid(asset.getUserid());
				res.setFromflag(scope==1?3:4);
				res.setFname(_name);
				res.setFpath(_path);
				res.setFullpath(_fullpath);
				
				res.setDes(asset.getAssetdesc());
				res.setMtype(Integer.parseInt(asset.getUnifytypeid()));
				res.setTitle(asset.getName());
				res.setKeyword(asset.getKeyword());
				
				
				districtMapper.insert(res);
			}
		}
		assetMapper.insertList(list);
	}




	@Override
	public void delAsset(String resIds) {
		if(StringUtils.isNotEmpty(resIds)){
			String[]ids = resIds.split(",");
			for (String id : ids) {
				assetMapper.deleteByPrimaryKey(Long.parseLong(id));
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


}
