package net.tfedu.zhl.cloud.teaching.discuss.dao;

import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.helper.ResultJSON;

public interface TDiscussRecommendMapper extends CoreMapper<TDiscussRecommend> {
	
	
	/**
	 * 批量删除推荐班级
	 * @return
	 * @throws Exception
	 */
	public void removeRecommendRecords(String[] ids) throws Exception;
	
	
	
	
}