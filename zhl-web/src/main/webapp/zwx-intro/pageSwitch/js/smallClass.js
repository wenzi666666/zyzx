$(function() {
	$(".cover").height($(window).height());
	$(".smallClassBox").height($(window).height() - 10);
//	$(".smallClassBox").width($(window).width() - 10);
//	$(".smallClassBox").css({
//		"margin-left": "5px",
//		"margin-top": "5px"
//	});
	$(".cov").height($(window).height());
	$(".inner").height($(window).height());

	//videoList高度
	var headHeight = $(".smallHead").height() + 20;
	var videoListHeight = $(window).height() - headHeight;
	$(".videoList").height(videoListHeight);
	$(".smallClass").on("click", function() {
		$(".cover").slideDown();
		$(".smallClassBox").slideDown();
	})
	$(".offVideo").on("click", function() {
		$(".cover").slideUp();
		$(".smallClassBox").slideUp();
	});

	//填充默认班级学科视频
	var dataSevenMath = list.seven.math;
	fill(dataSevenMath);
	//切换
	$(".subject li").on("click", function() {
		$(".subject li").removeClass("style");
		$(this).addClass("style");
		var subjectId = $(this).attr("data-id");
		var termId = $(".term option:selected").attr("data-id");
		var data = "";
		if (subjectId == "math") {
			if (termId == "seven") {
				data = list.seven.math;
			} else if (termId == "eight") {
				data = list.eight.math;
			} else if (termId == "nine") {
				data = list.nine.math;
			} else if (termId == "hs1") {
				data = list.hs1.math;
			} else if (termId == "hs2") {
				data = list.hs2.math;
			} else if (termId == "hs3") {
				data = list.hs3.math;
			}
		} else if (subjectId == "physics") {
			if (termId == "seven") {
				data = list.seven.physics;
			} else if (termId == "eight") {
				data = list.eight.physics;
			} else if (termId == "nine") {
				data = list.nine.physics;
			} else if (termId == "hs1") {
				data = list.hs1.physics;
			} else if (termId == "hs2") {
				data = list.hs2.physics;
			} else if (termId == "hs3") {
				data = list.hs3.physics;
			}
		} else if (subjectId == "chemistry") {
			if (termId == "seven") {
				data = list.seven.chemistry;
			} else if (termId == "eight") {
				data = list.eight.chemistry;
			} else if (termId == "nine") {
				data = list.nine.chemistry;
			} else if (termId == "hs1") {
				data = list.hs1.chemistry;
			} else if (termId == "hs2") {
				data = list.hs2.chemistry;
			} else if (termId == "hs3") {
				data = list.hs3.chemistry;
			}

		}
		fill(data);

	});
	//切换班级的时候
	$(".term").change(function() {
		var termId = $(".term option:selected").attr("data-id"); //seven
		var subjectId = $(".style").attr("data-id"); //数学 物理 化学
		var data = "";

		if (termId == "seven") {
			if (subjectId == "math") {
				data = list.seven.math;

			} else if (subjectId == "physics") {
				data = list.seven.physics;

			} else if (subjectId == "chemistry") {
				data = list.seven.chemistry;

			}

		} else if (termId == "eight") {
			if (subjectId == "math") {
				data = list.eight.math;

			} else if (subjectId == "physics") {
				data = list.eight.physics;

			} else if (subjectId == "chemistry") {
				data = list.eight.chemistry;

			}
		} else if (termId == "nine") {
			if (subjectId == "math") {
				data = list.nine.math;

			} else if (subjectId == "physics") {
				data = list.nine.physics;

			} else if (subjectId == "chemistry") {
				data = list.nine.chemistry;

			}
		} else if (termId == "hs1") {
			if (subjectId == "math") {
				data = list.hs1.math;

			} else if (subjectId == "physics") {
				data = list.hs1.physics;

			} else if (subjectId == "chemistry") {
				data = list.hs1.chemistry;

			}
		} else if (termId == "hs2") {
			if (subjectId == "math") {
				data = list.hs2.math;

			} else if (subjectId == "physics") {
				data = list.hs2.physics;

			} else if (subjectId == "chemistry") {
				data = list.hs2.chemistry;

			}
		} else if (termId == "hs3") {
			if (subjectId == "math") {
				data = list.hs3.math;

			} else if (subjectId == "physics") {
				data = list.hs3.physics;

			} else if (subjectId == "chemistry") {
				data = list.hs3.chemistry;

			}
		}
		fill(data);
		if ($(".term option:selected").attr("data-id") == "seven") {
			$(".subject li[data-id='physics']").hide();
			$(".subject li[data-id='chemistry']").hide();
			$(".subject li[data-id='math']").css({"margin-left":($(".subject").width()-$(".subject li").width())/2+"px"});
		} else {
			$(".subject li[data-id='physics']").show();
			$(".subject li[data-id='chemistry']").show();
			$(".subject li[data-id='math']").css({"margin":"0 20px"});
		}
	});


	if ($(".term option:selected").attr("data-id") == "seven") {
		$(".subject li[data-id='physics']").hide();
		$(".subject li[data-id='chemistry']").hide();
		$(".subject li[data-id='math']").css({"margin-left":($(".subject").width()-$(".subject li").width())/2+"px"});
	} else {
		$(".subject li[data-id='physics']").show();
		$(".subject li[data-id='chemistry']").show();
		$(".subject li[data-id='math']").css({"margin":"0 20px"});
	}


	$(".mb").height($(window).height());

	//显示视频
	//显示视频
	$(document).on("click", ".videoList li", function() {

      	var code=$(this).attr("data-code");
		var imgPath = $(this).attr("data-img");
		var txt=$(this).find("span").text()
		videoPath(code,imgPath,txt);
	});

	//关闭微课
	$(document).on("click", ".off", function() {
		$(".mb").slideUp();
		$("#videos").attr("src", "");
		$("#videos").attr("poster", "");
		$(".txt").text("");
		$("body").css({
			"font-size": "18px"
		})
	});

});

function videoPath(code,imgPath,txt)
{
	var Json = {
		"className": "com.zhl.unify.interfaces.move_work.service.ZwxWebService",
		"method": "getVideoPath",
		"contentJson": {
			"resCode": code
		}
	};
	var getUrl = classInfoAPI + JSON.stringify(Json);
	$.ajax({
		type: "post",
		url: getUrl,
		dataType: "json",
		success: function(data) {
				var msg = data.content.message;
				var videoPath=data.content.result.videoPath;
				if (msg == "error") {
					alert("访问服务器资源失败!请重新获取或者联系管理员")
					return false;
				}
				$(".mb").slideDown();
				$(".videos").attr("src",videoPath);
				$(".videos").attr("poster",imgPath);
				$(".txt").text(txt);
			} //success

	}); //ajax
}

function fill(info) {
	var txt = "";
	for (var i = 0; i < info.length; i++) {
		txt += "<li  data-img='" + info[i].imgPath + "' data-code='" + info[i].resCode + "'>" + "<div class='play'></div>" + "<img src='" + info[i].imgPath + "'/>" + "<span>" + info[i].resName + "</span>" + "</li>";
	}
	$(".videoList").html(txt);
	$(".videoList li").height(($(".videoList").height() - 15) / 4);
	$(".videoList img").height($(".videoList li").height() - 22);


	var widths = $(".videoList li").width();
	var heights = $(".videoList li").height() - 22;
	$('.videoList li img').jqthumb({
		width: widths,
		height: heights,
		after: function(imgObj) {
			imgObj.css('opacity', 0).animate({
				opacity: 1
			}, 10);
		}
	});
}