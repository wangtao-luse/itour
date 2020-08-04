//jsToolTip
(function ($, undefined) {
    $.extend($.fn.datagrid.methods, {
        doCellTip: function (jq, params) {
            function showTip(showParams, td, e, dg) {
                if ($(td).text() == "")return;
                params = params || {};
                var options = dg.data('datagrid');
                var styler = 'style="word-break:break-all; ';
                if (showParams.width) {
                    styler = styler + "width:" + showParams.width + ";"
                }
                if (showParams.maxWidth) {
                    styler = styler + "max-width:" + showParams.maxWidth + ";"
                }
                if (showParams.minWidth) {
                    styler = styler + "min-width:" + showParams.minWidth + ";"
                }
                styler = styler + '"';
                showParams.content = '<div class="tipcontent" ' + styler + '>' + showParams.content + '</div>';
                $(td).tooltip({
                    content: showParams.content,
                    trackMouse: true,
                    position: params.position,
                    onHide: function () {
                        $(this).tooltip('destroy')
                    },
                    onShow: function () {
                        $(".tooltip-bottom").length > 1 ? $(".tooltip-bottom").remove() : "";
                        var tip = $(this).tooltip('tip');
                        if (showParams.tipStyler) {
                            tip.css(showParams.tipStyler)
                        }
                        if (showParams.contentStyler) {
                            tip.find('div.tipcontent').css(showParams.contentStyler)
                        }
                    }
                }).tooltip('show')
            };
            return jq.each(function () {
                var grid = $(this);
                var options = $(this).data('datagrid');
                if (!options.tooltip) {
                    var panel = grid.datagrid('getPanel').panel('panel');
                    panel.find('.datagrid-body').each(function () {
                        var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;
                        $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td[field]', {
                            'mouseover': function (e) {
                                var that = this;
                                var setField = null;
                                if (params.specialShowFields && params.specialShowFields.sort) {
                                    for (var i = 0; i < params.specialShowFields.length; i++) {
                                        if (params.specialShowFields[i].field == $(this).attr('field')) {
                                            setField = params.specialShowFields[i]
                                        }
                                    }
                                }
                                if (setField == null) {
                                    options.factContent = $(this).find('>div').clone().css({
                                        'margin-left': '-5000px',
                                        'width': 'auto',
                                        'display': 'inline',
                                        'position': 'absolute'
                                    }).appendTo('body');
                                    var factContentWidth = options.factContent.width();
                                    params.content = $(this).text();
                                    if (params.onlyShowInterrupt) {
                                        if (factContentWidth > $(this).width()) {
                                            showTip(params, this, e, grid)
                                        }
                                    } else {
                                        showTip(params, this, e, grid)
                                    }
                                } else {
                                    panel.find('.datagrid-body').each(function () {
                                        var trs = $(this).find('tr[datagrid-row-index="' + $(that).parent().attr('datagrid-row-index') + '"]');
                                        trs.each(function () {
                                            var td = $(this).find('> td[field="' + setField.showField + '"]');
                                            if (td.length) {
                                                params.content = td.text()
                                            }
                                        })
                                    });
                                    showTip(params, this, e, grid)
                                }
                            }, 'mouseout': function (e) {
                                if (options.factContent) {
                                    options.factContent.remove();
                                    options.factContent = null
                                }
                            }, 'click': function(e){
                                if (options.factContent) {
                                    options.factContent.remove();
                                    options.factContent = null;
                                    $(this).tooltip('destroy');
                                }
                            }, 'focus': function(e){
                                if (options.factContent) {
                                    options.factContent.remove();
                                    options.factContent = null;
                                    $(this).tooltip('destroy');
                                }
                            }
                        })
                    })
                }
            })
        }, cancelCellTip: function (jq) {
            return jq.each(function () {
                var data = $(this).data('datagrid');
                if (data.factContent) {
                    data.factContent.remove();
                    data.factContent = null
                }
                var panel = $(this).datagrid('getPanel').panel('panel');
                panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')
            })
        }
    })
})(jQuery);
//单元格刷新
(function ($, undefined) {
    $.extend($.fn.datagrid.methods, {
        refreshRowTd: function (jq, param) {
            var _66e = $.data(jq[0], "datagrid");
            var rowIndex = param.rowIndex;
            var fList = param.fieldNameList;
            var format = param.formatMethods;
            var tr = $("#" + _66e.rowIdPrefix + "-2" + "-" + rowIndex);
            var rows = jq.datagrid("getRows");
            $.each(fList, function (index, value) {
                tr.find("td[field=" + value + "] div." + _66e.cellClassPrefix + "-" + value + "").text(format ? format[index](rows[rowIndex][value]) : rows[rowIndex][value])
            })
        }
    })
})(jQuery);
function doRefreshRowTd(node, rowIndex, fieldNameList, formatMethods) {
    var refreshData = {};
    refreshData.rowIndex = rowIndex;
    refreshData.fieldNameList = fieldNameList;
    refreshData.formatMethods = formatMethods;
    $('#' + node).datagrid('refreshRowTd', refreshData)
}