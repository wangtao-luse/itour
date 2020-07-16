//创建datagrid
function initDataGrid(url,dataGridNode,newOptions){
	//http://www.jeasyui.net/plugins/183.html
	var options ={
		width:'auto',
		height:'auto',
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
	    	//https://www.cnblogs.com/polk6/p/js-var.html(JavaScript 变量作用域)
	    	 options.data = formatData;	
	    	 console.log("formatData------>");
	    	 console.log(formatData);
	    	 //待研究
	    	 if ($.data(dataGridNode[0], "datagrid")) {
                 dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
             }
	    	 console.log(options);
	    	 initDataGrid(url,dataGridNode, options);
	    },{})
}
function initAjaxTotalDataGrid(url, dataGridNode, newOptions) {
    var options = {postData: {}};
    $.extend(options, newOptions);
    postAjax(url, JSON.stringify(options.postData), function (data) {
        if(data.returnResult){
            if(data.returnResult.page.records){
                var formatData = {};
                formatData.total = data.returnResult.page.total;
                formatData.rows = data.returnResult.page.records;
                formatData.sum = data.returnResult.sum;
                options.data = formatData;
                if ($.data(dataGridNode[0], "datagrid")) {
                    dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
                }
                if (options.successFunction) {
                    options.successFunction(data)
                }
                initDataTotalGrid(url, dataGridNode, options)
            }else{
                internalError();
            }
        }else{
            internalError();
        }

    }, {})
}
function initDataTotalGrid(url, dataGridNode, newOptions) {
	console.log(newOptions);
    var options = {
        fit: true,
        fitColumns: true,
        border: false,
        pagination: true,
        width: 'auto',
        height: 'auto',
        idField: 'id',
        sortName: 'id',
        sortOrder: 'asc',
        checkOnSelect: true,
        singleSelect: true,
        selectOnCheck: true,
        nowrap: true,
        pageNumber: 1,
        pageSize: (newOptions.postData && newOptions.postData.page && newOptions.postData.page.size) ? newOptions.postData.page.size : "100",
        pageList: [50, 100, 300, 500, 1000],
        toolbar: '#toolbar',
        onLoadSuccess: function () {
            $('#searchForm table').show();
            parent.$.messager.progress('close')
        }
    };
    $.extend(options, newOptions);
    dataGridNode.datagrid(options);
    /*dataGridNode.datagrid('doCellTip', {
        onlyShowInterrupt: true,
        position: 'bottom',
        maxWidth: '200px',
        tipStyler: {'backgroundColor': '#fff000', borderColor: '#ff0000', boxShadow: '1px 1px 3px #292929'}
    });*/
    dataGridNode.datagrid('getPager').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            newOptions.postData.page.size = pageSize;
            newOptions.postData.page.current = pageNumber;
            newOptions.pageNumber = pageNumber;
            newOptions.pageSize = pageSize;
            initAjaxTotalDataGrid(url, dataGridNode, newOptions)
        }, onChangePageSize: function (pageSize) {
            newOptions.postData.page.size = pageSize;
            newOptions.pageSize = pageSize;
            initAjaxTotalDataGrid(url, dataGridNode, newOptions)
        }, onRefresh: function () {
        }
    });
    return dataGridNode
}
//查询
function searchFun(url,node,funcName,op){
   	//序列化表单
   	// {'current': 1, 'size': 40,'k':'hyhmd'}
    var formData = $.serializeObject($('#searchForm'));
    op&&op.nodeNames && op.nodeNames.length > 0?
            $.each(op.nodeNames, function (index, value) {
                postData[value] = $("#" + op.nodes[index]).val();
            }):"";
    var postData ={"page":{}};
    op&&op.k?postData[op.k]=formData:'';
    postData.page.current=op&&op.current?op.current:"1";
    postData.page.size=op&&op.size?op.size:"100";
    var options = funcName({postData: postData});
    //为了在搜索的时候保证size的正确性：获取分页中的size
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? postData.page.size = pageNode.val() : "100";
    initAjaxDataGrid(url,node,options);
    }
function searchFunTotal(url, node, funcName, ops) {
    var formData = $.serializeObject($('#searchForm'));
    ops&&ops.nodeNames && ops.nodeNames.length > 0?
        $.each(ops.nodeNames, function (index, value) {
            postData[value] = $("#" + ops.nodes[index]).val();
        }):"";
    var postData={"page":{}};
    ops&&ops.k?postData[ops.k] = formData:"";
    postData.page.current = ops&&ops.c?ops.c:"1";
    postData.page.size = ops&&ops.s? ops.s:"100";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? postData.page.size = pageNode.val() : "100";
    initAjaxTotalDataGrid(url, node, funcName({postData: postData}))
}
function showErrorMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'error')
};

function addSubFun(title, url, dataGridNode, newOptions) {
    var options = getInitDialogOptions(title, url, "addFunction", title);
    $.extend(options, newOptions);
    parent.$.modalSubDialog(options)
}
function addFun(title,url,dataGridNode,newOptions){
	var options = getInitDialogOptions(title, url, "addFunction");
	options.buttons[0].handler = function(){
		parent.$.modalDialog.openner_treeGrid = dataGridNode
	}
	 newOptions && newOptions.treeGridFlag ? "" : options.dataGridNode = dataGridNode;
	    options.currentRefresh = true;
	    $.extend(options, newOptions);
	    parent.$.modalDialog(options);
}

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
function editfunArr(title, url, dataGridNode, newOptions) {
	var key = newOptions.key?newOptions.key:"id";
	var arr= new Array();
    var rows = dataGridNode.datagrid('getSelections');
    if (rows && rows.length > 0) {
    	for(var i=0;i<rows.length;i++){
    		arr.push(rows[i][key]);
    	}
    	url+=arr;
    } else {
        showErrorMsg('请选择一条数据.');
        return;
    }
    var options = getInitDialogOptions(title, url, "editFunction", "编辑");
    $.extend(options, newOptions);
    options.dataGridNode = dataGridNode;
    parent.$.modalDialog(options) 
}
function grantFunCommon(title, url, dataGridNode, newOptions) {
    var rows = dataGridNode.datagrid('getSelections');
    if (rows && rows.length > 0) {
        var options = {
            title: title,
            width: newOptions&&newOptions.width?newOptions.width:500,
            height: newOptions&&newOptions.height?newOptions.height:300,
            href: /*getContextPath() + */url + rows[0].id,
            data: {'id': rows[0].id}
        }
        $.extend(options, newOptions);
        parent.$.modalDialog(options);
    } else {
        showErrorMsg('请选择一条数据.')
    }
}
/**
 * 
 * @param title
 * @param url
 * @param param
 * @param dataGridNode
 * @param newOptions
 * @returns
 */
function editFuns(title, url, param, dataGridNode, newOptions) {
	var strs= new Array();
    var rows = dataGridNode.datagrid('getSelections');
    if (rows.length < 1) {
        showErrorMsg('请选择一条数据.');
        return
    }
    var jsonStr=JSON.stringify(rows[0]);
    var jsObject = JSON.parse(jsonStr);
    if (rows && rows.length > 0) {
        strs=param.split(",");
    	for(var i=0;i<strs.length;i++){
    		if(i==0){
    		 url=url+"?" + strs[i] +"="+eval('jsObject.'+strs[i]);
    		}else{
    		 url=url+"&" + strs[i] +"="+eval('jsObject.'+strs[i]);
    		}
    	}
    } else {
        showErrorMsg('请选择一条数据.');
        return;
    }
    var options = getInitDialogOptions(title, url, "editFunction", "编辑");
    $.extend(options, newOptions);
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
	                    }
	                	addTab({url:url,title:node.text});
	                }
				
				
				
			}	
	}
	$.extend(options,newOptions);
	successOptions.newOptions=options;
	postAjax(url,null,createWestTree,{successArguments: successOptions, type: 'get', async: false})
}
//授权角色
function initTree(url,postData, treeNode, newOptions) {
    var successOptions = {treeNode: treeNode, newOptions: newOptions};    
        var options = {
            onClick: function (node) {
              /*  if (node.attributes && node.attributes.url) {
                    var url;
                    if (node.attributes.url.indexOf('/') == 0) {
                        url = getContextPath() + node.attributes.url
                    }
                } else {
                    url = node.attributes.url
                }
                addTab({url: url, title: node.text})*/
            }
        };
        $.extend(options, newOptions);
        successOptions.newOptions = options;
        postAjax(url, postData, createTree, {successArguments: successOptions, async: false})
    
}
function createWestTree(data,successOptions){
	var options ={data:data.returnResult.result};
	 $.extend(options, successOptions.newOptions);
	 //生成tree
	 successOptions.treeNode.tree(options);
	
}
function createTree(data, successOptions) {
	console.log("--------Tree-----");
	console.log(data)
	
    var options = {data: data.returnResult.result, parentField: 'pid'};
    $.extend(options, successOptions.newOptions);
    successOptions.treeNode.tree(options)
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
function initSelect(selectNode, key, flag, title) {
	var data = {"setCode":key};
	$.ajax({
	    type: "POST",
	    data: JSON.stringify(data),
	    contentType: "application/json",
	    dataType: "json",
	    url: "/dictionary/getDictData",
	    success: function (resultData) {
	    	var showTitle = title ? title : "请选择";
	    	var str = flag ? "<option value=''>" + showTitle + "</option>" : "";
	    	 var data = resultData.returnResult.result;
	    	$.each(data, function (key, item) {
	    		item.CNAME.indexOf("#") != -1?
	    				str += "<option value='" + item.CNAME.substr(1,item.NAME.length) + "' >" + item.CNAME + "</option>":
	    					str += "<option value='" + item.CODE + "' >" + item.CNAME + "</option>"
	    	});
	    	selectNode.html(str)  
	    }
	});
	
};
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
//获取tree选中的组Ids和角色Ids（'getChecked', 'indeterminate'）
function getTreeCheckedGidsAndRids(node) {
    var nodes1 = node.tree('getChecked',['checked','indeterminate']);
    var nodes2 = node.tree('getChecked', 'unchecked');
    var checknodes = $.merge(nodes1, nodes2);
    var arr = [];   
    if (checknodes && checknodes.length > 0) {
        for (var i = 0; i < checknodes.length; i++) {
        	 var gr={};
        	 if(checknodes[i].gid!=undefined){
        		 gr.groupId=checknodes[i].gid;
        		 gr.roleId=checknodes[i].id;
        		 gr.checked=checknodes[i].checked;
        		 gr.grNo=checknodes[i].grNo;
        		 arr.push(gr);
        	 }        	
           
        }
    }
    return arr;
}
function getTreeGroupIds(node) {
    var nodes1 = node.tree('getChecked',['checked','indeterminate']);
    var nodes2 = node.tree('getChecked', 'unchecked');
    var checknodes = $.merge(nodes1, nodes2);
    var arr = [];   
    if (checknodes && checknodes.length > 0) {
        for (var i = 0; i < checknodes.length; i++) {
        	 var group={};
        	 group.groupId=checknodes[i].id;
        	 if(checknodes[i].checked!=undefined){
        		 group.checked=checknodes[i].checked;
        	 }else{
        		 group.checked=false;
        	 }        	
        	 arr.push(group);
        }
    }
    return arr;
}
function getTreeCheckedIds(node) {
    var nodes1 = node.tree('getChecked');
    var nodes2 = node.tree('getChecked', 'indeterminate');
    var checknodes = $.merge(nodes1, nodes2);
    var ids = [];
    if (checknodes && checknodes.length > 0) {
        for (var i = 0; i < checknodes.length; i++) {
            ids.push(checknodes[i].id)
        }
    }
    return ids;
}
function getTreeRoleidsAndRightids(node) {
    var nodes1 = node.tree('getChecked',['checked','indeterminate']);
    var nodes2 = node.tree('getChecked', 'unchecked');
    var checknodes = $.merge(nodes1, nodes2);
    var arr = [];   
    if (checknodes && checknodes.length > 0) {
        for (var i = 0; i < checknodes.length; i++) {
        	 var rr={};
        		 rr.rightId=checknodes[i].menuNo;
        		 rr.checked=checknodes[i].checked;
        		 rr.rrid=checknodes[i].rrid;
        		 arr.push(rr);
           
        }
    }
    return arr;
}
$.extend({
    remind: function (options) {
        var _msg = '';
        if (options.icon != undefined) {
            _msg += '<div class="messager-icon messager-' + options.icon + '"></div>'
        }
        if (options.msg != undefined) {
            _msg += '<div style="word-break : break-all;">' + options.msg + '</div>'
        }
        _msg += '<div style="clear:both;"></div>';
        _msg += '<div class="messager-button" style="padding-top:0"><a href="javascript:void(0)" onclick="hideSuccess(this)" class="l-btn" group="" id="" style="margin-left: 10px;"><span class="l-btn-left"><span class="l-btn-text">确定</span></span></a></div>';
        options.msg = _msg;
        $.messager.show(options)
    }
});
function hideSuccess(that) {
    var thatT = $(that);
    thatT.parent().parent().siblings().find('.panel-tool-close').click()
}
function showSuccessMsg(title) {
    $.remind({
        title: "成功提示",
        icon: "info",
        msg: title,
        width: 300,
        height: 160,
        timeout: 2000,
        style: {right: '', bottom: ''}
    })
}
function closeDialogCallBack(data) {
    $("div.panel-tool .panel-tool-close").click();
    showSuccessMsg(data.resultMessage);
}
//数据填充
function loadContainerWrapperData(obj,pNode) {
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
/**
 * 格式化日期 EasyUI使用
 * https://www.ynicp.com/news/content/441.html
 */
Date.prototype.Format = function (fmt) { //author: meizz 	
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}