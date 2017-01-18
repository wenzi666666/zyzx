package net.tfedu.zhl.sso.back.func.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;
import net.tfedu.zhl.sso.back.role.bean.SProductBackFuncInfoPermissionView;

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
	
	
	
	/**
	 * 查询角色在指定产品的功能权限
	 * @param roleId       
	 * @param productCode  产品编码
	 * @return 
	 *  
	 */
	public List<SProductBackFuncInfoPermissionView> 
					getRolePremissionAboutProductTopFunc(@Param("roleId")Long roleId, @Param("productCode")String productCode);

	
	/**
	 * 查询角色在指定功能的子功能下的权限
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	public List<SProductBackFuncInfoPermissionView> 
					getRolePremissionAboutProductChildrenFunc(@Param("parentId")Long parentId, @Param("roleId")Long roleId);

	
	
	/**
	 * 获取角色在指定功能的所有功能点操作的权限信息
	 * @param funcId  指定功能id
	 * @param roleId  角色id
	 * @return
	 * @throws Exception
	 */
	public List<SProductBackFuncInfoPermissionView> getRolePermissionAboutFuncDetail(@Param("funcId")Long funcId, @Param("roleId")Long roleId) throws Exception;
	
	
	
	
}