package net.tfedu.zhl.sso.term.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

public interface JTermMapper extends CoreMapper<JTerm> {
	// 根据term查询所有学科
    public List<JSubject> getSubjectsByTerm(Long termId);

    // 查询学段，除去“其他”
    public List<JTerm> getAllTerms();
    
    //题库对接，查询所有学段
    public List<JTerm> queryTerm();
    
    //题库对接，查询学段下的学科
    public List<JSubject> querySubjectByTermId(@Param("termId")long termId);
    
    /**
     * 根据名称获取对象
     * @param name
     * @return
     */
    public JTerm  getTermByName(@Param("name")String name);
    
}