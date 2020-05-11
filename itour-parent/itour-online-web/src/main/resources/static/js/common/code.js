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
		//校验验证码
		var url="/verifyImageCode";
		var postData={"moveLength":lastX};
		postAjax(url,postData,function(data){
			var result = data.returnResult;
   		    if(result.errcode == '10'){//校验成功
   		    	//隐藏验证码浮出层和验证码按钮
   		    	hideCodeAndInput();
   		    	//发送验证码
   		    	postData={"email":$("#form-email").val()};
	   		 	postAjax("/sendCodetoEmail",postData,function(data){	   		 	
	   		 	  //重发验证码倒计时
	   		 	  timer120();
	   		 	 showEmailCodeInput();
	   		 	
	   		   	}, {errorFunction:function(data){
	   		   		console.log(data.resultMessage);
	   		   	},cache: false, async: false}); 
	   		    	
	   		 
   		    }else{
   		    	//调整拖动按钮位置
   		    	divMove.css({"left": (0) + "px"});
   			    $(".taoValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
   		    }
	   	}, {errorFunction:function(data){
	   		console.log(data.resultMessage);
	   	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"}); 
		
		
	});
	
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
                $(".taoValidate-wrap").find(".itour-smallimg").css({"left": (x - dX-30) + "px"});//---->需要研究
            }

		}
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
       $("#getPhoneCode").text("重新获取"); 
       time=120;
       return;
   }
	  $("#getPhoneCode").text(time+"秒后重新获取"); 
	time--;
	
}
