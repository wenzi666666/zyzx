$(function(){
$(".btn").on("click",function(){
    $(this).attr("src","img/btnc.png");
    var u = navigator.userAgent, app = navigator.appVersion;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android缁堢鎴栬€卽c娴忚鍣�
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios缁堢
    var isWeChat = (u.toLowerCase().match(/MicroMessenger/i) == "micromessenger");
    
    var urlAndroid = "http://zwx.tfedu.net/apk/zwx.apk";
    var urliOS = "https://appsto.re/cn/DVxH9.i";
    var urlWeChat = "http://a.app.qq.com/o/simple.jsp?pkgname=com.zhl.hometeacher&g_f=991653";
    
    if(isAndroid==true)
    {
        if(isWeChat==true)
        {
            window.location.href=urlWeChat;
        }
        else
        {
            window.location.href=urlWeChat;
        }
        
    } else if(isiOS==true)
    {
        if(isWeChat==true)
        {
            window.location.href=urlWeChat;
        }
        else
        {
            window.location.href=urliOS;
        }
        
    }else{
        window.location.href=urlWeChat;
    }
});
  
});