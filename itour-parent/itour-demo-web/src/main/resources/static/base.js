//对Ajax进行封装
var postAjax = function(url,postData,successFunction,options){
	 var returnData;
	var defaultOption = {
			type : "post",
			async : true,
			contentType : 'application/json; charset=utf-8',
			dataType : "json",
			errorFunction : errorFunction,
			successArguments:""
		}	
	var currentOptions = $.extend(defaultOption,options);
	$.ajax({
		type : currentOptions.type,
		url : getContextPath() + url,
		data:postData,
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
//内部错误提示信息
function internalError() {
    showErrorMsg(500 + " " + "程序内部错误")
}
//程序报错信息
function errorFunction(resultData){
	$.messager.alert('提示', resultData.description, 'info')
}
//错误信息提示
function showErrorMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'error')
};
//提示信息提示
function showInfoMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'info')
};
//调用是否成果
function isSuccess(resultData){
	return "10" == resultData.code;
}
//获取项目名
function getContextPath() {
    // console.log(document.location.pathname);
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}
/*******************************************Tree************************************************/
//初始左边的Tree
function initWestTree(url,treeNode,newOptions){
	var sucessOption = {treeNode:treeNode,newOptions:newOptions};
	
	var options ={
			onClick: function(node){
				//console.log(node.text);
				var $this = $(this);
				var target = node.target;
				var id = node.id;
				if ($this.tree('getChildren', target).length) {
					$this.tree("toggle", target);
	            } else{
	            	if (node.attributes && node.attributes.url){
	            		var url;
                        if (node.attributes.url.indexOf('/') == 0) {
                            url = getContextPath() + node.attributes.url;
                        }
	            	} else {
	            		url = node.attributes.url;
	            	}
	            	addTab({url: url, title: node.text,id:id})
	            }
				 
			}
	}
	
	$.extend(options,newOptions);
	sucessOption.newOptions = options;
	postAjax(url,null,createWestTree,{successArguments:sucessOption, async: false});
	
}
//初始左边的Tree成功后的回调函数
function createWestTree(resultData,sucessOptions){
	var options = {data:resultData.data};
	console.log(options);
	$.extend(options,sucessOptions.newOptions);
	sucessOptions.treeNode.tree(options);
	
	
}

/*********************************************************************选项卡Tab*****************************************************/
//添加tab页
function addTab(params) {
    var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>';
    var t = $('#index-tabs');
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
/***********************************************************************DataGrid********************************************************/
//封装DataGrid带分页
function initDataGrid(url,node,newOptions){
	var options = {
		fit:true,
		border:false,
		width:'auto',
		height:'auto',
		fitColumns:true,
		pagination:true,
		idField:'id',
		sortName:'id',
		sortOrder:'asc',
		checkOnSelect:true,
		singleSelect:true,
		selectOnCheck:true,
		nowrap:true,
		pageNumber:1,
		pageSize:(newOptions.postData&&newOptions.postData.page&&newOptions.postData.page.size)?newOptions.postData.page.size:10,
		pageList: [10, 50, 100, 300, 500, 1000],
		toolbar:'#toolbar',
		onLoadSuccess:function(){}
	}
	$.extend(options,newOptions);
	node.datagrid(options);
	node.datagrid('getPager').pagination({
		onSelectPage : function(pageNumber, pageSize){//当用户选择新的页面时触发
			//设置分页（查询）
			newOptions.postData.page.size = pageSize;
			newOptions.postData.page.current = pageNumber;
			//设置页面页码
			newOptions.pageNumber = pageNumber;
			newOptions.pageSize = pageSize;
			initAjaxDataGrid(url,node,newOptions);
		},
		onChangePageSize : function(pageSize){
			newOptions.postData.page.size = pageSize;
			newOptions.pageSize = pageSize;
			initAjaxDataGrid(url,node,newOptions);
		},
		onBeforeRefresh : function(pageNumber, pageSize){
			
		},
		onRefresh:function(pageNumber, pageSize){
			
		}
	})
	}
//对DataGrid进行Ajax封装
function initAjaxDataGrid(url,node,newOptions){
	var options = {'postData':{}};
	$.extend(options, newOptions);
	postAjax(url, JSON.stringify(options.postData),function(result){
		console.log(result);
		if(result){
			var formatData = {};
			formatData.total = result.data.total;
			formatData.rows = result.data.records;
			options.data = formatData;				
			initDataGrid(url,node,options);
			
			
		}else{
			internalError();
		}
	},{});
	
}
//封装dataGrid实现查询
function searchFunc(url,node,funcName,ops){
	  var formData = $.serializeObject($('#searchForm'));
	  var postData = {"page":{}};
	  ops&&ops.k?postData[ops.k] = formData:"";
	  postData.page.size = ops&&ops.s?ops.s:"10";
	  postData.page.current = ops&&ops.c?ops.c:"1";
	  var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
	  pageNode.length > 0 ? postData.page.size = pageNode.val() : "10";
	  initAjaxDataGrid(url,node,funcName({"postData":postData}));
	}
//0,1,2..的对象数组转json
//由于后台不支持传入‘'’,所以在转json的时候做过滤
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

/*****************************************************Dialog************************************************/
function addFun(url,title,dataGridNode,newOptions){
	var options = initDialogOptions(url,title,"addFunction",newOptions);
	options.buttons[0].handler = function(){
		
	}
}

function initDialogOptions(url,title,btnId,newOptions){
	 var options = {
		        width: newOptions&&newOptions.width?newOptions.width:500,
		        height: newOptions&&newOptions.height?newOptions.height:300,
		        title: title,
		        href: getContextPath() + url,
		        buttons: [{id: mainBtnId, text: "保存"}]
		    };
	 newOptions ? $.extend(options, newOptions) : "";
	 return options;
	
}

