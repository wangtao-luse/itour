$(function(){
	/**
	 * 关闭图片验证码浮出层
	 */
	$(".slide-authCode-wraper .close").click(function(){
		$(".slide-authCode-wraper").css("display","none");
	});
	
	//鼠标的位置
	var mX=0,mY=0;
	//滑块区域左上位置
	var dX=0,dY=0;
	//拖动按钮对象
	var divMove = $(".itour-slide-btn");
	//鼠标可拖动的区域对象
	var divWrap=$(".itour-slide-bg");
	//鼠标拖拽标志
	var isMousedown= true;
	//onmousedown 事件会在鼠标按键被按下时发生
	$(".taoValidate-wrap").on("mousedown",".itour-slide-btn",function(e){
		var event = e||window.event;
		//鼠标的位置
		mX = event.pageX;
		//鼠标可拖动的区域的左上位置(坐标)
		dX = divWrap.offset().left;
		dY = divWrap.offset().top;
		//鼠标拖拽标志
		isMousedown= true;
		console.log(dX+","+dY);	
	});
	//当鼠标指针移动到元素上方，并松开鼠标左键时;
	$(".taoValidate-wrap").on("mouseup",".itour-slide-btn",function(e){
		var l = $(".itour-smallimg").offset().left;
		var lastX = $(".itour-smallimg").offset().left - dX - 1; //---->需要研究
		//修改鼠标拖拽标记
		isMousedown=false;
		//校图片验证验证码
		var url="/checkImageCode";
		var postData={"moveLength":lastX};
		postAjax(url,postData,function(data){
			var code = data.resultCode;
			var email = $("#form-email").val();
   		    //校验二维码成功
   		    	$(".taoValidate-wrap .itour-slide-btn").css("display","none");
   	   			$(".taoValidate-wrap .itour-slide-bar").css("width","360px");
   	   			$(".taoValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("拼接成功").css("color","#fff");
   	   			$(".taoValidate-wrap .itour-slide-bar .itour-slide-bar-right").css("display","block"); 
	   	   		setTimeout(function () {
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
		
	});
	//重置滑块图片和拖动按钮位置
	function resetlocation(){
		//调整拖动按钮位置
		   $(".taoValidate-wrap .itour-slide").removeClass("itour-slide-err");
	    	divMove.css({"left": (0) + "px"});
		    $(".taoValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
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
	/**
	 * 更新图片验证码
	 * @returns
	 */
	$(".itour-img-refresh").click(function(){
		$(".checkCode").trigger("click");
	});
});
/**
 * 隐藏验证码浮出层和验证码按钮
 */
function hideCodeAndInput(){
	//隐藏验证码浮出框
    	$(".item-getcode-wrap").css("display","none");
    	//隐藏验证验证码按钮
    	$(".slide-authCode-wraper").css("display","none");
    	//显示验证码文本框
    	$(".item-mailcode-wrap").css("display","block");
    	
}
/**
 * 隐藏验证码浮出框
 * @returns
 */
function hiddeCode(){
	//隐藏验证码浮出框
	$(".item-getcode-wrap .slide-authCode-wraper").css("display","none");
}
/**
 * 隐藏进行验证按钮
 * @returns
 */
function hideCheckInput(){
	$(".item-getcode-wrap").css("display","none");
}
/**
 * 显示邮箱验证码文本框
 * @returns
 */
function showEmailCodeInput(){
	//显示邮箱验证码文本框
   	$(".item-mailcode-wrap").css("display","block");
       $(".item-mailcode-wrap .input-tip").html("<span><i class='i-def'></i>验证码已发送，120秒内输入有效</span>");
}
/**
 * 定时器倒计时120秒
 * @returns
 */
function timer120(){
	   setTime = setInterval(timer,1000);
}
var time=120;  
var timer = function(){
	  if(time<0){
       clearInterval(setTime);
       $("#getMailCode").text("重新获取"); 
       time=120;
       return;
   }
	  $("#getMailCode").text(time+"秒后重新获取"); 
	time--;
	
}
/**
 * 重置蓝色(滑块滑动)轨迹
 * @returns
 */
function resetSlidBar(){
	$(".taoValidate-wrap .itour-slide-bar").css("display","none").css("width","44px");
}

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
/**
 * 显示错误 信息
 * @param $this 元素节点
 * @param id    元素节点的id
 * @param msg   错误信息
 * @returns
 */
function showtipErrorMsg($this,id,msg){
	$this.find("span").addClass("error").attr("id",id).html("<i class='i-error'></i>"+msg);
}
/**
 * 显示滑块到初始位置
 * @returns
 */
function showSliderBtn(){
	$(".itour-slide-btn").css({"left": (0) + "px"});
	$(".taoValidate-wrap .itour-slide-btn").css("display","block");
	$(".taoValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
	$(".taoValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("");
}
/**
 * 发送验证码
 * @returns
 */
function sendCode(email){
	    //隐藏验证码浮出层	  
	      hiddeCode();
	    //发送验证码
   	    postData={"email":email};
	 	postAjax("/msg/sendCodetoEmail",postData,function(data){
	 		
	 	    //重发验证码倒计时
	 	     timer120();
	 	   
	 	   //清楚错误信息
	 	 $(".item-getcode-wrap").find(".input-tip").html("<span></span>");
	 	
	   	}, {errorFunction:function(data){
	        //调整拖动按钮位置
	    	resetlocation();
	    	//重置(蓝色)轨迹样式
    	   resetSlidBar();
	   		console.log(data.resultMessage);
	   	},cache: false, async: true,contentType:"application/x-www-form-urlencoded"}); 
}


