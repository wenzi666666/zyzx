package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FileFormat;
/**
 * 查询格式
 * @author WeiCuicui
 *
 */
public interface FileFormatMapper extends CoreMapper<FileFormat> {
	
	//根据资源ids和typeIds，查询得到资源格式
	public List<FileFormat> getFormatsByMType(@Param("resourceIds") List<Long> resourceIds,@Param("typeIds") List<Integer> typeIds);
}