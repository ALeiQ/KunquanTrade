
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
            url: "/login/login.do",
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
    })
});



