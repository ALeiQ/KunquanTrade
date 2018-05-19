package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sdut.trade.bean.LogisticsCompanyInfoVO;
import com.sdut.trade.dao.LogisticsCompanyInfoDao;
import com.sdut.trade.entity.LogisticsCompanyInfo;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.LogisticsCompanyInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输公司信息业务层实现
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class LogisticsCompanyInfoServiceImp implements LogisticsCompanyInfoService {

    @Autowired
    private LogisticsCompanyInfoDao logisticsCompanyInfoDao;

    @Autowired
    private TermsRecordServiceImp termsRecordService;

    /**
     * 常用名词页获取全部运输公司信息
     * @return 运输公司信息数组
     */
    @Override
    public ResponseVO getAllLogisticsCompanyInfo() {

        ResponseVO responseVO = new ResponseVO();

        List<LogisticsCompanyInfoVO> logisticsCompanyInfoVOS = new ArrayList<>();
        List<LogisticsCompanyInfo> logisticsCompanyInfos;

        try {
            logisticsCompanyInfos = logisticsCompanyInfoDao.getAllEnable();
        } catch (Exception ex) {
            // 数据库读取异常
            log.error("getAllLogisticsCompanyInfo fromDB failed", ex);
            responseVO.setResult(ResultEnum.FAILURE);
            return responseVO;
        }

        /*
         * 将数据库原始数据包装成前端展示数据
         */
        for (LogisticsCompanyInfo logisticsCompanyInfoDO : logisticsCompanyInfos) {

            LogisticsCompanyInfoVO logisticsCompanyInfoVO = new LogisticsCompanyInfoVO();

            logisticsCompanyInfoVO.setId(logisticsCompanyInfoDO.getId());
            logisticsCompanyInfoVO.setName(logisticsCompanyInfoDO.getName());

            logisticsCompanyInfoVOS.add(logisticsCompanyInfoVO);
        }

        responseVO.setData(logisticsCompanyInfoVOS);

        return responseVO;
    }

    /**
     * 常用名词添加运输公司信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    @Override
    public ResponseVO addLogisticsCompanyInfoBatch(List<AddTermsRequest> addTermsRequests) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        List<LogisticsCompanyInfo> logisticsCompanyInfos = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequests) {

            LogisticsCompanyInfo logisticsCompanyInfo = new LogisticsCompanyInfo();

            logisticsCompanyInfo.setName(addTermsRequest.getName());
            logisticsCompanyInfo.setCreateDate(createDate);
            logisticsCompanyInfo.setEnable(EnableEnum.ENABLE.isValue());

            logisticsCompanyInfos.add(logisticsCompanyInfo);

        }

        int addNum = logisticsCompanyInfoDao.addLogisticsCompanyInfoBatch(logisticsCompanyInfos);

        if (addNum != addTermsRequests.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名次添加失败！"
                    + "[需要添加: " + Integer.toString(addTermsRequests.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addLogisticsCompanyInfoBatch add to DB less than need! [need = {}][real = {}]",
                    addTermsRequests.size(), addNum);

            return responseVO;
        }

        return termsRecordService.addRecords(TermsRecordTypeEnum.TRANSPORT_COMPANY_INFO, addTermsRequests, createDate);
    }

    /**
     * 删除指定id的运输公司信息
     *
     * @param id 需要删除的信息的Id
     *
     * @return 删除结果
     */
    @Override
    public ResponseVO delLogisticsCompanyInfoById(int id) {

        ResponseVO responseVO = new ResponseVO();

        Date deleteDate = new Date();

        LogisticsCompanyInfo logisticsCompanyInfo = logisticsCompanyInfoDao.getLogisticsCompanyById(id);

        int delNum = logisticsCompanyInfoDao.delLogisticsCompanyInfoById(id, deleteDate);

        if (delNum != 1) {
            responseVO.setResult(ExceptionEnum.DB_DEL_FAILURE);
            log.error("delLogisticsCompanyInfoBatch del false!");
        }

        AddTermsRequest delTermsRequest = new AddTermsRequest();
        delTermsRequest.setName(logisticsCompanyInfo.getName());
        responseVO = termsRecordService.delRecord(TermsRecordTypeEnum.TRANSPORT_COMPANY_INFO, delTermsRequest, deleteDate);

        return responseVO;

    }

    /**
     * 查询与关键字匹配的运输公司名
     *
     * @param query 匹配关键词
     *
     * @return
     */
    @Override
    public ResponseVO getLogisticsCompanyByKeyword(String query) {

        ResponseVO responseVO = new ResponseVO();

        List<LogisticsCompanyInfo> logisticsCompanyInfos = logisticsCompanyInfoDao.getLogisticsCompanyByKeyword(query);
        List<String> logisticsCompanyNames = new ArrayList<>();

        for (LogisticsCompanyInfo logisticsCompanyInfo : logisticsCompanyInfos) {
            logisticsCompanyNames.add(logisticsCompanyInfo.getName());
        }

        responseVO.setData(logisticsCompanyNames);
        return responseVO;

    }

}
