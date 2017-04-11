package net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.client;


/* 
 * @(#)SessionManager.java    Created on 2008-10-23
 * Copyright (c) 2008 ZDSoft Networks, Inc. All rights reserved.
 * $Id: SessionManager.java 10707 2010-12-09 12:47:56Z huangwj $
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import net.zdsoft.passport.exception.PassportException;
import net.zdsoft.passport.service.client.PassportClient;

/**
 * 用户会话管理类.
 * 
 * @author huangwj
 * @version $Revision: 10707 $, $Date: 2010-12-09 20:47:56 +0800 (星期四, 09 十二月 2010) $
 */
public class SessionManager implements HttpSessionListener {

    public static final String PASSPORT_TICKET_KEY = "passport.ticket";

    private static final Logger log = Logger.getLogger(SessionManager.class);

    private static Map<String, HttpSession> ticket2SessionMap = new ConcurrentHashMap<String, HttpSession>();

    public SessionManager() {
    }

    // 当session被web容器创建后会调用此方法
//    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
    }

    // 当session被web容器销毁之前会调用此方法
//    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        if (session != null) {
            // 取出登录时记录的ticket
            String ticket = (String) session.getAttribute(PASSPORT_TICKET_KEY);
            if (ticket != null) {
                try {
                    // 删除ticket
                    removeTicket(ticket);
                    // 调用Passport服务, 让Passport通知其他站点也做退出操作
                    PassportClient.getInstance().invalidate(ticket);
                    log.info("Invalidated ticket[" + ticket + "]");
                }
                catch (PassportException e) {
                    log.error("Could not invalidate ticket[" + ticket + "]", e);
                }
            }
        }
    }

    // 将ticket和对应的session保存到map中
    public static void putTicket(String ticket, HttpSession session) {
        if (ticket != null) {
            ticket2SessionMap.put(ticket, session);
        }
    }

    // 将指定的ticket从保存ticket的map中删除
    public static HttpSession removeTicket(String ticket) {
        return ticket == null ? null : ticket2SessionMap.remove(ticket);
    }

}