package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;
import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.dao.TermMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.Term;
import net.tfedu.zhl.cloud.resource.navigation.service.TermSubjectService;
import net.tfedu.zhl.sso.subject.entity.JSubject;

import org.springframework.stereotype.Service;

/*
 * 查询学段下的所有学科 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("termSubjectService")
public class TermSubjectServiceImpl implements TermSubjectService {

    @Resource
    TermMapper termMapper;

    // 查询学段下的所有学科
    @Override
    public List<JSubject> getAllSubjectsByTerm(Long termId) {
        return termMapper.getSubjectsByTerm(termId);
    }

    // （测试用）
    @Override
    public Term getSubjectsByTermId(Long termId) {
        return termMapper.getSubjectsByTermId(termId);
    }
}
