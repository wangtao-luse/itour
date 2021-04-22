$(function(){
	$(".modi_dialog .vicp-close,.vicp-step1 .vicp-operate a").click(function(){
		$(".modi_dialog").hide();
	});
	
	$(".vicp-drop-area").click(function(){
		$(".vicp-close").next().hide();
		$(".vicp-step1").prev().show();
		$(".vicp-step1").hide();
		$(".modi_dialog").find(".vicp-wrap").addClass("maxwrap");
		$(".vicp-step2").show();
	})
	$(".userCenter .user-img").click(function(){
		$(".vicp-close").next().show();
		$(".vicp-step1").prev().hide();
		$(".vicp-step2").hide();
		$(".vicp-step1").show();
		$(".modi_dialog").find(".vicp-wrap").removeClass("maxwrap");
		$(".upload-dialog").show();
	})
});