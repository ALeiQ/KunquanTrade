/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

// 个人头像下拉菜单
$(function () {
    var dropDown = $('.profile_details .dropdown-menu').find('li');

    $(dropDown[0]).click(function () {
        window.location.href = "/changePassword";
    });

    $(dropDown[1]).click(function () {
        $.ajax({
            url: "/logout.do",
            method: "GET",
            dataType: "json",
            async: false,
            success: function (result) {
                if (result.resultCode === 0) {
                    window.location.href = "/login";
                } else {
                    alert(result.resultMsg)
                }
            },
            error: function () {
                alert("ajax请求异常！");
            }
        });
    })

});

// 共用函数
$(document).ready(function () {


    // 删除行（隐藏）
    delRow = function (term) {
        $(term).parents("tr").fadeToggle(400);

        return false;
    };

    // form转换json
    $.fn.serializeObject = function()
    {
       var o = {};
       var a = this.serializeArray();
       $.each(a, function() {
           if (typeof(o[this.name]) !== "undefined") {
               if (!o[this.name].push) {
                   o[this.name] = [o[this.name]];
               }
               o[this.name].push(this.value || "");
           } else {
               o[this.name] = this.value || "";
           }
       });
       return o;
    };

    // 3s提示框
    showPopover = function (target, msg) {
        target.attr("data-original-title", msg);
        $('[data-toggle="tooltip"]').tooltip();
        target.tooltip('show');
        target.focus();

        //2秒后消失提示框
        var id = setTimeout(
            function () {
                target.attr("data-original-title", "");
                target.tooltip('hide');
            }, 3000
        );
    };

    setDateYYMMDD = function (form, inp) {
        $(inp).datetimepicker({
            format: 'yyyy-mm-dd',//显示格式
            todayHighlight: 1,//今天高亮
            minView: "month",//设置只显示到月份
            startView:2,
            forceParse: 0,
            showMeridian: 1,
            autoclose: 1//选择后自动关闭
        }).on('hide', function (e) {
            $(inp).blur();
            // 关闭后手动触发校验（否则不自动校验）
            form.data('bootstrapValidator')
                .updateStatus($(inp), 'NOT_VALIDATED',null)
                .validateField($(inp));
        });
    };

    // 开启inp的校验
    startValidator = function(form, inp) {
        form.data('bootstrapValidator')
            .updateStatus($(inp), 'NOT_VALIDATED',null)
            .validateField($(inp));
    };

    // 自动补全配置
    $.fn.makeTypeahead = function (form, url, params, goodsName) {
        var inp = this;
        this.typeahead({
            items: 'all',
            minLength: 0,
            selectOnBlur: false,    //默认为true，自动选取高亮(select)元素
            showHintOnFocus: true,
            source: function (query, process) {
                if (typeof(goodsName) !== "undefined") {
                    params['goodsName'] = $(goodsName).val();
                }
                params['query'] = query;
                $.get(url, params,
                    function (result) {
                        if(result.resultCode === 0) {
                            return process(result.data);
                        }
                    });
            },
            afterSelect: function (){
                startValidator(form, inp);
            }
        });
    };

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

    // 检查Input是否全空
    checkInputsEmpty = function(inputs) {

        for (var i  = 0; i < inputs.length; ++i) {
            if (typeof($(inputs[i]).val()) !== "undefined"
                && $(inputs[i]).val() !== "") {
                return false;
            }
        }

        return true;
    };

});

