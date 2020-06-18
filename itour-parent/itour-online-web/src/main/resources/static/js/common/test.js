$(function(){
	/**
	 * 点击按钮进行验证显示验证码
	 */
	$(".checkCode").click(function(){
		//重置滑块位置
		showSliderBtn();
		//校验相关参数
		var email=$("#form-email").val();
		var iserror=$(".item-email-wrap").find("span").hasClass("error");
		if(!email){return;}			
		if(iserror){return;	}		
		//获取验证码
		getImgCode();		
	});
	
	//当鼠标指针移动到元素上方，并松开鼠标左键时;
	$(".taoValidate-wrap").on("mouseup",".itour-slide-btn",function(e){
		mouseupWrap(checkImgCode);		
		
	});
	
	function checkImgCode(){
		//校图片验证验证码
		var url="/checkImageCode";
		var postData={"moveLength":lastX};
		postAjax(url,postData,function(data){
			var code = data.resultCode;
			var email = $("#form-email").val();
			validateCodeSucess();
   	   		setTimeout(function () {
   	   		 $(".form-item-getcode").css("z-index","2");
   	   		 $(".slide-authCode-wraper").css("display","none");
   	   		 //校验email是否已经被注册
   	   		 checkEmail(email);
   	   		}, 500);  	   	 
   		    
	   	}, {errorFunction:function(data){
	   		$(".taoValidate-wrap .itour-slide").addClass("itour-slide-err");
			    	//调整拖动按钮位置
			    setTimeout(function () {
		    	    // 重置滑块图片和拖动按钮位置
		    		resetlocation();
		    		//重置蓝色(滑块滑动)轨迹
			    	resetSlidBar();
			    	//重新获取二维码
	   		    	$(".checkCode").trigger("click");
		    	}, 500);
		    	
	   		console.log(data.resultMessage);
	   	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"}); 
	}
	//当鼠标指针在指定的元素中移动时，就会发生 mousemove 事件。
	$(".itour-slide-btn").mousemove(function(e){
		var event = e||event;
		//鼠标滑动的x的坐标
		var x = event.pageX;
		console.log("X:"+x);
		
		if(isMousedown){
			//鼠标滑动的x的坐标大于滑块区域x的坐标+30且滑块区域x的坐标+整个盒子的宽度-20-----》   需要研究
			if(x>(dX+30) && x<dX+$(".taoValidate-wrap").width()-20){				
                divMove.css({"left": (x - dX - 20) + "px"});//div动态位置赋值------》 需要研究
                $(".taoValidate-wrap .itour-slide-bar").css("display","block").css({"width": (x - dX+10) + "px"})
                $(".taoValidate-wrap").find(".itour-smallimg").css({"left": (x - dX-30) + "px"});//---->需要研究
            }

		}
	});
	
});
/**
 * 校验email是否已经注册
 * @param email
 * @returns
 */
function checkEmail(email){
	//校验邮箱是否可用
   	url="/account/checkEmail";
   	postData={"email":email};
   	postAjax(url,postData,function(data){  
     	//隐藏图片验证码按钮
 		  hideCheckInput();
   	      //显示验证码文本框
	 	   showEmailCodeInput();
   			//发送验证码
   	   		sendCode(email);
		
   	},{errorFunction:function(data){
   	      //隐藏验证码浮出框
			hiddeCode();
			//重置蓝色(滑块滑动)轨迹
			resetSlidBar();
			//显示滑块到初始位置
			showSliderBtn();
			//将错误信息显示到emial文本框下
			var errormsg=data.resultMessage;
			showtipErrorMsg($(".item-email-wrap .input-tip"),"form-account-error",errormsg);
			$(".checkCode").trigger("click");
   	},cache: false, async:true,contentType:"application/x-www-form-urlencoded"});
   
   
	    	
}





