<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String userName = (String)request.getAttribute("userName");
String pwd = (String)request.getAttribute("pwd");

%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes" />    
        <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
        <meta name="format-detection" content="telephone=yes"/>
        <meta name="msapplication-tap-highlight" content="no" />
        <meta content="telephone=no" name="format-detection">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <title>下载分享</title>
        <title></title>
        <link rel="stylesheet" href="../qrcode/css/reset.css" />
        <link rel="stylesheet" href="../qrcode/css/ScanCode.css"/>
    </head>
    <body>
        <div class="scan">
            <div class="head">
                <img src="../qrcode/img/head.png" class="bg"/>
                <img src="../qrcode/img/btn.png" class="btn"/>
                
                <iframe src="" width="" height="" id="iframe1" style="display: none;"></iframe>
            </div>
            
            <div class="main">
                <!--<img src="img/icon.png"/ class="icon">
                <div class="message">如果您是微信用户，请点击右上角按钮，在浏览器中打开，即可下载！</div>-->
                <img src="../qrcode/img/qinhua.png"/> 
            </div>
            
            <img src="../qrcode/img/logo.png"  class="logo"/>
            
        </div>
        
        <script type="text/javascript" src="../qrcode/jquery-1.11.3.min.js" ></script>
        <script type="text/javascript" src="../qrcode/ScanCode.js" ></script>
    </body>
</html>

        
        <input type="hidden" name="userName" id=userName value="${userName} "></input>
        <input type="hidden" name="pwd" id="pwd" value="${pwd} "></input>

  
        <script type="text/javascript">
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1 || navigator.platform.indexOf('Linux') > -1; //android终端或者uc浏览器
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

            var url_zwx = "zwx://zwx.tfedu.net/login?username=<%=userName%>&password=<%=pwd%>";
            var isWeChat = (u.toLowerCase().match(/MicroMessenger/i) == "micromessenger");

            if(isAndroid){
            	if(isWeChat){
            		alert("请选择使用原生的浏览器打开");         		
            	}else{
            	    window.location.href= url_zwx;
            	}
            }else if(isiOS){
                
               // alert("因为appstore审核应用需要时间，暂不支持IOS端助我学");
                
                var isSafari =  u.indexOf('Safari') > -1;
                if(!isSafari){
                    alert("请使用原生的浏览器打开");
                }else{
                	window.location.href="zhuwoxue://tapindata?username=<%=userName%>&password=<%=pwd%>";
                }
                  
            }else{
            	alert("助我学尚未提供anroid、IOS系统之外的安装版本");
            }
        
        </script>
