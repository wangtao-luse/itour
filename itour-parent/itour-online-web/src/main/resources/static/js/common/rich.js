$(function(){
	$(".richContent-inner").find("a").each(function(){
		if($(this).attr("target")!=="_blank"){
		$(this).attr('target','_blank');
		$(this).css("color","#0084ff");
		}
		});	
	//样式调整
	$(".richContent").addClass("markdown-body ");
	
});