$(function() {

	$("#backTop").on("click", function() {
			$(window).scrollTop(0);
	});


	var height = $(window).height();
	if (height > 730) {
		$(".section1").css({
			"min-height": $(window).height() + "px"
		});
	} else {
		$(".section1").css({
			"min-height": "730px"
		});
	}


	$(".move_light").animate({
		width: "123px",
		height: "188px"
	});

	$(".bcloud").animate({
		left: "+=120px"
	});
	$(".bcloud").animate({
		left: "+=120px"
	});
	$(".bcloud").animate({
		left: "+=200px"
	});


	$(".light").animate({
		top: "+=30px"
	});
	$(".light").animate({
		top: "-=30px"
	});
	$(".light").animate({
		top: "+=30px"
	});
	$(".light").animate({
		top: "-=30px"
	});
	$(".light").animate({
		top: "+=30px"
	});
	$(".light").animate({
		top: "-=30px"
	});
	$(".light").animate({
		top: "+=30px"
	});
	$(".move_light").animate({
		top: "+=30px"
	});


	$(".move_light").animate({
		top: "-=30px"
	});
	$(".move_light").animate({
		top: "+=30px"
	});
	$(".move_light").animate({
		top: "-=30px"
	});
	$(".move_light").animate({
		top: "+=30px"
	});
	$(".move_light").animate({
		top: "-=30px"
	});
	$(".move_light").animate({
		top: "+=30px"
	});

	//
	$(".carouselBox").animate({
		marginTop: "+=30px"
	});
	$(".carouselBox").animate({
		marginTop: "-=30px"
	});
	$(".carouselBox").animate({
		marginTop: "+=30px"
	});
	$(".carouselBox").animate({
		marginTop: "-=30px"
	});
	$(".carouselBox").animate({
		marginTop: "+=30px"
	});

	$("#slides").slides({
		preload: true,
		play: 3000,
		pause: 500,
		hoverPause: true
	});

	$(window).scroll(function() {
		if ($(this).scrollTop() > 20) {
			$("#backTop").show();

		} else {
			$("#backTop").hide();
		}


		var firstPage = $(".firstPage").height();
		var secondPage = $(".secondPage").height();
		var thirdPage = $(".thirdPage").height();
		if (($(this).scrollTop() > 25)) {
			$("#slideF1").slideDown();
			$("#slideL1").animate({
				width: "370px"
			});

		} else {
			$("#slideF1").slideUp();
			$("#slideL1").animate({
				width: "0px"
			});

		}
		if (($(this).scrollTop() > (firstPage))) {
			$("#slideF2").slideDown();
			$("#slideL2").slideDown();
		} else {
			$("#slideF2").slideUp();
			$("#slideL2").slideUp();
		}

		if (($(this).scrollTop() > (secondPage + firstPage - 30))) {
			$("#slideF3").slideDown();
			$("#slideL3").slideDown();
			$(".companyInfo").show();
		} else {
			$("#slideF3").slideUp();
			$("#slideL3").slideUp();
			$(".companyInfo").fadeOut();
		}

	});


});