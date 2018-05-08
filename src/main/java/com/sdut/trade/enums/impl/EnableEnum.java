package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：数据库逻辑删除标志
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/8
 */
public enum  EnableEnum {

    ENABLE(true, "可用（未删除）"),
    DISABLE(false, "已删除");

    @Getter
    private boolean value;

    @Getter
    private String desc;

    EnableEnum(boolean value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
