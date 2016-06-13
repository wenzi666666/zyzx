package net.tfedu.zhl.cloud.teaching.teachCases.service;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TeachCases;
import net.tfedu.zhl.helper.PaginationHelper;


public interface TeachCasesService {

	/**
	 * 根据所属平台、学段、学科，查询所有的教学案例
	 * @param fromFlag  0 双课堂； 1 资源中心
	 * @param termId
	 * @param subjectId
	 * @param page
	 * @param perPage
	 * @return
	 */
	public PaginationHelper<TeachCases> selectAllCases(int fromFlag,int termId,int subjectId,int page,int perPage);
	
	/**
	 * 新增一个教学案例
	 * @param teachCase
	 */
	public void createOneTeachCase(String teachCase,long userId);
	
	/**
	 * 编辑一个教学案例
	 * @param teachCase
	 * @param userId
	 */
	public void editOneTeachCase(String teachCase,long userId);
	
	/**
	 * 删除一个教学案例
	 * @param caseId
	 */
	public void deleteOneTeachCase(long caseId);
}
