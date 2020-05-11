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
		var lastX = $(".itour-smallimg").offset().left - dX - 1; //---->需要研究
		//修改鼠标拖拽标记
		isMousedown=false;
		//校验验证码
		var url="/verifyImageCode";
		var postData={"moveLength":lastX};
		postAjax(url,postData,function(data){
			var result = data.returnResult;
   		    if(result.errcode == '0'){
   		    	//隐藏验证码浮出框
   		    	$(".item-getcode-wrap").css("display","none");
   		    	//隐藏验证验证码按钮
   		    	$(".slide-authCode-wraper").css("display","none");
   		    	
   		    	//发送验证码
	   		 	postAjax("/sendCode",postData,function(data){
	   		 	  timer120();
	   		   	}, {errorFunction:function(data){
	   		   		alert(data.resultMessage);
	   		   	},cache: false, async: false}); 
	   		    	
   		    	//显示手机验证码文本框
   		    	$(".item-phonecode-wrap").css("display","block");
   		        $(".item-phonecode-wrap .input-tip").html("<span><i class='i-def'></i>验证码已发送，120秒内输入有效</span>");
   		    }else{
   		    	//调整拖动按钮位置
   		    	divMove.css({"left": (0) + "px"});
   			    $(".taoValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
   		    }
	   	}, {errorFunction:function(data){
	   		alert(data.resultMessage);
	   	},cache: false, async: false,type:"get",contentType:"application/x-www-form-urlencoded"}); 
		
		
	});
});
