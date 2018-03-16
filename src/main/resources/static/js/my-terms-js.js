/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

$(document).ready(function () {

    $('#goodsTable').bootstrapTable()

    $('#addGoods').click(function () {
        var data = {};
        $('#goodsTable').bootstrapTable('append', data);
    })
    
});