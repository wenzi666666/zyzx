package net.tfedu.zhl.cloud.resource.downloadrescord.service;

import java.util.HashMap;
import java.util.List;

public interface ResDownPathService {

	/**
	 * 获取资源的下载链接
	 */
	public List<HashMap<String, Object>> getResDownPath(long userId,long resId,int fromFlag);
}
