package com.sdut.trade.enums.impl;

import lombok.Getter;

/**
 * 类描述：常用名词增删记录操作（增/删）枚举类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/10
 */
public enum TermsRecordOperateEnum {

    ADD(true, "增加"),
    DEL(false, "删除");

    @Getter
    private boolean value;

    @Getter
    private String desc;

    public static String getDesc(boolean value) {

        for (TermsRecordOperateEnum termsRecordOperateEnum : values()) {
            if (termsRecordOperateEnum.value == value) {
                return termsRecordOperateEnum.getDesc();
            }
        }

        return "";
    }

    TermsRecordOperateEnum(boolean value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
