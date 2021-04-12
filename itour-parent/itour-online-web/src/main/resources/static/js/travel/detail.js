$(function(){
	$(document).on("click","#comment-form .btn-comment",function(){
		console.log("show");
		//$(this).removeClass("open");
		//评论文章编号
		var tid = $("#article_id").val();
		//评论人的编号
		var comment_userId = $("#comment_userId").val();
		//评论内容
		var comment_content = $("#comment_content").val();
	       
		var data={"tid":tid,"uid":comment_userId,"comment":comment_userId};
		postAjax("/travel/insertComment", JSON.stringify(data), function (result) {
		
        }, {errorFunction:function(result){
        	
        },cache: false, async: false});
	});
});