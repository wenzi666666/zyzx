package net.tfedu.zhl.cloud.resource.other;

import java.net.URLEncoder;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 * 资源中心3.1
 * 接入公司其他产品（非第三方对接）
 * 登录用户可以使用
 * @author wangwr
 *
 */

@Controller
@RequestMapping("/additional")
public class AdditionalController {
	
	
	@Resource
	RegisterService regSerivce;
	
	
	@Resource
	CommonWebConfig config ;
	
	/**
	 * 约定的key
	 */
	private static final String additional_key = "9k8i78jug6hd93kjf84h";
	
	
	
	
	/**
	 * 情景英语
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sceneEnglish",method=RequestMethod.GET)
	public ModelAndView sceneEnglish(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");
		
		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String pwd_str = PWDEncrypt.getPWD(register.getPwd());
		//page 学生为1 教师为0
		String page =  "1".equals(register.getRoleid().toString()) ? "1": "0";
		
		String sign = MD5.MD5("user=" + userName + "&pass=" + pwd_str + "&page="
				+ page + "&key="+additional_key);
		String s = "user=" + userName + "&pass=" + pwd_str + "&page=" + page
				+ "&sign=" + sign;
		
		byte[] sbytes = xxtea.encrypt(s.getBytes("utf-8"),additional_key.getBytes());
		s = Base64.encode(sbytes, 0, sbytes.length);
		s = URLEncoder.encode(s, "utf-8");
		
		//情景英语
		String url = config.getCurrentSceneEnglish(request)+"?s=" + s;
		
		ModelAndView view =new ModelAndView();
		view.setViewName("redirect:"+url);
		return  view ;
	}
	
	
	/**
	 * 题库
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/questionLibrary",method=RequestMethod.GET)
	public ModelAndView questionLibrary(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");
		
		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String password = PWDEncrypt.getPWD(register.getPwd());

		
		
		String key=additional_key;
		String params = "username="+userName+"&userpwd="+password;
		String _sign = MD5.MD5(params + "&key="+key);
		params+="&sign="+_sign;
		byte[] b1=xxtea.encrypt(params.getBytes("utf-8"), key.getBytes("utf-8"));
		String s1=Base64.encode(b1);
		s1= URLEncoder.encode(s1, "utf-8");
		
		String url = config.getCurrentTkHost(request)+"?args="+s1+"&platform=resourcecenter";
		ModelAndView view =new ModelAndView();
		view.setViewName("redirect:"+url);
		return  view ;
	}
	
	

	/**
	 * 论坛3.0
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/forum3",method=RequestMethod.GET)
	public ModelAndView forum3(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");
		
		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String password = PWDEncrypt.getPWD(register.getPwd());
		
		
		String forum3=config.getCurrentForum3(request);
		forum3+="?username="+userName+"&token="+UUID.randomUUID().toString();
//   	response.sendRedirect(forum3);
		ModelAndView view =new ModelAndView();
		view.setViewName("redirect:"+forum3);
		return  view ;
	}
	

}
