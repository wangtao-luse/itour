;(function ( $, window, document, undefined ){
//函数体内具体代码
	ValidateCode = function(container,options){
	   var defaultOptions={
			   validateWrapper:$(".taoValidate-wrap"),
			   validateBtn:$("(.itour-slide-btn"),
			   reSendCode:$(".itour-img-refresh"),
			   areaScop:$(".itour-slide-bg"),
			   checkCode:checkCode;
	   };
	   
	   var cuurentOptions = $.extend(defaultOptions, options);
	 //鼠标的位置
		var mX=0,mY=0;
		//滑块区域左上位置
		var dX=0,dY=0;
		//拖动按钮对象
		var divMove = cuurentOptions.validateBtn;
		//鼠标可拖动的区域对象
		var divWrap=cuurentOptions.areaScop;
		//鼠标拖拽标志
		var isMousedown= true;
	   function init(){			
			validateWrapper.mousedown(function(e){//
				dfMousedown(e);
			}).mouseup(function(e){//
				checkCode(e);
			});
			validateBtn.mousemove(function(e){//移动图片验证码
				slidBtn(e);
			});
			reSendCode.click(function(){//刷新图片验证码
				sendCode();
			});
		}
	   function dfMousedown(e){
		   var event = e||window.event;
			//鼠标的位置
			mX = event.pageX;
			//鼠标可拖动的区域的左上位置(坐标)
			dX = divWrap.offset().left;
			dY = divWrap.offset().top;
			//鼠标拖拽标志
			isMousedown= true;
			console.log(dX+","+dY);	
	   }
	   
	   
	   function slidBtn(e){

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
		
	   }
	   /**
		 * 更新图片验证码
		 * @returns
		 */		
		function sendCode(){
			$(".checkCode").trigger("click");	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(jQuery, window,document);