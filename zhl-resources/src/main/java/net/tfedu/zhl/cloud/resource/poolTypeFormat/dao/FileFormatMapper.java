package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FileFormat;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 查询格式
 * 
 * @author WeiCuicui
 *
 */
public interface FileFormatMapper extends CoreMapper<FileFormat> {

	/**
	 * 系统资源，根据资源ids和typeIds，查询得到资源格式
	 * 
	 * @param resourceIds
	 * @param typeIds
	 * @return
	 */
	public List<String> getSysResFormatsByMType(@Param("tfcode") String tfcode,
			@Param("sys_from") List<Integer> sys_from, @Param("typeIds") List<Integer> typeIds);

	/**
	 * 区本校本资源，根据资源ids和typeIds，查询得到资源格式
	 * 
	 * @param resourceIds
	 * @param fromFlag
	 * @return
	 */
	public List<String> getDisResFormatsByMType(@Param("mtype") int mtype, @Param("tfcode") String tfcode,
			@Param("fromFlag") int fromFlag, @Param("schoolId") long schoolId, @Param("districtId") long districtId);

	/**
	 * 查询全部资源格式
	 * 
	 * @return
	 */
	public List<String> getAllFileFormat();

	/**
	 * 获取用户自建资源涉及的文件格式
	 * 
	 * @param userId
	 *            用户
	 * @param isCollect
	 *            标签 0:所有 1:自建 2:收藏 3我的共享
	 * @param courseIds
	 *            自建目录的id
	 * @return
	 */
	public List<Map<String, Object>> getAssetFileFormat(@Param("userId") Long userId,
			@Param("userId") Integer isCollect, @Param("userId") List<Long> courseIds);

	/**
	 * 获取用户自建资源涉及的系统资源类型
	 * 
	 * @param userId
	 *            用户
	 * @param isCollect
	 *            标签 0:所有 1:自建 2:收藏 3我的共享
	 * @param courseIds
	 *            自建目录的id
	 * @return
	 */
	public List<Map<String, Object>> getAssetResourceType(@Param("userId") Long userId,
			@Param("userId") Integer isCollect, @Param("userId") List<Long> courseIds);
	
	
	
	/**
	 * 获取资源库对应的资源类型
	 * @param poolId			资源库的id，null或0时，为全部
	 * @param exceptPoolIds     （排除）例外的资源库,为对接页面提供接口时，需要指定不适合电子书包使用的库
	 * @return
	 */
	public List<Long> getPoolType(@Param("poolId")Long poolId,@Param("exceptPoolIds")Long[] exceptPoolIds);
	
	
	
	
	
	

}
