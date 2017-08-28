package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FileFormat;
import net.tfedu.zhl.helper.CoreMapper;

/**
 * 查询格式
 * 
 * @author WeiCuicui
 *
 */
public interface FileFormatMapper extends CoreMapper<FileFormat> {
	
	
	/**
	 * 根据文件后缀获取对应的文件格式
	 * @param fileExt  文件后缀   例如".jpg"
	 * @return
	 */
	public FileFormat getFileFormatByFileExt(@Param("fileExt") String fileExt);
	
	
	

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
			@Param("isCollect") Integer isCollect, @Param("courseIds") List<Long> courseIds);

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
			@Param("isCollect") Integer isCollect, @Param("courseIds") List<Long> courseIds);

	/**
	 * 获取资源库对应的资源类型
	 * 
	 * @param typeId
	 *            已经选择系统资源类型的id
	 * @param poolId
	 *            资源库的id，null或0时，为全部
	 * @param exceptPoolIds
	 *            （排除）例外的资源库,为对接页面提供接口时，需要指定不适合电子书包使用的库
	 * @return
	 */
	public List<Long> getPoolType(@Param("typeId") Integer typeId, @Param("poolId") Integer poolId,
			@Param("exceptPoolIds") Long[] exceptPoolIds);

	/**
	 * 获取系统资源涉及的系统资源类型
	 * 
	 * @param poolId
	 *            指定的系统资源库, 0、4 时，取一级类型
	 * @param syscourseCodes
	 *            查询的节点的集合
	 * @param sysFrom
	 *            系统资源的来源
	 * @param typeIds
	 *            要求的资源类型
	 * @return
	 */
	public List<Map<String, Object>> getSysResourceType(@Param("poolId") Integer poolId,
			@Param("syscourseCodes") List<String> syscourseCodes, @Param("sysFromFlag") Long[] sysFromFlag,
			@Param("typeIds") List<Long> typeIds);

	/**
	 * 获取系统资源涉及的文件格式
	 * 
	 * @param poolId
	 *            指定的系统资源库, 0、4 时，取一级类型
	 * @param syscourseCodes
	 *            查询的节点的集合
	 * @param sysFrom
	 *            系统资源的来源
	 * @param typeIds
	 *            要求的资源类型
	 * @return
	 */
	public List<Map<String, Object>> getSysResourceFileFormat(@Param("poolId") Integer poolId,
			@Param("syscourseCodes") List<String> syscourseCodes, @Param("sysFromFlag") Long[] sysFromFlag,
			@Param("typeIds") List<Long> typeIds);

	/**
	 * 
	 * 获取他人共享资源的资源类型的统计信息
	 * 
	 * @param userId
	 *            当前查询用户
	 * @param searchFlag
	 *            查询方式 1按教材目录查询 2按知识点目录查询
	 * @param tfcode
	 *            教材目录 或 知识点目录 code
	 * @param sysResType
	 *            选择的系统资源的id号
	 * @param schoolId
	 *            学校id
	 * @param districtId
	 *            地区id
	 * @return
	 */
	public List<Map<String, Object>> getOtherSharedAssetType(@Param("poolId") Long userId,
			@Param("searchFlag") Integer searchFlag, @Param("tfcode") String tfcode,
			@Param("sysResType") Long sysResType, @Param("schoolId") Long schoolId,
			@Param("districtId") Long districtId);

	/**
	 * 获取他人共享资源的文件格式的统计信息
	 * 
	 * @param userId
	 *            当前查询用户
	 * 
	 * @param searchFlag
	 *            查询方式 1按教材目录查询 2按知识点目录查询
	 * @param tfcode
	 *            教材目录 或 知识点目录 code
	 * @param sysResType
	 *            选择的系统资源的id号
	 * @param schoolId
	 *            学校id
	 * @param districtId
	 *            地区id
	 * @return
	 */
	public List<Map<String, Object>> getOtherSharedAssetFormat(@Param("poolId") Long userId,
			@Param("searchFlag") Integer searchFlag, @Param("tfcode") String tfcode,
			@Param("sysResType") Long sysResType, @Param("schoolId") Long schoolId,
			@Param("districtId") Long districtId);

	/**
	 * 获取区本资源的资源类型的统计信
	 * 
	 * @param resourceFromFlag
	 *            区校资源的区分字段 3校本资源 4 区本资源
	 * @param syscourseCodes
	 *            教材目录code
	 * @param sysResType
	 *            选择的系统资源的id号
	 * @param schoolId
	 *            学校id
	 * @param districtId
	 *            地区id
	 * @return
	 */
	public List<Map<String, Object>> getDistrictResourceType(@Param("resourceFromFlag") Integer resourceFromFlag,
			@Param("syscourseCodes") List<String> syscourseCodes, @Param("sysResType") Long sysResType,
			@Param("schoolId") Long schoolId, @Param("districtId") Long districtId);

	/**
	 * 获取区本资源的文件格式的统计信息
	 * 
	 * @param resourceFromFlag
	 *            区校资源的区分字段 3校本资源 4 区本资源
	 * @param syscourseCodes
	 *            教材目录code
	 * @param sysResType
	 *            选择的系统资源的id号 预留参数
	 * @param schoolId
	 *            学校id
	 * @param districtId
	 *            地区id
	 * @return
	 */
	public List<Map<String, Object>> getDistrictResourceFormat(@Param("resourceFromFlag") Integer resourceFromFlag,
			@Param("syscourseCodes") List<String> syscourseCodes, @Param("sysResType") Long sysResType,
			@Param("schoolId") Long schoolId, @Param("districtId") Long districtId);

	/**
	 * @param userId
	 *            用户主键
	 * @param isCollect
	 *            标签 1:自建 2:收藏 3我的共享
	 * @param courseIds
	 *            用户自建目录ids
	 * @param mTypeId
	 *            指定资源类型的id
	 * @param fileFormat
	 *            文件格式的描述
	 * @return
	 */
	public List<Map<String, Object>> queryAssetList(@Param("userId") Long userId, @Param("isCollect") Integer isCollect,
			@Param("courseIds") List<Long> courseIds, @Param("mTypeId") Long mTypeId,
			@Param("fileFormat") String fileFormat);

	/**
	 * 根据文件的媒体类型获取文件的后缀
	 * 
	 * @param fileFormat
	 * @return
	 */
	public List<String> getFileExtForFileFormat(@Param("fileFormat") String fileFormat);

	/**
	 * 根据查询的系统目录节点的集合，获取关联的系统资源
	 * 
	 * @param syscourseCodes
	 * @return
	 */
	public List<String> getResCodesFromResnav(@Param("syscourseCodes") List<String> syscourseCodes);

	/**
	 * 查询系统资源
	 * 
	 * @param resCodes
	 *            code范围
	 * @param typeIds
	 *            类型范围
	 * @param fileExts
	 *            后缀的范围
	 * @param sysFromFlag
	 *            系统资源来源
	 * @param orderBy
	 *            页面排序方式 0：默认排序（displayLevel） 1:点击次数 2收藏次数 3下载次数 4评论平均得分
	 * @return
	 */
	public List<Map<String, Object>> querySysResourceList(@Param("resCodes") List<String> resCodes,
			@Param("typeIds") List<Long> typeIds, @Param("fileExts") List<String> fileExts,
			@Param("sysFromFlag") Long[] sysFromFlag, @Param("orderBy") Integer orderBy);

	/**
	 * 查询用户是否已经收藏当前系统资源
	 * 
	 * @param userId
	 * @param resId
	 * @return
	 */
	public Boolean ifSysResourceCollected(@Param("userId") Long userId, @Param("resId") Long resId);

	/**
	 * 查询指定范围的共享资源列表
	 * 
	 * @param searchFlag
	 *            共享时searchFlag查询方式 1按教材目录查询 2按知识点目录查询
	 * @param districtId
	 *            地区id,可以为null或0，但是此时schoolId必须是有效的
	 * @param schoolId
	 *            学校id,可以为null或0，但是此时districtId必须是有效的
	 * @param tfcode
	 *            tfcode
	 * @param typeIds
	 *            类型范围
	 * @param fileExts
	 *            后缀的范围
	 * @param orderBy
	 *            页面排序方式 0：默认排序（共享时间） 1:点击次数 2引用次数 3下载次数 4评论平均得分
	 * 
	 * @return
	 */
	public List<Map<String, Object>> querySharedAssetList(@Param("searchFlag") Integer searchFlag,
			@Param("districtId") Long districtId, @Param("schoolId") Long schoolId, @Param("tfcode") String tfcode,
			@Param("typeIds") List<Integer> typeIds, @Param("fileExts") List<String> fileExts,
			@Param("orderBy") Integer orderBy);

	/**
	 * 查询用户是否已经收藏当前共享资源
	 * 
	 * @param userId
	 * @param resId
	 * @return
	 */
	public Boolean ifSharedAssetCollected(@Param("userId") Long userId, @Param("resId") Long resId);

	/**
	 * 查询区校资源列表
	 * @param fromFlag
	 *            资源来源 3校本资源 4 区本资源
	 * @param districtId
	 *            地区id,可以为null或0，但是此时schoolId必须是有效的
	 * @param schoolId
	 *            学校id,可以为null或0，但是此时districtId必须是有效的
	 * @param tfcode
	 *            tfcode
	 * @param typeIds
	 *            类型范围
	 * @param fileExts
	 *            后缀的范围
	 * @param orderBy
	 *            页面排序方式 0：默认排序（共享时间） 1:点击次数 2引用次数 3下载次数 4评论平均得分
	 * @return
	 */
	public List<Map<String, Object>> queryDistrictResList(@Param("fromFlag")Integer fromFlag, 
			@Param("districtId") Long districtId, @Param("schoolId") Long schoolId, @Param("tfcode") String tfcode,
			@Param("typeIds") List<Integer> typeIds, @Param("fileExts") List<String> fileExts,
			@Param("orderBy") Integer orderBy);
	

	/**
	 * 区校资源是否已经被当前用户收藏
	 * @param userId
	 * @param resId
	 * @return
	 */
	public String ifDistrictResCollected(@Param("userId")Long userId, @Param("resId")Long resId);

}
