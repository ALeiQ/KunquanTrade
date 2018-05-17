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
                field: "returnWeight",
                title: "回执数"
            }, {
                field: "lossWeight",
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
                field: "transUnitPrice",
                title: "运费单价"
            }, {
                field: "transSumPrice",
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

// 表格功能设置
$(function () {

    var myModal = $('#addTransportDetailModal');
    var form = $("#updateForm");

    // “增加”按钮显示模态框
    addItem = function () {
        myModal.modal();
    };

    $(document).ready(function() {
        form.bootstrapValidator({
            message: '输入值不合法',
            submitButtons: 'button[type="submit"]',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                txt_net_weight: {
                    message: '净重不合法',
                    validators: {
                        regexp: {
                            regexp: '^([1-9][0-9]*)+([.][0-9]*)?$',
                            message: '请输入整数或者小数'
                        }
                    }
                },
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
                }
                , email: {
                    validators: {
                        notEmpty: {
                            message: 'email不能为空'
                        },
                        emailAddress: {
                            message: '请输入正确的邮件地址如：123@qq.com'
                        }
                    }
                }, phone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: "^([0-9]{11})?$",
                            message: '手机号码格式错误'
                        }
                    }
                }, address: {
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
            }
        });
    });

    $("#btn_submit").click(function () {
        form.bootstrapValidator('validate');
        if (form.data('bootstrapValidator').isValid()) {
            clearModal();
            return false;
        }
    });

    // 清空Modal數據
    clearModal = function() {
        $('input').val('');
        form.data("bootstrapValidator").resetForm();
    };

    // 提交表单
    check_form = function () {
        var user_id = $.trim($('#user_id').val());
        var act     = $.trim($('#act').val());

        if(!user_id)
        {
            alert('用户ID不能为空！');
            return false;
        }
        var form_data = $('#form_data').serialize();

        // 异步提交数据到action/add_action.php页面
        $.ajax(
            {
                url: "action/user_action.php",
                data:{"form_data":form_data,"act":act},
                type: "post",
                beforeSend:function()
                {
                    $("#tip").html("<span style='color:blue'>正在处理...</span>");
                    return true;
                },
                success:function(data)
                {
                    if(data > 0)
                    {

                        var msg = "添加";
                        if(act == "edit") msg = "编辑";
                        $("#tip").html("<span style='color:blueviolet'>恭喜，" +msg+ "成功！</span>");
                        // document.location.href='system_notice.php'
                        alert(msg + "OK！");
                        location.reload();
                    }
                    else
                    {
                        $("#tip").html("<span style='color:red'>失败，请重试</span>");
                        alert('操作失败');
                    }
                },
                error:function()
                {
                    alert('请求出错');
                },
                complete:function()
                {
                    $('#acting_tips').hide();
                }
            });

        return false;
    };

    $('#btn_clear_modal').click(function () {
        clearModal();
    });

});
