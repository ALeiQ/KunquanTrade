package com.sdut.trade.dao.impl;

import java.util.Date;
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
     * 获取指定id的货物信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public GoodsInfo getGoodsInfoById(int id) {
        return goodsInfoMapper.selectByPrimaryKey(id);
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
     * @param id         待删除id
     * @param deleteDate 删除时间
     *
     * @return 成功插入数据库的条数
     */
    @Override
    public int delGoodsInfoById(int id, Date deleteDate) {

        GoodsInfo goodsInfo = new GoodsInfo();

        goodsInfo.setId(id);
        goodsInfo.setEnable(EnableEnum.DISABLE.isValue());
        goodsInfo.setDeleteDate(deleteDate);

        return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    /**
     * 通过获取名称关键词查询货物信息
     *
     * @param query 货物名称关键字
     *
     * @return 匹配的货物
     */
    @Override
    public List<GoodsInfo> getGoodsInfoByNameKeyword(String query) {

        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();

        goodsInfoExample.setOrderByClause("convert(name using gbk) asc");

        goodsInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andNameLike('%' + query + '%');

        return goodsInfoMapper.selectByExample(goodsInfoExample);
    }

    /**
     * 通过已填的货物名称和模糊匹配的型号关键词查询匹配的型号
     *
     * @param query
     * @param goodsName
     *
     * @return
     */
    @Override
    public List<GoodsInfo> getGoodsInfoByModelKeyword(String query, String goodsName) {

        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();

        goodsInfoExample.setOrderByClause("convert(model using gbk) asc");

        goodsInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andNameEqualTo(goodsName)
                .andModelLike('%' + query + '%');

        return goodsInfoMapper.selectByExample(goodsInfoExample);
    }

    /**
     * 查询库中是否有该货品信息
     *
     * @param goodsName
     * @param goodsModel
     *
     * @return
     */
    @Override
    public boolean hasGoods(String goodsName, String goodsModel) {

        GoodsInfoExample goodsInfoExample = new GoodsInfoExample();

        goodsInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andModelEqualTo(goodsModel)
                .andNameEqualTo(goodsName);

        return goodsInfoMapper.countByExample(goodsInfoExample) > 0;
    }

    /**
     * 插入单条货品信息到库中
     *
     * @param goodsInfo
     *
     * @return
     */
    @Override
    public boolean addGoodsInfo(GoodsInfo goodsInfo) {

        return goodsInfoMapper.insert(goodsInfo) == 1;

    }

}
