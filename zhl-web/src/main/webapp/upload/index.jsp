<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String path = request.getContextPath();   
%>
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="utf-8">  
<title>上传图片</title>  
</head>  
<body>  
<form action="<%=path %>/resBackAPI/appUserPoolConfig/v1.0/uploadExcelFile"
method="post" enctype="multipart/form-data">  
<input type="text" name="appId">
<input type="file" name="file" /> <input type="submit" value="Submit" /></form>  
</body>  
</html>  