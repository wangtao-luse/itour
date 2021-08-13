var mdEditer;
	$(function(){
		//markdown编辑器
		 mdEditer=editormd("edit-md-area",{
			width:1000,//设置编辑器的宽度
			height:720,//设置编辑器的高度
			path: ctxPath+"/md/lib/",
			watch:false,//关闭实时预览
			saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
			emoji:true,
			//图片上传
	        imageUpload : true,
	        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp",
	        				"JPG", "JPEG", "GIF", "PNG", "BMP", "WEBP"
	        			   ],
	        imageUploadURL : ctxPath+"/upload/multipartFileUpload",
	        onload : function() {
               
            }
      
		});
		 //获取编辑器文本的长度
		$("#edit-md-area").keyup(function(){
			var len =mdEditer.getMarkdown().length;
			$("#bottom_main .fold_tips_value").text(len);
		});
		
		
	//保存草稿,预览，保存		
	$("#js_send,#js_submit,#js_preview").click(function(){
		var $this = $(this);
		var text = mdEditer.getMarkdown();
		var tid = $("#tid").val();
		var title = $(".article-title-text").val();
		var summary = $("#js_description").val();
		var url = $("#input-fileUpload-path").val();
		var articleType = $("#ori-setting").val();
		var cityCode=$("#cityCode").val();
		var preview = $(this).attr("fun");
        var tag_arr=[];
		var tags = $("#tag-container .weui-desktop-form-tag-control .weui-desktop-form-tag .weui-desktop-form-tag__name");
		tags.each(function(i,item){
			    var v = $(this).text().trim();
			    tag_arr.push(v);
		});
		var col_arr=[];		
		var cols = $("#column-container .weui-desktop-form-tag-control .weui-desktop-form-tag .weui-desktop-form-tag__name");
		cols.each(function(i,item){
			    var c = $(this).text().trim();
			    col_arr.push(c);
		});
		if($.isEmpty(title)||$.isEmpty(text)){
			$("#js_editor_area").css("display","block");
			$(document).scrollTop(0);
			return;
		}
		if($.isEmpty(url)){
			var target = $("#js_cover_description_area").offset().top;
			$(document).scrollTop(target);
			$("#js_cover_description_area .js_error_msg").css("display","block").text("必须上传一张图片");
			return;
		}
		if($.isEmpty(cityCode)){
			$("#js_save_fail .inner").text("请选择攻略的所在城市");
			$("#js_save_fail").css("display","block");
			setTimeout(function(){
	    		$("#js_save_fail").css("display","none");
	    	},5000);
        	
		}
		var btn = $(this).attr("id");
		var fun="";
		if(btn=="js_submit"){
			fun="20";
		}else if(btn=="js_send"){
			fun="10";
		}
	    var data= {
	    		"vo":{
	    			"id":tid,
	    			"title":title,
	    			"summary":summary,	    			
	    			"url":url,
	    			"articleType":articleType,
	    			"code":cityCode
	    		}, 
	    		"markdown":text,
	    		"tag_arr":tag_arr,
	    		"col_arr":col_arr,
	    		"preview":preview,
	    		"function":fun
	    		
	    };
	    checkWeekTravel()&&postAjax("/travel/insertweekTravel", JSON.stringify(data), function (result) {
	    	var data = result.returnResult;
	    	$("#tid").val(data.id);
	    	var v = $($this).attr("id");
	    	if(v=="js_preview"){
	    		var url = ctxPath+"/travel/detail?id="+data.id;
	    		$("#link-preview").attr("href",url);
	    		$('#preview-link').trigger("click");
	    	}else{
	    		$("#js_save_success").css("display","block");
		    	$("#js_save_success .inner");
	    	}
	    	
	    	setTimeout(function(){
	    		$("#js_save_success").css("display","none");
	    	},3000);
        }, {errorFunction:function(result){
        	$("#js_save_fail").css("display","block");
        	$("#js_save_fail .inner").text(result.resultMessage);
	    	setTimeout(function(){
	    		$("#js_save_fail").css("display","none");
	    	},5000);
        },cache: false, async: false,"contentType": "application/json; charset=utf-8"});

	});
	//点击话题标签checkbox图标
	$("#js_article_tags_area .frm_checkbox_label .icon_checkbox").click(function(){
		var o =$("#js_article_tags_area .frm_checkbox_label");
		var has =o.hasClass("selected");
		 if(has){
			 o.removeClass("selected");
			 $("#js_article_tags_area .js_article_tags_content").text("最多可以指定三个标签");
		 }else{
			o.addClass("selected");
			$("#js_article_tags_area .frm_checkbox_label .allow_click_opr").trigger("click");
		 }
	});
	//话题标签
	$("#js_article_tags_area .frm_checkbox_label .allow_click_opr").click(function(e){
		 var dxUrl = ctxPath+"/travel/tag";		
		    $("#tag-container").load(dxUrl, function () {
		    	var tags_text = $("#js_article_tags_area .js_article_tags_content").text();
		    	var tobject =$("#tag-container .weui-desktop-form__check-label");
		    	
		    	if(tags_text&&tags_text.indexOf("最多可以指定三个标签")==-1){
		    		
		    		 var t_arr = tags_text.split(",");
		    		 console.log(t_arr);
		    		 for(var i = 0; i < t_arr.length; i++){
		    			 //1.追加标签
			    		  appendTag(t_arr[i],$(this));
			    		  //2.选择标签
			    		  tobject.each(function(j){
			    			  var v =$(this).find(".weui-desktop-form__check-content").text();
			    			  console.log(typeof t_arr[i] + " t_arr[i] "+t_arr[i]+typeof v + " v "+v );
				    			 if(t_arr[i]==v){
				    				 $(this).find(".weui-desktop-form__checkbox").prop("checked","checked");
				    			 }
				    		 });
			    		}
		    		 var tags = $("#tag-container .article_tags_history_bd .weui-desktop-form__checkbox:checked");
			    	 var disTags =$("#tag-container .article_tags_history_bd .weui-desktop-form__checkbox:not(:checked)");
		    		 if(tags.length>=3){
		    		     	disTags.prop("disabled","disabled");
		    		     	$("#tag-container .weui-desktop-form-tag__input").attr("placeholder","");
		    		     }else{
		    		     	disTags.prop("disabled","");
		    		     	$("#tag-container .weui-desktop-form-tag__input").attr("placeholder","请输入话题，按回车分割");
		    		     }
		    		
		    	}
		    	var disaled_btn =$("#tag-container .article_tags_history_bd .weui-desktop-form__checkbox:checked");
		    	if(disaled_btn.length>0){
		    	    $("#tag-container .weui-desktop-btn.weui-desktop-btn_primary").removeClass("weui-desktop-btn_disabled");
		    	}
		    	$("#tag-container .weui-desktop-wrp").show();  
		    });
	});
	//话题标签提交
	$(document).on("click","#btn_tag_sub",function(){
		var arr=[];		
		var tags = $("#tag-container .weui-desktop-form-tag-control .weui-desktop-form-tag .weui-desktop-form-tag__name");
		tags.each(function(i,item){
			var tag ={};
			    tag.tag=$(this).text();
			arr.push(tag);
		});
		var data={"arr":arr};
		postAjax("/travel/inserTravelTag", JSON.stringify(data), function (result) {
			var tag_arr=[];
        	$("#tag-container .weui-desktop-form-tag__name").each(function(i){
        		tag_arr[i]=$(this).text();
        	});
        	$("#js_article_tags_area .js_article_tags_content").text(tag_arr.join(","));
        	$("#js_article_tags_area .frm_checkbox_label").addClass("selected");
        	$(".weui-desktop-dialog__close-btn").trigger("click");
        }, {errorFunction:function(result){
        	alert(result.resultMessage);
        },cache: false, async: false,"contentType": "application/json; charset=utf-8"});

		
	});
	//分类专栏checkbox图标
	$("#js_article_option_area .frm_checkbox_label .icon_checkbox").click(function(){
		var o =$("#js_article_option_area .frm_checkbox_label");
		var has =o.hasClass("selected");
		 if(has){
			 o.removeClass("selected");
			 $("#js_article_option_area .article_url_setting").text("最多可以设置三个分类专栏");
		 }else{
			o.addClass("selected");
			$("#js_article_option_area .frm_checkbox_label .allow_click_opr").trigger("click");
		 }
	});
	//分类专栏
	$("#js_article_option_area .frm_checkbox_label .allow_click_opr").click(function(e){
		
		var dxUrl = ctxPath+"/travel/column";
		$("#column-container").load(dxUrl, function () {
			var cols_text = $("#js_article_option_area .article_url_setting").text();
	    	var tobject =$("#column-container .weui-desktop-form__check-label");
	    	if(cols_text&&cols_text.indexOf("最多可以设置三个分类专栏")==-1){
	    		 var c_arr = cols_text.split(",");
	    		 console.log(c_arr);
	    		 for(var i = 0; i < c_arr.length; i++){
	    			 //1.追加标签
		    		  appendTag(c_arr[i],$(this));
		    		  //2.选择标签
		    		  tobject.each(function(j){
		    			  var v =$(this).find(".weui-desktop-form__check-content").text();
			    			 if(c_arr[i]==v){
			    				 $(this).find(".weui-desktop-form__checkbox").prop("checked","checked");
			    			 }
			    		 });
		    		}
	    		 var cols = $("#column-container .article_tags_history_bd .weui-desktop-form__checkbox:checked");
	    		 var discols =$("#column-container .article_tags_history_bd .weui-desktop-form__checkbox:not(:checked)");
	    		 if(cols.length>=3){
	    			 discols.prop("disabled","disabled");
	    		     	$("#column-container .").attr("placeholder","");
	    		     }else{
	    		    	 discols.prop("disabled","");
	    		     	$("#column-container .weui-desktop-form-tag__input").attr("placeholder","请输入话题，按回车分割");
	    		     }
	    		
	    	}
	    	var disabled_text= $("#column-container .article_tags_history_bd .weui-desktop-form__checkbox:checked");
	    	if(disabled_text.length>0){
	    		 $("#column-container .weui-desktop-btn.weui-desktop-btn_primary").removeClass("weui-desktop-btn_disabled");
	    	}
			$("#column-container .weui-desktop-wrp").show();
		});
	});
	//分类专栏提交
	$(document).on("click","#btn-col-sub",function(){
		var arr=[];		
		var cols = $("#column-container .weui-desktop-form-tag-control .weui-desktop-form-tag .weui-desktop-form-tag__name");
		cols.each(function(i,item){
			var col ={};
			    col.column=$(this).text();
			arr.push(col);
		});
		var data={"arr":arr};
		postAjax("/travel/inserTravelCol", JSON.stringify(data), function (result) {
			var col_arr=[];
        	$("#column-container .weui-desktop-form-tag__name").each(function(i){
        		col_arr[i]=$(this).text();
        	});
        	$("#js_article_option_area .article_url_setting").text(col_arr.join(","));
        	$("#js_article_option_area .frm_checkbox_label").addClass("selected");
        	$(".weui-desktop-dialog__close-btn").trigger("click");
        }, {errorFunction:function(result){
        	alert(result.resultMessage);
        },cache: false, async: false,"contentType": "application/json; charset=utf-8"});
	});
	//标题文字长度提示
	$(".article-title-wrp .article-title-text").keyup(function(){
		var len =$(this).val().length;
		$(".article-title-wrp .suffix").text(len+"/100");
		if(len>100){
			$(".article-title-wrp .suffix").addClass("error");
		}else{
			$(".article-title-wrp .suffix").removeClass("error");
		}
		
		
	});
	//简介文字长度提示
	$("#js_description").keyup(function(){
		var len =$(this).val().length;
		$("#js_description_area .frm_counter").text(len+"/120");
		if(len>120){
			$("#js_description_area .frm_counter").addClass("error");
		}else{
			$("#js_description_area .frm_counter").removeClass("error");
		}
		
	});
	
	
	$("#js_original").on("click",".weui-desktop-switch__input",function(){
		var c = $(".weui-desktop-switch__input").is(":checked");
		if(c){
			$("#ori-setting").val("1");
			$(this).parent().next("div").text("原创");
		}else{
			$("#ori-setting").val("2");
			$(this).parent().next("div").text("未声明原创");
		}
		
	});
	$("#js_editor_area").on("click",".msg_closed.js_msg_close",function(){
		$("#js_editor_area").css("display","none");
	});
	//城市设置
	$("#js_article_city_area .frm_checkbox_label .allow_click_opr").click(function(){
		var dxUrl = ctxPath+"/travel/cityPage";
		$("#city-container").load(dxUrl,function(){
			$("#city-container .weui-desktop-wrp").show();
			
		});
	});
	//城市设置
	$("#city-container").on("click","#btn-city-sub",function(){
		var cityCode = $("#city-select").val();
		var cityName =$("#city-select option:selected").text();
		$("#js_article_city_area .lbl_content_desc").text(cityName);
		$("#js_article_city_area #cityCode").val(cityCode);
		$("#js_article_city_area .frm_checkbox_label").addClass("selected");
		$("#city-container .weui-desktop-wrp").hide();
	});
});
	
function checkWeekTravel(){
	var text = mdEditer.getHTML();
	var title = $(".article-title-text").val();
	if($.isEmpty(text)){
		return false;
	}
	if($.isEmpty(title)){
		return false;
	}
	return true;
}
//文件上传
function upload_file() {
        $('#form_upload').ajaxSubmit({            
            type: 'post',
            url :ctxPath+"/upload/fileUpload",
            dataType:"json",
            beforeSubmit:function(){
            	$("#js_cover_area .select-cover__loading__mask").css("visibility","visible");
            },
            success: function(data) {
            	$("#js_cover_area .select-cover__loading__mask").css("visibility","hidden");
            	$("#js_cover_description_area .js_error_msg").css("display","none").text("");
            	if("10"==data.resultCode){
            		var path = data.returnResult.result;
                	$("#input-fileUpload-path").val(path);
                	$("#js_cover_area .select-cover__preview").css("background-image","url("+path.replace(/\\/g,"/")+")").css("display","block");
            	}else{
            		$("#js_cover_description_area .js_error_msg").css("display","block").text(data.resultMessage);
            	}
            	
            },
            error:function(data){
            	var msg = data.returnResult.result;
            	$("#js_cover_area .select-cover__loading__mask").css("visibility","hidden");
            	$("#js_cover_description_area .js_error_msg").css("display","block").text(msg);
            }
        });
     return false; // 阻止表单自动提交事件
}

