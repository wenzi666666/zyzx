package net.tfedu.zhl.cloud.resource.resSearch.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

public interface ResSearchService {

	/**
	 * 跨库检索资源
	 * 
	 * @param fromFlag
	 * @param sys_from
	 * @param searchKeyword
	 * @param format
	 * @param page
	 * @param perPage
	 * @return
	 */

	public Pagination<ResSearchResultEntity> getResources(int fromFlag, List<Integer> sys_from, String searchKeyword,
			String format, int page, int perPage, long userId, int expire);

	/**
	 * 查询资源格式
	 * 
	 * @param searchKeyword
	 * @param fromFlag
	 * @param sys_from
	 * @return
	 */
	public List<String> getFileFormats(String searchKeyword, int fromFlag, List<Integer> sys_from, long userId);

	/**
	 * 分页查询指定资源库下的系统资源
	 * 
	 * @param respool
	 * @param searchKeyword
	 * @param page
	 * @param perPage
	 * @param expire
	 * @return
	 */
	public Pagination<ResSearchResultEntity> querySysResource(List<Integer> sys_from, int respool, String searchKeyword,
			int page, int perPage, int expire);

	/**
	 * 批量查询 （不同类型的）资源的信息
	 * 
	 * @param assetIds
	 *            自建资源id集合
	 * @param sysResourceIds
	 *            系统资源id集合
	 * @param districtResIds
	 *            区校资源id集合
	 * @return 返回的信息以3.0对接云平台的标准为准 fromflag, isfinished,'asset_res' as
	 *         restype,rescode,assetid,assetname,fpath,assetpath,assetdesc,isdwj
	 */
	public List<Map<String, Object>> queryBatchResourceInfo(Long[] assetIds, Long[] sysResourceIds,
			Long[] districtResIds);

	/**
	 * 获取用户自建资源涉及的系统资源类型\文件格式
	 * 
	 * @param userId
	 *            用户
	 * @param isCollect
	 *            标签 1:自建 2:收藏 3我的共享
	 * @param courseIds
	 *            自建目录的id
	 * @return
	 */
	public List<Map<String, Object>> getAssetFileTypeAndFormat(Long userId, Integer isCollect, List<Long> courseIds);

	/**
	 * 获取系统资源涉及的系统资源类型\文件格式
	 * 
	 * @param typeId
	 *            已经选择系统资源类型的id
	 * @param exceptPoolIds
	 *            系统要求排除的资源库的id的集合(对接接口中排除多媒体教辅库)
	 * @param poolId
	 *            查询的6大库的id，null或0时，查询全部库
	 * @param syscourseCodes查询的系统目录的范围
	 * @param sysFrom
	 *            系统资源表中的fromflag字段,区分系统资源的来源（知好乐、梯子网等）
	 * @return
	 */
	public List<Map<String, Object>> getSysResourceTypeAndFormat(Long typeId, String exceptPoolIds, Integer poolId,
			List<String> syscourseCodes, String sysFrom);

	/**
	 * 获取他人共享资源的资源类型和文件格式的统计信息 取合集
	 * 
	 * @param userId
	 *            当前查询用户
	 * @param searchFlag
	 *            查询方式 1按教材目录查询 2按知识点目录查询
	 * @param tfcode
	 *            教材目录 或 知识点目录 code
	 * @param sysResType
	 *            选择的系统资源的id号
	 * @return
	 */
	public List<Map<String, Object>> getOtherSharedAssetTypeAndFormat(Long userId, Integer searchFlag, String tfcode,
			Long sysResType);

	/**
	 * 获取区本资源的资源类型和文件格式的统计信息 取合集
	 * 
	 * @param userId
	 *            用户的id，用于确定区校信息
	 * @param resourceFromFlag
	 *            区校资源的区分字段 3校本资源 4 区本资源
	 * @param syscourseCodes
	 *            教材目录code
	 * @param sysResType
	 *            选择的系统资源的id号
	 * @return
	 */
	public List<Map<String, Object>> getDistrictResourceTypeAndFormat(Long userId, Integer resourceFromFlag,
			List<String> syscourseCodes, Long sysResType);

	/**
	 * 分页获取获取用户自建资源
	 * @param userId    用户主键
	 * @param isCollect   标签 1:自建 2:收藏 3我的共享
	 * @param courseIds   用户自建目录ids
	 * @param page        页码 
	 * @param perPage		页面条目数
	 * @param mtype			指定资源类型的id
	 * @param fileFormat	文件格式的描述
	 * @return
	 */
	public List<Map<String, Object>> queryAssetList(Long userId, Integer isCollect, List<Long> courseIds, Integer page,
			Integer perPage, Long mtype, String fileFormat);

}
