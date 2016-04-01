package net.tfedu.zhl.cloud.resource.asset.service.impl;

import java.util.List;

import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetValuateMapper;
import net.tfedu.zhl.cloud.resource.asset.dao.ZTypeConvertMapper;
import net.tfedu.zhl.cloud.resource.asset.entity.ResourceReview;
import net.tfedu.zhl.cloud.resource.asset.entity.ReviewResultStatis;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.entity.ZTypeConvert;
import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;

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
	public void addAssetBatch(List<ZAsset> list) {
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
	public void updateAsset(ZAsset asset) {
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
