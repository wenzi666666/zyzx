$(function() {


	showPosition();

	//关闭微课
	$(document).on("click", ".off", function() {
		$(".mb").slideUp();
		$("#videoInfo").html("");
	});

	$(".offVideo").on("click", function() {
		$(".cover").slideUp();
		$(".smallClass").slideUp();
	});

	$(".cover").on("click", function() {
		$(this).slideUp();
		$(".smallClass").slideUp();
	});

	//显示视频
	$(document).on("click", ".videoUl li", function() {
		$(".mb").slideDown();
		var videoPath = $(this).attr("data-path");
		var imgPath = $(this).attr("data-img");
		var txt = "<div class='off'>&times;</div>" + "<video class='videos' src='" + videoPath + "' type='mp4' poster='" + imgPath + "' controls id='videos' autoPlay='autoPlay'></video>" + "<div class='txt'>" + $(this).find("span").text() + "</div>";
		$("#videoInfo").html(txt);
	});

	//显示微课
	$("#lesson").on("click", function() {
		showPosition();
		$(".cover").slideDown();
		$(".smallClass").slideDown();

	});


	$('.videoUl li img').jqthumb({
		width: 184,
		height: 101,
		after: function(imgObj) {
			imgObj.css('opacity', 0).animate({
				opacity: 1
			}, 10);
		}
	});

	//切换班级
	$("#smallTerm li").on("click", function() {
		$("#smallTerm li").removeClass("style");
		$(this).addClass("style");
		var termId = $(this).attr("id");
		var data = "";
		if (termId == "seven") {
			data = list.seven;
		} else if (termId == "eight") {
			data = list.eight;
		} else if (termId == "nine") {
			data = list.nine;
		} else if (termId == "hs1") {
			data = list.hs1;
		} else if (termId == "hs2") {
			data = list.hs2;
		} else if (termId == "hs3") {
			data = list.hs3;
		}
		fillChemistry(data.chemistry);
		fillMath(data.math);
		fillPhysics(data.physics);

	});
	$("#smallTerm li").removeClass("style");
	$("#smallTerm li").eq(2).addClass("style");
	var dataSeven = list.nine;
	var seven_math = dataSeven.math;
	var seven_physics = dataSeven.physics;
	var seven_chemistry = dataSeven.chemistry;
	fillChemistry(seven_chemistry);
	fillMath(seven_math);
	fillPhysics(seven_physics);






});

function showPosition() {
	var left = Math.ceil(($(window).width() - $("#smallClass").width()) / 2);
	var top = Math.ceil(($(window).height() - $("#smallClass").height()) / 2);
	$("#smallClass").css({
		"margin-top": top,
		"margin-left": left
	});


	var leftVideo = Math.ceil(($(window).width() - $("#videoInfo").width()) / 2);
	var topVideo = Math.ceil(($(window).height() - $("#videoInfo").height()) / 2);
	$("#videoInfo").css({
		"margin-top": topVideo,
		"margin-left": leftVideo
	});
}

function fillMath(info) {
	var txt = "";
	for (var i = 0; i < info.length; i++) {
		txt += "<li data-path='" + info[i].videoPath + "' data-img='" + info[i].imgPath + "' data-code='" + info[i].resCode + "'>" + "<div class='play'></div>" + "<img src='" + info[i].imgPath + "'/>" + "<span>" + info[i].resName + "</span>" + "</li>";

	}
	$("#mathUl").html(txt);
}

function fillPhysics(info) {
	var txt = "";
	for (var i = 0; i < info.length; i++) {
		txt += "<li data-path='" + info[i].videoPath + "' data-img='" + info[i].imgPath + "' data-code='" + info[i].resCode + "'>" + "<div class='play'></div>" + "<img src='" + info[i].imgPath + "'/>" + "<span>" + info[i].resName + "</span>" + "</li>";

	}
	$("#physicsUl").html(txt);
}

function fillChemistry(info) {
	var txt = "";
	for (var i = 0; i < info.length; i++) {
		txt += "<li data-path='" + info[i].videoPath + "' data-img='" + info[i].imgPath + "' data-code='" + info[i].resCode + "'>" + "<div class='play'></div>" + "<img src='" + info[i].imgPath + "'/>" + "<span>" + info[i].resName + "</span>" + "</li>";

	}
	$("#chemistryUl").html(txt);
}