package net.tfedu.zhl.cloud.teaching.discuss.dao;

import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussLog;
import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.helper.ResultJSON;

public interface TDiscussLogMapper extends CoreMapper<TDiscussLog> {
	
	
	/**
	 * 获取最新访问列表
	 * @param userId
	 * @return
	 */
	public ResultJSON getReadLog(Long userId) throws Exception;
	
}