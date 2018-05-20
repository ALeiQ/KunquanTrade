package com.sdut.trade.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.InvoiceDao;
import com.sdut.trade.entity.InvoiceInfo;
import com.sdut.trade.entity.InvoiceInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.InvoiceInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票明细Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Component
@Slf4j
public class InvoiceDaoImp implements InvoiceDao {

    @Autowired
    InvoiceInfoMapper invoiceInfoMapper;

    /**
     * 获取所有发票信息
     *
     * @return
     */
    @Override
    public List<InvoiceInfo> getAll() {

        InvoiceInfoExample invoiceInfoExample = new InvoiceInfoExample();

        invoiceInfoExample.setOrderByClause("id desc");

        invoiceInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return invoiceInfoMapper.selectByExample(invoiceInfoExample);
    }

    /**
     * 获取该流向的所有发票信息（1.进项 2.销项 3.中转）
     *
     * @param direction
     *
     * @return
     */
    @Override
    public List<InvoiceInfo> getAllByDirection(Integer direction) {

        InvoiceInfoExample invoiceInfoExample = new InvoiceInfoExample();

        invoiceInfoExample.setOrderByClause("id desc");

        invoiceInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andDirectionEqualTo(direction);

        return invoiceInfoMapper.selectByExample(invoiceInfoExample);
    }
}
