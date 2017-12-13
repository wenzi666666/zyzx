    var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isAndroid==true || isiOS==true) {//如果是手机
		window.location.href="http://zwx.tfedu.net/pageSwitch/index.html";
	}
	