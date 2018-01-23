package net.tfedu.zhl.cloud.teaching.personalblog.service;


import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogPraiseRecord;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　PersonalBlogPraiseRecordService.java
*/

public interface PersonalBlogPraiseRecordService extends BaseService<PersonalBlogPraiseRecord> {
	
	
	public ResultJSON addPraise(HttpServletRequest request, PersonalBlogPraiseRecord record);
	
}