package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resources.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resources.navigation.service.UserDefaultService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户历史选择 controller
 * @author WeiCuicui
 * 返回结果为统一类型
 */
@Controller
@RequestMapping("/resRestAPI")
public class UserDefaultController {

	@Resource UserDefaultService userDefaultService;
	
	/**
	 * 返回json的结果对象
	 */
	private ResultJSON result = new ResultJSON();
	
	/**
	 * 异常
	 */
	private CustomException exception;
	
	/**
	 * 定义全局map，用于向mybatis传递参数
	 */
	private HashMap<String, Object> map = new HashMap<String, Object>();
	
	//查询用户历史选择  使用GET方法
	@RequestMapping(value="/v1.0/history",method=RequestMethod.GET) 
	@ResponseBody
	public ResultJSON getUserDefault(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//定义用户历史选择
		JUserDefault userDefault = null;
		try {
			
			long userId = Long.parseLong(request.getParameter("userId").toString().trim());
			int type = Integer.parseInt(request.getParameter("type").toString().trim());
			map.put("userId", userId);
			map.put("type", type);
			userDefault = userDefaultService.getUserHistoryDefault(map);
			
			exception = CustomException.SUCCESS;
			
		} catch (Exception e) {
			// TODO: handle exception
			//获得异常信息，并打印
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			//封装result
			result.setCode(exception.getCode());
			result.setData(userDefault);
			result.setMessage(exception.getMessage());
			result.setSign("");
		}
		
		return result;
	}
	
	//修改、增加用户历史选择
	@RequestMapping(value="/v1.0/history") 
	@ResponseBody
	public ResultJSON updateUserDefault(HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			long userId = Long.parseLong(request.getParameter("userId").toString().trim());
			int type = Integer.parseInt(request.getParameter("type").toString().trim());
			String tfcode = request.getParameter("tfcode");
			String _method = request.getParameter("_method");
			
			map.put("userId", userId);
			map.put("type", type);
			map.put("tfcode", tfcode);
			
			if (StringUtils.isNotEmpty(_method) && RequestMethod.PATCH.equals(_method)) {
				//修改用户历史选择
				userDefaultService.updateUserHistoryDefault(map);
				exception = CustomException.SUCCESS;
			} else if(StringUtils.isNotEmpty(_method) && RequestMethod.POST.name().equals(_method)){
				//增加用户历史选择
				userDefaultService.addUserHistoryDefault(map);
				exception = CustomException.SUCCESS;
			} 
		} catch (Exception e) {
			// TODO: handle exception
			//获得异常信息，并打印
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			//封装result
			result.setCode(exception.getCode());
			result.setData("");
			result.setMessage(exception.getMessage());
			result.setSign("");
		}
		
		return result;
	}
	
}