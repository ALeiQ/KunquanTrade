package com.sdut.trade.httpmodel.request;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 类描述：运输明细增加数据请求类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/16
 */
@Data
public class AddTransportRequest {

    /**
     * 装车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JSONField(name = "load_time")
    private Date loadTime;

    /**
     * 物资名称
     */
    @JSONField(name = "goods_name")
    private String goodsName;

    /**
     * 型号
     */
    @JSONField(name = "goods_model")
    private String goodsModel;

    /**
     * 净重
     */
    @JSONField(name = "net_weight")
    private Double netWeight;

    /**
     * 回执数
     */
    @JSONField(name = "return_weight")
    private Double returnWeight;

    /**
     * 亏吨
     */
    @JSONField(name = "loss_weight")
    private Double lossWeight;

    /**
     * 物资来源
     */
    @JSONField(name = "goods_from")
    private String goodsFrom;

    /**
     * 厂家结算单价
     */
    @JSONField(name = "seller_unit_price")
    private Double sellerUnitPrice;

    /**
     * 厂家结算金额
     */
    @JSONField(name = "seller_sum_price")
    private Double sellerSumPrice;

    /**
     * 结算单位
     */
    @JSONField(name = "buyer_company")
    private String buyerCompany;

    /**
     * 结算单价
     */
    @JSONField(name = "unit_price")
    private Double unitPrice;

    /**
     * 结算金额
     */
    @JSONField(name = "sum_price")
    private Double sumPrice;

    /**
     * 运输公司
     */
    @JSONField(name = "trans_company")
    private String transCompany;

    /**
     * 运费单价
     */
    @JSONField(name = "trans_unit_price")
    private Double transUnitPrice;

    /**
     * 运费金额
     */
    @JSONField(name = "trans_sum_price")
    private Double transSumPrice;

    /**
     * 利润
     */
    @JSONField(name = "profit")
    private Double profit;

    /**
     * 检斤号
     */
    @JSONField(name = "weighing_number")
    private String weighingNumber;

    /**
     * 车牌号
     */
    @JSONField(name = "car_number")
    private String carNumber;

    /**
     * 备注
     */
    @JSONField(name = "remark")
    private String remark;

}
