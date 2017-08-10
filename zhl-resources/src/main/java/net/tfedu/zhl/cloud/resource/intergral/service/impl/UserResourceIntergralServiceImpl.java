package net.tfedu.zhl.cloud.resource.intergral.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.intergral.constant.ResourceIntegralConstants;
import net.tfedu.zhl.cloud.resource.intergral.dao.ResourceIntergralOperateTypeMapper;
import net.tfedu.zhl.cloud.resource.intergral.dao.UserResourceIntergralMapper;
import net.tfedu.zhl.cloud.resource.intergral.dao.UserResourceIntergralRecordMapper;
import net.tfedu.zhl.cloud.resource.intergral.entity.UserResourceIntergral;
import net.tfedu.zhl.cloud.resource.intergral.entity.UserResourceIntergralRecord;
import net.tfedu.zhl.cloud.resource.intergral.service.UserResourceIntergralService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
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
@Service("userResourceIntergralService")
public class UserResourceIntergralServiceImpl extends BaseServiceImpl<UserResourceIntergral>
		implements UserResourceIntergralService {

	@Autowired
	UserResourceIntergralMapper mapper;

	@Autowired
	UserResourceIntergralRecordMapper recordMapper;

	@Autowired
	ResourceIntergralOperateTypeMapper operTypeMapper;

	@Override
	public UserResourceIntergral getUserIntegral(Long userId) {

		UserResourceIntergral record = new UserResourceIntergral();
		record.setUserid(userId);

		return mapper.selectOne(record);
	}

	/**
	 * 用于判断用户的对当前资源是否需要消耗积分，并且积分是否充足
	 * 
	 * integralExpendFlag 是否需要消耗积分，true 是 （提示积分的消费），false 否
	 * 可直接下载或引用，当为false时，以下两个返回值为undefined
	 * 
	 * integralEnoughFlag 积分是否充足，true 是 （提示积分的消费），false 否 提示积分不足
	 * 
	 * userUseableIntegral 用户可用积分 //判断是否需要消费积分（sharedAssetIntegral是否为0）
	 * //判断是否共享资源的积分已经支付过 (该资源历史上是否已经消耗过当前用户积分 ) //当前用户的可用积分是否充足
	 */
	@Override
	public ResultJSON isUserIntegralEnough(Long userId, Long sharedAssetId, Integer sharedAssetIntegral,
			Long sharedUserId) throws Exception {

		boolean integralExpendFlag = true;
		boolean integralEnoughFlag = false;
		int userUseableIntegral = 0;
		Map<String, Object> result = new HashMap<String, Object>();
		// 判断是否需要消费积分（sharedAssetIntegral是否为0）
		if (sharedAssetIntegral > 0 && userId.longValue() != sharedUserId.longValue()) {
			// 判断是否共享资源的积分已经支付过 (该资源历史上是否已经消耗过当前用户积分 ),未支付时继续
			if (!ifSharedAssetScoreBeExpended(userId, sharedAssetId)) {
				// 当前用户的可用积分是否充足
				UserResourceIntergral u = getUserIntegral(userId);
				if (u != null) {
					userUseableIntegral = u.getUseableScore();
					integralEnoughFlag = (userUseableIntegral >= sharedAssetIntegral);
					result.put("userUseableIntegral", userUseableIntegral);
				}
			} else {
				integralExpendFlag = false;
			}
		} else {
			integralExpendFlag = false;
		}
		result.put("integralExpendFlag", integralExpendFlag);
		result.put("integralEnoughFlag", integralEnoughFlag);
		return ResultJSON.getSuccess(result);
	}

	/**
	 * * 用于判断用户的对当前资源是否需要消耗积分，并且积分是否充足,并返回真正需要消耗积分的集合
	 * 
	 * integralExpendFlag 是否需要消耗积分，true 是 （提示积分的消费），false 否
	 * 可直接下载或引用，当为false时，以下两个返回值为undefined
	 * 
	 * integralEnoughFlag 积分是否充足，true 是 （提示积分的消费），false 否 提示积分不足
	 * 
	 * userUseableIntegral 用户可用积分 //判断是否需要消费积分（sharedAssetIntegral是否为0）
	 * //判断是否共享资源的积分已经支付过 (该资源历史上是否已经消耗过当前用户积分 ) //当前用户的可用积分是否充足
	 * 
	 * @param userId
	 * 
	 * @param sharedAssetIds
	 * @param sharedAssetIntegrals
	 * @param sharedUserIds
	 */
	@Override
	public ResultJSON isUserIntegralEnough(Long userId, String[] sharedAssetIds, String[] sharedAssetIntegrals,
			String[] sharedUserIds) throws Exception {

		if (sharedAssetIds == null || sharedAssetIds.length == 0) {
			throw new ParamsException();
		}

		if (sharedAssetIds.length != sharedAssetIntegrals.length
				|| sharedAssetIntegrals.length != sharedUserIds.length) {
			throw new ParamsException();
		}

		Map<String, Object> result = new HashMap<String, Object>();

		// integralExpendFlag 是否需要消耗积分，true 是 （提示积分的消费），false 否
		// 可直接下载或引用，当为false时，以下两个返回值为undefined
		boolean integralExpendFlag = false;
		// integralEnoughFlag 积分是否充足，true 是 （提示积分的消费），false 否 提示积分不足
		boolean integralEnoughFlag = false;
		// userUseableIntegral 用户可用积分
		int userUseableIntegral = 0;

		// 获取用户的积分
		UserResourceIntergral u = getUserIntegral(userId);
		userUseableIntegral = u != null ? u.getUseableScore() : 0;

		long sharedUserId = 0;
		long sharedAssetId = 0;
		int sharedAssetIntegral = 0;
		int score_all_needed = 0;// 所有需要的总积分

		// 以下3个StringBuffer用于收集 真正需要进行积分处理的数据的集合
		StringBuffer assetUserIds = new StringBuffer();
		StringBuffer assetIntegrals = new StringBuffer();
		StringBuffer assetIds = new StringBuffer();

		for (int i = 0; i < sharedUserIds.length; i++) {

			sharedUserId = Long.parseLong(sharedUserIds[i]);
			sharedAssetId = Long.parseLong(sharedAssetIds[i]);
			sharedAssetIntegral = Integer.parseInt(sharedAssetIntegrals[i]);

			//// 判断是否需要消费积分（sharedAssetIntegral是否为0）
			if (sharedAssetIntegral == 0 || userId.longValue() == sharedUserId) {
				integralExpendFlag = false;
				continue;
			}

			// 判断是否共享资源的积分已经支付过 (该资源历史上是否已经消耗过当前用户积分 ),未支付即false时继续
			if (!ifSharedAssetScoreBeExpended(userId, sharedAssetId)) {
				integralExpendFlag = true;
				// 当前用户的可用积分是否充足
				score_all_needed += sharedAssetIntegral;

				if (assetUserIds.length() > 0) {
					assetUserIds.append(",");
				}
				assetUserIds.append(sharedUserId);

				if (assetIntegrals.length() > 0) {
					assetIntegrals.append(",");
				}
				assetIntegrals.append(sharedAssetIntegral);

				if (assetIds.length() > 0) {
					assetIds.append(",");
				}
				assetIds.append(sharedAssetId);

			}
		}

		result.put("userUseableIntegral", userUseableIntegral);
		result.put("integralExpendFlag", integralExpendFlag);
		result.put("integralEnoughFlag", userUseableIntegral >= score_all_needed);
		result.put("integralAllNeeded", score_all_needed);
		result.put("sharedAssetIds", assetIds);
		result.put("sharedUserIds", assetUserIds);
		result.put("sharedAssetIntegrals", assetIntegrals);
		return ResultJSON.getSuccess(result);
	}

	@Override
	public Boolean userIntegralExpend(Long userId, Long sharedUserId, Long sharedAssetId, Integer sharedAssetIntegral)
			throws Exception {

		Boolean result = false;

		Date date = Calendar.getInstance().getTime();
		// 用户消费积分记录
		UserResourceIntergralRecord decrease = new UserResourceIntergralRecord();
		// 资源创建人的积分增加记录
		UserResourceIntergralRecord increase = new UserResourceIntergralRecord();

		decrease.setCreateman(userId);
		decrease.setCreatetime(date);
		decrease.setFlag(false);
		decrease.setInvalidtime(null);
		decrease.setOperateid(sharedAssetId);
		decrease.setOperatetype(ResourceIntegralConstants.DOWN_SHAREDRES_CODE);
		decrease.setScore(sharedAssetIntegral);
		// 复制相同属性
		BeanUtils.copyProperties(increase, decrease);
		// 分别设置积分增减的用户id
		decrease.setUserid(userId);
		increase.setUserid(sharedUserId);

		// 将消费积分记录中的分值设为负数
		decrease.setScore(0 - decrease.getScore());

		if (ifSharedAssetScoreBeExpended(userId, sharedAssetId)) {
			result = addUserIntegralRecord(decrease)

					&& decreaseUserIntegral(userId, sharedAssetIntegral)

					&& addUserIntegralRecord(increase)

					&& increaseUserIntegral(sharedUserId, sharedAssetIntegral);

			;
		} else {
			result = true;
		}

		return result;

	}

	@Override
	public Boolean userIntegralExpend(Long userId, String[] sharedUserIds, String[] sharedAssetIds,
			String[] sharedAssetIntegrals) throws Exception {

		if (sharedAssetIds == null || sharedAssetIds.length == 0) {
			throw new ParamsException();
		}

		if (sharedAssetIds.length != sharedAssetIntegrals.length
				|| sharedAssetIntegrals.length != sharedUserIds.length) {
			throw new ParamsException();
		}

		Boolean result = true;

		Date date = Calendar.getInstance().getTime();

		List<UserResourceIntergralRecord> decreaseList = new ArrayList<UserResourceIntergralRecord>();
		List<UserResourceIntergralRecord> increaseList = new ArrayList<UserResourceIntergralRecord>();
		// 用户消费积分记录
		UserResourceIntergralRecord decrease = null;
		// 资源创建人的积分增加记录
		UserResourceIntergralRecord increase = null;

		long sharedUserId = 0;
		long sharedAssetId = 0;
		int sharedAssetIntegral = 0;

		for (int i = 0; i < sharedAssetIntegrals.length; i++) {
			sharedUserId = Long.parseLong(sharedUserIds[i]);
			sharedAssetId = Long.parseLong(sharedAssetIds[i]);
			sharedAssetIntegral = Integer.parseInt(sharedAssetIntegrals[i]);

			if (ifSharedAssetScoreBeExpended(userId, sharedAssetId)) {
				continue;
			}

			decrease = new UserResourceIntergralRecord();
			increase = new UserResourceIntergralRecord();

			decrease.setCreateman(userId);
			decrease.setCreatetime(date);
			decrease.setFlag(false);
			decrease.setInvalidtime(null);
			decrease.setOperateid(sharedAssetId);
			decrease.setOperatetype(ResourceIntegralConstants.DOWN_SHAREDRES_CODE);
			decrease.setScore(sharedAssetIntegral);
			
			
			// 复制相同属性
			ConvertUtils.register(new DateLocaleConverter(Locale.SIMPLIFIED_CHINESE,"yyyy-MM-dd HH:mm:ss"), Date.class);
			BeanUtils.copyProperties(increase, decrease);
			// 分别设置积分增减的用户id
			decrease.setUserid(userId);
			increase.setUserid(sharedUserId);

			// 将消费积分记录中的分值设为负数
			decrease.setScore(0 - decrease.getScore());

			decreaseList.add(decrease);
			increaseList.add(increase);

		}

		// 都支付过，decreaseList 为空集合
		if (decreaseList.size() == 0) {
			return true;
		}

		for (int i = 0; i < decreaseList.size(); i++) {
			decrease = decreaseList.get(i);
			increase = increaseList.get(i);

			result = result && addUserIntegralRecord(decrease)

					&& decreaseUserIntegral(userId, sharedAssetIntegral)

					&& addUserIntegralRecord(increase)

					&& increaseUserIntegral(sharedUserId, sharedAssetIntegral);

			;

		}

		return result;

	}

	@Override
	public Pagination list(Long userId, Integer curPage, Integer numPerPage) throws Exception {

		return null;
	}

	@Override
	public Map<String, Object> staticUserIntegral(Long userId) throws Exception {

		UserResourceIntergral obj = getUserIntegral(userId);
		int increase_share = getIntegralIncrementByShare(userId);
		int increase_recommend = getIntegralIncrementByRecommend(userId);
		int decrease = getIntegralDecrement(userId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("useable_integral", obj.getUseableScore());
		map.put("increase_share", increase_share);
		map.put("increase_recommend", increase_recommend);
		map.put("decrease", decrease);

		return map;
	}

	/**
	 * 是否共享资源的积分已经支付过 ,支付过 则返回true
	 * 
	 * 
	 * @param sharedAssetId
	 * @param userId
	 */
	private Boolean ifSharedAssetScoreBeExpended(Long userId, Long sharedAssetId) {

		UserResourceIntergralRecord record = new UserResourceIntergralRecord();
		record.setFlag(false);
		record.setCreateman(userId);
		record.setOperateid(sharedAssetId);
		record.setOperatetype(ResourceIntegralConstants.DOWN_SHAREDRES_CODE);

		List<UserResourceIntergralRecord> history = recordMapper.select(record);

		return null != history && history.size() > 0;
	}

	/**
	 * 增加用户积分记录
	 * 
	 * @param record
	 * 
	 * 
	 *            (userid,createman,score,createtime,invalidtime,flag,
	 *            operatetype,operateid)
	 */
	private Boolean addUserIntegralRecord(UserResourceIntergralRecord record) {

		recordMapper.insert(record);

		return true;
	}

	/**
	 * 增加用户的积分
	 * 
	 * @param userId
	 * 
	 * @param score
	 *            增加的分值
	 * @return
	 * @throws Exception
	 * @throws NamingException
	 * @throws SQLException
	 */
	private boolean increaseUserIntegral(Long userId, int score) {

		UserResourceIntergral record = getUserIntegral(userId);
		
		if(null == record){
			record = new UserResourceIntergral();
			record.setTotalScore(score);
			record.setUseableScore(score);
			record.setUserid(userId);
			mapper.insert(record);
			
		}else{
			
			record.setTotalScore(record.getTotalScore() + score);
			record.setUseableScore(record.getUseableScore() + score);
			
			mapper.updateByPrimaryKey(record);
		}


		return true;

	}

	/**
	 * 减少用户的积分
	 * 
	 * @param userId
	 * @param score
	 *            减少的分值
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	private boolean decreaseUserIntegral(Long userId, int score) {

		UserResourceIntergral record = getUserIntegral(userId);

		record.setTotalScore(record.getTotalScore() - score);
		record.setUseableScore(record.getUseableScore() - score);

		mapper.updateByPrimaryKey(record);

		return true;
	}

	/**
	 * 获取用户共享资源 获取的积分
	 * 
	 * @param userId
	 * @param dbId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int getIntegralIncrementByShare(long userId) {

		return recordMapper.getIntegralIncrementByShare(userId);

	}

	/**
	 * 获取用户推荐资源 获取的积分
	 * 
	 * @param userId
	 * @param dbId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int getIntegralIncrementByRecommend(long userId) {

		return recordMapper.getIntegralIncrementByRecommend(userId);

	}

	/***
	 * 获取资源消费的总积分
	 * 
	 * @param userId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public int getIntegralDecrement(long userId) {

		return recordMapper.getIntegralDecrement(userId);

	}

}
