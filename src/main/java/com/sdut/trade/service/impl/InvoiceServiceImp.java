package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import com.sdut.trade.bean.InvoiceDetailVO;
import com.sdut.trade.bean.InvoiceInfoVO;
import com.sdut.trade.dao.InvoiceDao;
import com.sdut.trade.dao.InvoiceDetailDao;
import com.sdut.trade.entity.InvoiceDetail;
import com.sdut.trade.entity.InvoiceInfo;
import com.sdut.trade.enums.impl.InvoiceInfoDircetionEnum;
import com.sdut.trade.enums.impl.InvoiceInfoTypeEnum;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票明细业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Component
@Slf4j
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    InvoiceDetailDao invoiceDetailDao;

    /**
     * 根据开票流向获取全数据
     *
     * @param direction 0.全部 1.进项 2.销项 3.中转
     *
     * @return
     */
    @Override
    public ResponseVO getAllByDirection(Integer direction) {

        ResponseVO responseVO = new ResponseVO();

        List<InvoiceInfoVO> invoiceInfoVOS = new ArrayList<>();

        List<InvoiceInfo> invoiceInfoList;

        if (direction == 0) {
            invoiceInfoList = invoiceDao.getAll();
        } else {
            invoiceInfoList = invoiceDao.getAllByDirection(direction);
        }

        for (InvoiceInfo invoiceInfo : invoiceInfoList) {

            InvoiceInfoVO invoiceInfoVO = new InvoiceInfoVO();

            invoiceInfoVO.setId(invoiceInfo.getId());
            invoiceInfoVO.setNumber(invoiceInfo.getNumber());
            invoiceInfoVO.setAmount(invoiceInfo.getAmount());
            invoiceInfoVO.setMakeDate(invoiceInfo.getMakeDate());
            invoiceInfoVO.setUseDate(invoiceInfo.getUseDate());
            invoiceInfoVO.setPayCompany(invoiceInfo.getPayCompany());
            invoiceInfoVO.setReceiveCompany(invoiceInfo.getReceiveCompany());
            invoiceInfoVO.setRemark(invoiceInfo.getRemark());
            invoiceInfoVO.setDirection(invoiceInfo.getDirection());
            invoiceInfoVO.setType(invoiceInfo.getType());
            invoiceInfoVO.setDirectionDesc(InvoiceInfoDircetionEnum.getDesc(invoiceInfo.getDirection()));
            invoiceInfoVO.setTypeDesc(InvoiceInfoTypeEnum.getDesc(invoiceInfo.getType()));

            invoiceInfoVOS.add(invoiceInfoVO);
        }

        responseVO.setData(invoiceInfoVOS);
        return responseVO;
    }

    /**
     * 根据票据id获取票据详细内容
     *
     * @param queryId
     *
     * @return
     */
    @Override
    public ResponseVO getInvoiceDetailsById(Integer queryId) {

        ResponseVO responseVO = new ResponseVO();

        List<InvoiceDetailVO> invoiceDetailVOS = new ArrayList<>();
        List<InvoiceDetail> invoiceDetailList = invoiceDetailDao.getAllByInvoiceId(queryId);

        for (InvoiceDetail invoiceDetail : invoiceDetailList) {

            InvoiceDetailVO invoiceDetailVO = new InvoiceDetailVO();

            invoiceDetailVO.setId(invoiceDetail.getId());
            invoiceDetailVO.setGoodsName(invoiceDetail.getGoodsName());
            invoiceDetailVO.setGoodsModel(invoiceDetail.getGoodsModel());
            invoiceDetailVO.setNumber(invoiceDetail.getNumber());
            invoiceDetailVO.setUnitPrice(invoiceDetail.getUnitPrice());
            invoiceDetailVO.setSumPrice(invoiceDetail.getSumPricet());
            invoiceDetailVO.setTax(invoiceDetail.getTax());

            invoiceDetailVOS.add(invoiceDetailVO);
        }

        responseVO.setData(invoiceDetailVOS);
        return responseVO;
    }
}
