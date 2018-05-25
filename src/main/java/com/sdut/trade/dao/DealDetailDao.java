package com.sdut.trade.dao;

import java.util.Date;
import java.util.List;

import com.sdut.trade.entity.DealDetail;

/**
 * 类描述：资金往来Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
public interface DealDetailDao {

    /**
     * 获取所有资金往来记录
     * @return
     */
    List<DealDetail> getAll();

    /**
     * 添加单条资金往来数据
     * @param dealDetail
     * @return
     */
    int addDetail(DealDetail dealDetail);

    /**
     * 刪除指定id的資金往來
     * @param delId
     * @param delDate
     * @return
     */
    int delDealById(Integer delId, Date delDate);
}
