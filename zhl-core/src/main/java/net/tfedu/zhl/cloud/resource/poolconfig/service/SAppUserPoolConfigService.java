package net.tfedu.zhl.cloud.resource.poolconfig.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.poolconfig.entity.SAppUserPoolConfig;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigIndentInfo;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.userlayer.funclist.entity.FuncListSimple;

/**
 * 
 * 第三方用户与资源库的物权管理服务
 * 
 * @author wangwr
 * @date 2017年2月21日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public interface SAppUserPoolConfigService extends BaseService<SAppUserPoolConfig> {

	/**
	 * 查询用户有权限的资源库(返回资源库的id但是缺少name)
	 * 
	 * @param termId
	 *            学段
	 * @param subjectId
	 *            学科
	 * @param userName
	 *            用户
	 * @param appId
	 *            第三方appid
	 * @return 所有指定资源库以及用户是否有权限
	 * @throws CustomException
	 */
	public List<Map<String, Object>> getAppUserPoolConfig(Long termId, Long subjectId, String userName, String appId)
			throws CustomException;

	/**
	 * 分页查询物权分配的历史记录
	 * 
	 * @param page
	 *            页码
	 * @param perPage
	 *            每页条目数
	 * @param year
	 *            开放年份
	 * @param termId
	 *            学段Id，全部时为0
	 * @param poolId 
	 * 				资源库Id，全部时为0
	 * @param subjectId 
	 * 				学科Id，全部时为0
	 * @param userName
	 *            第三方用户主键（可选）
	 * @param appId
	 *            第三方app主键
	 */
	public ResultJSON pageQueryAppUserPoolConfig(Integer page, Integer perPage, Integer year, Long termId,
			Long poolId,Long subjectId, String userName, String appId) throws CustomException;

	/**
	 * 更新用户的资源库授权
	 * 
	 * @param termId
	 *            学段id
	 * @param subjectId
	 *            学课id
	 * @param poolId
	 *            资源库Id
	 * @param appId
	 *            第三方应用主键
	 * @param userName
	 *            第三方用户主键
	 * @param months
	 *            月份数
	 * @param recordId
	 *            需要更新的记录的主键
	 */
	public ResultJSON updateAppUserPoolConfig(Long termId, Long subjectId, Integer poolId, String appId,
			String userName, Integer months, Long recordId) throws CustomException;

	/**
	 * 批量增加或修改用户的资源库物权
	 * 
	 * @param list
	 *            授权信息列表
	 * 
	 *            返回成功或失败信息
	 */
	public ResultJSON addAppUserPoolConfigBatch(List<AppUserPoolConfigIndentInfo> list) throws CustomException;

	/**
	 * 删除用户的资源库授权
	 * 
	 * @param recordId
	 *            需要更新的记录的主键
	 */
	public ResultJSON delAppUserPoolConfig(Long recordId) throws CustomException;
	
	
	/**
	 * 根据第三方用户及其学段获取有权限的资源库(返回资源库的id但是缺少name)
	 * @param appId  	第三方id
	 * @param userName	第三方用户名
	 * @param termName  学段 
	 * @return
	 * @throws CustomException
	 */
	public List<FuncListSimple> getAppUserPool(String appId, String userName,String termName)throws CustomException;
	

}
