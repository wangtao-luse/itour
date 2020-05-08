$(function(){
$("#form-register").click(function(){
	var url="/account/regSub";
	var data=$.serializeObject($('#register-form'));	
	postAjax(url,JSON.stringify(data),function(result){
		alert(result.resultMessage);
	},{errorFunction:function(data){
		alert(data.resultMessage);
	},cache: false, async: false})
});	
	
});