package net.tfedu.zhl.cloud.resource.resCount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.resCount.entity.ZResCount;
import net.tfedu.zhl.helper.CoreMapper;

public interface ZResCountMapper extends CoreMapper<ZResCount> {

	/**
	 * 获取指定教材目录下的资源统计数据
	 * 
	 * @param tfcode
	 * @return
	 */
	public List<ZResCount> getResCountWithChildren(String tfcode);

	/**
	 * 获取指定教材目录下,指定资源库的资源大于0 的 资源统计数据
	 * 
	 * @param tfcode
	 * @param poolId
	 * @return
	 */
	public List<ZResCount> getResCountWithChildrenLimitPoolResNumber(@Param("tfcode") String tfcode,
			@Param("poolId") Integer poolId);

}