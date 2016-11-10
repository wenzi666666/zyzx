package net.tfedu.zhl.cloud.resource.asset.service;

import net.tfedu.zhl.cloud.resource.asset.entity.TeachingPlan;
import net.tfedu.zhl.cloud.resource.asset.entity.ZTeachingPlanContent;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * 
 * 教案专用service
 * 
 * @author wangwr
 * @date 2016年11月10日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
public interface TeachingPlanService extends BaseService<ZTeachingPlanContent> {

	/**
	 * 增加
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public ResultJSON addRecord(TeachingPlan obj) throws Exception;

	/**
	 * 修改I啊
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public ResultJSON editRecord(TeachingPlan obj) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ResultJSON delRecord(long id) throws Exception;

	/**
	 * 获取
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ResultJSON getRecord(long id) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param perPage
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ResultJSON queryRecord(int page, int perPage, long userId) throws Exception;

}
