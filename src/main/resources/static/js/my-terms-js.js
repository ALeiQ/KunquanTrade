/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

$(function () {

    $('.collapse-toggle').collapse({
        parent: accordion
    });

    $('#goodsTable').bootstrapTable({
        columns: [
            {field:"id", edit:false,title:"编号"},
            {field:"id", title:"名称", width: "150px"}
        ]
    });

    $('#companysTable').bootstrapTable({

    });

    $('#transportTable').bootstrapTable({

    });


    $('#bankTable').bootstrapTable({

    });

    $('.add-row-button').click(function () {
        var panel = $(this).parents('.panel');
        panel.children('.collapse').collapse('show');
    });

    $('#addGoods').click(function () {
        //var data = {};
        //$('#goodsTable').bootstrapTable('append', data);
    });
    
});

// 常用名词 折叠表格 删除按钮
$(document).ready(function () {
    $(".term-table .closed-tool").click(function (event) {
        $(this).parents("tr").fadeToggle(400);

        return false;
    });
});
