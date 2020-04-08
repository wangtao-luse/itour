$.fn.panel.defaults.loadingMessage = "加载中....";
$.fn.datagrid.defaults.loadMsg = "加载中....";
$.fn.panel.defaults.onBeforeDestroy = function () {
    var frame = $("iframe", this);
    try {
        if (frame.length > 0) {
            for (var i = 0; i < frame.length; i++) {
                frame[i].src = "";
                frame[i].contentWindow.document.write("");
                frame[i].contentWindow.close()
            }
            frame.remove();
            if (navigator.userAgent.indexOf("MSIE") > 0) {
                try {
                    CollectGarbage()
                } catch (e) {
                }
            }
        }
    } catch (e) {
    }
};
//var easyuiPanelOnMove = function (left, top) {
//    var l = left;
//    var t = top;
//    if (l < 1) {
//        l = 1
//    }
//    if (t < 1) {
//        t = 1
//    }
//    var width = parseInt($(this).parent().css("width")) + 14;
//    var height = parseInt($(this).parent().css("height")) + 14;
//    var right = l + width;
//    var buttom = t + height;
//    var browserWidth = $(window).width();
//    var browserHeight = $(window).height();
//    if (right > browserWidth) {
//        l = browserWidth - width
//    }
//    if (buttom > browserHeight) {
//        t = browserHeight - height
//    }
//    $(this).parent().css({left: l, top: t})
//};
//$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
//$.fn.window.defaults.onMove = easyuiPanelOnMove;
//$.fn.panel.defaults.onMove = easyuiPanelOnMove;
var easyuiErrorFunction = function (XMLHttpRequest) {
    $.messager.progress("close");
    $.messager.alert("错误", XMLHttpRequest.responseText)
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;
var createGridHeaderContextMenu = function (e, field) {
    e.preventDefault();
    var grid = $(this);
    var headerContextMenu = this.headerContextMenu;
    if (!headerContextMenu) {
        var tmenu = $('<div style="width:100px;"></div>').appendTo("body");
        var fields = grid.datagrid("getColumnFields");
        for (var i = 0; i < fields.length; i++) {
            var fildOption = grid.datagrid("getColumnOption", fields[i]);
            if (!fildOption.hidden) {
                $('<div iconCls="tick" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu)
            } else {
                $('<div iconCls="bullet_blue" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu)
            }
        }
        headerContextMenu = this.headerContextMenu = tmenu.menu({
            onClick: function (item) {
                var field = $(item.target).attr("field");
                if (item.iconCls == "tick") {
                    grid.datagrid("hideColumn", field);
                    $(this).menu("setIcon", {target: item.target, iconCls: "bullet_blue"})
                } else {
                    grid.datagrid("showColumn", field);
                    $(this).menu("setIcon", {target: item.target, iconCls: "tick"})
                }
            }
        })
    }
    headerContextMenu.menu("show", {left: e.pageX, top: e.pageY})
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
var gridTooltipOptions = {
    tooltip: function (jq, fields) {
        return jq.each(function () {
            var panel = $(this).datagrid("getPanel");
            if (fields && typeof fields == "object" && fields.sort) {
                $.each(fields, function () {
                    var field = this;
                    bindEvent($(".datagrid-body td[field=" + field + "] .datagrid-cell", panel))
                })
            } else {
                bindEvent($(".datagrid-body .datagrid-cell", panel))
            }
        });
        function bindEvent(jqs) {
            jqs.mouseover(function () {
                var content = $(this).text();
                if (content.replace(/(^\s*)|(\s*$)/g, "").length > 5) {
                    $(this).tooltip({
                        content: content, trackMouse: true, position: "bottom", onHide: function () {
                            $(this).tooltip("destroy")
                        }, onUpdate: function (p) {
                            var tip = $(this).tooltip("tip");
                            if (parseInt(tip.css("width")) > 500) {
                                tip.css("width", 500)
                            }
                        }
                    }).tooltip("show")
                }
            })
        }
    }
};
$.extend($.fn.datagrid.methods, gridTooltipOptions);
$.extend($.fn.treegrid.methods, gridTooltipOptions);
$.extend($.fn.validatebox.defaults.rules, {
    eqPwd: {
        validator: function (value, param) {
            return value == $(param[0]).val()
        }, message: "密码不一致！"
    }
});
$.extend($.fn.tree.methods, {
    getCheckedExt: function (jq) {
        var checked = $(jq).tree("getChecked");
        var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
        $.each(checkbox2, function () {
            var node = $.extend({}, $.data(this, "tree-node"), {target: this});
            checked.push(node)
        });
        return checked
    }, getSolidExt: function (jq) {
        var checked = [];
        var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
        $.each(checkbox2, function () {
            var node = $.extend({}, $.data(this, "tree-node"), {target: this});
            checked.push(node)
        });
        return checked
    }
});
$.fn.tree.defaults.loadFilter = function (data, parent) {
    var opt = $(this).data().tree.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || "id";
        textFiled = opt.textFiled || "text";
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i]
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]["children"]) {
                    tmpMap[data[i][parentField]]["children"] = []
                }
                data[i]["text"] = data[i][textFiled];
                tmpMap[data[i][parentField]]["children"].push(data[i])
            } else {
                data[i]["text"] = data[i][textFiled];
                treeData.push(data[i])
            }
        }
        return treeData
    }
    return data
};
$.fn.treegrid.defaults.loadFilter = function (data, parentId) {
    var opt = $(this).data().treegrid.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || "id";
        textFiled = opt.textFiled || "text";
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i]
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]["children"]) {
                    tmpMap[data[i][parentField]]["children"] = []
                }
                data[i]["text"] = data[i][textFiled];
                tmpMap[data[i][parentField]]["children"].push(data[i])
            } else {
                data[i]["text"] = data[i][textFiled];
                treeData.push(data[i])
            }
        }
        return treeData
    }
    return data
};
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;
$.modalDialog = function (options) {
    if ($.modalDialog.handler == undefined) {
        var opts = $.extend({
            title: "", width: 840, height: 680, modal: true, onClose: function () {
                var gNode = options.dataGridNode;
                if (gNode && !options.treeGridNode) {
                    options.currentRefresh?
                        gNode.datagrid("getPager").pagination("select", 1):
                        gNode.datagrid("getPager").pagination("select", gNode.datagrid('options').pageNumber);
                    gNode.datagrid("unselectAll").datagrid("uncheckAll");
                }
                $.modalDialog.handler = undefined;
                $(this).dialog("destroy")
            }, onOpen: function () {
            }
        }, options);
        opts.modal = true;
        return $.modalDialog.handler = $("<div/>").dialog(opts)
    }
};
$.modalSubDialog = function (options) {
    if ($.modalSubDialog.handler == undefined) {
        var opts = $.extend({
            title: "", width: 840, height: 680, modal: true, onClose: function () {
                $.modalSubDialog.handler = undefined;
                $(this).dialog("destroy")
            }, onOpen: function () {
            }
        }, options);
        opts.modal = true;
        return $.modalSubDialog.handler = $("<div/>").dialog(opts)
    }
};