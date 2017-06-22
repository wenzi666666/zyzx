package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.navigation.service.TermSubjectService;
import net.tfedu.zhl.userlayer.subject.entity.JSubject;
import net.tfedu.zhl.userlayer.term.dao.JTermMapper;

/**
 * 查询学段下的所有学科 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("termSubjectService")
public class TermSubjectServiceImpl implements TermSubjectService {

    @Resource
    JTermMapper termMapper;

    /**
     *  查询学段下的所有学科
     */
    @Override
    @Cacheable(value="bussinesscache",key="'subjectsByTerm_'+#p0")
    public List<JSubject> getAllSubjectsByTerm(Long termId) {
    	return termMapper.getSubjectsByTerm(termId);
    }
}
