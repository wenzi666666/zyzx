package net.tfedu.zhl.cloud.resource.prepare.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.prepare.entity.FirstNavigationInfo;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepare;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareView;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.cloud.resource.prepare.entity.UserPrepareStatisInfo;
import net.tfedu.zhl.helper.CoreMapper;

public interface JPrepareMapper extends CoreMapper<JPrepare> {
	

	
	/**
	 * 清空目标备课夹下的所有备课夹内容
	 * @param prepareId
	 */
	public void clearPrepareContentByPrepareId(Long prepareId);
	
	
	
	/**
	 * 将默认0的排序字段设置为id的值
	 */
	public void update_default_prepare_order();
	
	
	/**
	 * 删除备课夹中指定的类型的资源
	 * @param prepareId
	 * @param contType
	 * @param contId
	 */
	public void removeResourceFromPrepare(Long prepareId,Integer contType,Long contId);
	
	
		
	
	/**
	 * 更新内容的排序字段
	 */
	public void update_default_prepare_content_order();
	
	
	
	
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
	 * 获取备课夹内容列表(其中缩略图、文件后缀需要工具类继续处理)
	 * @param prepareId
	 * @return
	 */
	public List<JPrepareContentView> queryPrepareContentList(Long prepareId);
	
	

	
	/**
	 * 获取系统资源的第一个关联节点的信息
	 * @param resCode
	 */
	public FirstNavigationInfo getFirstNavigationForSysResource(String resCode);
	
	
	
	/**
	 * 获取自建资源的信息
	 * @param id
	 * @return
	 */
	public ResourceSimpleInfo getAssetInfo(Long id);
	
	/**
	 * 获取系统资源的信息
	 * @param id
	 * @return
	 */
	public ResourceSimpleInfo getResourceInfo(Long id);

	
	/**
	 * 获取区校本资源的信息
	 * @param id
	 * @return
	 */
	public ResourceSimpleInfo getDistrictORSchoolResInfo(Long id);

	
	
	
	/**
	 * 获取用户的备课夹统计(部分)
	 * 只 获取tfcode、title、resourcenums
	 * @param userId
	 * @return
	 */
	public List<UserPrepareStatisInfo> getUserPrepareStatisButPartResult(Long userId);
	
	
	
	/**
	 * 获取教材的已经备完的课程、  需要备课的课程数
	 * @param info
	 * @return
	 */
	public  UserPrepareStatisInfo getBookPrepareStatis(@Param("userId")Long userId,@Param("tfcode")String tfcode);
	
	
}