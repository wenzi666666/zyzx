package net.tfedu.zhl.cloud.resource.integration.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;
import net.tfedu.zhl.cloud.resource.integration.util.CloudIntegralParamCheckUtil;

/**
 * 
 * 拦截器--校验对接云平台的数据接口的参数
 * 
 * @author wangwr
 * @date 2017年8月4日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class CloudDataInteceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object controller,
			Exception e) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller,
			ModelAndView model) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		if (null != result) {
			// 如果参数有异常

			response.setContentType("application/json");
			response.getWriter().write("");

			return false;
		}

		return true;
	}

}
