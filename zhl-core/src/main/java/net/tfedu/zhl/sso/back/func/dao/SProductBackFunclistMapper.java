package net.tfedu.zhl.sso.back.func.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;

public interface SProductBackFunclistMapper extends CoreMapper<SProductBackFunclist> {
	
	
	/**
	 * 查询角色对应的(一級)功能
	 * @param roleId
	 * @param productCode
	 * @return
	 */
	public List<SProductBackFunclist> getFuncListForRole(@Param("roleId")long roleId,@Param("productCode")String productCode);
	 
	
	/**
	 * 获取指定功能的子功能
	 * @param parentIds
	 * @return
	 */
	public List<SProductBackFunclist> getFuncListByParent(@Param("parentIds")long[] parentIds);
	
	/**
	 * 获取指定功能的子功能
	 * @param parentIds
	 * @return
	 */
	public List<SProductBackFunclist> getFuncListByTheParent(@Param("parentId")long parentId);
	
	
}