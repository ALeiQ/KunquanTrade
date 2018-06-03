
var mainTable = $('#invoiceTable');
var queryDirection = 0;
var myModal = $('#addInvoiceModal');
var mainForm = $("#updateInvoiceForm");

// 表格属性设定
$(function () {

    mainTable.bootstrapTable({
        url: '/invoice/getAllByDirection',
        method: 'get',
        dataType: 'json',
        dataFiled: 'data',
        advancedSearch: true,   //开启高级搜索
        idTable: "invoiceTable",
        search: true,           // 显示检索框
        showRefresh: true,      // 显示刷新按钮
        pagination: true,       // 在表格底部显示分页条
        showColumns: true,      // 显示内容列下拉框
        showToggle: true,       // 显示视图切换按钮（分页/卡片）
        toolbar : "#toolbar",
        showExport: true,
        exportDataType: 'all',
        exportTypes:['txt', 'doc', 'excel'],  //导出文件类型
        exportOptions:{
            ignoreColumn: [0,'operate'],  //忽略某一列的索引
            fileName: '开票明细' + (new Date()).toLocaleString( )  //文件名称设置
        },
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
                title: "#",
                visible: false
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
                formatter: function (value, row, index) {
                    return delIcon(1, value, row, index);
                }
            }
        ],
        onExpandRow: showDetails,
        onClickRow: editRow
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
                    field: "id",
                    title: "#",
                    visible: false
                },
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
                }, {
                    field: "operate",
                    title: "操作",
                    formatter: function (value, row, index) {
                        return delIcon(2, value, row, index);
                    }
                }
            ]
        });
    }

    function editRow(row, $element, field) {

        if (field === "operate") {
            return false;
        }

        mainForm.data('bootstrapValidator').destroy();
        mainForm.data('bootstrapValidator', null);
        var inbox = $('.inbox-detail');
        var inputs = inbox.find('input');
        inputs.parent().parent().remove();
        formValidator();

        myModal.find('.modal-title').text('编号：' + row['id']);
        $('#txt_make_date').val(row['makeDate']);
        $('#txt_use_date').val(row['useDate']);
        $('#txt_pay_company').val(row['payCompany']);
        $('#txt_receive_company').val(row['receiveCompany']);
        $('#txt_number').val(row['number']);
        $('#txt_type').val(row['type']);
        $('#txt_amount').val(row['amount']);
        $('#txt_invoice_remark').val(row['remark']);

        var data = ajaxGetDetails(row['id']);
        var btn = $('#btn_add_invoice_detail_input');

        for (var i in data) {
            addInvoiceDetailInput(btn, data[i]);
        }

        myModal.modal();
    }

    function delIcon(type, value, row, index) {
        return '<a class="icon closed-tool" style="cursor: pointer;" onclick="delData('+ row.id + ',' + type + ', this)"><i' +
            ' class="fa' +
            ' fa-times"></i></a>';
    }

    ajaxGetDetails = function (invoidId) {
        var data;
        $.ajax({
            type: "get",
            dataType: 'json',
            url: '/invoice/getInvoiceDetailsById',
            async: false,
            data: {queryId: invoidId},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    data = result.data;
                }
            }
        });
        return data;
    };

});

// 表格功能设置
$(function () {

    // “增加”按钮显示模态框
    $('#addInvoic').click(function () {
        var title = myModal.find('.modal-title');

        if (title[0].innerHTML !== '新增') {
            title.text('新增');
            clearModal();
        }

        myModal.modal();
    });

    // 删除行按钮
    delData = function (delId, type, del_icon) {

        var row = $(del_icon).parent().parent();
        var table = row.parent().parent();

        var url;
        if (type === 1) {
            url = "/invoice/delInvoice";
            delInvoice(url, $(table), delId);
        } else if (type === 2) {
            url = "/invoice/delInvoiceDetail";
            delInvoice(url, $(table), delId);
        }


        return false;
    };


    // 提交表单按钮
    $("#btn_submit_invoice").click(function () {

        mainForm.bootstrapValidator('validate');

        if (mainForm.data('bootstrapValidator').isValid()) {
            var load = $(this).prev().prev()[0];
            load.style.display="";
            var form_data = mainForm.serializeObject();
            var details_data = getDetails(form_data);

            var title = $.trim(myModal.find('.modal-title')[0].textContent);
            if (title === '新增') {
                addInvoice(load, form_data, details_data);
            } else {
                var id = $.trim((title.split('：'))[1]);
                updateInvoice(id, load, form_data, details_data);
            }
        }

        return false;
    });

    // 添加详细货品信息按钮
    addInvoice = function(load, form_data, invoiceDetails) {
        $.ajax({
            type: "post",
            dataType: 'json',
            url: '/invoice/addInvoice',
            data: {params: JSON.stringify(form_data),
                details: JSON.stringify(invoiceDetails)},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    load.style.display="none";
                    showPopover($('#btn_submit_invoice').children('span'), "添加成功");
                    clearModal();
                    mainTable.bootstrapTable('refresh');
                }
            }
        })
    };

    // 更新详细货品信息按钮
    updateInvoice = function(id, load, form_data, invoiceDetails) {
        $.ajax({
            type: "post",
            dataType: 'json',
            url: '/invoice/updateInvoice',
            data: {invoiceId: id,
                params: JSON.stringify(form_data),
                details: JSON.stringify(invoiceDetails)},
            success: function (result) {
                if (result.resultCode !== 0) {
                    alert(result.resultMsg);
                } else {
                    myModal.modal('hide');
                    load.style.display="none";
                    resetValidator();
                    mainTable.bootstrapTable('refresh');
                }
            }
        })
    };

    // 删除单条附加信息
    delInvoice = function(url, table, delId) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            async: false,
            data: {delId: delId},
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

    // 获取表单中附加信息的输入
    getDetails = function(form_data) {
        var details_data = [];
        var names = ['txt_invoice_goods_name',
            'txt_invoice_goods_model',
            'txt_invoice_number',
            'txt_invoice_sum_price',
            'txt_invoice_unit_price',
            'txt_invoice_tax'];

        var list = [];
        var details = $('#btn_add_invoice_detail_input').prevAll();
        var len = details.length;

        for (var i in names) {
            var l = form_data[names[i]];
            if (typeof(l) === 'undefined' || !(l instanceof Array)) {
                l = [l];
            }
            list.push(l);
        }

        for (var i in list[0]) {

            var flag = false;
            for (var j in names) {
                if (list[j][i] !== "") {
                    flag = true;
                    break;
                }
            }

            if (flag === false) {
                continue;
            }

            var detail = {};
            for (var j in names) {
                detail[names[j]] = list[j][i];
            }
            detail['id'] = $(details[len-(eval(i)+1)*6-1]).val();
            details_data.push(detail);
        }

        return details_data;
    };

    // 增加开票明细详情输入栏
    $('#btn_add_invoice_detail_input').click(function () {
        addInvoiceDetailInput($(this), {});
    });

    // 表单清空按钮
    $('#btn_clear_invoice_modal').click(function () {
        clearModal();
    });

    addInvoiceDetailInput = function(btn, detail) {

        var list = createInvoiceDetailInput();

        var goodsNameInp;
        var goodsModelInp;

        for (var i in list) {
            $(btn).before(list[i]);

            switch (i) {
                case "0":
                    goodsNameInp = $(btn).prev().find('input');
                    $(btn).prev().find('input').val(detail['goodsName']);
                    $(btn).prev().val(detail['id']);
                    break;
                case "1":
                    goodsModelInp = $(btn).prev().find('input');
                    $(btn).prev().find('input').val(detail['goodsModel']);
                    break;
                case "2":
                    $(btn).prev().find('input').val(detail['number']);
                    break;
                case "3":
                    $(btn).prev().find('input').val(detail['unitPrice']);
                    break;
                case "4":
                    $(btn).prev().find('input').val(detail['sumPrice']);
                    break;
                case "5":
                    $(btn).prev().find('input').val(detail['tax']);
                    break;
            }
        }

        $(goodsNameInp).makeTypeahead(mainForm, '/majorTerms/getTypeaheadData', {getType: 'goodsName'});
        $(goodsModelInp).makeTypeahead(mainForm, '/majorTerms/getTypeaheadData', {getType: 'goodsModel'}, goodsNameInp);

        resetValidator();
    };

    // 清空Modal數據
    clearModal = function() {
        $("input").val('');
        $('#btn_add_invoice_detail_input').prevAll().val('');
        resetValidator();
    };

    resetValidator = function () {
        mainForm.data('bootstrapValidator').destroy();
        mainForm.data('bootstrapValidator', null);
        formValidator();
    };
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

    // 抵扣时间的日历组件
    setDateYYMMDD(mainForm, $('#txt_use_date'));
    // 开票时间的日历组件
    setDateYYMMDD(mainForm, $('#txt_make_date'));

});

// 自动补全配置
$(function () {
    $('#txt_pay_company').makeTypeahead(mainForm, '/majorTerms/getTypeaheadData', {getType: 'company'});
    $('#txt_receive_company').makeTypeahead(mainForm, '/majorTerms/getTypeaheadData', {getType: 'company'});
});

// 校验配置
$(document).ready(function () {
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
                txt_make_date: {
                    message: '开票时间不合法',
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
                                        message: '开票时间不合法，请输入正确的日期格式（如2018-05-12）'
                                    };
                                }
                                if (!dateFailure.test(value)) {
                                    return {
                                        valid: false,
                                        message: '开票时间不合法，日期不存在'
                                    };
                                }
                                return true;
                            }
                        }
                    }
                },
                txt_use_date: {
                    message: '抵扣时间不合法',
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
                                        message: '抵扣时间不合法，请输入正确的日期格式（如2018-05-12）'
                                    };
                                }
                                if (!dateFailure.test(value)) {
                                    return {
                                        valid: false,
                                        message: '抵扣时间不合法，日期不存在'
                                    };
                                }
                                return true;
                            }
                        }
                    }
                },
                txt_pay_company: {
                    message: '开票单位不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_receive_company: {
                    message: '受票单位不合法',
                    validators: {
                        stringLength: {
                            max: 25,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_number: {
                    message: '票号不合法',
                    validators: {
                        stringLength: {
                            max: 50,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_amount: {
                    message: '发票总额合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_invoice_remark: {
                    message: '备注不合法',
                    validators: {
                        stringLength: {
                            max: 100,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_invoice_goods_name: {
                    message: '物资名称不合法',
                    validators: {
                        stringLength: {
                            max: 10,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_invoice_goods_model: {
                    message: '物资型号不合法',
                    validators: {
                        stringLength: {
                            max: 10,
                            message: '输入超过上限'
                        }
                    }
                },
                txt_invoice_number: {
                    message: '数量不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_invoice_unit_price: {
                    message: '单价不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_invoice_sum_price: {
                    message: '总额不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                },
                txt_invoice_tax: {
                    message: '税额不合法',
                    validators: {
                        regexp: {
                            regexp: '^(0|([1-9][0-9]*))+([.][0-9]*)?$',
                            message: '请输入非零开头的整数或者小数'
                        }
                    }
                }
            }
        })
    };

    formValidator();
});

// 动态拼接div
$(function () {

    var needInsert = [{inp_name: "txt_invoice_goods_name", placeholder: "物资名称"},
        {inp_name: "txt_invoice_goods_model", placeholder: "型号"},
        {inp_name: "txt_invoice_number", placeholder: "数量"},
        {inp_name: "txt_invoice_unit_price", placeholder: "单价（含税）"},
        {inp_name: "txt_invoice_sum_price", placeholder: "总额（含税）"},
        {inp_name: "txt_invoice_tax", placeholder: "税额"}];

    createInvoiceDetailInput = function () {

        var inputs = [];

        for (i in needInsert) {
            inputs.push(createInputDiv(needInsert[i]['inp_name'], needInsert[i]['placeholder']));
        }

        return inputs
    };

    createInputDiv = function (inp_name, placeholder) {
        var div1 = document.createElement("div");
        var div2 = document.createElement("div");
        var inp = document.createElement("input");

        div1.className = "col-sm-2";
        div2.className = "form-group";
        inp.type = "text";
        inp.name = inp_name;
        inp.className = "form-control";
        inp.placeholder = placeholder;
        inp.autocomplete = "off";

        div2.appendChild(inp);
        div1.appendChild(div2);

        return div1;
    }

});


