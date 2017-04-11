/* 
 * @(#)PassportClientInitServlet.java    Created on 2008-10-28
 * Copyright (c) 2008 ZDSoft Networks, Inc. All rights reserved.
 * $Id: PassportClientInitServlet.java 9426 2010-10-15 09:33:45Z huangwj $
 */
package net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.passport.service.client.PassportClient;
import net.zdsoft.passport.service.client.PassportClientParam;
import net.zdsoft.passport.service.client.RemotingServiceProtocal;

/**
 * 对 {@link PassportClient} 类进行初始化的 Servlet 类。<br>
 * 此 Servlet 应该在应用启动时进行初始化操作。
 * 
 * @author huangwj
 * @version $Revision: 9426 $, $Date: 2010-10-15 17:33:45 +0800 (星期五, 15 十月 2010) $
 */
public class PassportClientInitServlet extends HttpServlet {

    private static final long serialVersionUID = -7552414514624448570L;
    
    public static String passportURL;

    public static String serverId;

    public static String verifyKey;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 获取适合自身系统的 serverId (服务器编号)和 verifyKey (校验码)
        passportURL = config.getInitParameter("passportURL");
        serverId = config.getInitParameter("serverId");
        verifyKey = config.getInitParameter("verifyKey");
        
        
        System.out.println("-----PassportClientInitServlet-init----passportURL--"+passportURL);
		System.out.println("-----PassportClientInitServlet-init----serverId--"+serverId);
		System.out.println("-----PassportClientInitServlet-init----verifyKey--"+verifyKey);

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
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}
    
    

}