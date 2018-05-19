package com.sdut.trade.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sdut.trade.dao.TransportDetailsDao;
import com.sdut.trade.entity.TransportDetails;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.httpmodel.request.AddTransportRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TransportDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输明细业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
@Slf4j
@Component
public class TransportDetailsServiceImp implements TransportDetailsService {

    @Autowired
    TransportDetailsDao transportDetailsDao;

    /**
     * 添加单条运输明细数据
     *
     * @param addTransportRequest 待处理添加的原生数据（前端传来）
     *
     * @return
     */
    @Override
    public ResponseVO addTransportDetail(AddTransportRequest addTransportRequest) {

        ResponseVO responseVO = new ResponseVO();

        log.info("addTransportRequest add start!");

        TransportDetails transportDetails = parseRequestToModel(addTransportRequest);

        int addNum = transportDetailsDao.addTransportDetail(transportDetails);

        if (addNum != 1) {
            responseVO.setResult(ExceptionEnum.DB_ADD_FAILURE);
            log.error("addTransportRequest add false!", transportDetails.toString());
        }

        log.info("addTransportRequest add sucess!");

        return responseVO;
    }

    /**
     * 将请求对象转换为存储对象
     * @return
     */
    private TransportDetails parseRequestToModel(AddTransportRequest addTransportRequest) {

        TransportDetails transportDetails = new TransportDetails();

        transportDetails.setGoodsFrom(addTransportRequest.getGoodsFrom());
        transportDetails.setSellerUnitPrice(addTransportRequest.getSellerUnitPrice());
        transportDetails.setSellerSumPrice(addTransportRequest.getSellerSumPrice());

        transportDetails.setBuyerCompany(addTransportRequest.getBuyerCompany());
        transportDetails.setUnitPrice(addTransportRequest.getUnitPrice());
        transportDetails.setSumPrice(addTransportRequest.getSumPrice());

        transportDetails.setTransCompany(addTransportRequest.getTransCompany());
        transportDetails.setTransUnitPrice(addTransportRequest.getTransUnitPrice());
        transportDetails.setTransSumPrice(addTransportRequest.getTransSumPrice());

        transportDetails.setLoadTime(addTransportRequest.getLoadTime());
        transportDetails.setWeighingNumber(addTransportRequest.getWeighingNumber());
        transportDetails.setCarNumber(addTransportRequest.getCarNumber());

        transportDetails.setNetWeight(addTransportRequest.getNetWeight());
        transportDetails.setReturnWeight(addTransportRequest.getReturnWeight());
        transportDetails.setLossWeight(addTransportRequest.getLossWeight());

        transportDetails.setGoodsName(addTransportRequest.getGoodsName());
        transportDetails.setGoodsModel(addTransportRequest.getGoodsModel());
        transportDetails.setProfit(addTransportRequest.getProfit());

        transportDetails.setCreateDate(addTransportRequest.getLoadTime());
        transportDetails.setRemark(addTransportRequest.getRemark());
        transportDetails.setCreateDate(new Date());
        transportDetails.setEnable(EnableEnum.ENABLE.isValue());

        return transportDetails;
    }

}
