package net.tfedu.zhl.cloud.resource.autologin.aop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.cloud.resource.poolconfig.service.SAppUserPoolConfigService;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.users.entity.FuncListSimple;

/**
 
 	aop 工具类
 	
 	用于对第三方对接用户的自动登录时 权限的控制
  
    @author wangwr
    @date 2017年2月28日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Component
@Aspect
public class UserServiceAutoLoginAOP {
	
	
	/**
	 * 云洲对接的第三方编码（对接后知好乐用户名的前缀）
	 */
	public static final String THIRDPARTYCODE_YUNZHOU = "yunzhou";

	@Resource
	CommonWebConfig commonWebConfig;
	
	@Resource
	SAppUserPoolConfigService appUserPoolConfigService;
	
	@Resource 
	SAppService sAppService;
	
    @Autowired
    CacheManager cacheManager;


	@Resource
	ResPoolService resPoolService;
	
	
	Map<Long,String> allPoolsMap = null;
	
	

	
	@Pointcut("execution(* net.tfedu.zhl.sso.user.service.JUserService.getUserSimpleByIdForThirdParty(..))")
	public void getUserSimpleByIdForThirdParty() {
	}


	@Around("getUserSimpleByIdForThirdParty()")
	public Object doAround(ProceedingJoinPoint pjp) throws ServletException, IOException {
		
		

		Object result = null;
		UserSimple user = null;
		
		

		try {
		
			
			initPoolMap();
			
			
			
			result = pjp.proceed();
			
			if(result instanceof UserSimple){
				user = (UserSimple)result; 
				if(StringUtils.isNotEmpty(user.getThirdParyCode())
						&& THIRDPARTYCODE_YUNZHOU.equals(user.getThirdParyCode())
						){
					//对云洲对接的用户，处理其权限
					
					user.setFuncList(null);
					//根据知好乐的用户名获取云洲的用户名
					String yunzhou_name = user.getUserName().startsWith(THIRDPARTYCODE_YUNZHOU)?
							user.getUserName().substring(THIRDPARTYCODE_YUNZHOU.length()+1,
							user.getUserName().length())
							:user.getUserName();
					String termName = user.getTermName();
					
					
					
					//获取app
					SApp app = sAppService.getSAppByCode(THIRDPARTYCODE_YUNZHOU);
					
					//获取云洲的用户的物权
					List<FuncListSimple> ls = appUserPoolConfigService.getAppUserPool(app.getAppid().toString(), yunzhou_name, termName);
					
					resetPoolsName(ls);
					
					
					//更新用户的功能菜单
					user.setFuncList(ls);
					
					
					//重置用戶的緩存
			        UserTokenCacheUtil.addUserInfoCache(user.getModel()
			        		,cacheManager, user.getToken(), user, commonWebConfig.getIsRepeatLogin());

					
					
				}
			}
			
			
			
			
			
		} catch (CustomException e) {
			return showErrorPage(e);

		} catch (Throwable e) {
			return showErrorPage(e);

		}
		return result;
	}


	/**
	 * 重置list中FuncListSimple 的name code path 
	 *    重置之前  FuncListSimple 的 code  为资源库的id
	 * @param ls
	 */
	protected void resetPoolsName(List<FuncListSimple> ls) {
		
		if(ls!=null && ls.size()>0){
			for (Iterator<FuncListSimple> iterator = ls.iterator(); iterator.hasNext();) {
				FuncListSimple funcListSimple =  iterator.next();
				String  poolName  = allPoolsMap.get(Long.parseLong(funcListSimple.getCode()));
				funcListSimple.setCode(poolName);
				funcListSimple.setName(poolName);
				funcListSimple.setPath(poolName);
			}
		}
	}


	/**
	 * 初始化库map
	 * @throws Exception
	 */
	protected void initPoolMap() throws Exception {
		if(allPoolsMap==null || allPoolsMap.size() == 0){
			allPoolsMap = new HashMap<Long,String>();
			//获取对应的资源库
			List<ResPool> allPools = resPoolService.getAllPoolsWithAppend();
			for (Iterator<ResPool> iterator = allPools.iterator(); iterator.hasNext();) {
				ResPool resPool = (ResPool) iterator.next();
				allPoolsMap.put(resPool.getId(), resPool.getName());
			}
		}
	}

	/**
	 * 跳转到错误页面
	 * 
	 * @param e
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String showErrorPage(Throwable e) throws ServletException, IOException {
		e.printStackTrace();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setAttribute("message", e.getMessage() == null ? e.getCause() : e.getMessage());
		request.getRequestDispatcher("/common/exception/500.jsp").forward(request, response);
		return null;

	}
	
	
	
	public static void main(String[] args) {
		
		
	}
	
	
	
}
