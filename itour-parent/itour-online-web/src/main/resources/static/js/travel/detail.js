$(function(){
	$("#comment-form").click(function(e){
		var hasOpen =$("#comment_contentNew").hasClass("open");
		if(hasOpen){
			$("#comment_contentNew").removeClass("open");
		}
		$(this).find("#comment_content").addClass("open");
		$(document).one("click",function(){
			$("#comment-form").find("#comment_content").removeClass("open");
		})
		e.stopPropagation();
	});
	$(document).on("click","#commentformNew",function(e){
		var hasOpen =$("#comment_content").hasClass("open");
		if(hasOpen){
			$("#comment_content").removeClass("open")
		}
		$(this).find("#comment_contentNew").addClass("open");
		$(document).one("click",function(){
			//$("#commentformNew").find("#comment_contentNew").removeClass("open");
			$(".commentListV2 .comment-box.comment-edit-box").remove();
		})
		e.stopPropagation();
		
	});

	$("#comment_content").keyup(function(){
		var len = $(this).val().length;
		var commentLen = parseInt(1200)-parseInt(len);
		$("#comment-form .right-box em").text(commentLen);
	});
	$("#comment_contentNew").keyup(function(){
		var len = $(this).val().length;
		var commentLen = parseInt(1200)-parseInt(len);
		$("#commentformNew .right-box em").text(commentLen);
		
	});
	
	$(document).on("click",".commentRely",function(){
		var $this=$(this);
		var data={};
		var url="/travel/commentReply?ajaxCmd=commentReply";
		postForm(url, JSON.stringify(data), function (result) {
			$(".commentListV2 .comment-box.comment-edit-box").remove();
		    $($this).closest(".commentItemV2-footer").after(result);
		    $("#commentformNew").trigger("click");
	      }, {"contentType": "application/json; charset=utf-8"});
	});
	$(document).on("click","#commentformNew .btn-cancel",function(e){
		$(".commentListV2 .comment-box.comment-edit-box").remove();
		e.stopPropagation();
	})
	
});
function outMsg(msg){
	$("#ct-out span").text(msg);
	$("#ct-mask").css("display","block");
	$("#ct-out").css("display","block");
	setTimeout(function(){
		$("#ct-mask").css("display","none");
		$("#ct-out").css("display","none");
	},5000);	
}
function comment(){
	console.log("show");
	//评论文章编号
	var tid = $("#article_id").val();
	//评论人的编号
	var comment_userId = $("#comment_userId").val();
	//评论内容
	var comment_content = $("#comment_content").val();
	if($.isEmpty(comment_content)){
		outMsg("请填写评论内容");
		return;
	}
       
	var data={"tid":tid,"uid":comment_userId,"comment":comment_content};
	postAjax("/travel/insertComment", JSON.stringify(data), function (result) {
		$("#comment_content").val("");
		outMsg("评论成功,审核后显示");
    }, {errorFunction:function(result){
    	console.log(result);
    },cache: false, async: false});
}

function closeMask(){
	$("#ct-mask").css("display","none");
	$("#ctn-mask").css("display","none");
}

