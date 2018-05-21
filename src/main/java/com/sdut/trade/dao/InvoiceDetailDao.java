package com.sdut.trade.dao;

import java.util.List;

import com.sdut.trade.entity.InvoiceDetail;

/**
 * 类描述：开票明细详情表Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
public interface InvoiceDetailDao {

    /**
     * 查询票务信息Id为queryId的发票详情
     * @param queryId
     * @return
     */
    List<InvoiceDetail> getAllByInvoiceId(Integer queryId);

    /**
     * 批量添加开票附加信息
     * @param invoiceDetailList
     * @return
     */
    int addInvoiceDetails(List<InvoiceDetail> invoiceDetailList);
}
