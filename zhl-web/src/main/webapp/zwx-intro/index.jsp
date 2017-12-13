<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String userName = (String)request.getAttribute("userName");
String pwd = (String)request.getAttribute("pwd");

%>
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
        
        <script type="text/javascript" src="../qrcode/jquery-1.8.0.js"></script>
        <script type="text/javascript" src="../qrcode/jquery.qrcode.js"></script>
        <script type="text/javascript" src="../qrcode/utf.js"></script>
        <input type="hidden" name="userName" id=userName value="${userName} "></input>
        <input type="hidden" name="pwd" id="pwd" value="${pwd} "></input>
        
        <script type="text/javascript">
        
	        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	        
	        if(isAndroid){
	        	window.location.href="zwx://zwx.tfedu.net/login?username=<%=userName%>&password=<%=pwd%>";
	        }
        
        
        </script>
        
    </head>

    <body>
        
        <!--查看微课弹出框-->
        <div class="mb">
            <div class="videoInfo" id="videoInfo">
                <div></div>
            </div>
        </div>
        <div class="smallClass" id="smallClass">
            <!--年级-->
            <div class="small_main" id="smallMain">
                <span class="offVideo"></span>
                <div class="small_title">名师微课</div>
                <ul class="small_term" id="smallTerm">
                    <li class="style" id="seven">七年级</li>
                    <li id="eight">八年级</li>
                    <li id="nine">九年级</li>
                    <li id="hs1">高一</li>
                    <li id="hs2">高二</li>
                    <li id="hs3">高考</li>
                </ul>
                <!--科目-->
                <div class="small_video">
                    <div class="subject math">
                        <ul class="videoUl" id="mathUl">
                        </ul>
                        <div class="ulHead">
                            数学
                        </div>
                    </div>
                    <div class="subject physics">
                        <ul class="videoUl" id="physicsUl">
                        </ul>
                        <div class="ulHead">
                            物理
                        </div>
                    </div>
                    <div class="subject chemistry">
                        <ul class="videoUl" id="chemistryUl">   
                        </ul>
                        <div class="ulHead">
                            化学
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
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
                    <span>扫码下载app</span>
                    <div>
                        <img src="../zwx-intro/img/QRcode.png" id="QRcode" />
                    </div>
                    <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.zhl.hometeacher">
                        <button id="android">Android下载</button>
                    </a>
                    <a href="https://itunes.apple.com/cn/app/zhu-wo-xue/id1032158173?mt=8">
                        <button id="ios">iPhone下载</button>
                    </a>
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