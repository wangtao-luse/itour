//创建datagrid
function initDataGrid(url,dataGridNode,newOptions){
	//http://www.jeasyui.net/plugins/183.html
	var options ={		
		fitColumns:true,//自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
		pagination:true,//在数据网格（datagrid）底部显示分页工具栏
		idField:'id',//标识字段
		sortName:'id',//可以排序的列
		sortOrder:'asc',//指定排序的方式,只能用 'asc' 或 'desc'
		checkOnSelect:true,//当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。
		singleSelect:true,//只允许选中一行
		selectOnCheck:true,//如果设置为 true，点击复选框将会选中该行。如果设置为 false，选中该行将不会选中复选框。
		nowrap:true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
		pageNumber:1,//当设置了 pagination 属性时，初始化页码。
		pageSize:(newOptions.postData&&newOptions.postData.page&&newOptions.postData.page.size?newOptions.postData.page.size:"100"),//当设置了 pagination 属性时，初始化页面尺寸。
		pageList:[50, 100, 300, 500, 1000],//当设置了 pagination 属性时，初始化页面尺寸的选择列表。
		toolbar: '#toolbar',//数据网格（datagrid）面板的头部工具栏
		onLoadSuccess:function(data){
			//当数据加载成功时触发
		}
	};
	$.extend(options, newOptions);
	dataGridNode.datagrid(options);
	//获取当前datagrid的分页（pager）对象且使用 javascript 创建分页（pagination）
	dataGridNode.datagrid('getPager').pagination({
		total:newOptions.data.total,
		onSelectPage:function(pageNumber, pageSize){
			//当用户选择新的页面时触发
			//alert("当用户选择新的页面时触发");
			newOptions.postData.page.size = pageSize;
            newOptions.postData.page.current = pageNumber;
            newOptions.pageNumber = pageNumber;
            newOptions.pageSize = pageSize;
            initAjaxDataGrid(url, dataGridNode, newOptions)
		},
		onChangePageSize:function(pageSize){
			//当用户改变页面显示的条数时触发
			//alert("当用户改变页面尺寸时触发");
			newOptions.postData.page.size = pageSize;
            newOptions.pageSize = pageSize;			
		    initAjaxDataGrid(url, dataGridNode, newOptions)
		},
		onRefresh:function(){
			//刷新之后触发
		}
	});
	return dataGridNode;
	
}

function initAjaxDataGrid(url,dataGridNode,newOptions){
	    var options = {postData: {}};
	    $.extend(options, newOptions);
	    postAjax(url,JSON.stringify(options.postData),function(data){
	    	if(data.returnResult.result){
	    	  var formatData={};
	    	  if(data.returnResult.result.records) {
                  formatData.total = data.returnResult.result.total;
                  formatData.rows = data.returnResult.result.records;
                 // formatData={"total":"2","rows":[{"id":"01","name":"zhangsan","age":"18"},{"id":"02","name":"mk","age":"25"}]};
              }else{
                  internalError();
                  return;
              }
	    	}
	    	 options.data = formatData;	
	    	 //待研究
	    	 if ($.data(dataGridNode[0], "datagrid")) {
                 dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
             }
	    	 console.log(options);
	    	 initDataGrid(url,dataGridNode, options);
	    },{})
}
//查询
function searchFun(url,node,funcName,op){
   	//序列化表单
   	// {'current': 1, 'size': 40,'k':'hyhmd'}
    var formData = $.serializeObject($('#searchForm'));
    var postData ={"page":{}};
    op&&op.k?postData[op.k]=formData:'';
    postData.page.current=op&&op.current?op.current:"1";
    postData.page.size=op&&op.size?op.size:"100";
    var options = funcName({postData: postData});
    //待研究
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? postData.page.size = pageNode.val() : "100";
    initAjaxDataGrid(url,node,options);
    }
function showErrorMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'error')
};



//编辑单条只能传递一个参数
function editFun(title,url,dataGridNode,newOptions){
	var key = newOptions.key?newOptions.key:"id";
	if(newOptions[key]==undefined){
		//返回所有选中的行，当没有选中的记录时，将返回空数组
		var rows = dataGridNode.datagrid('getSelections');
		if (rows.length < 1) {
            showErrorMsg('请选择一条数据.');
            return
        } else {
            newOptions[key]= rows[0][key];
            url = url + newOptions[key]
        }
	}else{
		url = url + newOptions[key];
		//取消选中当前页所有的行
        dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
	}
	var options = getInitDialogOptions(title, url, "editFunction");
	$.extend(options,newOptions);
	//待研究
	options.dataGridNode = dataGridNode;
	parent.$.modalDialog(options)
	
}

//初始化对话框Option
function getInitDialogOptions(modelTitle,url,mainBtnId,newOptions){
	var options ={
			width:newOptions&&newOptions.width?newOptions.width:500,
			height:newOptions&&newOptions.height?newOptions.height:300,
			title:modelTitle,
			href: /*getContextPath() +*/ url,
			buttons: [{id: mainBtnId, text: "保存"}]
	};
	newOptions?$.extend(options,newOptions):'';
	return options;
	
}
//清楚查询条件
function clearFunction(node) {
    var clearNode = $("#" + node);
    clearNode.find('input').not("input[readonly]").not("input[type='checkbox']").not("input[type='radio']").val('');
    clearNode.find('.combo-text').val('');
    var selectNodes = clearNode.find('select');
    $.each(selectNodes, function (index, value) {
        $(this).find("option:eq(0)").attr("selected", true)
    });
    clearNode.find('input:checkbox').prop("checked", false);
    clearNode.find('select[name="sort"]').attr('disabled', true);
    var radioNode = clearNode.find('input:radio');
    if (radioNode && radioNode.length > 1) {
        var listFlagNode = clearNode.find("input:radio[name='listFlag']");
        var dingXiangFlag = clearNode.find("input:radio[name='customerNameFlag']");
        var paidStatusFlag = clearNode.find("input:radio[name='paidStatus']");
        var roadPackageTypeFlag = clearNode.find("input:radio[name='roadPackageType']");
        if (listFlagNode && listFlagNode.length > 1) {
            $(listFlagNode.get(0)).prop("checked", true);
            $(listFlagNode.get(0)).val("1")
        }
        if (roadPackageTypeFlag && roadPackageTypeFlag.length > 1) {
            $(roadPackageTypeFlag.get(0)).prop("checked", true);
            $(roadPackageTypeFlag.get(0)).val("0")
        }
        if (dingXiangFlag && dingXiangFlag.length > 1) {
            $(dingXiangFlag.get(0)).prop("checked", true);
            $(dingXiangFlag.get(0)).val("")
        }
        if (paidStatusFlag && paidStatusFlag.length > 1) {
            $(paidStatusFlag.get(0)).prop("checked", true);
            $(paidStatusFlag.get(0)).val("")
        }
    }
}

//生成左侧菜单
function initWestTree(url,treeNode,newOptions){
	var successOptions = {treeNode: treeNode, newOptions: newOptions};
	var options={
			onClick:function(node){
				var $this = $(this);
				var target = node.target;
				var len = $this.tree('getChildren', target).length;
				 if ($this.tree('getChildren', target).length) {
	                    $this.tree("toggle", target)
	                }else{
	                	if (node.attributes && node.attributes.url) {
	                        var url;
	                        if (node.attributes.url.indexOf('/') == 0) {
	                            url =/* getContextPath() +*/ node.attributes.url
	                        }
	                    } else {
	                        url = node.attributes.url
	                    }
	                	addTab({url:url,title:node.text});
	                }
				
				
				
			}	
	}
	$.extend(options,newOptions);
	successOptions.newOptions=options;
	postAjax(url,null,createWestTree,{successArguments: successOptions, type: 'get', async: false})
}
function createWestTree(data,successOptions){
	var options ={data:data.returnResult.result};
	 $.extend(options, successOptions.newOptions);
	 //生成tree
	 successOptions.treeNode.tree(options);
	
}
function addTab(params){
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
	        parent.$.messager.progress('close')
	    } else {
	        t.tabs('add', opts)
	    }
}
//自动填充表单函数
function loadData(obj,pNode) {
    var key, value, tagName, type, arr;
    for (x in obj) {
        key = x;
        value = obj[x];
        pNode.find("[name='" + key + "']").each(function () {
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName == 'INPUT') {
                if (type == 'radio') {
                    $(this).attr('checked', $(this).val() == value)
                } else if (type == 'checkbox') {
                } else {
                    $(this).val(value)
                }
            } else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
                $(this).val(value)
            } else if (tagName == 'IMG') {
                $(this).attr('src', value);
            }
        })
    }
}
function initSelect(selectNode, data, flag, title) {
    var showTitle = title ? title : "请选择";
    var str = flag ? "<option value=''>" + showTitle + "</option>" : "";
    $.each(data, function (key, value) {
        key.indexOf("#") != -1?
        str += "<option value='" + key.substr(1,value.length) + "' >" + value + "</option>":
            str += "<option value='" + key + "' >" + value + "</option>"
    });
    selectNode.html(str)
};