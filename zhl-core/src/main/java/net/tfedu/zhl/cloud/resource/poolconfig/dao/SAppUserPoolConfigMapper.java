package net.tfedu.zhl.cloud.resource.poolconfig.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.poolconfig.entity.SAppUserPoolConfig;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigRecord;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.CoreMapper;

public interface SAppUserPoolConfigMapper extends CoreMapper<SAppUserPoolConfig> {
	
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
	 * 查询 指定年份 增加的  、指定学段的、   指定应用 的、指定用户名 的物权记录
	 * @param year
	 * @param termId
	 * @param poolId
	 * @param poolId
	 * @param userName
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public List<AppUserPoolConfigRecord> queryAppUserPoolConfig(@Param(value="year")Integer year, @Param(value="termId")Long termId,
				@Param(value="poolId")Long poolId,@Param(value="subjectId")Long subjectId,
				@Param(value="userName")String userName, @Param(value="appId")String appId) ;
	
}