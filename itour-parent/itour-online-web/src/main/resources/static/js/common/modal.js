$(function(){
	
	//checkbox
	$(document).on("click",".article_tags_history_bd .weui-desktop-form__checkbox",function(){
		//已勾选
	     var tags =$(this).closest(".dialog-wrapper").find(".article_tags_history_bd .weui-desktop-form__checkbox:checked");
	     //未勾选
	     var disTags =$(this).closest(".dialog-wrapper").find(".article_tags_history_bd .weui-desktop-form__checkbox:not(:checked)");
	     //当前对象的标签
	     var tagContent=$(this).parent().find(".weui-desktop-form__check-content").text();
	     //只允许选择三个标签
	     if(tags.length>=3){
	     	disTags.prop("disabled","disabled");
	     	$(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__input").attr("placeholder","");
	     }else{
	     	disTags.prop("disabled","");
	     	$(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__input").attr("placeholder","请输入话题，按回车分割");
	     }
	     //确认按钮样式
	     if(tags.length>=1){
	    	 $(this).closest(".dialog-wrapper").find(".weui-desktop-dialog__ft .weui-desktop-btn_primary.weui-desktop-btn_disabled").removeClass("weui-desktop-btn_disabled");	
	     }else{
	    	 $(this).closest(".dialog-wrapper").find(".weui-desktop-dialog__ft .weui-desktop-btn_primary").addClass("weui-desktop-btn_disabled");	
	     }
	     //追加标签
	    var check= $(this).is(":checked");
	    if(check){
	    appendTag(tagContent,$(this));	
	    }else{//取消选择
	    	var tagct =  $(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__wrp .weui-desktop-form-tag__name");
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
		var check =$(this).closest(".dialog-wrapper").find(".article_tags_history_bd .weui-desktop-form__check-content");
		var tags = $(this).closest(".dialog-wrapper").find(".article_tags_history_bd .weui-desktop-form__checkbox:checked");
		var disTags =$(this).closest(".dialog-wrapper").find(".article_tags_history_bd .weui-desktop-form__checkbox:not(:checked)");
		var btn_tag = $(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__wrp .weui-desktop-form-tag").length;
		if(btn_tag>1){
			$(this).closest(".dialog-wrapper").find(".weui-desktop-btn.weui-desktop-btn_primary").removeClass("weui-desktop-btn_disabled");	
		}else{
			$(this).closest(".dialog-wrapper").find(".weui-desktop-btn.weui-desktop-btn_primary").addClass("weui-desktop-btn_disabled");	
		}
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
	     	$(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__input").attr("placeholder","");
	     }else{
	     	disTags.prop("disabled","");
	     	$(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__input").attr("placeholder","请输入话题，按回车分割");
	     }
		$(this).parent().remove();
		
		
		
		
	});
	
	$(document).on("keyup",".weui-desktop-form-tag__input",function(e){
		var ev = document.all ? window.event : e;
		//输入的标签
    	var t=$(this).val();
    	$(this).closest(".dialog-wrapper").find(".weui-desktop-form__counter").text(t.length+"/16");
		if(t.length>16){
			$(this).closest(".dialog-wrapper").find(".weui-desktop-form__counter").addClass("error");
    		return;
    	}else{
    		$(this).closest(".dialog-wrapper").find(".weui-desktop-form__counter").removeClass("error");
    		
    	}
	    if(ev.keyCode==13) {
	        appendTag(t,$(this));
	        
	        $(this).closest(".dialog-wrapper").find(".article_tags_history .weui-desktop-form__checkbox").each(function(){
	        	var txt = $(this).parent().find(".weui-desktop-form__check-content").text();
	        	if(t==txt){
	        		$(this).prop("checked","checked");
	        		return;
	        	}
	        	
	        });
	        //禁用索引的checkbox
	       var len = $(this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__wrp .weui-desktop-form-tag").length;
	       if(len>=3){
	    	   $(".article_tags_history .weui-desktop-form__checkbox").each(function(){
	    		  var checked = $(this).is(":checked");
	    		  if(!checked){
	    			  $(this).prop("disabled","disabled");  
	    		  }
		        		
		        	
		        }); 
	       }
	    }
	});
	
	
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
	
	
});
//追加话题标签
function appendTag(t,$this){
	var tag ="<span class='weui-desktop-form-tag'><i class='weui-desktop-form-tag__name'>"+t+"</i><button type='button' class='weui-desktop-opr-btn weui-desktop-opr-btn_close'></button>	</span>";
	$($this).closest(".dialog-wrapper").find(".weui-desktop-form-tag__wrp .weui-desktop-form-tag__input").before(tag).val("");
	$($this).closest(".dialog-wrapper").find(".weui-desktop-btn.weui-desktop-btn_primary").removeClass("weui-desktop-btn_disabled");
	$($this).closest(".dialog-wrapper").find(".weui-desktop-form__counter").text("0/16");
}
//关闭浮出层
$(document).on("click",".weui-desktop-dialog__close-btn,.weui-desktop-btn.weui-desktop-btn_default",function(){
	$(".dialog-wrapper").hide();
});
$(document).on("click",".modal-closeButton",function(){
	$(".modal-wrapper").hide();
})
