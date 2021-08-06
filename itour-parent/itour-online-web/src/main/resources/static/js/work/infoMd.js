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
})
function showTip(msg){
	$(".notice-box .notice").text(msg);
	$(".notice-box").show();
	setTimeout(function(){
		$(".notice-box").hide();
	},3000);
}