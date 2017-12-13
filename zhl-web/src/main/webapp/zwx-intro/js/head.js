$(function() {
	
	storage = window.localStorage;
	var loginUserId = storage.loginUserId;
	var loginSchoolId = storage.loginSchoolId;
	var loginSchoolName = storage.loginSchoolName;
	var loginUserName = storage.loginUserName;
	var loginTrueName = storage.loginTrueName;
	var loginUserHead = storage.loginTeacherHead;
	$(".teacher").attr("src", storage.loginTeacherHead);
	$(".school").text(storage.loginSchoolName);
//	if($.cookie("autoLogin")==null || $.cookie("autoLogin")=="" || (!storage.loginUserName))
//	{//一天之后数据全部清除  需要重新登陆
//		storage.clear();
//		window.location.href = "login.html";
//	}

	//绑定enter事件
	enterSure();
	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == "#infoSure") {
				if($(".popManInfo").css("display")!="none")
				{
					$("#infoSure").click();
				}else
				{
					btnN="";
				}
				
			}
		}
	});
	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == "#pwdSure") {
				if($(".popChangePwd").css("display")!="none")
				{
					$("#pwdSure").click();
				}else
				{
					btnN="";
				}
			}
		}
	});
	
	

	$(".personalInfo").hover(function() {
		$(".personalInfo div").stop(true, false).slideDown(150);
	}, function() {
		$(".personalInfo div").stop(true, false).slideUp(150);
	});

	$(".personalInfo span").mousedown(function() {
		$(".personalInfo span").removeClass("rabg");
		$(this).addClass("rabg");
	});
	$(".personalInfo span").mouseout(function() {
		$(".personalInfo span").removeClass("rabg");
	});

	$(".MyAll,.tClass").hover(function() {
		$(".MyAll .tClass").stop(true, false).slideDown(150);
	}, function() {
		$(".MyAll .tClass").stop(true, false).slideUp(150);
	});

	$(".sourceBox,.source").hover(function() {
		$(".sourceBox .source").stop(true, false).slideDown(150);
	}, function() {
		$(".sourceBox .source").stop(true, false).slideUp(150);
	});
	
	

//		$(".cover1").height(3000);

	
	
	
	mouseDownOut(".ibtn", "url(img/popup.png)", "url(img/popupc.png)");

	//点击个人信息  修改密码  退出
	teacherHover(".manInfo", ".popManInfo");
	teacherHover(".changePwd", ".popChangePwd");



	//取消按钮
	coverPop("#infoCancel", ".popManInfo", ".cover1");
	coverPop("#pwdCancel", ".popChangePwd", ".cover1");


	//点击个人信息 显示 地区 学校
	//	$(".manInfo").on("click",function(){
	//areadId 默认为0 typeFlag="province"
	//		getAreaSchoolInfo(0,"province",classInfoAPI,0);
	var loginTermId = storage.loginTermId;
	var loginSubjectId = storage.loginSubjectId;
	var areaTerm = $("#areaTerm option").each(function(i) {
		if ($(this).attr("data-id") == loginTermId) {
			$(this).attr("selected", "selected");
		}
	});
	var areaSubject = $("#areaSubject option").each(function(i) {
		if ($(this).attr("data-id") == loginSubjectId) {
			$(this).attr("selected", "selected");
		}
	});
	

	$("#areaSchool").html("<option data-id='" + loginSchoolId + "'>" + loginSchoolName + "</option>");
	$("#areaProvince").html("<option data-id='" + storage.loginProvinceId + "'>" + storage.loginProvinceName + "</option>");
	$("#areaCity").html("<option data-id='" + storage.loginCityId + "'>" + storage.loginCityName + "</option>");
	$("#areaDistrict").html("<option data-id='" + storage.loginDistrictId + "'>" + storage.loginDistrictName + "</option>");
	$("#infoUserName").val(loginUserName);
	$("#infoRealName").val(loginTrueName);
	$("#infoTechHead").attr("src", loginUserHead);
	//	});

	//个人信息点击确定
	$("#infoSure").on("click", function(e) {
		var name = $("#infoUserName").val();
		var realName = $("#infoRealName").val();
		if (name == "") {
			$(".action1").text("请输入用户名！");
			return false;
		}
		if (realName == "") {
			$(".action1").text("请输入真实姓名！");
			return false;
		}
		e.stopPropagation();
		var userName = $("#infoUserName").val();
		var trueName = $("#infoRealName").val();
		var schoolId = $("#areaSchool option:selected").attr("data-id");
		var schoolName = $("#areaSchool option:selected").text();
		var termId = $("#areaTerm option:selected").attr("data-id");
		var subjectId = $("#areaSubject option:selected").attr("data-id");
		var headImg = "";
		if (storage.headImgPath != null && storage.headImgPath != "") {
			headImg = storage.headImgPath;
		}
		editUserInfo(loginUserId, userName, trueName, schoolId, termId, subjectId, classInfoAPI, schoolName, headImg);
	});

	//点击修改省市区县
	//	changeArea("#areaProvince","#areaProvince option:selected","city",classInfoAPI);
	//	changeArea("#areaCity","#areaCity option:selected","district",classInfoAPI);
	//	changeArea("#areaDistrict","#areaDistrict option:selected","school",classInfoAPI);

	//处于无法选择状态
	$("#areaProvince").attr("disabled", "disabled");
	$("#areaCity").attr("disabled", "disabled");
	$("#areaDistrict").attr("disabled", "disabled");
	$("#areaSchool").attr("disabled", "disabled");
	$("#areaTerm").attr("disabled", "disabled");
	$("#areaSubject").attr("disabled", "disabled");
	//修改密码点击确定发送ajax
	changePwdApi(loginUserId, classInfoAPI);


	//点击退出
	$(".exit").on("click", function() {
		top.location = "login.html";
		$.cookie("userId", null, {
			path: '/'
		});
		$.cookie("autoLogin", null, {
			path: '/'
		});
		storage.clear();

	});

	//上传头像相关函数

	uploadImg();
}); //ready
//未选择情况下的提示框
function popChange(text) {
	$(".popChange .tit2").text(text);
	$(".popChange").show(1).delay(1200).hide(1);
}

function coverPop(cancelId, pop, cover) {
	$(cancelId).on("click", function(e) {
		$(cover).hide();
		$(pop).slideUp(300);
		e.stopPropagation();
	});
	$(pop).on("click", function(e) {
		$(cover).show();
		e.stopPropagation();
	});
}

//鼠标点击事件修改颜色mousedown mouseout
function mouseDownOut(objclass, bgcur, bgnew) {
	$(objclass).mousedown(function() {
		$(this).css("background", bgnew);
	});
	$(objclass).mouseout(function() {
		$(this).css("background", bgcur);
	});
}

//点击修改密码  个人信息   退出
function teacherHover(objClass, pop) {
	$(objClass).on("click", function() {
		$(".cover1").show();
		$(pop).slideDown();
	});
}


function changePwdApi(loginUserId, classInfoAPI) {
	$("#pwdSure").on("click", function(e) {
		var prePwd = $("#prePwd").val();
		var newPwd = $("#newPwd").val();
		var newPwds = $("#newPwds").val();

		if (prePwd == "") {
			$(".action2").text("请输入旧密码！");
			return false;
		}
		if (newPwd == "") {
			$(".action2").text("请输入新密码！");
			return false;
		}
		if (newPwds == "") {
			$(".action2").text("请确认新密码！");
			return false;
		}
		if (newPwd != newPwds) {
			$(".action2").text("新密码与确认密码不统一！");
			return false;
		}

		e.stopPropagation();
		changePwd(loginUserId, newPwd, prePwd, classInfoAPI);
	});

} //changePwdApi
function changePwd(userId, newPwd, prePwd, classInfoAPI) {
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "changePwd",
		"contentJson": {
			"userId": userId,
			"userPwd": newPwd,
			"oldPwd": prePwd
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		success: function(data) {
				var msg = data.content.message;
				var errorMessage = data.content.errorMessage;
				if (msg == "error") {
					$(".action2").text(errorMessage);
					return false;
				}
				$(".cover1").hide();
				$(".popChangePwd").slideUp(500);
				popChange("密码修改成功!");
				$.cookie("userPwd", null, {
					path: "/",
					expires: 7
				});

			} //success

	}); //ajax
}
//function changeArea(parent,selected,typeFlag,classInfoAPI)
//	{
//	   var areaId=$(selected).attr("data-id");
//		$(parent).change(function(){
//			areaId=$(selected).attr("data-id");
//			if(areaId==0)
//			{
//				return false;
//			}
//			getAreaSchoolInfo(areaId,typeFlag,classInfoAPI,1);
//		});
//	}

//function getAreaSchoolInfo(areaId,typeFlag,classInfoAPI,school)
//{
//	//var school=0;
//	var Json = {
//		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
//		"method": "getSchoolInfo",
//		"contentJson": {
//			"areaId":areaId,
//			"typeFlag":typeFlag
//		}
//	};
//	var getUrl = classInfoAPI + JSON.stringify(Json);
//	$.ajax({
//		type: "post",
//		url: getUrl,
//		dataType: "json",
//		success: function(data) {
//          
//		    var area_info=data.content.result.areaList;
//		    var school_info=data.content.result.schoolList;
//		    fillAreaList(area_info,typeFlag);
//		    if(school==1)
//		    {
//		    	fillSchoolList(school_info);
//		    }
//		 
//        
//		}//success
//		
//	});//ajax
//}
//function fillAreaList(info,typeFlag)
//{
//	
//	var text="";
//	if(typeFlag=="province")
//	{
//		text="<option data-id='0'>省</option>";
//	}
//	else if(typeFlag=="city")
//	{
//		text="<option data-id='0'>市</option>";
//	}
//	else if(typeFlag=="district")
//	{
//			text="<option data-id='0'>区/县</option>";
//	}
//	
//	
//	for(var i=0;i<info.length;i++)
//	{	
//	  text+="<option data-id='"+info[i].areaId+"'>"+info[i].areaName+"</option>";	
//	}
//	if(typeFlag=="province")
//	{
//		$("#areaProvince").html(text);
//	}
//	else if(typeFlag=="city")
//	{
//		$("#areaCity").html(text);
//	}
//	else if(typeFlag=="district")
//	{
//			$("#areaDistrict").html(text);
//	}
//}

//fillAreaList
function fillSchoolList(info) {
	var text_school = "";
	for (var i = 0; i < info.length; i++) {
		text_school += "<option data-Id='" + info[i].schoolId + "'>" + info[i].schoolName + "</option>"
	}
	$("#areaSchool").html(text_school);

}

function editUserInfo(userId, userName, trueName, schoolId, termId, subjectId, classInfoAPI, schoolName, headImg) {
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "editUserInfo",
		"contentJson": {
			"userId": userId,
			"userName": userName,
			"trueName": trueName,
			"schoolId": schoolId,
			"termId": termId,
			"subjectId": subjectId,
			"headImgPath": headImg
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		success: function(data) {
				//个人信息填写成功后 隐藏该窗口
				var message = data.content.message;
				var errorMessage = data.content.errorMessage;
				if (message == "error") {
					alert(errorMessage);
					return false;
				}

				$(".cover1").hide();
				$(".popManInfo").hide(500);
				popChange("信息修改成功！");
				$.cookie("userName", null, {
					path: "/",
					expires: 7
				});
				storage.loginTrueName = trueName;
				storage.loginUserName = userName;
				storage.loginSchoolId = schoolId;
				storage.loginSchoolName = schoolName;
				storage.loginTermId = termId;
				storage.loginSubjectId = subjectId;
				var img = $("#infoTechHead").attr("src");
				storage.loginTeacherHead = img;
				//此处还要添加头像修改
				//由于页面刷新 不做本地修改  也是可以得
				//$(".teacher").text("欢迎您！"+trueName+"老师");
				$(".school").text(schoolName);
				window.location.reload();
			} //success

	}); //ajax
}

btnN = "";

function enter(btn) {
	$(document).keydown(function(e) {
		var oEven = e || event;
		if (oEven.keyCode == 13) {
			if (btnN == btn) {
				$(btn).click();
				btnN = "";
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
	$(".addClass").on("click", function() {
		btnN = "#addSure";
		enter("#addSure");
	});
	$(".deleteClass").on("click", function() {
		btnN = "#deleteSure";
		enter("#deleteSure");
	});
	$("#removeStu").on("click", function() {
		btnN = "#removeStuSure";
		enter("#removeStuSure");
	});
	$("#removeTeach").on("click", function() {
		btnN = "#removeTeachSure";
		enter("#removeTeachSure");
	});
	$("#passStu").on("click", function() {
		btnN = "#passStuSure";
		enter("#passStuSure");
	});
	$("#rejustStu").on("click", function() {
		btnN = "#rejustStuSure";
		enter("#rejustStuSure");
	});
	$("#passTeach").on("click", function() {
		btnN = "#passTeachSure";
		enter("#passTeachSure");
	});
	$("#rejustTeach").on("click", function() {
		btnN = "#rejustTeachSure";
		enter("#rejustTeachSure");
	});
	$("#power").on("click", function() {
		btnN = "#powerSure";
		enter("#powerSure");
	});
	$(".applyToClass").on("click", function() {
		btnN = "#applySure";
		enter("#applySure");
	});
	$(".setNemClass").on("click", function() {
		btnN = "#newSure";
		enter("#newSure");
	});
	$(".deleteClassa").on("click", function() {
		btnN = "#breakSure";
		enter("#breakSure");
	});

}

//上传头像函数
function uploadImg() {
	$(".headTitle span").on("click", function() {
		$("#photo").attr("src", "");
		btnN = "#infoSure";
		$(".popChangeHead").hide();
		ias.cancelSelection(); //取消默认裁剪区域 如果鼠标动了区域  则不属于默认区域了
		clearPosition();
	});
	$("#changeCancel").on("click", function() {
		//		$(".frame").hide();
		btnN = "#infoSure";
		$("#photo").attr("src", "");
		$(".popChangeHead").hide();
		ias.cancelSelection(); //取消默认裁剪区域
		//清除裁剪区域的坐标
		clearPosition();

	});
}

function clearPosition() {
	$("#startY").val("");
	$("#startX").val("");
	$("#width").val("");
	$("#height").val("");
}