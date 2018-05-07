package com.sdut.trade.enums;

import lombok.Getter;

/**
 * 任务处理结果
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
public enum ResultEnum {

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
