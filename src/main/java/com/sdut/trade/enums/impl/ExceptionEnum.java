package com.sdut.trade.enums.impl;

import com.sdut.trade.enums.ResponseEnum;

import lombok.Getter;

/**
 * 类描述：异常状态码及原因
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
public enum ExceptionEnum implements ResponseEnum {

    PARAM_ERR(20010, "字段有误"),
    PARAM_EMPTY(20011, "字段不可为空"),

    JSON_PARSE_FAILURE(30010, "JSON串解析失败"),

    DB_SEARCH_FAILURE(40010, "数据库查询失败"),
    DB_ADD_FAILURE(40011, "数据库增加数据失败"),
    DB_DEL_FAILURE(40012, "数据库删除数据失败");

    @Getter
    private int value;

    @Getter
    private String desc;

    ExceptionEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
