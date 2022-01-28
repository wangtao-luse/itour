var postAjax = function(url,successFunction,options){
	 var returnData;
	var defaultOption = {
			type : "post",
			async : false,
			contentType : 'application/json; charset=utf-8',
			dataType : "json",
			errorFunction : errorFunction,
			successArguments:""
		}	
	var currentOptions = $.extend(defaultOption,options);
	$.ajax({
		type : currentOptions.type,
		url : url,
		async : currentOptions.async,
		contentType : currentOptions.contentType,
		dataType : currentOptions.dataType,
		beforeSend :function(){
			$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
		},
		success : function(resultData) {
			$.messager.progress('close');
			if (resultData&&isSuccess(resultData)) {
				if (currentOptions&&currentOptions.successArguments){
					successFunction(resultData,currentOptions.successArguments);
					resultData = resultData.data;
					 return returnData;
				}else{
					successFunction(resultData);
					resultData = resultData.data;
					 return returnData;	
				}
				
			} else {
				if (resultData){
				  defaultOption.errorFunction(resultData);	
				} else {
					internalError();
				}
				
			}
		},
		complete : function(){},
		error : function(){
			$.messager.progress('close');
			internalError();
			return;
		}
		
	});
	return resultData;
}
function internalError() {
    showErrorMsg(500 + " " + "程序内部错误")
}
function errorFunction(resultData){
	$.messager.alert('提示', resultData.description, 'info')
}
function isSuccess(resultData){
	return "10" == resultData.resultCode;
}
function initWestTree(url,treeNode,newOptions){
	var sucessOption = {"treeNode":treeNode};
	$.extend(sucessOption,newOptions);
	postAjax(url,createWestTree,{successArguments:sucessOption});
	
}
function createWestTree(resultData,sucessOptions){
	var options = {"data":resultData.data};
	$.extend(options,sucessOptions);
	sucessOptions.treeNode.tree(options);
	
	
}