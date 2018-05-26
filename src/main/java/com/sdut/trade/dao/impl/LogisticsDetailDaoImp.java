package com.sdut.trade.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.LogisticsDetailDao;
import com.sdut.trade.entity.LogisticsDetail;
import com.sdut.trade.entity.LogisticsDetailExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.LogisticsDetailMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输明细Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
@Component
@Slf4j
public class LogisticsDetailDaoImp implements LogisticsDetailDao {

    @Autowired
    LogisticsDetailMapper logisticsDetailMapper;

    /**
     * 添加单条运输明细数据
     *
     * @param logisticsDetail
     *
     * @return
     */
    @Override
    public int addLogisticsDetail(LogisticsDetail logisticsDetail) {

        return logisticsDetailMapper.insert(logisticsDetail);

    }

    /**
     * 分页查询运输明细（逆序查询，后插入的先查到）
     *
     * @return
     */
    @Override
    public List<LogisticsDetail> getAll() {

        LogisticsDetailExample logisticsDetailExample = new LogisticsDetailExample();

        logisticsDetailExample
                .setOrderByClause("id desc");

        logisticsDetailExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return logisticsDetailMapper.selectByExample(logisticsDetailExample);

    }

    /**
     * 获取数据总数
     *
     * @return
     */
    @Override
    public long getCount() {

        LogisticsDetailExample logisticsDetailExample = new LogisticsDetailExample();

        logisticsDetailExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return logisticsDetailMapper.countByExample(logisticsDetailExample);
    }

    /**
     * 更新id的运输明细
     *
     * @param id
     * @param logisticsDetail
     *
     * @return
     */
    @Override
    public int updateLogisticsDetail(int id, LogisticsDetail logisticsDetail) {

        LogisticsDetailExample logisticsDetailExample = new LogisticsDetailExample();

        logisticsDetailExample.createCriteria()
                .andIdEqualTo(id)
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return logisticsDetailMapper.updateByExample(logisticsDetail, logisticsDetailExample);
    }

    /**
     * 删除单条运输明细记录
     *
     * @param delId
     *
     * @return
     */
    @Override
    public int delLogisticsDetail(Integer delId, Date deleteDate) {

        LogisticsDetail logisticsDetail = new LogisticsDetail();

        logisticsDetail.setId(delId);
        logisticsDetail.setDeleteDate(deleteDate);
        logisticsDetail.setEnable(EnableEnum.DISABLE.isValue());

        return logisticsDetailMapper.updateByPrimaryKeySelective(logisticsDetail);

    }

    /**
     * 获取id的运输明细
     *
     * @param id
     *
     * @return
     */
    @Override
    public LogisticsDetail getById(int id) {
        return logisticsDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取多条id数据
     *
     * @param ids
     *
     * @return
     */
    @Override
    public List<LogisticsDetail> getByIds(List<Integer> ids) {

        LogisticsDetailExample logisticsDetailExample = new LogisticsDetailExample();

        logisticsDetailExample.setOrderByClause("id desc");

        logisticsDetailExample.createCriteria()
                .andIdIn(ids);

        return logisticsDetailMapper.selectByExample(logisticsDetailExample);
    }
}
