<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../qrcode/utf.js"></script>
<script type="text/javascript" src="../qrcode/jquery-1.8.0.js"></script>
<script type="text/javascript" src="../qrcode/jquery.qrcode.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#qrcodeCanvas").qrcode({
            render : !!document.createElement('canvas').getContext ? 'canvas' : 'table',    //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
            text : "{'note':'央馆对接','userName':'test','pwd':'000000'}",    //扫描了二维码后的内容显示,在这里也可以直接填一个网址，扫描二维码后
            width : "100",               //二维码的宽度
            height : "100",              //二维码的高度
            background : "#ffffff",       //二维码的后景色
            foreground : "#000000",        //二维码的前景色
            src: 'photo.jpg'             //二维码中间的图片
        });
    });
    </script>
<title>助我学APP</title>
</head>
<body>
	<h1>助我学APP</h1>
	
	  <h2>该二维码支持中文和LOGO</h2>
      <div id="qrcodeCanvas"></div>
</body>
</html>