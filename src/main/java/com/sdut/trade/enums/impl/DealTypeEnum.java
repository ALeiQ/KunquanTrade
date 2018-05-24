package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：资金往来类型枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
public enum DealTypeEnum {

    INPUT(1, "收入"),
    OUTPUT(2, "支出");

    @Getter
    private int value;

    @Getter
    private String desc;

    public static String getDesc(Integer value) {

        if (value == null) {
            return "";
        }

        for (DealTypeEnum dealTypeEnum : values()) {
            if (dealTypeEnum.value == value) {
                return dealTypeEnum.getDesc();
            }
        }

        return "";
    }

    DealTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
