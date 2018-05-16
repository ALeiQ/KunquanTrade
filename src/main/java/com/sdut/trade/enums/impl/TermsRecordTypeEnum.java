package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：常用名词增删记录类型枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/10
 */
public enum  TermsRecordTypeEnum {

    GOODS_INFO(1, "货物总览"),
    COMPANY_INFO(2, "公司总览"),
    TRANSPORT_COMPANY_INFO(3, "运输公司"),
    BANK_INFO(4, "银行汇总");

    @Getter
    private int value;

    @Getter
    private String desc;

    public static String getDesc(int value) {

        for (TermsRecordTypeEnum termsRecordTypeEnum : values()) {
            if (termsRecordTypeEnum.value == value) {
                return termsRecordTypeEnum.getDesc();
            }
        }

        return "";
    }

    TermsRecordTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
