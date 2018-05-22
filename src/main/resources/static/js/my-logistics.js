/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

var myModal = $('#addLogisticsDetailModal');
var form = $("#updateForm");
var table = $('#transTable');

// bootstrap表格属性设定
$(function () {

    table.bootstrapTable({
        url: '/logistics/getLogisticsDetails',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        showColumns: true,      // 显示内容列下拉框
        showToggle: true,       // 显示视图切换按钮（分页/卡片）
        uniqueId: 'id',
        undefinedText: '',      // null显示空，默认'-'
        pageSize: 10,
        pageList: [10, 15, 20],
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
            }, {
                field: "operate",
                title: "操作",
                formatter: delIcon
            }
        ],
        onClickRow: editRow
    });

    function editRow(row, $element, field) {

        if (field === "operate") {
            return false;
        }

        clearModal();
        myModal.find('.modal-title').text('编号：' + row['id']);
        $('#txt_goods_from').val(row['goodsFrom']);
        $('#txt_seller_unit_price').val(row['sellerUnitPrice']);
        $('#txt_seller_sum_price').val(row['sellerSumPrice']);
        $('#txt_buyer_company').val(row['buyerCompany']);
        $('#txt_unit_price').val(row['unitPrice']);
        $('#txt_sum_price').val(row['sumPrice']);
        $('#txt_trans_company').val(row['transCompany']);
        $('#txt_trans_unit_price').val(row['transUnitPrice']);
        $('#txt_trans_sum_price').val(row['transSumPrice']);
        $('#txt_load_time').val(row['loadTime']);
        $('#txt_weighing_number').val(row['weighingNumber']);
        $('#txt_car_number').val(row['carNumber']);
        $('#txt_net_weight').val(row['netWeight']);
        $('#txt_return_weight').val(row['returnWeight']);
        $('#txt_loss_weight').val(row['lossWeight']);
        $('#txt_goods_name').val(row['goodsName']);
        $('#txt_goods_model').val(row['goodsModel']);
        $('#txt_profit').val(row['profit']);
        $('#txt_remark').val(row['remark']);
        myModal.modal();
    }

    function delIcon(value, row, index) {
        return '<a class="icon closed-tool" style="cursor: pointer;" onclick="delData(this)"><i class="fa' +
            ' fa-times"></i></a>';

    }

});

// 表格功能设置
$(function () {

    // “增加”按钮显示模态框
    $('#addGoods').click(function () {
        var title = myModal.find('.modal-title');

        if (title[0].innerHTML !== '新增') {
            title.text('新增');
            clearModal();
        }

        myModal.modal();
    });

    // 表单提交按钮
    $("#btn_submit").click(function () {

        form.bootstrapValidator('validate');

        if (form.data('bootstrapValidator').isValid()) {
            var form_data = form.serializeObject();

            var title = $.trim(myModal.find('.modal-title')[0].textContent);

            if (title === '新增') {
                addLogisticsDetail(form_data);
            } else {
                var id = $.trim((title.split('：'))[1]);
                updateLogisticsDetail(id, form_data);
            }
        }

        return false;
    });

    addLogisticsDetail = function(form_data) {
        $.ajax({
            type: "post",
            dataType: 'json',
            url: '/logistics/addLogisticsDetail',
            data: {params: JSON.stringify(form_data)},
            async: false,
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    showPopover($('#btn_submit').children('span'), "添加成功");
                    clearModal();
                    table.bootstrapTable('refresh');
                }
            }
        });
    };

    updateLogisticsDetail = function(id, form_data) {
        $.ajax({
            type: "post",
            dataType: 'json',
            url: '/logistics/updateLogisticsDetail',
            data: {id: id, params: JSON.stringify(form_data)},
            async: false,
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    showPopover($('#btn_submit').children('span'), "修改成功");
                    myModal.modal('hide');
                    resetValidator();
                    table.bootstrapTable('refresh');
                }
            }
        });
    };

    // 删除行按钮
    delData = function (del_icon) {

        var row = $(del_icon).parent().parent();

        delLogisticsDetail($(row.children()[0])[0].innerText);

        return false;
    };

    // 删除单条数据ajax请求
    delLogisticsDetail = function (id) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: '/logistics/delLogisticsDetail',
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
    $('#btn_clear_modal').click(function () {
        clearModal();
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

    setDateYYMMDD($('#txt_load_time'));

    // 净重监听
    $('#txt_net_weight').change(function () {
        getSumPrice($('#txt_seller_unit_price'), $('#txt_seller_sum_price'));
        getSumPrice($('#txt_unit_price'), $('#txt_sum_price'));
        getSumPrice($('#txt_trans_unit_price'), $('#txt_trans_sum_price'));
    });

    // 金额Input监听
    $('#txt_seller_unit_price').change(function (){
        getDoublePoint($(this));
        getSumPrice($(this), $('#txt_seller_sum_price'));
    });
    $('#txt_unit_price').change(function (){
        getDoublePoint($(this));
        getSumPrice($(this), $('#txt_sum_price'));
    });
    $('#txt_trans_unit_price').change(function (){
        getDoublePoint($(this));
        getSumPrice($(this), $('#txt_trans_sum_price'));
    });

    // 总价Input监听
    $('#txt_seller_sum_price').change(function (){
        getDoublePoint($(this));
        getProfit();
    });
    $('#txt_sum_price').change(function (){
        getDoublePoint($(this));
        getProfit();
    });
    $('#txt_trans_sum_price').change(function (){
        getDoublePoint($(this));
        getProfit();
    });

    // 重量Input监听
    $('#txt_net_weight').change(function () {
        getDoublePoint($(this));
        getLossWeight();
    });
    $('#txt_return_weight').change(function () {
        getDoublePoint($(this));
        getLossWeight();
    });

    getSumPrice = function (unit, sum) {

        netWeight = eval($('#txt_net_weight').val());
        unitPrice = eval(unit.val());

        if (isNaN(unitPrice) || isNaN(netWeight)) {
            return false;
        }

        sum.val(returnFloat(unitPrice*netWeight));
        getProfit();
    };

    getProfit = function () {
        // 购买花费
        var buy = eval($('#txt_seller_sum_price').val());
        // 卖出金额
        var sell = eval($('#txt_sum_price').val());
        // 运费
        var trans = eval($('#txt_trans_sum_price').val());

        if (isNaN(buy)) {
            return false;
        }

        if (isNaN(sell)) {
            sell = 0;
        }

        if (isNaN(trans)) {
            trans = 0;
        }

        var profit = returnFloat(sell-buy-trans);
        $('#txt_profit').val(profit);
    };

    getLossWeight = function () {
        // 净重
        var netWeight = eval($('#txt_net_weight').val());
        // 回执数
        var returnWeight = eval($('#txt_return_weight').val());

        if (isNaN(netWeight) || isNaN(returnWeight)) {
            return false;
        }

        var lossWeigth = returnFloat(netWeight-returnWeight);
        $('#txt_loss_weight').val(lossWeigth);
    };

    getDoublePoint = function(input) {
        if(!isNaN(eval(input.val())))
        {
            input.val(returnFloat(input.val()));
        }
    };

});

// 自动补全配置
$(function () {

    $.fn.makeTypeahead = function(url, params) {
        var inp = this;
        inp.typeahead({
            items: 'all',
            minLength: 0,
            showHintOnFocus: true,
            autoSelect: false,
            changeInputOnMove: false,
            hint: false,
            source: function (query, process) {
                params['goodsName'] = $('#txt_goods_name').val();
                params['query'] = query;
                $.get(url, params,
                    function (result) {
                        return process(result.data);
                    });
            },
            afterSelect: function () {
                startValidator(inp);
            }
        });
    };

    $('#txt_goods_model').makeTypeahead('/logistics/getTypeaheadData', {getType: 'goodsModel'});
    $('#txt_goods_name').makeTypeahead('/logistics/getTypeaheadData', {getType: 'goodsName'});
    $('#txt_trans_company').makeTypeahead('/logistics/getTypeaheadData', {getType: 'transCompany'});
    $('#txt_buyer_company').makeTypeahead('/logistics/getTypeaheadData', {getType: 'company'});
    $('#txt_goods_from').makeTypeahead('/logistics/getTypeaheadData', {getType: 'company'});
});

//

// 校验配置
$(document).ready(function() {
    formValidator = function () {
        form.bootstrapValidator({
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
                txt_load_time: {
                    message: '装车时间不合法',
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
                                        message: '装车时间不合法，请输入正确的日期格式（如2018-05-12）'
                                    };
                                }
                                if (!dateFailure.test(value)) {
                                    return {
                                        valid: false,
                                        message: '装车时间不合法，日期不存在'
                                    };
                                }
                                return true;
                            }
                        }
                    }
                },
                txt_goods_from: {
                    message: '物资来源不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_seller_unit_price: {
                    message: '厂家结算单价不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_seller_sum_price: {
                    message: '厂家结算金额不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_buyer_company: {
                    message: '结算公司不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_unit_price: {
                    message: '结算单价不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_sum_price: {
                    message: '结算金额不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_trans_company: {
                    message: '运输公司不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_trans_unit_price: {
                    message: '运费单价不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_trans_sum_price: {
                    message: '运费金额不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_profit: {
                    message: '利润不合法',
                    validators: {
                        regexp: {
                            regexp: '^[-]?(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_net_weight: {
                    message: '净重不合法',
                    validators: {
                        notEmpty: {
                            message: '净重不能为空'
                        },
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_return_weight: {
                    message: '回执数不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_loss_weight: {
                    message: '亏吨不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_goods_name: {
                    message: '物资名称不合法',
                    validators: {
                        notEmpty: {
                            message: '物资名称不可为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_goods_model: {
                    message: '物资型号不合法',
                    validators: {
                        stringLength: {
                            max: 10,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_weighing_number: {
                    message: '检斤号不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_car_number: {
                    message: '车牌号不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_remark: {
                    message: '备注不合法',
                    validators: {
                        stringLength: {
                            max: 100,
                            message: '输入超过上限'
                        }
                    }
                }
                /**
                 ,
                 loginname: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: '请输入3到30个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                        message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                    }
                }
            },
                 email: {
                validators: {
                    notEmpty: {
                        message: 'email不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    }
                }
            },
                 phone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: "^([0-9]{11})?$",
                        message: '手机号码格式错误'
                    }
                }
            },
                 address: {
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    }, stringLength: {
                        min: 8,
                        max: 60,
                        message: '请输入5到60个字符'
                    }
                }
            }
                 **/
            }
        });
    };

    formValidator();
});

