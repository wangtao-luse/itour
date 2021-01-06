$(function(){
	//关闭浮出层
	$(".weui-desktop-dialog__close-btn,.weui-desktop-btn.weui-desktop-btn_default").click(function(){
		$(".dialog-wrapper").hide();
	});
	//checkbox
	$(document).on("click",".article_tags_history_bd .weui-desktop-form__checkbox",function(){
	     var tags = $(".article_tags_history_bd .weui-desktop-form__checkbox:checked");
	     var disTags =$(".article_tags_history_bd .weui-desktop-form__checkbox:not(:checked)");
	     //当前对象的标签
	     var tagContent=$(this).parent().find(".weui-desktop-form__check-content").text();
	     //只允许选择三个标签
	     if(tags.length>=3){
	     	disTags.prop("disabled","disabled");
	     	$(".weui-desktop-form-tag__input").attr("placeholder","");
	     }else{
	     	disTags.prop("disabled","");
	     	$(".weui-desktop-form-tag__input").attr("placeholder","请输入话题，按回车分割");
	     }
	     //确认按钮样式
	     if(tags.length>=1){
	     $(".weui-desktop-dialog__ft .weui-desktop-btn_primary.weui-desktop-btn_disabled").removeClass("weui-desktop-btn_disabled");	
	     }else{
	     $(".weui-desktop-dialog__ft .weui-desktop-btn_primary").addClass("weui-desktop-btn_disabled");	
	     }
	     //追加标签
	    var check= $(this).is(":checked");
	    if(check){
	    appendTag(tagContent);	
	    }else{
	    	var tagct = $(".weui-desktop-form-tag__wrp").find(".weui-desktop-form-tag__name");
	         $(tagct).each(function(){
	         	var t = $(this).text();
	         	if(t==tagContent){
	    		$(this).parent().remove();
	    	}
	         })
	    	
	    	
	    }
	     
	});
	
	
	$(document).on("click",".weui-desktop-form-tag .weui-desktop-opr-btn_close",function(){
		var name = $(this).parent().find(".weui-desktop-form-tag__name").text();
		var check =$(".article_tags_history_bd .weui-desktop-form__check-content");
		var tags = $(".article_tags_history_bd .weui-desktop-form__checkbox:checked");
		var disTags =$(".article_tags_history_bd .weui-desktop-form__checkbox:not(:checked)");
		//取消checkbox选择
		$(check).each(function(){
			var v =$(this).text();
			if(name==v){
			$(this).parent().find(".weui-desktop-form__checkbox").prop("checked","");
			}
		});
		//删除标签时检查checkbox状态
		if(tags.length>3){
	     	disTags.prop("disabled","disabled");
	     	$(".weui-desktop-form-tag__input").attr("placeholder","");
	     }else{
	     	disTags.prop("disabled","");
	     	$(".weui-desktop-form-tag__input").attr("placeholder","请输入话题，按回车分割");
	     }
		$(this).parent().remove();
	});
	
	$(document).on("keyup",".weui-desktop-form-tag__input",function(e){
		var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	var t=$(this).val();
	        appendTag(t);
	    }
	});
	
	$(".modal-closeButton").click(function(){
		$(".modal-wrapper").hide();
	})
	$(".favlists-addButton").click(function(){
		$(this).closest(".modal-wrapper").hide();
		$(".article_add_collect").show();
	});
	//验证码
	$(".itour-close").click(function(){
		$("#itour-wrap-loginsubmit").hide();
	});
	$(".slide-authCode-wrape .close").click(function(){
		$(".slide-authCode-wrape").hide();
	});
	$(".slide-authCode-wraper .close").click(function(){
		$(".slide-authCode-wraper").hide();
	});
	$(".setnew-wrap button.btn-check-defaut").click(function(){
		$(".slide-authCode-wrape").show();
	})
	 $("#step1-next").click(function(){
    	$("#step1-wrap").css("display","none");
    	$("#step2-wrap").css("display","block");
    	$(".cur-step").addClass("done-step");
    	$(".cur-step span").text("");
    	$(".cur-step").removeClass("cur-step");    	
    	$(".person-pro-step2").addClass("cur-step");
   });
});
//追加话题标签
function appendTag(t){
	var tag ="<span class='weui-desktop-form-tag'><i class='weui-desktop-form-tag__name'>"+t+"</i><button type='button' class='weui-desktop-opr-btn weui-desktop-opr-btn_close'></button>	</span>";
	$(".weui-desktop-form-tag__wrp .weui-desktop-form-tag__input").before(tag);
}
