		$(function(){
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
			$(".itourValidate-wrap").on("mousedown",".itour-slide-btn",function(e){
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
			
			
			//当鼠标指针在指定的元素中移动时，就会发生 mousemove 事件。
			$(".itour-slide-btn").mousemove(function(e){
				var event = e||event;
				//鼠标滑动的x的坐标
				var x = event.pageX;
				console.log("X:"+x);
				
				if(isMousedown){
					//鼠标滑动的x的坐标大于滑块区域x的坐标+30且滑块区域x的坐标+整个盒子的宽度-20-----》   需要研究
					if(x>(dX+30) && x<dX+$(".itourValidate-wrap").width()-20){				
		                divMove.css({"left": (x - dX - 20) + "px"});//div动态位置赋值------》 需要研究
		                $(".itourValidate-wrap .itour-slide-bar").css("display","block").css({"width": (x - dX+10) + "px"})
		                $(".itourValidate-wrap").find(".itour-smallimg").css({"left": (x - dX-30) + "px"});//---->需要研究
		            }
		
				}
			});
			//当鼠标指针移动到元素上方，并松开鼠标左键时;
			$("#slideAuthCode .itourValidate-wrap").on("mouseup",".itour-slide-btn",function(e){
				var l = $(".itour-smallimg").offset().left;
				var lastX = $(".itour-smallimg").offset().left - dX - 1; //---->需要研究
				if(lastX<0){
					return;
				}
				//修改鼠标拖拽标记
				isMousedown=false;
				//校图片验证验证码
				var url="/checkImageCode";
				var key_verify = $("#key-verify").val();
				var postData={"moveLength":lastX,"key-verify":key_verify};
				postAjax(url,postData,function(data){
					var code = data.resultCode;
					var email = $("#form-email").val();
		   		    //校验二维码成功
		   		    	$(".itourValidate-wrap .itour-slide-btn").css("display","none");
		   	   			$(".itourValidate-wrap .itour-slide-bar").css("width","360px");
		   	   			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("拼接成功").css("color","#fff");
		   	   			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-right").css("display","block"); 
			   	   		setTimeout(function () {
			   	   		 $(".form-item-getcode").css("z-index","2");
			   	   		 $(".slide-authCode-wraper").css("display","none");
			   	   	//校验email是否已经被注册
			   		     checkEmail(email);
			   	   		}, 500);
			   	   	 
		   		    
			   	}, {errorFunction:function(data){
			   		$(".itourValidate-wrap .itour-slide").addClass("itour-slide-err");
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
		/**
		 * 校验email,用户名,密码框,确认密码框
		 *  显示默认提示信息
		 */
		$("#form-email,#form-account,#form-pwd,#form-equalTopwd").focus(function(){
			showDefault($(this));	
		});
		/**
		 * 校验email
		 * 校验通过显示成功图标
		 * 校验失败提示错误信息
		 */
		$("#form-email").blur(function(){
			var email=$(this).val();		
			if(test_email(email)){//校验通过
				hideClose($(this));
				showsucess($(this));		
			}else{//校验失败
				//显示错误信息	
				if(email!=null&&email!=''){
					showError($(this),"form-email-error","格式错误");
				}
				
			}
			//清楚默认的提示信息
			clearTip($(this));
		});
		
		/**
		 * 鼠标释放时校验email
		 */
		$("#form-email").keyup(function(){
			var email = $(this).val();
			if(email){//不为空
				//显示email推荐
				showEmailSuggest(email);			
				if(!test_email(email)){//校验失败
					//隐藏成功图标
					hideSucess($(this));
					//显示清除的图标
					showClose($(this));
					//显示错误信息	
					showError($(this),"form-email-error","格式错误")
				}else{//校验成功
					clearError($(this));
					showsucess($(this));
					hideClose($(this));
					$(".suggest-container.email-suggest").css("display","none");
				}
			}else{//为空
				$(".suggest-container.email-suggest").css("display","none");
				//隐藏清除的图标
				hideClose($(this));
				//显示默认的提示信息(需要先清楚错误信息)
				clearError($(this));
				showDefault($(this));
			}
		});
		
		function showEmailSuggest(email){
			//显示邮箱推荐邮箱
			$(".suggest-container.email-suggest").css("display","block");
			//获取后缀
			var arr = $(".suggest-container.email-suggest").find(".value");
			for (var i = 0; i < arr.length; i++) {
				//获取最后一次出现@的下标
				var start =$(arr[i]).text().lastIndexOf("@");
				//得到后缀
				 var  suffix =$(arr[i]).text().substring(start);
				 //清空推荐文本
				 //$(arr[i]).text("");
				 //添值
				 if(email.indexOf("@")==-1){
					 $(arr[i]).text(email.trim()+suffix); 
				 }
				
				
			}
			
		}
		$(".suggest-container.email-suggest li").click(function(){
			var v = $(this).text();
			$("#form-email").val(v);
			$(".suggest-container.email-suggest").css("display","none");
			$("#form-email").trigger("blur");
		});
		/**
		 * 点击按钮进行验证显示验证码
		 */
		$(".checkCode").click(function(){
			showSliderBtn();
			var email=$("#form-email").val();
			if(email){
				var iserror=$(".item-email-wrap").find("span").hasClass("error");
				if(iserror){
					return;
				}else{
					//显示验证码
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
		        		 $("#key-verify").val(data.returnResult.key_verify);
		        		 //显示验证码浮出层
		        		 $(".item-email-wrap").css("z-index","2");
		        		 $(".slide-authCode-wraper").css("display","block");
		        	}, {errorFunction:function(data){
		        		showError($(".form-item-getcode"),"form-email-error",data.resultMessage);
		        	},cache: false, async: false});
				}
			}else{
				showError($("#form-email"),"form-email-error","请输入邮箱");
			}
		});
		$(".slide-authCode-wraper .close").click(function(){
			$(".slide-authCode-wraper").hide();
			 $(".item-email-wrap").css("z-index","1000");
		});
		
		/**
		 * 显示滑块到初始位置
		 * @returns
		 */
		function showSliderBtn(){
			$(".itour-slide-btn").css({"left": (0) + "px"});
			$(".itourValidate-wrap .itour-slide-btn").css("display","block");
			$(".itourValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("");
			$(".itourValidate-wrap .itour-slide-bar").css("display","none").css("width","44px");
		}
		$("#mailCode").blur(function(){
			var code = $("#mailCode").val();
			//清楚默认的提示信息
			var hasdef = $("#getMailCode").next().find("span i").hasClass("i-def");
			if(hasdef){
				$("#getMailCode").next().find("span").html("");
			}
			if(!$.isEmpty(code)){
				$("#getMailCode").next().find("span").removeClass("error").attr("id","");
			}
		});
		$("#mailCode").focus(function(){
			var code = $("#mailCode").val();
			var has=$(".item-mailcode-wrap .input-tip").find("span i").hasClass("error");
			if(!has){
				 $(".item-mailcode-wrap .input-tip").html("<span><i class='i-def'></i>验证码已发送，120秒内输入有效</span>");
			}
			
		});
		/**
		 * 注册下一步按钮
		 */
		$("#step1-next").click(function(){
			 //1.校验邮箱	
			var email = $("#form-email").val();
			if($.isEmpty(email)){
				showError($("#form-email"),"form-email-error","请输入邮箱");
				return;
			}
			if(!test_email(email)){
				showError($("#form-email"),"form-email-error","格式错误");
				return;
			}
			var v = $(".item-getcode-wrap").is(':visible');
			if(v){
				showError($(".form-item-getcode .checkCode"),"form-account-error","请先进行验证！");
				return;
			}
			$("#mailCode").trigger("blur");
			var iserror = $(this).parent().find("span").hasClass("error");
			if(iserror){
				return;
			}	
			var mailcode=$("#mailCode").val();
			if($.isEmpty(mailcode)){
				//显示错误信息	
				$("#getMailCode").next().find("span").addClass("error").attr("id","form-emailCode-error").html("<i class='i-error'></i>"+"请输入验证码");
				return;
			}
				checkEmailCode($("#mailCode").val());
			
		})
		
		
		/**
		 * 校验用户名
		 * 校验通过显示成功图标
		 * 校验失败提示错误信息
		 */
		$("#form-account").blur(function(){
			var account = $(this).val();
			if(test_nickName(account)){//校验通过
				hideClose($(this));
				
				//showsucess($(this));
				//校验用户名是否被占用
				checkRegName(account,$(this));
			}else{
				account?showError($(this),"form-account-error","只支持英文、数字、“-”、“_”的组合，3-18个字符"):"";
			}
			//清楚默认的提示信息
			clearTip($(this));
		});
		
		/**
		 * 鼠标释放时校验用户名
		 */
		$("#form-account").keyup(function(){
			var account = $(this).val();
			if(account){//不为空
				if(!test_nickName(account)){//校验失败
					//隐藏成功图标
					hideSucess($(this));
					//显示清除的图标
					showClose($(this));
					//显示错误信息	
					showError($(this),"form-account-error","只支持英文、数字、“-”、“_”的组合，3-18个字符");
				}else{//校验成功
					clearError($(this));
					showsucess($(this));
					hideClose($(this));
				}
			}else{//为空
				//隐藏清除的图标
				hideClose($(this));
				//显示默认的提示信息(需要先清楚错误信息)
				clearError($(this));
				showDefault($(this));
			}
		});
		/**
		 * 鼠标失焦时校验密码
		 */
		$("#form-pwd").blur(function(){
			var pwd = $(this).val();
			if(test_pwd(pwd)){
				//隐藏清除的图标
				hideClose($(this));		
				showsucess($(this));
			}else{
				pwd?showError($(this),"form-pwd-error","只支持6-20个字符"):"";
			}
			//清楚默认的提示信息
			clearTip($(this));
		});
		
		/**
		 * 键盘释放时校验密码
		 */
		$("#form-pwd").keyup(function(){
			var pwd = $(this).val();
			if(pwd){//不为空
				if(!test_pwd(pwd)){//校验失败
					//隐藏成功图标
					hideSucess($(this));
					//显示清除的图标
					showClose($(this));
					//显示错误信息	
					showError($(this),"form-pwd-error","格式错误")
				}else{//校验成功
					clearError($(this));
					showsucess($(this));
					hideClose($(this));
				}
			}else{//为空
				//隐藏清除的图标
				hideClose($(this));
				//显示默认的提示信息(需要先清楚错误信息)
				clearError($(this));
				showDefault($(this));
			}
		});
		/**
		 * 失焦确认密码
		 */
		$("#form-equalTopwd").blur(function(){
			var eqpwd = $(this).val();
			if(test_pwd(eqpwd)&&equalTopwd($("#form-pwd").val(),eqpwd)){
				//隐藏清除的图标
				hideClose($(this));
				//显示状态图标
				showsucess($(this));
			}else{
				eqpwd?showError($(this),"form-equalTopwd-error","两次密码不一致"):"";
			}
			//清楚默认的提示信息
			clearTip($(this));
		});
		
		/**
		 * 键盘释放时处理
		 */
		$("#form-equalTopwd").keyup(function(){
			var eqpwd = $(this).val();
			if(eqpwd){//不为空
				if(!test_pwd(eqpwd)&&equalTopwd($("#form-pwd").val(),eqpwd)){//校验失败
					//隐藏成功图标
					hideSucess($(this));
					//显示清除的图标
					showClose($(this));
					//显示错误信息	
					showError($(this),"form-equalTopwd-error","两次密码不一致");
				}else{//校验成功
					clearError($(this));
					showsucess($(this));
					hideClose($(this));
				}
			}else{//为空
				//隐藏清除的图标
				hideClose($(this));
				//显示默认的提示信息(需要先清楚错误信息)
				clearError($(this));
				showDefault($(this));
			}
			
			
		});
		//清除文本
		$("#form-register .i-cancel").click(function(){
			$(this).parent().find("input").val("");
			hideClose($(this));
			//显示默认的提示信息(需要先清楚错误信息)
			clearError($(this));
			showDefault($(this));
			//隐藏邮箱提示信息
			$(".suggest-container.email-suggest").css("display","none");
		});
		/**注册提交*/
		$("#form-register").click(function(){
			var hasError = $("#register-form").find(".input-tip span").hasClass("error");
			if(hasError){
				return;
			}
			var url="/account/regSub";
			var data=$.serializeObject($('#register-form'));	
			postAjax(url,JSON.stringify(data),function(result){
				//alert(result.resultMessage);
				var regName=$("#form-account").val();
				location.href=ctxPath+"/account/registerSucess?regName="+regName;
			},{errorFunction:function(data){
				alert(data.resultMessage);
			},cache: false, async: false})
		});	
		
		/**
		 * 更新图片验证码
		 * @returns
		 */
		$("#itour-wrap-loginsubmit .itour-img-refresh").click(function(){
			$("#loginsubmit").trigger("click");
		});
		$("#find-pwd-code .itour-img-refresh").click(function(){
			$("#find-pwd-button").trigger("click");
		});
		$("#slideAuthCode .itour-img-refresh").click(function(){
			$(".checkCode").trigger("click");
		});
		$("#loginsubmit").click(function(){
			showSliderBtn();
			var loginname=$("#loginname").val();
			var nloginpwd=$("#nloginpwd").val();
			if(!loginname){
				$(".login-box .msg-wrap .msg-error").html("<b></b>请输入用户名").show();
				return;
			}
			if(!nloginpwd){
				$(".login-box .msg-wrap .msg-error").html("<b></b>请输入密码").show();
				return;
			}
					//显示验证码
					//获取图片验证码
					var url="/getVerifyImage";
		        	var postData ={"small":"small"};
		        	postAjax(url,JSON.stringify(postData),function(data){
		        		 console.log(data);	
		        		 //带阴影的图片
		        		 $(".itour-bigimg img").attr("src","data:image/png;base64,"+data.returnResult.verifyImage.srcImage);
		        		 //滑动的小图片
		        		 $(".itour-smallimg img").attr("src","data:image/png;base64,"+data.returnResult.verifyImage.cutImage);
		        		 //滑动小图片的纵坐标
		        		 $(".itour-smallimg").css("top",data.returnResult.verifyImage.yPosition+"px");
		        		 $("#key-verify").val(data.returnResult.key_verify);
		        		 //显示验证码浮出层
		        		 $(".item.item-fore5").css("z-index","10");
		        		 $("#itour-wrap-loginsubmit").css("display","block");
		        	}, {errorFunction:function(data){
		        		$(".login-box .msg-wrap .msg-error").html("<b></b>"+data.resultMessage).show();
		        	},cache: false, async: false});
			
		});
		//当鼠标指针移动到元素上方，并松开鼠标左键时;
		$("#itour-wrap-loginsubmit .itourValidate-wrap").on("mouseup",".itour-slide-btn",function(e){
			var l = $(".itour-smallimg").offset().left;
			var lastX = $(".itour-smallimg").offset().left - dX - 1; //---->需要研究
			if(lastX<0){
				return;
			}
			//修改鼠标拖拽标记
			isMousedown=false;
			//校图片验证验证码
			var url="/checkImageCode";
			var key_verify = $("#key-verify").val();
			var postData={"moveLength":lastX,"key-verify":key_verify};
			postAjax(url,postData,function(data){
				var code = data.resultCode;
				    //校验二维码成功
				    	$(".itourValidate-wrap .itour-slide-btn").css("display","none");
			   			$(".itourValidate-wrap .itour-slide-bar").css("width","290px");
			   			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("拼接成功").css("color","#fff");
			   			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-right").css("display","block"); 
			   			setTimeout(function () {
			   				$("#itour-wrap-loginsubmit").css("display","none");
			      	         //登录
			      	    	loginSub();
				   	   		}, 500);
			   			
				    
		   	}, {errorFunction:function(data){
		   		$(".itourValidate-wrap .itour-slide").addClass("itour-slide-err");
				    	//调整拖动按钮位置
				    setTimeout(function () {
			    	    // 重置滑块图片和拖动按钮位置
			    		resetlocation();
			    		//重置蓝色(滑块滑动)轨迹
				    	resetSlidBar();
				    	//重新获取二维码
		   		    	$("#loginsubmit").trigger("click");
			    	}, 500);
			    	
		   		console.log(data.resultMessage);
		   	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"}); 
			
		});
		
		
		$(".clear-btn").click(function(){
			$(this).parent().find("input").val("");
			$(this).hide();
		
		});
		
		$(".findpwd-step1 .input-text-account").keyup(function(){
			var v = $(this).val();
			if(v){
				$(this).next().find(".i-cancel").show();
			}else{
				$(this).next().find(".i-cancel").hide();
			}
		})
		$(".findpwd-step1 .input-text-account").change(function(){
			var succ = $("#find-pwd-button").hasClass("btn-check-succ");
			if(succ){
				$("#find-pwd-button").removeClass("btn-check-succ")
				 					 .addClass("btn-check-defaut")
				 					 .html("<span class=' icon-click'></span>点击进行验证 ");
				$("#find-pwd-next").addClass("disable").removeClass("btn-register");
				
			}
		})
		
		$("#find-pwd-button").click(function(){
			showSliderBtn();
			var v =$(".findpwd-step1 .input-text-account").val();
			$(".item-rcol .input-tip").html("");
			if(v){
				
				//获取二维码
				//显示验证码
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
		    		 $("#key-verify").val(data.returnResult.key_verify);
		    		 //显示验证码浮出层
		    		 $(".slide-authCode-wrape").css("display","block");
		    	}, {errorFunction:function(data){
		    		$(".login-box .msg-wrap .msg-error").html("<b></b>"+data.resultMessage).show();
		    	},cache: false, async: false});
			}else{
				$(".item-rcol .input-tip").append("请输入用户名/邮箱/已验证手机号");
				$(".item-rcol .input-tip").show();
			}
		});
		
		$("#find-pwd-code .itourValidate-wrap").on("mouseup",".itour-slide-btn",function(e){
			var l = $(".itour-smallimg").offset().left;
			var lastX = $(".itour-smallimg").offset().left - dX - 1; //---->需要研究
			if(lastX<0){
				return;
			}
			//修改鼠标拖拽标记
			isMousedown=false;
			//校图片验证验证码
			var url="/checkImageCode";
			var key_verify = $("#key-verify").val();
			var postData={"moveLength":lastX,"key-verify":key_verify};
			postAjax(url,postData,function(data){
				var code = data.resultCode;
				    //校验二维码成功
				    	$(".itourValidate-wrap .itour-slide-btn").css("display","none");
			   			$(".itourValidate-wrap .itour-slide-bar").css("width","364px");
			   			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("拼接成功").css("color","#fff");
			   			$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-right").css("display","block"); 
			   			//1.校验用户是否存在
			   			var email = $("#form-email").val();
			   			checkExistEmail(email);
			   			setTimeout(function () {	   				
			   				//$("#find-pwd-button").addClass("btn-check-succ").removeClass("btn-check-defaut").html("<span class=' iconfont icon-done'></span>"+"认证成功");
			   				//$("#find-pwd-next").removeClass("disable").addClass("btn-register");
			   				//$(".findpwd-step1 .item-mailcode-wrap").css("display","block");
			   				$("#find-pwd-code").css("display","none");
			      	         
				   	   		}, 500);
			   			
				    
		   	}, {errorFunction:function(data){
		   		$(".itourValidate-wrap .itour-slide").addClass("itour-slide-err");
				    	//调整拖动按钮位置
				    setTimeout(function () {
			    	    // 重置滑块图片和拖动按钮位置
			    		resetlocation();
			    		//重置蓝色(滑块滑动)轨迹
				    	resetSlidBar();
				    	//重新获取二维码
		   		    	$("#find-pwd-button").trigger("click");
			    	}, 500);
			    	
		   		console.log(data.resultMessage);
		   	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"}); 
			
		});
		$("#find-pwd-next").click(function(){
			var d = $(this).hasClass("disable");
			if(d){return;}
			//1.校验验证码
			var ecode=$("#mailCode").val();
			if($.isEmpty(ecode)){
				$("#mailCode").parent().parent().find(".input-tip").html("<span class='input-error'><i class='i-error'></i>请输入验证码</span>");
			   return;
			}
			checkFindpwdEmailCode(ecode);
		});
		$("#form-kl").blur(function(){
			var pwd = $(this).val();
			if(!test_pwd(pwd)){
				$(this).parent().next().html("<span class='input-error'><i class='i-error'></i>请将密码设置为8-20位，并且由字母，数字和符号两种以上组合</span>")
			}else{
				$(this).parent().next().html("");
			}
			
		});
		
		$("#form-kl").focus(function(){
			var pwd = $(this).val();
			if($.isEmpty(pwd)){
				var v ="<span><i class='i-def'></i>建议使用字母、数字和符号两种及以上的组合，8-20个字符</span>";
				$(this).parent().next().html(v);
			}
		
			
		});
		$("#findpwd_sub").click(function(){
			$("#form-kl").trigger("blur");
			var v = $("#form-kl").parent().next().find("span").hasClass("input-error");
			if(v){
				return;
			}
			var pwd = $("#form-kl").val();
			var email = $("#form-email").val();
			var data={"email":email,"pwd":pwd};
			var url="/account/findPwd";
			postAjax(url,JSON.stringify(data),function(data){
				alert("操作成功");
		   		location.href=ctxPath+"/account/login";
		   	},{errorFunction:function(data){
			   	 alert("修改失败");
			   	 //location.reload();
			},cache: false, async: false});	
		})
});
function checkFindpwdEmailCode(ecode){
   	url="/checkemailCode";
   	var key = $("#key-email-code").val();
   	postData={"code":ecode,"key-email-code":key};
   	postAjax(url,postData,function(data){
   		$(".findpwd-step1").css("display","none");
    	$(".findpwd-step2").css("display","block");
   	},{errorFunction:function(data){
   	 $(".item-mailcode-wrap .input-tip").html("<span class='input-error'><i class='i-error'></i>"+data.resultMessage+"</span>");
	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"});	
}
$(document).on("keyup","#loginname,#nloginpwd",function(){
	var name = $(this).val();
	if(name){
		$(this).next().css("display","inline");
	}else{
		$(this).next().hide();
	}
});


//登录
function loginSub(){
	var url ="/account/loginSub";
	var data=$.serializeObject($('#loginform'))
	postAjax(url,JSON.stringify(data),function(data){
		location.href=ctxPath+"/index";
	},{errorFunction:function(data){
		$(".login-box .msg-wrap .msg-error").html("<b></b>"+data.resultMessage).show();
	},
		cache:false
	})
};

/**
 * 重新获取验证码
 */
var time=120; 
$(document).on("click","#getMailCode",function(){
	//重发验证码倒计时
	time=120;
    timer120();
	sendCode($("#form-email").val());
});
$(document).on("change","#form-email",function(){
	var v =$(".item-mailcode-wrap").is(':visible');
	if(v){
		 clearInterval(setTime);
		 time=120;
		 $("#getMailCode").text(time+"秒后重新获取");
		$(".item-mailcode-wrap").hide();	
		$(".item-getcode-wrap").show();	
	}
	
});
/**
 * 用于文本框错误信息提示
 * @param $this 元素节点
 * @param id    错误信息父元素的id
 * @param errormsg 错误信息
 * @returns
 */
function showError($this,id,errormsg){
$this.parent().next().find("span").addClass("error").attr("id",id).html("<i class='i-error'></i>"+errormsg);
}
/**
 * 清除错误信息
 * @param $this
 * @returns
 */
function clearError($this){
	$this.parent().next().find("span").removeClass("error").attr("id","");
	$this.parent().next().find("span i").removeClass("i-error").addClass("i-def");
}
/**
 * 校验成功后显示状态图标
 * @param $this
 * @returns
 */
function showsucess($this){
	//1.添加成功的图标
	$this.parent().addClass("form-item-valid");
	//2.移除错误信息提示
	$this.parent().next().find("span").html("").removeClass("error");
}
/**
 * 隐藏状态图标
 * @param $this
 * @returns
 */
function hideSucess($this){
	//1.添加成功的图标
	$this.parent().removeClass("form-item-valid");
}
/**
 * 显示默认的提示信息
 * @param $this
 * @returns
 */
function showDefault($this){
	var df=$this.attr("default");
	var hasError = $this.parent().next().find("span").hasClass("error");
	if(!hasError){
		$this.parent().next().find("span").html(df);
	}
}
/**
 * 清楚默认的提示信息
 */
function clearTip($this){
	//清除默认的提示信息
	var hasdef = $this.parent().next().find("span i").hasClass("i-def");
	if(hasdef){
		$this.parent().next().find("span").html("");
	}
	

}
/**
 * 显示清除图片
 * @param $this
 * @returns
 */
function showClose($this){
	$this.parent().find(".i-cancel").css("display","block");
}
/**
 * 隐藏清除图片
 * @param $this
 * @returns
 */
function hideClose($this){
	$this.parent().find(".i-cancel").css("display","none");
}
/**
 * 校验验证码
 * @param ecode
 * @returns
 */
function checkEmailCode(ecode){
   	url="/checkemailCode";
	var key = $("#key-email-code").val();
   	postData={"code":ecode,"key-email-code":key};
   	postAjax(url,postData,function(data){
   		$("#step1-wrap").css("display","none");
    	$("#step2-wrap").css("display","block");
    	$(".cur-step").addClass("done-step");
    	$(".cur-step span").text("");
    	$(".cur-step").removeClass("cur-step");    	
    	$(".person-pro-step2").addClass("cur-step");
   	},{errorFunction:function(data){
   	 $(".item-mailcode-wrap .input-tip").html("<span><i class='i-error'></i>"+data.resultMessage+"</span>");
	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"});	
}
/**
 * 
 * @param account 用户名
 * @param $this ele
 * @returns
 */
function checkRegName(account,$this){
	var url="/account/checkRegName";
	var postData={"regName":account};
	postAjax(url,postData,function(data){
		//成功显示可用图标
		showsucess($this);
	},{errorFunction:function(data){
		showError($this,"form-account-error",data.resultMessage);
	},cache: false, async: false,contentType:"application/x-www-form-urlencoded"
		
	});
}
//重置滑块图片和拖动按钮位置
function resetlocation(){
	//调整拖动按钮位置
	   $(".itourValidate-wrap .itour-slide").removeClass("itour-slide-err");
	   $(".itour-slide-btn").css({"left": (0) + "px"});
	    $(".itourValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
}
/**
 * 重置蓝色(滑块滑动)轨迹
 * @returns
 */
function resetSlidBar(){
	$(".itourValidate-wrap .itour-slide-bar").css("display","none").css("width","44px");
}
/**
 * 隐藏验证码浮出框
 * @returns
 */
function hiddeCode(){
	//隐藏验证码浮出框
	 $(".form-item-getcode").css("z-index","2");
	$(".item-getcode-wrap .slide-authCode-wraper").css("display","none");
}
/**
 * 显示滑块到初始位置
 * @returns
 */
function showSliderBtn(){
	$(".itour-slide-btn").css({"left": (0) + "px"});
	$(".itourValidate-wrap .itour-slide-btn").css("display","block");
	$(".itourValidate-wrap").find(".itour-smallimg").css({"left": (0) + "px"});
	$(".itourValidate-wrap .itour-slide-bar .itour-slide-bar-center").text("");
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
 * 隐藏进行验证按钮
 * @returns
 */
function hideCheckInput(){
	$(".item-getcode-wrap").css("display","none");
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
//邮箱是否存在
function checkExistEmail(email){
	//校验邮箱是否可用
   	url="/account/checkExistEmail";
   	postData={"email":email};
   	postAjax(url,postData,function(data){  
     	//隐藏图片验证码按钮
 		  hideCheckInput(); 		 
   	      //显示验证码文本框
	 	   showEmailCodeInput();
   			//找回密码发送验证码
	 	  findpwdSendCodetoEmail(email);
   	  
		
   	},{errorFunction:function(data){
   	      //隐藏验证码浮出框
			hiddeCode();
			//重置蓝色(滑块滑动)轨迹
			resetSlidBar();
			//显示滑块到初始位置
			showSliderBtn();
			//将错误信息显示到emial文本框下
			var errormsg=data.resultMessage;
			$(".item-rcol .input-tip").append("<i class='i-error'></i>"+errormsg);
			$(".item-rcol .input-tip").show();
			$(".checkCode").trigger("click");
   	},cache: false, async:true,contentType:"application/x-www-form-urlencoded"});
   
   
	    	
}
/**
 * 显示邮箱验证码文本框
 * @returns
 */
function showEmailCodeInput(){
	//重发验证码倒计时
	time=120;
     timer120();
	//显示邮箱验证码文本框
 	$("#getMailCode").addClass("btn-code-disable");
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
 
var timer = function(){
	$("#getMailCode").addClass("btn-code-disable");
	  if(time<0){
       clearInterval(setTime);
       $("#getMailCode").removeClass("btn-code-disable");
       $("#getMailCode").prop("disabled","");
       $("#getMailCode").text("重新获取"); 
       time=120;
       return;
   }
	  $("#getMailCode").text(time+"秒后重新获取"); 
	time--;
	
}
/**
 * 发送验证码
 * @returns
 */
function sendCode(email){	
	    //隐藏验证码浮出层	  
	      hiddeCode();
	    //发送验证码
   	    postData={"email":email,"ip":$("#ip").val()};
	 	postAjax("/msg/sendCodetoEmail",postData,function(data){
	 	   //清楚错误信息
	 	 $(".item-getcode-wrap").find(".input-tip").html("<span></span>");
	 	  $("#key-email-code").val(data.returnResult.key_email_code);
	   	}, {errorFunction:function(data){
	        //调整拖动按钮位置
	    	resetlocation();
	    	//重置(蓝色)轨迹样式
    	   resetSlidBar();
	   		console.log(data.resultMessage);
	   	},cache: false, async: true,contentType:"application/x-www-form-urlencoded"}); 
}
function findpwdSendCodetoEmail(email){	
    //隐藏验证码浮出层	  
      hiddeCode();
    //发送验证码
	   postData={"email":email,"ip":$("#ip").val()};
 	postAjax("/msg/sendCodetoEmail",postData,function(data){
 		 $("#key-email-code").val(data.returnResult.key_email_code);
 		 $("#find-pwd-button").addClass("btn-check-succ").removeClass("btn-check-defaut").html("<span class=' iconfont icon-done'></span>"+"认证成功");
 		 $("#find-pwd-next").removeClass("disable").addClass("btn-register");
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
