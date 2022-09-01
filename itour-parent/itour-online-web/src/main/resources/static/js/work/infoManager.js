$(function(){

      var rpm = $("#rpm").val();
	 queryInfo({"mold":"1","rpm":rpm,"page":{"current":1,"size":10}});
	 
	 $(document).on("click","#dynamic-btn",function(){
		 queryInfo({"mold":"1","rpm":rpm,"page":{"current":1,"size":10}});
	 })
	 $(document).on("click","#article-btn",function(){
		 var arr= new Array();
		 var o1 ={"sortType":"time","sortRule":"0"};
		 arr.push(o1);
		 queryInfo({"mold":"2","rpm":rpm,"orderbyList":arr,"page":{"current":"1","size":"10"}});
	 })
	 $(document).on("click","#collect-btn",function(){
		 var arr= new Array();
		 var o1 ={"sortType":"time","sortRule":"0"};
		 arr.push(o1);
		 var data={"mold":"4","rpm":rpm,"orderbyList":arr,"page":{"current":"1","size":"10"}};
		 queryInfo(data);
	 })
	 $(document).on("click","#draft-btn",function(){
		 var arr= new Array();
		 var o1 ={"sortType":"time","sortRule":"0"};
		 arr.push(o1);
		 queryInfo({"mold":"5","rpm":rpm,"orderbyList":arr,"page":{"current":"1","size":"10"}});
	 })
	 
	 $(document).on("click",".collectionsHeader-addFavlistButton",function(){
		 var url =ctxPath+"/travel/svaeOrUpdateFavoritesP";
		 $(".personCenter_add_collect").load(url,function(result){
			 $(this).show();
			 console.log(result);
		 });
	 })
	 $(document).on("click",".modify-favlist-btn",function(){
		 var id = $(this).attr("cid");
		 var url = ctxPath+"/travel/svaeOrUpdateFavoritesP?id="+id;
		 $(".personCenter_add_collect").load(url,function(result){
			 $(this).show();
			 $(".favlists-descritionInput.input-wrapper input").trigger("keyup");
			 $("#input-tip-favor").hide();
		 });
	 })
	 $(document).on("click",".workInfo_edit_btn",function(){
		var id = $(this).attr("tid");
		location.href=ctxPath+"/work/workUpdateMd?id="+id;
	})
	});
function queryInfo(postData){
	var url="/work/inofMangerList?ajaxCmd=content";
	postForm(url, JSON.stringify(postData), function(result){
		if(result){
			$("#profile-mainColumn").html(result);
		}
	},{"contentType": "application/json; charset=utf-8"});
}
function queryFavListList(postData){
	var url="/travel/favlistPage?ajaxCmd=favaritescontent";
	postForm(url, JSON.stringify(postData), function(result){
		if(result){
			$(".selfCollectionItem").html(result);
		}
	},{"contentType": "application/json; charset=utf-8"});
}
$(document).on("click",".Pagination.CommentsV2-pagination button",function(){
	var pageNo = $(this).attr("pageNo");
	var mold=$("#mold").val();
	var data={"mold":mold,"page":{"current":pageNo,"size":"10"}};
	var url="/work/inofMangerList?ajaxCmd=content";
	postForm(url, JSON.stringify(data), function (result) {
		if(result){
			$("#profile-mainColumn").html(result);
		}
      }, {"contentType": "application/json; charset=utf-8"});
})
$(document).on("click","#percenter-nice-btn",function(){
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
$(document).on("click","#order-toggle",function(){
	var isAsc =$(this).attr("isasc");
	var sortRule ="0";
	if("0"==isAsc){
		sortRule="1";
		isAsc="1";
	}else{
		isAsc="0";
	}
	 var arr= new Array();
	 var o1 ={"sortType":"time","sortRule":sortRule};
	 arr.push(o1);
	 var rpm =$("#rpm").val();
	 queryInfo({"isAsc":isAsc,"mold":"2","rpm":rpm,"orderbyList":arr,"page":{"current":"1","size":"10"}});
})
function dataURLtoFile (dataurl, filename) { 
    var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
}
$(document).on("click",".cacel-favoraties",function(){
	   $(this).closest(".modal-wrapper").hide();
})
$(document).on("click",".createOrUpdate-collect-btn",function(){
	var id = $(this).attr("cid");
	if($.isEmpty(id)){//新增
		 var formData = $.serializeObject($(".favlists-content"));
	     var data ={"vo":formData}
		  postAjax("/travel/insertFavorite", JSON.stringify(data), function (result) {
			  $("#collect-btn").trigger("click");
			  $(".cacel-favoraties").trigger("click");
	      }, {errorFunction:function(result){
	      	alert(result.resultMessage);
	      },cache: false, async: false});
	}else{//修改
		 var formData = $.serializeObject($(".favlists-content"));
	     var data ={"vo":formData}
		  postAjax("/travel/updateFavortie", JSON.stringify(data), function (result) {
			  $("#collect-btn").trigger("click");
			  $(".cacel-favoraties").trigger("click");
	      }, {errorFunction:function(result){
	      	alert(result.resultMessage);
	      },cache: false, async: false});
	}
})


