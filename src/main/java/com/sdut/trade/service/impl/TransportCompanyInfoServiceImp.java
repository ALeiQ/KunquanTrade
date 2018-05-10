package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sdut.trade.bean.TransportCompanyInfoVO;
import com.sdut.trade.dao.TransportCompanyInfoDao;
import com.sdut.trade.entity.TransportCompanyInfo;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TransportCompanyInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输公司信息业务层实现
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class TransportCompanyInfoServiceImp implements TransportCompanyInfoService {

    @Autowired
    private TransportCompanyInfoDao transportCompanyInfoDao;

    @Autowired
    private TermsRecordServiceImp termsRecordService;

    /**
     * 常用名词页获取全部运输公司信息
     * @return 运输公司信息数组
     */
    @Override
    public ResponseVO getAllTransportCompanyInfo() {

        ResponseVO responseVO = new ResponseVO();

        List<TransportCompanyInfoVO> transportCompanyInfoVOS = new ArrayList<>();
        List<TransportCompanyInfo> transportCompanyInfos;

        try {
            transportCompanyInfos = transportCompanyInfoDao.getAllEnable();
        } catch (Exception ex) {
            // 数据库读取异常
            log.error("getAllTransportCompanyInfo fromDB failed", ex);
            responseVO.setResult(ResultEnum.FAILURE);
            return responseVO;
        }

        /*
         * 将数据库原始数据包装成前端展示数据
         */
        for (TransportCompanyInfo transportCompanyInfoDO : transportCompanyInfos) {

            TransportCompanyInfoVO transportCompanyInfoVO = new TransportCompanyInfoVO();

            transportCompanyInfoVO.setId(transportCompanyInfoDO.getId());
            transportCompanyInfoVO.setName(transportCompanyInfoDO.getName());

            transportCompanyInfoVOS.add(transportCompanyInfoVO);
        }

        responseVO.setData(transportCompanyInfoVOS);

        return responseVO;
    }

    /**
     * 常用名词添加运输公司信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    @Override
    public ResponseVO addTransportCompanyInfoBatch(List<AddTermsRequest> addTermsRequests) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        List<TransportCompanyInfo> transportCompanyInfos = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequests) {

            TransportCompanyInfo transportCompanyInfo = new TransportCompanyInfo();

            transportCompanyInfo.setName(addTermsRequest.getName());
            transportCompanyInfo.setCreateDate(createDate);
            transportCompanyInfo.setEnable(EnableEnum.ENABLE.isValue());

            transportCompanyInfos.add(transportCompanyInfo);

        }

        int addNum = transportCompanyInfoDao.addTransportCompanyInfoBatch(transportCompanyInfos);

        if (addNum != addTermsRequests.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名次添加失败！"
                    + "[需要添加: " + Integer.toString(addTermsRequests.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addTransportCompanyInfoBatch add to DB less than need! [need = {}][real = {}]",
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
    public ResponseVO delTransportCompanyInfoById(int id) {

        ResponseVO responseVO = new ResponseVO();

        Date deleteDate = new Date();

        TransportCompanyInfo transportCompanyInfo = transportCompanyInfoDao.getTransportCompanyById(id);

        int delNum = transportCompanyInfoDao.delTransportCompanyInfoById(id, deleteDate);

        if (delNum != 1) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("数据删除失败");

            log.error("delTransportCompanyInfoBatch del false!");
        }

        AddTermsRequest delTermsRequest = new AddTermsRequest();
        delTermsRequest.setName(transportCompanyInfo.getName());
        responseVO = termsRecordService.delRecord(TermsRecordTypeEnum.TRANSPORT_COMPANY_INFO, delTermsRequest, deleteDate);

        return responseVO;

    }

}
