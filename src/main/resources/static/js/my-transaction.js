
var mainTable = $('#dealTable');

// 表格属性设定
$(function () {

    mainTable.bootstrapTable({
        url: '/transactionDetail/getAll',
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
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "#",
                visible: false
            }, {
                field: "date",
                title: "交易时间"
            }, {
                field: "typeDesc",
                title: "交易类型"
            }, {
                field: "wayDesc",
                title: "交易途径"
            }, {
                field: "amount",
                title: "交易金额"
            }, {
                field: "company",
                title: "交易公司"
            }, {
                field: "wechatPayAccount",
                title: "微信打款账号"
            }, {
                field: "wechatReceiveAccount",
                title: "微信收款账号"
            }, {
                field: "bankPayAccount",
                title: "银行打款账号"
            }, {
                field: "bankReceiveAccount",
                title: "银行收款账号"
            }, {
                field: "bankName",
                title: "银行名称"
            }, {
                field: "checkPeoplePay",
                title: "出票方"
            }, {
                field: "checkPeopleReceive",
                title: "受票方"
            }, {
                field: "checkNumber",
                title: "承兑票号"
            }, {
                field: "checkDate",
                title: "出票日期"
            }, {
                field: "checkDeadLine",
                title: "到期日"
            }, {
                field: "remark",
                title: "备注"
            }, {
                field: "operate",
                title: "操作",
                formatter: delIcon(1)
            }
        ]
        //onExpandRow: showDetails,
        //onClickRow: editRow
    });

    function delIcon(type, value, row, index) {
        return '<a class="icon closed-tool" style="cursor: pointer;" onclick="delData(' + type + ', this)"><i' +
            ' class="fa' +
            ' fa-times"></i></a>';
    }

});