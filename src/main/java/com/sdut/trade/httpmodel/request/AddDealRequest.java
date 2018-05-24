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

    @JSONField(name = "txt_deal_date", format = "yyyy-MM-dd")
    private Date date;

    @JSONField(name = "txt_deal_company")
    private String rompany;

    @JSONField(name = "txt_deal_amount")
    private Double amount;

    @JSONField(name = "txt_deal_direction")
    private Integer type;

    @JSONField(name = "txt_deal_company")
    private String company;

    @JSONField(name = "txt_wechat_pay_account")
    private String wechatPayAccount;

    @JSONField(name = "txt_wechat_receive_account")
    private String wechatReceiveAccount;

    @JSONField(name = "txt_bank_pay_account")
    private Long bankPayAccount;

    @JSONField(name = "txt_bank_receive_account")
    private Long bankReceiveAccount;

    @JSONField(name = "txt_bank_name")
    private String bankName;

    @JSONField(name = "txt_check_pay_people")
    private String checkPayPeople;

    @JSONField(name = "txt_check_receive_people")
    private String checkReceivePeople;

    @JSONField(name = "txt_check_number")
    private Long checkNumber;

    @JSONField(name = "txt_check_date", format = "yyyy-MM-dd")
    private Date checkDate;

    @JSONField(name = "txt_check_deadline", format = "yyyy-MM-dd")
    private Date checkDeadline;

}
