package net.tfedu.zhl.cloud.resource.user.teach.service;

import net.tfedu.zhl.cloud.resource.user.teach.entity.JTeachSyscourse;
import net.tfedu.zhl.core.service.BaseService;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年8月10日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public interface JTeachSyscourseService extends BaseService<JTeachSyscourse> {

	/**
	 * 获取用户的历史选择的系统目录的tfcode
	 * 
	 * @param userId
	 * @return
	 */
	public String queryTeachingSelectTfcode(Long userId);

	/**
	 * 替换用户的教材选择 （清理所有历史记录，只保留当前的一个选择）
	 * @param userId
	 * @param termId
	 * @param subjectId
	 * @param syscourseId
	 */
	public void replaceUserTeachingSelect(Long userId, Long termId, Long subjectId, Long syscourseId);

}
