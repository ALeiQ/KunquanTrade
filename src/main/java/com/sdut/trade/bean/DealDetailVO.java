package com.sdut.trade.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 类描述：资金往来记录展示对象
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
@Data
public class DealDetailVO {

    private int id;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 交易类型
     * 1.收入
     * 2.支出
     */
    private Integer type;

    /**
     * 交易类型描述
     */
    private String typeDesc;

    /**
     * 交易途径
     * 1.微信
     * 2.银行
     * 3.承兑支票
     */
    private Integer way;

    /**
     * 交易途径描述
     */
    private String wayDesc;

    /**
     * 交易金额
     */
    private Double amount;

    /**
     * 交易公司
     */
    private String company;

    /**
     * 微信打款账号
     */
    private String wechatPayAccount;

    /**
     * 微信收款账号
     */
    private String wechatReceiveAccount;

    /**
     * 银行打款账号
     */
    private Long bankPayAccount;

    /**
     * 银行收款账号
     */
    private Long bankReceiveAccount;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 出票方
     */
    private String checkPayPeople;

    /**
     * 受票方
     */
    private String checkReceivePeople;

    /**
     * 承兑票号
     */
    private Long checkNumber;

    /**
     * 出票日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkDate;

    /**
     * 到期日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkDeadLine;

    /**
     * 备注
     */
    private String remark;

}
