/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

// bootstrap表格属性设定
$(function () {

    $('#transTable').bootstrapTable({
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
        pageList: [10, 15, 20],
        pageSize: 10,
        responseHandler : function(res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        },
        columns: [
            {
                field: "id",
                title: "编号"
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
        ]
    });

    function delIcon(value, row, index) {
        return '<a class="icon closed-tool" onclick="delRow(this)"><i class="fa' +
            ' fa-times"></i></a>';
    }

});

var myModal = $('#addLogisticsDetailModal');
var form = $("#updateForm");

// 表格功能设置
$(function () {

    // “增加”按钮显示模态框
    addItem = function () {
        myModal.modal();
    };

    // 表单提交按钮
    $("#btn_submit").click(function () {
        form.bootstrapValidator('validate');
        if (form.data('bootstrapValidator').isValid()) {
            var form_data = form.serializeObject();
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
                    }
                }
            });
        }
        return false;
    });

    // 表单清空按钮
    $('#btn_clear_modal').click(function () {
        clearModal();
    });

    // 清空Modal數據
    clearModal = function() {
        $("input").val('');
        form.data("bootstrapValidator").resetForm();
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

    // 发车时间的日历组件
    $('#txt_load_time').datetimepicker({
        format: 'yyyy-mm-dd',//显示格式
        todayHighlight: 1,//今天高亮
        minView: "month",//设置只显示到月份
        startView:2,
        forceParse: 0,
        showMeridian: 1,
        autoclose: 1//选择后自动关闭
    }).on('hide', function (e) {
        // 关闭后手动触发校验（否则不自动校验）
        form.data('bootstrapValidator')
            .updateStatus('txt_load_time', 'NOT_VALIDATED',null)
            .validateField('txt_load_time');
    });

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

    $('#txt_goods_from').typeahead({
        items: 'all',
        minLength: 0,
        showHintOnFocus: true,
        autoSelect: false,
        source: function (query, process) {
            $.get('/logistics/getTypeaheadData', {getType: 'goodsFrom', query: query}, function (result) {
                return process(result.data);
            });
        },
        afterSelect: function () {
            form.data('bootstrapValidator')
                .updateStatus('txt_goods_from', 'NOT_VALIDATED',null)
                .validateField('txt_goods_from');
        }
    });
    $('#txt_buyer_company').typeahead({
        items: 'all',
        minLength: 0,
        showHintOnFocus: true,
        autoSelect: false,
        source: function (query, process) {
            $.get('/logistics/getTypeaheadData', {getType: 'buyerCompany', query: query}, function (result) {
                return process(result.data);
            });
        },
        afterSelect: function () {
            form.data('bootstrapValidator')
                .updateStatus('txt_buyer_company', 'NOT_VALIDATED',null)
                .validateField('txt_buyer_company');
        }
    });
    $('#txt_trans_company').typeahead({
        items: 'all',
        minLength: 0,
        showHintOnFocus: true,
        autoSelect: false,
        source: function (query, process) {
            $.get('/logistics/getTypeaheadData', {getType: 'transCompany', query: query}, function (result) {
                return process(result.data);
            });
        },
        afterSelect: function () {
            form.data('bootstrapValidator')
                .updateStatus('txt_trans_company', 'NOT_VALIDATED',null)
                .validateField('txt_trans_company');
        }
    });
    $('#txt_goods_name').typeahead({
        items: 'all',
        minLength: 0,
        showHintOnFocus: true,
        autoSelect: false,
        source: function (query, process) {
            $.get('/logistics/getTypeaheadData', {getType: 'goodsName', query: query}, function (result) {
                return process(result.data);
            });
        },
        afterSelect: function () {
            form.data('bootstrapValidator')
                .updateStatus('txt_goods_name', 'NOT_VALIDATED',null)
                .validateField('txt_goods_name');
        }
    });
    $('#txt_goods_model').typeahead({
        items: 'all',
        minLength: 0,
        showHintOnFocus: true,
        autoSelect: false,
        source: function (query, process) {
            $.get('/logistics/getTypeaheadData',
                {getType: 'goodsModel', query: query, goodsName: $('#txt_goods_name').val()},
                function (result) {
                return process(result.data);
            });
        },
        afterSelect: function () {
            form.data('bootstrapValidator')
                .updateStatus('txt_goods_model', 'NOT_VALIDATED',null)
                .validateField('txt_goods_model');
        }
    });

    $('#txt_goods_from').focus(function (){
        $('#txt_goods_from').typeahead('show')
    });
    $('#txt_buyer_company').focus(function (){
        $('#txt_buyer_company').typeahead('show')
    });
    $('#txt_trans_company').focus(function (){
        $('#txt_trans_company').typeahead('show')
    });
    $('#txt_goods_name').focus(function (){
        $('#txt_goods_name').typeahead('show')
    });
});

//

// 校验配置
$(document).ready(function() {
    form.bootstrapValidator({
        //excluded:[":hidden",":disabled",":not(visible)"], // 默认不验证隐藏域和不可用域
        excluded:[":hidden"],
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

                            var dateFailure = /^20[0-9][0-9]-(0[1-9]|1[0-2])-((0[1-9])|((1|2)[0-9])|30|31)$/;

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
});

// 工具方法
$(function () {
    // 将value保留两位小数（截尾）并补全小数点后两个零
    returnFloat = function (value){
        var value=parseInt(parseFloat(value)*100)/100;
        var xsd=value.toString().split(".");
        if(xsd.length==1){
            value=value.toString()+".00";
            return value;
        }
        if(xsd.length>1){
            if(xsd[1].length<2){
                value=value.toString()+"0";
            }
            return value;
        }
    }
});

