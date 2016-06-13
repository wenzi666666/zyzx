package net.tfedu.zhl.cloud.teaching.teachCases.dao;

import java.util.List;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TeachCases;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface TeachCasesMapper extends CoreMapper<TeachCases> {
	
	/**
	 * 查询所有的教学案例
	 * @return
	 */
	public List<TeachCases> selectAllCases(@Param("fromFlag")int fromFlag,@Param("termId")int termId,@Param("subjectId")int subjectId);

	/**
	 * 新建一个教学案例
	 * @param teachCase
	 */
    public void addOneTeachCase(@Param("teachCase")TeachCases teachCase);
    
    /**
     * 编辑一个教学案例
     * @param teachCase
     */
    public void editOneTeachCase(@Param("teachCase")TeachCases teachCase);
    
    /**
     * 删除一个教学案例
     * @param id
     */
    public void deleteOneTeachCase(@Param("id")long id);
}