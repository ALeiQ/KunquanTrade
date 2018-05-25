/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

// “增加”按钮展开折叠栏功能
$(function () {

    $('.collapse-toggle').collapse({
        parent: accordion,
        toggle: false
    });


    $('.add-row-button').click(function () {
        var panel = $(this).parents('.panel');
        panel.children('.collapse').collapse('show');
    });

});

// bootstrap表格参数设定
$(function () {

    $('#goodsTable').bootstrapTable({
        url: '/majorTerms/getTermsInfo',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        editable: true,         // 开启编辑模式
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [10, 20, 30],
        pageSize: 10,
        queryParams: function() {
            return {
                getType: "goodsTable"
            };
        },
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "#",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称",
                width: "150px"
            }, {
                field: "model",
                title: "型号"
            }, {
                field: "operate",
                title: "操作",
                formatter: function (value, row, index) {
                    return delIcon(value, row, index);
                }
            }
        ]
    });

    $('#companysTable').bootstrapTable({
        url: '/majorTerms/getTermsInfo',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        editable: true,         // 开启编辑模式
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [10, 20, 30],
        pageSize: 10,
        queryParams: function() {
            return {
                getType: "companysTable"
            };
        },
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "#",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称"
            }, {
                field: "operate",
                title: "操作",
                formatter: function (value, row, index) {
                    return delIcon(value, row, index);
                }
            }
        ]
    });

    $('#logisticsTable').bootstrapTable({
        url: '/majorTerms/getTermsInfo',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        editable: true,         // 开启编辑模式
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [10, 20, 30],
        pageSize: 10,
        queryParams: function() {
            return {
                getType: "logisticsTable"
            };
        },
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "#",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称"
            }, {
                field: "operate",
                title: "操作",
                formatter: function (value, row, index) {
                    return delIcon(value, row, index);
                }
            }
        ]
    });

    $('#bankTable').bootstrapTable({
        url: '/majorTerms/getTermsInfo',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        editable: true,         // 开启编辑模式
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [10, 20, 30],
        pageSize: 10,
        queryParams: function() {
            return {
                getType: "bankTable"
            };
        },
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "#",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称"
            }, {
                field: "operate",
                title: "操作",
                formatter: function (value, row, index) {
                    return delIcon(value, row, index);
                }
            }
        ]
    });

    function delIcon(value, row, index) {
        return '<a class="icon closed-tool" style="cursor: pointer;" onclick="delData(' + row.id + ', this, )"><i class="fa' +
            ' fa-times"></i></a>';
    }

});

// 表格功能模块
$(function () {

    var isEmpty = new RegExp('^[ ]+$');
    var inp_tmp_front = '<input type="text" onchange="reloadRowData($(this), ';
    var inp_tmp_back = ')" value="" style="width: 100%;"/>';


    // "增加"按钮
    addItem = function (btn) {

        var confirmBtn = $($(btn).parent()).children('.confirm-add-button');
        var table = $($(btn).parents('.panel')).find('.table');
        var rows = $(table)[0].rows;
        var topics = $(rows[0]).children();

        $(confirmBtn).show();

        var tr = {id: "", operate: ""};

        for (var i = 1; i < topics.length - 1; ++i) {
            var field = topics[i].dataset.field;
            tr[field] = inp_tmp_front + "'" + field + "'" + inp_tmp_back;
        }

        table.bootstrapTable('prepend', tr);

        return false;
    };

    // 删除行按钮
    delData = function (delId, del_icon) {

        var row = $(del_icon).parents('tr');
        var table = $(row).parents('.table');

        ajaxDelData(table[0].id, delId);

        table.bootstrapTable('refresh');

        refreshTermHistory(0);

        return false;
    };

    // 确认增加按钮
    confirmAdd = function (th_button) {
        var panel = $(th_button).parents('.panel');
        var table = $(panel).find('.table');
        var rows = $(table)[0].rows;

        var newInfo = [];

        var topics = $(rows[0]).children();

        for (var i = 1; i < $(rows).length; ++i) {
            var hasText = false;
            var rowJson = {};

            for (var j = 1; j < rows[i].cells.length - 1; ++j) {
                var text = $($(rows[i].cells[j])[0].firstChild).val();

                // name字段必须非空
                if (j === 1 && (typeof(text) === 'undefined' || text === '' || isEmpty.test(text))) {
                    hasText = false;
                    break;
                } else {
                    hasText = true;
                }

                rowJson[topics[j].dataset.field] = text;
            }

            if (!hasText) continue;

            rowJson['id'] = '1';
            rowJson['operate'] = '';

            newInfo.push(rowJson);
        }

        ajaxAddData(table[0].id, newInfo);

        table.bootstrapTable('refresh');

        $(th_button).hide();

        refreshTermHistory(0);

        return false;
    };

    // 添加多条数据ajax请求
    ajaxAddData = function (tableId, data) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/majorTerms/addTerms" ,//url
            async: false,
            data: {addType: tableId, termsJson: JSON.stringify(data)},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                }
            },
            error : function() {
                alert("ajax请求异常！");
            }
        });
    };

    // 删除单条数据ajax请求
    ajaxDelData = function (tableId, id) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/majorTerms/delTerm" ,//url
            async: false,
            data: {delType: tableId, delId: id},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                }
            },
            error : function() {
                alert("ajax请求异常！");
            }
        });
    };

    // 改变 input 编辑框值时，更新 data 数据，便于新增行时原来填写好的数据不会被新增行强制覆盖
    reloadRowData = function (inp, field) {

        var table = inp.parents('.table');
        var index = getRowOfInp(inp, table);

        var newRow = table.bootstrapTable('getOptions').data[index];

        newRow[field] = '<input type="text" onchange="reloadRowData($(this),\'' + field +
            '\')" value="' + inp.val() +'" style="width: 100%;"/>';

        table.bootstrapTable('getData')[index] = newRow;

        return false;
    };

    getRowOfInp = function (inp, table) {

        var rows = $(table)[0].rows;

        for (var i = 1; i < $(rows).length; ++i) {
            for (var j = 1; j < rows[i].cells.length; ++j) {
                if ($(rows[i].cells[j])[0] === $(inp.parent())[0]) {
                    return i - 1;
                }
            }
        }

        return 0;
    };

});

var termHistorySwitch;
// 常用名词增删记录模块
$(function () {

    var totalPages = 1;

    // 加载常用名词增删记录
    loadData = function (event, page) {
        var query = {};
        query['page'] = page;
        //这里另每页数量为5，可自行调整
        query['pageSize'] = 10;
        query['getType'] = termHistorySwitch;
        $.getJSON("/majorTerms/getTermsRecords", query, function (result) {

            var historyTable = $('#term-history')[0];
            var innerHtml = "";

            var data = result.data;

            for (i in data) {

                var operateColor = '';
                var typeColor = '';

                switch (data[i].operateValue) {
                    case 1:
                        operateColor = 'green';
                        break;
                    case 0:
                        operateColor = 'red';
                        break;
                }

                switch (data[i].typeValue) {
                    case 1:
                        typeColor = '#dc11ff';
                        break;
                    case 2:
                        typeColor = '#0093ff';
                        break;
                    case 3:
                        typeColor = '#c75400'
                        break;
                    case 4:
                        typeColor = 'gold';
                        break;
                }

                innerHtml += '<li class="list-group-item">';
                innerHtml += data[i].createDate + '';
                innerHtml += '   <h4 style="margin-left: 40px; color: ' + typeColor + '">' + data[i].typeDesc + '</h4>';
                innerHtml += '   <br /><h4 style="color: ' + operateColor + '">' + data[i].operateDesc + '</h4>';
                innerHtml += '   <h4 style="margin-left: 10px">' + data[i].name + '</h4>';
                innerHtml += '</li>';
            }

            historyTable.innerHTML = innerHtml;

            totalPages = result.totalPages;

            $("#pagination-history").twbsPagination({
                totalPages: totalPages,
                startPage: 1,
                onPageClick: loadData
            });

        });
    }

});

// 历史记录模块
$(function () {

    var switchBtnGroup = $('#term-history-switch');

    switchBtnGroup.children('.btn').click(function () {
        refreshTermHistory($(this).val());
    });

    refreshTermHistory = function(getType) {
        var paginationHistory = $('#pagination-history');
        paginationHistory.empty();
        paginationHistory.removeData("twbs-pagination");
        paginationHistory.unbind("page");
        termHistorySwitch = getType;
        loadData();
    };

    refreshTermHistory(0);

});

