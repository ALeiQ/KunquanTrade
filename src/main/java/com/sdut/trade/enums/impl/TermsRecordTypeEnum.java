package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：常用名词增删记录类型枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/10
 */
public enum  TermsRecordTypeEnum {

    GOODS_INFO(1, "货物信息"),
    COMPANY_INFO(2, "公司信息"),
    TRANSPORT_COMPANY_INFO(3, "运输公司信息"),
    BANK_INFO(4, "银行信息");

    @Getter
    private int value;

    @Getter
    private String desc;

    TermsRecordTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
