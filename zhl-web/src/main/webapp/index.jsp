<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="net.tfedu.zhl.config.CommonWebConfig"%>
<%@ page import="net.tfedu.zhl.helper.ApplicationContextHelper"%>


<%





CommonWebConfig commonWebConfig =  (CommonWebConfig) ApplicationContextHelper.getBean("commonWebConfig");


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部署信息</title>
</head>
<body>
	<div style="text-align: center; margin-top: 30px;">
		<h3>JAVA后端部署成功</h3>
		<h5>
			当前项目：<%=commonWebConfig.getBussinessname() %></h5>
		<h5>
			当前版本：<%=commonWebConfig.getVersion() %></h5>


	</div>
</body>
</html>