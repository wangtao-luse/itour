$(function(){
	/**
	 * 初始化MarkDown
	 */
	var mdEditer=editormd("md-container",{
				autoHeight : false,
				path: ctxPath+"/md/lib/",
				watch:true//关闭实时预览
				
			});
	
	/**
	 * 保存草稿
	 */		
	$(".article-bar__user-box .btn-save").click(function(){
		var title = $(".article-bar__input-box .article-bar__title").val();
		var text = mdEditer.getMarkdown();
		if($.isEmpty(title)){showTip("标题不能为空！");return;}
		if($.isEmpty(text)){showTip("内容不能为空！");return;}
		var url="/work/savaOrUpdateWorkInfo";
		var tagArr=[];
		var colArr=[];
		var data={"vo":{
			 			"title":title
						},
				   "markdown":text,
				   "tag_arr":tagArr,
				   "col_arr":colArr
		};
		postAjax(url,JSON.stringify(data),function(){
			showTip("以保存为草稿！");
		},{errorFunction:function(){}})
	});
	$(".layou-panel .btn-publish").click(function(){
		$(".modal-article-pushlish-wrp").show();
	})
	$(".mod-dialog-close,.modal__button-bar .btn-cancel").click(function(){
		$(".modal-dialog-wrapper").hide();
	});
	$(".mark_selection .mark_selection_title_el_tag").click(function(){
		$(".mark_selection .mark_selection_box").show();
	});
	$(".mark_selection .modal__close-button.tag-close-btn").click(function(){
		$(".mark_selection .mark_selection_box").hide();
	});
	//标签相关事件
	$(document).on("keyup",".mark_selection_box_header .el-input__inner",function(ev){
		if(ev.keyCode==13) {
			var tag =$(this).val();
			var chlid_len = $(".mark_selection .mark_selection_title_el_tag span>span").length;
			if(chlid_len<5){
				$(".mark_selection .tag__btn-tag").show();
				appendTag(tag,$(this));
			}
			if(chlid_len>=4){
				$(".mark_selection .tag__btn-tag").hide();
			}
			
		}
	}); 
	$(document).on("click",".mark_selection .el-tag__close.el-icon-close",function(){
		$(this).parent().remove();
		var chlid_len = $(".mark_selection .mark_selection_title_el_tag span>span").length;
		if(chlid_len<5){
			$(".mark_selection .tag__btn-tag").show();
		}
	})
	//专栏相关
	//新建分类专栏
	$(".tag__box .tag__btn-tag").click(function(){
		var tlen = $(".tag__box .tag__item-list .tag__item-box").length;
		if(tlen<3){
			appendinitcol();
		}					
		$(".tag__item-box .tag-input-text").focus();
	})
	//专栏删除
	$(document).on("click",".tag__item-box .tag__btn-tag-delete",function(){
	   $(this).parent().remove();
	   var tlen = $(".tag__box .tag__item-list .tag__item-box").length;
		if(tlen<3){
			$(".tag__box .tag__btn-tag").show();
		}
	})
	//新建专栏文本框样式
	$(document).on("keyup",".input-text-box .tag-input-text",function(){
		var v = $(this).val();
		var w =$(this).parent().parent().find(".tag__name").text(v).width();
		var txt=$(this).width();
	    this.style.width = w + "px";
		
	});
	//新建专栏文本框样式
	$(document).on("blur",".input-text-box .tag-input-text",function(){
		$(this).hide();
		$(this).parent().prev().css("display","block");
		var v =$(this).val();
		if(!v){
		 $(this).closest(".tag__item-box").remove();	
		}
		$(this).parent().remove();
		var tlen = $(".tag__box .tag__item-list .tag__item-box").length;
		if(tlen>=3){
			$(".tag__box .tag__btn-tag").hide();
		}
	});
	//专栏多选框检查
	
	//发布文章
	$(".modal__button-bar .pulish-article-btn").click(function(){
		console.log("click");
		var title = $(".article-bar__input-box .article-bar__title").val();
		var text = mdEditer.getMarkdown();
		if($.isEmpty(title)){showTip("标题不能为空！");return;}
		if($.isEmpty(text)){showTip("内容不能为空！");return;}
	});
})
//显示提示信息
function showTip(msg){
	$(".notice-box .notice").text(msg);
	$(".notice-box").show();
	setTimeout(function(){
		$(".notice-box").hide();
	},3000);
}
//追加标签
function appendTag(tag,$that){
	var len = $(".mark_selection .mark_selection_title_el_tag span").length;
	if(len<=0){
		 $($that).closest(".modal-dialog-wrapper").find(".mark_selection .mark_selection_title_el_tag .tag__btn-tag").before("<span></span>");
	}
var html="<span class='el-tag el-tag--light mark_selection_box_el_tag '>"+tag+"<i class='el-tag__close el-icon-close'></i></span>";
  $($that).closest(".modal-dialog-wrapper").find(".mark_selection .mark_selection_title_el_tag>span").append(html);
}
//追加专栏
function appendinitcol(){
	var html="<div class='tag__item-box'>"
	            +"<span contenteditable='false' class='tag__name'></span>"
				+"<span class='input-text-box'><input type='text' class='tag-input-text'/></span>"
				+"<button class='tag__btn-tag-delete'>"
					+"<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' class='icon'>"
						+"<path d='M19,6.41L17.59,5L12,10.59L6.41,5L5,6.41L10.59,12L5,17.59L6.41,19L12,13.41L17.59,19L19,17.59L13.41,12L19,6.41Z'></path>"
					+"</svg>"
				+"</button>"
			+"</div>";
$(".tag__box .tag__btn-tag").before(html);
}
function upload_file() {
    $('#form_upload').ajaxSubmit({            
        type: 'post',
        url :ctxPath+"/upload/fileUpload",
        dataType:"json",
        beforeSubmit:function(){
        	$(".form-cover-desc-box .select-cover__loading__mask").css("visibility","visible");
        },
        success: function(data) {
        	 $(".form-cover-desc-box .js_error_msg").css("display","none").text("");
        	if("10"==data.resultCode){
        		var path = data.returnResult.result;
            	$("#input-fileUpload-path").val(path);
            	$(".form-cover-desc-box .select-cover__preview").css("background-image","url("+path.replace(/\\/g,"/")+")").css("display","block");
        	}else{
        		$(".form-cover-desc-box .js_error_msg").css("display","block").text(data.resultMessage);
        	}
        	
        },
        complete:function(){
        	$(".form-cover-desc-box .select-cover__loading__mask").css("visibility","hidden");
        },
        error:function(data){
        	var msg = data.returnResult.result;
        	$(".form-cover-desc-box .select-cover__loading__mask").css("visibility","hidden");
        	$(".form-cover-desc-box .js_error_msg").css("display","block").text(msg);
        }
    });
 return false; // 阻止表单自动提交事件
}