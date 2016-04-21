package net.tfedu.zhl.cloud.resource.navigation.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.subject.entity.JSubject;

/**
 * 查询学段下的所有学科
 * 
 * @author WeiCuicui
 *
 */
public interface TermSubjectService extends BaseService<JSubject> {

    // 查询学段下的所有学科
    public ResultJSON getAllSubjectsByTerm(Long termId);

}
