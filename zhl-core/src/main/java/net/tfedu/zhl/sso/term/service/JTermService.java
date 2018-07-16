package net.tfedu.zhl.sso.term.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

public interface JTermService extends BaseService<JTerm> {

	
	/**
	 * 获取学科
	 * @param id
	 * @return
	 */
	public ResultJSON getSubjectBySubjectId(long id);
	/**
	 * 获取学科
	 * @param id
	 * @return
	 */
	public JSubject getSubjectById(long id);
	
	/**
	 * 根据term查询所有学科
	 * @param termId
	 * @return
	 */
	public ResultJSON getSubjectByTermId(long termId);
	
	

    /**
     * 所有学段
     * @return
     */
    public ResultJSON queryAllTerm();
    

	
}
