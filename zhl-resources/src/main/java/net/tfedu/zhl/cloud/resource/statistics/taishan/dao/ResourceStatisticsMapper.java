package net.tfedu.zhl.cloud.resource.statistics.taishan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ResourceStatisticsMapper {
	/**
	 * 资源动态 -- 资源上传\资源共享，取最新的三条（区、校） 参数： scope String 是 查询范围 S校、D区、W全站 scopeId
	 * String 是 相应的学校id或地区id number int 否 个数，默认为3
	 * 
	 * 用户头像 用户id 真实姓名 学校 资源名称 时间
	 */
	public List<Map<String, Object>> getResDynamic(@Param("scope") String scope, @Param("scopeId") Long scopeId,
			@Param("number") Integer number);

	/**
	 * 精品系統资源 按引用次数 排序（浏览、下载、收藏）合计 支持资源预览 参数 number int 否 个数，默认为3 返回
	 * 标题、缩略图、播放链接、浏览次数、下载次数、收藏次数,文件后缀
	 * 
	 * @param number
	 * @return
	 */
	public List<Map<String, Object>> getBastSysRes(@Param("number") Integer number);

	/**
	 * * 精品（区）校本资源 按引用次数 排序（浏览、下载、收藏）合计（按区或按校） 支持资源预览 scope String 是 查询范围
	 * S校、D区、W全站 scopeId String 是 相应的学校id或地区id number int 否 个数，默认为3 返回
	 * 标题、缩略图、播放链接、浏览次数、下载次数、收藏次数,文件后缀
	 * 
	 * @param scope
	 * @param scopeId
	 * @param number
	 * @return
	 */
	public List<Map<String, Object>> getBastScopeRes(@Param("scope") String scope, @Param("scopeId") Long scopeId,
			@Param("number") Integer number);

	/**
	 * 4资源最新上传（按区或校） scope String 是 查询范围 S校、D区、W全站 scopeId String 是 相应的学校id或地区id
	 * number int 否 个数，默认为 4
	 * 
	 * 返回 标题、缩略图、播放链接、上传时间,文件后缀
	 * 
	 */
	public List<Map<String, Object>> getLastAsset(@Param("scope") String scope, @Param("scopeId") Long scopeId,
			@Param("number") Integer number);

	/**
	 * 
	 * 5、上传资源总量（按校） 指定范围内的全部学校的上传总量的统计 scope String 是 查询范围 S校、D区、W全站 scopeId
	 * String 是 相应的学校id或地区id 学校id，学校名称、上传资源总量
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getSchoolAssetStatistics(@Param("scope") String scope,
			@Param("scopeId") Long scopeId);

}
