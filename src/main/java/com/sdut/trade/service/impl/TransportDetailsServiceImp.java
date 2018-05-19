package com.sdut.trade.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.sdut.trade.dao.CompanyInfoDao;
import com.sdut.trade.dao.GoodsInfoDao;
import com.sdut.trade.dao.TransportCompanyInfoDao;
import com.sdut.trade.dao.TransportDetailsDao;
import com.sdut.trade.entity.CompanyInfo;
import com.sdut.trade.entity.GoodsInfo;
import com.sdut.trade.entity.TermsRecord;
import com.sdut.trade.entity.TransportCompanyInfo;
import com.sdut.trade.entity.TransportDetails;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.request.AddTransportRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TermsRecordService;
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
    TermsRecordService termsRecordService;

    @Autowired
    TransportDetailsDao transportDetailsDao;

    @Autowired
    CompanyInfoDao companyInfoDao;

    @Autowired
TransportCompanyInfoDao transportCompanyInfoDao;

    @Autowired
    GoodsInfoDao goodsInfoDao;

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

        Date createDate = new Date();

        TransportDetails transportDetails = parseRequestToModel(addTransportRequest, createDate);

        int addNum = transportDetailsDao.addTransportDetail(transportDetails);

        if (addNum != 1) {
            responseVO.setResult(ExceptionEnum.DB_ADD_FAILURE);
            log.error("addTransportRequest add transportDetail false!", transportDetails.toString());
        }

        // 如果物资来源不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addTransportRequest.getGoodsFrom())) {

            if (!companyInfoDao.hasCompanyName(addTransportRequest.getGoodsFrom())) {

                CompanyInfo companyInfo = new CompanyInfo();

                companyInfo.setName(addTransportRequest.getGoodsFrom());
                companyInfo.setCreateDate(createDate);
                companyInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!companyInfoDao.addCompanyInfo(companyInfo)) {
                    log.warn("addTransportRequest add GoodsFrom false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addTransportRequest.getGoodsFrom());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.COMPANY_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addTransportRequest add GoodsFromRecord false!");
                }
            }
        }

        // 如果结算公司不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addTransportRequest.getBuyerCompany())) {

            if (!companyInfoDao.hasCompanyName(addTransportRequest.getBuyerCompany())) {

                CompanyInfo companyInfo = new CompanyInfo();

                companyInfo.setName(addTransportRequest.getBuyerCompany());
                companyInfo.setCreateDate(createDate);
                companyInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!companyInfoDao.addCompanyInfo(companyInfo)) {
                    log.warn("addTransportRequest add BuyerCompany false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addTransportRequest.getBuyerCompany());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.COMPANY_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addTransportRequest add BuyerCompanyRecord false!");
                }
            }
        }

        // 如果运输公司不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addTransportRequest.getTransCompany())) {

            if (!transportCompanyInfoDao.hasTransportCompanyName(addTransportRequest.getTransCompany())) {

                TransportCompanyInfo transportCompanyInfo = new TransportCompanyInfo();

                transportCompanyInfo.setName(addTransportRequest.getTransCompany());
                transportCompanyInfo.setCreateDate(createDate);
                transportCompanyInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!transportCompanyInfoDao.addTransportCompanyInfo(transportCompanyInfo)) {
                    log.warn("addTransportRequest add TransportCompany false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addTransportRequest.getTransCompany());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.TRANSPORT_COMPANY_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addTransportRequest add TransportCompany false!");
                }
            }
        }

        // 如果货物信息不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addTransportRequest.getGoodsName())) {

            if (!goodsInfoDao.hasGoods(addTransportRequest.getGoodsName(), addTransportRequest.getGoodsModel())) {

                GoodsInfo goodsInfo = new GoodsInfo();

                goodsInfo.setName(addTransportRequest.getGoodsName());
                goodsInfo.setModel(addTransportRequest.getGoodsModel());
                goodsInfo.setCreateDate(createDate);
                goodsInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!goodsInfoDao.addGoodsInfo(goodsInfo)) {
                    log.warn("addTransportRequest add GoodsInfo false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addTransportRequest.getGoodsName());
                addTermsRequest.setModel(addTransportRequest.getGoodsModel());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.GOODS_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addTransportRequest add GoodsInfo false!");
                }
            }
        }

        log.info("addTransportRequest add sucess!");

        return responseVO;
    }

    /**
     * 将请求对象转换为存储对象
     *
     * @return
     */
    private TransportDetails parseRequestToModel(AddTransportRequest addTransportRequest, Date createDate) {

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
        transportDetails.setCreateDate(createDate);
        transportDetails.setEnable(EnableEnum.ENABLE.isValue());

        return transportDetails;
    }

}
