$(function(){
	showVerify();
	$("#loginsubmit").click(function(){
		var url="/verify/checkVerify"; 
		var verify = $("#authcode").val();
		if($.isEmpty(verify)){
			$(".login-form .msg-wrap").html("<div class='msg-error'><b></b>请输入验证码</div>").show();
			return;
		}
		//校验二维码
		var key = $("#code_login").val();
    	var postData ={"verify":verify,"key":key};
    	postAjax(url,JSON.stringify(postData),function(data){
	    		 //登录
	    		var url ="/member/loginSub";
	    		var postData=$.serializeObject($('#loginform'));
	    		postAjax(url,JSON.stringify(postData),function(data){
	    			location.href=ctxPath+"/index";
	       		
		       	}, {errorFunction:function(data){
		       		showVerify();
		       		$(".login-form .msg-wrap").html("<div class='msg-error'><b></b>"+data.resultMessage+"</div>").show();
		       	},cache: false, async: false});
	    		
    	}, {errorFunction:function(data){
    		showVerify();
    		$(".login-form .msg-wrap").html("<div class='msg-error'><b></b>"+data.resultMessage+"</div>").show();
    	},cache: false, async: false});
	});
	
	$("#o-authcode a").click(function(){
		showVerify();
	});
	function showVerify(){
		//显示验证码
		//获取图片验证码
		var url="/verify/getVerify";
    	var postData ={};
    	postAjax(url,JSON.stringify(postData),function(data){
    		 console.log(data);	
    		 //带阴影的图片
    		 $("#o-authcode img").attr("src","data:image/png;base64,"+data.returnResult.data.verification);
    		 $("#code_login").val(data.returnResult.data.code_login);
    	}, {errorFunction:function(data){
    	},cache: false, async: false});
	}
});