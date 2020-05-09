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
		showsucess($(this));
	}else{//校验失败
		//显示错误信息		
		email?showError($(this),"form-email-error","格式错误"):"";
	}
	clearTip($(this));
});

/**注册提交*/
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
 * 校验成功后显示成功图标
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