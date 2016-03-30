package net.tfedu.zhl.cloud.resource.asset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.asset.entity.ResourceReview;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetValuate;
import net.tfedu.zhl.helper.CoreMapper;

public interface ZAssetValuateMapper extends CoreMapper<ZAssetValuate> {
	
	
	/**
	 * 查询用户的 评论
	 * @param userId
	 * @param reviewType 评论级别 0 全部 1 好 2 中3 差
	 * @return
	 */
	public List<ResourceReview> getMyReview(@Param("userId")Long userId,@Param("reviewType")Integer reviewType);
	
	
	
	
	/**
	 * 获取当前用户评论的资源数目
	 * @param userId
	 * @return
	 */
	public Integer getReviewedNum(Long userId);
	/**
	 * 获取当前用户所有备课夹中未进行评论的资源数目
	 * @param userId
	 * @return
	 */
	public Integer getUnReviewedNum(Long userId);
	

}