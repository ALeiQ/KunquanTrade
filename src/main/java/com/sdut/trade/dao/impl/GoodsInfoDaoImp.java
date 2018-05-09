package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.sdut.trade.dao.GoodsInfoDao;
import com.sdut.trade.entity.GoodsInfo;
import com.sdut.trade.entity.GoodsInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
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
     * 获取货物信息全部可用（未删除的）数据
     *
     * @return 全部货物信息
     */
    @Override
    public List<GoodsInfo> getAllEnable() {
        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();

        goodsInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        goodsInfoExample.setOrderByClause("id desc");

        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByExample(goodsInfoExample);

        return goodsInfos;
    }

    /**
     * 添加货物信息到数据库
     *
     * @param goodsInfos
     *
     * @return 数据库是否插入成功
     */
    @Override
    public int addGoodsInfoBatch(List<GoodsInfo> goodsInfos) {

        return goodsInfoMapper.batchInsert(goodsInfos);

    }

    /**
     * 删除货物信息到数据库
     *
     * @param id 待删除id
     *
     * @return 成功插入数据库的条数
     */
    @Override
    public int delGoodsInfoById(int id) {

        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(id);

        goodsInfo.setEnable(EnableEnum.DISABLE.isValue());

        return goodsInfoMapper.updateByPrimaryKey(goodsInfo);

    }

}
