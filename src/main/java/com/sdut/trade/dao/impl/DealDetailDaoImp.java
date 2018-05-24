package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.DealDetailDao;
import com.sdut.trade.entity.DealDetail;
import com.sdut.trade.entity.DealDetailExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.DealDetailMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：资金往来明细Dao实现层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
@Slf4j
@Component
public class DealDetailDaoImp implements DealDetailDao {

    @Autowired
    DealDetailMapper dealDetailMapper;

    /**
     * 获取所有资金往来记录
     *
     * @return
     */
    @Override
    public List<DealDetail> getAll() {

        DealDetailExample dealDetailExample = new DealDetailExample();

        dealDetailExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        dealDetailExample.setOrderByClause("id desc");

        return dealDetailMapper.selectByExample(dealDetailExample);
    }

    /**
     * 添加单条资金往来数据
     *
     * @param dealDetail
     *
     * @return
     */
    @Override
    public int addDetail(DealDetail dealDetail) {
        return dealDetailMapper.insertSelective(dealDetail);
    }
}
