package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;
import org.thymeleaf.util.StringUtils;

import com.sdut.trade.bean.LogisticsDetailVO;
import com.sdut.trade.dao.CompanyInfoDao;
import com.sdut.trade.dao.GoodsInfoDao;
import com.sdut.trade.dao.LogisticsCompanyInfoDao;
import com.sdut.trade.dao.LogisticsDetailDao;
import com.sdut.trade.entity.CompanyInfo;
import com.sdut.trade.entity.GoodsInfo;
import com.sdut.trade.entity.TermsRecord;
import com.sdut.trade.entity.LogisticsCompanyInfo;
import com.sdut.trade.entity.LogisticsDetail;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.request.AddLogisticsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TermsRecordService;
import com.sdut.trade.service.LogisticsDetailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输明细业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
@Slf4j
@Component
public class LogisticsDetailServiceImp implements LogisticsDetailService {

    @Autowired
    TermsRecordService termsRecordService;

    @Autowired
    LogisticsDetailDao logisticsDetailDao;

    @Autowired
    CompanyInfoDao companyInfoDao;

    @Autowired
    LogisticsCompanyInfoDao logisticsCompanyInfoDao;

    @Autowired
    GoodsInfoDao goodsInfoDao;

    /**
     * 添加单条运输明细数据
     *
     * @param addLogisticsRequest 待处理添加的原生数据（前端传来）
     *
     * @return
     */
    @Override
    public ResponseVO addLogisticsDetail(AddLogisticsRequest addLogisticsRequest) {

        ResponseVO responseVO = new ResponseVO();

        log.info("addLogisticsRequest add start!");

        Date createDate = new Date();

        LogisticsDetail logisticsDetail = parseRequestToModel(addLogisticsRequest, createDate);

        int addNum = logisticsDetailDao.addLogisticsDetail(logisticsDetail);

        if (addNum != 1) {
            responseVO.setResult(ExceptionEnum.DB_ADD_FAILURE);
            log.error("addLogisticsRequest add logisticsDetail false!", logisticsDetail.toString());
        }

        // 如果物资来源不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addLogisticsRequest.getGoodsFrom())) {

            if (!companyInfoDao.hasCompanyName(addLogisticsRequest.getGoodsFrom())) {

                CompanyInfo companyInfo = new CompanyInfo();

                companyInfo.setName(addLogisticsRequest.getGoodsFrom());
                companyInfo.setCreateDate(createDate);
                companyInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!companyInfoDao.addCompanyInfo(companyInfo)) {
                    log.warn("addLogisticsRequest add GoodsFrom false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addLogisticsRequest.getGoodsFrom());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.COMPANY_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addLogisticsRequest add GoodsFromRecord false!");
                }
            }
        }

        // 如果结算公司不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addLogisticsRequest.getBuyerCompany())) {

            if (!companyInfoDao.hasCompanyName(addLogisticsRequest.getBuyerCompany())) {

                CompanyInfo companyInfo = new CompanyInfo();

                companyInfo.setName(addLogisticsRequest.getBuyerCompany());
                companyInfo.setCreateDate(createDate);
                companyInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!companyInfoDao.addCompanyInfo(companyInfo)) {
                    log.warn("addLogisticsRequest add BuyerCompany false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addLogisticsRequest.getBuyerCompany());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.COMPANY_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addLogisticsRequest add BuyerCompanyRecord false!");
                }
            }
        }

        // 如果运输公司不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addLogisticsRequest.getTransCompany())) {

            if (!logisticsCompanyInfoDao.hasLogisticsCompanyName(addLogisticsRequest.getTransCompany())) {

                LogisticsCompanyInfo logisticsCompanyInfo = new LogisticsCompanyInfo();

                logisticsCompanyInfo.setName(addLogisticsRequest.getTransCompany());
                logisticsCompanyInfo.setCreateDate(createDate);
                logisticsCompanyInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!logisticsCompanyInfoDao.addLogisticsCompanyInfo(logisticsCompanyInfo)) {
                    log.warn("addLogisticsRequest add LogisticsCompany false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addLogisticsRequest.getTransCompany());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.TRANSPORT_COMPANY_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addLogisticsRequest add LogisticsCompany false!");
                }
            }
        }

        // 如果货物信息不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(addLogisticsRequest.getGoodsName())) {

            if (!goodsInfoDao.hasGoods(addLogisticsRequest.getGoodsName(), addLogisticsRequest.getGoodsModel())) {

                GoodsInfo goodsInfo = new GoodsInfo();

                goodsInfo.setName(addLogisticsRequest.getGoodsName());
                goodsInfo.setModel(addLogisticsRequest.getGoodsModel());
                goodsInfo.setCreateDate(createDate);
                goodsInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!goodsInfoDao.addGoodsInfo(goodsInfo)) {
                    log.warn("addLogisticsRequest add GoodsInfo false!");
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(addLogisticsRequest.getGoodsName());
                addTermsRequest.setModel(addLogisticsRequest.getGoodsModel());

                if (termsRecordService.addRecord(TermsRecordTypeEnum.GOODS_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addLogisticsRequest add GoodsInfo false!");
                }
            }
        }

        log.info("addLogisticsRequest add sucess!");

        return responseVO;
    }

    /**
     * 分页查询运输明细
     *
     * @return
     */
    @Override
    public ResponseVO getAll() {

        ResponseVO responseVO = new ResponseVO();

        List<LogisticsDetail> logisticsDetailList = logisticsDetailDao.getAll();

        if (ListUtils.isEmpty(logisticsDetailList)) {
            responseVO.setResult(ExceptionEnum.DB_SEARCH_FAILURE);
            log.error("getAllInRange " + ExceptionEnum.DB_SEARCH_FAILURE.getDesc());
            return responseVO;
        }

        List<LogisticsDetailVO> logisticsDetailVOS = new ArrayList<>();

        for (LogisticsDetail logisticsDetail : logisticsDetailList) {

            LogisticsDetailVO logisticsDetailVO = parseModelToView(logisticsDetail);

            logisticsDetailVOS.add(logisticsDetailVO);
        }

        responseVO.setData(logisticsDetailVOS);

        return responseVO;
    }

    /**
     * 将请求对象转换为存储对象
     *
     * @return
     */
    private LogisticsDetail parseRequestToModel(AddLogisticsRequest addLogisticsRequest, Date createDate) {

        LogisticsDetail logisticsDetail = new LogisticsDetail();

        logisticsDetail.setGoodsFrom(addLogisticsRequest.getGoodsFrom());
        logisticsDetail.setSellerUnitPrice(addLogisticsRequest.getSellerUnitPrice());
        logisticsDetail.setSellerSumPrice(addLogisticsRequest.getSellerSumPrice());

        logisticsDetail.setBuyerCompany(addLogisticsRequest.getBuyerCompany());
        logisticsDetail.setUnitPrice(addLogisticsRequest.getUnitPrice());
        logisticsDetail.setSumPrice(addLogisticsRequest.getSumPrice());

        logisticsDetail.setTransCompany(addLogisticsRequest.getTransCompany());
        logisticsDetail.setTransUnitPrice(addLogisticsRequest.getTransUnitPrice());
        logisticsDetail.setTransSumPrice(addLogisticsRequest.getTransSumPrice());

        logisticsDetail.setLoadTime(addLogisticsRequest.getLoadTime());
        logisticsDetail.setWeighingNumber(addLogisticsRequest.getWeighingNumber());
        logisticsDetail.setCarNumber(addLogisticsRequest.getCarNumber());

        logisticsDetail.setNetWeight(addLogisticsRequest.getNetWeight());
        logisticsDetail.setReturnWeight(addLogisticsRequest.getReturnWeight());
        logisticsDetail.setLossWeight(addLogisticsRequest.getLossWeight());

        logisticsDetail.setGoodsName(addLogisticsRequest.getGoodsName());
        logisticsDetail.setGoodsModel(addLogisticsRequest.getGoodsModel());
        logisticsDetail.setProfit(addLogisticsRequest.getProfit());

        logisticsDetail.setCreateDate(addLogisticsRequest.getLoadTime());
        logisticsDetail.setRemark(addLogisticsRequest.getRemark());
        logisticsDetail.setCreateDate(createDate);
        logisticsDetail.setEnable(EnableEnum.ENABLE.isValue());

        return logisticsDetail;
    }

    /**
     * 将数据库数据对象拼装成展示对象
     *
     * @return
     */
    private LogisticsDetailVO parseModelToView(LogisticsDetail logisticsDetail) {

        LogisticsDetailVO logisticsDetailVO = new LogisticsDetailVO();

        logisticsDetailVO.setId(logisticsDetail.getId());

        logisticsDetailVO.setGoodsFrom(logisticsDetail.getGoodsFrom());
        logisticsDetailVO.setSellerUnitPrice(logisticsDetail.getSellerUnitPrice());
        logisticsDetailVO.setSellerSumPrice(logisticsDetail.getSellerSumPrice());

        logisticsDetailVO.setBuyerCompany(logisticsDetail.getBuyerCompany());
        logisticsDetailVO.setUnitPrice(logisticsDetail.getUnitPrice());
        logisticsDetailVO.setSumPrice(logisticsDetail.getSumPrice());

        logisticsDetailVO.setTransCompany(logisticsDetail.getTransCompany());
        logisticsDetailVO.setTransUnitPrice(logisticsDetail.getTransUnitPrice());
        logisticsDetailVO.setTransSumPrice(logisticsDetail.getTransSumPrice());

        logisticsDetailVO.setLoadTime(logisticsDetail.getLoadTime());
        logisticsDetailVO.setWeighingNumber(logisticsDetail.getWeighingNumber());
        logisticsDetailVO.setCarNumber(logisticsDetail.getCarNumber());

        logisticsDetailVO.setNetWeight(logisticsDetail.getNetWeight());
        logisticsDetailVO.setReturnWeight(logisticsDetail.getReturnWeight());
        logisticsDetailVO.setLossWeight(logisticsDetail.getLossWeight());

        logisticsDetailVO.setGoodsName(logisticsDetail.getGoodsName());
        logisticsDetailVO.setGoodsModel(logisticsDetail.getGoodsModel());
        logisticsDetailVO.setProfit(logisticsDetail.getProfit());

        logisticsDetailVO.setRemark(logisticsDetail.getRemark());

        return logisticsDetailVO;
    }

}
