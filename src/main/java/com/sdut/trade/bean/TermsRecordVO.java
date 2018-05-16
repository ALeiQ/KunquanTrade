package com.sdut.trade.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 类描述：常用名词增删记录展示对象
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/16
 */
@Data
public class TermsRecordVO {

    private int id;

    /**
     * 增/删时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 增删类型
     * 货物
     * 公司
     * 运输公司
     * 银行
     */
    private String typeDesc;

    /**
     * 增删类型标识
     * 1.货物
     * 2.公司
     * 3.运输公司
     * 4.银行
     */
    private int typeValue;

    /**
     * 增还是删
     * 增加/删除
     */
    private String operateDesc;

    /**
     * 增删标识
     * 1.增加
     * 0.删除
     */
    private int operateValue;

    /**
     * 增删的名词
     */
    private String name;

}
