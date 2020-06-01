//对象数组转json
//由于不支持传入‘'’,所以在转json的时候做过滤
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

//过滤掉"'"字符
function filterSpecial(value) {
    return value.replace(/[']/g, '')
}

var postAjax = function(url,postData,successFunction,options){
	var returnData;
	var defaultOptions = {
        errorFunction: errorFunction,
        successArguments: "",
        errorArguments: "",
        successMessage: "",
        type: "post",
        async: true,
        contentType: "application/json; charset=utf-8",//请求的数据格式
        dataType: "json",//预期服务器返回的数据类型
        cache:false
    };
    var currentOptions = $.extend(defaultOptions,options);
    $.ajax({
    	type: currentOptions.type,
        data: postData,
        contentType: currentOptions.contentType,
        dataType: currentOptions.dataType,
        url: url,
        async: currentOptions.async,
    	beforeSend:function(){
    		$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
    		
    	},
    	success:function(resultData){
    		$.messager.progress('close');
    		
    		if(resultData&&isSuccess(resultData)){
    			if(currentOptions&&currentOptions.successArguments){
    				successFunction(resultData,currentOptions.successArguments);
        			returnData=resultData;
    			}else{
    				successFunction(resultData);
        			returnData=resultData;
    			}
    			
    		}else{
    			 $.messager.progress('close');
    			defaultOptions.errorFunction(resultData);
    			
    		}
    	},
    	complete: function(){
    		
    	},
    	error:function(resultData){
    		$.messager.progress('close');
    		defaultOptions.errorFunction(resultData);
    		return;
    	}
    });
    return returnData;
}
function isSuccess(result){
	return result.resultCode=="10";
}
function errorFunction(resultData) {
    $.messager.alert('提示', resultData.resultMessage, 'info')
}
function internalError() {
    showErrorMsg(500 + " " + "程序内部错误")
}

function getContextPath() {
    // alert(document.location.pathname);
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}