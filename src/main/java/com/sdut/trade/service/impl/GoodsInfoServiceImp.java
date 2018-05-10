package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sdut.trade.bean.GoodsInfoVO;
import com.sdut.trade.dao.GoodsInfoDao;
import com.sdut.trade.entity.GoodsInfo;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.GoodsInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：货物信息业务层实现
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
@Component
@Slf4j
public class GoodsInfoServiceImp implements GoodsInfoService {

    @Autowired
    private GoodsInfoDao goodsInfoDao;

    @Autowired
    private TermsRecordServiceImp termsRecordService;

    /**
     * 常用名词页获取全部货物信息
     * @return 货物信息数组
     */
    @Override
    public ResponseVO getAllGoodsInfo() {

        ResponseVO responseVO = new ResponseVO();

        List<GoodsInfoVO> goodsInfoVOS = new ArrayList<>();
        List<GoodsInfo> goodsInfos;

        try {
            goodsInfos = goodsInfoDao.getAllEnable();
        } catch (Exception ex) {
            // 数据库读取异常
            log.error("getAllGoodsInfo fromDB failed", ex);
            responseVO.setResult(ResultEnum.FAILURE);
            return responseVO;
        }


        /*
         * 将数据库原始数据包装成前端展示数据
         */
        for (GoodsInfo goodsInfoDO : goodsInfos) {

            GoodsInfoVO goodsInfoVO = new GoodsInfoVO();

            goodsInfoVO.setId(goodsInfoDO.getId());
            goodsInfoVO.setName(goodsInfoDO.getName());
            goodsInfoVO.setModel(goodsInfoDO.getModel());

            goodsInfoVOS.add(goodsInfoVO);
        }

        responseVO.setData(goodsInfoVOS);

        return responseVO;
    }

    /**
     * 常用名词添加货物信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    @Override
    public ResponseVO addGoodsInfoBatch(List<AddTermsRequest> addTermsRequests) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        List<GoodsInfo> goodsInfos = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequests) {

            GoodsInfo goodsInfo = new GoodsInfo();

            goodsInfo.setName(addTermsRequest.getName());
            goodsInfo.setModel(addTermsRequest.getModel());
            goodsInfo.setCreateDate(createDate);
            goodsInfo.setEnable(EnableEnum.ENABLE.isValue());

            goodsInfos.add(goodsInfo);

        }

        int addNum = goodsInfoDao.addGoodsInfoBatch(goodsInfos);

        if (addNum != addTermsRequests.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名次添加失败！"
                    + "[需要添加: " + Integer.toString(addTermsRequests.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addGoodsInfoBatch add to DB less than need! [need = {}][real = {}]",
                    addTermsRequests.size(), addNum);

            return responseVO;
        }

        return termsRecordService.addRecords(TermsRecordTypeEnum.GOODS_INFO, addTermsRequests, createDate);
    }

    /**
     * 删除指定id的货物信息
     *
     * @param id 需要删除的信息的Id
     *
     * @return 删除结果
     */
    @Override
    public ResponseVO delGoodsInfoById(int id) {

        ResponseVO responseVO = new ResponseVO();

        Date deleteDate = new Date();

        GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfoById(id);

        int delNum = goodsInfoDao.delGoodsInfoById(id, deleteDate);

        if (delNum != 1) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("数据删除失败");

            log.error("delGoodsInfoBatch del false!");
        }

        AddTermsRequest delTermsRequest = new AddTermsRequest();
        delTermsRequest.setName(goodsInfo.getName());
        delTermsRequest.setModel(goodsInfo.getModel());
        responseVO = termsRecordService.delRecord(TermsRecordTypeEnum.GOODS_INFO, delTermsRequest, deleteDate);

        return responseVO;
    }

}
