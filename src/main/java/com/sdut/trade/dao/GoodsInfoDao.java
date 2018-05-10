package com.sdut.trade.dao;

import java.util.Date;
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
     * 获取货物信息全部可用（未删除的）数据
     *
     * @return 全部货物信息
     */
    List<GoodsInfo> getAllEnable();

    /**
     * 获取指定id的货物信息
     *
     * @param id
     * @return
     */
    GoodsInfo getGoodsInfoById(int id);

    /**
     * 添加货物信息到数据库
     *
     * @param goodsInfos 待添加的货物列表
     * @return 成功插入数据库的条数
     */
    int addGoodsInfoBatch(List<GoodsInfo> goodsInfos);

    /**
     * 删除货物信息到数据库
     *
     * @param id 待删除id
     * @param deleteDate 删除时间
     * @return 成功插入数据库的条数
     */
    int delGoodsInfoById(int id, Date deleteDate);

}
