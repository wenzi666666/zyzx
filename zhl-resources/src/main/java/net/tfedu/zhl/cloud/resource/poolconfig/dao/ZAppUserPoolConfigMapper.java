package net.tfedu.zhl.cloud.resource.poolconfig.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.poolconfig.entity.ZAppUserPoolConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.CoreMapper;

public interface ZAppUserPoolConfigMapper extends CoreMapper<ZAppUserPoolConfig> {
	
	/**
	 * 查询用户有权限的资源库
	 * @param termId    学段
	 * @param subjectId 学科
	 * @param userName  用户
	 * @param appId     第三方appid
	 * @return 所有指定资源库以及用户是否有权限
	 * @throws CustomException
	 */
	public List<Map<String,Object>> getAppUserPoolConfig(@Param(value="termId") Long termId
		, @Param(value="subjectId")Long subjectId,@Param(value="userName")String userName,@Param(value="appId")String appId)
			throws CustomException;
	
	
	
	/**
	 * 资源库以及用户是否有权限
	 * @param termId    学段
	 * @param subjectId 学科
	 * @param userName  用户
	 * @param appId     第三方appid
	 * @return 所有指定资源库以及用户是否有权限
	 * @throws CustomException
	 */
	public List<Map<String,Object>> getPoolsAndAppUserConfig(@Param(value="termId")Long termId
		, @Param(value="subjectId")Long subjectId,@Param(value="userName")String userName,@Param(value="appId")String appId)
			throws CustomException;
	
}