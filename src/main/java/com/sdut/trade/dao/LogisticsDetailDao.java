package com.sdut.trade.dao;

import java.util.List;

import com.sdut.trade.entity.LogisticsDetail;

/**
 * 类描述：运输明细Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
public interface LogisticsDetailDao {

    /**
     * 添加单条运输明细数据
     * @param logisticsDetail
     * @return
     */
    int addLogisticsDetail(LogisticsDetail logisticsDetail);

    /**
     * 查询运输明细（逆序查询，后插入的先查到）
     * @return
     */
    List<LogisticsDetail> getAll();

    /**
     * 获取数据总数
     * @return
     */
    long getCount();
}
