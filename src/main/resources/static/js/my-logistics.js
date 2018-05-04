/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */


// bootstrap表格属性设定
$(function () {

    $('#transTable').bootstrapTable({
        //url: '/index.xhtml',
        //method: 'post',
        editable: true,         // 开启编辑模式
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        showColumns: true,      // 显示内容列下拉框
        showToggle: true,       // 显示视图切换按钮（分页/卡片）
        uniqueId: 'id',
        pageList: [5, 25],
        pageSize: 10,
        columns: [
            {
                field: "loadTime",
                title: "装车时间"
            }, {
                field: "goodsName",
                title: "物资名称"
            }, {
                field: "goodsModel",
                title: "型号"
            }, {
                field: "netWeight",
                title: "净重"
            }, {
                field: "returnNumber",
                title: "回执数"
            }, {
                field: "lossTon",
                title: "亏吨"
            }, {
                field: "goodsFrom",
                title: "物资来源"
            }, {
                field: "sellerUnitPrice",
                title: "厂家结算单价"
            }, {
                field: "sellerSumPrice",
                title: "厂家结算金额"
            }, {
                field: "buyerCompany",
                title: "结算单位"
            }, {
                field: "unitPrice",
                title: "结算单价"
            }, {
                field: "sumPrice",
                title: "结算金额"
            }, {
                field: "transCompany",
                title: "运输公司"
            }, {
                field: "transPerCost",
                title: "运费单价"
            }, {
                field: "transSumCost",
                title: "运费金额"
            }, {
                field: "profit",
                title: "利润"
            }, {
                field: "weighingNumber",
                title: "检斤号"
            }, {
                field: "carNumber",
                title: "车牌号"
            }, {
                field: "remark",
                title: "备注"
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


