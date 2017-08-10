package net.tfedu.zhl.cloud.resource.integration.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.fileservice.MD5;

public class CookieUtil {

	/**
	 * 资源中心的cookie和云平台的区分
	 */
	private static final String RESOURCE_CENTER_PREFIX = "zhl_resource_";

	// 用户名称
	public static final String DEFAULT_USERID = RESOURCE_CENTER_PREFIX + "userId";
	// 用户名称
	public static final String DEFAULT_USERNAME = RESOURCE_CENTER_PREFIX + "userName";
	// 真实姓名
	public static final String DEFAULT_TRUENAME = RESOURCE_CENTER_PREFIX + "trueName";
	// 数据源ID
	public static final String DEFAULT_DBID = RESOURCE_CENTER_PREFIX + "DBId";
	// 用户注册节点
	public static final String DEFAULT_REGISTERNODE = RESOURCE_CENTER_PREFIX + "registerNode";

	// socketIp
	public static final String DEFAULT_SOCKETIP = RESOURCE_CENTER_PREFIX + "socketIp";
	// socketPort
	public static final String DEFAULT_SOCKETPORT = RESOURCE_CENTER_PREFIX + "socketPort";
	// 用户Role
	public static final String DEFAULT_ROLEID = RESOURCE_CENTER_PREFIX + "roleId";
	// 登录验证码
	public static final String DEFAULT_RAND = RESOURCE_CENTER_PREFIX + "rand";

	// 设备信息
	public static final String DEFAULT_DEVICEINFO = RESOURCE_CENTER_PREFIX + "deviceInfo";

	/**
	 * 云平台的版本信息(对接中区分云平台的请求，并定制页面)
	 */
	public static final String DEFAULT_CLOUDPLATFORM_VRESION = RESOURCE_CENTER_PREFIX + "cloudplatformVersion";
	/**
	 * 云平台Local访问地址 url： 用于系统之间的通讯
	 */
	public static final String DEFAULT_CLOUDPLATFORM_LOCAL = RESOURCE_CENTER_PREFIX + "cloudPlatFormLocal";
	/**
	 * 云平台访问地址 url： 用于返回云平台的页面
	 */
	public static final String DEFAULT_CLOUDPLATFORM_CURRENT = RESOURCE_CENTER_PREFIX + "currentCloudForm";

	// 默认cookie 一个月有效
	public static final int DEFAULT_MAXAGE = 30 * 24 * 3600;

	public static final String PAPERBASKET = RESOURCE_CENTER_PREFIX + "paperBasket";
	// 教师授课模式
	public static final String DEFAULT_TEACHTYPE = RESOURCE_CENTER_PREFIX + "teachtype";

	/**
	 * 教师当前选中的自建目录
	 */
	public static final String DEFAULT_COURSEID = RESOURCE_CENTER_PREFIX + "courseId_sel_cur";

	/**
	 * 教师当前选中的自建目录
	 */
	public static final String DEFAULT_SYSCOURSEID = RESOURCE_CENTER_PREFIX + "sysCourseId_sel_cur";

	/**
	 * 题类型（选择题，填空题）
	 */
	public static final String TIKU_SHITILIST = RESOURCE_CENTER_PREFIX + "TiKu_shitiList";

	/**
	 * 保持登录状态 在cookie中保存加密后的用户名密码
	 */
	public static final String KEEP_LOGIN_USER_NAME = RESOURCE_CENTER_PREFIX + "keep_login_name";
	public static final String KEEP_LOGIN_USER_PWD = RESOURCE_CENTER_PREFIX + "keep_login_pwd";
	public static final String KEEP_LOGIN_KEEP_FLAG = RESOURCE_CENTER_PREFIX + "keep_login_flag";
	/**
	 * 题ID
	 */
	public static final String TIKU_XIAOTILIST = RESOURCE_CENTER_PREFIX + "TiKu_XiaoTiList";

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @throws UnsupportedEncodingException
	 */
	public static void addCookie(HttpServletResponse response, HttpServletRequest request, String name, String value,
			int maxAge) throws UnsupportedEncodingException {
		String serverName = request.getServerName();
		String setCookieDomain = request.getServletContext().getInitParameter("setCookieDomain");
		String domain = null;// 站点domain
		if ("Y".equalsIgnoreCase(setCookieDomain)) {
			domain = getDomainByServerName(serverName);
		}

		value = value == null ? value : URLEncoder.encode(value, "utf-8");
		Cookie coo = new Cookie(name, value);
		coo.setPath("/");
		if (maxAge > 0) {
			coo.setMaxAge(maxAge);
		}
		if (domain != null) {
			coo.setDomain(domain);
		}
		response.addCookie(coo);
	}

	/**
	 * 根据host 获取domain 1 localhost 主机名 2 ip 地址 192.168.111.10 3 域名 ：
	 * yun.tfedu.net 或 baidu.com
	 * 
	 * @param serverName
	 * @return
	 */
	private static String getDomainByServerName(String serverName) {
		if (serverName == null) {
			return null;
		} else if (serverName.indexOf(".") == -1) { // 主机名
			return null;
		} else if (serverName.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$")) {// IP
			return null;
		} else {// 域名 yun.tfedu.net 或 baidu.com
			String temp[] = serverName.split("\\.");
			int start = temp.length - 2;
			return new StringBuffer().append(".").append(temp[start]).append(".").append(temp[start + 1]).toString();
		}
	}

	/**
	 * get cookie by name
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookieByName(HttpServletRequest request, String name) throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equals(cookies[i].getName())) {
					String temp = cookies[i].getValue();
					return temp == null ? temp : URLDecoder.decode(temp, "utf-8");
				}
			}
		}
		return null;
	}

	/**
	 * 返回用户信息
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HashMap getUserInfo(HttpServletRequest request) throws UnsupportedEncodingException {
		HashMap map = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			map = new HashMap();
			for (int i = 0; i < cookies.length; i++) {
				String _name = cookies[i].getName();
				String _val = cookies[i].getValue();
				_val = URLDecoder.decode(_val, "utf-8");
				if (DEFAULT_USERID.equals(_name)) {
					map.put(DEFAULT_USERID, _val);
				} else if (DEFAULT_USERNAME.equals(_name)) {
					map.put(DEFAULT_USERNAME, _val);
				} else if (DEFAULT_DBID.equals(_name)) {
					map.put(DEFAULT_DBID, _val);
				} else if (DEFAULT_SOCKETIP.equals(_name)) {
					map.put(DEFAULT_SOCKETIP, _val);
				} else if (DEFAULT_SOCKETPORT.equals(_name)) {
					map.put(DEFAULT_SOCKETPORT, _val);
				} else if (DEFAULT_ROLEID.equals(_name)) {
					map.put(DEFAULT_ROLEID, _val);
				} else if (DEFAULT_REGISTERNODE.equals(_name)) {
					map.put(DEFAULT_REGISTERNODE, _val);
				} else if (DEFAULT_TRUENAME.equals(_name)) {
					map.put(DEFAULT_TRUENAME, _val);
				} else if (DEFAULT_COURSEID.equals(_name)) {
					map.put(DEFAULT_COURSEID, _val);
				} else if (DEFAULT_SYSCOURSEID.equals(_name)) {
					map.put(DEFAULT_SYSCOURSEID, _val);
				} else if (DEFAULT_DEVICEINFO.equals(_name)) {
					map.put(DEFAULT_DEVICEINFO, _val);
				}
			}
			return map;
		}
		return null;
	}

	/**
	 * 清理用户的cookie信息 一并清理云平台对接时传递的数据
	 * 
	 * @param request
	 * @param response
	 */
	public static void deleteUserInfo(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Cookie[] cookies = request.getCookies(); String domain =
		 * null;//站点domain String setCookieDomain =
		 * request.getServletContext().getInitParameter("setCookieDomain");
		 * if("Y".equalsIgnoreCase(setCookieDomain)){ String serverName =
		 * request.getServerName(); domain = getDomainByServerName(serverName);
		 * } if(cookies!=null && cookies.length > 0){ for(int
		 * i=0;i<cookies.length;i++){
		 * 
		 * //只修改本域下的cookie if(domain!=null &&
		 * !domain.equals(cookies[i].getDomain())){ continue; }
		 * if(DEFAULT_USERID.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_USERNAME.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_DBID.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_SOCKETIP.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_SOCKETPORT.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_ROLEID.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_REGISTERNODE.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_TRUENAME.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_COURSEID.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_SYSCOURSEID.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_DEVICEINFO.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_CLOUDPLATFORM_VRESION.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_CLOUDPLATFORM_LOCAL.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }else
		 * if(DEFAULT_CLOUDPLATFORM_CURRENT.equals(cookies[i].getName())){
		 * cookies[i].setMaxAge(0); }
		 * 
		 * if(domain!=null){ cookies[i].setDomain(domain); }
		 * cookies[i].setPath("/");
		 * 
		 * response.addCookie(cookies[i]); } }
		 */
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 */
	public static void loginOut(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String domain = null;// 站点domain
		String setCookieDomain = request.getServletContext().getInitParameter("setCookieDomain");
		if ("Y".equalsIgnoreCase(setCookieDomain)) {
			String serverName = request.getServerName();
			domain = getDomainByServerName(serverName);
		}
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {

				// 只修改本域下的cookie
				if (domain != null && !domain.equals(cookies[i].getDomain())) {
					continue;
				}
				if (DEFAULT_USERID.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_USERNAME.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_DBID.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_SOCKETIP.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_SOCKETPORT.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_ROLEID.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_REGISTERNODE.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_TRUENAME.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_COURSEID.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_SYSCOURSEID.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_DEVICEINFO.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_CLOUDPLATFORM_VRESION.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_CLOUDPLATFORM_LOCAL.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				} else if (DEFAULT_CLOUDPLATFORM_CURRENT.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				}

				if (domain != null) {
					cookies[i].setDomain(domain);
				}
				cookies[i].setPath("/");

				response.addCookie(cookies[i]);
			}
		}
	}

	/**
	 * 保持用户名密码
	 * 
	 * @param operat
	 */
	public static void doKeepLogin(HttpServletResponse response, HttpServletRequest request, String keepLoginFlag,
			String name, String pwd, int maxAge) throws UnsupportedEncodingException {
		keepLoginFlag = "Y".equals(keepLoginFlag) ? "Y" : "N";

		String serverName = request.getServerName();
		String setCookieDomain = request.getServletContext().getInitParameter("setCookieDomain");
		String domain = null;// 站点domain
		if ("Y".equalsIgnoreCase(setCookieDomain)) {
			domain = getDomainByServerName(serverName);
		}

		Cookie coo_name = new Cookie(KEEP_LOGIN_USER_NAME, Base64.doEncoding64(name));
		Cookie coo_pwd = new Cookie(KEEP_LOGIN_USER_PWD, Base64.doEncoding64(pwd));
		Cookie coo_flag = new Cookie(KEEP_LOGIN_KEEP_FLAG, keepLoginFlag);
		coo_name.setPath("/");
		coo_pwd.setPath("/");
		coo_flag.setPath("/");
		if (maxAge > 0) {
			coo_name.setMaxAge(maxAge);
			coo_pwd.setMaxAge(maxAge);
			coo_flag.setMaxAge(maxAge);
		}
		if (domain != null) {
			coo_name.setDomain(domain);
			coo_pwd.setDomain(domain);
			coo_flag.setDomain(domain);
		}
		response.addCookie(coo_flag);
		response.addCookie(coo_name);
		response.addCookie(coo_pwd);
	}

	/**
	 * 删除用户名密码
	 * 
	 * @param operat
	 */
	public static void removeKeepLogin(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String domain = null;// 站点domain
		String setCookieDomain = request.getServletContext().getInitParameter("setCookieDomain");
		if ("Y".equalsIgnoreCase(setCookieDomain)) {
			String serverName = request.getServerName();
			domain = getDomainByServerName(serverName);
		}
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (KEEP_LOGIN_USER_NAME.equals(cookies[i].getName())
						|| KEEP_LOGIN_KEEP_FLAG.equals(cookies[i].getName())
						|| KEEP_LOGIN_USER_PWD.equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
					if (domain != null) {
						cookies[i].setDomain(domain);
					}
					cookies[i].setPath("/");
					response.addCookie(cookies[i]);
				}
			}
		}
	}

	/**
	 * 获取用户名密码
	 * 
	 * @param operat
	 */
	public static HashMap getKeepLogin(HttpServletRequest request) throws UnsupportedEncodingException {
		HashMap map = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			map = new HashMap();
			for (int i = 0; i < cookies.length; i++) {
				String _name = cookies[i].getName();
				String _val = cookies[i].getValue();
				_val = URLDecoder.decode(_val, "utf-8");
				if (KEEP_LOGIN_USER_NAME.equals(_name)) {
					map.put(KEEP_LOGIN_USER_NAME, _val != null && _val.length() > 0 ? Base64.doDecoding64(_val) : "");
				} else if (KEEP_LOGIN_USER_PWD.equals(_name)) {
					map.put(KEEP_LOGIN_USER_PWD, _val != null && _val.length() > 0 ? Base64.doDecoding64(_val) : "");
				} else if (KEEP_LOGIN_KEEP_FLAG.equals(_name)) {
					map.put(KEEP_LOGIN_KEEP_FLAG, _val);
				}
			}
			return map;
		}
		return null;
	}

	public static void operats(HttpServletRequest request, HttpServletResponse response, String operat) {

		try {
			if (operat == null) {
				CookieUtil.addCookie(response, request, "operatsss", CookieUtil.getCookieByName(request, "operatsss"),
						24 * 3600);
			} else {
				Cookie[] cookies = request.getCookies();
				if (cookies.length > 0) {
					for (int i = 0; i < cookies.length; i++) {
						if ("operatsss".equals(cookies[i].getName())) {
							cookies[i].setMaxAge(0);
						}
						response.addCookie(cookies[i]);
					}
				}
				CookieUtil.addCookie(response, request, "operatsss", operat, 24 * 3600);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getCookieByBasket(HttpServletRequest request, String basket)
			throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (basket.equals(cookies[i].getName())) {
				String temp = cookies[i].getValue();
				return temp == null ? temp : URLDecoder.decode(temp, "utf-8");
			}
		}
		return null;
	}

	public static String getDeviceInfoWeb(HttpServletRequest request, long userid, long nodeid, int clientType) {
		// 由request获取浏览器版本、ip地址
		String clientVersion = request.getHeader("user-agent");
		String loginIP = CookieUtil.getIpAddr(request);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curTime = format.format(Calendar.getInstance().getTime());
		StringBuffer sb = new StringBuffer();
		sb.append(curTime).append("_").append(userid).append("_").append(nodeid).append("_").append(clientType)
				.append("_").append(loginIP).append("_").append(clientVersion).append("_").append(curTime);
		return MD5.MD5(sb.toString());
	}

	/** 获取IP地址 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

}