$(function(){

/**
 * 校验email
 *  显示提示信息
 */
$("#form-email").focus(function(){
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
		email?showError($(this),"form-email-error","格式错误"):"";
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
 * 校验用户名
 * 校验通过显示成功图标
 * 校验失败提示错误信息
 */
$("#form-account").blur(function(){
	var account = $(this).val();
	if(test_nickName(account)){//校验通过
		hideClose($(this));
		showsucess($(this));
	}else{
		account?showError($(this),"form-account-error","只支持英文、数字、“-”、“_”的组合，3-18个字符"):"";
	}
	//清楚默认的提示信息
	clearTip($(this));
});
/**
 *  校验用户名
 *  显示提示信息
 */
$("#form-account").focus(function(){
	showDefault($(this));
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
			showError($(this),"form-account-error","只支持英文、数字、“-”、“_”的组合，3-18个字符")
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
 * 鼠标聚焦时校验密码
 */
$("#form-pwd").focus(function(){
	showDefault($(this));
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
 * 鼠标聚焦时校验密码
 */
$("#form-equalTopwd").focus(function(){
	showDefault($(this));
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
$(".i-cancel").click(function(){
	$(this).parent().find("input").val("");
	hideClose($(this));
	//显示默认的提示信息(需要先清楚错误信息)
	clearError($(this));
	showDefault($(this));
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
		alert(result.resultMessage);
	},{errorFunction:function(data){
		alert(data.resultMessage);
	},cache: false, async: false})
});	
	
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
