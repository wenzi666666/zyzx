<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误：500</title>
</head>
<script src="${pageContext.request.contextPath}/common/exception/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/exception/js/my500.js" type="text/javascript"  charset="utf-8"></script>
<link  type="text/css" href="${pageContext.request.contextPath}/common/exception/css/my404.css" rel="stylesheet"/>
<body>
<div class="my404">
    <div class="my404Top">  
        <img src="${pageContext.request.contextPath}/common/exception/images/404.jpg" class="flimg"/>
        <div class="my404_R">
            <p class="p1">500</p>
            <p class="p2">:( 很抱歉，服务器后端发生错误！</p><br/>
            <p class="p2" style='color:red;'>${message }</p>
        </div>
    </div>
</div>
</body>
</html>
