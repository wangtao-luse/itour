$(function(){
		$("#itour-banner .toolbar-subMenu-box").mouseenter(function(){
		$(this).find(".toolbar-subMenu").show();
	});
	$("#itour-banner .toolbar-subMenu-box").mouseleave(function(){
		$(this).find(".toolbar-subMenu").hide();
	});
	$("article .contentItem-action.button,.commentItemV2-footer .button").mouseenter(function(){
		$(this).find(".isdefault").css("display","none");
		$(this).find(".isactive").css("display","block");
		$(this).css("color","#0077E6");
	});
	$("article .contentItem-action.button,.commentItemV2-footer .button").mouseleave(function(){
		$(this).find(".isdefault").css("display","block");
		$(this).css("color","#646464");
		$(this).find(".isactive").css("display","none");
	});
	
	$("#comment_content,#comment_contentNew").focus(function(){
		$(this).addClass("open");
	});
	$("#comment_content").blur(function(){
		$(this).removeClass("open");
	});
	$(".tool_bar__fold-btn").click(function(){
		var fold = $(this).hasClass("fold");
		if(fold){
			 $(this).removeClass("fold");	
			 $(this).text("回到顶部");
		}else{
		  $(this).addClass("fold");	
		  $(this).text("文章设置");
		}
		
	});
	$(document).on("click",".item-input-wrap .i-ops .icon-yanjing",function(){
		$(this).addClass("icon-biyan");
		$(this).removeClass("icon-yanjing");
		$(this).closest(".item-input-wrap").find(".field").attr("type","text");
	});
	$(document).on("click",".item-input-wrap .i-ops .icon-biyan",function(){
		$(this).addClass("icon-yanjing");
		$(this).removeClass("icon-biyan");
		$(this).closest(".item-input-wrap").find(".field").attr("type","password");
	});
	
	$(".form-wrap input").focus(function(){
		$(this).closest(".form-item").addClass("z-focuse");
		var v = $(this).val();
		
	});
	$(".form-wrap input").blur(function(){
		$(this).closest(".form-item").removeClass("z-focuse");
	});
	
	$(".appConsole .el-submenu__title").click(function(){
		var hasclzz =$(this).closest("li").hasClass("is-opened");
		if(hasclzz){
			$(this).closest("li").removeClass("is-opened");
			$(this).closest("li").find(".el-menu.el-menu--inline").css("display","none");
		}else{
			$(this).closest("li").addClass("is-opened");
			$(this).closest("li").find(".el-menu.el-menu--inline").css("display","inline-block");
		}
		
	});
	
})
