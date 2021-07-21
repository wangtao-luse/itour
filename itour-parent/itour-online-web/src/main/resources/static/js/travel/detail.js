$(function(){
	query(new Array());
	$(document).on("mouseenter","article .contentItem-action.button.link-btn",function(){
		$(this).find(".isdefault").css("display","none");
		$(this).find(".isactive").css("display","inline");
		$(this).css("color","#0077E6");
	});
	$(document).on("mouseleave","article .contentItem-action.button.link-btn",function(){
		$(this).find(".isdefault").css("display","inline");
		$(this).css("color","#646464");
		$(this).find(".isactive").css("display","none");
	});
	
	$(".contentItem-action.travel_nice_btn").mouseout(function(){
		var has = $(this).hasClass("nice");
		if(!has){
			$(this).find(".isactive").css("display","none");
			$(this).find(".isdefault").css("display","inline");
			$(this).css("color","#646464");
			
		}
		
	})
	$(".contentItem-action.travel_nice_btn").mouseover(function(){
		$(this).find(".isdefault").css("display","none");
		$(this).find(".isactive").css("display","inline");
		$(this).css("color","#0077E6");
	})
	$(".contentItem-action.travel_nice_btn").click(function(){
		var has = $(this).hasClass("nice");
		if(has){
			$(this).find(".isactive").css("display","none");
			$(this).find(".isdefault").css("display","inline");
			$(this).css("color","#646464");
			$(this).removeClass("nice");
			return;
		  }
		   //样式调整
			$(this).find(".isdefault").css("display","none");
			$(this).find(".isactive").css("display","inline");
			$(this).css("color","#0077E6");
            $(this).addClass("nice");
            var vi =  $(this).find(".isdefault").is(":visible");
			var tid = $(this).attr("tid");
	    	var status="";
	    	if(vi){
	    		status="0";
	    	}else{
	    		status="1";
	    	}
	    	
	    	var data={tid:tid,status:status};
	    	postAjax("/niceSub", JSON.stringify(data), function (result) {
	    		console.log(tid);
	        }, {errorFunction:function(result){
	        	alert(result.resultMessage);
	        },cache: false, async: false});
	})
	$(document).on("click","#comment-form",function(e){
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
			$(".commentListV2 .comment-box.comment-edit-box").remove();
		})
		e.stopPropagation();
		
	});
   
	$(document).on("keyup","#comment_content",function(){
		var len = $(this).val().length;
		var commentLen = parseInt(1200)-parseInt(len);
		$("#comment-form .right-box em").text(commentLen);
	});
	$(document).on("keyup","#comment_contentNew",function(){
		var len = $(this).val().length;
		var commentLen = parseInt(1200)-parseInt(len);
		$("#commentformNew .right-box em").text(commentLen);
		
	});
	//评论回复
	$(document).on("click",".commentRely",function(){
		var $this=$(this);
		 var commentId = $(this).attr("comment_id");
		 var toNickname = $(this).attr("to_nickname");
		 var fromUid = $(this).attr("from_uid");
		 var toUid = $(this).attr("to_uid");
		var data={commentId:commentId,toNickname:toNickname,fromUid:fromUid,toUid:toUid};
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
	});
	
	$(document).on("click",".commentMoreReplyButton .commentMore_btn",function(){
		$(this).closest(".nestComment").find(".nestComment--child").removeClass("hide");
		$(this).hide();
	});
	//删除评论
	$(document).on("click",".commentItemV2-footer .delete_comment_btn",function(){
		if(confirm("你确定要删除此评论吗？")){
			var cid = $(this).attr("cid");
			var data={id:cid};
			postAjax("/travel/delComment", JSON.stringify(data), function (result) {
				query(new Array());
		    }, {errorFunction:function(result){
		    	console.log(result);
		    },cache: false, async: false});
		}
		
	})
	//删除评论回复
	$(document).on("click",".commentItemV2-footer .del-commentRely-btn",function(){
		if(confirm("你确定要删除此评论吗？")){
			var rid = $(this).attr("rid");
			var data={id:rid};
			postAjax("/travel/delCommentReply", JSON.stringify(data), function (result) {
				query(new Array());
			}, {errorFunction:function(result){
				console.log(result);
			},cache: false, async: false});
		}
	});
	//评论分页
	$(document).on("click",".Pagination.CommentsV2-pagination button",function(){
		var pageNo = $(this).attr("pageNo");
		var id=$("#tid").val();
		var data={"id":id,"page":{"current":pageNo}};
		var url="/travel/commentList?ajaxCmd=commentList";
		postForm(url, JSON.stringify(data), function (result) {
			$("#comment-container").html(result);
	      }, {"contentType": "application/json; charset=utf-8"});
	})
	//评论点赞
	$(document).on("click",".commentItemV2-footer .comment-nice-btn",function(){
		var cid = $(this).attr("cid");
		var uid = $(this).attr("uid");
		var isactive = $(this).find("img.isactive").is(":visible");
		var status;
		var nice =$(this).find(".nice-count").text();
		 var vv =nice.replace(/\s*/g,"")
		var txt = parseInt(Boolean(vv)?nice:"0");
		if(isactive){//取消点赞
			$(this).find("img.isactive").css("display","none");
			$(this).find("img.isdefault").css("display","block");
			status="0";
			var v =txt-parseInt("1");
			$(this).find(".nice-count").text(v>0?v:"");
		}else{//点赞
			$(this).find("img.isactive").css("display","block");
			$(this).find("img.isdefault").css("display","none");
			status="1";
			var v = txt+parseInt("1");
			$(this).find(".nice-count").text(v>0?v:"");
		}
		var data={"cid":cid,"uid":uid,"status":status};
		postAjax("/travel/commentNice", JSON.stringify(data), function (result) {
		}, {errorFunction:function(result){
			console.log(result);
		},cache: false, async: false});
	})
	//评论回复点赞
	$(document).on("click",".commentItemV2-footer .commentReply-nice-btn",function(){
		var rid = $(this).attr("rid");
		var uid = $(this).attr("uid");
		var isactive = $(this).find("img.isactive").is(":visible");
		var status;
		var nice =$(this).find(".nice-count").text().replace(/\s*/g,"")
		if(isactive){//取消点赞
			$(this).find("img.isactive").css("display","none");
			$(this).find("img.isdefault").css("display","block");
			status="0";
			var txt = parseInt(nice?nice:"0")-parseInt("1");
			$(this).find(".nice-count").text(txt>0?txt:"");			
		}else{//点赞
			$(this).find("img.isactive").css("display","block");
			$(this).find("img.isdefault").css("display","none");
			status="1";
			var txt = parseInt(nice?nice:"0")+parseInt("1");
			
			$(this).find(".nice-count").text(txt>0?txt:"");
		}
		var data={"rid":rid,"uid":uid,"status":status};
		postAjax("/travel/commentReplyNice", JSON.stringify(data), function (result) {
		}, {errorFunction:function(result){
			console.log(result);
		},cache: false, async: false});
	})
	//编辑信息
	$(document).on("click",".travel_area .travel_edit_btn",function(){
		var id = $("#tid").val();
		location.href=ctxPath+"/travel/updateMd?id="+id;
	})
	//评论排序
	$(document).on("click","#topbar-options-btn",function(){
		var order = $("#order").val();
		var arr = new Array();
		var orderby = {};
		    orderby.sortType="CTIME";
		    orderby.sortRule=order;
		    arr.push(orderby);
		if("1"==order){
			$("#order").val("0");
			$(this).text("切换为默认的排序方式");
			query(arr);
		}else if("0"==order){
			$("#order").val("1");
			$(this).text("切换为时间升序排序");
			query(arr);
		}
	});
	//获取指定元素在y轴上偏移量
	var offsetTop = $(".fixed-line").offset().top;
	//获取浏览器可见高度
	var wheight = $(window).height();
	if(offsetTop>=wheight){
		$(".contentItem-actions").addClass("is-fixed sticky");
		$(".is-fixed.sticky").css("width", $(".storyCard").outerWidth()+"px")
		.css("left",$(".storyCard").offset().left+"px");
		$(".is-fixed.sticky").css("bottom",0);
	}
	console.log("offsetTop: "+offsetTop);
	$(document).scroll(function() {
		//https://www.cnblogs.com/yuqiandoudou/p/4436368.html
		//1.获取垂直滚动的距离
		  //scrollTop()==0的时候就是顶端了;
		  //scrollTop()>=$(document).height()-$(window).height()  就可以知道已经滚动到底端了
		//获取垂直滚动的距离
		var top = $(document).scrollTop();
		
		//获取整个页面的高度
		var dheight = $(document).height();
		//计算到底部滚动的距离
		var b = dheight -wheight-90;
		console.log("top: "+top);
		if(top<offsetTop){
			$(".contentItem-actions").removeClass("is-fixed sticky")
		}else if(top>=offsetTop&&top<=b){
			$(".contentItem-actions").addClass("is-fixed sticky");
			$(".is-fixed.sticky").css("width", $(".storyCard").outerWidth()+"px")
			.css("left",$(".storyCard").offset().left+"px");
			$(".is-fixed.sticky").css("bottom",0);
		}else if(top>b){
			$(".contentItem-actions").addClass("is-fixed sticky");
			$(".is-fixed.sticky").css("width", $(".storyCard").outerWidth()+"px")
			.css("left",$(".storyCard").offset().left+"px");
			$(".is-fixed.sticky").css("bottom","90px");
		}
	});
});
function outMsg(msg,ele){
	$("#ct-out span").text(msg);
	$(ele).css("display","block");
	$("#ct-out").css("display","block");
	setTimeout(function(){
		$(ele).css("display","none");
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
		outMsg("请填写评论内容","#ct-mask");
		return;
	}
       
	var data={"tid":tid,"uid":comment_userId,"comment":comment_content};
	postAjax("/travel/insertComment", JSON.stringify(data), function (result) {
		$("#comment_content").val("");
		outMsg("评论成功,审核后显示","#ct-mask");
		query(new Array());
    }, {errorFunction:function(result){
    	console.log(result);
    },cache: false, async: false});
}

function closeMask($this){
	var mask = $($this).attr("id");
	$("#"+mask).css("display","none");
}
//获取评论列表
function query(list){
	var pageNo =1;
	var id=$("#tid").val();
	var arr = new Array();
	var orderby = {};
	    orderby.sortType="CTIME";
	    orderby.sortRule="0";
	    arr.push(orderby);
	if(list.length>0){
		arr=list;
	}  
	var order =$("#order").val();
	var data={"id":id,"order":order,"orderbyList":arr,"page":{"current":pageNo}};
	var url="/travel/commentList?ajaxCmd=commentList";
	postForm(url, JSON.stringify(data), function (result) {
		$("#comment-container").html(result);
      }, {"contentType": "application/json; charset=utf-8"});	
}
