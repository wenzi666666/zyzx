package net.tfedu.zhl.cloud.teaching.discuss.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.helper.CoreMapper;

public interface TDiscussRecommendMapper extends CoreMapper<TDiscussRecommend> {
	
	
	/**
	 * 批量删除推荐班级
	 * @return
	 * @throws Exception
	 */
	public void removeRecommendRecords(@Param("ids")String[] ids) throws Exception;
	
	
	
	
}