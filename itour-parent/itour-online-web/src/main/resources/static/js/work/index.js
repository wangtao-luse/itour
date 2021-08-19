$(function(){
	 $("#topstoryContent").on("click","button.work_like_btn",function(){
	    	var tid = $(this).attr("tid");
	    	var has = $(this).hasClass("nice");
	    	var status="";
	    	if(has){
	    		$(this).removeClass("nice");
	    		status="0";
	    	}else{
	    		$(this).addClass("nice");
	    		status="1";
	    	}
	    	
	    	var data={tid:tid,status:status};
	    	postAjax("/work/likeSub", JSON.stringify(data), function (result) {
	    		console.log(tid);
	        }, {errorFunction:function(result){
	        	alert(result.resultMessage);
	        },cache: false, async: false});
	    })	
});
	var current=1;
	function loadMore(){
		current=current+1;
		var postDate = {"current":current,"size":"10"};
		queryInfo(postDate);
	}
	
	//默认查询
	function queryInfo(postData){
		var url="/index?ajaxCmd=content";
		postForm(url, postData, function(result){
			if(result){
				$("#topstoryContent .topstory-recommend div:first").append(result);
			}
			
		},{});
	}
	
	//https://www.jianshu.com/p/05ef1dd140e4
	//https://www.jb51.net/article/104904.htm
	//https://bbs.csdn.net/topics/392344151
	/**
	 * 判断元素span在不在可见窗口内
	 * 在返回true;不在返回false;
	 */
	/*function loadMore(node){
		//窗口滚动的高度
		var scrollTop = $(window).scrollTop();
		//窗口高度+滚动高度(元素刚好要进入窗口)
		var s_rTop = $(window).height()+scrollTop;
		//元素底部到文档顶部的距离
		var offsetTop = node.offset().top;
		//元素底部到刚好出窗口的上边（元素刚好要离开窗口）
		var e_rTop = offsetTop+node.outerHeight();
		if(offsetTop<s_rTop&&scrollTop<e_rTop){
			return true;
		}else{
			return false;
		}
		
	}
	var send=true;
	var current=1;
	function loadInfo(){
	  if(send){
		  send=false;
		  current++;
			var url="/index?ajaxCmd=content";
			$.ajax({
				url:url,
				type:'get',
				data:{"current":current,"size":"12"},
				success:function(result){
					console.log("result :::-->"+result);
					$("#storyContent .story-recommend div").append(result);
				}
			}).done(function(result){
				if(result.status=0){
					send=true;
					if(loadMore($(".load-more"))&&send){
						loadInfo();
					}
				}
				
			}); 
	  }
		
	}*/
/*	//滚动监听事件
	$(window).scroll(function () {
	//滚动条在Y轴上的滚动距离
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    console.log("widownscrollTop: "+scrollTop+" documentHeight: "+scrollHeight+" windowHeight： "+windowHeight);
    if (scrollTop + windowHeight == scrollHeight) {
    	var data={"page":{"current":"1","size":"2"}}
    	queryInfo({});
    	
    }
    var isload = loadMore($(".load-more"));
    if(!isload&&send){
    	//queryInfo({});
    	loadInfo();
    }
    
  });
	//推荐
	$(".story-tabCard .story_scenic_spot").click(function(){
		$(".story-tabCard .story_tabs a").removeClass("is--active");
		$(this).addClass("is--active");
		var postData={"size":12,"current":1};
		current=1;
		//queryInfo(postData);
	});
	//攻略
	$(".story-tabCard .story-recomend").click(function(){
		$(".story-tabCard .story_tabs a").removeClass("is--active");
		$(this).addClass("is--active");
		var postData={"size":12,"current":1};
		current=1;
		queryInfo(postData);
	});
	//点赞
	$("#storyContent").on("click",".listShortcut .voteButton--up",function(){
		var c = $(this).hasClass("is-active");
		if(c){
			var alias = $(this).attr("alias");
		    var alia = parseInt(alias)-1;
		    $(this).attr("alias",alia);
		    $(this).text("赞同 "+alia);
			$(this).removeClass("is-active");
		}else{
			var alias = $(this).attr("alias");
		    var alia = parseInt(alias)+1;
		    $(this).attr("alias",alia);
		    $(this).text("已赞同 "+alia);
			$(this).addClass("is-active");
		}
		 var data= {"url":urlstr};
	        postAjax("/travel/thumbUp", JSON.stringify(data), function (result) {
	        }, {errorFunction:function(result){},cache: false, async: false,"contentType": "application/json; charset=utf-8"});
	});
	//分享
	$("#storyContent").on("click",".shareBtn",function(e){
		$(".shareBtn").parent().find(".menu").hide();
		$(this).parent().find(".menu").show();
		 $(document).one('click',function(){
		  $(".shareBtn").parent().find(".menu").hide();
         })
         e.stopPropagation();stopPropagation();方法可以阻止把事件分派到其他节点
		
	});
	//关闭浮出层
	$(document).on("click",".Modal-closeButton",function(){
		$(".Modal-wrapper").hide();
	});
	//收藏弹出框
	$(document).on("click",".favoriteButton,.ModalButtonGroup .button-box",function(){
		 var dxUrl = "/favoriteModal";
		    $("#favoriteBox").load(dxUrl, function () {
		        $(".Modal-wrapper").show();
		    });
	});
	//创建收藏夹弹出框
	$(document).on("click",".Favlists-addButton",function(){
		 var dxUrl = "/favoriteAddModal";
		    $("#favoriteBox").load(dxUrl, function () {
		        $(".Modal-wrapper").show();
		    });
	});*/
	
	$("#comment_content").on("click",".btn-comment",function(){
		//$(this).removeClass("open");
	});	


