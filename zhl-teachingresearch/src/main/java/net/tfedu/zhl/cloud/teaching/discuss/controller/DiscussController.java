	package net.tfedu.zhl.cloud.teaching.discuss.controller;

	import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussLog;
import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.cloud.teaching.discuss.service.DiscussLogService;
import net.tfedu.zhl.cloud.teaching.discuss.service.DiscussRecommendService;
import net.tfedu.zhl.cloud.teaching.discuss.util.DiscussURLUtil;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;




	/**
	 * 推荐班级及其访问记录的相关接口
	 * @author wangwr
	 *
	 */

	@Controller
	@RequestMapping("/teachingServiceRestAPI")
	public class DiscussController {

		
		@Resource
		RegisterService regSerivce;
		
		
		@Resource
		DiscussRecommendService discussService;
		

		@Resource
		DiscussLogService  discussLogService;
		
		
		@Resource
		CommonWebConfig config;
		
		
		/**
		 * 返回推荐班级列表
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="v1.0/discuss/recommend",method=RequestMethod.GET)
		@ResponseBody
		public ResultJSON getRecomended(HttpServletRequest request, int page,int perPage ) throws Exception{
			//http://chat.tfedu.net:7070/circle/ff808181520fcf4401521f1118c50130
			// 当前登录用户id
			Long currentUserId = (Long) request.getAttribute("currentUserId");

			//返回推荐班级列表
			ResultJSON result = null;

			
			//准备重置url
			SRegister register = regSerivce.getRegister(currentUserId);
			String userName = register.getName();
			String  forum3 =  config.getCurrentForum3(request);		

			
			PageInfo<TDiscussRecommend> _page = discussService.queryRecommendRecordsPage(page, perPage);
			List<TDiscussRecommend> list =  _page.getList();
			if(list!=null && list.size()>0){
				for (Iterator<TDiscussRecommend> iterator = list.iterator(); iterator.hasNext();) {
					TDiscussRecommend tDiscussRecommend = (TDiscussRecommend) iterator.next();
					String _url = DiscussURLUtil.convert(tDiscussRecommend.getClassurl(),tDiscussRecommend.getVisit_name(),tDiscussRecommend.getVisit_pwd());
					tDiscussRecommend.setClassurl(_url);
				}
			}
			return ResultJSON.getSuccess(_page);
		}
		
		/**
		 * 返回推荐班级列表(后台)
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value="v1.0/discuss/recommend_back",method=RequestMethod.GET)
		@ResponseBody
		public ResultJSON getRecomendedBack(HttpServletRequest request, int page,int perPage,String orderBy ) throws Exception{
			//http://chat.tfedu.net:7070/circle/ff808181520fcf4401521f1118c50130
			// 当前登录用户id
			Long currentUserId = (Long) request.getAttribute("currentUserId");

			//返回推荐班级列表
			ResultJSON result = discussService.queryRecommendRecordsPageForBack(page, perPage, orderBy);

			
			//准备重置url
			SRegister register = regSerivce.getRegister(currentUserId);
			String userName = register.getName();
			String  forum3 =  config.getCurrentForum3(request);		

			
			PageInfo<TDiscussRecommend> _page = ((PageInfo<TDiscussRecommend>)result.getData());
			List<TDiscussRecommend> list =  _page.getList();
			if(list!=null && list.size()>0){
				for (Iterator<TDiscussRecommend> iterator = list.iterator(); iterator.hasNext();) {
					TDiscussRecommend tDiscussRecommend = iterator.next();
					String _url = DiscussURLUtil.convert(tDiscussRecommend.getClassurl(),tDiscussRecommend.getVisit_name(),tDiscussRecommend.getVisit_pwd());
					tDiscussRecommend.setClassurl(_url);
				}
			}
			return result;
		}
	
	/**
	 * 增加访问记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/readed",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addReadRecord(HttpServletRequest request,String classId) throws Exception{
		classId = ControllerHelper.checkEmpty(classId);
	
		Long currentUserId = (Long)request.getAttribute("currentUserId");


		
		TDiscussLog c = new TDiscussLog();
		c.setClassid(classId);
		c.setUserid(currentUserId);
		c.setCreatetime(Calendar.getInstance().getTime());
		
		return discussLogService.insert(c);
	}
	
	

	/**
	 * 返回最近访问列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/readed",method=RequestMethod.GET)
	@ResponseBody	
	public ResultJSON getReaded( HttpServletRequest request,HttpServletResponse response ) throws Exception{
		
		Long currentUserId = (Long)request.getAttribute("currentUserId");	
		
		//返回最近访问列表
		ResultJSON result = discussLogService.getReadLog(currentUserId);

		//准备重置url
		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String  forum3 =  config.getCurrentForum3(request);		
		
		
		List<TDiscussRecommend> data = (List<TDiscussRecommend>)result.getData();
		for (Iterator<TDiscussRecommend> iterator = data.iterator(); iterator.hasNext();) {
			TDiscussRecommend tDiscussRecommend = (TDiscussRecommend) iterator.next();
			String _url = DiscussURLUtil.convert(tDiscussRecommend.getClassurl(),tDiscussRecommend.getVisit_name(),tDiscussRecommend.getVisit_pwd());
			tDiscussRecommend.setClassurl(_url);
		}
		
		return  result;
	}
	
	
	
	
	
	/**
	 * 增加推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/recommend",method=RequestMethod.POST)
	@ResponseBody		
	public ResultJSON addRecomended( HttpServletRequest request,HttpServletResponse response ) throws Exception{
		Long currentUserId = (Long)request.getAttribute("currentUserId");	

		String className = ControllerHelper.getParameter(request, "className"); //	班级名称
		String classImage	 = ControllerHelper.getParameter(request, "classImage");	//班级图片路径
		String schoolName = ControllerHelper.getParameter(request, "schoolName");		//学校名称
		String note		 = ControllerHelper.getOptionalParameter(request, "note");//班级简介
		String visit_name		 = ControllerHelper.getParameter(request, "visit_name");//账号
		String visit_pwd		 = ControllerHelper.getParameter(request, "visit_pwd");//密码
		String classurl		 = ControllerHelper.getParameter(request, "classurl");//链接
		
		TDiscussRecommend record = new TDiscussRecommend();
		record.setClassname(className);
		record.setClassimage(classImage);
		record.setSchoolname(schoolName);
		record.setNote(note);
		record.setCreator(currentUserId);
		record.setCreatetime(Calendar.getInstance().getTime());
		record.setFlag(false);
		record.setVisit_name(visit_name);
		record.setVisit_pwd(visit_pwd);
		record.setClassurl(classurl);
		return discussService.insert(record);
	}
	
	

	/**
	 * 获取指定班级
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="v1.0/discuss/recommend/{id}",method=RequestMethod.GET)
	@ResponseBody		
	public ResultJSON getOne(@PathVariable Long id) throws Exception{
		
		return discussService.get(id) ;
	}
	
	
	/**
	 * 修改推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="v1.0/discuss/recommend/{id}",method=RequestMethod.POST)
	@ResponseBody		
	public ResultJSON updateRecomended(HttpServletRequest request,@PathVariable Long id) throws Exception{
		
		
		
		String className = ControllerHelper.getParameter(request, "className"); //	班级名称
		String classImage	 = ControllerHelper.getParameter(request, "classImage");	//班级图片路径
		String schoolName = ControllerHelper.getParameter(request, "schoolName");		//学校名称
		String note		 = ControllerHelper.getOptionalParameter(request, "note");//班级简介
		String visit_name		 = ControllerHelper.getParameter(request, "visit_name");//账号
		String visit_pwd		 = ControllerHelper.getParameter(request, "visit_pwd");//密码
		String classurl		 = ControllerHelper.getParameter(request, "classurl");//链接

		TDiscussRecommend record = new TDiscussRecommend();
		record.setId(id);
		record.setClassname(className);
		record.setClassimage(classImage);
		record.setSchoolname(schoolName);
		record.setNote(note);
		record.setVisit_name(visit_name);
		record.setVisit_pwd(visit_pwd);
		record.setClassurl(classurl);
		
		return discussService.update(record) ;
	}
	
	
	
	
	/**
	 * 删除推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/remove",method=RequestMethod.POST)
	@ResponseBody		
	public ResultJSON delRecomended(String ids ) throws Exception{
		
		ControllerHelper.checkEmpty(ids);
		
		return discussService.removeRecommendRecords(ids);
	}
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException {
		
		
		String username = "csls10";
		String pwd = "000000";
		
		String p =  username+":"+pwd;
		p = Base64.encode(p.getBytes());
		
		
		String url = "http://192.168.111.158:8090/myForum/forumGrade_forumGradeState.action?classNo=5&partId=0";
		
		String yun_service = null ;
		
		String temp = url.replace("http://", "");
		
		yun_service = "http://"+temp.substring(0, temp.indexOf("/")+1);
		
		
		
		System.out.println(yun_service);
		
		url = URLEncoder.encode(url, "utf-8");
		
		
		String target = "http://192.168.111.158:8090/net_jyForum.action?args="+p+"&targetPage="+url;
		



		
		
	}
	
	
	
	
}
