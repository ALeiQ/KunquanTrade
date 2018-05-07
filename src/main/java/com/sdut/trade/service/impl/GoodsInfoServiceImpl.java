package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sdut.trade.dao.trade.GoodsInfoDao;
import com.sdut.trade.domain.data.GoodsInfoDO;
import com.sdut.trade.domain.view.GoodsInfoVO;
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
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoDao goodsInfoDao;

    /**
     * 常用名词页获取全部货物信息
     * @return 货物信息数组
     */
    @Override
    public List<GoodsInfoVO> getAllGoodsInfo() {

        List<GoodsInfoVO> goodsInfoVOS = new ArrayList<>();

        List<GoodsInfoDO> goodsInfoDOS = goodsInfoDao.getAll();

        /**
         * 将数据库原始数据包装成前端展示数据
         */
        for (GoodsInfoDO goodsInfoDO : goodsInfoDOS) {

            GoodsInfoVO goodsInfoVO = new GoodsInfoVO();

            goodsInfoVO.setId(goodsInfoDO.getId());
            goodsInfoVO.setName(goodsInfoDO.getName());
            goodsInfoVO.setModel(goodsInfoDO.getModel());

            goodsInfoVOS.add(goodsInfoVO);
        }

        return goodsInfoVOS;
    }

}
