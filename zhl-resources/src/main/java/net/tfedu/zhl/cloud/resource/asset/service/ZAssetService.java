package net.tfedu.zhl.cloud.resource.asset.service;

import java.util.ArrayList;
import java.util.List;

import net.tfedu.zhl.cloud.resource.asset.entity.ReviewResultStatis;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

public interface ZAssetService {
	
	/**
	 *  自建资源 ： 查询全部一级资源类型
	 */
	public List<FirstLevelResType> getAllFirstLevelResType();
	
	
	
	
	/**
	 *  自建资源 ： 查询全部文件格式
	 */
	public List<String> getAllFileFormat();
	
	
	

	/**
	 * 查询用户的 评论
	 * @param userId
	 * @param reviewType 评论级别 0 全部 1 好 2 中3 差
	 * @param page
	 * @param perPage
	 * @return
	 */
	public Pagination getMyReview(Long userId,Integer reviewType,Integer page,Integer perPage);
	
	
	
	
	/**
	 * 删除我的资源评论
	 * @param ids   评论id的复数
	 */
	public void removeMyReview(String ids);
	
	
	
	
	/**
	 * 对用户的资源评论（评论、未评论）进行统计
	 * @param userId
	 * @return
	 */
	public ReviewResultStatis getReviewStatis(Long userId);
	
	
	
	/**
	 * 设置上传资源格式转换成功
	 * @param userId
	 * @param resPath  上传文件原始路径
	 */
	public void setTypeConvertSucceed(Long userId,String resPath);
	
	
	
	
	
	/**
	 * 批量增加自建资源
	 * @param list
	 * @param codes
	 * @param hostLocal 
	 * @param currentResPath 
	 * @param resServiceLocal 
	 */
	public void addAssetBatch(List<ZAsset> list,List<String> codes,ArrayList<Integer> scope_list,String resServiceLocal, String currentResPath, String hostLocal) ;
	
	
	
	
	/**
	 * 删除自建资源
	 * @param resIds
	 */
	public void delAsset(String resIds);
	
	
	
	/**
	 * 修改自建资源
	 * @param asset
	 */
	public void updateAsset(ZAsset asset, String resServiceLocal, String currentResPath, String hostLocal);
	
	
	
	/**
	 * 分页查询我的上传
	 * @param userId
	 * @param unifyTypeId  资源类型
	 * @param fileFormat   资源格式 
	 * @param page
	 * @param perPage
	 * @return
	 */
	public Pagination queryMyAssets(Long userId,Long unifyTypeId,String fileFormat,Integer page,Integer perPage);
	
	

}
