package com.sdut.trade.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.TransportDetailsDao;
import com.sdut.trade.entity.TransportDetails;
import com.sdut.trade.mapper.TransportDetailsMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输明细Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
@Component
@Slf4j
public class TransportDetailsDaoImp implements TransportDetailsDao {

    @Autowired
    TransportDetailsMapper transportDetailsMapper;

    /**
     * 添加单条运输明细数据
     *
     * @param transportDetails
     *
     * @return
     */
    @Override
    public int addTransportDetail(TransportDetails transportDetails) {

        return transportDetailsMapper.insert(transportDetails);

    }
}
