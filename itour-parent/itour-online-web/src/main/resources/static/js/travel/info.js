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
	//保存草稿
	$("#js_send").click(function(){
		var text = mdEditer.getHTML();
		var title = $(".article-title-text").val();
		var summary = $("#js_description").val();
		var url = $("#input-fileUpload-path").val();
		var articleType = $("#ori-setting").val();
        var tag_arr=[];
		var tags = $("#tag-container .weui-desktop-form-tag-control .weui-desktop-form-tag .weui-desktop-form-tag__name");
		tags.each(function(i,item){
			var tag ={};
			    tag.tag=$(this).text();
			    tag_arr.push(tag);
		});
		var col_arr=[];		
		var cols = $("#column-container .weui-desktop-form-tag-control .weui-desktop-form-tag .weui-desktop-form-tag__name");
		cols.each(function(i,item){
			var col ={};
			    col.column=$(this).text();
			    col_arr.push(col);
		});
	    var data= {"markdown":text,"title":title,"summary":summary,"url":url,"articleType":articleType,"tag_arr":tag_arr,"col_arr":col_arr};
	    checkWeekTravel()&&postAjax("/travel/insertweekTravel", JSON.stringify(data), function (result) {
        }, {errorFunction:function(result){
        	
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
		    	$("#tag-container .dialog-wrapper").show();  
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
			$("#column-container .dialog-wrapper").show();
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
	
	$("#js_cover_area .input-fileUpload").change(function(){/*
		postAjax("/upload/multipartFileUpload", JSON.stringify(data), function (result) {
			alert(result.resultMessage);
        }, {errorFunction:function(result){
        	alert(result.resultMessage);
        },cache: false, async: false,"contentType": "application/x-www-form-urlencoded"});
	*/});
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

function upload_file() {
        $('#form_upload').ajaxSubmit({            
            type: 'post',
            url :ctxPath+"/upload/fileUpload",
            success: function(data) {
            	if(data.success=='1'){
            		alert(data.message);
            	}
            }
        });
     return false; // 阻止表单自动提交事件
}