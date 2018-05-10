package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sdut.trade.bean.BankInfoVO;
import com.sdut.trade.dao.BankInfoDao;
import com.sdut.trade.dao.TermsRecordDao;
import com.sdut.trade.entity.BankInfo;
import com.sdut.trade.entity.TermsRecord;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.enums.impl.TermsRecordOperateEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.BankInfoService;
import com.sdut.trade.service.TermsRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：银行信息业务层实现
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class BankInfoServiceImp implements BankInfoService {

    @Autowired
    private BankInfoDao bankInfoDao;

    @Autowired
    private TermsRecordService termsRecordService;

    /**
     * 常用名词页获取全部银行信息
     * @return 银行信息数组
     */
    @Override
    public ResponseVO getAllBankInfo() {

        ResponseVO responseVO = new ResponseVO();

        List<BankInfoVO> bankInfoVOS = new ArrayList<>();

        List<BankInfo> bankInfos;

        try {
            bankInfos = bankInfoDao.getAllEnable();
        } catch (Exception ex) {
            // 数据库读取异常
            log.error("getAllBankInfo fromDB failed", ex);
            responseVO.setResult(ResultEnum.FAILURE);
            return responseVO;
        }

        /*
         * 将数据库原始数据包装成前端展示数据
         */
        for (BankInfo bankInfoDO : bankInfos) {

            BankInfoVO bankInfoVO = new BankInfoVO();

            bankInfoVO.setId(bankInfoDO.getId());
            bankInfoVO.setName(bankInfoDO.getName());

            bankInfoVOS.add(bankInfoVO);
        }

        responseVO.setData(bankInfoVOS);

        return responseVO;
    }

    /**
     * 常用名词添加银行信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    @Override
    public ResponseVO addBankInfoBatch(List<AddTermsRequest> addTermsRequests) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        List<BankInfo> bankInfos = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequests) {

            BankInfo bankInfo = new BankInfo();

            bankInfo.setName(addTermsRequest.getName());
            bankInfo.setCreateDate(createDate);
            bankInfo.setEnable(EnableEnum.ENABLE.isValue());

            bankInfos.add(bankInfo);
        }

        // 批量添加货物信息到数据库中的货物信息表
        int addNum = bankInfoDao.addBankInfoBatch(bankInfos);

        if (addNum != bankInfos.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名词添加失败！"
                    + "[需要添加: " + Integer.toString(bankInfos.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addBankInfoBatch add to DB less than need! [need = {}][real = {}]",
                    bankInfos.size(), addNum);
            return responseVO;
        }

        return termsRecordService.addRecords(TermsRecordTypeEnum.BANK_INFO, addTermsRequests, createDate);
    }

    /**
     * 删除指定id的银行信息
     *
     * @param id 需要删除的信息的Id
     *
     * @return 删除结果
     */
    @Override
    public ResponseVO delBankInfoById(int id) {

        ResponseVO responseVO = new ResponseVO();

        Date deleteDate = new Date();

        BankInfo bankInfo = bankInfoDao.getBankInfoById(id);

        int delNum = bankInfoDao.delBankInfoById(id, deleteDate);

        if (delNum != 1) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("数据删除失败");

            log.error("delBankInfoBatch del false!");
        }

        AddTermsRequest delTermsRequest = new AddTermsRequest();
        delTermsRequest.setName(bankInfo.getName());
        responseVO = termsRecordService.delRecord(TermsRecordTypeEnum.BANK_INFO, delTermsRequest, deleteDate);

        return responseVO;
    }

}
