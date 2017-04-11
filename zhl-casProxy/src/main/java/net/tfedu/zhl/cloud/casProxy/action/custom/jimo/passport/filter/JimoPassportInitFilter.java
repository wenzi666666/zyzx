package net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.passport.service.client.PassportClient;
import net.zdsoft.passport.service.client.PassportClientParam;
import net.zdsoft.passport.service.client.RemotingServiceProtocal;

/**
 * 
 *     使用filter 替换 即墨对接原来的servlet
 * @author wangwr
 * @date 2017年4月7日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class JimoPassportInitFilter implements Filter {

	public static String passportURL;

	public static String serverId;

	public static String verifyKey;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取适合自身系统的 serverId (服务器编号)和 verifyKey (校验码)
		passportURL = filterConfig.getInitParameter("passportURL");
		serverId = filterConfig.getInitParameter("serverId");
		verifyKey = filterConfig.getInitParameter("verifyKey");

		System.out.println("-----JimoPassportInitFilter-init----passportURL--"+passportURL);
		System.out.println("-----JimoPassportInitFilter-init----serverId--"+serverId);
		System.out.println("-----JimoPassportInitFilter-init----verifyKey--"+verifyKey);
		if (StringUtils.isEmpty(passportURL)) {
			throw new NullPointerException("passportURL is null");
		}

		if (StringUtils.isEmpty(serverId)) {
			throw new NullPointerException("serverId is null");
		}

		if (StringUtils.isEmpty(verifyKey)) {
			throw new NullPointerException("verifyKey is null");
		}

		
		// 创建 PassportClient 参数对象
		PassportClientParam param = new PassportClientParam(passportURL, Integer.parseInt(serverId), verifyKey);

		// 对 PassportClient 初始化, 可选服务协议有 SOAP 和 Spring HttpInvoker
		// 这里使用 Spring HttpInvoker 协议
		PassportClient.getInstance().init(param, RemotingServiceProtocal.SOAP);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("-------JimoPassportInitFilter-------doFilter----");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
