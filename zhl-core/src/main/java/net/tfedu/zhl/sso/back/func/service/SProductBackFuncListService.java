package net.tfedu.zhl.sso.back.func.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclistDetail;

/**
 
 	各个产品的后台的功能管理
 	包括功能点管理
  
    @author wangwr
    @date 2017年1月16日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface SProductBackFuncListService extends BaseService<SProductBackFunclist> {
	
	
	/**
	 * 获取指定功能下的全部功能点
	 * @param funcId
	 * @return 
	 * @throws Exception
	 */
	public ResultJSON getFuncAllDetail(Long funcId) throws Exception;
	
	
	
	/**
	 * 修改指定功能点
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public ResultJSON updateFuncDetail(SProductBackFunclistDetail detail)throws Exception;
	
	
	/**
	 * (逻辑)删除指定功能点
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	public ResultJSON delFuncDetail(Long detailId)throws Exception;
	
	
	/**
	 * 添加功能点
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public ResultJSON addFuncDetail(SProductBackFunclistDetail detail)throws Exception;
	
	
	
	/**
	 * 将功能点的权限分配给指定的role
	 * @param funcId    功能点所在的功能的主键，由于检查是否给该角色分配了此功能的权限
	 * @param detailIds 待分配的功能点
	 * @param roleId    指定的角色id
	 * @return
	 * @throws Exception
	 */
	public ResultJSON addDetailRoleConfig(Long funcId,Long[] detailIds,Long roleId)throws Exception;
	

}
