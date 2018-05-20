package com.sdut.trade.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 类描述：开票明细展示层
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Data
public class InvoiceInfoVO {

    /**
     * 编号
     */
    private int id;

    /**
     * 开票时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date makeDate;

    /**
     * 发票总额
     */
    private Double amount;

    /**
     * 开票单位
     */
    private String payCompany;

    /**
     * 受票单位
     */
    private String receiveCompany;

    /**
     * 票号
     */
    private String number;

    /**
     * 抵扣时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date useDate;

    /**
     * 发票流向 1.进项 2.销项 3.中转
     */
    private Integer direction;

    private String directionDesc;

    /**
     * 发票类别 1.专票 2.普票
     */
    private Integer type;

    private String typeDesc;

    /**
     * 备注
     */
    private String remark;
}
