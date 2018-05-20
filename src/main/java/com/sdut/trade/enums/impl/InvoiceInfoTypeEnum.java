package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：开票明细类型枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
public enum InvoiceInfoTypeEnum {

    SPECIAL(1, "专票"),
    COMMON(2, "普票");

    @Getter
    int value;

    @Getter
    String desc;

    public static String getDesc(Integer value) {

        if (value == null) {
            return "";
        }

        for (InvoiceInfoTypeEnum invoiceInfoTypeEnum : values()) {
            if (invoiceInfoTypeEnum.value == value) {
                return invoiceInfoTypeEnum.getDesc();
            }
        }

        return "";
    }

    InvoiceInfoTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
