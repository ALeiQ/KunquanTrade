package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sdut.trade.bean.CompanyInfoVO;
import com.sdut.trade.dao.CompanyInfoDao;
import com.sdut.trade.entity.CompanyInfo;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.CompanyInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：公司信息业务层实现
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class CompanyInfoServiceImp implements CompanyInfoService {

    @Autowired
    private CompanyInfoDao companyInfoDao;

    @Autowired
    private TermsRecordServiceImp termsRecordService;

    /**
     * 常用名词页获取全部公司信息
     * @return 公司信息数组
     */
    @Override
    public ResponseVO getAllCompanyInfo() {

        ResponseVO responseVO = new ResponseVO();

        List<CompanyInfoVO> companyInfoVOS = new ArrayList<>();
        List<CompanyInfo> companyInfos;

        try {
            companyInfos = companyInfoDao.getAllEnable();
        } catch (Exception ex) {
            // 数据库读取异常
            log.error("getAllCompanyInfo fromDB failed", ex);
            responseVO.setResult(ResultEnum.FAILURE);
            return responseVO;
        }

        /*
         * 将数据库原始数据包装成前端展示数据
         */
        for (CompanyInfo companyInfoDO : companyInfos) {

            CompanyInfoVO companyInfoVO = new CompanyInfoVO();

            companyInfoVO.setId(companyInfoDO.getId());
            companyInfoVO.setName(companyInfoDO.getName());

            companyInfoVOS.add(companyInfoVO);
        }

        responseVO.setData(companyInfoVOS);

        return responseVO;
    }

    /**
     * 常用名词添加公司信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    @Override
    public ResponseVO addCompanyInfoBatch(List<AddTermsRequest> addTermsRequests) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        List<CompanyInfo> companyInfos = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequests) {

            CompanyInfo companyInfo = new CompanyInfo();

            companyInfo.setName(addTermsRequest.getName());
            companyInfo.setCreateDate(createDate);
            companyInfo.setEnable(EnableEnum.ENABLE.isValue());

            companyInfos.add(companyInfo);

        }

        int addNum = companyInfoDao.addCompanyInfoBatch(companyInfos);

        if (addNum != addTermsRequests.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名次添加失败！"
                    + "[需要添加: " + Integer.toString(addTermsRequests.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addCompanyInfoBatch add to DB less than need! [need = {}][real = {}]",
                    addTermsRequests.size(), addNum);
            return responseVO;
        }

        return termsRecordService.addRecords(TermsRecordTypeEnum.COMPANY_INFO, addTermsRequests, createDate);
    }

    /**
     * 删除指定id的公司信息
     *
     * @param id 需要删除的信息的Id
     *
     * @return 删除结果
     */
    @Override
    public ResponseVO delCompanyInfoById(int id) {

        ResponseVO responseVO = new ResponseVO();

        Date deleteDate = new Date();

        CompanyInfo companyInfo = companyInfoDao.getCompanyInfoById(id);

        int delNum = companyInfoDao.delCompanyInfoById(id, deleteDate);

        if (delNum != 1) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("数据删除失败");

            log.error("delCompanyInfoBatch del false!");
        }

        AddTermsRequest delTermsRequest = new AddTermsRequest();
        delTermsRequest.setName(companyInfo.getName());
        responseVO = termsRecordService.delRecord(TermsRecordTypeEnum.COMPANY_INFO, delTermsRequest, deleteDate);

        return responseVO;

    }

}
