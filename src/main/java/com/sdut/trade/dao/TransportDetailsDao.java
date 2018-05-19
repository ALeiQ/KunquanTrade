package com.sdut.trade.dao;

import com.sdut.trade.entity.TransportDetails;

/**
 * 类描述：运输明细Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
public interface TransportDetailsDao {

    /**
     * 添加单条运输明细数据
     * @param transportDetails
     * @return
     */
    int addTransportDetail(TransportDetails transportDetails);

}
