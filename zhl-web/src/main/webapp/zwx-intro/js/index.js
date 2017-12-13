textAudio = "<audio preload='auto' controls id='errorAudio'>" + "<source src='raw/22.mp3' type='audio/mp3' id='stuAudio'/>" + "</audio>";
img = "<img src='' id='bpic'/>";

function addLoadEvent(fuc) {
	var oldonload = window.onload;
	if (typeof oldonload != 'function') {
		window.onload = fuc;
	} else {
		window.onload = function() {
			oldonload();
			fuc();
		}
	}
}

function addClass(targetElement, newClass) {
	if (targetElement.className) {
		targetElement.className = targetElement.className + " " + newClass;
	} else {
		targetElement.className = newClass;
	}
}

function moveElement(elementID, final_x, final_y, interval) {
	var elem = document.getElementById(elementID);
	var xpos = parseInt(elem.style.left);
	var ypos = parseInt(elem.style.top);
	if (xpos == final_x && ypos == final_y) {
		return true;
	}
	if (elem.init) {
		clearTimeout(elem.init);
	}
	if (xpos < final_x) {
		var dist = Math.ceil((final_x - xpos) / 10);
		xpos = xpos + dist;

	}
	if (xpos > final_x) {
		var dist = Math.ceil((xpos - final_x) / 10);
		xpos = xpos - dist;
	}
	if (ypos < final_y) {
		var dist = Math.ceil((final_y - ypos) / 10);
		ypos = ypos + dist;
	}
	if (ypos > final_y) {
		var dist = Math.ceil((ypos - final_y) / 10);
		ypos = ypos - dist;
	}
	elem.style.left = xpos + "px";
	elem.style.top = ypos + "px";
	var repeat = "moveElement('" + elementID + "'," + final_x + "," + final_y + "," + interval + ")";
	elem.init = setTimeout(repeat, interval); //在函数体内调用本函数，称为递归调用。

}

//addLoadEvent(stuName);
//移动错题查看页面的图片error.html
function movePic() {
	if (!document.getElementById("epictures_ul")) return false;
	var oUl = document.getElementById("epictures_ul");
	var osPre = document.getElementById("spre");
	var osNext = document.getElementById("snext");
	var oLis = oUl.getElementsByTagName("li");
	var oText = document.getElementById("text");
	var oBpic = document.getElementById("bpic");
	var obPre = document.getElementById("bpre");
	var obNext = document.getElementById("bnext");
	var oImg = oUl.getElementsByTagName("img");
	var oS3 = document.getElementById("s3");
	var oS4 = document.getElementById("s4");
	var oS2 = document.getElementsByClassName("s2")[0];
	var oQues = document.getElementsByClassName("questionName")[0];
	var oError = document.getElementsByClassName("errorName")[0];
	var oNote = document.getElementById("note");
	oS4.childNodes[0].nodeValue = oLis.length;

	var storage = window.localStorage;
	var num = 0;
	var index = 0;
	var resultLength = JSON.parse(storage.errorList).length;
	storage.current = index;
	var end = Math.ceil(oLis.length / 7) - 1;
	oUl.style.width = oLis.length * oLis[0].offsetWidth + 12 * oLis.length + "px";
	oLis[index].style.border = "2px solid #5A8FF5";
	oS3.innerHTML = 1;
	for (var i = 0; i < oLis.length; i++) {
		oLis[i].j = i;
		oLis[i].onclick = function() {
			$(".epicture").html(img);
			$("#bpic").attr("src", this.childNodes[1].getAttribute("src"));

			pictureRotate(); //图片自适应旋转
			index = this.j;
			storage.current = index;
			oS3.childNodes[0].nodeValue = this.j + 1;
			clear();
			this.style.border = "2px solid #5A8FF5";
		}

	}

	obNext.onclick = function() {
	
		if (index == 0) {

			var dataIndex = $("#preQuestion").attr("data-index");
			var questionId = $("#preQuestion").attr("data-questionId");
			if (dataIndex != "") {
				$(".questionName").text($("#preQuestion").attr("data-ques"));
				$(".errorName").text($("#preQuestion").attr("data-error"));
				$(".s2").text($("#preQuestion").attr("data-time").substring(0, 16));

				if (($("#preQuestion").attr("data-voice") != "") && ($("#preQuestion").attr("data-voice") != null)) {

					$(".sound").html(textAudio);
					$("#errorAudio").audioPlayer();
					$("#stuAudio").attr("src", $("#preQuestion").attr("data-voice"));
					$(".sound").show();
				} else {
					$(".sound").hide();
					$(".sound").html("");
				}
				$(".text").text($("#preQuestion").attr("data-note"));
				$(".epicture").html(img);
				$("#bpic").attr("src", $("#preQuestion").attr("src"));


				pictureRotate(); //图片旋转
				getSelfWrongImg(questionId);
				getCommentList(questionId);
					$("#questionIdAll").val(questionId);
				if ((parseInt(dataIndex) + 1) < resultLength) {
					nextQuestion(parseInt(dataIndex) + 1);
				}

				if (parseInt(dataIndex) != 0) {
					preQuestion(parseInt(dataIndex) - 1);
				} else {
					clearQuestion("#preQuestion", "没有上一题");

				}
			} else {
				pop("没有上一题哦");
			}
			return false;
		}
		$(".epicture").html(img);
		index--;
		storage.current = index;
//		console.log(storage.current);

		if ((parseInt(storage.current) != 0) && ((parseInt(storage.current) % 6) == 0)) {
			if (num != 0) {
				num--;
				$("#epictures_ul").animate({
					left: '+=859px'
				}, "slow");
			}

		}
		oS3.childNodes[0].nodeValue = index + 1;
		$("#bpic").attr("src", oImg[index].getAttribute("src"));

		pictureRotate(); //图片自适应 旋转
		clear();
		oLis[index].style.border = "2px solid #5A8FF5";
	}
	obPre.onclick = function() {

		if (index == oLis.length - 1) {
			//			pop("已经是最后一题了哦！");
			//			return false;

			var dataIndex = $("#nextQuestion").attr("data-index");
			var questionId = $("#nextQuestion").attr("data-questionId");
			if (dataIndex != "") {
				$(".questionName").text($("#nextQuestion").attr("data-ques"));
				$(".errorName").text($("#nextQuestion").attr("data-error"));
				$(".s2").text($("#nextQuestion").attr("data-time").substring(0, 16));

				if (($("#nextQuestion").attr("data-voice") != "") && ($("#nextQuestion").attr("data-voice") != null)) {

					$(".sound").html(textAudio);
					$("#errorAudio").audioPlayer();
					$("#stuAudio").attr("src", $("#nextQuestion").attr("data-voice"));
					$(".sound").show();
				} else {
					$(".sound").hide();
					$(".sound").html("");
				}
				$(".text").text($("#nextQuestion").attr("data-note"));
				$(".epicture").html(img);
				$("#bpic").attr("src", $("#nextQuestion").attr("src"));
				pictureRotate(); //图片旋转
				getSelfWrongImg(questionId);//获取图片
				getCommentList(questionId);//获取评论
				$("#questionIdAll").val(questionId);
				preQuestion(parseInt(dataIndex) - 1);
				if ((parseInt(dataIndex) + 1) < resultLength) {
					nextQuestion(parseInt(dataIndex) + 1);
				} else {
					clearQuestion("#nextQuestion", "没有下一题");

				}
			} else {
				pop("没有下一题哦");
			}
			return false;
		}
		$(".epicture").html(img);
		index++;
		storage.current = index;
		oS3.childNodes[0].nodeValue = index + 1;
		$("#bpic").attr("src", oImg[index].getAttribute("src"));

		pictureRotate();

		clear();
		oLis[index].style.border = "2px solid #5A8FF5";
	}

	function clear() {
		for (var j = 0; j < oLis.length; j++) {
			oLis[j].style.border = "2px solid rgb(42,44,48)";
		}
	}
}

function pictureRotate() {
	$('#bpic').jqthumb({
		width: 950,
		height: 650,
		after: function(imgObj) {
			imgObj.css('opacity', 0).animate({
				opacity: 1
			}, 1000);
			var rot = 0;
			var turn = 1;
			$("#rotate").click(function() {
				turn = -turn;
				rot += 90;

				$(".epicture .jqthumb div").rotate({
					animateTo: rot
				});
				if (turn == -1) {
					$(".epicture .jqthumb div").css({
						"width": "650px",
						"height": "650px",
						"margin-left": "150px"
					});

				}
				if (turn == 1) {
					$(".epicture .jqthumb div").css({
						"width": "950px",
						"height": "650px",
						"margin-left": "0px"
					});
				}

			});
		}
	});
}