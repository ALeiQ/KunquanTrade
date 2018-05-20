package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.InvoiceDetailDao;
import com.sdut.trade.entity.InvoiceDetail;
import com.sdut.trade.entity.InvoiceDetailExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.InvoiceDetailMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票信息明细表Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Slf4j
@Component
public class InvoiceDetailDaoImp implements InvoiceDetailDao {

    @Autowired
    InvoiceDetailMapper invoiceDetailMapper;

    /**
     * 查询票务信息Id为queryId的发票详情
     *
     * @param queryId
     * @return
     */
    @Override
    public List<InvoiceDetail> getAllByInvoiceId(Integer queryId) {

        InvoiceDetailExample invoiceDetailExample = new InvoiceDetailExample();

        invoiceDetailExample.setOrderByClause("id asc");

        invoiceDetailExample.createCriteria()
                .andInvoiceIdEqualTo(queryId)
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return invoiceDetailMapper.selectByExample(invoiceDetailExample);
    }
}
