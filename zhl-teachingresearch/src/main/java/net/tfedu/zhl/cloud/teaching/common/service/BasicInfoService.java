package net.tfedu.zhl.cloud.teaching.common.service;

import net.tfedu.zhl.cloud.teaching.term.entity.Term;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;



public interface BasicInfoService extends BaseService<Term>{

	
	/**
	 * 获取全部的学段（业务相关）t_term
	 * @return
	 * @throws Exception
	 */
	public ResultJSON getAllTerm() throws  Exception;
	
	
	
	
}
