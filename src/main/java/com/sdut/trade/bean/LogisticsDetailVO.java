package com.sdut.trade.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 类描述：运输明细展示类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
@Data
public class LogisticsDetailVO {

    /**
     * 编号
     */
    private int id;

    /**
     * 装车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date loadTime;

    /**
     * 物资名称
     */
    @JSONField(name = "txt_goods_name")
    private String goodsName;

    /**
     * 型号
     */
    @JSONField(name = "txt_goods_model")
    private String goodsModel;

    /**
     * 净重
     */
    @JSONField(name = "txt_net_weight")
    private Double netWeight;

    /**
     * 回执数
     */
    @JSONField(name = "txt_return_weight")
    private Double returnWeight;

    /**
     * 亏吨
     */
    @JSONField(name = "txt_loss_weight")
    private Double lossWeight;

    /**
     * 物资来源
     */
    @JSONField(name = "txt_goods_from")
    private String goodsFrom;

    /**
     * 厂家结算单价
     */
    @JSONField(name = "txt_seller_unit_price")
    private Double sellerUnitPrice;

    /**
     * 厂家结算金额
     */
    @JSONField(name = "txt_seller_sum_price")
    private Double sellerSumPrice;

    /**
     * 结算单位
     */
    @JSONField(name = "txt_buyer_company")
    private String buyerCompany;

    /**
     * 结算单价
     */
    @JSONField(name = "txt_unit_price")
    private Double unitPrice;

    /**
     * 结算金额
     */
    @JSONField(name = "txt_sum_price")
    private Double sumPrice;

    /**
     * 运输公司
     */
    @JSONField(name = "txt_trans_company")
    private String transCompany;

    /**
     * 运费单价
     */
    @JSONField(name = "txt_trans_unit_price")
    private Double transUnitPrice;

    /**
     * 运费金额
     */
    @JSONField(name = "txt_trans_sum_price")
    private Double transSumPrice;

    /**
     * 利润
     */
    @JSONField(name = "txt_profit")
    private Double profit;

    /**
     * 检斤号
     */
    @JSONField(name = "txt_weighing_number")
    private String weighingNumber;

    /**
     * 车牌号
     */
    @JSONField(name = "txt_car_number")
    private String carNumber;

    /**
     * 备注
     */
    @JSONField(name = "txt_remark")
    private String remark;

}
