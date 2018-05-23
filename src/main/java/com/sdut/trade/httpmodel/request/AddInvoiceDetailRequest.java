package com.sdut.trade.httpmodel.request;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 类描述：开票明细详细信息数据请求类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/21
 */
@Data
public class AddInvoiceDetailRequest {

    @JSONField(name = "id")
    private Integer id;

    /**
     * 物资名称
     */
    @JSONField(name = "txt_invoice_goods_name")
    private String goodsName;

    /**
     * 物资类型
     */
    @JSONField(name = "txt_invoice_goods_model")
    private String goodsModel;

    /**
     * 数量
     */
    @JSONField(name = "txt_invoice_number")
    private Double number;

    /**
     * 单价（含税）
     */
    @JSONField(name = "txt_invoice_unit_price")
    private Double unitPrice;

    /**
     * 总值（含税）
     */
    @JSONField(name = "txt_invoice_sum_price")
    private Double sumPrice;

    /**
     * 税额
     */
    @JSONField(name = "txt_invoice_tax")
    private Double tax;

}
