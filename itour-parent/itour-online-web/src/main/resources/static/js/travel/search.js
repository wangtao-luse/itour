var current = 1;
$(function(){
	$(".banner-search-button").removeClass("button-search-banner");
	var v =$(".searchInput").val();
	$(".banner-search-input").val(v);
	 var pages =$(".pages").val();
	 if(pages>1){
		 $(".text-center.load-more").css("display","block");
	 }
	$(".banner-search-button").click(function(){
		 var s = $(".banner-search-input").val();
		var data={"search":s,"current":"1","size":"10"};
		 query(data);		
	})
	$(document).on("click","#btn-load-more",function(){
		current++;
		 var s = $(".banner-search-input").val();
		var data={"search":s,"current":current,"size":"10"};
		query(data);
	})
	$(".searchTabs-actions .tabs-item").click(function(){
	$(".searchTabs-actions .tabs-item .tabs-link").removeClass("is-active");
	$(this).find(".tabs-link").addClass("is-active");
	var type = $(this).attr("type");
	 var s = $(".banner-search-input").val();
	var data={"search":s,"type":type,"current":"1","size":"10","r":"1"};
	query(data);
})

})

function query(data){
	 var s = $(".banner-search-input").val();
	 var ps =$(".pages").val();
	 if(s&&current<=ps){
		 $(".text-center.nothing").css("display","none");
		 var url ="/travel/search?ajaxCmd=searchcontent";
		 postForm(url, data, function (result) {
			var r= data.r;
			if(r=="1"){
				$(".searchResult-Card").html(result);
			}else{
				$(".searchResult-Card").append(result);
			}
				
				if(current >= ps){
					 $(".text-center.load-more").css("display","none");
					 $(".text-center.nothing").css("display","block");
				}else{
					 $(".text-center.load-more").css("display","block");
				}
		    }, {errorFunction:function(result){
		    },cache: false, async: false,contentType:"application/x-www-form-urlencoded"});
	 }
	 
		
	 
 }