package net.tfedu.zhl.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.MD5SignError;
import net.tfedu.zhl.core.exception.PropertiesMissing;
import net.tfedu.zhl.helper.sign.SignUtil;

/**
 * 
 * 后台第三方对接时的拦截器
 * 
 * 仅支持url中传递参数，且md5校验
 * 
 * @author wangwr
 * @date 2017年1月5日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class RequestParamsMD5CheckInterceptor implements HandlerInterceptor {


	@Autowired
	CommonWebConfig config;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String method = request.getMethod();

		if (RequestMethod.OPTIONS.toString().equals(method)) {
			return false;
		}
		
		//上传请求不过滤
		if(request instanceof  DefaultMultipartHttpServletRequest){
			return true ;
		}
		
		
		
		
			
		// 获取sign
		String sign = request.getParameter("sign");
		if (StringUtils.isNotEmpty(sign)) {
			
			String appKey = config.getAppKey();
			// 检查配置信息
			if (StringUtils.isEmpty(appKey)) {
				throw new PropertiesMissing("appKey");
			}
			// md5校验
			if (!sign.equals(SignUtil.createSign(request, appKey))) {
				throw new MD5SignError();
			}
		}else{
			throw new MD5SignError();
		}



		return true;

	}

	
}
