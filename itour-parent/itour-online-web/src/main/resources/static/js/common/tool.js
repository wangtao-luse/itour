var postAjax = function(url,postData,successFunction,options){
			var returnFlag=false;
			var defaultOptions={
				type:"post",
				async:true,//请求是否异步处理,默认是 true
				cache:true,//浏览器是否缓存被请求页面,默认是 true
				errorFunction:errorFunction,
				successArguments: "",
				errorArguments: "",
				contentType:"application/json; charset=utf-8",/*传入数据类型*/
				dataType:"json"/* 返回的数据类型*///https://www.cnblogs.com/likui-bookHouse/p/8399827.html
			};
			//合并属性
			var currentOptions = $.extend(defaultOptions, options);//https://www.runoob.com/jquery/misc-extend.html
			$.ajax({
				type:currentOptions.type,//规定请求的类型（GET 或 POST）
				url:getContextPath()+url,//发送请求的 URL。默认是当前页面
				data:postData,//发送到服务器的数据
				async:currentOptions.async,
				contentType:currentOptions.contentType,//发送数据到服务器时所使用的内容类型。默认是："application/x-www-form-urlencoded"
				dataTyp:currentOptions.dataType,//预期的服务器响应的数据类型
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
					}else if(isLogin(resultData.resultCode)){
						  var pathName = document.location.pathname;
			                var index = pathName.substr(1).indexOf("/");
			                var result = pathName.substr(0, index + 1);
			                alert(resultData.resultMessage);
			                location.replace(result+resultData.returnResult.result);
					}else{
						defaultOptions.errorFunction(resultData);
						returnFlag = false;
					}
					
					
				},
				complete:function(XMLHttpRequest, status){
					var sessionStatus = XMLHttpRequest.getResponseHeader("session-status");
			        if(sessionStatus=="timeout"){
			        location.href=getContextPath() + "/account/login";
			        }
					deleteLoading();
				},
				error:function(result){
					deleteLoading();
				},
				
				
			});
			return returnFlag;
		}


var postForm = function (url, postData, successFunction, options) {
    var returnFlag= false;;
    var defaultOptions = {
        errorFunction: errorFunction,
        successArguments: "",
        errorArguments: "",
        successMessage: "",
        type: "post",
        async: true,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        cache:false
    };
    var currentOptions = $.extend(defaultOptions, options);
    $.ajax({
        type: currentOptions.type,
        data: postData,
        contentType: currentOptions.contentType,
        dataType: currentOptions.dataType,
        url: getContextPath()+url,
        async: currentOptions.async,
        beforeSend: function () {
           
        },
        success: function (resultData) {
        	console.log(typeof resultData);
            if(typeof resultData =="string"){
                successFunction(resultData);
            }else{
                if(typeof resultData =="object") {
                    alert(resultData.resultMessage);
                }
            }
        },
        complete:function(){
            deleteLoading();
		},
        error: function (result) {
            returnFlag = false;
        }
    });
    return returnFlag;
};



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
function isLogin(result) {
    return (result === "99")
}
function errorFunction(resultData) {
showMessage(resultData);
}

function showMessage(resultData) {
alert(resultData.resultMessage);
}
//获取
function getContextPath() {
    // alert(document.location.pathname);
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
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
}
$.isEmpty = function(o){
	return o==null||o=="";
}
