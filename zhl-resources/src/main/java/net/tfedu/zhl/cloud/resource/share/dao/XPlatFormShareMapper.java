package net.tfedu.zhl.cloud.resource.share.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.share.entity.XPlatFormShare;
import net.tfedu.zhl.helper.CoreMapper;

public interface XPlatFormShareMapper extends CoreMapper<XPlatFormShare> {

	
	/**
	 * 更新指定类型的共享对象的 下载次数
	 * @param sharedType
	 * @param shareId
	 */
	void updateDownloadTimes( @Param("sharedType") int sharedType,@Param("shareId") Long shareId);
}