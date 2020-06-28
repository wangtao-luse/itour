$(function(){
	$("#loginsubmit").click(function(){
		var url ="/member/loginSub";
		var data=$.serializeObject($('#formlogin'))
		postAjax(url,JSON.stringify(data),function(data){
			location.href="/index";
		},{
			cache:false
		})
	});
});