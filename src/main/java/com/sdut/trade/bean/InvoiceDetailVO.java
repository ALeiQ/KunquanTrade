package com.sdut.trade.bean;

import lombok.Data;

/**
 * 类描述：开票明细详情展示层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Data
public class InvoiceDetailVO {

    /**
     * 编号
     */
    private int id;

    /**
     * 货物名称
     */
    private String goodsName;

    /**
     * 型号
     */
    private String goodsModel;

    /**
     * 数量
     */
    private Double number;

    /**
     * 单价（含税）
     */
    private Double unitPrice;

    /**
     * 金额（含税）
     */
    private Double sumPrice;

    /**
     * 税额
     */
    private Double tax;
}
