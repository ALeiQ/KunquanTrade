
var mainTable = $('#invoiceTable');
var queryDirection = 0

//表格属性设定
$(function () {

    mainTable.bootstrapTable({
        url: '/invoice/getAllByDirection',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        showColumns: true,      // 显示内容列下拉框
        uniqueId: 'id',
        undefinedText: '',      // null显示空，默认'-'
        detailView: true,//父子表
        pageSize: 10,
        pageList: [10, 15, 20],
        queryParams: function() {
            return {
                direction: queryDirection
            };
        },
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "#"
            },
            {
                field: "makeDate",
                title: "开票时间"
            }, {
                field: "payCompany",
                title: "开票单位"
            }, {
                field: "receiveCompany",
                title: "受票单位"
            }, {
                field: "number",
                title: "票号"
            }, {
                field: "typeDesc",
                title: "发票类别"
            }, {
                field: "directionDesc",
                title: "发票流向"
            }, {
                field: "amount",
                title: "发票总额"
            }, {
                field: "useDate",
                title: "抵扣时间"
            },
            {
                field: "remark",
                title: "备注"
            }, {
                field: "operate",
                title: "操作",
                formatter: delIcon
            }
        ],
        onExpandRow: showDetails
    });

    function showDetails(index, row, $detail) {
        var parentid = row.id;
        var cur_table = $detail.html('<table class="table-striped"></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/invoice/getInvoiceDetailsById',
            method: 'get',
            dataType: 'json',
            dataFiled: 'data',
            uniqueId: "id",
            undefinedText: '',      // null显示空，默认'-'
            //pagination: true,       // 在表格底部显示分页条
            //pageSize: 5,
            //pageList: [5, 10, 20],
            queryParams: function() {
                return {
                    queryId: parentid
                };
            },
            columns: [
            {
                field: 'goodsName',
                title: '名称'
            }, {
                field: 'goodsModel',
                title: '型号'
            }, {
                field: 'number',
                title: '数量'
            }, {
                field: 'unitPrice',
                title: '单价（含税）'
            }, {
                field: 'sumPrice',
                title: '总额（含税）'
            }, {
                field: 'tax',
                title: '税额'
            }]
        });
    }

    function delIcon(value, row, index) {
        return '<a class="icon closed-tool" onclick="delData(this)"><i class="fa' +
            ' fa-times"></i></a>';

    }
});
