package net.tfedu.zhl.cloud.resource.resPreview.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;

/**
 * 资源预览service接口
 * @author WeiCuicui
 *
 */
public interface ResPreviewService {

	//根据resId和fromFlag，查询资源具体信息
	public ResPreviewInfo getResPreviewInfo(long resId,int fromFlag);
	
	//对一个资源进行预览时，查询该资源所在的所有版本目录
	public List<ResNavEntity> getAllResNavs(long resId,int fromFlag,String curTfcode);
}
