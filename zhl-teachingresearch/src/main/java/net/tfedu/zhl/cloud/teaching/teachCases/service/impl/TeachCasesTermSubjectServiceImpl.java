package net.tfedu.zhl.cloud.teaching.teachCases.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.teaching.teachCases.dao.TGradeMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.dao.TermMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TGrade;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.Term;
import net.tfedu.zhl.cloud.teaching.teachCases.service.TeachCasesTermSubjectService;

/**
 * 教学案例中与学段、学科、年级相关的接口
 * @author WeiCuicui
 *
 */
@Service("teachCasesTermSubjectService")
public class TeachCasesTermSubjectServiceImpl implements TeachCasesTermSubjectService{

	@Autowired TermMapper  tMapper;
	@Resource TGradeMapper tGradeMapper;
	
	/**
	 * 查询所有学段
	 * @return
	 */
	public List<Term> getAllTerms(){
		return tMapper.getAllTerms();
	}
	
	/**
	 * 查询学段下的学科
	 * @return
	 */
	public List<TSubject> getSubjectsByTerm(int termId){
		return tMapper.getSubjectsByTerm(termId);
	}
	
	/**
	 * 查询所有年级
	 * @return
	 */
	public List<TGrade> getAllGrades(){
		return tGradeMapper.getAllGrades();
	}
}
