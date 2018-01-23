/* 
 * @(#)LogoutServlet.java    Created on 2008-10-28
 * Copyright (c) 2008 ZDSoft Networks, Inc. All rights reserved.
 * $Id: LogoutServlet.java 10707 2010-12-09 12:47:56Z huangwj $
 */
package net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.client.SessionManager;
import net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.dto.User;
import net.zdsoft.passport.service.client.PassportClient;

/**
 * 处理用户退出系统操作的Servlet类.
 * 
 * @author huangwj
 * @version $Revision: 10707 $, $Date: 2010-12-09 20:47:56 +0800 (星期四, 09 十二月 2010) $
 */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = -3903797543957844738L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取ticket
        String ticket = (String) req.getSession().getAttribute(SessionManager.PASSPORT_TICKET_KEY);
        // 从SessionManager中清除ticket
        SessionManager.removeTicket(ticket);

        // 清除ticket是为了在session失效的时候不再重复通知Passport
        req.getSession().removeAttribute(SessionManager.PASSPORT_TICKET_KEY);
        req.getSession().removeAttribute(User.KEY);
        req.getSession().invalidate();

        // 跳转到Passport的退出系统的地址
        String passportLogoutURL = PassportClient.getInstance().getLogoutURL(ticket, getWebsiteRoot(req), null);
        resp.sendRedirect(passportLogoutURL);
    }

    private static String getWebsiteRoot(HttpServletRequest request) {
        int serverPort = request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + (serverPort == 80 ? "" : ":" + serverPort)
                + request.getContextPath();
    }

}