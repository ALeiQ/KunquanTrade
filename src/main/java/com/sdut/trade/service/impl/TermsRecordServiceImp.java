package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import com.sdut.trade.bean.TermsRecordVO;
import com.sdut.trade.dao.TermsRecordDao;
import com.sdut.trade.entity.TermsRecord;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.enums.impl.TermsRecordOperateEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TermsRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：常用名词增删记录业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/10
 */
@Slf4j
@Component
public class TermsRecordServiceImp implements TermsRecordService {

    @Autowired
    TermsRecordDao termsRecordDao;

    /**
     * 分页查询常用名词增删记录
     *
     * @param page 页码
     * @param rows   行数
     *
     * @return 查询页的记录列表
     */
    @Override
    public ResponseVO getAllInRange(int page, int rows) {

        ResponseVO responseVO = new ResponseVO();

        int offset = (page-1)*rows;
        List<TermsRecord> termsRecords = termsRecordDao.getAllInRange(offset, rows);

        if (ListUtils.isEmpty(termsRecords)) {
            responseVO.setResult(ExceptionEnum.DB_SEARCH_FAILURE);
            log.error("getAllInRange " + ExceptionEnum.DB_SEARCH_FAILURE.getDesc());
            return responseVO;
        }

        List<TermsRecordVO>  termsRecordVOS = new ArrayList<>();

        for (TermsRecord termsRecord : termsRecords) {

            TermsRecordVO termsRecordVO = new TermsRecordVO();

            termsRecordVO.setId(termsRecord.getId());
            termsRecordVO.setCreateDate(termsRecord.getCreateDate());
            termsRecordVO.setTypeValue(termsRecord.getType());
            termsRecordVO.setTypeDesc(TermsRecordTypeEnum.getDesc(termsRecord.getType()));
            termsRecordVO.setOperateValue(BooleanUtils.toInteger(termsRecord.getOperate()));
            termsRecordVO.setOperateDesc(TermsRecordOperateEnum.getDesc(termsRecord.getOperate()));
            termsRecordVO.setName(termsRecord.getName());

            termsRecordVOS.add(termsRecordVO);
        }

        responseVO.setCurrentPage(page);
        responseVO.setTotalPages((termsRecordDao.getCount()+rows-1)/rows);
        responseVO.setData(termsRecordVOS);

        return responseVO;
    }

    /**
     * 批量添加常用名词添加记录
     *
     * @param addType             添加类型
     * @param addTermsRequestList 待添加数据列表
     * @param createDate          添加时间
     *
     * @return
     */
    @Override
    public ResponseVO addRecords(TermsRecordTypeEnum addType, List<AddTermsRequest> addTermsRequestList, Date createDate) {

        ResponseVO responseVO = new ResponseVO();

        List<TermsRecord> termsRecords = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequestList) {

            TermsRecord termsRecord = new TermsRecord();

            termsRecord.setName(addTermsRequest.getName() + addTermsRequest.getModel());
            termsRecord.setCreateDate(createDate);
            termsRecord.setType(addType.getValue());
            termsRecord.setOperate(TermsRecordOperateEnum.ADD.isValue());
            termsRecord.setEnable(EnableEnum.ENABLE.isValue());

            termsRecords.add(termsRecord);
        }

        // 批量添加常用名词增删记录数据到数据库的常用名词增删记录表
        int addTermNum = termsRecordDao.addTermBatch(termsRecords);

        if (addTermNum != termsRecords.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名词记录添加失败！"
                    + "[需要添加: " + Integer.toString(termsRecords.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addTermNum) + " 条]");

            log.error("addBankInfoBatch add to DB less than need! [need = {}][real = {}]",
                    termsRecords.size(), addTermNum);
        }

        return responseVO;
    }

    /**
     * 删除单条常用名词添加记录
     *
     * @param delType         删除类型
     * @param delTermsRequest 待删除数据列表
     * @param delDate         删除时间
     *
     * @return 数据库添加结果
     */
    @Override
    public ResponseVO delRecord(TermsRecordTypeEnum delType, AddTermsRequest delTermsRequest, Date delDate) {

        ResponseVO responseVO = new ResponseVO();

        TermsRecord termsRecord = new TermsRecord();

        termsRecord.setName(delTermsRequest.getName() + delTermsRequest.getModel());
        termsRecord.setCreateDate(delDate);
        termsRecord.setType(delType.getValue());
        termsRecord.setOperate(TermsRecordOperateEnum.DEL.isValue());
        termsRecord.setEnable(EnableEnum.ENABLE.isValue());

        int addNum = termsRecordDao.addTerm(termsRecord);

        if (addNum != 1) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("增加数据删除记录失败");

            log.error("delRecord del false!");
        }

        return responseVO;
    }
}
