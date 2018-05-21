package com.sdut.trade.httpmodel.request;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 类描述：开票明细增加数据请求类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/21
 */
@Data
public class AddInvoiceRequest {

    /**
     * 开票时间
     */
    @JSONField(name = "txt_make_date", format = "yyyy-MM-dd")
    private Date makeDate;

    /**
     * 抵扣时间
     */
    @JSONField(name = "txt_use_date", format = "yyyy-MM-dd")
    private Date useDate;

    /**
     * 开票单位
     */
    @JSONField(name = "txt_pay_company")
    private String payCompany;

    /**
     * 受票单位
     */
    @JSONField(name = "txt_receive_company")
    private String receiveCompany;

    /**
     * 票号
     */
    @JSONField(name = "txt_number")
    private String number;

    /**
     * 发票类别
     * 1.专票
     * 2.普票
     */
    @JSONField(name = "txt_type")
    private Integer type;

    /**
     * 发票总额
     */
    @JSONField(name = "txt_amount")
    private Double amount;

    /**
     * 备注
     */
    @JSONField(name = "txt_invoice_remark")
    private String remark;

}
