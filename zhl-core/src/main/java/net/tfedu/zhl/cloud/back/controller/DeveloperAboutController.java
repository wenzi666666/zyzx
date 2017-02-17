package net.tfedu.zhl.cloud.back.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.developer.entity.SDeveloper;
import net.tfedu.zhl.sso.developer.service.SDeveloperService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 
  	开发者相关的接口
  	
  	developer + app 
  
    @author wangwr
    @date 2017年1月19日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Controller
@RequestMapping(value = "/*BackAPI/v1.0/developer")
public class DeveloperAboutController extends BaseController<SDeveloper> {

	@Resource
	SDeveloperService developerService;
	
	
	/**
	 * 增加开发者
	 * @param developer
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addDeveloper",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addDeveloper(SDeveloper developer, HttpServletRequest request  )throws Exception{
		ControllerHelper.checkEmpty(developer.getCompanyname());
		developer.setCreatetime(Calendar.getInstance().getTime());
		developer.setFlag(false);
		
		return super.insertSelective(developer);
	}
	
	
	/**
	 * 修改开发者
	 * @param developer
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateDeveloper",method = RequestMethod.POST)
	@ResponseBody	
	public ResultJSON updateDeveloper(SDeveloper developer, HttpServletRequest request  )throws Exception{
		
		ControllerHelper.checkIntegerEmpty(developer.getDeveloperid());
		
		return super.update(developer);
		
	}
	
	/**
	 * 删除开发者
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delDeveloper",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delDeveloper(Integer id , HttpServletRequest request )throws Exception{
		
		ControllerHelper.checkIntegerEmpty(id);
		
		return super.delete(id);
		
	}
	
	/**
	 * 分页查询开发者
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pageDeveloper",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageDeveloper(Integer pageNum, Integer pageSize, HttpServletRequest request )throws Exception{
		
		ControllerHelper.checkIntegerEmpty(pageNum);
		ControllerHelper.checkIntegerEmpty(pageSize);
		
		Example example = new Example(SDeveloper.class);
		example.createCriteria().andCondition("flag = false ");
		
		
		
		return developerService.pageDeveloper(example, pageNum, pageSize);
	}
	
	
	
	/**
	 * 增加应用	
	 * @param app
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addApp",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addApp(@Valid SApp app,BindingResult bindingResult, HttpServletRequest request  )throws Exception{
		
		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		app.setFlag(false);
		
		return developerService.addApp(app);
	}
	
	
	/**
	 * 修改应用
	 * @param app
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateApp",method = RequestMethod.POST)
	@ResponseBody	
	public ResultJSON updateApp(SApp app,HttpServletRequest request  )throws Exception{
		
		ControllerHelper.checkIntegerEmpty(app.getAppid());
		
		return developerService.updateApp(app);
		
	}
	
	/**
	 * 删除应用
	 * @param appId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delApp",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delApp(Integer appId , HttpServletRequest request )throws Exception{
		
		ControllerHelper.checkIntegerEmpty(appId);
		
		return developerService.delApp(appId);
		
	}
	
	/**
	 * 分页查询应用
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pageApp",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageApp(Integer pageNum, Integer pageSize, HttpServletRequest request )throws Exception{
		
		ControllerHelper.checkIntegerEmpty(pageNum);
		ControllerHelper.checkIntegerEmpty(pageSize);
		
		Integer developerId = ControllerHelper.getOptionalIntegerParameter(request, "developerId");
		
		
		Example example = new Example(SApp.class);
		Criteria c =  example.createCriteria();
		c.andCondition("flag = false ");
		if(developerId!=null && developerId > 0){
			c.andEqualTo("developerid", developerId);
		}
		
		return developerService.pageApp(example, pageNum, pageSize);
	}
	
	
	
}
