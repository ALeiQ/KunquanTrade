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
        url: '/majorTerms/getGoodsInfo',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        editable: true,         // 开启编辑模式
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [5, 10, 25],
        pageSize: 5,
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "编号",
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
                formatter: delIcon
            }
        ]
    });

    $('#companysTable').bootstrapTable({
        //url: '/index.xhtml',
        //method: 'post',
        editable: true,             // 开启编辑模式
        search: true,               // 显示检索框
        showRefresh: true,          // 显示刷新按钮
        pagination: true,           // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [5, 25],
        pageSize: 10,
        columns: [
            {
                field: "id",
                title: "编号",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称"
            }, {
                field: "operate",
                title: "操作",
                formatter: delIcon
            }
        ]
    });

    $('#transportTable').bootstrapTable({
        //url: '/index.xhtml',
        //method: 'post',
        editable: true,             // 开启编辑模式
        search: true,               // 显示检索框
        showRefresh: true,          // 显示刷新按钮
        pagination: true,           // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [5, 25],
        pageSize: 10,
        columns: [
            {
                field: "id",
                title: "编号",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称"
            }, {
                field: "operate",
                title: "操作",
                formatter: delIcon
            }
        ]
    });

    $('#bankTable').bootstrapTable({
        //url: '/index.xhtml',
        //method: 'post',
        editable: true,             // 开启编辑模式
        search: true,               // 显示检索框
        showRefresh: true,          // 显示刷新按钮
        pagination: true,           // 在表格底部显示分页条
        uniqueId: 'id',
        pageList: [5, 25],
        pageSize: 10,
        columns: [
            {
                field: "id",
                title: "编号",
                sortable: true,
                order: 'desc'
            }, {
                field: "name",
                title: "名称"
            }, {
                field: "operate",
                title: "操作",
                formatter: delIcon
            }
        ]
    });

    function delIcon(value, row, index) {
        return '<a class="icon closed-tool" onclick="delRow(this)"><i class="fa' +
            ' fa-times"></i></a>';
    }

});

// 表格功能模块
$(function () {

    var isEmpty = new RegExp('^[ ]+$');
    var inp_tmp_front = '<input type="text" onchange="reloadRowData($(this), ';
    var inp_tmp_back = ')" value="" style="width: 100%;"/>';
    var suc_tmp = '<a class="icon closed-tool" onclick="delRow(this)"><i class="fa' +
        ' fa-times"></i></a>';


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

        table.bootstrapTable('remove', {field: 'id', values: ['']});

        ajaxAddData(table[0].id, newInfo);

        table.bootstrapTable('prepend', newInfo);

        $(th_button).hide();

        return false;
    };

    ajaxAddData = function (tableId, data) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/majorTerms/addTerms" ,//url
            data: {addType: tableId, termsJson: JSON.stringify(data)},
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                alert(result.resultMsg);
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



