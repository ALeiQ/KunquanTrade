
$(function () {
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

    $('#login').click(function () {

        var form = $('#login_form');

        $.ajax({
            url: "/login.do",
            method: "GET",
            dataType: "json",
            async: false,
            data: {param: JSON.stringify(form.serializeObject())},
            success: function (result) {
                if (result.resultCode === 0) {
                    window.location.href = "/index";
                } else {
                    alert(result.resultMsg)
                }
            },
            error: function () {
                alert("ajax请求异常！");
            }
        });

        return false;
    });

    formValidator = function () {
        $('#login_form').bootstrapValidator({
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
                username: {
                    message: '用户名不合法',
                    validators: {
                        notEmpty: {
                            message: '用户名不可为空'
                        }
                    }
                },
                password: {
                    message: '密码不合法',
                    validators: {
                        notEmpty: {
                            message: '密码不可为空'
                        }
                    }
                }
            }
        });
    };

    formValidator();
});



