package com.sdut.trade.httpmodel.request;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 类描述：增加资金往来请求类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
@Data
public class AddDealRequest {

    /**
     * 交易时间
     */
    @JSONField(name = "txt_deal_date", format = "yyyy-MM-dd")
    private Date date;

    /**
     * 交易金额
     */
    @JSONField(name = "txt_deal_amount")
    private Double amount;

    /**
     * 交易类型（1收入/2支出）
     */
    @JSONField(name = "txt_deal_direction")
    private Integer type;

    /**
     * 交易公司
     */
    @JSONField(name = "txt_deal_company")
    private String company;

    /**
     * 微信打款账号
     */
    @JSONField(name = "txt_wechat_pay_account")
    private String wechatPayAccount;

    /**
     * 微信收款账号
     */
    @JSONField(name = "txt_wechat_receive_account")
    private String wechatReceiveAccount;

    /**
     * 银行打款账号
     */
    @JSONField(name = "txt_bank_pay_account")
    private Long bankPayAccount;

    /**
     * 银行收款账号
     */
    @JSONField(name = "txt_bank_receive_account")
    private Long bankReceiveAccount;

    /**
     * 银行名称
     */
    @JSONField(name = "txt_bank_name")
    private String bankName;

    /**
     * 出票人
     */
    @JSONField(name = "txt_check_pay_people")
    private String checkPayPeople;

    /**
     * 受票人
     */
    @JSONField(name = "txt_check_receive_people")
    private String checkReceivePeople;

    /**
     * 承兑票号
     */
    @JSONField(name = "txt_check_number")
    private Long checkNumber;

    /**
     * 出票日期
     */
    @JSONField(name = "txt_check_date", format = "yyyy-MM-dd")
    private Date checkDate;

    /**
     * 到期日
     */
    @JSONField(name = "txt_check_deadline", format = "yyyy-MM-dd")
    private Date checkDeadline;

    /**
     * 备注
     */
    @JSONField(name = "txt_deal_remark")
    private String remark;

    /**
     * 绑定的物流Id
     */
    private String bind_logistics_id;

}
