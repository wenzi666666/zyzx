package net.tfedu.zhl.cloud.teaching.personalblog.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.teaching.personalblog.entity.LastActive;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogComment;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogPraiseRecord;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogCommentService;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogPraiseRecordService;
import net.tfedu.zhl.cloud.teaching.personalblog.service.PersonalBlogService;
import net.tfedu.zhl.cloud.teaching.personalblog.util.ListUtil;
import net.tfedu.zhl.cloud.teaching.personalblog.util.UserInfoConfigUtil;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.NoAuthorizationException;
import net.tfedu.zhl.core.exception.RepeatOperateException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.PaginationHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.userlayer.user.entity.UserAreaInfo;
import net.tfedu.zhl.userlayer.user.service.JUserService;
import tk.mybatis.mapper.entity.Example;

/** 个人反思的主要业务处理类 */
@Controller
//@RequestMapping("teachingServiceRestAPI/v1.0/personalBlog")
@RequestMapping("*RestAPI/v1.0/personalBlog")
public class PersonalBlogController {

	
	@Resource
	private JUserService userSerivce ;
	
	@Resource
	private PersonalBlogService personalBlogService;
	@Resource
	private PersonalBlogCommentService personalBlogCommentService;
	@Resource
	private PersonalBlogPraiseRecordService personalBlogPraiseRecordService;
	
	@Resource
	CommonWebConfig commonWebConfig ;
	
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
		long  userId =(Long) request.getAttribute("currentUserId");
		blog.setUserId(userId);
		
		blog.setCreateTime(Calendar.getInstance().getTime());
		blog.setClickNum(0);
		blog.setCommentNum(0);
		blog.setUpdateTime(blog.getCreateTime());
		blog.setPraiseNum(0);
		blog.setDeleteFlag(false);

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
		
		blog.setUpdateTime(Calendar.getInstance().getTime());
		
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
		
		PersonalBlog blog = new PersonalBlog();
		blog.setUuid(uuid);
		blog.setDeleteFlag(true);
		return personalBlogService.update(blog);
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
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ResultJSON get(HttpServletRequest request, String uuid) throws Exception {
		
		long  userId =(Long) request.getAttribute("currentUserId");

		ResultJSON json = personalBlogService.getByPrimaryKey(uuid);
		//是否有权限
		if(ResultJSON.isSuccess(json) && json.getData()!=null){
			PersonalBlog blog = (PersonalBlog)json.getData();
			
			String scope =  blog.getScope();
			Long scopeId = blog.getScopeid();
			
			//NoAuthorizationException
//			if("P".equalsIgnoreCase(scope)){
//				return ResultJSON.defaultError(new NoAuthorizationException("浏览"));
//			}
//			UserAreaInfo info = userSerivce.getUserAreaInfo(userId);
//			if("S".equalsIgnoreCase(scope)&& (scopeId != info.getSchoolId())){
//				return ResultJSON.defaultError(new NoAuthorizationException("浏览"));
//			}else if("D".equalsIgnoreCase(scope)&& (scopeId != info.getDistrictId())){
//				return ResultJSON.defaultError(new NoAuthorizationException("浏览"));
//			}
			
			if(userId != blog.getUserId()){
				//增加点击记录
				blog.setClickNum(blog.getClickNum()+1);
				personalBlogService.update(blog);
			}
			
			
			
			//需要将blog转map并设置头像
			
			Map<String, Object> blogMap =  null;
			try {
				blogMap = BeanUtils.describe(blog);
				blogMap.put("createTime", blog.getCreateTime().getTime());
				blogMap.put("updateTime", blog.getUpdateTime().getTime());
			} catch (Exception e) {
				throw new CustomException("类型PersonalBlog转map失败");
			}
			List<Map<String, Object>> tempL = new ArrayList<Map<String, Object>>();
			tempL.add(blogMap);
			
			
			UserInfoConfigUtil.resetUserImage(commonWebConfig.getCloudPlatFormLocal()
					, commonWebConfig.getCurrentCloudPlatform(request), tempL);

			json.setData(tempL.get(0));
		}
		
		return json;
		
	}
	/**
	 * 是否有浏览权限
	 * 
	 * @param request
	 * @param uuid
	 *            个人反思主键
	 */
	@ResponseBody
	@RequestMapping(value="ifViewPremiss", method = RequestMethod.GET)
	public ResultJSON ifViewPremiss(HttpServletRequest request, String uuid) throws CustomException {
		
		long  userId =(Long) request.getAttribute("currentUserId");
		
		ResultJSON json = personalBlogService.getByPrimaryKey(uuid);
		//是否有权限
		if(ResultJSON.isSuccess(json) && json.getData()!=null){
			PersonalBlog blog = (PersonalBlog)json.getData();
			
			String scope =  blog.getScope();
			Long scopeId = blog.getScopeid();
			
			//NoAuthorizationException
			if("P".equalsIgnoreCase(scope)){
				return ResultJSON.defaultError(new NoAuthorizationException("浏览"));
			}
			UserAreaInfo info = userSerivce.getUserAreaInfo(userId);
			if("S".equalsIgnoreCase(scope)&& (scopeId != info.getSchoolId())){
				return ResultJSON.defaultError(new NoAuthorizationException("浏览"));
			}else if("D".equalsIgnoreCase(scope)&& (scopeId != info.getDistrictId())){
				return ResultJSON.defaultError(new NoAuthorizationException("浏览"));
			}

		}
		
		return json;
		
	}

	/**
	 * 点赞
	 * 
	 * @param request
	 * @param record
	 *            个人反思主键
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "praise/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addPraise(HttpServletRequest request, PersonalBlogPraiseRecord record) throws CustomException {
		
		
		ResultJSON  res = personalBlogPraiseRecordService.select(record);
		
		if(ResultJSON.isSuccess(res) && null!= res.getData()){
			ArrayList<PersonalBlogPraiseRecord> tem = (ArrayList<PersonalBlogPraiseRecord>)res.getData();
			if(tem!=null && tem.size()>0){
				return ResultJSON.defaultError(new RepeatOperateException("点赞"));
			}
			
		}
		
		long  userId =(Long) request.getAttribute("currentUserId");
		record.setUserId(userId);
		record.setDeleteFlag(false);
		record.setCreateTime(Calendar.getInstance().getTime());
		ResultJSON rs = personalBlogPraiseRecordService.addPraise(request, record);

		return rs ; 
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
		
		long  userId =(Long) request.getAttribute("currentUserId");
		comment.setUserId(userId);
		comment.setDeleteFlag(false);
		comment.setCreateTime(Calendar.getInstance().getTime());

		ResultJSON rs = personalBlogCommentService.insert(comment);
		if(ResultJSON.isSuccess(rs)){
			
			ResultJSON rss = personalBlogService.getByPrimaryKey(comment.getBlogUuid());
			if(ResultJSON.isSuccess(rss)){
				
				PersonalBlog blog = (PersonalBlog)rss.getData();
				
				blog.setCommentNum(blog.getCommentNum()+1);
				
				personalBlogService.update(blog);
				
			}
		}
		
		return rs ; 

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
		
		example.createCriteria().andCondition(" delete_flag = false ").andCondition(" blog_uuid = '"+blogUuid+"'");
		
		
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
		
		scopeId = scopeId==null?0L:scopeId;
		
		
		ResultJSON result =  personalBlogService.lastBlog(scope, scopeId, 1, number);
		
		List<Object> ls =  ((PaginationHelper)result.getData()).getList();
		
		
		String cloudPlatFormLocal= commonWebConfig.getCloudPlatFormLocal();
		
		String cloudPlatForm = commonWebConfig.getCurrentCloudPlatform(request);
		
		List<Map<String, Object>> ls_map = ListUtil.CreateListToLowerCase(ls);
		
		UserInfoConfigUtil.resetUserImage(cloudPlatFormLocal, cloudPlatForm, ls_map);
		
		((PaginationHelper)result.getData()).setList(ls_map);
		
		return ResultJSON.getSuccess(result);
	}

	/**
	 * （区校）范围内最新教学动态
	 * 
	 * 		各自 获取前 50 个 查询 结果，
        	缓存（1个小时）全部结果之后，分页显示 
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "lastActive", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON lastActive(HttpServletRequest request, String scope, Long scopeId, int page, int pageSize) throws Exception{
		
		ControllerHelper.checkEmpty(scope);
		
		scopeId = scopeId==null?0L:scopeId;
		
		
		
		String currentUserName = (String)request.getAttribute("currentUserName");
		
		
		//cacheManager
		//是否缓存当前合并排序后的查询结果
		String cacheKey_ALL =  new StringBuffer()
					.append("final_lastActive_")
					.append(scope)
					.append("_")
					.append(scopeId)
					.append("_ALLSORTED")
					.toString();
		
		//是否缓存当前分页的查询结果
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
		
		
		
		ValueWrapper o =  cacheManager.getCache("appCache").get(cacheKey);
		
		if(null != o ){
			PageInfo<LastActive> pageInfo = (PageInfo<LastActive>) o.get() ;
			return ResultJSON.getSuccess(pageInfo);
		}
		
		
		try {
			
			
			List<LastActive> all = null ; 
			
			ValueWrapper list_ALL =  cacheManager.getCache("appCache").get(cacheKey_ALL);
			if(null!=list_ALL){
				all = ((List<LastActive>)list_ALL.get());
			}else{
				all = getLastTiveFromSKTAndLocal(scope, scopeId, currentUserName);
				
				String cloudPlatFormLocal= commonWebConfig.getCloudPlatFormLocal();
				
				String cloudPlatForm = commonWebConfig.getCurrentCloudPlatform(request);

				UserInfoConfigUtil.resetUserImageForActive(cloudPlatFormLocal, cloudPlatForm, all);
				
				cacheManager.getCache("appCache").put(cacheKey_ALL,all);
				
			}
			
			//100条结果，去分页
			//组装
			List<LastActive> pageIN =  null ; 
			
			
			pageIN  = 
					(page-1)*pageSize>all.size()?
							null:
					(page)*pageSize>all.size()?
							all.subList((page-1)*pageSize,all.size()):
								all.subList((page-1)*pageSize, (page)*pageSize);
			
			PageInfo<LastActive> pageInfo = new PageInfo<LastActive>(pageIN);
			pageInfo.setPageNum(page);
			pageInfo.setPageSize(pageSize);
			
			
			cacheManager.getCache("appCache").put(cacheKey,pageInfo);

			
			return ResultJSON.getSuccess(pageInfo);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return ResultJSON.defaultError(new CustomException(e));
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return ResultJSON.defaultError(new CustomException(e));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJSON.defaultError(new CustomException(e));
		}
	}

	protected List<LastActive> getLastTiveFromSKTAndLocal(String scope, Long scopeId, String currentUserName)
			throws Exception, IllegalAccessException, InvocationTargetException {
		
		List<LastActive> all =  new ArrayList<LastActive>();
		List<PersonalBlog> lastLog =  null ; 
		List<LastActive> lastActive =  null ; 
		
		LastActive actve = null;
		PersonalBlog personalBlog = null;	
		
		PaginationHelper temp = (PaginationHelper)(personalBlogService.lastBlog(scope, scopeId, 1, 50).getData());

		lastLog = temp.getList();
		
		lastActive = getLastTopicFromSKTbackend(currentUserName, scope, scopeId, 50);

		
		
		//合并查询结果list
		if(lastLog!=null){
			moveBlogListToActiveList(lastLog, all);
		}
		
		if(lastActive!=null){
			all.addAll(lastActive);
		}
		
		//排序
		Collections.sort(all);
		
		
		
		return all;
	}

	protected void moveBlogListToActiveList(List<PersonalBlog> lastLog, List<LastActive> all)
			throws IllegalAccessException, InvocationTargetException {
		LastActive actve;
		PersonalBlog personalBlog;
		for (Iterator<PersonalBlog> iterator = lastLog.iterator(); iterator.hasNext();) {
			
			personalBlog = (PersonalBlog) iterator.next();
			actve = new LastActive();
			
			BeanUtils.copyProperties(actve, personalBlog);
			
			actve.setTypeName("个人反思");
			all.add(actve);
		}
	}
	
	
	/**
	 * 从语文双课堂获取最新讨论 
	 * @return
	 * @throws Exception 
	 */
	private List<LastActive> getLastTopicFromSKTbackend(String userName,String scope, Long scopeId,int number) throws Exception{
		
		String sktHostLocal =  commonWebConfig.getSktHostLocal();
		
		
		String url = sktHostLocal+"/topicStatistic/lastTopics?user="+userName
				+"&scope="+scope
				+"&scopeId="+scopeId
				+"&number="+number
				;
		
		System.out.println("从语文双课堂获取最新讨论URL:"+url);
		String result = HttpClientUtils.doGET(url);
		
		if(StringUtils.isNotEmpty(result)){
			
			ResultJSON json = JSONObject.parseObject(result, ResultJSON.class);
			if(json!=null && "OK".equalsIgnoreCase(json.getCode())){
				
				List<LastActive> list  = new ArrayList<LastActive>();
				LastActive obj =  null;

				Iterator<Object> it = ((JSONArray)(json.getData())).listIterator();
				for (; it.hasNext();) {
					obj = JSONObject.parseObject(JSONObject.toJSONString(it.next()),LastActive.class);
					list.add(obj);
				}
				
				
				return list ; 
			}
			
		}
		return null ;
		
	}
	
	

	public static void main(String[] args) {
		
		List<Integer> ls = new ArrayList<Integer>();
		
		ls.add(1);
		ls.add(2);
		ls.add(3);
		ls.add(4);
		ls.add(5);
		ls.add(6);
		
		
		System.out.println(ls.subList(0, 6).toString());
		
	}
	

}