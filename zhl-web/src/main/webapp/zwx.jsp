<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../qrcode/utf.js"></script>
<script type="text/javascript" src="../qrcode/jquery-1.8.0.js"></script>
<script type="text/javascript" src="../qrcode/jquery.qrcode.js"></script>
<input type="hidden" name="targetPath" id="targetPath" value="${targetPath}"></input>

<script type="text/javascript">
    $(document).ready(function() {
    	var targetPath = $("#targetPath").val()+"&time="+new Date().getTime();
    	
        $("#qrcodeCanvas").qrcode({
            render : !!document.createElement('canvas').getContext ? 'canvas' : 'table',    //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
            text : targetPath,    //扫描了二维码后的内容显示,在这里也可以直接填一个网址，扫描二维码后
            width : "150",               //二维码的宽度
            height : "150",              //二维码的高度
            background : "#ffffff",       //二维码的后景色
            foreground : "#000000",        //二维码的前景色
            src: '../zwx-intro/img/poplogo.png'             //二维码中间的图片
        });
    });
    </script>
<title>助我学APP</title>
</head>
<body>
  <!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
        <meta name="renderer" content="webkit" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="助我学" />
        <meta name="description" content="助我学" />
        <title>助我学</title>
        <link rel="shortcut icon" href="../zwx-intro/img/logoicon.png" />
        <link rel="stylesheet" href="../zwx-intro/css/home.css" />
        <link rel="stylesheet" href="../zwx-intro/css/slides.css" />
        <link rel="stylesheet" href="../zwx-intro/css/smallClass.css" />
        <link rel="stylesheet" href="../zwx-intro/css/login.css" />
    </head>

    <body>
        
        <!--登陆、注册-->
        <!--遮罩-->
        <div class="cover1">

        </div>
        <div class="popChange">
            <span class="tit2"></span>
        </div>
        <input type="hidden" id="userId" />
        <input type="hidden" id="userName" />
        <input type="hidden" id="trueName" />
        <input type="hidden" id="schoolId" />
        <input type="hidden" id="schoolName" />

        <!--弹出蒙版层-->
        <div class="cover">
        </div>
        
        <input type="hidden" id="code" />
        <input type="hidden" id="registerId" />
       
        
        <!--首页内容-->
        <div class="firstPage">
            <div class="header">
                <div class="header-right">
                    <span>请用手机的默认浏览器扫描下方二维码</span>
                    <span>前往登录或下载页面</span>
                    <div>
                        <div id="qrcodeCanvas"></div>
                    </div>
                    <span>第一次下载安装之后请重复扫码登录</span>
                </div>
                <div class="gradient"></div>
            </div>
        
            <div class="section2">
                
            </div>
            <div class="section3">
                
            </div>
            <div class="section4">
                
            </div>
            <div class="footer">
                <p>联系我们 客服电话：400-051-8958-3(周一至周五 10:00-17:00) 客服QQ:3324624248 手机：13220155438 微信号：zhuwoxue</p>
                <p>Copyright@2000-2015版权所有：同方知好乐教育科技（北京）有限公司 京ICP备 05045440号</p>
            </div>
        </div>
    </body>

</html>    
      
</body>
</html>