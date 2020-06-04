$(function(){
	$("#loginsubmit").click(function(){
		var url ="/account/regSub";
		var data=$.serializeObject($('#formlogin'))
		postAjax(url,JSON.stringify(data),function(data){},{
			cache:false
		})
	});
});