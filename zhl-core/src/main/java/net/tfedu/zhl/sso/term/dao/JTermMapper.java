package net.tfedu.zhl.sso.term.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

public interface JTermMapper extends CoreMapper<JTerm> {
	// 根据term查询所有学科
    public List<JSubject> getSubjectsByTerm(Long termId);

    // 查询所有学段
    public List<JTerm> getAllTerms();
}