package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：开票明细流向枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
public enum InvoiceInfoDircetionEnum {

    INPUT(1, "进项"),
    OUTPUT(2, "销项"),
    TRANSFER(3, "中转");

    @Getter
    int value;

    @Getter
    String desc;

    public static String getDesc(Integer value) {

        if (value == null) {
            return "";
        }

        for (InvoiceInfoDircetionEnum invoiceInfoDircetionEnum : values()) {
            if (invoiceInfoDircetionEnum.value == value) {
                return invoiceInfoDircetionEnum.getDesc();
            }
        }

        return "";
    }

    InvoiceInfoDircetionEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
