	var mdEditer;
	$(function(){
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
	$("#js_send").click(function(){
		var text = mdEditer.getHTML();
		var title = $("#article-title-text").val();
		var summary = $("#js_description").text();
		var url = $("#input-fileUpload-path").val();
		var articleType = $("#ori-setting").val();
	    var data= {"markdown":text,"title":title,"summary":summary,"url":url,"articleType":articleType};
	    checkWeekTravel()&&postAjax("/travel/insertweekTravel", JSON.stringify(data), function (result) {
        	
        }, {errorFunction:function(result){
        	
        },cache: false, async: false,"contentType": "application/json; charset=utf-8"});

	});
	$("#js_article_tags_area .frm_checkbox_label").click(function(){
		 var dxUrl = ctxPath+"/travel/tag";
		    $("#tag-container").load(dxUrl, function () {
		        $("#tag-container .dialog-wrapper").show();
		    });
	});
	$("#js_article_option_area").click(function(){
		var dxUrl = ctxPath+"/travel/column";
		$("#column-container").load(dxUrl, function () {
			$("#column-container .dialog-wrapper").show();
		});
	});
});
function checkWeekTravel(){
	var text = mdEditer.getHTML();
	var title = $("#article-title-text").val();
	if($.isEmpty(text)){
		return false;
	}
	if($.isEmpty(title)){
		return false;
	}
	return true;
}