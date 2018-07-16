package net.tfedu.zhl.cloud.resource.intergral.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.intergral.entity.UserResourceIntergralRecord;
import net.tfedu.zhl.helper.CoreMapper;

public interface UserResourceIntergralRecordMapper extends CoreMapper<UserResourceIntergralRecord> {

	/**
	 * 用户共享资源 获取的积分总数
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getIntegralIncrementByShare(@Param("userId") Long userId);

	/**
	 * 用户推荐资源 获取的积分总数
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getIntegralIncrementByRecommend(@Param("userId") Long userId);

	/**
	 * 获取资源消费的总积分
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getIntegralDecrement(@Param("userId") Long userId);
	/**
	 * 分页查询用户积分记录
	 * @param userId
	 * @return
	 */
	public List<Map<String,Object>> getUserIntegralRecord(@Param("userId") Long userId);
	
}