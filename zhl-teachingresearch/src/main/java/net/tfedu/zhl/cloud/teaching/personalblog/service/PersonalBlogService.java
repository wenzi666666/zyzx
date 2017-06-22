package net.tfedu.zhl.cloud.teaching.personalblog.service;


import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　PersonalBlogService.java
*/

public interface PersonalBlogService extends BaseService<PersonalBlog> {
   /** 分页查询范围内的最新个人反思
    * 
    * @param scope 查询范围 S校、D区、W全站，默认为全站
    * @param scopeId 相应的学校id或地区id，全站范围时可以为0
    * @param page 页码
    * @param pageSize 页面条目数 */
    ResultJSON lastBlog(String scope, Long scopeId, int page, int pageSize);
    

}