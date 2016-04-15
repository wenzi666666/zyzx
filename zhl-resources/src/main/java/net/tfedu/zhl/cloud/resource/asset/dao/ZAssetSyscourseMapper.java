package net.tfedu.zhl.cloud.resource.asset.dao;

import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetSyscourse;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface ZAssetSyscourseMapper extends CoreMapper<ZAssetSyscourse> {
	
	/**
	 * 根据resId，查询所在的第一个课程结点
	 * @return
	 */
	public String getFirstCourseByResId(@Param("resId")long resId);
}