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
        //url: '/index.xhtml',
        //method: 'post',
        editable: true,//开启编辑模式
        clickToSelect: true,
        search: true,  //显示检索框
        showRefresh: true,  //显示刷新按钮
        pagination: true,
        uniqueId: "id",
        pageList: [5, 25],
        pageSize: 10,
        columns: [
            {
                field: "id",
                title: "编号"
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

    $('#companysTable').bootstrapTable({});

    $('#transportTable').bootstrapTable({});

    $('#bankTable').bootstrapTable({});

    function delIcon(value, row, index) {
        return '<a class="icon closed-tool" onclick="delRow(this)"><i class="fa' +
            ' fa-times"></i></a>';
    };

});

// 表格功能模块
$(function () {

    var isEmpty = new RegExp('^[ ]+$');
    var inp_tmp_front = '<input type="text" onchange="reloadRowData($(this), ';
    var inp_tmp_back = ')" value="" style="width: 100%;"/>';
    var suc_tmp = '<a class="icon closed-tool" onclick="delRow(this)"><i class="fa' +
        ' fa-times"></i></a>';


    // "增加"按钮
    $('#addGoods').click(function () {

        $('#confirmAdd').show();

        var table = $("#goodsTable");
        var tr = {id: "", name: inp_tmp_front + "'name'" + inp_tmp_back,
            model: inp_tmp_front + "'model'" + inp_tmp_back, operate: ''};
        table.bootstrapTable('prepend', tr);
    });

    // 确认增加按钮
    confirmAdd = function (th_button) {
        var panel = $(th_button).parents('.panel');
        var table = $(panel).find('.table');
        var rows = $(table)[0].rows;

        var newInfo = [];

        //console.log(rows);

        for (var i = 1; i < $(rows).length; ++i) {
            //console.log(rows[i]);
            var hasText = true;
            for (var j = 1; j < rows[i].cells.length - 1; ++j) {
                var text = $($(rows[i].cells[j])[0].firstChild).val();
                if (text !== "" && !isEmpty.test(text)) {



                    hasText = false;
                    break;
                }
            }
        }

        table.bootstrapTable('remove', {field: 'id', values: ['']});
        table.bootstrapTable('prepend', newInfo);
        $(th_button).hide();
    };

    // 删除行（隐藏）
    delRow = function (term) {
        $(term).parents("tr").fadeToggle(400);

        return false;
    };

    // 改变 input 编辑框值时，更新 data 数据，便于新增行时原来填写好的数据不会被新增行强制覆盖
    reloadRowData = function (inp, field) {

        var table = inp.parents('.table');
        var index = getRowOfInp(inp, table);

        var newRow = table.bootstrapTable('getOptions').data[index];

        newRow[field] = '<input type="text" onchange="reloadRowData($(this),\'' + field +
            '\')" value="' + inp.val() +'" style="width: 100%;"/>';

        table.bootstrapTable('getData')[index] = newRow;
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
    };

});



