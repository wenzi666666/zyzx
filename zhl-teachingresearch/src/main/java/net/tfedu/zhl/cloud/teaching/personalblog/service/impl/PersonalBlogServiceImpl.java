package net.tfedu.zhl.cloud.teaching.personalblog.service.impl;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　PersonalBlogServiceImpl.java
*/

@Service("personalBlogService")
public class PersonalBlogServiceImpl extends BaseServiceImpl<PersonalBlog>
implements PersonalBlogService{
	
	
	
   /** 分页查询范围内的最新个人反思
    * 
    * @param scope 查询范围 S校、D区、W全站，默认为全站
    * @param scopeId 相应的学校id或地区id，全站范围时可以为0
    * @param page 页码
    * @param pageSize 页面条目数 */
   @Cacheable(value="bussinesscache")
   public ResultJSON lastBlog(String scope, Long scopeId, int page, int pageSize){
	   
	   Example example = new Example(PersonalBlog.class);
	   
	   Criteria c =  example.createCriteria();
	   
	   c.andCondition(" delete_flag = false ");
	   
	   
	   if(!"W".equalsIgnoreCase(scope) && StringUtils.isNotEmpty(scope) && 0 < scopeId){
		   c.andCondition(" scope= '"+scope+"'").andCondition(" scopeid= "+scopeId);
	   }
	   
	   
	   return super.getPageByExample(example, page, pageSize, "create_time", false);

   }

}