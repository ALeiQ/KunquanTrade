
$(function () {

    $('#btn_change_password').click(function () {
        $.ajax({
            url: '/changePassword.do',
            method: 'POST',
            dataType: 'json',
            data: $('#change_password_form').serialize(),
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
        return false;
    });

});