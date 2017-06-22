package net.tfedu.zhl.cloud.teaching.personalblog.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.teaching.personalblog.entity.LastActive;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogComment;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogPraiseRecord;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogCommentService;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogPraiseRecordService;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogService;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.userlayer.user.entity.UserSimple;
import tk.mybatis.mapper.entity.Example;

/** 个人反思的主要业务处理类 */
@Controller
@RequestMapping("teachingServiceRestAPI/v1.0/personalBlog")
public class PersonalBlogController {

	@Resource
	private PersonalBlogService personalBlogService;
	@Resource
	private PersonalBlogCommentService personalBlogCommentService;
	@Resource
	private PersonalBlogPraiseRecordService personalBlogPraiseRecordService;
	
	
    @Autowired
    CacheManager cacheManager;

	/**
	 * 新增个人反思
	 * 
	 * @param request
	 * @param blog
	 *            个人反思对想 Exception
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON add(HttpServletRequest request, PersonalBlog blog) {

		return personalBlogService.insert(blog);
	}

	/**
	 * 修改个人反思
	 * 
	 * @param request
	 * @param blog
	 *            个人反思对想
	 */
	@ResponseBody
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public ResultJSON edit(HttpServletRequest request, PersonalBlog blog) {
		return personalBlogService.update(blog);
	}

	/**
	 * 删除个人反思
	 * 
	 * @param request
	 * @param uuid
	 *            个人反思主键
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResultJSON delete(HttpServletRequest request, String uuid) {
		return personalBlogService.delete(uuid);
	}

	/**
	 * 查询列表个人反思
	 * 
	 * @param request
	 * @param blog
	 *            个人反思对想
	 */
	@ResponseBody
	@RequestMapping(value = "query", method = RequestMethod.GET)
	public ResultJSON query(HttpServletRequest request,Long userId,Integer page,Integer pageSize) {
		
		Example example = new Example(PersonalBlog.class);
		
		example.createCriteria().andCondition(" user_id = "+userId).andCondition(" delete_flag = false  ") ;
		
		return personalBlogService.getPageByExample(example, page, pageSize,"create_time",false);
	}

	/**
	 * 查询指定个人反思
	 * 
	 * @param request
	 * @param uuid
	 *            个人反思主键
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ResultJSON get(HttpServletRequest request, String uuid) {

		return personalBlogService.getByPrimaryKey(uuid);
		
	}

	/**
	 * 点赞
	 * 
	 * @param request
	 * @param record
	 *            个人反思主键
	 */
	@RequestMapping(value = "praise/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addPraise(HttpServletRequest request, PersonalBlogPraiseRecord record) {

		return personalBlogPraiseRecordService.addPraise(request, record);
	}

	/**
	 * 增加评论
	 * 
	 * @param request
	 * @param comment
	 *            评论对象
	 */
	@RequestMapping(value = "comment/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addComment(HttpServletRequest request, PersonalBlogComment comment) {

		return personalBlogCommentService.insert(comment);

	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @param blogUuid
	 *            个人反思主键
	 * @param page
	 *            页码
	 * @param pageSize
	 *            页面条目数
	 */
	@RequestMapping(value = "queryComment", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON queryComment(HttpServletRequest request, String blogUuid, int page, int pageSize) {

		
		Example example = new Example(PersonalBlogComment.class);
		
		example.createCriteria().andCondition(" delete_flag = false ").andEqualTo("blog_uuid", blogUuid);
		
		
		return personalBlogCommentService.getPageByExample(example, page, pageSize,"create_time",false);
	}

	/**
	 * （区校）范围内最新个人反思
	 * 
	 * @param request
	 * @param scope
	 *            查询范围 S校、D区、W全站，默认为全站
	 * @param scopeId
	 *            相应的学校id或地区id，全站范围时可以为0
	 * @param number
	 *            个数，默认为5
	 * @throws Exception 
	 */
	@RequestMapping(value = "lastBlog", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON lastBlog(HttpServletRequest request, String scope, Long scopeId, Integer number) throws Exception {
		
		
		if(number==null || 0 == number){
			number = 5;
		}
		
		ControllerHelper.checkEmpty(scope);
		ControllerHelper.checkLongEmpty(scopeId);
		
		return ResultJSON.getSuccess(personalBlogService.lastBlog(scope, scopeId, 1, number));
	}

	/**
	 * （区校）范围内最新教学动态
	 * 
	 * @param request
	 * @param scope
	 *            查询范围 S校、D区、W全站，默认为全站
	 * @param scopeId
	 *            相应的学校id或地区id，全站范围时可以为0
	 * @param page
	 *            页码,默认为1
	 * @param pageSize
	 *            页面条目数,默认为5
	 */
	@RequestMapping(value = "lastActive", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON lastActive(HttpServletRequest request, String scope, Long scopeId, int page, int pageSize) {
		
		//cacheManager
		
		String cacheKey =  new StringBuffer()
					.append("final_lastActive_")
					.append(scope)
					.append("_")
					.append(scopeId)
					.append("_")
					.append(page)
					.append("_")
					.append(pageSize)
					.toString();
		
		
		
		ValueWrapper o =  cacheManager.getCache("appCache").get("cacheKey");
		
		if(null != o ){
			PageInfo<LastActive> pageInfo = (PageInfo) o ;
			return ResultJSON.getSuccess(pageInfo);
		}
		

		
		
		
		
		
		List<PersonalBlog> lastLog = (List<PersonalBlog>) personalBlogService.lastBlog(scope, scopeId, 2, 50);
		
		List<PersonalBlog> lastActive = (List<PersonalBlog>) personalBlogService.lastBlog(scope, scopeId, 1, 50);
		
		List<LastActive> all = new ArrayList<LastActive>();
		
		
		
		
		LastActive actve = null;
		PersonalBlog personalBlog = null;
		
		
		try {
			
			for (Iterator<PersonalBlog> iterator = lastActive.iterator(); iterator.hasNext();) {
				
				personalBlog = (PersonalBlog) iterator.next();
				actve = new LastActive();
				
				BeanUtils.copyProperties(actve, personalBlog);
				
				actve.setTypeName("个人反思");
				
				all.add(actve);
			}
	
			
			
			for (Iterator<PersonalBlog> iterator = lastLog.iterator(); iterator.hasNext();) {
				
				personalBlog = (PersonalBlog) iterator.next();
				actve = new LastActive();
				
				BeanUtils.copyProperties(actve, personalBlog);
				
				actve.setTypeName("个人反思");
				
				all.add(actve);
			}
			
			
			//合并查询结果list
			//排序
			Collections.sort(all);
			//查询用户头像 
			
			//组装查询分页信息
			
			//放入缓存
			
			
			
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		

		return null;
	}
	
	
	
	
	
	
	

}