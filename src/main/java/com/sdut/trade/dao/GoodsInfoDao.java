package com.sdut.trade.dao;

import java.util.List;

import com.sdut.trade.entity.GoodsInfo;

/**
 * 类描述：货物信息Dao接口层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/8
 */
public interface GoodsInfoDao {

    /**
     * 获取货物信息全部数据
     * @return 全部货物信息
     */
    List<GoodsInfo> getAll();

}
