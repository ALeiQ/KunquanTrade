package com.sdut.trade.dao;

import java.util.Date;
import java.util.List;

import com.sdut.trade.entity.InvoiceInfo;

/**
 * 类描述：开票明细Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
public interface InvoiceDao {

    /**
     * 获取所有发票信息
     * @return
     */
    List<InvoiceInfo> getAll();

    /**
     * 获取该流向的所有发票信息（1.进项 2.销项 3.中转）
     * @param direction
     * @return
     */
    List<InvoiceInfo> getAllByDirection(Integer direction);

    /**
     * 添加开票简要信息
     * @param invoiceInfo
     * @return id
     */
    int addInvoiceInfo(InvoiceInfo invoiceInfo);

    /**
     * 通过开票详情id删除数据
     * @param delId
     * @param deleteDate
     * @return
     */
    int delInvoiceById(int delId, Date deleteDate);
}
