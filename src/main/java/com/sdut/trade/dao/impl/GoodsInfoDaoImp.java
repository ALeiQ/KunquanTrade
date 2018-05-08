package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.GoodsInfoDao;
import com.sdut.trade.entity.GoodsInfo;
import com.sdut.trade.entity.GoodsInfoExample;
import com.sdut.trade.mapper.GoodsInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：货物信息Dao实现层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/8
 */
@Component
@Slf4j
public class GoodsInfoDaoImp implements GoodsInfoDao {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    /**
     * 获取货物信息全部数据
     *
     * @return 全部货物信息
     */
    @Override
    public List<GoodsInfo> getAll() {
        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();

        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByExample(goodsInfoExample);

        return goodsInfos;
    }

}
