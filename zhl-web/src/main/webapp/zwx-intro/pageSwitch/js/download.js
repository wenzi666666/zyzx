$(function(){
	 var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		var isPC = u.indexOf('AppleWebKit') > -1 || u.indexOf("Trident") > -1 || u.indexOf('Presto') > -1 || ( u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1);
		var isWeChat = (u.toLowerCase().match(/MicroMessenger/i) == "micromessenger");
		
		var urlAndroid = "http://a.app.qq.com/o/simple.jsp?pkgname=com.zhl.hometeacher&g_f=991653";
		var urliOS = "https://appsto.re/cn/DVxH9.i";
		var urlHomepage = "http://123.57.218.152/index/zwxIntro/index.html";
		var urlWeChat = "http://a.app.qq.com/o/simple.jsp?pkgname=com.zhl.hometeacher&g_f=991653";
		
		var bg = $("#bgDownload");	
		var ap = $("#alertBox");
		var bw = bg.width();
		var aw = ap.width();
		var margin = 0.5 * ($("body").width() - bw);
			
		if(isAndroid == true){
			if(isWeChat == true){
				$("#downloadUrl").attr("href", urlWeChat);
			}
			else{
				$("#downloadUrl").attr("href", urlAndroid);
			}
			
		}
		else if(isiOS == true){
			if(isWeChat == true){
				$("#downloadUrl").attr("href", urlWeChat);
			}
			else
			{
				$("#downloadUrl").attr("href", urliOS);
			}
		}
		else if(isPC == true){
			$("#downloadUrl").attr("href", urlHomepage);	
		}
});
