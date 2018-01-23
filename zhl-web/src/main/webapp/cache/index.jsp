<%@page import="java.util.*"%>
<%@page import="org.springframework.cache.CacheManager"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>  
<%@page import="org.springframework.context.ApplicationContext"%>  
<%@page import="org.springframework.cache.Cache"%>  
<%@page import="com.alibaba.fastjson.JSONObject"%>  
<%@page import="org.springframework.cache.ehcache.EhCacheCacheManager"%>  
<%@page import="net.sf.ehcache.Ehcache"%>  
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%


ServletContext context = request.getSession().getServletContext();  
ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
CacheManager cacheManager = (CacheManager)ctx.getBean("cacheManager");







	



%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

EhCacheCacheManager _cacheManager = (EhCacheCacheManager)cacheManager;

Collection<String> keys = _cacheManager.getCacheNames();

for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
	
	String string = (String) iterator.next();
	
	Cache _cache = _cacheManager.getCache(string);
	
	
	Ehcache cache  = (Ehcache)_cache.getNativeCache();
	
	
	List<String> list =  cache.getKeys();
	
	
			
			
	
%>

缓存命名空间：<%=string %>

缓存内容：
<br>
<table>
			<thead>
				<tr>KEY</tr>
				<tr>VALUE</tr>
			</thead>
			<tbody>
		
			<%
			
			
			for (Iterator<String> iterator2 = list.iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
			 %>
				<tr><%=key %></tr>
				<tr><%=JSONObject.toJSONString(cache.get(key)) %></tr>
			<%} %>
			</tbody>

		</table>
	
<hr>
<br/>
<%
}


%>
	




</body>
</html>