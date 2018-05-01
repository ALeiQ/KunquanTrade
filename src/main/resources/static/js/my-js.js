/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

// 右侧导航栏效果
$(document).ready(function () {
    var menuRight = document.getElementById('cbp-spmenu-s2'),
        showRightPush = document.getElementById('showRightPush'),
        body = document.body;

    showRightPush.onclick = function () {
        classie.toggle(this, 'active');
        classie.toggle(body, 'cbp-spmenu-push-toleft');
        classie.toggle(menuRight, 'cbp-spmenu-open');
        disableOther('showRightPush');
    };


    function disableOther(button) {
        if (button !== 'showRightPush') {
            classie.toggle(showRightPush, 'disabled');
        }
    }
});


// 共用函数
$(document).ready(function () {

    // 删除行（隐藏）
    delRow = function (term) {
        $(term).parents("tr").fadeToggle(400);

        return false;
    };

});
