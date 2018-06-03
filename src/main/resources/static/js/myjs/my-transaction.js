
var mainForm = $("#updateDealForm");
var mainTable = $('#dealTable');
var myModal = $('#addDealModal');
var bindModal = $('#bindLogisticsModal');

// 表格属性设定
$(function () {

    mainTable.bootstrapTable({
        url: '/transactionDetail/getAll',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        idTable: "dealTable",
        toolbar : "#toolbar",
        advancedSearch: true,   //开启高级搜索
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        showToggle: true,       // 显示视图切换按钮（分页/卡片）
        pagination: true,       // 在表格底部显示分页条
        showColumns: true,      // 显示内容列下拉框
        showExport: true,
        exportDataType: 'all',
        exportType:[ 'txt', 'doc', 'excel'],  //导出文件类型
        exportOptions:{
            ignoreColumn: [0,'operate'],  //忽略某一列的索引
            fileName: '资金往来' + (new Date()).toLocaleString( )  //文件名称设置
        },
        uniqueId: 'id',
        undefinedText: '',      // null显示空，默认'-'
        detailView: true,//父子表
        pageSize: 10,
        pageList: [10, 15, 20],
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        formatLoadingMessage: function () {
            return "请稍等，正在加载中...";
        },
        formatNoMatches: function () {  //没有匹配的结果
            return '无符合条件的记录';
        },
        columns: [
            {
                field: "bindLogisticsId",
                visible: false
            },
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
                field: "checkPayPeople",
                title: "出票方"
            }, {
                field: "checkReceivePeople",
                title: "受票方"
            }, {
                field: "checkNumber",
                title: "承兑票号"
            }, {
                field: "checkDate",
                title: "出票日期"
            }, {
                field: "checkDeadline",
                title: "到期日"
            }, {
                field: "remark",
                title: "备注"
            }, {
                field: "operate",
                title: "操作",
                formatter: function (value, row, index){
                    return delIcon(1, row);
                }
            }
        ],
        onExpandRow: showDetails,
        onClickRow: editRow
    });

    function showDetails(index, row, $detail) {
        var bindIds = row.bindLogisticsId;
        var cur_table = $detail.html('<table class="table-striped"></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/logistics/getLogisticsDetailsByIds',
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
                    params: bindIds
                };
            },
            formatLoadingMessage: function () {
                return "请稍等，正在加载中...";
            },
            formatNoMatches: function () {  //没有匹配的结果
                return '这条交易记录没有绑定物流信息';
            },
            columns: [
                {
                    field: "id",
                    title: "#",
                    visible: false
                },
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
                    field: "returnWeight",
                    title: "回执数"
                }, {
                    field: "lossWeight",
                    title: "亏吨"
                }, {
                    field: "sellerUnitPrice",
                    title: "厂家结算单价"
                }, {
                    field: "sellerSumPrice",
                    title: "厂家结算金额"
                }, {
                    field: "unitPrice",
                    title: "结算单价"
                }, {
                    field: "sumPrice",
                    title: "结算金额"
                }, {
                    field: "transUnitPrice",
                    title: "运费单价"
                }, {
                    field: "transSumPrice",
                    title: "运费金额"
                }, {
                    field: "profit",
                    title: "利润"
                }, {
                    field: "goodsFrom",
                    title: "物资来源"
                }, {
                    field: "buyerCompany",
                    title: "结算公司"
                }, {
                    field: "transCompany",
                    title: "运输公司"
                }, {
                    field: "weighingNumber",
                    title: "检斤号"
                }, {
                    field: "carNumber",
                    title: "车牌号"
                }, {
                    field: "remark",
                    title: "备注"
                }
            ]
        });
    }

    function editRow(row, $element, field) {

        if (field === "operate") {
            return false;
        }

        clearModal();

        myModal.find('.modal-title').text('编号：' + row['id']);
        $('#txt_deal_date').val(row['date']);
        $('#txt_deal_company').val(row['company']);
        $('#txt_deal_amount').val(row['amount']);
        $('#txt_deal_direction').val(row['type']);
        $('#txt_wechat_pay_account').val(row['wechatPayAccount']);
        $('#txt_wechat_receive_account').val(row['wechatReceiveAccount']);
        $('#txt_bank_pay_account').val(row['bankPayAccount']);
        $('#txt_bank_receive_account').val(row['bankReceiveAccount']);
        $('#txt_bank_name').val(row['bankName']);
        $('#txt_check_pay_people').val(row['checkPayPeople']);
        $('#txt_check_receive_people').val(row['checkReceivePeople']);
        $('#txt_check_number').val(row['checkNumber']);
        $('#txt_check_date').val(row['checkDate']);
        $('#txt_check_deadline').val(row['checkDeadline']);
        $('#txt_deal_remark').val(row['remark']);

        var bindId = row['bindLogisticsId'];

        if (typeof(bindId) === 'undefined'
            || $.trim(bindId) == '') {
            $('#btn_deal_bind_logistics').text('点击绑定');
        } else {
            bindId = bindId.replace('[', '').replace(']', '');
            $('#btn_deal_bind_logistics').text(bindId);
        }

        dealWithInput();
        myModal.modal();
    }

    function delIcon(type, row, index) {
        return '<a class="icon closed-tool" style="cursor: pointer;" onclick="delData(' + row.id + ',' + type + ', this)"><i' +
            ' class="fa' +
            ' fa-times"></i></a>';
    }

});

var dealDetail = myModal.find('.modal-body').find('.row')[1];

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

    // 表格提交按钮
    $("#btn_submit_deal").click(function () {

        mainForm.bootstrapValidator('validate');

        if (mainForm.data('bootstrapValidator').isValid()) {
            var form_data = mainForm.serializeObject();

            var title = $.trim(myModal.find('.modal-title')[0].textContent);
            if (title === '新增') {
                addDeal(form_data);
            } else {
                var id = $.trim((title.split('：'))[1]);
                updateDeal(id, form_data);
            }
        }

        return false;
    });

    // 删除行按钮
    delData = function (delId, type, del_icon) {

        var row = $(del_icon).parent().parent();
        var table = row.parent().parent();

        var url;
        if (type === 1) {
            url = "/transactionDetail/delDeal";
            delDeal(url, $(table), delId);
        }

        return false;
    };

    // 添加资金往来信息
    addDeal = function(form_data) {
        var regex = /^([1-9][0-9]*,?)*$/;
        $.ajax({
            type: "post",
            dataType: 'json',
            url: '/transactionDetail/addDeal',
            data: {params: JSON.stringify(form_data),
                bindLogistics: regex.test($('#btn_deal_bind_logistics').text())?
                    ('[' + $('#btn_deal_bind_logistics').text() + ']') :
                    ""},
            async: false,
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    showPopover($('#btn_submit_deal').children('span'), "添加成功");
                    clearModal();
                    mainTable.bootstrapTable('refresh');
                }
            }
        });
    };

    // 更新资金往来信息按钮
    updateDeal = function(id, form_data) {
        var regex = /^([1-9][0-9]*,?)*$/;
        $.ajax({
            type: "post",
            dataType: 'json',
            url: '/transactionDetail/updateDeal',
            data: {dealId: id,
                params: JSON.stringify(form_data),
                bindLogistics: regex.test($('#btn_deal_bind_logistics').text())?
                    ('[' + $('#btn_deal_bind_logistics').text() + ']') :
                    ""},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    myModal.modal('hide');
                    resetValidator();
                    mainTable.bootstrapTable('refresh');
                }
            }
        })
    };

    // 删除单条附加信息
    delDeal = function(url, table, id) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            async: false,
            data: {delId: id},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    table.bootstrapTable('refresh');
                }
            },
            error : function() {
                alert("ajax请求异常！");
            }
        });
    };

    // 表单清空按钮
    $('#btn_clear_deal_modal').click(function () {
        clearModal();
    });

    // 清空Modal數據
    clearModal = function() {
        $("input").val('');
        $('#btn_deal_bind_logistics').text('点击绑定');
        $(dealDetail).find('input').attr("disabled", false);
        resetValidator();
    };

    resetValidator = function () {
        mainForm.data('bootstrapValidator').destroy();
        mainForm.data('bootstrapValidator', null);
        formValidator();
    };
});

// 绑定相关物流
$(function () {

    var overAllIds = new Set();                // 全局保存选中行的对象

    function examine(type,datas){            // 操作类型，选中的行
        if(type.indexOf('uncheck')==-1){
            $.each(datas,function(i,v){        // 如果是选中则添加选中行的 id
                overAllIds.add(v.id);
            });
        }else{
            $.each(datas,function(i,v){
                overAllIds.delete(v.id);     // 删除取消选中行的 id
            });
        }
    }

    $('#bindTransTable').bootstrapTable({
        url: '/logistics/getLogisticsDetails',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        advancedSearch: true,   //开启高级搜索
        idTable: "bindTransTable",//开启高级搜索绑定的表格
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        showColumns: true,      // 显示内容列下拉框
        uniqueId: 'id',
        undefinedText: '',      // null显示空，默认'-'
        clickToSelect: true,
        pageSize: 10,
        pageList: [10, 15, 20],
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: 'state',
                checkbox: true,
                formatter: function (i,row) {            // 每次加载 checkbox 时判断当前 row 的 id 是否已经存在全局 Set() 里
                    if($.inArray(row.id,Array.from(overAllIds))!=-1){    // 因为 Set是集合,需要先转换成数组
                        return {
                            checked : true               // 存在则选中
                        }
                    }
                }
            },
            {
                field: "id",
                title: "#",
            },
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
                field: "returnWeight",
                title: "回执数"
            }, {
                field: "lossWeight",
                title: "亏吨"
            }, {
                field: "sellerUnitPrice",
                title: "厂家结算单价"
            }, {
                field: "sellerSumPrice",
                title: "厂家结算金额"
            }, {
                field: "unitPrice",
                title: "结算单价"
            }, {
                field: "sumPrice",
                title: "结算金额"
            }, {
                field: "transUnitPrice",
                title: "运费单价"
            }, {
                field: "transSumPrice",
                title: "运费金额"
            }, {
                field: "profit",
                title: "利润"
            }, {
                field: "goodsFrom",
                title: "物资来源"
            }, {
                field: "buyerCompany",
                title: "结算公司"
            }, {
                field: "transCompany",
                title: "运输公司"
            }, {
                field: "weighingNumber",
                title: "检斤号"
            }, {
                field: "carNumber",
                title: "车牌号"
            }, {
                field: "remark",
                title: "备注"
            }
        ]
    });

    $('#bindTransTable').on('uncheck.bs.table check.bs.table check-all.bs.table uncheck-all.bs.table',function(e,rows){
        var datas = $.isArray(rows) ? rows : [rows];        // 点击时获取选中的行或取消选中的行
        examine(e.type,datas);                                 // 保存到全局 Set() 里
    });

    $('#btn_deal_bind_logistics').click(function () {

        $('#bindTransTable').bootstrapTable('refresh');

        var selectText = $(this).text().trim();
        var selectId = selectText.split(',');
        var regex = /^[1-9][0-9]*$/;

        overAllIds = new Set();

        for (var i in selectId) {
            if (regex.test(selectId[i])) {
                overAllIds.add(eval(selectId[i]));
            }
        }

        var title = bindModal.find('.modal-title');

        if (title[0].innerHTML !== '绑定贸易物流') {
            title.text('绑定贸易物流');
        }

        bindModal.modal();
    });

    $('#btn_confirm_logistics').click(function () {

        var arr = Array.from(overAllIds);

        if (arr.length === 0) {
            $('#btn_deal_bind_logistics').text('点击绑定');
        } else {
            $('#btn_deal_bind_logistics').text(arr);
        }

        bindModal.modal('hide');
    });
});

// 初始化事件
$(function () {

    // 手动绑定回车触发表单提交，解决回车刷新页面的问题
    $(document).keydown(function(event){
        if (event.keyCode == 13) {
            mainForm.each(function() {
                $('#btn_submit').click();
                event.preventDefault();
            });
        }
    });

    setDateYYMMDD(mainForm, $('#txt_deal_date'));
    setDateYYMMDD(mainForm, $('#txt_check_date'));
    setDateYYMMDD(mainForm, $('#txt_check_deadline'));

    var weChatInputs = $($(dealDetail).find('.col-sm-3')[0]).find('input');
    var bankInputs = $($(dealDetail).find('.col-sm-3')[1]).find('input');
    var checkInputs = $($(dealDetail).find('.check-input')).find('input');

    // 微信、银行、支票转账三只可选一。其中某个有输入，屏蔽其他几个的input
    $(dealDetail).find('input').blur(function () {
        dealWithInput();
    });

    dealWithInput = function () {
        if (!checkInputsEmpty(weChatInputs)) {
            weChatInputs.attr("disabled", false);
            bankInputs.attr("disabled", true);
            checkInputs.attr("disabled", true);
            bankInputs.val('');
            checkInputs.val('');
        } else if (!checkInputsEmpty(bankInputs)) {
            bankInputs.attr("disabled", false);
            weChatInputs.attr("disabled", true);
            checkInputs.attr("disabled", true);
            weChatInputs.val('');
            checkInputs.val('');
        } else if (!checkInputsEmpty(checkInputs)) {
            checkInputs.attr("disabled", false);
            bankInputs.attr("disabled", true);
            weChatInputs.attr("disabled", true);
            bankInputs.val('');
            weChatInputs.val('');
        } else {
            weChatInputs.attr("disabled", false);
            bankInputs.attr("disabled", false);
            checkInputs.attr("disabled", false);
            weChatInputs.val('')
            bankInputs.val('');
            checkInputs.val('');
        }
    }

});

// 自动补全配置
$(function () {
    $('#txt_deal_company').makeTypeahead(mainForm, '/majorTerms/getTypeaheadData', {getType: 'allCompany'});
    $('#txt_bank_name').makeTypeahead(mainForm, '/majorTerms/getTypeaheadData', {getType: 'bank'})
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
                    message: '交易时间不合法',
                    validators: {
                        notEmpty: {
                            message: '交易时间不可为空'
                        },
                        callback: {
                            callback: function (value, validator, $field) {

                                var dateFormat = /^\d{4}-\d{1,2}-\d{1,2}/;
                                var dateFailure = /^20[0-9][0-9]-(0[1-9]|1[0-2])-((0[1-9])|((1|2)[0-9])|30|31)$/;

                                if (value === "") {
                                    return true;
                                }

                                if (!dateFormat.test(value)) {
                                    return {
                                        valid: false,
                                        message: '交易时间不合法，请输入正确的日期格式（如2018-05-12）'
                                    };
                                }
                                if (!dateFailure.test(value)) {
                                    return {
                                        valid: false,
                                        message: '交易时间不合法，日期不存在'
                                    };
                                }
                                return true;
                            }
                        }
                    }
                },
                txt_deal_company: {

                },
                txt_deal_amount: {
                    message: '交易金额不合法',
                    validators: {
                        notEmpty: {
                            message: '交易金额不可为空'
                        },
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_deal_direction: {
                    message: '交易流向不合法',
                    validators:{
                        notEmpty: {
                            message: '交易流向不可为空'
                        }
                    }
                },
                txt_wechat_pay_account: {

                },
                txt_wechat_receive_account: {

                },
                txt_bank_pay_account: {
                    message: '银行打款账号不合法',
                    validators: {
                        regexp: {
                            regexp: '^([0-9]*)$',
                            message: '请输入纯数字账号'
                        }
                    }
                },
                txt_bank_receive_account: {
                    message: '银行收款账号不合法',
                    validators: {
                        regexp: {
                            regexp: '^([0-9]*)$',
                            message: '请输入纯数字账号'
                        }
                    }
                },
                txt_bank_name: {

                },
                txt_check_pay_account: {

                },
                txt_check_receive_account: {

                },
                txt_check_number: {
                    message: '承兑票号不合法',
                    validators: {
                        regex: {
                            regexp: '^([0-9]*)$',
                            message: '请输入纯数字承兑票号'
                        }
                    }
                },
                txt_check_date: {
                    message: '出票时间不合法',
                    validators: {
                        callback: {
                            callback: function (value, validator, $field) {

                                var dateFormat = /^\d{4}-\d{1,2}-\d{1,2}/;
                                var dateFailure = /^20[0-9][0-9]-(0[1-9]|1[0-2])-((0[1-9])|((1|2)[0-9])|30|31)$/;

                                if (value === "") {
                                    return true;
                                }

                                if (!dateFormat.test(value)) {
                                    return {
                                        valid: false,
                                        message: '出票时间不合法，请输入正确的日期格式（如2018-05-12）'
                                    };
                                }
                                if (!dateFailure.test(value)) {
                                    return {
                                        valid: false,
                                        message: '出票时间不合法，日期不存在'
                                    };
                                }
                                return true;
                            }
                        }
                    }
                },
                txt_check_deadline: {
                    message: '到期时间不合法',
                    validators: {
                        callback: {
                            callback: function (value, validator, $field) {

                                var dateFormat = /^\d{4}-\d{1,2}-\d{1,2}/;
                                var dateFailure = /^20[0-9][0-9]-(0[1-9]|1[0-2])-((0[1-9])|((1|2)[0-9])|30|31)$/;

                                if (value === "") {
                                    return true;
                                }

                                if (!dateFormat.test(value)) {
                                    return {
                                        valid: false,
                                        message: '到期时间不合法，请输入正确的日期格式（如2018-05-12）'
                                    };
                                }
                                if (!dateFailure.test(value)) {
                                    return {
                                        valid: false,
                                        message: '到期时间不合法，日期不存在'
                                    };
                                }
                                return true;
                            }
                        }
                    }
                },
                txt_deal_remark: {
                    message: '备注不合法',
                    validators: {
                        stringLength: {
                            max: 100,
                            message: '输入超过上限'
                        }
                    }
                }
            }
        })
    };

    formValidator();
});
