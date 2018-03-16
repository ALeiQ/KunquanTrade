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

// 常用名词 折叠表格 删除按钮
$(document).ready(function () {
    $(".term-table .closed-tool").click(function (event) {
        $(this).parents("tr").fadeToggle(400);

        return false;
    });
});
