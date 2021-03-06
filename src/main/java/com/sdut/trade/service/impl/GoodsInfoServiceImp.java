package com.sdut.trade.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sdut.trade.bean.GoodsInfoVO;
import com.sdut.trade.dao.GoodsInfoDao;
import com.sdut.trade.entity.GoodsInfo;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.ExceptionEnum;
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
     *
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
     *
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
                    + "[需要添加: " + Integer.toString(addTermsRequests.size()) + " 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addGoodsInfoBatch add to DB less than need! [need = {}][real = {}]",
                    addTermsRequests.size(), addNum);

            return responseVO;
        }

        return termsRecordService.addRecords(TermsRecordTypeEnum.GOODS_INFO, addTermsRequests, createDate);
    }

    /**
     * 添加单条货物信息
     *
     * @param goodsName
     * @param goodsModel
     * @param createDate
     */
    @Override
    public void addGoodsTerm(String goodsName, String goodsModel, Date createDate) {

        // 如果货物信息不在常用名词库，则添加到库
        if (!StringUtils.isEmpty(goodsName)) {

            if (!goodsInfoDao
                    .hasGoods(goodsName, goodsModel)) {

                GoodsInfo goodsInfo = new GoodsInfo();

                goodsInfo.setName(goodsName);
                goodsInfo.setModel(goodsModel);
                goodsInfo.setCreateDate(createDate);
                goodsInfo.setEnable(EnableEnum.ENABLE.isValue());

                if (!goodsInfoDao.addGoodsInfo(goodsInfo)) {
                    log.warn("addGoodsTerms add GoodsInfo false! [name={}, model={}]", goodsName, goodsModel);
                }

                AddTermsRequest addTermsRequest = new AddTermsRequest();

                addTermsRequest.setName(goodsName);
                addTermsRequest.setModel(goodsModel);

                if (termsRecordService.addRecord(TermsRecordTypeEnum.GOODS_INFO, addTermsRequest, createDate)
                        .getResultCode() != 0) {
                    log.warn("addGoodsTerms add GoodsInfoRecord false! [name={}, model={}]", goodsName, goodsModel);
                }
            }
        }
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
            responseVO.setResult(ExceptionEnum.DB_DEL_FAILURE);
            log.error("delGoodsInfoBatch del false!");
        }

        AddTermsRequest delTermsRequest = new AddTermsRequest();
        delTermsRequest.setName(goodsInfo.getName());
        delTermsRequest.setModel(goodsInfo.getModel());
        responseVO = termsRecordService.delRecord(TermsRecordTypeEnum.GOODS_INFO, delTermsRequest, deleteDate);

        return responseVO;
    }

    /**
     * 根据关键词查询货物名称
     *
     * @param query 关键词
     *
     * @return
     */
    @Override
    public ResponseVO getGoodsNameByKeyword(String query) {

        ResponseVO responseVO = new ResponseVO();

        List<GoodsInfo> goodsInfos = goodsInfoDao.getGoodsInfoByNameKeyword(query);
        Set<String> goodsNames = new HashSet<>();

        for (GoodsInfo goodsInfo : goodsInfos) {
            if (StringUtils.isEmpty(goodsInfo.getName())) {
                continue;
            }
            goodsNames.add(goodsInfo.getName());
        }

        List<String> orderGoodsNames = new ArrayList<>(goodsNames);

        Collections.sort(orderGoodsNames, (o1, o2) -> {
            Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
            return com.compare(o1, o2);
        });

        responseVO.setData(orderGoodsNames);
        return responseVO;
    }

    /**
     * 根据型号关键词查询匹配的型号
     *
     * @param query
     * @param goodsName
     *
     * @return
     */
    @Override
    public ResponseVO getGoodsModelByKeyword(String query, String goodsName) {

        ResponseVO responseVO = new ResponseVO();

        List<GoodsInfo> goodsInfos = goodsInfoDao.getGoodsInfoByModelKeyword(query, goodsName);
        List<String> goodsModels = new ArrayList<>();

        for (GoodsInfo goodsInfo : goodsInfos) {
            if (StringUtils.isEmpty(goodsInfo.getModel())) {
                continue;
            }
            goodsModels.add(goodsInfo.getModel());
        }

        responseVO.setData(goodsModels);
        return responseVO;

    }

}
