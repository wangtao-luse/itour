$(function(){
	//queryInfo({});
	
	$(window).scroll(function () {
		//滚动条在Y轴上的滚动距离
	    var scrollTop = $(this).scrollTop();
	    var scrollHeight = $(document).height();
	    var windowHeight = $(this).height();
	    console.log("scrollTop: "+scrollTop+" scrollHeight: "+scrollHeight+" windowHeight： "+windowHeight);
	    if (scrollTop + windowHeight == scrollHeight) {
	    	var data={"page":{"current":"1","size":"10"}}
	    	queryInfo({});
	    }
	  });
	
	
	
	
	//推荐
	$(".story-tabCard .story_scenic_spot").click(function(){
		$(".story-tabCard .story_tabs a").removeClass("is--active");
		$(this).addClass("is--active");
		var postData={};
		queryInfo(postData);
	});
	//攻略
	$(".story-tabCard .story-recomend").click(function(){
		$(".story-tabCard .story_tabs a").removeClass("is--active");
		$(this).addClass("is--active");
		var postData={};
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
	function queryInfo(postData){
		var url="/index?ajaxCmd=content";
		postForm(url, postData, function(result){
			$("#storyContent").html(result);
		},{});
	}
	//https://www.jianshu.com/p/05ef1dd140e4
	//https://www.jb51.net/article/104904.htm
	//https://bbs.csdn.net/topics/392344151
	//判断元素span在不在可是窗口内
	function loadMore(node){
		//滚动的高度
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
	
	function loadInfo(){
		if(send){
			send=false;
			var url="/index?ajaxCmd=content";
			$.ajax({
				url:url,
				type:'get',
				data:{"dt":start},
				dataType:'json'
			}).done(function(result){
				
			});
		}
	}
});
