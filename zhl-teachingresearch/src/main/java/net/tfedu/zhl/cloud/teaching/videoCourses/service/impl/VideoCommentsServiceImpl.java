package net.tfedu.zhl.cloud.teaching.videoCourses.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.teachCases.dao.TGradeMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.dao.TermMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TGrade;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.Term;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoCommentsService;

import org.springframework.stereotype.Service;

/**
 * 视频课程评论的相关接口
 * @author WeiCuicui
 *
 */
@Service("videoCommentsService")
public class VideoCommentsServiceImpl implements VideoCommentsService{
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
