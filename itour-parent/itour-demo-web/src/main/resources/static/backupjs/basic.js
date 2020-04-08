//计算选中合计 field可以数组
function calculateZJ(node, field, showNode) {
    var rows = node.datagrid("getChecked");
    var total = 0;
    $.each(rows, function (index, value) {
        total = parseFloat(total).add(parseFloat(value[field]));
    });
    $("#" + showNode).html(total);
}

function resourceCalculateTotalS() {
    var rows = $('#dataGrid').datagrid("getChecked");
    var total = 0;
    var tAmount = 0;
    $.each(rows, function (index, value) {
        total = parseFloat(total).add(parseFloat(value["listNumber"]));
        var JE = (value["listNumber"].mul(value["listPrice"])).toFixed(2);
        tAmount = JE.add(tAmount)
    });
    $("#" + "guaPaiLiangHJ").html(total);
    $("#" + "guaPaiJE").html(tAmount)
}

function initTotalTiDanAdBottom() {
    $("div[dgflag='dg'] .pagination-info").after("<div class='tableBottomTotal'><span >&nbsp;合计：</span><span id='tiDanZLHJ' class='redSharp bold'>0</span></div>");
}

function initTotalDreaftZhongLiangJianShu() {
    var tHtml = "<div class='tableBottomTotal'><span>选中合计&nbsp;重量：</span><span id='sourceZhongLiangZJ' class='redSharp bold'>0</span><span>&nbsp;件数：</span><span id='sourceJianShuZJ' class='redSharp bold'>0</span></div>";
    $("#saleSourceAddSub .pagination-info").after(tHtml);
}

function initTotalTouFangLiang() {
    var tHtml = "<div class='tableBottomTotal'><span>采购金额：</span>" + "<span id='purchaseAmountZJ' class='redSharp bold'></span></div>" + "<div class='tableBottomTotal'><span>总合计&nbsp;重量：</span><span id='touFangLiangZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>采购金额：</span>" + "<span id='purchaseAmountBYZJ' class='redSharp bold'></span></div>" + "<div class='tableBottomTotal'><span>当前页合计&nbsp;重量：</span><span id='touFangLiangBYZJ' class='redSharp bold'>0</span></div>";
    $("div[dgflag='dg'] .pagination-info").after(tHtml)
}

function initTotalGuaPaiLiang() {
    var tHtml = "<div class='tableBottomTotal'><span>总合计&nbsp;重量：</span><span id='touFangLiangZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>当前页合计&nbsp;重量：</span><span id='touFangLiangBYZJ' class='redSharp bold'>0</span></div>";
    $("div[dgflag='dg'] .pagination-info").after(tHtml)
}

function initTotalZhongLiangJianShu() {
    var tHtml = "<div class='tableBottomTotal'><span>&nbsp;重量：</span><span id='zhongLiangZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>总合计&nbsp;件数：</span> <span id='jianShuZJ' class='redSharp bold'>0</span></div>" + " <div class='tableBottomTotal'><span>&nbsp;重量：</span> <span id='zhongLiangBYZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>当前页合计&nbsp;件数：</span> <span id='jianShuBYZJ' class='redSharp bold'>0</span></div>";
    $("div[dgflag='dg'] .pagination-info").after(tHtml)
}

function initTotalZZhongLiangZZhongJinE() {
    var tHtml = "<div class='tableBottomTotal'><span>&nbsp;总金额：</span>" + "<span id='zongJinEZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>总合计&nbsp;总重量：</span>" + "<span id='zongZhongLiangZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;总金额：</span>" + "<span id='zongJinEBYZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>当前页合计&nbsp;总重量：</span>" + "<span id='zongZhongLiangBYZJ' class='redSharp bold'></span></div>";
    $("div[dgflag='dg'] .pagination-info").after(tHtml)
}

function initTotalJSHJ() {
    var tHtml = "<div class='tableBottomTotal'><span>&nbsp;税额：</span>" + "<span id='suiEZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;不含税金额：</span>" + "<span id='buHanJEZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;含税金额：</span>" + "<span id='hanJEZJ' class='redSharp bold'>0</span></div>" + "<div class='tableBottomTotal'><span>总合计&nbsp;结算总量：</span>" + "<span id='jsZLZJ' class='redSharp bold'>0</span></div>" + "</div>";
    $("div[dgflag='dg'] .pagination-info").after(tHtml)
}

function initTotalYLHJ(dgpje,dgpzl,gpzje,gpzl) {
	var tHtml = "<div class='tableBottomTotal'><span>&nbsp;待挂牌总金额：</span>" + "<span id='dgpje' class='redSharp bold'>"+ dgpje+"</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;待挂牌总重量：</span>" + "<span id='dgpzl' class='redSharp bold'>"+ dgpzl +"</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;挂牌总金额：</span>" + "<span id='gpzje' class='redSharp bold'>"+ gpzje +"</span></div>" + "<div class='tableBottomTotal'><span>总合计&nbsp;挂牌总重量：</span>" + "<span id='gpzl' class='redSharp bold'>"+ gpzl +"</span></div>" + "</div>";
    $("#gpwzyl .pagination-info").after(tHtml);
}
function initTotalTHD(zzl,zsl) {
	var tHtml = "<div class='tableBottomTotal'><span>&nbsp;</span>" + "<span id='dgpje' class='redSharp bold'>"+"</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;总数量</span>" + "<span id='dgpzl' class='redSharp bold'>"+zsl+"</span></div>" + "<div class='tableBottomTotal'><span>&nbsp;</span>" + "<span id='gpzje' class='redSharp bold'>"+"</span></div>" + "<div class='tableBottomTotal'><span>总合计&nbsp;总重量：</span>" + "<span id='gpzl' class='redSharp bold'>" +zzl+"</span></div>" + "</div>";
	$("#thd .pagination-info").after(tHtml);
}

function initTotal(m) {
    var tHtml = "";
    m.forEach(function (value, key, map) {
        tHtml += "<div class='tableBottomTotal'>"+key+"：<span class='redSharp bold'>"+value+"&nbsp;</span></div>";
    })
    $("div[dgflag='dg'] .pagination-info").after(tHtml);
}

//整页刷新
function refreshIndexPage() {
    location.replace(getContextPath() + '/index.html');
}

//税额计算方法
function calculateTax(total, taxRate) {
    return (total * (taxRate) / (1 + parseFloat(taxRate)));
}

//获取税率
function getTaxRate() {
    return parent.taxRate;
}

function getInitDialogOptions(modelTitle, url, mainBtnId, mainBtnText, newOptions) {
    var options = {
        width: newOptions&&newOptions.width?newOptions.width:500,
        height: newOptions&&newOptions.height?newOptions.height:300,
        title: modelTitle,
        href: getContextPath() + url,
        buttons: [{id: mainBtnId, text: "保存"}]
    };
    newOptions ? $.extend(options, newOptions) : "";
    return options
}

function addFun(title, url, dataGridNode, newOptions) {
    var options = getInitDialogOptions(title, url, "addFunction", title);
    options.buttons[0].handler = function () {
        parent.$.modalDialog.openner_treeGrid = dataGridNode
    };
    newOptions && newOptions.treeGridFlag ? "" : options.dataGridNode = dataGridNode;
    options.currentRefresh = true;
    $.extend(options, newOptions);
    parent.$.modalDialog(options)
}

//两级菜单
function addSubFun(title, url, dataGridNode, newOptions) {
    var options = getInitDialogOptions(title, url, "addFunction", title);
    $.extend(options, newOptions);
    parent.$.modalSubDialog(options)
}

function editFun(title, url, dataGridNode, newOptions) {
    var key = newOptions.key?newOptions.key:"id";
    if (newOptions[key] === undefined) {
        var rows = dataGridNode.datagrid('getSelections');
        if (rows.length < 1) {
            showErrorMsg('请选择一条数据.');
            return
        } else {
            newOptions[key]= rows[0][key];
            url = url + newOptions[key]
        }
    } else {
        url = url + newOptions[key];
        dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
    }
    var options = getInitDialogOptions(title, url, "editFunction", "编辑");
    $.extend(options, newOptions);
    options.dataGridNode = dataGridNode;
    parent.$.modalDialog(options)
}

/**
 * @param title  请求标题
 * @param url   请求URL
 * @param param  请求参数 可以多个,格式为:  'id,name,...'
 * @param dataGridNode  选中表格行对象
 * @param newOptions  自定义弹出框属性
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

function printFun(url, dataGridNode) {
    var rows = dataGridNode.datagrid('getSelections');
    if (rows.length < 1) {
        showErrorMsg('请选择一条数据.');
        return
    } else {
        parent.window.open(getContextPath() + url + rows[0].id)
    }
}

function editSubFun(title, url, dataGridNode, newOptions) {
    if (newOptions.id === undefined) {
        var rows = dataGridNode.datagrid('getSelections');
        if (rows.length < 1) {
            showErrorMsg('请选择一条数据.');
            return
        } else {
            newOptions.id = rows[0].id;
            url = url + newOptions.id
        }
    } else {
        url = url + newOptions.id;
        dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
    }
    var options = getInitDialogOptions(title, url, "editFunction", "编辑");
    $.extend(options, newOptions);
    options.dataGridNode = dataGridNode;
    parent.$.modalSubDialog(options)
}

function setCBDefault(url, dataGridNode, options) {
    var rows = dataGridNode.datagrid('getSelections');
    if (rows.length < 1) {
        showErrorMsg('请选择一条数据.');
        return
    } else {
        $.post(getContextPath() + url, {id: rows[0].id}, function (result) {
            parent.$.messager.progress('close');
            if (result && isSuccess(result.resultcode)) {
                dataGridNode.datagrid('getPager').pagination('select', 1);
                dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
            }
            showSuccessMsg(result.description)
        }, 'JSON')
    }
}

function deleteFunById(title, url, dataGridNode, newOptions) {
    var options = {width: 500, height: 300};
    $.extend(options, newOptions);
    if (options.id === undefined) {
        var rows = dataGridNode.datagrid('getSelections');
        if (rows.length < 1) {
            showErrorMsg('请选择一条数据.');
            return
        } else {
            options.id = rows[0].id
        }
    } else {
        dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
    }
    parent.$.messager.confirm('询问', title, function (b) {
        if (b) {
            parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
            $.post(getContextPath() + url, {id: options.id}, function (result) {
                parent.$.messager.progress('close');
                if (result && isSuccess(result.resultcode)) {
                    dataGridNode.datagrid('getPager').pagination('select', 1);
                    dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
                }
                showSuccessMsg(result.description)
            }, 'JSON')
        }
    })
}

function grantFun(title, url, dataGridNode, newOptions) {
    var rows = dataGridNode.datagrid('getSelections');
    if (rows && rows.length > 0) {
        var key = newOptions.key?newOptions.key:"id";
        var dgKey = newOptions.dgKey?newOptions.dgKey:"id";
        var options = {width: 500, height: 300};
        $.extend(options, newOptions);
        if (!options.buttonId) {
            options.buttonId = "grantsFunction"
        }
        if (options[key] == undefined) {
            options[key] = rows[0][dgKey];
        } else {
            dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
        }
        parent.$.modalDialog({
            title: title,
            width: options.width,
            height: options.height,
            href: getContextPath() + url + options[key],
            data: {key: options[key]},
            buttons: [{
                id: options.buttonId, text: '授权', handler: function () {
                    parent.$.modalDialog.openner_treeGrid = dataGridNode;
                    var f = parent.$.modalDialog.handler.find('#form');
                    f.submit()
                }
            }]
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function grantFunCommon(title, url, dataGridNode, newOptions) {
    var rows = dataGridNode.datagrid('getSelections');
    if (rows && rows.length > 0) {
        var options = {
            title: title,
            width: newOptions&&newOptions.width?newOptions.width:500,
            height: newOptions&&newOptions.height?newOptions.height:300,
            href: getContextPath() + url + rows[0].id,
            data: {'id': rows[0].id}
        }
        $.extend(options, newOptions);
        parent.$.modalDialog(options);
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function grantFunMulti(title, url, dataGridNode, newOptions) {
    var rows = dataGridNode.datagrid('getChecked');
    if (rows && rows.length > 0) {
        var options = {width: 500, height: 300};
        $.extend(options, newOptions);
        if (!options.buttonId) {
            options.buttonId = "grantsFunction"
        }
        if (options.ids == undefined) {
            options.ids = [];
            $.each(rows, function (index, value) {
                options.ids.push(value.id);
            })
        } else {
            dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
        }
        parent.$.modalDialog({
            title: title,
            width: options.width,
            height: options.height,
            href: getContextPath() + url + options.ids,
            data: {'ids': options.ids},
            buttons: [{
                id: options.buttonId, text: '授权', handler: function () {
                    parent.$.modalDialog.openner_treeGrid = dataGridNode;
                    var f = parent.$.modalDialog.handler.find('#form');
                    f.submit()
                }
            }]
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function deleteTreeGridFunction(url, node, callBackFun) {
    var row = node.treegrid('getSelections');
    if (row.length > 0) {
        parent.$.messager.confirm('询问', "您是否要删除这条记录？", function (b) {
            if (b) {
                parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
                $.post(getContextPath() + url, {id: row[0].id}, function (result) {
                    parent.$.messager.progress('close');
                    if (callBackFun) {
                        callBackFun(result, node)
                    }
                }, 'JSON')
            }
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function doByIdFunction(url, node, title, callBackFun) {
    var row = node.datagrid('getSelections');
    if (row.length > 0) {
        parent.$.messager.confirm('询问', title, function (b) {
            if (b) {
                parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
                postAjax(url,JSON.stringify({id: row[0].id}),function (result) {
                    parent.$.messager.progress('close');
                    if (callBackFun) {
                        callBackFun(result, node)
                    }
                });
            }
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function doByKeyFunction(url, node, title, opts) {
    var key = opts.key?opts.key:"id";
    var row = node.datagrid('getSelections');

    if (row.length > 0) {
        parent.$.messager.confirm('询问', title, function (b) {
            if (b) {
                parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
                var json = {};
                json[key] = row[0][key];
                postAjax(url,JSON.stringify(json),function (result) {
                    parent.$.messager.progress('close');
                    if (opts&&opts.callBackFun) {
                        opts.callBackFun(result, node);
                    }
                });
            }
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function deleteSingleDataGridFunction(url, node, callBackFun) {
    var row = node.datagrid('getSelections');
    if (row.length > 0) {
        parent.$.messager.confirm('询问', "您是否要删除这条记录？", function (b) {
            if (b) {
                parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
                $.post(getContextPath() + url, {id: row[0].id}, function (result) {
                    parent.$.messager.progress('close');
                    if (callBackFun) {
                        callBackFun(result, node)
                    }
                }, 'JSON')
            }
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function changeMultiStatus(node, url, id, status, funStr) {
    var passIds = getCheckedIds(node);
    if (passIds.length > 0) {
        changeStatusIds(url, id, passIds, status, funStr);
        node.datagrid('unselectAll').datagrid('uncheckAll')
    } else {
        showErrorMsg("请选中一条数据")
    }
}

function changeMultiStatusOnlyId(node, url, id, status, funStr) {
    changeStatusId(url, id, status, funStr);
    node.datagrid('unselectAll').datagrid('uncheckAll')
}

function changeStatusId(url, id, status, funStr) {
    var postData = {id: id, 'status': status};
    postAjax(url, JSON.stringify(postData), funStr, {async: false})
}

function afterDeleteDataGridRefresh(result, node) {
    node.datagrid('getPager').pagination('select', 1);
    node.datagrid('unselectAll').datagrid('uncheckAll');
    parent.$.messager.progress('close');
    showSuccessMsg(result.description)
}

function updateTreeGridFunction(url, node, title) {
    var row = node.treegrid('getSelections');
    if (row.length > 0) {
        url = url + row[0].id;
        var options = getInitDialogOptions(title, url, "editFunction", '编辑');
        options.buttons[0].handler = function () {
            parent.$.modalDialog.openner_treeGrid = node
        };
        parent.$.modalDialog(options)
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function distributionFun(title, url, dataGridNode, newOptions) {
    var rows = dataGridNode.datagrid('getSelections');
    if (rows && rows.length > 0) {
        var options = {width: 800, height: 500};
        $.extend(options, newOptions);
        if (options.id == undefined) {
            options.id = rows[0].id
        }
        parent.$.modalDialog({
            title: title,
            width: options.width,
            height: options.height,
            href: getContextPath() + url + options.id,
            buttons: [{
                id: options.distributionId ? options.distributionId : "distributionFunction",
                text: "授权",
                handler: function () {
                    parent.$.modalDialog.openner_dataGrid = dataGridNode;
                    var f = parent.$.modalDialog.handler.find('#form');
                    f.submit()
                }
            }]
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

function createComboDataGrid(data, successOptions) {
    var newOptions = successOptions.newOptions;
    var comboGridNode = successOptions.comboGridNode;
    var options = {
        data: data.returnResult,
        panelWidth: 500,
        panelHeight: 200,
        idField: 'id',
        textField: 'id',
        pagination: true,
        fitColumns: true,
        delay: 500,
        sortName: 'id',
        sortOrder: 'asc',
        pageSize: 5,
        pageList: [5, 10]
    };
    $.extend(options, newOptions);
    comboGridNode.combogrid(options);
    if (successOptions.keyword) {
        $(comboGridNode.parent().find('.combo-text')[0]).val(successOptions.keyword.trim());
        $(comboGridNode.parent().find('.combo-text')[0]).focusEnd()
    }
}

function initComboBoxDataGrid(url, comboGridNode, newOptions) {
    var options = {
        panelWidth: 520,
        panelHeight: 300,
        idField: 'id',
        textField: 'id',
        pagination: true,
        fitColumns: true,
        delay: 1000,
        sortName: 'id',
        sortOrder: 'asc',
        pageSize: (newOptions.postData && newOptions.postData.page && newOptions.postData.page.size) ? newOptions.postData.page.size : "10",
        pageList: [10, 20]
    };
    $.extend(options, newOptions);
    comboGridNode.combogrid(options);
    if (newOptions && newOptions.keyword) {
        $(comboGridNode.parent().find('.combo-text')[0]).val(newOptions.keyword.trim());
        $(comboGridNode.parent().find('.combo-text')[0]).focusEnd()
    }
    // if (newOptions && newOptions.warehouseName) {
    //     comboGridNode.combogrid("setValue", newOptions.warehouseName)
    // }
    var dataGridNode = comboGridNode.combogrid('grid');
    dataGridNode.datagrid('getPager').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            newOptions.postData.page.size = pageSize;
            newOptions.postData.page.current = pageNumber;
            newOptions.pageNumber = pageNumber;
            newOptions.pageSize = pageSize;
            initAjaxComboBoxDataGrid(url, comboGridNode, newOptions)
        }, onChangePageSize: function (pageSize) {
            newOptions.postData.page.size = pageSize;
            newOptions.pageSize = pageSize;
            initAjaxComboBoxDataGrid(url, comboGridNode, newOptions)
        }
    })
}

function initComboList(url, comboGridNode, newOptions) {
    var type = 'get';
    var successOptions = {comboGridNode: comboGridNode, newOptions: newOptions};
    postAjax(url, null, createComboDataGrid, {successArguments: successOptions, type: type, async: false})
}

function initDataGridNoPager(dataGridNode, newOptions) {
    var options = {
        fitColumns: false,
        border: false,
        pagination: false,
        width: 'auto',
        height: 'auto',
        idField: 'id',
        sortName: 'id',
        sortOrder: 'asc',
        checkOnSelect: true,
        singleSelect: true,
        selectOnCheck: true,
        nowrap: true,
        toolbar: '#toolbar',
        onLoadSuccess: function () {
            $('#searchForm table').show();
            parent.$.messager.progress('close')
        }
    };
    $.extend(options, newOptions);
    if ($.data(dataGridNode[0], "datagrid")) {
        dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
    }
    dataGridNode.datagrid(options);
    dataGridNode.datagrid('doCellTip', {
        onlyShowInterrupt: true,
        position: 'bottom',
        maxWidth: '200px',
        tipStyler: {'backgroundColor': '#fff000', borderColor: '#ff0000', boxShadow: '1px 1px 3px #292929'}
    });
    return dataGridNode
}
function initDataGridSpecialPage(url, dataGridNode, newOptions) {
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
        pageSize: (newOptions.postData && newOptions.postData.page && newOptions.postData.page.size) ? newOptions.postData.page.size : "999999",
        pageList: [999999],
        toolbar: '#toolbar',
        onLoadSuccess: function () {
            $('#searchForm table').show();
            parent.$.messager.progress('close')
        }
    };
    $.extend(options, newOptions);
    dataGridNode.datagrid(options);
    dataGridNode.datagrid('doCellTip', {
        onlyShowInterrupt: true,
        position: 'bottom',
        maxWidth: '200px',
        tipStyler: {'backgroundColor': '#fff000', borderColor: '#ff0000', boxShadow: '1px 1px 3px #292929'}
    });
    dataGridNode.datagrid('getPager').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            newOptions.pageNumber = pageNumber;
            newOptions.pageSize = pageSize;
            initAjaxDataGridNoPage(url, dataGridNode, newOptions)
        }, onChangePageSize: function (pageSize) {
            newOptions.pageSize = pageSize;
            initAjaxDataGridNoPage(url, dataGridNode, newOptions)
        }, onRefresh: function () {
        }
    });
    return dataGridNode
}

function initDataGridOnly10(url, dataGridNode, newOptions) {
    var options = {
        fit: true,
        fitColumns: true,
        border: false,
        pagination: false,
        width: 'auto',
        height: 'auto',
        idField: 'id',
        sortName: 'id',
        sortOrder: 'asc',
        checkOnSelect: true,
        singleSelect: true,
        selectOnCheck: true,
        nowrap: true,
        toolbar: '#toolbar',
        onLoadSuccess: function () {
            $('#searchForm table').show();
            parent.$.messager.progress('close')
        }
    };
    $.extend(options, newOptions);
    dataGridNode.datagrid(options);
    dataGridNode.datagrid('doCellTip', {
        onlyShowInterrupt: true,
        position: 'bottom',
        maxWidth: '200px',
        tipStyler: {'backgroundColor': '#fff000', borderColor: '#ff0000', boxShadow: '1px 1px 3px #292929'}
    });
    return dataGridNode
}

function initDataGrid(url, dataGridNode, newOptions) {
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
    dataGridNode.datagrid('doCellTip', {
        onlyShowInterrupt: true,
        position: 'bottom',
        maxWidth: '200px',
        tipStyler: {'backgroundColor': '#fff000', borderColor: '#ff0000', boxShadow: '1px 1px 3px #292929'}
    });
    dataGridNode.datagrid('getPager').pagination({
        onSelectPage: function (pageNumber, pageSize) {
            newOptions.postData.page.size = pageSize;
            newOptions.postData.page.current = pageNumber;
            newOptions.pageNumber = pageNumber;
            newOptions.pageSize = pageSize;
            initAjaxDataGrid(url, dataGridNode, newOptions)
        }, onChangePageSize: function (pageSize) {
            newOptions.postData.page.size = pageSize;
            newOptions.pageSize = pageSize;
            initAjaxDataGrid(url, dataGridNode, newOptions)
        }, onRefresh: function () {
        }
    });
    return dataGridNode
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
    dataGridNode.datagrid('doCellTip', {
        onlyShowInterrupt: true,
        position: 'bottom',
        maxWidth: '200px',
        tipStyler: {'backgroundColor': '#fff000', borderColor: '#ff0000', boxShadow: '1px 1px 3px #292929'}
    });
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

function errorFunction(resultData) {
    parent.$.messager.alert('提示', resultData.description, 'info')
};

function showErrorMsg(errorMsg) {
    parent.$.messager.alert('提示', errorMsg, 'error')
};
function showInfoMsg(errorMsg) {
    parent.$.messager.alert('提示', errorMsg, 'info')
};

function showErrorThisMsg(errorMsg) {
    $.messager.alert('提示', errorMsg, 'error')
};

function initTreeGrid(dataGridNode, newOptions) {
    var options = {
        fit: true,
        fitColumns: true,
        border: false,
        idField: 'id',
        treeField: 'text',
        nowrap: false,
        toolbar: '#toolbar',
        onLoadSuccess: function () {
            $('#searchForm table').show();
            parent.$.messager.progress('close')
        }
    };
    $.extend(options, newOptions);
    return dataGridNode.treegrid(options)
}

function initAjaxDataGrid(url, dataGridNode, newOptions) {
    var options = {postData: {}};
    $.extend(options, newOptions);
    postAjax(url, JSON.stringify(options.postData), function (data) {
        if(data.returnResult){
                var formatData = {};
                var pF = options.preparDataFunction;
                if(pF){
                    formatData = pF(data)
                }else{
                    if(data.returnResult.records) {
                        formatData.total = data.returnResult.total;
                        formatData.rows = data.returnResult.records;
                    }else{
                        internalError();
                        return;
                    }
                }
                options.data = formatData;
                if ($.data(dataGridNode[0], "datagrid")) {
                    dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
                }
                if (options.successFunction) {
                    options.successFunction(data)
                }
                initDataGrid(url, dataGridNode, options);
        }else{
            internalError();
        }

    }, {})
}

function initAjaxDataGridNoPage(url, dataGridNode, newOptions) {
    var options = {postData: {}};
    $.extend(options, newOptions);
    postAjax(url, JSON.stringify(options.postData), function (data) {
        if(data.returnResult){
            options.data = data.returnResult;
            if ($.data(dataGridNode[0], "datagrid")) {
                dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
            }
            if (options.successFunction) {
                options.successFunction(data)
            }
            initDataGridSpecialPage(url,dataGridNode, options);
        }else{
            internalError();
        }
    }, {})
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

function initAjaxDataGridOnly10(url, dataGridNode, newOptions) {
    var options = {postData: {}};
    $.extend(options, newOptions);
    postAjax(url, JSON.stringify(options.postData), function (data) {
        if(data.returnResult){
            if(data.returnResult.records){
                var formatData = {};
                formatData.total = data.returnResult.total;
                formatData.rows = data.returnResult.records;
                options.data = formatData;
                if ($.data(dataGridNode[0], "datagrid")) {
                    dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
                }
                if (options.successFunction) {
                    options.successFunction(data)
                }
                initDataGridOnly10(url, dataGridNode, options);
            }else{
                internalError();
            }
        }else{
            internalError();
        }

    }, {})
}

function initAjaxComboBoxDataGrid(url, comboGridNode, newOptions) {
    var options = {"postData":{"page": {"current": 1, "size": 10}}};
    $.extend(options, newOptions);
    if (newOptions && newOptions.postData && newOptions.postData.keyword) {
        options.postData.keyword = newOptions.postData.keyword
    }
    // if (newOptions && newOptions.postData && newOptions.postData.warehouseName) {
    //     options.postData.warehouseName = newOptions.postData.warehouseName
    // }
    // if (newOptions && newOptions.postData && newOptions.postData.id) {
    //     options.postData.id = newOptions.postData.id
    // }
    postAjax(url, JSON.stringify(options.postData), function (data) {
        if(data.returnResult.records) {
            var formatData = {};
            formatData.total = data.returnResult.total;
            formatData.rows = data.returnResult.records;
            options.data = formatData;
            initComboBoxDataGrid(url, comboGridNode, options);
            if (newOptions && newOptions.postData && newOptions.postData.keyword) {
                $(comboGridNode.parent().find('.combo-text')[0]).val(newOptions.postData.keyword.trim());
                $(comboGridNode.parent().find('.combo-text')[0]).focus();
            }
        }
    }, {async: false})
}

function uniqueValidate(node, url, data, text) {
    if (checkValidate(url, data)) {
        node.removeClass("validatebox-invalid");
        return true
    } else {
        node.addClass("validatebox-invalid");
        showErrorMsg("<span style='color:red'>验证不通过</span><br/><br/><br/>" + text);
        return false
    }
}

function checkValidate(url, data) {
    var flag = false;
    $.ajax({
        type: "post", dataType: "json", async: false, url: getContextPath() + url, data: data, before: function () {
            parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'})
        }, success: function (data) {
            parent.$.messager.progress('close');
            if (data && isSuccess(data.resultcode)) {
                if (data.resultcode == "10050") {
                    flag = true
                } else if (data.resultcode == "10040") {
                    flag = false
                }
            } else {
                commonError(data.resultcode, data.description)
            }
        }, timeout: 1000, error: function (result) {
            parent.$.messager.progress('close');
            templateInternalError(result.responseText)
        }
    });
    return flag
}

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

function clearMadanFun(node) {
    var clearNode = $("#" + node);
    clearNode.find('input').not("input[readonly]")
        .not("input[name='checkDirectionalCustomer']").not("input[name='isAuthResource']").val('');
    var selectNodes = clearNode.find('select');
    $.each(selectNodes, function (index, value) {
        $(this).find("option:eq(0)").attr("selected", true)
    });
    clearNode.find('input:checkbox').prop("checked", false);
    clearNode.find('select[name="sort"]').attr('disabled', true);
    if ($("#batchPackageNumber").length > 0) {
        $("#batchPackageNumber").val("");
    }
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

function clearMadanMltiFun(node) {
    var clearNode = $("#" + node);
    clearNode.find('input').not("input[readonly]")
        .not("input[name='checkDirectionalCustomer']")
        .not("input[name='isAuthResource']")
        .not("input[name='customerName']").val('');
    $("#batchPackageNumber").val('');
    var tNode = clearNode.find("input[name='customerName']");
    if (tNode.hasClass("combo-value")) {
        $("#customerNameS").combobox("clear");
        // var nList = $("$customerNameS").combobox("getValues");
        // $.each(nList,function(index,value){
        //     tNode.combobox("unselect",value);
        // })
    } else {
        tNode.val("");
    }
    var selectNodes = clearNode.find('select');
    $.each(selectNodes, function (indrenex, value) {
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

function createComboTree(data, successOptions) {
    var options = {data: data.returnResult};
    $.extend(options, successOptions.newOptions);
    successOptions.treeNode.combotree(options)
}

function initComboTree(url, treeNode, newOptions) {
    var successOptions = {treeNode: treeNode, newOptions: newOptions};
    postAjax(url, null, createComboTree, {successArguments: successOptions, type: 'get'})
}

function initComboTreeSync(url, treeNode, newOptions) {
    var successOptions = {treeNode: treeNode, newOptions: newOptions};
    postAjax(url, null, createComboTree, {successArguments: successOptions, type: 'get', async: false})
}

function createTree(data, successOptions) {
    var options = {data: data.returnResult, parentField: 'pid'};
    $.extend(options, successOptions.newOptions);
    successOptions.treeNode.tree(options)
}

//仅生成右边导航树使用
function createWestTree(data, successOptions) {
    var options = {data: data.returnResult, parentField: 'pid'};
    $.extend(options, successOptions.newOptions);
    westTree = successOptions.treeNode.tree(options)
}

function initTree(url, treeNode, newOptions) {
    var successOptions = {treeNode: treeNode, newOptions: newOptions};
    if (newOptions.postData) {
        postAjax(url, newOptions.postData, createTree, {successArguments: successOptions, type: 'get'})
    } else {
        var options = {
            onClick: function (node) {
                if (node.attributes && node.attributes.url) {
                    var url;
                    if (node.attributes.url.indexOf('/') == 0) {
                        url = getContextPath() + node.attributes.url
                    }
                } else {
                    url = node.attributes.url
                }
                addTab({url: url, title: node.text})
            }
        };
        $.extend(options, newOptions);
        successOptions.newOptions = options;
        postAjax(url, null, createTree, {successArguments: successOptions, type: 'get', async: false})
    }
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
        parent.$.messager.progress('close')
    } else {
        t.tabs('add', opts)
    }
}

//仅生成右边导航树使用
function initWestTree(url, treeNode, newOptions) {
    var successOptions = {treeNode: treeNode, newOptions: newOptions};
    if (newOptions.postData) {
        postAjax(url, newOptions.postData, createWestTree, {successArguments: successOptions, type: 'get'})
    } else {
        var options = {
            onClick: function (node) {
                var $this = $(this);
                var target = node.target;
                if ($this.tree('getChildren', target).length) {
                    $this.tree("toggle", target)
                } else {
                    if (node.attributes && node.attributes.url) {
                        var url;
                        if (node.attributes.url.indexOf('/') == 0) {
                            url = getContextPath() + node.attributes.url
                        }
                    } else {
                        url = node.attributes.url
                    }
                    addTab({url: url, title: node.text})
                }
            }
        };
        $.extend(options, newOptions);
        successOptions.newOptions = options;
        postAjax(url, null, createWestTree, {successArguments: successOptions, type: 'get', async: false})
    }
};

function exportData(url) {
    $('#searchForm').attr("action", getContextPath() + url);
    $('#searchForm').attr("method", "post");
    $('#searchForm').submit();
    $('#searchForm').removeAttr("action");
    $('#searchForm').removeAttr("method")
}

function exportDataDetail(url) {
    var checks = $("#dataGrid").datagrid('getChecked');
    if (checks.length > 0) {
        $('#searchForm').attr("action", getContextPath() + url + "?id=" + checks[0].id);
        $('#searchForm').attr("method", "post");
        $('#searchForm').submit();
        $('#searchForm').removeAttr("action");
        $('#searchForm').removeAttr("method")
    } else {
        showErrorMsg("请选择一条数据！")
    }
}

function exportDataMulti(url) {
    var checks = $("#dataGrid").datagrid('getChecked');
    if (checks.length > 0) {
        var ids = [];
        $.each(checks, function (index, value) {
            ids.push(value.id);
        });
        $('#exportDataMulti').attr("action", getContextPath() + url + "?ids=" + ids);
        $('#exportDataMulti').attr("method", "post");
        $('#exportDataMulti').submit();
        $('#exportDataMulti').removeAttr("action");
        $('#exportDataMulti').removeAttr("method")
    } else {
        showErrorMsg("请选择一条数据！")
    }
}

function exportDataSmall(url) {
    $('#searchForm-small').attr("action", getContextPath() + url);
    $('#searchForm-small').attr("method", "post");
    $('#searchForm-small').submit();
    $('#searchForm-small').removeAttr("action");
    $('#searchForm-small').removeAttr("method")
};

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

function initSelectText(selectNode, data, flag, title) {
    var showTitle = title ? title : "请选择";
    var str = flag ? "<option value=''>" + showTitle + "</option>" : "";
    $.each(data, function (key, value) {
        str += "<option value='" + value + "' >" + value + "</option>"
    });
    selectNode.html(str)
};

//初始化combobox 回调函数 注释利于 没用到text combobox
function createCombobox(data, successOptions) {
    var options = {data: data.returnResult};
    $.extend(options, successOptions);
    successOptions.comboboxNode.combobox(options)
}

//初始化combobox
function initOptionsAjax(url, comboboxNode, key1, key2) {
    var successOptions = {comboboxNode: comboboxNode, valueField: key1, textField: key2};
    postAjax(url, null, createCombobox, {successArguments: successOptions, type: 'get', async: false})
};

function initNumberboxForNum(node) {
    node.numberbox({
        precision: 2, groupSeparator: ',', decimalSeparator: '.', formatter: function (value) {
            return formatNum(value)
        }
    })
};

function initNumberboxForWeight(node) {
    node.numberbox({
        precision: 6, formatter: function (value) {
            value = Math.round(parseFloat(value) * Math.pow(10, 6)) / Math.pow(10, 6);
            return formatWeight(value)
        }
    })
};

//获取globo status list
function getStatusData(url) {
    return postAjax(url, {}, function () {
    }, {type: 'get', async: false})
}

function getStatusDataTong(url) {
    return postAjax(url, {}, function () {
    }, {type: 'get', async: true})
}

//初始化TreeGrid ajax
function initAjaxTreeGrid(url, dataGridNode, newOptions) {
    var options = {postData: {}};
    $.extend(options, newOptions);
    postAjax(url, options.postData, function (data) {
        initTreeGrid(dataGridNode, options).treegrid('loadData', data.returnResult)
    }, {async: false, contentType: "text/html; charset=utf-8"})
}

function preparLoadData(data) {
    loadData(data.returnResult)
}

function preparUserLoadData(data, successOptions) {
    preparLoadData(data);
    if ($('#dept') && $('#dept').size() > 0) {
        successOptions.deptNode.combotree('setValues', [data.returnResult.deptNo])
    } else {
        $('#jiTuan').combobox('setValues', [data.returnResult.f_groupId])
    }
}

//为节点添加validType
//addValidateOldName($('#deptName'), "uniqueDeptName");
function addValidateOldName(oldNameNode, validateName) {
    var oldRoleName = oldNameNode.val();
    oldNameNode.attr("validType", validateName + "('" + oldRoleName + "')")
}

//获取tree文字
//id
//Text
//没用到
var treeNodeName;

function getComboTreeText(data, id) {
    for (var i = 0; i < data.length; i++) {
        var children = data[i].children;
        if (children && children.length > 0) {
            getComboTreeText(children, id)
        } else {
            if (data[i].id == id) {
                treeNodeName = data[i].text;
                return treeNodeName
            }
        }
    }
    return treeNodeName
}

//获取tree文字
//id
//Text
//没用到
var treeNodeId;

function getComboTreeId(data, text) {
    for (var i = 0; i < data.length; i++) {
        var children = data[i].children;
        if (children && children.length > 0) {
            getComboTreeId(children, text)
        } else {
            if (data[i].text == text) {
                treeNodeId = data[i].id;
                return treeNodeId
            }
        }
    }
    return treeNodeId
}

//验证是不是为空
function isEmpty(val) {
    return !val
}

//验证是不是数字
function isMoreThan0Number(val) {
    var reg = /^\d+(?=\.{0,1}\d+$|$)/;
    return reg.test(val)
}

//显示验证不通过的提示信息
function showValidteMessage(value) {
    if (value && value != "true") {
        showErrorMsg("<span style='color:red'>验证不通过</span><br/><br/><br/>" + value);
        return false
    }
    return true
}

//状态方法
//flag 有值的时候刷新无分页的view页面
function changeStatus(url, id, status, funStr) {
    var postData = {'id': id, 'operationType': status};
    postAjax(url, JSON.stringify(postData), funStr, {async: false})
}

function dataGridChangeStatus(url, status, dataNode, text, funStr) {
    var rows = dataNode.datagrid('getSelections');
    if (rows && rows.length > 0) {
        parent.$.messager.confirm('询问', text, function (b) {
            if (b) {
                changeStatus(url, rows[0].id, status, funStr)
            }
        })
    } else {
        showErrorMsg('请选择一条数据.')
    }
}

//状态方法
function changeStatusIds(url, id, ids, status, funStr) {
    var postData = {id: id, 'ids': ids, 'status': status};
    postAjax(url, JSON.stringify(postData), funStr, {async: false})
}

function doByMultiKey(field, text, url, funStr) {
    var checks = $("#dataGrid").datagrid('getChecked');
    if (checks.length > 0) {
        parent.$.messager.confirm('询问', text, function (b) {
            if (b) {
                var ids = [];
                $.each(checks, function (index, value) {
                    ids.push(value[field]);
                });
                var postData = {'ids': ids};
                postAjax(url, JSON.stringify(postData), funStr);
            }
        })
    } else {
        showErrorMsg("请选择一条数据！")
    }
}
function changeStatus1(url, id, status, funStr) {
    var postData = {'id': id, 'type': status};
    postAjax(url, JSON.stringify(postData),funStr)
}
//回调函数 返回data不返回object里的数据
function closeDialogCallBack(data) {
    $("div.panel-tool .panel-tool-close").click();
    showSuccessMsg(data.description);
}

//回调函数 返回data不返回object里的数据
function closeSubDialogCallBack(data) {
    var dataGridUser = $('#dataGridUser');
    parent.$.modalSubDialog.handler.dialog('close');
    dataGridUser.datagrid('getPager').pagination('select');
    dataGridUser.datagrid('unselectAll').datagrid('uncheckAll');
    showSuccessMsg(data.description);
}

//获取datagrid中选中数据id的list
function getCheckedIds(node) {
    var ids = [];
    var checkedRows = node.datagrid("getChecked");
    $.each(checkedRows, function (index, value) {
        if (value.id) {
            ids.push(value.id)
        }
    });
    return ids
}

//搜索
function searchFun(url, node, funcName, ops) {
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
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}


//搜索
function searchFunOnly10(url, node, funcName, ops) {
    var formData = $.serializeObject($('#searchForm'));
    if(formData&&formData.hyqc.length>=2){
        ops&&ops.nodeNames && ops.nodeNames.length > 0?
            $.each(ops.nodeNames, function (index, value) {
                postData[value] = $("#" + ops.nodes[index]).val();
            }):"";
        var postData={"page":{}};
        ops&&ops.k?postData[ops.k] = formData:"";
        postData.page.current = ops&&ops.c?ops.c:"1";
        postData.page.size = ops&&ops.s? ops.s:"10";
        var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
        pageNode.length > 0 ? postData.page.size = pageNode.val() : "10";
        initAjaxDataGridOnly10(url, node, funcName({postData: postData}))
    }else{
        showInfoMsg("会员名称搜索至少输入两个字符")
    }

}


//搜索
function searchFunNoPage(url, node, funcName, ops) {
    var formData = $.serializeObject($('#searchForm'));
    ops&&ops.nodeNames && ops.nodeNames.length > 0?
        $.each(ops.nodeNames, function (index, value) {
            postData[value] = $("#" + ops.nodes[index]).val();
        }):"";
    var postData={};
    ops&&ops.k?postData[ops.k] = formData:postData = formData;
    initAjaxDataGridNoPage(url, node, funcName({postData: postData}));

    // postAjax(url,JSON.stringify(postData),function(result){
    //     var dgOps = funcName();
    //     dgOps.data = result.returnResult;
    // })
}

//搜索
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


function pDataModel(ops,fData){
    var data = {"page":{}};
    ops.k?data[ops.k] = fData:{};
    data.page.current = ops&&ops.c?ops.c:"1";
    data.page.size = ops&&ops.s? ops.s:"100";
    return JSON.stringify(data);
}

//搜索
function searchFunSSP(url, node, funcName, page, rows, nodeNames, nodes) {
    var postData = $.serializeObject($('#searchForm'));
    postData.paidMethod = $("#paidMethod").val();
    if (nodeNames && nodeNames.length > 0) {
        $.each(nodeNames, function (index, value) {
            postData[value] = $("#" + nodes[index]).val();
        });
    }
    page ? postData.page = page : "";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? rows = pageNode.val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

//搜索
function searchSmallFun(url, node, funcName, page, rows, nodeNames, nodes) {
    var postData = $.serializeObject($('#searchForm-small'));
    if (nodeNames && nodeNames.length > 0) {
        $.each(nodeNames, function (index, value) {
            postData[value] = $("#" + nodes[index]).val();
        });
    }
    page ? postData.page = page : "";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? rows = pageNode.val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

function searchFunSpecial(url, node, funcName, page, rows) {
    var postData = $.serializeObject($('.searchForm-small'));
    page ? postData.page = page : "";
    $("#dingXiangModel .pagination-page-list").length > 0 ? rows = $("#dingXiangModel .pagination-page-list").val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

function searchFunPayMethod(url, node, funcName, page, rows) {
    var postData = $.serializeObject($('#searchForm'));
    postData.payMethod = $("#payMethodSub").val();
    page ? postData.page = page : "";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? rows = pageNode.val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

//搜索
function searchFunForOrder(url, node, funcName, page, rows) {
    var postData = $.serializeObject($('#searchForm'));
    var userNameRow = $('#userName').combogrid('grid').datagrid('getSelected');
    if (userNameRow) {
        postData.userName = userNameRow.trueName
    }
    page ? postData.page = page : "";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? rows = pageNode.val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

//搜索
function searchFunForZYUAN(url, node, funcName, nodeN, page, rows) {
    var postData = $.serializeObject($('#searchForm'));
    var userNameRow = $('#' + nodeN).combogrid('grid').datagrid('getSelected');
    if (userNameRow) {
        postData.cyLocker = userNameRow.trueName
    }
    page ? postData.page = page : "";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? rows = pageNode.val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

function searchSmallFunPS(url, node, funcName, page, rows) {
    var postData = $.serializeObject($('#searchForm-small'));
    postData.packageStatus = $("#packageStatusS").val();
    postData.listFlag = $("#listFlagS").val();
    page ? postData.page = page : "";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? rows = pageNode.val() : "";
    rows ? postData.rows = rows : "";
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

function initTopComboGrid(url, node, funcName, ops) {
    var pData = ops && ops.postData ? ops.postData : {};
    var postData={"page":{}};
    ops&&ops.k?postData[ops.k] = pData:"";
    if (ops != undefined) {
        ops.key != undefined ? postData.key = ops.key : postData.key = "";
    }
    postData.page.current = ops&&ops.c?ops.c:"1";
    postData.page.size = ops&&ops.s? ops.s:"10";
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? postData.page.size = pageNode.val() : "10";
    initAjaxComboBoxDataGrid(url, node, funcName({postData: postData}, node));
}

//搜索
function searchFun(url, node, funcName, ops) {
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
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}


//搜索
function searchBillOfLadingFun(url, node, funcName, page, rows) {
    var postData = $.serializeObject($('#searchForm-small'));
    page ? postData.page = page : "";
    rows ? postData.rows = rows : "";
    postData.f_orderId = $('#salesOrderId').text();
    initAjaxDataGrid(url, node, funcName({postData: postData}))
}

////通过状态名称获取状态id
//function changeStatusVal(str,statusObj){for(var i in statusObj){if(statusObj[i]===str){return i}}};

//根据列名字（field）获取第几列
function getColumnIndex(columns, name) {
    for (var i = 0; i < columns.length; i++) {
        if (columns[i].field == name) {
            return i
        }
    }
}

var editIndex = undefined;

function endEditing(node) {
    if (editIndex === undefined) {
        return true
    }
    var $panel = node.datagrid("getPanel");
    var $field = $('td', $panel);
    var $input = $("input", $field);
    $input.removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
    if (node.datagrid('validateRow', editIndex)) {
        node.datagrid('endEdit', editIndex).datagrid('selectRow', editIndex);
        editIndex = undefined;
        return true
    } else {
        node.datagrid('highlightRow', editIndex);
        return false
    }
}

//总数据 combo保存的数据 combo保存数据key 返回数据的key
//返回[nodeName1:value,nodeName2:value1]  传入value返回value1
//示例getComboboxTextC(payTypeStatusData, rowData.payType, "payName", "payCode");
function getComboboxTextC(data, value, nodeName1, nodeName2) {
    for (var i = 0; i < data.length; i++) {
        if (data[i][nodeName1] == value) {
            return data[i][nodeName2]
        }
    }
}

//获取tree选中ids （'getChecked', 'indeterminate'）
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
    return ids
}

// 不能选一级菜单
function checkParentChecked(node) {
    var nodes1 = node.tree('getChecked');
    var nodes2 = node.tree('getChecked', 'indeterminate');
    var checknodes = $.merge(nodes1, nodes2);
    return getSelectParentNodes(node, checknodes)
};

function getSelectParentNodes(node, checknodes) {
    var selectedFirstLevelIds = [];
    $.each(checknodes, function (index, value) {
        var thisPN = node.tree('getParent', value.target);
        if (thisPN === null) {
            selectedFirstLevelIds.push(value.id)
        }
    });
    var allParentArrayIds = [];
    $.each(checknodes, function (index, value) {
        var thisPN = node.tree('getParent', value.target);
        if (thisPN !== null) {
            allParentArrayIds.push(thisPN.id)
        }
    });
    var flag = true;
    $.each(selectedFirstLevelIds, function (index, value) {
        if ($.inArray(value, allParentArrayIds) === -1) {
            flag = false
        }
    });
    return flag
}

// 选择子菜单必须选择父菜单
function checkOnlyCChecked(node) {
    var nodes1 = node.tree('getChecked');
    var nodes2 = node.tree('getChecked', 'indeterminate');
    var checknodes = $.merge(nodes1, nodes2);
    return getNotSelectPNode(node, checknodes);
};

function getNotSelectPNode(node, checknodes) {
    var selectedNotLeafIds = [];
    $.each(checknodes, function (index, value) {
        var thisPN = node.tree('getParent', value.target);
        if (thisPN !== null) {
            if ($.inArray(value.id, selectedNotLeafIds) === -1) {
                selectedNotLeafIds.push(thisPN.id)
            }
            var thisPN2 = node.tree('getParent', thisPN.target);
            if (thisPN2 !== null) {
                if ($.inArray(thisPN2.id, selectedNotLeafIds) === -1) {
                    selectedNotLeafIds.push(thisPN2.id);
                }
            }
        }
    });
    var allCheckedArrayIds = [];
    $.each(checknodes, function (index, value) {
        allCheckedArrayIds.push(value.id);
    });
    var flag = true;
    $.each(selectedNotLeafIds, function (index, value) {
        if ($.inArray(value, allCheckedArrayIds) === -1) {
            flag = false
        }
    });
    return flag
}

function getComboSearchOptions(oldOptions, node) {
    var options = node.combogrid("options");
    if (options.data) {
        delete options.data
    }
    $.extend(options, oldOptions);
    return options
}

//供应商代码的搜索功能 no
function comboSearch(url, node, q, newOptions) {
    var options = {key: q.trim()};
    $.extend(options, newOptions);
    initTopComboGrid(url, node, getComboSearchOptions, options);
    $(node.parent().find('.combo-text')[0]).val(q.trim());
    $(node.parent().find('.combo-text')[0]).focusEnd()
}

//供应商代码的搜索功能 no
function comboSearchWH(url, node, q, newOptions) {
    var options = {warehouseName: q.trim()};
    $.extend(options, newOptions);
    initTopComboGrid(url, node, getComboSearchOptions, 0, 0, options);
    $(node.parent().find('.combo-text')[0]).val(q.trim());
    $(node.parent().find('.combo-text')[0]).focusEnd()
}

function convertToTree(data, textFiled) {
    var idFiled = 'id', parentField = 'pid';
    var i, l, treeData = [], tmpMap = [];
    for (i = 0, l = data.length; i < l; i++) {
        tmpMap[data[i][idFiled]] = data[i]
    }
    for (i = 0, l = data.length; i < l; i++) {
        if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
            if (!tmpMap[data[i][parentField]]['children']) tmpMap[data[i][parentField]]['children'] = [];
            data[i]['text'] = data[i][textFiled];
            tmpMap[data[i][parentField]]['children'].push(data[i])
        } else {
            data[i]['text'] = data[i][textFiled];
            treeData.push(data[i])
        }
    }
    return treeData
}

function numV(that) {
    var t = $(that);
    t.val(t.val().replace(/[^0-9.]/g, ''));
}

function numO(that) {
    var t = $(that);
    t.val(t.val().replace(/[^0-9]/g, ''));
}

function showKBMessage(data, title, successFun) {
    var str = "<h4 class='alertDialogTitle'>" + title + "</h4>";
    str += "<div class='alertDialogLevel1'><div class='alertDialogLevel2'>";
    $.each(data, function (index, value) {
        str += value + " "
    });
    str += "</div></div>";
    if ($('#alertDialogModel').length === 0) {
        $('#alertDialog').dialog({
            id: 'alertDialogModel',
            title: "提示",
            width: 400,
            cache: false,
            modal: true,
            buttons: [{
                text: '确定', handler: function () {
                    successFun();
                    $('#alertDialogModel').window('close')
                }
            }, {
                text: '取消', handler: function () {
                    $('#alertDialogModel').window('close')
                }
            }]
        });
        $('#alertDialogModel').window('close')
    }
    $("#alertDialogModel .dialog-content").html(str);
    $('#alertDialogModel').window('open')
}

function arrayConvertToJson(data) {
    var jsonMapArray = [];
    $.each(data, function (index, value) {
        var jsonMap = {};
        jsonMap.name = value;
        jsonMapArray.push(jsonMap)
    });
    return jsonMapArray
}

function initCombox(node,url,ops){
    postAjax(url,ops.p,function(data){
        var dataCombobox = null;
        if(data.returnResult.records&&data.returnResult.records.length>0) {
            dataCombobox = data.returnResult.records;
        } else if(data.returnResult&&data.returnResult.length>0) {
            dataCombobox = data.returnResult;
        }
        if (dataCombobox) {
            node.combobox({
                width: ops.width?ops.width:140,
                height: 24,
                data: ops.filterf==="1"? $.grep(dataCombobox,function(value){return value[ops.text] != ops.filtert;}):dataCombobox,
                valueField: ops.id?ops.id:"id",
                textField: ops.text,
                // multiple: true,
                // editable:false,
                onLoadSuccess: function (data) {
                    var sFun = ops.successFunction;
                    if(sFun){
                        sFun(data);
                    }

                    $(".combo").click(function(){
                        $(this).prev().combobox("showPanel");
                    })

                    // var that = $(this);
                    // that.combobox("showPanel");
                    // $($(that[0]).siblings().find(".combo-text")[0]).focus(function () {
                    //     node.combobox("showPanel")
                    // });
                    // $($(that[0]).siblings().find(".combo-text")[0]).focus()
                }
            });
            // node.unbind("click")
        }
    })
}

function initComboxNoPage(node,url,ops){
    postAjax(url,ops.p,function(data){
        if(data.returnResult) {
            var dataCombobox = data.returnResult;
            node.combobox({
                width: ops.width?ops.width:140,
                height: 24,
                data: ops.filterf==="1"? $.grep(dataCombobox,function(value){return value[ops.text] != ops.filtert;}):dataCombobox,
                valueField: ops.id?ops.id:"id",
                textField: ops.text,
                // multiple: true,
                editable:false,
                onLoadSuccess: function (data) {
                    var sFun = ops.successFunction;
                    if(sFun){
                        sFun(data);
                    }
                    // var that = $(this);
                    // that.combobox("showPanel");
                    // $($(that[0]).siblings().find(".combo-text")[0]).focus(function () {
                    //     node.combobox("showPanel")
                    // });
                    // $($(that[0]).siblings().find(".combo-text")[0]).focus()
                }
            });
            // node.unbind("click")
        }
    })
}


function initSearchCombox(node, url) {
    var data = getStatusData(url);
    node.combobox({
        width: 140,
        height: 24,
        data: arrayConvertToJson(data),
        valueField: "name",
        textField: "name",
        onLoadSuccess: function () {
            var that = $(this);
            that.combobox("showPanel");
            $($(that[0]).siblings().find(".combo-text")[0]).focus(function () {
                node.combobox("showPanel")
            });
            $($(that[0]).siblings().find(".combo-text")[0]).focus()
        }
    });
    node.unbind("click")
}

function initThisNodeClick(name) {
    $("#" + name).click(function () {
        initSearchCombox($("#" + name), "/madan/selectFieldList.html?fieldName=" + name)
    })
}

function initMultiSearchCombox(node, url) {
    var data = getStatusData(url);
    node.combobox({
        width: 140,
        height: 24,
        data: arrayConvertToJson(data),
        valueField: "name",
        textField: "name",
        multiple: true,
        onLoadSuccess: function () {
            var that = $(this);
            that.combobox("showPanel");
            $($(that[0]).siblings().find(".combo-text")[0]).focus(function () {
                node.combobox("showPanel")
            });
            $($(that[0]).siblings().find(".combo-text")[0]).focus()
        }
    });
    node.unbind("click")
}

function initMultiNodeClick(name) {
    $("#" + name + "S").click(function () {
        initMultiSearchCombox($("#" + name + "S"), "/madan/selectFieldList.html?fieldName=" + name)
    })
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
        height: 120,
        timeout: 2000,
        style: {right: '', bottom: ''}
    })
}

function bindChangeStatusClick(nodeid, flagMsg, id) {
    var node = $("#" + nodeid + "");
    node.length > 0 ? node.click(function () {
        changeStatus("/payment/saveChangeStatusApplicationSub.html", id, flagMsg, closeDialogCallBack)
    }) : ""
}

function bindChangeStatusClickC(url, nodeid, flagMsg, id) {
    var node = $("#" + nodeid + "");
    node.length > 0 ? node.click(function () {
        changeStatus(url, id, flagMsg, closeDialogCallBack)
    }) : ""
}

function addZYKRow() {
    var row = {cid: UUID(), contractCode: "ZYK", appBalance: "", paymentDate: "", remark: ""};
    $('#subItem').datagrid('appendRow', row)
}

function addSXSKZYKRow() {
    var row = {cid: UUID(), orderCode: "ZYK", allocationAmount: ""};
    $('#subItem').datagrid('appendRow', row)
}

function initOpenBlankList(customerId) {
    var openNoNode = $("#openNo");
    openNoNode.val("");
    $("#openBlankDiv").html("<label>* 开户行：</label> <select id='openBlankCombo' name='openBlank' data-options='required:true' style='width:142px'> </select>");
    var openBlankComboNode = $('#openBlankCombo');
    initTopComboGrid("/system/customers/selectPageCustomersBlankList.html?f_customersId=" + customerId, openBlankComboNode, getCustomerBanksOptions);
    var dData = getDefaultCustomer(openBlankComboNode.combogrid('grid').datagrid('getData').rows);
    openBlankComboNode.combogrid('setValues', [dData.id]);
    openNoNode.val(dData.openNo);
    $("#openNameUnique").val(dData.openName)
}

function getDefaultCustomer(bankList) {
    var dB = "";
    for (var i = 0; i < bankList.length; i++) {
        if (bankList[i].isDefault === "1") {
            dB = bankList[i];
            break;
        }
    }
    return dB ? dB : bankList[0];
}

function clearOpenBlankList() {
    $("#openNameUnique").val("");
    $("#openBlankDiv").html("<label>开户行：</label> <input id='openBlankCombo' name='openBlank' readonly/>");
    $("#openBlankCombo").val("");
    $("#openNo").val("")
}

function initBankInfo() {
    $("#openNameUnique").val("中钢银通信息技术服务有限公司客户备付金");
    $("#openBlankDiv").html("<label>开户行：</label> <input id='openBlankCombo' name='openBlank' readonly/>");
    $("#openBlankCombo").val("建行上海浦东分行");
    $("#openNo").val("31001520313059168888")
}

function ZYKRefundDirectionBank() {
    $("payMethod").val("0").attr("disabled", "true");
    $("#openNameUnique").val("");
    $("#openBlankDiv").html("<label>开户行：</label> <input id='openBlankCombo' name='openBlank' readonly/>");
    $("#openBlankCombo").val("");
    $("#openNo").val("")
}

//
function isContainXZ(data, nodeN, text) {
    var fg = false;
    $.each(data, function (index, value) {
        if (value[nodeN] === text) {
            fg = true
        }
    });
    return fg
}

function resizeCenterHeight(node) {
    var c = node;
    var p = c.layout('panel', 'center');
    var oldHeight = p.panel('panel').outerHeight();
    p.panel('resize', {height: 'auto'});
    var newHeight = p.panel('panel').outerHeight();
    c.height(c.height() + newHeight - oldHeight);
    c.layout('resize')
}

//
function resizePanelShowHArea(b, s, nodeStr, cNode) {
    if (b) {
        cNode ? $("." + cNode).show() : $(".classHiddenFlag").show()
    }
    $('#' + nodeStr + 'Panel').panel('resize', {height: s});
    resizeCenterHeight($('#' + nodeStr + 'Layout'))
}

//
function resizePanelHideHArea(b, s, nodeStr, cNode) {
    if (b) {
        cNode ? $("." + cNode).hide() : $(".classHiddenFlag").hide()
    }
    $('#' + nodeStr + 'Panel').panel('resize', {height: s});
    resizeCenterHeight($('#' + nodeStr + 'Layout'))
}

function clearFunctionForUserName(node, cNode) {
    clearFunction(node);
    $('#' + cNode).combogrid('clear');
}

function payTypeSChangeR(pt, ct, text) {
    var ptD = $("#" + pt);
    var ctD = $("#" + ct);
    ptD.change(function () {
        var tVal = $(this).val();
        (tVal === "2" || tVal === "3" || tVal === "4") ? ctD.parent().find('label').addClass("redSharp").text(text ? "*" + text : "") : ctD.parent().find('label').removeClass("redSharp").text(text ? text : "")
    })
}

function initRadioDis(cNo) {
    cNo.bind("change", function (event) {
        var siblingsNode = cNo.siblings();
        siblingsNode.attr('disabled', true);
        siblingsNode.filter("select").val("0");
        siblingsNode.filter("input").val("");
        cNo.filter("input:checked").siblings().attr('disabled', null);
        event.stopPropagation()
    });
    $(cNo[0]).click()
}

function initCheckBoxList(cNo) {
    cNo.bind("change", function (event) {
        var that = $(this);
        var siblingsNode = that.siblings();
        if (that.prop("checked")) {
            siblingsNode.filter("input").attr('disabled', null);
            siblingsNode.filter("textarea").attr('disabled', null)
        } else {
            siblingsNode.filter("input").val("").attr('disabled', true);
            siblingsNode.filter("textarea").val("").attr('disabled', true)
        }
        event.stopPropagation()
    })
}

function refreshDataGrid(data) {
    $('#dataGrid').datagrid('getPager').pagination('select');
    showSuccessMsg(data.description)
}

function refreshDataGridBE(data) {
    $('#dataGrid').datagrid('getPager').pagination('select');
    bigErrorDialog(data.description, "错误提示")
}

function bigErrorDialog(data, title, successFun) {
    var str = "<h4 class='alertDialogTitle'>" + title + "</h4>";
    str += "<div class='alertDialogLevel1'><div class='alertDialogLevel2'>";
    str += data;
    str += "</div></div>";
    if ($('#alertDialogModel').length === 0) {
        $('#alertDialog').dialog({
            id: 'alertDialogModel',
            title: "提示",
            width: 400,
            cache: false,
            modal: true,
            buttons: [{
                text: '确定', handler: function () {
                    $('#alertDialogModel').window('close')
                }
            }]
        });
        $('#alertDialogModel').window('close')
    }
    $("#alertDialogModel .dialog-content").html(str);
    $('#alertDialogModel').window('open')
}

function checkListByOne(data,array){
    $.each(array,function(i,v){
        data[v]=="1"?$("input[name="+v+"]").prop("checked",true):$("input[name="+v+"]").prop("checked",false);
    })
}

function checkSubmitConvert(data,array){
    $.each(array,function(i,v){
        $("input[name="+v+"]").prop("checked")?data[v] = "1":data[v] = "0";
    });
    return data;
}

function detailContractFunctionByHthm(hthm){
    var optionsn={"width":1200,"height":630,
        buttons:[]};
    var dataGridNode = $("#dataGrid");
    var url = '/contract/contractDetailHthm?hthm='+ hthm;
    var options = getInitDialogOptions('合同明细', url, "editFunction", "编辑");
    $.extend(options, optionsn);
    options.dataGridNode = dataGridNode;
    parent.$.modalDialog(options);
}
function tdDetailFunByTDH(tdh){
    var dataGridNode = $("#dataGrid");
    var url = '/contract/selectTdmxPageTDH?tdh='+ tdh;
    var newOptions = {buttons: [],'height':500,'width':1100};
    var options = getInitDialogOptions('查看详情', url, "editFunction", "编辑");
    $.extend(options, newOptions);
    options.dataGridNode = dataGridNode;
    parent.$.modalDialog(options);
}
//查看会员详情
function hydaViewByHydm(hydm) {
    var dataGridNode = $("#dataGrid");
    var url = '/member/hydaQyView?hydm='+ hydm;
    var newOptions = {buttons: [],'height':600, 'width':1250};
    var options = getInitDialogOptions('查看会员详情', url, "editFunction", "编辑");
    $.extend(options, newOptions);
    options.dataGridNode = dataGridNode;
    parent.$.modalDialog(options)
}