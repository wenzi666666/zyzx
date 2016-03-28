package net.tfedu.zhl.cloud.resource.prepare.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResZipDownRecord;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepare;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContent;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareView;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;

public interface JPrepareService {
	
	
	/**
	 * 增加备课夹
	 * @param obj
	 * @return
	 */
	public JPrepare addPrepare(JPrepare obj);
	
	
	/**
	 * 修改备课夹
	 * @param obj
	 */
	public void editPrepare(JPrepare obj);
	
	
	
	/**
	 * 删除备课夹
	 * @param id
	 */
	public void deletePrepareById(Long id);
	
	
	
	/**
	 *  增加备课夹内容
	 * @param content
	 * @return
	 */
	public JPrepareContent addPrepareContent(JPrepareContent content);
	
	/**
	 * 修改备课夹内容
	 * @param content
	 * @return
	 */	
	public void editPrepareContent(JPrepareContent content);
	
	
	/**
	 * 删除备课夹内容
	 * @param content
	 * @return
	 */
	public void deletePrepareContentById(Long id);
	
	
	
	/**
	 * 清空目标备课夹下的所有备课夹
	 * @param prepareId
	 */
	public void clearPrepareContentByPrepareId(Long prepareId);
	
	
	
	/**
	 * 获取备课夹
	 * @param tfcode
	 * @return
	 */
	public List<JPrepareView> queryPrepareList(String tfcode,Long userId);
	
	
	
	/**
	 * 获取备课夹及其时间范围
	 * @param tfcode
	 * @return
	 */
	public List<JPrepareView> queryPrepareAndTimeScopeList(String tfcode,Long userId);
	

	/**
	 * 获取备课夹内容列表
	 * @param prepareId
	 * @return
	 */
	public List<JPrepareContentView> queryPrepareContentList(Long prepareId);
	
	
	/**
	 * 删除备课夹中指定的类型的资源
	 * @param prepareId
	 * @param contType
	 * @param contId
	 */
	public void removeResourceFromPrepare(Long prepareId,Integer contType,Long contId);
	
	
	
	
	/**
	 * 无节点选择时系统资源加入备课夹接口 
	 * @param resCode
	 */
	public void addToPrepareWithOnlySysResource(String resCode,Long userId);
	
	
	
	/**
	 * 相互交换内容的排序字段
	 * @param prevId
	 * @param nextId
	 */
	public void exchangeContOrderIdx(long prevId,long nextId);
	
	
	
	
	

	/**
	 * 根据资源fromflag获取相应的 info
	 * 
	 * @param ids
	 * @param fromFlags
	 * @return
	 */
	public List<ResourceSimpleInfo> getResourceSimpleInfo(String[] ids,String[] fromFlags);
	
	
	
	
	
	
	
	
	

}
