/**https://www.jianshu.com/p/e177bcc23132
 * https://blog.csdn.net/yangxiaoyanger/article/details/79858172
 * 1.在定义插件之前添加一个分号，可以解决js合并时可能会产生的错误问题;
 * 2.undefined在老一辈的浏览器是不被支持的，直接使用会报错，js框架要考虑到兼容性，因此增加一个形参undefined;
 * 3.把window对象作为参数传入，是避免了函数执行的时候到外部去查找
 * 4."use strict"  //使用严格模式检查，使语法更规范
 *
 */

    
    
    
    
    
;(function ( $, window, document, undefined ){
	
 //函数体内具体代码
ImageCode = function(container,options){
	 "use strict"	
	 //默认参数
	   var defaultOptions={
//			   /**显示图片验证码的元素节点**/
			   imagCodeWrapper:$(".checkCode"),
			   /**滑块图片的元素节点**/
			   slideBtn:$("(.itour-slide-btn"),
			   /**更新图片二维码的元素节点**/
			   refreshCode:$(".itour-img-refresh"),
			   /**滑块滑动的范围的元素节点**/
			   areaScop:$(".itour-slide-bg"),
			   /**校验图片验证码的的函数**/
			   checkCode:checkCode;
	   };
	   //获取当前参数
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
		   imagCodeWrapper.click(function(){//显示图片验证码
			  showImgCode(); 
		   });
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
	   
	   function showImgCode(){
		   showSliderBtn();
			var email=$("#form-email").val();
			if(email){
				var iserror=$(".item-email-wrap").find("span").hasClass("error");
				if(iserror){
					return;
				}else{//显示验证码
					//获取图片验证码
					var url="/getVerifyImage";
		        	var postData ={};
		        	postAjax(url,JSON.stringify(postData),function(data){
		        		 console.log(data);	
		        		 //带阴影的图片
		        		 $(".itour-bigimg img").attr("src","data:image/png;base64,"+data.returnResult.verifyImage.srcImage);
		        		 //滑动的小图片
		        		 $(".itour-smallimg img").attr("src","data:image/png;base64,"+data.returnResult.verifyImage.cutImage);
		        		 //滑动小图片的纵坐标
		        		 $(".itour-smallimg").css("top",data.returnResult.verifyImage.yPosition+"px");
		        		 //显示验证码浮出层
		        		 $(".form-item-getcode").css("z-index","1001");
		        		 $(".slide-authCode-wraper").css("display","block");
		        	}, {errorFunction:function(data){
		        		alert(data.resultMessage);
		        	},cache: false, async: false});
				}
			}
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

