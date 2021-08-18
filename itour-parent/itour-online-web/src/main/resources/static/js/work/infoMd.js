$(function(){
	/**
	 * 初始化MarkDown
	 */
	var mdEditer=editormd("md-container",{
				autoHeight : false,
				path: ctxPath+"/md/lib/",
				watch:true,
				saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
				emoji:true,
				imageUpload : true,
		        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp",
		        				"JPG", "JPEG", "GIF", "PNG", "BMP", "WEBP"
		        			   ],
		        imageUploadURL : ctxPath+"/upload/multipartFileUpload"
				
			});
	

	//发布文章,保存草稿
	$(".pulish-article-btn,.article-bar__user-box .btn-save").click(function(){
		var has = $(this).hasClass("pulish-article-btn");
		var fun="draft";
		if(has){
			fun="save";
		}
		var id = $("#article-value").val();
		var title = $(".article-bar__input-box .article-bar__title").val();
		var text = mdEditer.getMarkdown();
		var articleType = $("#articleType").val();
		var modality = $("input[name='modality']").val();
		var path = $("#input-fileUpload-path").val();
		var summary = $("#js_description").val();
		if($.isEmpty(title)){showTip("标题不能为空！");return;}
		if(title.length<5){showTip("标题长度必须大于等于5！");return;}
		if($.isEmpty(text)){showTip("内容不能为空！");return;}
		if($.isEmpty(summary)&&has){showTip("请编写摘要！");return;}
		if($.isEmpty(articleType)&&has){showTip("请选择文章类型！");return;}
		
		var url="/work/savaOrUpdateWorkInfo";
		var tagArr=[];
		$(".mark_selection .el-tag").each(function(i,item){
		    var v = $(this).text().trim();
		    tagArr.push(v);
	   });
		var colArr=[];
		$(".tag__box .tag__item-box").each(function(i,item){
		    var v = $(this).find(".tag__name").text().trim();
		    colArr.push(v);
	   });
		var data={"vo":{
			 			"title":title,
			 			"modality":modality,
			 			"articleType":articleType,
			 			"url":path,
			 			"summary":summary,
			 			 "id":id
						},
				   "markdown":text,
				   "tag_arr":tagArr,
				   "col_arr":colArr,
				   "function":fun
		};
		postAjax(url,JSON.stringify(data),function(data){
			$("#article-value").val(data.returnResult.id);
			if(has){
				showTip("发布成功！");
			}else{
				showTip("已保存为草稿！");
			}
			
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
			var check_repeat =true;
			$(".mark_selection .el-tag").each(function(){
				var v =$(this).text().trim();
				if(tag==v){
					check_repeat=false;
				}
				
			})
			if(chlid_len<5 &&check_repeat){
				$(".mark_selection .tag__btn-tag").show();
				appendTag(tag,$(this));
				$(".mark_selection .tag__option-label").each(function(i,item){
					var text = $(this).find(".spanIsAgree").text().trim();
					if(tag==text){
						$(this).find(".tag__option-chk").prop("checked","checked");
					}
				})
			}
			if(chlid_len>=4){
				var uncheked = $(".mark_selection_box .tag__option-chk:not(:checked)");
				uncheked.prop("disabled","disabled");
				$(".mark_selection .tag__btn-tag").hide();
			}
			
		}
	}); 
	//关闭标签
	$(document).on("click",".mark_selection .el-tag__close.el-icon-close",function(){
		$(this).parent().remove();
		var chlid_len = $(".mark_selection .mark_selection_title_el_tag span>span").length;
		var text = $(this).parent().text().trim();
		var uncheked = $(".mark_selection_box .tag__option-chk:not(:checked)");
		if(chlid_len<5){
			$(".mark_selection .tag__btn-tag").show();
			uncheked.prop("disabled","");
			$(".mark_selection .tag__option-label").each(function(i,item){
				var tag = $(this).find(".spanIsAgree").text().trim();
				if(tag==text){
					$(this).find(".tag__option-chk").prop("checked","");
				}
			})
		}
	})
	//标签checkbox检查
	$(document).on("change",".mark_selection_box .tag__option-label",function(e){
				  var checked = $(".mark_selection_box .tag__option-chk:checked");
				  var uncheked = $(".mark_selection_box .tag__option-chk:not(:checked)");
				  var check = $(this).find(".tag__option-chk").is(':checked')
				  var clen =checked.length;
				  var ulen =uncheked.length;
				  var text = $(this).find(".spanIsAgree").text();
				  var tagList = $(".mark_selection .mark_selection_title_el_tag .el-tag");
				 
				  if(clen>=5){				  	
				  	uncheked.prop("disabled","disabled");
				  }else{
				  	uncheked.prop("disabled","");
				  }
				  if(tagList.length<5 && check){
				  	 appendTag(text,$(this));
				  }
				  if(!check){
				  	tagList.each(function(i,item){
				  		var tag =$(this).text().trim();
				  		if(tag==text){
				  			$(this).remove();
				  		}
				  	})
				  }
				 var nlen = $(".mark_selection .mark_selection_title_el_tag .el-tag");
				 if(nlen.length>4){
				  	 uncheked.prop("disabled","disabled");
				  	 $(".mark_selection .tag__btn-tag").hide();
				  }else{
				  	$(".mark_selection .tag__btn-tag").show();
				  }
				});
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
		   var text = $(this).parent().find(".tag__name").text().trim();
		   $(this).parent().remove();
		   var tlen = $(".tag__box .tag__item-list .tag__item-box").length;
		    var uncheked = $(".tag__box .tag__option-chk:not(:checked)");
			if(tlen<3){
				$(".tag__box .tag__btn-tag").show();
				uncheked.prop("disabled","");
				$(".tag__box .tag__option-label").each(function(i,item){
					var tag = $(this).find(".spanIsAgree").text().trim();
					if(text==tag){
						$(this).find(".tag__option-chk").prop("checked","");
					}
				})
			}
		});
	//新建专栏文本框样式
	$(document).on("keyup",".input-text-box .tag-input-text",function(){
		var v = $(this).val();
		var w =$(this).parent().parent().find(".tag__name").text(v).width();
		var txt=$(this).width();
	    this.style.width = w + "px";
		
	});
	//新建专栏文本框样式
	$(document).on("blur",".input-text-box .tag-input-text",function(){
		var v =$(this).val();
		var check_repeat = 0;
		$(".tag__box .tag__item-box").each(function(i,item){
			var tname = $(this).find(".tag__name").text();
			if(tname==v){
				check_repeat += 1;
			}
		});
		if(check_repeat>1){
			$(this).val('');
			$(this).focus();
			return;
		}
		$(this).hide();
		$(this).parent().prev().css("display","block");
		if(!v){
		 $(this).closest(".tag__item-box").remove();	
		}
		//checkbox控制
		$(".tag__box .tag__option-label").each(function(i,item){
			var text = $(this).find(".spanIsAgree").text().trim();
			if(v==text){
				$(this).find(".tag__option-chk").prop("checked","checked");
			}
		});
		
		$(this).parent().remove();
		var tlen = $(".tag__box .tag__item-list .tag__item-box").length;
		if(tlen>=3){
			var uncheked = $(".tag__box .tag__option-chk:not(:checked)");
				uncheked.prop("disabled","disabled");
			$(".tag__box .tag__btn-tag").hide();
			
		}
	});
	//专栏多选框检查
	$(document).on("change",".tag__box .tag__option-label",function(e){
		  var checked = $(".tag__box .tag__option-chk:checked");
		  var uncheked = $(".tag__box .tag__option-chk:not(:checked)");
		  var check = $(this).find(".tag__option-chk").is(':checked')
		  var clen =checked.length;
		  var ulen =uncheked.length;
		  var text = $(this).find(".spanIsAgree").text();
		  var tagList = $(".tag__box .tag__item-list .tag__item-box");
		  if(clen>=3){				  	
		  	uncheked.prop("disabled","disabled");
		  }else{
		  	uncheked.prop("disabled","");
		  }
		  if(tagList.length<3 && check){
		  	 appendCheckboxCol(text);
		  }
		  if(!check){
		  	tagList.each(function(i,item){
		  		var tag =$(this).find(".tag__name").text().trim();
		  		if(tag==text){
		  			$(this).remove();
		  		}
		  	})
		  }
		 var nlen= $(".tag__box .tag__item-list .tag__item-box").length;
		 if(nlen>2){
		  	 uncheked.prop("disabled","disabled");
		  	$(" .tag__box .tag__btn-tag").hide();
		  }else{
			  $(" .tag__box .tag__btn-tag").show();
		  }
		});
	$(document).on("keyup",".modal-article-pushlish-wrp #js_description",function(){
		var len =$(this).val().length;
		$(".modal-article-pushlish-wrp .frm_counter").text(len+"/120");
		if(len>120){
			$(".modal-article-pushlish-wrp .frm_counter").addClass("error");
		}else{
			$(".modal-article-pushlish-wrp .frm_counter").removeClass("error");
		}
	});
	$(document).on("keyup",".mark_selection .mark_selection_box_header .el-input__inner",function(){
		var len =$(this).val().length;
		$(".mark_selection .mark-frm-counter").text(len+"/16");
		if(len>16){
			$(".mark_selection .mark-frm-counter").addClass("error");
		}else{
			$(".mark_selection .mark-frm-counter").removeClass("error");
		}
	});
	$(document).on("keyup",".article-bar__input-box .article-bar__title--input",function(){
		var len =$(this).val().length;
		if(len<5){
			$(this).parent().find(".article-bar__number.default").hide();
			$(this).parent().find(".article-bar__number.tip").text("还需要输入"+(5-len)+"个字").show();
		}else{
			$(this).parent().find(".article-bar__number.tip").hide();
			if(len>100){
				$(this).next().find("span").addClass("error");	
			}
			$(this).next().find("span").text(len);
			$(this).parent().find(".article-bar__number.default").show();
		}
		
	});
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
//追加专栏
function appendCheckboxCol(text){
	var html="<div class='tag__item-box'>"
	            +"<span contenteditable='false' class='tag__name' style='display:block'>"+text+"</span>"
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