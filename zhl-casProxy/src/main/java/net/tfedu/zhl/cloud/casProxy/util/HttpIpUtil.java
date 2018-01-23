package net.tfedu.zhl.cloud.casProxy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取真实IP工具对象
 * 
 * copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-12-5
 * @version v1.0.0
 */
public class HttpIpUtil {

	/**
	 * 获取客户端的真实IP地址
	 * 
	 * WL-Proxy-Client-IP=211.161.1.239 Proxy-Client-IP=211.161.1.239
	 * X-Forwarded-For=211.161.1.239 WL-Proxy-Client-Keysize=
	 * WL-Proxy-Client-Secretkeysize= X-WebLogic-Request-ClusterInfo=true
	 * X-WebLogic-KeepAliveSecs=30 X-WebLogic-Force-JVMID=-327089098
	 * WL-Proxy-SSL=false
	 * 
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getIpAddr(HttpServletRequest request) throws UnknownHostException {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				inet = InetAddress.getLocalHost();
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
