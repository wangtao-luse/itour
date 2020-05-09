var postAjax = function(url,postData,successFunction,options){
			var returnFlag=false;
			var defaultOptions={
				type:"post",
				async:true,
				errorFunction:errorFunction,
				successArguments: "",
				errorArguments: "",
				contentType:"application/json; charset=utf-8",/*传入数据类型*/
				dataType:"json"/* 返回的数据类型*/
			};			
			var currentOptions = $.extend(defaultOptions, options);//https://www.runoob.com/jquery/misc-extend.html
			$.ajax({
				type:currentOptions.type,
				url:url,
				data:postData,
				async:currentOptions.async,
				contentType:currentOptions.contentType,
				dataTyp:currentOptions.dataType,
				beforeSend:function(){
					//loading();
				},
				success:function(resultData){
					if(resultData&&isSuccess(resultData.resultCode)){
						if(defaultOptions&&defaultOptions.successArguments){
							successFunction(resultData,defaultOptions.successArguments);
						}else{
							successFunction(resultData);
							
						}
						returnFlag = true;
					}else{
						defaultOptions.errorFunction(resultData);
						returnFlag = false;
					}
					
					
				},
				complete:function(){
					deleteLoading();
				},
				error:function(result){
					deleteLoading();
				},
				
				
			});
			return returnFlag;
		}





		function loading(){
			 $('body').append('<div id="aLoad"><div id="aLoad-overlay"></div><div class="cssload-loader"><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div></div>');
		}
		function deleteLoading(){
			 $("#aLoad").remove();
		}
		function isSuccess(resultCode){
		return ("10"===resultCode)
		}
		function errorFunction(resultData) {
		showMessage(resultData);
		}
		
		function showMessage(resultData) {
		alert(resultData.description);
		}
		
		
		function filterSpecial(value) {
		    return value.replace(/[']/g, '')
		}
		$.serializeObject = function (form) {
		    var o = {};
		    $.each(form.serializeArray(), function (index) {
		        if (o[this['name']]) {
		            o[this['name']] = filterSpecial(o[this['name']] + "," + this['value'])
		        } else {
		            o[this['name']] = filterSpecial(this['value'])
		        }
		    });
		    return o
		};
		
/**
 * 正则表达式
 * 所有的通用的正则表达
 */		
var test_email = function(email){
	/**
	 * 邮箱格式:用户名称@域名
	 * 用户名由数字、字母、下划线、中划线组成至少两位;
	 * 域名：只能为.com ,.cn ,.net,.com.cn,.edu,.gov,.org
	 */
	var email_reg =/^[a-zA-Z0-9_\-\.]{2,}\@\w+\.(com|cn|net|com\.cn|edu|gov|org)$/;
	return email_reg.test(email);
}
var test_nickName = function(nickName){
	/**
	 * 用户名:数字、字母、下划线(3-18位)、不能以数字开头
	 */
	var nickName_reg=/^([a-zA-Z])([a-zA-Z0-9_\-]{2,18})$/;
	return nickName_reg.test(nickName);
}
var test_pwd = function(pwd){
/**
 * 密码：必须（6-20位）字符
 */	
	var pwd_reg = /^[a-zA-z0-9_\-\.\*\#\@\?\%\!]{6,20}$/;
	return pwd_reg.test(pwd);
}
var test_phone = function(phone){
	/**
	 * 电话号码:长度必须为11位,必须以1开头,第二位必须为3、4、5、6、7、8、9中的一位;
	 */
	var phone_reg = /^1[3456789]\d{9}$/;
	return phone_reg.test(phone);
}
var equalTopwd = function(pwd,topPwd){
	return pwd == topPwd;
}