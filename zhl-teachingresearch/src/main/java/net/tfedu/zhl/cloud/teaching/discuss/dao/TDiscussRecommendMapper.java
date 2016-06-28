package net.tfedu.zhl.cloud.teaching.discuss.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.helper.CoreMapper;

public interface TDiscussRecommendMapper extends CoreMapper<TDiscussRecommend> {
	
	
	/**
	 *  删除推荐班级列表
	 * @param ids
	 */
	public void removeRecommendRecords(@Param("ids") String[] ids);
	
	
	
}