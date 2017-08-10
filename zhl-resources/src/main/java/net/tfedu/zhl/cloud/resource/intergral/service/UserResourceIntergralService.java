package net.tfedu.zhl.cloud.resource.intergral.service;

import java.util.Map;

import net.tfedu.zhl.cloud.resource.intergral.entity.UserResourceIntergral;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年8月9日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public interface UserResourceIntergralService extends BaseService<UserResourceIntergral> {

	/**
	 * 
	 * 获取当前用户的积分
	 * 
	 * @param userId
	 *            用户主键
	 */
	public UserResourceIntergral getUserIntegral(Long userId) throws Exception;

	/**
	 * 当前用户的积分是否充足
	 * 
	 * @param userId
	 * @param sharedAssetId
	 *            需要应用的资源
	 * @param sharedAssetIntegral
	 *            需要消耗的积分
	 * @param sharedUserId
	 *            共享资源的人的id
	 */
	public ResultJSON isUserIntegralEnough(Long userId, Long sharedAssetId, Integer sharedAssetIntegral,
			Long sharedUserId) throws Exception;

	/**
	 * 当前用户的积分是否充足
	 * 
	 * @param userId
	 * @param sharedAssetIds
	 *            需要应用的资源
	 * @param sharedAssetIntegrals
	 *            需要消耗的积分
	 * @param sharedUserIds
	 *            共享资源的人的id
	 */
	public ResultJSON isUserIntegralEnough(Long userId, String[] sharedAssetId, String[] sharedAssetIntegral,
			String[] sharedUserId) throws Exception;

	/**
	 * 用户消费积分，并返回成功或失败信息
	 * 
	 * @param userId
	 * @param sharedAssetId
	 * @param sharedUserId
	 * @param sharedAssetIntegral
	 */
	public Boolean userIntegralExpend(Long userId, Long sharedUserId, Long sharedAssetId, Integer sharedAssetIntegral)
			throws Exception;

	/**
	 * 用户消费积分，并返回成功或失败信息
	 * 
	 * @param userId
	 * @param sharedAssetId
	 * @param sharedUserId
	 * @param sharedAssetIntegral
	 */
	public Boolean userIntegralExpend(Long userId, String[] sharedUserId, String[] sharedAssetId,
			String[] sharedAssetIntegral) throws Exception;

	/**
	 * 
	 * 查询用户的(消费积分的)历史记录
	 * 
	 * @param userId
	 *            用户
	 * @param curPage
	 *            页码
	 * @param numPerPage
	 *            页面条目数
	 */
	public Pagination list(Long userId, Integer curPage, Integer numPerPage) throws Exception;

	/**
	 * 统计用户的积分
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> staticUserIntegral(Long userId) throws Exception;

}
