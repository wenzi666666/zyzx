package net.tfedu.zhl.cloud.teaching.teachCases.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.teachCases.dao.TeachCasesMapper;
import net.tfedu.zhl.cloud.teaching.teachCases.entity.TeachCases;
import net.tfedu.zhl.cloud.teaching.teachCases.service.TeachCasesService;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.PaginationHelper;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

/**
 * 教学案例serviceImpl
 * @author WeiCuicui
 *
 */
@Service("teachCasesService")
public class TeachCasesServiceImpl implements TeachCasesService{

	@Resource TeachCasesMapper teachCasesMapper;
	/**
	 * 根据所属平台、学段、学科，查询所有的教学案例
	 * @param fromFlag  0 双课堂； 1 资源中心
	 * @param termId
	 * @param subjectId
	 * @param page
	 * @param perPage
	 * @return
	 */
	public PaginationHelper<TeachCases> selectAllCases(int fromFlag,int termId,int subjectId,int page,int perPage){
		
		PageHelper.startPage(page,perPage);
		
		List<TeachCases> list = teachCasesMapper.selectAllCases(fromFlag, termId, subjectId);
		
		return PaginationHelper.transfer(list);
	}
	
	/**
	 * 新增一个教学案例
	 * @param teachCase
	 */
	public void createOneTeachCase(String teachCase,long userId){
		TeachCases item = new TeachCases();
		item = JsonUtil.getInstance().fromJson(teachCase, TeachCases.class);
		item.setCreator(userId);
		teachCasesMapper.addOneTeachCase(item);
	}
	
	/**
	 * 编辑一个教学案例
	 * @param teachCase
	 * @param userId
	 */
	public void editOneTeachCase(String teachCase,long userId){
		TeachCases item = new TeachCases();
		item = JsonUtil.getInstance().fromJson(teachCase, TeachCases.class);
		item.setCreator(userId);
		
		teachCasesMapper.editOneTeachCase(item);
	}
	
	
	/**
	 * 删除一个教学案例
	 * @param caseId
	 */
	public void deleteOneTeachCase(long caseId){
		teachCasesMapper.deleteOneTeachCase(caseId);
	}
	
}
