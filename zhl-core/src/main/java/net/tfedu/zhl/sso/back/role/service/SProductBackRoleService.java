package net.tfedu.zhl.sso.back.role.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRole;

/**
 
 
 
 	后台角色管理功能业务类
  
  
  	
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface SProductBackRoleService extends BaseService<SProductBackRole> {
	/**
	 * 查询指定角色(在指定的产品功能下)的权限分配
	 * @param roleId
	 * @param productCode
	 * @return
	 * @throws Exception
	 */
	public ResultJSON queryRoleConfig(Long roleId,String productCode)
			throws Exception ;
	
	
	
	
	/**
	 * 增加(角色与功能的)分配记录
	 * @param roleId
	 * @param funcIds
	 * @return
	 * @throws Exception
	 */
	public ResultJSON addRoleConfig(Long roleId,Long[] funcIds)
			throws Exception  ;
	
	
	
	
	/**
	 * （物理）删除指定的(角色与功能的)分配记录
	 * @param roleId
	 * @param funcIds
	 * @return
	 * @throws Exception
	 */
	public ResultJSON delRoleConfig(Long roleId,Long[] funcIds)
			throws Exception ;
	
	

	/**
	 * 查询指定角色(在指定功能下)的权限分配
	 * @param productCode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ResultJSON queryRoleConfigDetail(Long funcId,Long roleId)
			throws Exception  ;
	

	/**
	 * 增加(角色与功能点的)分配记录
	 * @param roleId
	 * @param detailIds
	 * @return
	 * @throws Exception
	 */
	public ResultJSON addRoleConfigDetail(Long roleId,Long[] detailIds)
			throws Exception  ;
	
	
	
	/**
	 * （物理）删除指定的(角色与功能点的)分配记录
	 * @param roleId
	 * @param detailIds
	 * @return
	 * @throws Exception
	 */
	public ResultJSON delRoleConfigDetail(Long roleId,Long[] detailIds)
			throws Exception  ;
	
	
}
