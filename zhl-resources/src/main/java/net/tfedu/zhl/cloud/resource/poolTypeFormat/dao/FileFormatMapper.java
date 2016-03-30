package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FileFormat;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;
/**
 * 查询格式
 * @author WeiCuicui
 *
 */
public interface FileFormatMapper extends CoreMapper<FileFormat> {
	
	//系统资源，根据资源ids和typeIds，查询得到资源格式
	public List<String> getSysResFormatsByMType(@Param("resourceIds") List<Long> resourceIds,@Param("typeIds") List<Integer> typeIds);
	
	//区本校本资源，根据资源ids和typeIds，查询得到资源格式
	public List<String> getDisResFormatsByMType(@Param("resourceIds") List<Long> resourceIds,@Param("fromFlag") int fromFlag);
	
	//根据资源格式，查询所有后缀
	public List<String> getExtsByFormat(@Param("fileFormat")String fileFormat);
	
	
	//查询全部资源格式
	public List<String> getAllFileFormat();
	
}