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
import com.sdut.trade.enums.impl.ResultEnum;
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

    /**
     * 常用名词页获取全部货物信息
     * @return 货物信息数组
     */
    @Override
    public ResponseVO getAllGoodsInfo() {

        ResponseVO responseVO = new ResponseVO();

        List<GoodsInfoVO> goodsInfoVOS = new ArrayList<>();

        List<GoodsInfo> goodsInfos = goodsInfoDao.getAll();

        // 数据库中未获取到数据
        if (CollectionUtils.isEmpty(goodsInfos)) {
            log.error("getAllGoodsInfo fromDB is empty!");
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
     * @param addTermsRequests
     *
     * @return 添加结果
     */
    @Override
    public ResponseVO addGoodsInfo(List<AddTermsRequest> addTermsRequests) {

        ResponseVO responseVO = new ResponseVO();

        Date createDate = new Date();

        List<GoodsInfo> goodsInfos = new ArrayList<>();

        for (AddTermsRequest addTermsRequest : addTermsRequests) {

            GoodsInfo goodsInfo = new GoodsInfo();

            goodsInfo.setName(addTermsRequest.getName());
            goodsInfo.setModel(addTermsRequest.getModel());
            goodsInfo.setCreateDate(createDate);

            goodsInfos.add(goodsInfo);

        }



        return responseVO;

    }

}
