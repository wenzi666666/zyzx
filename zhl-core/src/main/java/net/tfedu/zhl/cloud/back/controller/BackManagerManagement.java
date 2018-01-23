package net.tfedu.zhl.cloud.back.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.user.entity.SBackUser;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;
import net.tfedu.zhl.sso.back.user.service.SBackUserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 
  	后台管理员的管理接口
  
  
    @author wangwr
    @date 2017年1月19日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Controller
@RequestMapping(value = "/*BackAPI/v1.0/manager")
public class BackManagerManagement extends BaseController<SBackUser> {

	
	
	@Resource
	SBackUserService sBackUserService;
	
	
	/**
	 * 新增管理员账号
	 * @param user
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addManager",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addManager(@Valid SBackUser user,BindingResult bindingResult,HttpServletRequest request)throws Exception{
		
		
		String pwd = ControllerHelper.getParameter(request, "pwd");
		user.setPwd(PWDEncrypt.doEncryptByte(pwd));
		user.setFlag(false);
		user.setMale(false);
		user.setBirthdate(Calendar.getInstance().getTime());
		return sBackUserService.addManager(user);
	}
	
	
	/**
	 * 修改管理员账号
	 * @param user
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updateManager",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateManager(@Valid SBackUser user,BindingResult bindingResult,HttpServletRequest request)throws Exception{
		
		return super.update(user);
	}
	
	
	/**
	 * 根据主键逻辑删除管理员
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delManager",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delManager(Long id,HttpServletRequest request)throws Exception{
		
		return super.delete(id);
	}
	
	

	/**
	 * 
	 * 分页查询后台管理员
	 *     支持通过（用户名或真实姓名的）关键字查询
	 * 
	 * @param key
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="pageManager",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageManager(String key, Integer pageNum,Integer pageSize,HttpServletRequest request)throws Exception{
		
		
		ControllerHelper.checkIntegerEmpty(pageNum);
		ControllerHelper.checkIntegerEmpty(pageSize);
		
		
		Example example = new Example(SBackUser.class);
		Criteria cr =  example.createCriteria();
		
		cr.andCondition("flag = false ");
		
		//查询关键字不为空
		if(StringUtils.isNotEmpty(key)){
			cr.andCondition(" name like '"+key+"%' or truename like '"+key+"%' ");
		}
		
		
		return super.getPage(example, pageNum, pageSize);
	}
	
	
	
	
	
	
	/**
	 * 给管理员分配角色和管理范围
	 * @param roleId
	 * @param userScope
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addManagerRoleAndScope",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addManagerRoleAndScope(Long roleId,SBackUserScope userScope, HttpServletRequest request)throws Exception{
		
		long userId =  ControllerHelper.getCurrentUserId(request);
		
		ControllerHelper.checkLongEmpty(roleId);
		
		//设置管理范围
		if(userScope!=null){			
			if(userScope.getProvinceid() ==null || userScope.getProvinceid() == 0){
				userScope.setProvinceid(0l);
			}
			
			if(userScope.getCityid() ==null || userScope.getCityid() == 0){
				userScope.setCityid(0l);
			}
			
			if(userScope.getDistrictid() ==null || userScope.getDistrictid() == 0){
				userScope.setDistrictid(0l);
			}
			
			if(userScope.getSchoolid() ==null || userScope.getSchoolid() == 0){
				userScope.setSchoolid(0l);
			}
			
			//如果是校管理員
			if(roleId==2){
				userScope.setScope("5");
			}else if(roleId==3){//如果是区管理員
				userScope.setScope("4");
			}else{
				userScope.setScope("1");
			}
			
		}else{
			userScope = new SBackUserScope();
			userScope.setScope("1");
		}
		userScope.setUserid(userId);
		userScope.setFlag(false);		
		
		
		return sBackUserService.addManagerRoleAndScope(userId, roleId, userScope);
	}
	
	
	
	
	
	/**
	 * 重置用户密码
	 * @param userid    需要重置的用户
	 * @param pwd       新密码，为空时默认为000000
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="resetPwd",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON resetPwd(Long userid,String pwd)throws Exception{
		
		ControllerHelper.checkLongEmpty(userid);
		
		pwd = StringUtils.isEmpty(pwd)?"000000":pwd.trim();
		
		
		return sBackUserService.resetPwd(userid, pwd);
	}
			
	
	
	
	
	
	
	
	
}
