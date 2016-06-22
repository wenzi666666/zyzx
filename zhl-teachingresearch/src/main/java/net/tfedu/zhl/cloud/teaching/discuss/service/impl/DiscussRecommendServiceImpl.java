package net.tfedu.zhl.cloud.teaching.discuss.service.impl;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.discuss.dao.TDiscussRecommendMapper;
import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.cloud.teaching.discuss.service.DiscussRecommendService;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Service;


@Service("disCussService")
public class DiscussRecommendServiceImpl extends BaseServiceImpl<TDiscussRecommend>  implements DiscussRecommendService  {

	
	
	@Resource
	TDiscussRecommendMapper mapper;
	
	/**
	 * 批量删除推荐班级
	 * @return
	 * @throws Exception
	 */
	public ResultJSON removeRecommendRecords(String ids) throws Exception{
//		mapper.removeRecommendRecords(ids.split(",")) ;
		return ResultJSON.getSuccess("");
	}
	
	

}
