package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.sdut.trade.bean.DealDetailVO;
import com.sdut.trade.dao.DealDetailDao;
import com.sdut.trade.entity.DealDetail;
import com.sdut.trade.enums.impl.DealTypeEnum;
import com.sdut.trade.enums.impl.DealWayEnum;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.httpmodel.request.AddDealRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.BankInfoService;
import com.sdut.trade.service.CompanyInfoService;
import com.sdut.trade.service.TransactionDetailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：资金往来业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
@Component
@Slf4j
public class TransactionDetailServiceImp implements TransactionDetailService {

    @Autowired
    CompanyInfoService companyInfoService;

    @Autowired
    BankInfoService bankInfoService;

    @Autowired
    DealDetailDao dealDetailDao;

    /**
     * 获取所有资金往来记录
     *
     * @return
     */
    @Override
    public ResponseVO getAll() {

        ResponseVO responseVO = new ResponseVO();

        List<DealDetail> dealDetailList = dealDetailDao.getAll();
        List<DealDetailVO> dealDetailVOList = new ArrayList<>();

        for (DealDetail dealDetail : dealDetailList) {

            DealDetailVO dealDetailVO = new DealDetailVO();

            dealDetailVO.setId(dealDetail.getId());
            dealDetailVO.setDate(dealDetail.getDate());
            dealDetailVO.setAmount(dealDetail.getAmount());
            dealDetailVO.setType(dealDetail.getType());
            dealDetailVO.setTypeDesc(DealTypeEnum.getDesc(dealDetail.getType()));
            dealDetailVO.setWay(dealDetail.getWay());
            dealDetailVO.setWayDesc(DealWayEnum.getDesc(dealDetail.getWay()));
            dealDetailVO.setCompany(dealDetail.getCompany());
            dealDetailVO.setWechatPayAccount(dealDetail.getWechatPayAccount());
            dealDetailVO.setWechatReceiveAccount(dealDetail.getWechatReceiveAccount());
            dealDetailVO.setBankPayAccount(dealDetail.getBankPayAccount());
            dealDetailVO.setBankReceiveAccount(dealDetail.getBankReceiveAccount());
            dealDetailVO.setBankName(dealDetail.getBankName());
            dealDetailVO.setCheckReceivePeople(dealDetail.getCheckReceivePeople());
            dealDetailVO.setCheckPayPeople(dealDetail.getCheckPayPeople());
            dealDetailVO.setCheckNumber(dealDetail.getCheckNumber());
            dealDetailVO.setCheckDate(dealDetail.getCheckDate());
            dealDetailVO.setCheckDeadLine(dealDetail.getCheckDeadline());

            dealDetailVOList.add(dealDetailVO);
        }

        responseVO.setData(dealDetailVOList);
        return responseVO;
    }

    /**
     * 添加资金往来记录
     *
     * @param addDealRequest
     *
     * @return
     */
    @Override
    public ResponseVO addDeal(AddDealRequest addDealRequest) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        DealDetail dealDetail = parseRequestToModel(addDealRequest, createDate);

        int addNum = dealDetailDao.addDetail(dealDetail);

        if (addNum != 1) {
            responseVO.setResult(ExceptionEnum.DB_ADD_FAILURE);
            log.error("addDeal add dealDetail false!", dealDetail.toString());
        }

        companyInfoService.addCompanyTerm(addDealRequest.getCompany(), createDate);
        bankInfoService.addBankTerm(addDealRequest.getBankName(), createDate);

        return responseVO;
    }

    /**
     * 将前端请求类解析为数据对象
     * @param addDealRequest
     * @param createDate
     * @return
     */
    private DealDetail parseRequestToModel(AddDealRequest addDealRequest, Date createDate) {

        DealDetail dealDetail = new DealDetail();

        DealWayEnum dealWayEnum = getDealWay(addDealRequest);

        if (dealWayEnum != null) {
            dealDetail.setWay(dealWayEnum.getValue());
        }

        dealDetail.setDate(addDealRequest.getDate());
        dealDetail.setAmount(addDealRequest.getAmount());
        dealDetail.setType(addDealRequest.getType());
        dealDetail.setCompany(addDealRequest.getCompany());
        dealDetail.setWechatPayAccount(addDealRequest.getWechatPayAccount());
        dealDetail.setWechatReceiveAccount(addDealRequest.getWechatReceiveAccount());
        dealDetail.setBankPayAccount(addDealRequest.getBankPayAccount());
        dealDetail.setBankReceiveAccount(addDealRequest.getBankReceiveAccount());
        dealDetail.setBankName(addDealRequest.getBankName());
        dealDetail.setCheckPayPeople(addDealRequest.getCheckPayPeople());
        dealDetail.setCheckReceivePeople(addDealRequest.getCheckReceivePeople());
        dealDetail.setCheckNumber(addDealRequest.getCheckNumber());
        dealDetail.setCheckDate(addDealRequest.getCheckDate());
        dealDetail.setCheckDeadline(addDealRequest.getCheckDeadline());

        dealDetail.setCreateDate(createDate);
        dealDetail.setEnable(EnableEnum.ENABLE.isValue());

        return dealDetail;
    }

    /**
     * 获取支付方式（微信/银行/承兑汇票）
     * @param addDealRequest
     * @return
     */
    private DealWayEnum getDealWay(AddDealRequest addDealRequest) {

        if (!StringUtils.isEmpty(addDealRequest.getWechatPayAccount())
                || !StringUtils.isEmpty(addDealRequest.getWechatReceiveAccount())) {
            return DealWayEnum.WECHAT;
        } else if (!StringUtils.isEmpty(addDealRequest.getBankName())
                || addDealRequest.getBankPayAccount() != null
                || addDealRequest.getBankReceiveAccount() != null) {
            return DealWayEnum.BANK;
        } else if (!StringUtils.isEmpty(addDealRequest.getCheckPayPeople())
                || !StringUtils.isEmpty(addDealRequest.getCheckReceivePeople())
                || addDealRequest.getCheckNumber() != null
                || addDealRequest.getCheckDate() != null
                || addDealRequest.getBankName() != null) {
            return DealWayEnum.CHECK;
        }
        return null;
    }

}
