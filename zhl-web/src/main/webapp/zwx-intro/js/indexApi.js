$(function() {
	var time = new Date();
	var mouth = time.getMonth() + 1;
	$("#data_box1").val(time.getFullYear() + "-" + mouth + "-" + time.getDate());
	storage = window.localStorage;
	var schoolId = storage.loginSchoolId;
	var userId = storage.loginUserId;
	var schoolId = storage.loginSchoolId;
	var userName = storage.loginUserName;
	var classJson = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "getTeachGrade",
		"contentJson": {
			"userId": userId,
			"schoolId": schoolId
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(classJson);

	var infos = [];

	$.ajax({
		type: "POST",
		dataType: "json",
		url: getUrl,
		success: function(data) {
			infos = data.content.result;
			var message = data.content.message;
			var errorMessage = data.content.errorMessage;
			if(message == "error") {
				alert(errorMessage);
				return false;
			}
			var text = "";
			//填充班级信息
			if(infos.length == 0) {
				$(".noInfo").text("您还没有班级哦，去班级管理模块添加班级/新建班级哦！");
				$(".noInfo").show();
			} else {
				$(".noInfo").hide();
			}
			for(var i = 0; i < infos.length; i++) {
				text += "<option id='class" + infos[i].classId + "' subjectId='subject" + infos[i].subjectId + "' termId='term" + infos[i].termId + "' manager='" + infos[i].manager + "'>" + infos[i].className + "</option>";
			}
			$(".select_class").html(text);
			if(infos.length != 0) {
				$("#classIdHidden").val($(".select_class").find("option:selected").attr("id").substring(5));
				$("#subjectIdHidden").val($(".select_class").find("option:selected").attr("subjectId").substring(7));
				$("#termIdHidden").val($(".select_class").find("option:selected").attr("termId").substring(4));
				$("#managerHidden").val($(".select_class").find("option:selected").attr("manager"));
				//判断是否是班主任
				if($("#managerHidden").val() == storage.loginUserName) {
					$(".charge").hide();
					$(".charge_term").empty(); // 内容清空
					for(var i = 0; i < infos.length; i++) {
						if(infos[i].classId == $("#classIdHidden").val()) {
							if(infos[i].subjectIds.length > 0) {
								for(var j = 0; j < infos[i].subjectIds.length; j++) {
									$(".charge_term").append("<option data-id='" + infos[i].subjectIds[j].subjectId + "'>" + infos[i].subjectIds[j].subjectName + "</option>");
								}
							}
						}
					}

					$(".charge_term option").each(function(i) {
						if($(this).attr("data-id") == $("#subjectIdHidden").val()) {
							$(this).attr("selected", "selected");
						}
					});
					$(".charge").show();
				} else {
					$(".charge").hide();
					$(".charge_term").empty(); // 内容清空
					for(var i = 0; i < infos.length; i++) {
						if(infos[i].classId == $("#classIdHidden").val()) {
							$(".charge_term").append("<option data-id='" + infos[i].subjectId + "'>" + infos[i].subjectName + "</option>");
						}
					}

					$(".charge_term option").each(function(i) {
						if($(this).attr("data-id") == $("#subjectIdHidden").val()) {
							$(this).attr("selected", "selected");
						}
					});
					$(".charge").show();
				}
				var classId = $("#classIdHidden").val();
				var subjectId = $("#subjectIdHidden").val();
				var termId = $("#termIdHidden").val();
				var timeType = $(".green").attr("id");
				fillHtml(classId, subjectId, termId, timeType);
				$(".queue").on("click", function() {
					var classId = $("#classIdHidden").val();
					var subjectId = $("#subjectIdHidden").val();
					var termId = $("#termIdHidden").val();
					$(".queue").removeClass("green");
					$(this).addClass("green");
					var timeType = $(this).attr("id");
					fillHtml(classId, subjectId, termId, timeType);
				});

			}

		}
	});

	//当选择班级以后
	$(".select_class").change(function() {
		//传给后台的四个参数
		$(".vname1").text("教材版本");
		$(".direct").css("visibility", "hidden");
		$(".oclick2").css("visibility", "hidden");
		$(".oclick3").css("visibility", "hidden");
		$("#lastCode").val("");
		$("#errorTypeCode").val("");
		$("#versionCode").val("");
		$("#courseCode").val("");

		$("#classIdHidden").val($(this).find("option:selected").attr("id").substring(5));
		$("#subjectIdHidden").val($(this).find("option:selected").attr("subjectId").substring(7));
		$("#termIdHidden").val($(this).find("option:selected").attr("termId").substring(4));
		$("#managerHidden").val($(this).find("option:selected").attr("manager"));
		//if ($("#managerHidden").val() == storage.loginUserName) {
		//	$(".charge").show();
		//	$(".charge_term option").each(function() {
		//		if ($(this).attr("data-id") == $("#subjectIdHidden").val()) {
		//			$(this).attr("selected", "selected");
		//		}
		//	});
		//} else {
		//	$(".charge").hide();
		//}
		//判断是否是班主任
		if($("#managerHidden").val() == storage.loginUserName) {
			$(".charge").hide();
			$(".charge_term").empty(); // 内容清空
			for(var i = 0; i < infos.length; i++) {
				if(infos[i].classId == $("#classIdHidden").val()) {
					if(infos[i].subjectIds.length > 0) {
						for(var j = 0; j < infos[i].subjectIds.length; j++) {
							$(".charge_term").append("<option data-id='" + infos[i].subjectIds[j].subjectId + "'>" + infos[i].subjectIds[j].subjectName + "</option>");
						}
					}
				}
			}

			$(".charge_term option").each(function(i) {
				if($(this).attr("data-id") == $("#subjectIdHidden").val()) {
					$(this).attr("selected", "selected");
				}
			});
			$(".charge").show();
		} else {
			$(".charge").hide();
			$(".charge_term").empty(); // 内容清空
			for(var i = 0; i < infos.length; i++) {
				if(infos[i].classId == $("#classIdHidden").val()) {
					$(".charge_term").append("<option data-id='" + infos[i].subjectId + "'>" + infos[i].subjectName + "</option>");
				}
			}

			$(".charge_term option").each(function(i) {
				if($(this).attr("data-id") == $("#subjectIdHidden").val()) {
					$(this).attr("selected", "selected");
				}
			});
			$(".charge").show();
		}
		var classId = $("#classIdHidden").val(); //  id=class2   ====>  2
		var subjectId = $("#subjectIdHidden").val(); //   =====> subjectId
		var termId = $("#termIdHidden").val();
		var timeType = $(".green").attr("id");
		fillHtml(classId, subjectId, termId, timeType); //填充版本，教材，学生信息

	}); //change

	function fillHtml(classId, subjectId, termId, timeType) {
		//		//教材版本信息
		//		var tfCode = $("#errorTypeCode").val();
		//		var courseType = 0;
		//		//如果已经选择过了  则不需要再次填充  因为tfcode也许会和Type不一致
		//		if (tfCode.length != 0) {
		//			courseType = 3
		//		} else {
		//			courseType = 0
		//		}
		//		var userId=$("#lastUserId").val();
		//		getCourse(tfCode, termId, subjectId, courseType,userId);
		var classChangeJson = {
			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
			"method": "classChanged",
			"contentJson": {
				"subjectId": subjectId,
				"termId": termId,
				"classId": classId,
				"timeType": timeType
			}
		};
		var getClassUrl = classInfoAPI + JSON.stringify(classChangeJson);

		$.ajax({
			type: "post",
			url: getClassUrl,
			dataType: "json",
			success: function(data) {
					var infos = data.content.result.stuList;
					var message = data.content.message;
					var errorMessage = data.content.errorMessage;
					if(message == "error") {
						alert(errorMessage);
						return false;
					}
					changeStu(infos, timeType);
					showStuName(infos); //侧栏学生姓名

					$("#stuInfo li").on("click", function() { //进入main2页 传递错误类型 书本列表
						<!--因为学生错题数原因  每次需要重新选择目录--->
						//传给后台的四个参数
						$(".vname1").text("教材版本");
						$(".direct").css("visibility", "hidden");
						$(".oclick2").css("visibility", "hidden");
						$(".oclick3").css("visibility", "hidden");
						$("#lastCode").val("");
						$("#errorTypeCode").val("");
						$("#versionCode").val("");
						$("#courseCode").val("");

						$("#errorId").val("0");
						$("#noList").hide();
						$("#error_list").html(""); //防止页面加载过慢  显示上一个学生的错题
						$("#stuIndex").val($("#stuInfo li").index($(this)));
						stuName(); //侧栏学生下拉点击事件
						var userId = $(this).find("p").attr("data-id").substring(4);
						$("#lastUserId").val(userId);
						$("#stuInfo li").removeClass("cur");
						$(this).addClass("cur");
						var tfCode = $("#errorTypeCode").val();
						var subjectId = $("#subjectIdHidden").val();
						var termId = $("#termIdHidden").val();
						var timeStart = $("#data_box").val();
						var timeEnd = $("#data_box1").val();
						errorTypes(userId, subjectId, termId); //错误类型	

						<!----根据学生获取目录--->
						//教材版本信息
						var courseType = 0;

						getCourse(tfCode, termId, subjectId, courseType, userId);
						//						console.log(userId)
						<!----根据学生获取目录--->

						stuErrorList(userId, tfCode, subjectId, termId, 0, timeStart, timeEnd); //默认全部（学生错题列表）
						storage.userName = $(".cur .stuN").text();
						storage.userImgPath = $(this).attr("data-path");
						storage.className = $(".select_class option:selected").text();
						$("#stu_name li").each(function() {
							if($(this).find("span").text() == storage.userName) {
								$("#stu_name li").removeClass("blue");
								$(this).addClass("blue");
							}
						});

					});

					//点击侧栏切换学生错题
					$("#stu_name li").on("click", function() {
						<!-----因为学生错题数目原因 需要将目录初始化----->
						$(".vname1").text("教材版本");
						$(".direct").css("visibility", "hidden");
						$(".oclick2").css("visibility", "hidden");
						$(".oclick3").css("visibility", "hidden");
						$("#lastCode").val("");
						$("#errorTypeCode").val("");
						$("#versionCode").val("");
						$("#courseCode").val("");

						$("#errorId").val("0");
						$("#error_list").html(""); //防止页面加载过慢  显示上一个学生的错题
						$("#noList").hide();
						$("#stu_name li").removeClass("blue");
						$(this).addClass("blue");
						var userId = $(this).attr("data-id").substring(4);
						$("#lastUserId").val(userId);
						var tfCode = $("#errorTypeCode").val();
						var timeStart = $("#data_box").val();
						var timeEnd = $("#data_box1").val();
						var subjectId = $("#subjectIdHidden").val();
						var termId = $("#termIdHidden").val();

						<!----根据学生获取目录--->
						//教材版本信息
						var courseType = 0;

						getCourse(tfCode, termId, subjectId, courseType, userId);
						console.log(userId)
							<!----根据学生获取目录--->

						errorTypes(userId, subjectId, termId); //错误类型	
						stuErrorList(userId, tfCode, subjectId, termId, 0, timeStart, timeEnd);
						storage.userName = $(this).text();
						storage.userImgPath = $(this).find("img").attr("src");
					});

					//					stuName(); //侧栏学生下拉点击事件
				} //success
		}); //ajax

	} //fillHtml函数

	$(".beginTime,.endTime").on("blur", function() {
		var userId = $("#lastUserId").val();
		var tfCode = $("#errorTypeCode").val();
		var errorId = $("#errorId").val();
		var timeStart = $("#data_box").val();
		var timeEnd = $("#data_box1").val();
		var subjectId = $("#subjectIdHidden").val();
		var termId = $("#termIdHidden").val();
		if(timeStart == "" || timeEnd == "") {
			popChange("请选择时间！");
			return false;
		}

		if(new Date(timeStart) > new Date(timeEnd)) {

			alert("开始时间大于结束时间，请重新输入！");
			return false;
		}

		stuErrorList(userId, tfCode, subjectId, termId, errorId, timeStart, timeEnd); //默认全部
	});

	function fillTreeHtml(result) {
		var _result = eval(result);
		var jsonDataTree = transData(_result, 'couId', 'couPId', 'children');
		var setting = {
			treeId: "tree",
			treeObj: "treeobj",
			data: {
				key: {
					name: "couName"
				}
			},
			callback: {
				onClick: clickNode
			},
			view: {
				selectedMulti: false
			}
		};
		$.fn.zTree.init($("#treeDemo"), setting, jsonDataTree);
		courseMain2();
	}

	function clickNode() {
		var ztreeobj = $.fn.zTree.getZTreeObj("treeDemo");
		var selNode = ztreeobj.getSelectedNodes();
		var obj = selNode[0];
		//		if (!(obj.isParent)) {
		//修改为父级也可以点击
		var name = obj.couName;
		var tfCode = obj.tfCode;
		$("#lastCode").val(tfCode);
		$("#errorTypeCode").val(tfCode);
		var userId = $("#lastUserId").val();
		var subjectId = $("#subjectIdHidden").val();
		var termId = $("#termIdHidden").val();
		var errorId = $("#errorId").val();
		var timeStart = $("#data_box").val();
		var timeEnd = $("#data_box1").val();
		var type = 3;
		getCourse(tfCode, termId, subjectId, type, userId);
		stuErrorList(userId, tfCode, subjectId, termId, errorId, timeStart, timeEnd);
		$(".oclick3 .btn .vname3").text(name);
		$(".ul3").hide();

		//		}
	}

	function transData(a, idStr, pidStr, chindrenStr) {
		var r = [],
			hash = {},
			id = idStr,
			pid = pidStr,
			children = chindrenStr,
			i = 0,
			j = 0,
			len = a.length;
		for(; i < len; i++) {
			hash[a[i][id]] = a[i];
		}
		for(; j < len; j++) {
			var aVal = a[j],
				hashVP = hash[aVal[pid]];
			if(hashVP) {
				!hashVP[children] && (hashVP[children] = []);
				hashVP[children].push(aVal);
			} else {
				r.push(aVal);
			}
		}
		return r;
	}

	//点击学生跳转到main2页面 ajax请求
	function errorTypes(userId, subjectId, termId, m) {
		var errorTypeJson = {
			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
			"method": "getErrorType",
			"contentJson": {
				"userId": userId,
				"subjectId": subjectId,
				"termId": termId
			}
		};
		var getErrorUrl = classInfoAPI + JSON.stringify(errorTypeJson);
		$.ajax({
			type: "post",
			url: getErrorUrl,
			dataType: "json",
			success: function(data) {
					var _info = data.content.result;
					var message = data.content.message;
					var errorMessage = data.content.errorMessage;
					if(message == "error") {
						alert(errorMessage);
						return false;
					}
					changeErrorType(_info);
					$(".ul4 li").on("click", function() {
						//当改变错误类型
						var userId = $("#lastUserId").val();
						var errorId = $(this).attr("data-id").substring(5);
						$("#errorId").val(errorId);
						var tfCode = $("#errorTypeCode").val();
						var timeStart = $("#data_box").val();
						var timeEnd = $("#data_box1").val();
						var subjectId = $("#subjectIdHidden").val();
						var termId = $("#termIdHidden").val();
						stuErrorList(userId, tfCode, subjectId, termId, errorId, timeStart, timeEnd);
					});

				} //success
		}); //ajax

	} //errorTypes

	//获取单个学生错题列表
	function stuErrorList(userId, tfCode, subjectId, termId, errorId, timeStart, timeEnd) { //错误信息列表

		var errorListJson = {
			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
			"method": "getWrongByUserId",
			"contentJson": {
				"userId": userId,
				"tfCode": tfCode,
				"errorId": errorId,
				"timeStart": timeStart,
				"timeEnd": timeEnd,
				"subjectId": subjectId,
				"termId": termId
			}
		};
		var getErrorListUrl = classInfoAPI + JSON.stringify(errorListJson);
		$.ajax({
			type: "post",
			url: getErrorListUrl,
			dataType: "json",
			success: function(data) {
					var _info_wrong = data.content.result;
					var message = data.content.message;
					var errorMessage = data.content.errorMessage;
					if(message == "error") {
						alert(errorMessage);
						return false;
					}
					storage.errorList = JSON.stringify(_info_wrong);
					$("#error_num").html(_info_wrong.length);

					/**-----------初始化分页------------*/
					//fillPage2(_info_wrong);
					changeErrorList(_info_wrong);
					$('.imgBg').jqthumb({
						width: 175,
						height: 125,
						after: function(imgObj) {
							imgObj.css('opacity', 0).animate({
								opacity: 1
							}, 10);
							$(".jqthumb").css({
								"margin-top": "5px",
								"margin-left": "5px",
								"background": "#e6e6e6"
							});
						}
					});

					$("#error_list li").on("click", function() { //点击错题  跳转到错题详情页面
						storage.index = $("#error_list li").index(this);
						storage.questionId = $(this).attr("data-id");
					});

				} //success
		}); //ajax
	} //stuErrorList

	function getCourse(tfCode, termId, subjectId, type, userId) {
		var Json = {
			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
			"method": "getCourse",
			"contentJson": {
				"tfCode": tfCode,
				"subjectId": subjectId,
				"termId": termId,
				"userId": userId
			}
		};
		var getUrl = classInfoAPI + JSON.stringify(Json);
		$.ajax({
			type: "post",
			url: getUrl,
			dataType: "json",
			success: function(data) {
					var _info_course = data.content.result;
					if(type == 0) { //填充版本ul
						changeVersion(_info_course);
					} else if(type == 1) { //填充教材ul
						changeCourse(_info_course);
					} else if(type == 2) { //填充ztree
						fillTreeHtml(_info_course);
					}

				} //success
		}); //ajax
	}

	$(document).on("click", ".ul1 li", function() {
		$(".oclick3").css("visibility", "hidden");
		$(".direct2").css("visibility", "hidden");
		$(".vname2").text("课本");

		$(".oclick1 .btn .vname1").text($(this).text());
		$(".ul1").hide();

		var tfCode = $(this).attr("data-code").substring(4);
		$("#errorTypeCode").val(tfCode);
		if(tfCode != 0) {
			$("#lastCode").val(tfCode);
			$("#courseCode").val(tfCode);
			$(".direct1").css("visibility", "visible");
			$(".oclick2").css("visibility", "visible");
		} else {
			tfCode = "";
			$("#errorTypeCode").val(tfCode);
			$("#courseCode").val("");
			$(".oclick2").css("visibility", "hidden");
			$(".direct1").css("visibility", "hidden");
		}

		var userId = $("#lastUserId").val();
		var subjectId = $("#subjectIdHidden").val();
		var termId = $("#termIdHidden").val();
		var errorId = $("#errorId").val();
		var timeStart = $("#data_box").val();
		var timeEnd = $("#data_box1").val();
		var type = 1;
		getCourse(tfCode, termId, subjectId, type, userId)
		stuErrorList(userId, tfCode, subjectId, termId, errorId, timeStart, timeEnd);
	});

	//课本
	$(document).on("click", ".ul2 li", function() {
		var tfCode = $(this).attr("data-code").substring(4);
		$("#errorTypeCode").val(tfCode);
		$(".oclick2 .btn .vname2").text($(this).text());
		$(".ul2").hide();
		if(tfCode != 0) {
			$("#lastCode").val(tfCode);
			$("#versionCode").val(tfCode);
			$(".direct2").css("visibility", "visible");
			$(".oclick3").css("visibility", "visible");
			tfCode = $("#lastCode").val();
			$("#errorTypeCode").val(tfCode);

		} else {
			$("#versionCode").val($("#courseCode").val());
			$(".direct2").css("visibility", "hidden");
			$(".oclick3").css("visibility", "hidden");
			tfCode = $("#versionCode").val();
			$("#errorTypeCode").val(tfCode);

		}
		var userId = $("#lastUserId").val();
		var subjectId = $("#subjectIdHidden").val();
		var termId = $("#termIdHidden").val();
		var errorId = $("#errorId").val();
		var timeStart = $("#data_box").val();
		var timeEnd = $("#data_box1").val();
		var type = 2;
		getCourse(tfCode, termId, subjectId, type, userId)
		stuErrorList(userId, tfCode, subjectId, termId, errorId, timeStart, timeEnd)

	});

	//如果老师是班主任  选择学科以后
	$(".charge_term").change(function() {
		var subjectId = $(this).find("option:selected").attr("data-id");
		if(subjectId != "") {
			$("#lastCode").val("");
			$("#errorTypeCode").val("");
			$("#versionCode").val("");
			$("#courseCode").val("");
			$("#subjectIdHidden").val(subjectId);
			var classId = $("#classIdHidden").val();
			var subjectId = $("#subjectIdHidden").val();
			var termId = $("#termIdHidden").val();
			var timeType = $(".green").attr("id");
			fillHtml(classId, subjectId, termId, timeType);
			$(".vname1").text("教材版本");
			$(".direct").css("visibility", "hidden");
			$(".oclick2").css("visibility", "hidden");
			$(".oclick3").css("visibility", "hidden");
		}
	});

}); //ready 

function changeStu(infos, timeType) {
	if(infos.length == 0) {
		$(".noInfo").text("该班级还没有学生加入哦！");
		$(".noInfo").show();

	} else {
		$(".noInfo").hide();
	}
	var texts = "";
	for(var n = 0; n < infos.length; n++) {
		var imgPath = infos[n].imgPath;
		if(imgPath == "" || imgPath == null) {
			imgPath = "img/stuhead.png";
		}
		texts +=
			"<li data-path='" + imgPath + "'>" +
			"<i></i>" +
			"<p class='stuN' data-id='user" + infos[n].userId + "'>" + infos[n].userName + "</p> " + "<p class='time'>" +
			"<span  class='color n'>今日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + infos[n].dayNum + "</span><br />" +
			"<span class='static n'>本周&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + infos[n].weekNum + "</span>" + "</p>" +
			"<p class='num'>" + "<span class='n'>总错题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + infos[n].totalNum + "</span>" + "</p>" + "</li>";
	}
	$("#stuInfo").html(texts);
	var num = 0;
	if(timeType == "day") {
		num = 0;
	} else if(timeType == "week") {
		num = 1;
	} else if(timeType == "total") {
		num = 2;
	}
	$("#stuInfo li").each(function(i) {
		$($($("#stuInfo li").get(i)).find(".n").get(num)).addClass("orange");
	});
	changeScreen(); //main1-2切换

}

function page2(currentPage, pageSize, length) {

	var start = (currentPage - 1) * pageSize;
	var end = currentPage * pageSize;

	if(end > length) {
		end = length;
	}

	$("#error_list li").css({
		"display": "none"
	});
	for(var i = start; i < end; i++) {
		$("#error_list li").get(i).style.display = "block";
	}
}

function fillPage2(stuList) {
	var total = stuList.length; //总条数
	var pageSize = 12; //每页条数
	var totalPages = Math.ceil(total / pageSize);
	var currentPage = 1;
	page2(currentPage, pageSize, total);
	var options = {
		currentPage: 1,
		totalPages: totalPages, //总共多少也
		numberOfPages: 3, //显示的页码数
		alignment: "center",
		itemTexts: function(type, page, current) {
			switch(type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
			}
		}, //点击事件，用于通过Ajax来刷新整个list列表
		onPageClicked: function(event, originalEvent, type, page) {
			page2(page, pageSize, total);
		}
	}
	$('#example').bootstrapPaginator(options);
}

function changeErrorList(_info) {

	var _text = "";
	var imgs = "";
	for(var i = 0; i < _info.length; i++) {
		var time = _info[i].createTime;
		time = time.substring(0, 16);
		_text +=
			"<li data-id='" + _info[i].questionId +
			"' data-id='question" + _info[i].questionId + "'>" +
			"<i></i>" +
			"<a href='error.html' target='_blank'>" +
			"<div class='eimg'>" +
			"<div class='mbMirror'>" +
			"<div class='mb'>" +
			"<div class='mirror'>" +
			"</div>" +
			"</div>" +
			"</div>" +
			"<img src='" + _info[i].imgPath + "' class='imgBg'/>" +
			"<span class='questionN' >" + decodeURI(_info[i].questionName) + "</span>" +
			"</div>" +
			"<div class='set_time'>" + time + "</div>" +
			"<div class='kinds'>错误类型：" +
			"<span class='errorN' >" + _info[i].errorName + "</span>" +
			"</div>" +
			"<input type='hidden' value='" + _info[i].note + "'>" +
			"</a>" +
			"</li>";

	}
	$("#error_list").html(_text);
	if(_info.length != 0) {
		fillPage2(_info);
		$("#noList").hide();
	} else {
		$("#example").html("");
		$("#noList").show();
	}

}

function changeVersion(infov) {
	var textv = "<li data-code='code0'>全部</li>";
	for(var i = 0; i < infov.length; i++) {
		textv += "<li data-id='version" + infov[i].versionId + "' data-code='code" + infov[i].tfCode + "'>" + infov[i].versionName + "</li>";
	}
	$(".ul1").html(textv);
	version();

} //changeVersion

function changeCourse(infoc) {
	var textc = "<li data-code='code0'>全部</li>";
	for(var j = 0; j < infoc.length; j++) {

		textc += "<li data-id='course" + infoc[j].courseId + "' data-code='code" + infoc[j].tfCode + "'>" + infoc[j].courseName + "</li>";
	}
	$(".ul2").html(textc);
	course();
} //changeCourse

function showStuName(infos) {
	var _text = "";
	for(var i = 0; i < infos.length; i++) {
		var imgPath = infos[i].imgPath;
		if(imgPath == "" || imgPath == null) {
			imgPath = "img/stuhead.png";
		}
		_text +=
			"<li data-id='user" + infos[i].userId + "'>" +
			"<img src='" + imgPath + "'/>" + "<span>" + infos[i].userName + "</span>" + "</li>";
	}
	$("#stu_name").html(_text);

}

function changeErrorType(_info) {
	var _text = "<li data-id='error0'>全部</li><li data-id='error-1'>无错误类型</li>";

	for(var i = 0; i < _info.length; i++) {
		_text += "<li data-id='error" + _info[i].id + "'>" + _info[i].name + "</li>";
	}
	$(".ul4").html(_text);
	errorType();

}

function version() {

	$(".oclick1 ").hover(function() {
		$(".ul1").show();
	}, function() {
		$(".ul1").hide();
	});
}

function course() {

	$(".oclick2").hover(function() {
		$(".ul2").show();
	}, function() {
		$(".ul2").hide();
	});
}

//main2 课本章节	在需要ready里面调用
function courseMain2() {

	$(".oclick3").hover(function() {
		$(".ul3").show();
	}, function() {
		$(".ul3").hide();
	});
	$(".oclick3 .btn .vname3").text("教材目录（" + $(".oclick2 .btn .vname2").text() + "）");
	$(".level1").click(function() {
		$(".oclick3 .btn .vname3").text($(this).text());
		$(".ul3").hide();

	});
}

function errorType() {
	$(".oclick4 .btn .vname4").text($(".ul4 li:first").text());
	$(".ul4 li").on("click", function() {
		$(".oclick4 .btn .vname4").text($(this).text());
		$(".ul4").hide();
	});

	$(".oclick4 ").hover(function() {
		$(".ul4").show();
	}, function() {
		$(".ul4").hide();
	});
}

//main1  main2界面之间的切换
function changeScreen() {
	//返回main1界面选项卡
	$(".class-date .img").click(function() {
		$(".stuclass").removeClass("stuclass2");
		$(".select_class").removeAttr("disabled");
		$(".charge_term").removeAttr("disabled");
		$(".select_class").css({
			"color": "#59332a",
			"background": "#fff"
		});
		$(".charge_term").css({
			"color": "#59332a",
			"background": "#fff"
		});

		$(".class-date .img ").css("visibility", "hidden");
		$(".class-date").css("background", "");
		$(".booklist").hide();
		$(".main1").show();
		$(".main2").hide();
	});
	//返回main2界面选项卡
	$(".main1 .stuname li").click(function() {
		$(".main1").hide();
		$(".stuclass").addClass("stuclass2");
		$(".select_class").attr("disabled", "disabled");
		$(".charge_term").attr("disabled", "disabled");
		$(".select_class").css({
			"color": "#999",
			"background": "#e6e6e6"
		});
		$(".charge_term").css({
			"color": "#999",
			"background": "#e6e6e6"
		});
		$(".class-date .img ").css("visibility", "visible");
		$(".class-date").css("background", "");
		$(".booklist").show();
		$(".main2").show();

	});
}

function stuName() {
	$("#stu_name").css({
		"position": "absolute",
		"left": "0px",
		"top": "0px"
	});
	var stuIndex = parseInt($("#stuIndex").val()) + 1;
	var num = 0;
	$("#pre").unbind("click");
	$("#next").unbind("click");
	var end = Math.ceil($("#stu_name li").length * 51 / 510) - 1;
	if(stuIndex > 10) {
		num = Math.ceil(stuIndex / 10) - 1;
		$("#stu_name").animate({
			top: "-=" + 510 * num + "px"

		}, "slow");
	}
	$("#pre").on("click", function() {

		if(num == 0) {
			return false;
		}
		num--;
		$("#stu_name").animate({
			top: '+=510px'
		}, "slow");

	})
	$("#next").on("click", function() {
		if(num == end) {
			return false;
		}
		num++;
		$("#stu_name").animate({
			top: '-=510px'
		}, "slow");
	});

}

//	function versionChange(versionId, classId, timeType) {
//		var versionChangeJson = {
//			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
//			"method": "versionChanged",
//			"contentJson": {
//				"versionId": versionId,
//				"classId": classId,
//				"timeType": timeType
//			}
//		};
//		var getVersionUrl = classInfoAPI + JSON.stringify(versionChangeJson);
//		$.ajax({
//			type: "post",
//			url: getVersionUrl,
//			dataType: "json",
//			success: function(data) {
//					var infoc = data.content.result.courseList;
//					var infos = data.content.result.stuList;
//					var message = data.content.message;
//					var errorMessage = data.content.errorMessage;
//					if (message == "error") {
//						alert(errorMessage);
//						return false;
//					}
//					changeCourse(infoc); //修改课程信息
//					//changeStu(infos); //修改学生信息
//					//fillPageHmtl(infos);
//					showStuName(infos);
//
//				} //success
//		}); //ajax
//	} //versionChange

//	function courseChange(tfCode, classId, timeType) {
//		var courseChangeJson = {
//			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
//			"method": "courseChanged",
//			"contentJson": {
//				"tfCode": tfCode,
//				"classId": classId,
//				"timeType": timeType
//			}
//		};
//		var getCourseUrl = classInfoAPI + JSON.stringify(courseChangeJson);
//		$.ajax({
//			type: "post",
//			url: getCourseUrl,
//			dataType: "json",
//			success: function(data) {
//
//					var infos = data.content.result.stuList;
//					var message = data.content.message;
//					var errorMessage = data.content.errorMessage;
//					if (message == "error") {
//						alert(errorMessage);
//						return false;
//					}
//					//changeStu(infos); //修改学生信息
//					//fillPageHmtl(infos);
//					showStuName(infos);
//
//
//				} //success
//		}); //ajax
//	} 
//获取版本

//	function getTreeNodesByCode(tfcode) {
//		var treeJson = {
//			"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
//			"method": "getTreeNodes",
//			"contentJson": {
//				"tfCode": tfcode
//			}
//		};
//		var getTreeUrl = classInfoAPI + JSON.stringify(treeJson);
//		$.ajax({
//			type: "post",
//			url: getTreeUrl,
//			dataType: "json",
//			success: function(data) {
//					var result = data.content.result;
//					var message = data.content.message;
//					var errorMessage = data.content.errorMessage;
//					if (message == "error") {
//						alert(errorMessage);
//						return false;
//					}
//					fillTreeHtml(result);
//					courseMain2();
//
//				} //success
//		}); //ajax
//	}