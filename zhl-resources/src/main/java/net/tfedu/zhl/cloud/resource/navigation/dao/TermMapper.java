package net.tfedu.zhl.cloud.resource.navigation.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.cloud.resource.navigation.entity.Term;

/**
 * 学段的 dao
 * 
 * @author WeiCuicui
 *
 */
public interface TermMapper extends CoreMapper<JTerm> {

    // 根据term查询所有学科
    public List<JSubject> getSubjectsByTerm(Long termId);

    // 查询所有学段
    public List<JTerm> getAllTerms();

    // 根据学段，查询学科（测试用）
    public Term getSubjectsByTermId(Long termId);
}