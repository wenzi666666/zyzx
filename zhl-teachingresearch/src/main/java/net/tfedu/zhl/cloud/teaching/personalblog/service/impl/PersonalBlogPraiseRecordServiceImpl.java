package net.tfedu.zhl.cloud.teaching.personalblog.service.impl;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.cloud.teaching.personalblog.dao.PersonalBlogMapper;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogPraiseRecord;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogPraiseRecordService;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　PersonalBlogPraiseRecordServiceImpl.java
*/

@Transactional("transactionManager")
@Service("personalBlogPraiseRecordService")
public class PersonalBlogPraiseRecordServiceImpl extends BaseServiceImpl<PersonalBlogPraiseRecord>
				implements PersonalBlogPraiseRecordService
					{
	
	@Autowired
	PersonalBlogMapper blogmapper;
	
	
	@Override
	public ResultJSON addPraise(HttpServletRequest request, PersonalBlogPraiseRecord record) {
		
		
		
		
		PersonalBlog blog =  blogmapper.selectByPrimaryKey(record.getBlogUuid());
		
		blog.setPraiseNum(blog.getPraiseNum()==null?1:(1+blog.getPraiseNum()));
		
		super.insert(record);
		
		blogmapper.updateByPrimaryKeySelective(blog);
		
		
		return ResultJSON.getSuccess(blog.getPraiseNum());
	}
	
	
	
}