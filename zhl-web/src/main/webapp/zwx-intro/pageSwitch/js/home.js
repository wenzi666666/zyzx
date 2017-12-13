$(document).ready(function() {

	upDown("#slideF1", 8);
	upDown("#slideL2", 8);
	$(window).scroll(function() {
		upDown("#slideF1", 8);
		upDown("#slideL2", 8);

	});

	$(".smallClass").on("click", function() {
		window.open("mircoClass.html");
	});

	//下载判断
	var u = navigator.userAgent,
		app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	var isPC = u.indexOf('AppleWebKit') > -1 || u.indexOf("Trident") > -1 || u.indexOf('Presto') > -1 || (u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1);
	var isWeChat = (u.toLowerCase().match(/MicroMessenger/i) == "micromessenger");

	var urlAndroid = "http://a.app.qq.com/o/simple.jsp?pkgname=com.zhl.hometeacher";
	var urliOS = "https://itunes.apple.com/cn/app/zhu-wo-xue/id1032158173?mt=8";
	var urlHomepage = "http://123.57.218.152/index/zwxIntro/index.html";
	var urlWeChat = "http://a.app.qq.com/o/simple.jsp?pkgname=com.zhl.hometeacher";

	if (isAndroid == true) {
		if (isWeChat == true) {
			$(".android").attr("href", urlWeChat);
		} else {
			$(".android").attr("href", urlAndroid);
		}

	} else if (isiOS == true) {
		if (isWeChat == true) {
			$(".iphone").attr("href", urlWeChat);
		} else {
			$(".iphone").attr("href", urliOS);
		}
	}
});

function upDown(id, height) {

	$(id).animate({
		marginTop: "-=" + height + "px"
	});
	$(id).animate({
		marginTop: "+=" + height + "px"
	});
	$(id).animate({
		marginTop: "-=" + height + "px"
	});
	$(id).animate({
		marginTop: "+=" + height + "px"
	});
	
	
}