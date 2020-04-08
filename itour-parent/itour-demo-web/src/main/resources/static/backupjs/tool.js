function GetDateDiff(startTime, endTime, diffType) {
    startTime = startTime.replace(/-/g, "/");
    endTime = endTime.replace(/-/g, "/");
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);
    var eTime = new Date(endTime);
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 1000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum)); //
}

//光标
$.fn.setCursorPosition = function (position) {
    if (this.lengh == 0) return this;
    return $(this).setSelection(position, position);
}
$.fn.setSelection = function (selectionStart, selectionEnd) {
    if (this.lengh == 0) return this;
    input = this[0];
    if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select()
    } else if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionEnd)
    }
    return this
}
$.fn.focusEnd = function () {
    this.setCursorPosition(this.val().length);
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

//计算选中合计 field可以数组
function calculateZJTwo(node, field, showNode) {
    var rows = node.datagrid("getChecked");
    var total1 = 0;
    var total2 = 0;
    $.each(rows, function (index, value) {
        total1 = parseFloat(total1).add(parseFloat(value[field[0]]));
        total2 = parseFloat(total2).add(parseFloat(value[field[1]]))
    });
    $("#" + showNode[0]).html(total1);
    $("#" + showNode[1]).html(total2)
}

//自动填充表单函数
function loadData(obj) {
    var key, value, tagName, type, arr;
    for (x in obj) {
        key = x;
        value = obj[x];
        $("[name='" + key + "'],[name='" + key + "[]']").each(function () {
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName == 'INPUT') {
                if (type == 'radio') {
                    $(this).attr('checked', $(this).val() == value)
                } else if (type == 'checkbox') {
                    if (value == 1) {
                        $(this).attr('checked', true);
                    }
                } else {
                    $(this).val(value)
                }
            } else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
                $(this).val(value)
            } else if (tagName == 'IMG') {
                $(this).attr('src', value);
            } else if (tagName == 'LABEL') {
                $(this).attr('title', value);
                $(this).text(value == null ? "" : value);
            } else if (tagName == 'A') {
                if (value) {
                    $(this).text('查看图片');
                    $(this).attr('href', value);
                } else {
                    $(this).text('无图');
                }
            }
        })
    }
}

//日期控件 日期格式化（设置默认日期使用） 年月日
function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d)
}

//年月日时分秒
function myparser(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var h = date.getHours();
    var s = date.getMinutes();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d) + " " + (h < 10 ? ('0' + h) : h) + ':' + (s < 10 ? ('0' + s) : s)
}

//生成uuid
var random4 = function () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
};
var UUID = function () {
    return (random4() + random4() + "-" + random4() + "-" + random4() + "-" + random4() + "-" + random4() + random4() + random4())
};
//判断数组内是否有重复内容，返回-1无重复
//用于入库管理判断捆包数组
// 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
function isRepeat(packageArrray) {
    var hash = {};
    for (var i in packageArrray) {
        if (hash[packageArrray[i]]) {
            return i
        }
        hash[packageArrray[i]] = true
    }
    return -1
}

//form数据拼接 上部search的form
(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value.trim())
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value.trim()]
                }
            } else {
                serializeObj[this.name] = this.value.trim()
            }
        });
        return serializeObj
    }
})(jQuery);

//获取
function getContextPath() {
    // alert(document.location.pathname);
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}

//过滤掉"'"字符
function filterSpecial(value) {
    return value.replace(/[']/g, '')
}

// ajax post
//参数 url 链接 ， postData post使用的数据， successFunction 成功时的回调函数函数
//options errorFunction 返回错误时的回调函数
//        passArguments 成功时回调函数参数
//        errorArguments 失败时回调函数参数
//        successMessage 成功时的提示信息
var postAjax = function (url, postData, successFunction, options) {
    var returnData;
    var defaultOptions = {
        errorFunction: errorFunction,
        successArguments: "",
        errorArguments: "",
        successMessage: "",
        type: "post",
        async: true,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    };
    var currentOptions = $.extend(defaultOptions, options);
    $.ajax({
        type: currentOptions.type,
        data: postData,
        contentType: currentOptions.contentType,
        dataType: currentOptions.dataType,
        url: getContextPath() + url,
        async: currentOptions.async,
        beforeSend: function () {
            parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
        },
        success: function (resultData) {
            parent.$.messager.progress('close');
            if (resultData && isSuccess(resultData.resultcode)) {
                if (currentOptions && currentOptions.successArguments) {
                    successFunction(resultData, currentOptions.successArguments);
                    returnData = resultData.returnResult;
                    return returnData;
                } else {
                    successFunction(resultData);
                    returnData = resultData.returnResult;
                    return returnData;
                }
            } else {
                parent.$.messager.progress('close');
                defaultOptions.errorFunction(resultData);
            }
        },
        error: function (result) {
            console.log(result);
            parent.$.messager.progress('close');
            internalError();
            // templateInternalError(result.responseText);
            return;
        }
    });
    return returnData;
};

function errorFunction(resultData) {
    parent.$.messager.alert('提示', resultData.description, 'info')
}

function templateInternalError(msg) {
    $('body').html(msg)
}

function isSuccess(result) {
    return (result==="10")
}

function internalError() {
    showErrorMsg(500 + " " + "程序内部错误")
}

function commonError(errorCode, errormsg) {
    showErrorMsg(errormsg)
}

//将数组转化为json key值是index
function arrayToJson(array) {
    var jsonObj = {};
    for (var i = 0; i < array.length; i++) {
        jsonObj[i] = array[i]
    }
    return jsonObj
}

//设置按钮disable状态
//nodeArray和statusArray长度相同
//statusArray 为0,1 0为disable,1为enable
function setLinkButtonStatus(nodeArray, statusArray) {
    $.each(nodeArray, function (index, value) {
        nodeArray[index].linkbutton(statusArray[index] ? 'enable' : 'disable')
    })
}

function isNumberFun(val) {
    var reg = /^\-?\d+\.?\d*$/;
    return reg.test(val)
}

function initUplodifyList(url, successFunction, options) {
    var title = (options && options.title) ? options.title : '导入数据';
    $("#toolbar").append("<a  href='javascript:void(0);' type='file' id='importData' >" + title + "</a>");
    $('#importData').uploadify({
        'swf': getContextPath() + '/resources/jslib/uploadify/uploadify.swf?var=' + (new Date()).getTime(),
        'uploader': getContextPath() + url,
        'buttonText': title,
        'button_width': '120px',
        'button_height': '20px',
        'fileObjName': 'file',
        'queueID': 'uploadify-queue',
        'queueSizeLimit': 1,
        'auto': true,
        'multi': false,
        'removeCompleted': true,
        'fileTypeDesc': 'Excel',
        'fileTypeExts': '*.xls;*.xlsm;*.xlsx',
        'successTimeout': 500,
        'onUploadStart': function (file) {
            parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'})
        },
        'onUploadSuccess': function (file, data, response) {
            parent.$.messager.progress('close');
            try {
                var resultData = JSON.parse(data);
                if (resultData && isSuccess(resultData.resultcode)) {
                    successFunction(resultData)
                } else {
                    showErrorMsg(resultData.description)
                }
            } catch (error) {
                templateInternalError(data)
            }
        },
        'onUploadError': function (event, queueId, fileObj, errorObj) {
            parent.$.messager.progress('close')
        }
    });
    $('#importData').css("display", "inline-block").css("margin-bottom", "0").css("float", "none");
    $('#importData').append("<div id='uploadify-queue' class='uploadify-queue'></div>")
}

function initListDownLoad(title, url) {
    $("#toolbar").append("<a  href='javascript:void(0);' class='easyui-linkbutton l-btn l-btn-plain' data-options='plain:true' type='file' id='downLoadModel' >" + title + "</a>");
    $('#downLoadModel').linkbutton({iconCls: 'box-down'});
    $('#downLoadModel').click(function () {
        window.location.href = getContextPath() + url
    })
}

function initDownLoadSub(url) {
    $('#downLoadModel').css("float", "left");
    $('#downLoadModel').linkbutton({iconCls: 'box-down'});
    $('#downLoadModel').click(function () {
        window.location.href = getContextPath() + url
    })
}

function Arabia_to_Chinese(Num) {
    for (i = Num.length - 1; i >= 0; i--) {
        Num = Num.replace(",", "");
        Num = Num.replace(" ", "")
    }
    Num = Num.replace("￥", "");
    if (isNaN(Num)) {
        alert("请检查小写金额是否正确");
        return;
    }
    part = String(Num).split(".");
    newchar = "";
    for (i = part[0].length - 1; i >= 0; i--) {
        tmpnewchar = "";
        perchar = part[0].charAt(i);
        tmpnewchar = perchar + tmpnewchar;
        switch (part[0].length - i - 1) {
            case 0:
                tmpnewchar = tmpnewchar + "元";
                break;
            case 4:
                tmpnewchar = tmpnewchar + "万";
                break;
            case 8:
                tmpnewchar = tmpnewchar + "亿";
                break
        }
        newchar = tmpnewchar + newchar;
    }
    if (Num.indexOf(".") != -1) {
        if (part[1].length > 2) {
            part[1] = part[1].substr(0, 2)
        }
        for (i = 0; i < part[1].length; i++) {
            tmpnewchar = "";
            perchar = part[1].charAt(i);
            tmpnewchar = perchar + tmpnewchar;
            if (i == 0) tmpnewchar = tmpnewchar + "角";
            if (i == 1) tmpnewchar = tmpnewchar + "分";
            newchar = newchar + tmpnewchar
        }
    }
    while (newchar.search("零零") != -1) newchar = newchar.replace("零零", "零");
    newchar = newchar.replace("0000万", "");
    newchar = newchar.replace("0000元", "");
    newchar = newchar.replace("0角", "");
    newchar = newchar.replace("0分", "");
    if (newchar.charAt(newchar.length - 1) == "元" || newchar.charAt(newchar.length - 1) == "角") newchar = newchar + "整";
    return newchar
}

$.extend($.fn.validatebox.methods, {
    remove: function(jq, newposition){
        return jq.each(function(){
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
        });
    }
});

function isImgGS(name){
    var s = name.substr(name.lastIndexOf(".")+1,name.length).toLowerCase();
    return !(s!="jpg" && s!="gif"&&s!="png");
}

function upSfzPic(th) {
    var now = new Date().getTime();
    var that = $(th);
    if (!that.val()) {
        alert("请选择上传图片");
        return;
    }
    if (!isImgGS(that.val())) {
        alert("仅支持JPG、GIF、PNG格式！");
        return;
    }
    $(that.closest("form[method='post']")).ajaxSubmit(
        {
            "beforeSubmit":function(){
                parent.$.messager.progress({title: '提示', text: '数据处理中，请稍候....'});
            },
            "success":function(data){
                parent.$.messager.progress('close');
                if (data.resultcode == "10") {
                    that.parent().find("input[type='hidden']").val(data.returnResult);
                    that.parent().find("a").attr('href', data.returnResult + '?' + now).show();
                } else {
                    showErrorMsg(data.description);
                }
            },
            "error":function(){
                parent.$.messager.progress('close');
                showErrorMsg("上传失败，系统错误");
            }
        });
}

function openImg(url){
    window.open(url);
}