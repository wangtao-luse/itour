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
					returnData = resultData.data;
					 return returnData;
				}else{
					successFunction(resultData);
					returnData = resultData.data;
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
	return returnData;
}
function internalError() {
    showErrorMsg(500 + " " + "程序内部错误")
}
function errorFunction(resultData){
	$.messager.alert('提示', resultData.description, 'info')
}
function showErrorMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'error')
};
function showInfoMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'info')
};
function isSuccess(resultData){
	return "10" == resultData.code;
}
function getContextPath() {
    // console.log(document.location.pathname);
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}
//初始左边的Tree
function initWestTree(url,treeNode,newOptions){
	var sucessOption = {treeNode:treeNode,newOptions:newOptions};
	
	var options ={
			onClick: function(node){
				 addTab({url: url, title: node.text})
			}
	}
	
	$.extend(options,newOptions);
	sucessOption.newOptions = options;
	postAjax(url,createWestTree,{successArguments:sucessOption});
	
}
function createWestTree(resultData,sucessOptions){
	var options = {"data":resultData.data};
	console.log(resultData.data);
	$.extend(options,sucessOptions);
	sucessOptions.treeNode.tree(options);
	
	
}
function addTab(params) {
    var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>';
    var t = $('#index_tabs');
    var opts = {
        title: params.title,
        closable: true,
        iconCls: params.iconCls,
        content: iframe,
        border: false,
        fit: true
    };
    if (t.tabs('exists', opts.title)) {
        t.tabs('close', opts.title);
        t.tabs('add', opts);
        $.messager.progress('close')
    } else {
        t.tabs('add', opts)
    }
}