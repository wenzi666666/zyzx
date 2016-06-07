package net.tfedu.zhl.cloud.teaching.teachCases.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.teaching.teachCases.dao.TGradeMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TGrade;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.teachCases.service.TeachCasesService;
import net.tfedu.zhl.cloud.teaching.teachCases.dao.TermMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.Term;

/**
 * 教学案例serviceImpl
 * @author WeiCuicui
 *
 */
@Service("teachCasesService")
public class TeachCasesServiceImpl implements TeachCasesService{

	@Resource TermMapper termMapper;
	@Resource TGradeMapper tGradeMapper;
	
	/**
	 * 查询所有学段
	 * @return
	 */
	public List<Term> getAllTerms(){
		return termMapper.getAllTerms();
	}
	
	/**
	 * 查询学段下的学科
	 * @return
	 */
	public List<TSubject> getSubjectsByTerm(int termId){
		return termMapper.getSubjectsByTerm(termId);
	}
	
	/**
	 * 查询所有年级
	 * @return
	 */
	public List<TGrade> getAllGrades(){
		return tGradeMapper.getAllGrades();
	}
}
