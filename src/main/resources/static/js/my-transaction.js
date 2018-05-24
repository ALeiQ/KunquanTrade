
var mainForm = $("#updateDealForm");
var mainTable = $('#dealTable');
var myModal = $('#addDealModal');

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

// 表格功能设定
$(function () {

    // “增加”按钮显示模态框
    $('#addDeal').click(function () {
        var title = myModal.find('.modal-title');

        if (title[0].innerHTML !== '新增') {
            title.text('新增');
            clearModal();
        }

        myModal.modal();
    });

    // 清空Modal數據
    clearModal = function() {
        $("input").val('');
        resetValidator();
    };

    resetValidator = function () {
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
        formValidator();
    };

});

// 初始化事件
$(function () {

    // 手动绑定回车触发表单提交，解决回车刷新页面的问题
    $(document).keydown(function(event){
        if (event.keyCode == 13) {
            form.each(function() {
                $('#btn_submit').click();
                event.preventDefault();
            });
        }
    });

    setDateYYMMDD(mainForm, $('#txt_deal_date'));
    setDateYYMMDD(mainForm, $('#txt_check_date'));
    setDateYYMMDD(mainForm, $('#txt_check_deadline'));

    var dealDetail = myModal.find('.modal-body').find('.row')[1];

    var weChatInputs = $($(dealDetail).find('.col-sm-3')[0]).find('input');
    var bankInputs = $($(dealDetail).find('.col-sm-3')[1]).find('input');
    var checkInputs = $($(dealDetail).find('.check-input')).find('input');

    // 微信、银行、支票转账三只可选一。其中某个有输入，屏蔽其他几个的input
    $(dealDetail).find('input').blur(function () {
        dealWithInput($(this))
    });

    dealWithInput = function (inp) {
        var id = inp.attr('id');
        var tag = id.split('_')[1];

        switch(tag) {
            case "wechat":
                if (checkInputsEmpty(weChatInputs)) {
                    bankInputs.attr("disabled", false);
                    checkInputs.attr("disabled", false);
                } else {
                    bankInputs.attr("disabled", true);
                    checkInputs.attr("disabled", true);
                    bankInputs.val('');
                    checkInputs.val('');
                }
                break;
            case "bank":
                if (checkInputsEmpty(bankInputs)) {
                    weChatInputs.attr("disabled", false);
                    checkInputs.attr("disabled", false);
                } else {
                    weChatInputs.attr("disabled", true);
                    checkInputs.attr("disabled", true);
                    weChatInputs.val('');
                    checkInputs.val('');
                }
                break;
            case "check":
                if (checkInputsEmpty(checkInputs)) {
                    weChatInputs.attr("disabled", false);
                    bankInputs.attr("disabled", false);
                } else {
                    weChatInputs.attr("disabled", true);
                    bankInputs.attr("disabled", true);
                    weChatInputs.val('');
                    bankInputs.val('');
                }
                break;
        }
    }

});

// 校验配置
$(function () {

    formValidator = function () {
        mainForm.bootstrapValidator({
            //excluded:[":hidden",":disabled",":not(visible)"], // 默认不验证隐藏域和不可用域
            excluded: [":hidden"],
            message: '输入值不合法',
            submitButtons: 'button[type="submit"]',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                txt_deal_date: {

                },
                txt_deal_company: {

                },
                txt_deal_amount: {

                },
                txt_deal_direction: {

                },
                txt_wechat_pay_account: {

                },
                txt_wechat_receive_account: {

                },
                txt_bank_pay_account: {

                },
                txt_bank_receive_account: {

                },
                txt_check_pay_account: {

                },
                txt_check_receive_account: {

                },
                txt_check_number: {

                },
                txt_check_date: {

                },
                txt_check_deadline: {

                }
            }
        })
    };

    formValidator();
});
