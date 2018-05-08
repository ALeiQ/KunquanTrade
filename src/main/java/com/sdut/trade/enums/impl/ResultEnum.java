package com.sdut.trade.enums.impl;

import com.sdut.trade.enums.ResponseEnum;

import lombok.Getter;

/**
 * 任务处理结果
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
public enum ResultEnum implements ResponseEnum {

    SUCCESS(0, "成功"),
    FAILURE(-1, "失败");

    @Getter
    private int value;

    @Getter
    private String desc;

    ResultEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
