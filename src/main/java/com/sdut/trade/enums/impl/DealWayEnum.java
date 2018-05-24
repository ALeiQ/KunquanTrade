package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：资金往来交易途径枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
public enum DealWayEnum {

    WECHAT(1, "微信"),
    BANK(2, "银行`"),
    CHECK(3, "承兑汇票");

    @Getter
    private int value;

    @Getter
    private String desc;

    public static String getDesc(Integer value) {

        if (value == null) {
            return "";
        }

        for (DealWayEnum dealWayEnum : values()) {
            if (dealWayEnum.value == value) {
                return dealWayEnum.getDesc();
            }
        }

        return "";
    }

    DealWayEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
