$(function() {
//	var storage = window.localStorage;
//	if ($.cookie("autoLogin") != null && $.cookie("autoLogin") != "" && (storage.loginUserId)) {
//		$(".teacher").attr("src", storage.loginTeacherHead);
//		$(".school").text(storage.loginSchoolName);
//		$(".enter").show();
//		$(".login").hide();
//		$(".register").hide();
//	} else if (($.cookie("autoLogin") == null) || ($.cookie("autoLogin") == "")) {
//		$(".enter").hide();
//		$(".login").show();
//		$(".register").show();
//
//	}
		//完善信息框
//		$(".popFillInfo").show();
		
	//	var mainHeight = $(window).height() - 53;
	//	var allMarginTop = mainHeight - 487;
	//	var mainTop = Math.floor(allMarginTop / 5);
	//	var bannerTop = Math.floor(allMarginTop / 5) * 2;
	//	var messageTop = Math.floor(allMarginTop / 5) * 2;
	//	$(".banner").css("marginTop", bannerTop);
	//	$(".main").css("marginTop", mainTop);
	
	
	if ($.cookie("userName") && $.cookie("userPwd")) {
//		var deUserPwd = $.base64.decode($.cookie("userPwd"));
		$(".user").val($.cookie("userName"));
		$(".psw").val($.cookie("userPwd"));

	}
	mouseDownOut(".login", "loginc");
	mouseDownOut(".register", "registerc");
	mouseDownOut(".errorText", "errorTextc");
	mouseDownOut(".classManage", "classManagec");
	mouseDownOut(".smallClass", "smallClassc");
	mouseDownOut(".lastStep", "lastStepc");
	mouseDownOut(".nextStep", "nextStepc");
	mouseDownOut(".loginBtn", "loginBtnc");
	mouseDownOut(".setAdmin", "setAdminc");
	mouseDownOut(".intoEmail", "intoEmailc");
	mouseDownOut(".fillInfo", "fillInfoc");
	mouseDownOut(".setAdmin2", "setAdmin2c");
	mouseDownOut(".sure", "surec");
	mouseDownOut(".backa", "backac");

	//	绑定enter事件
	enterSure();
	
	//关闭弹出框
	$(document).on("click", ".off", function() {
//		$(".cover1").slideUp();
		$(".popChange").html("");
	});

	$(".offVideo").on("click", function() {
//		$(".cover").slideUp();
		$(".pop").slideUp();
	});

	$(".cover").on("click", function() {
//		$(this).slideUp();
		$(".pop").slideUp();
	});
	
	
	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {

			if (btnN == ".loginBtn") {
				if ($(".popLogin").css("display") != "none") {
					$(".loginBtn").click();
				} else {
					btnN = "";
				}
			}

		}
	});
	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == "#pwdSure") {
				if ($(".popChangePwd").css("display") != "none") {
					$("#pwdSure").click();
				} else {
					btnN = "";
				}
			}
		}
	});

	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == "#infoSure") {
				if ($(".popManInfo").css("display") != "none") {
					$("#infoSure").click();
				} else {
					btnN = "";
				}

			}
		}
	});

	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == "#setTeacherInfo") {
				if ($(".popFillInfo").css("display") != "none") {
					$("#setTeacherInfo").click();
				} else {
					btnN = "";
				}

			}
		}
	});

	//光标定位 alert为空
	clearAlert(".user", "#loginError");
	clearAlert(".psw", "#loginError");
	clearAlert("#infoUserName", ".action1");
	clearAlert("#infoRealName", ".action1");
	clearAlert("#prePwd", ".action2");
	clearAlert("#newPwd", ".action2");
	clearAlert("#newPwds", ".action2");
	clearAlert(".email", "#registerError");

	$(".login").on("click", function() {
		$(".cover").show();
		$(".popLogin").slideDown();
		$(".user").focus();

	});
	$(".cover").on("click", function() {
		if ($(".popLogin").css("display") != "none" || $(".popRegister").css("display") != "none") {
			$(".cover").hide();
			$(".popLogin").hide();
			$(".popRegister").hide();
		}

	});
	//冒泡处理函数
	stopPropagation(".popLogin");
	stopPropagation(".popRegister");
	stopPropagation(".popLisePsw");
	stopPropagation(".popLisePswNext");
	stopPropagation(".popRegisterSuc");
	stopPropagation(".popRegisterSuc");
	stopPropagation(".popFillInfo");
	stopPropagation(".popApply");

	$(".register").on("click", function() {
		$("#registerError").text("");
		$(".cover").show();
		$(".popRegister").slideDown();
	});

	//页面跳转
	
	//教师端
	$(".teacherInto").on("click", function() {
		if ($.cookie("userId")!=null && $.cookie("autoLogin")!=null) {
			window.location.href="../Task/index.html";
			console.log("当前状态为已登录");
		}else {
			console.log('当前状态为未登录');
			$(".cover").show();
			$(".popLogin").slideDown();
			$(".user").focus();
		}

	});
	//学生端
	$(".studentInto").on("click", function() {
		console.log("学生端即将发布");
	});
	
	
	//显示||隐藏密码 
	//登陆页
	//显示密码
	$(".showLoginPsw").on("click", function() {
		var num = 0;
		$(this).toggleClass("showPswc");

		$(".showPswc").each(function(i) {
			num = i + 1;
		});
		if (num == 0) {
			$(".psw").attr("type", "password");
		} else {
			$(".psw").attr("type", "text");

		}
	});
	//注册页
	$(".showPsw").on("click", function() {
		var num = 0;
		$(this).toggleClass("showPswc");

		$(".showPswc").each(function(i) {
			num = i + 1;
		});
		if (num == 0) {
			$(".setPsw").attr("type", "password");
		} else {
			$(".setPsw").attr("type", "text");

		}
	});

	//点击登录上面的 注册账号链接
	btnEventNext("#registerLink", ".popLogin", ".popRegister");
	//点击 注册上的请登录
	btnEventNext("#loginLink", ".popRegister", ".popLogin")
		//点击忘记密码
		//btnEventNext(".lisePsw",".popLogin",".popLisePsw");
		//点击忘记密码里面的下一步
	btnEventNext(".nextStep", ".popLisePsw", ".popLisePswNext");
	//点击进入邮箱
	btnEventNext(".intoEmail", ".popRegisterSuc", ".popSuccess");
	//点击完善信息
	btnEventNext(".fillInfo", ".popSuccess", ".popFillInfo");
	//申请学校
	btnEventNext(".apply", ".popFillInfo", ".popApply");

	//点击忘记密码的返回
	btnEvent(".lastStep", ".popLisePsw", ".popLogin");
	//点击忘记密码第二页 返回
	btnEvent(".liseBack", ".popLisePswNext", ".popLisePsw");
	//点击申请里的返回
	btnEvent(".backa", ".popApply", ".popFillInfo");

	//点击登录
	//	enter(".loginBtn");

	$(".loginBtn").on("click", function() {
		var userName = $(".user").val();
		var userPwd = $(".psw").val();
		if (userName == "") {
			$("#loginError").html("请输入用户名!");
			return false;
		}

		if (userPwd == "") {
			$("#loginError").html("请输入密码!");
			return false;
		}
		userLogin(userName, userPwd, classInfoAPI, storage);

	});

	//点击获取验证码
	$("#codeTime").on("click", function() {

		var phoneCode = $(".email").val();
		var regexEmail = "^[1][3578][0-9]{9}$";
		if (phoneCode == "") {
			$("#registerError").html("请输入手机号！");
			return false;
		}
		if (!(phoneCode.match(regexEmail))) {
			$("#registerError").html("请正确输入手机号！");
			return false;
		}
		time(this);
		sendMessage(phoneCode);
	});
	//验证用户名是否已经存在
	$(".setUser").on("blur", function() {
		var name = $(".setUser").val();
		if (name != "") {
			isExsit(name);

		}
	});
	//点击注册
	var phonenumber;
	$(".setAdmin").on("click", function() {
		var email = $(".email").val();
		phonenumber = email;
		var setUser = $(".setUser").val();
		var setPsw = $(".setPsw").val();
		var validateCode = $("#valiCode").val();
		var trueCode = $("#code").val();
		//		var regexEmail="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		var regexEmail = "^[1][3578][0-9]{9}$";
		var regexUser = "^([\u4e00-\u9fa5]+|[a-zA-Z0-9]+)$";

		if (!(email.match(regexEmail))) {
			$("#registerError").html("请正确输入手机号！");
			return false;
		}

		if (setUser == "") {
			$("#registerError").html("请输入用户名！");
			return false;
		}
		if (!(setPsw.match(regexUser))) {
			$("#registerError").html("请输入密码！");
			return false;
		}
		if (validateCode == "") {
			$("#registerError").html("请获取/输入手机验证码！");
			return false;
		}
		if (validateCode != trueCode) {
			$("#registerError").html("验证码输入错误！");
			return false;
		}
		$(".popRegister").slideUp();
		$(".popFillInfo").slideDown();
	});

	JudgeLogin(".errorText", "index.html");
	JudgeLogin(".classManage", "myClass.html");
	JudgeLogin(".myResource", "smallClass.html");

	getAreaSchoolInfo(0, "province", classInfoAPI, 0);

	//点击修改省市区县
	changeArea("#areaProvinceRegister", "#areaProvinceRegister option:selected", "city");
	changeArea("#areaCityRegister", "#areaCityRegister option:selected", "district");
	changeArea("#areaDistrictRegister", "#areaDistrictRegister option:selected", "school");

	//完善信息
	$("#setTeacherInfo").on("click", function() {
		var userName = $(".setUser").val();
		var userPwd = $(".setPsw").val();
		var schoolId = $("#areaSchoolRegister option:selected").attr("data-id");
		var subjectId = $("#areaSubjectRegister option:selected").attr("data-id");
		var termId = $("#areaTermRegister option:selected").attr("data-id");
		var trueName = $("#areaTrueNameRegister").val();
		if (schoolId == 0) {
			$("#infoError").text("请选择省/市/区，便于定位学校");
			return false;
		}
		//		console.log("学段"+termId,"学科"+subjectId);
		completeInfo(userName, userPwd, schoolId, subjectId, termId, trueName, phonenumber);

	});

}); //ready
function JudgeLogin(objClass, href) {
	$(objClass).on("click", function() {
		if ($.cookie("autoLogin") == "" || $.cookie("autoLogin") == null || (!storage.loginUserId)) {
			$(".cover").show();
			$(".user").focus();
			$(".popLogin").slideDown();

			return false;
		} else {
			window.location.href = href;
		}

	});
}

function mouseDownOut(objClass, addClass) {
	$(objClass).mousedown(function() {
		$(this).addClass(addClass);
	});
	$(objClass).mouseout(function() {
		$(this).removeClass(addClass)
	});

}

//返回
function btnEvent(btnClass, hidePop, showPop) {
	$(btnClass).on("click", function() {
		$(hidePop).hide();
		$(showPop).fadeIn("slow");
	});
}

//点击下一步 
function btnEventNext(btnClass, hidePop, showPop) {
	$(btnClass).on("click", function() {
		$(hidePop).hide();
		$(showPop).fadeIn("slow");
	});
}

function stopPropagation(pop) {
	$(pop).on("click", function(e) {
		$(".cover").show();
		e.stopPropagation();
	});
}

//用户登录
function userLogin(userName, userPwd, classInfoAPI, storage) {
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "userLogin",
		"contentJson": {
			"name": userName,
			"userPwd": userPwd
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		contentType: 'text/plain',

	}).done(function(data) {
		var info = data.content.result;
		var msg = data.content.message;
		var errorMessage = data.content.errorMessage;
		if (msg == "error") {
			$("#loginError").html(errorMessage);
			return false;
		}
		$("#userName").val(info.userName);
		$("#userId").val(info.userId);
		$("#trueName").val(info.trueName);
		$("#schoolId").val(info.schoolId);
		$("#schoolName").val(info.schoolName);
		$(".school").text(info.schoolName);
		$.cookie("userId", null);
		$.cookie("userName", null);
		$.cookie("autoLogin", null);
		$.cookie("userPwd", null);

		$.cookie("userId", info.userId, {
			path: "/",
			expires: 7
		});
		$.cookie("userName", userName, {
			path: "/",
			expires: 7
		});
//		var enUserPwd = $.base64.encode(userPwd);	//密码加密
		$.cookie("userPwd", userPwd, {
			path: "/",
			expires: 7
		});

		var expiresDate = new Date();
		expiresDate.setTime(expiresDate.getTime() + (12 * 60 * 60 * 1000));
		$.cookie("autoLogin", info.userId, {
			path: "/",
			expires: expiresDate
		});

		storage.loginUserId = $("#userId").val();
		storage.loginUserName = $("#userName").val();
		storage.loginTrueName = $("#trueName").val();
		storage.loginSchoolId = $("#schoolId").val();
		storage.loginSchoolName = $("#schoolName").val();
		storage.loginTermId = info.termId;
		storage.loginSubjectId = info.subjectId;
		storage.loginProvinceId = info.provinceId;
		storage.loginCityId = info.cityId;
		storage.loginDistrictId = info.districtId;
		storage.loginProvinceName = info.provinceName;
		storage.loginCityName = info.cityName;
		storage.loginDistrictName = info.districtName;
		//增加是否电子书包用户判断	true: 是 false: 否
		storage.loginIsEbagUser = info.isEbagUser;
		var path = info.imgPath;
		if (path == null || path == "") {
			path = "img/infohead.png";
		}
		storage.loginTeacherHead = path;

		$(".cover").hide();
		$(".popLogin").hide();
		$(".teacher").attr("src", storage.loginTeacherHead);
		$(".enter").show();
//		$(".login").hide();
//		$(".register").hide();
		//登陆成功之后跳转到主页
		location.href="../Task/index.html";
		storage.messageNum = info.messageNum;

		//上传头像操作 修改信息
//		uploadLogin();
//		changeLogin();

		//
		var messageNum = storage.messageNum;
		if (messageNum != "0") {
			$(".NewCur").addClass("NewCurc");
		} else {
			$(".NewCur").removeClass("NewCurc");
		}

	});
} //userLogin

//获取验证码函数
function sendMessage(phoneCode) {
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "sendMessage",
		"contentJson": {
			"phoneCode": phoneCode
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		contentType: 'text/plain',

	}).done(function(data) {
		var code = data.content.result;
		$("#code").val(code);
	});
}

var wait = 60;

function time(o) {
	if (wait == 0) {
		o.removeAttribute("disabled");
		o.value = "获取验证码";
		wait = 60;
	} else {
		o.setAttribute("disabled", true);
		o.value = "重新发送(" + wait + ")";
		wait--;
		setTimeout(function() {
				time(o)
			},
			1000)
	}
}

//判断用户名是否已经存在
function isExsit(name) {
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "isExsit",
		"contentJson": {
			"name": name
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		contentType: 'text/plain',

	}).done(function(data) {
		var info = data.content.result.isExist;
		if (info != 0) {
			$("#registerError").text("用户名已存在");
			return false;
		} else {
			$("#registerError").text("");
		}

	});

}
//完善信息
function completeInfo(userName, userPwd, schoolId, subjectId, termId, trueName, phoneNumber) {

	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "completeInfo",
		"contentJson": {
			"userName": userName,
			"userPwd": userPwd,
			"schoolId": schoolId,
			"subjectId": subjectId,
			"termId": termId,
			"trueName": trueName,
			"phoneNumber": phoneNumber
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		contentType: 'text/plain',

	}).done(function(data) {
		$(".popFillInfo").slideUp();
		$(".popSuccess").slideDown(100).delay(1000).slideUp(10);
		$(".cover").delay(1000).hide(100);
		//成功以后清除填充
		$(".email").val("");
		$(".setUser").val("");
		$(".setPsw").val("");
		$("#valiCode").val("");
		$("#areaCityRegister").val("");
	});

}

btnN = "";

function enter(btn) {
	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == btn) {
				$(btn).click();
				btn = "";
			}
		}
	});
}

function enterSure() {

	$(".manInfo").on("click", function() {
		$(".action1").text("");
		btnN = "#infoSure";
	});
	$(".changePwd").on("click", function() {
		//再次点击修改密码的时候  原始信息清空
		$("#prePwd").val("");
		$("#newPwd").val("");
		$("#newPwds").val("");
		$(".action2").text("");
		btnN = "#pwdSure";
	});
	$(".login").on("click", function() {
		$("#loginError").text("");
		btnN = ".loginBtn";
	});
	$(".smallClass").on("click", function() {
		$("#loginError").text("");
		btnN = ".loginBtn";
	});
	$(".classManage").on("click", function() {
		$("#loginError").text("");
		btnN = ".loginBtn";
	});
	$(".errorText").on("click", function() {
		$("#loginError").text("");
		btnN = ".loginBtn";
	});

	$(".setAdmin").on("click", function() {
		$("#infoError").text("");
		btnN = "#setTeacherInfo";
	});

}

function changeArea(parent, selected, typeFlag) {
	var areaId = $(selected).attr("data-id");
	$(parent).change(function() {
		$("#infoError").text("");
		areaId = $(selected).attr("data-id");
		if (areaId == 0) {
			return false;
		}
		getAreaSchoolInfo(areaId, typeFlag, 1);
	});
}

function getAreaSchoolInfo(areaId, typeFlag, school) {
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "getSchoolInfo",
		"contentJson": {
			"areaId": areaId,
			"typeFlag": typeFlag
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		success: function(data) {

				var area_info = data.content.result.areaList;
				var school_info = data.content.result.schoolList;
				fillAreaList(area_info, typeFlag);
				if (school == 1) {
					fillSchoolList(school_info);
				}
			} //success

	}); //ajax
}

function fillAreaList(info, typeFlag) {

	var text = "";
	if (typeFlag == "province") {
		text = "<option data-id='0'>省/直辖市</option>";
	} else if (typeFlag == "city") {
		text = "<option data-id='0'>市</option>";
	} else if (typeFlag == "district") {
		text = "<option data-id='0'>区/县</option>";
	}

	for (var i = 0; i < info.length; i++) {
		text += "<option data-id='" + info[i].areaId + "'>" + info[i].areaName + "</option>";
	}
	if (typeFlag == "province") {
		$("#areaProvinceRegister").html(text);
	} else if (typeFlag == "city") {
		$("#areaCityRegister").html(text);
	} else if (typeFlag == "district") {
		$("#areaDistrictRegister").html(text);
	}
}

function fillSchoolList(info) {
	var text_school = "<option data-id='0'>请选择学校</option>";
	for (var i = 0; i < info.length; i++) {
		text_school += "<option data-Id='" + info[i].schoolId + "'>" + info[i].schoolName + "</option>"
	}
	$("#areaSchoolRegister").html(text_school);

}

function clearAlert(obj, objAlert) {
	$(obj).focus(function() {
		$(objAlert).text("");
	});
}