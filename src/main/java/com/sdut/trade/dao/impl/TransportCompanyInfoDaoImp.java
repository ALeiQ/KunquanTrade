package com.sdut.trade.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.sdut.trade.dao.TransportCompanyInfoDao;
import com.sdut.trade.entity.TransportCompanyInfo;
import com.sdut.trade.entity.TransportCompanyInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.TransportCompanyInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输公司信息Dao实现层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class TransportCompanyInfoDaoImp implements TransportCompanyInfoDao {

    @Autowired
    private TransportCompanyInfoMapper transportCompanyInfoMapper;

    /**
     * 获取运输公司信息全部可用（未删除的）数据
     *
     * @return 全部运输公司信息
     */
    @Override
    public List<TransportCompanyInfo> getAllEnable() {
        TransportCompanyInfoExample transportCompanyInfoExample = new TransportCompanyInfoExample();

        transportCompanyInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        transportCompanyInfoExample.setOrderByClause("id desc");

        List<TransportCompanyInfo> transportCompanyInfos = transportCompanyInfoMapper.selectByExample(transportCompanyInfoExample);

        return transportCompanyInfos;
    }

    /**
     * 获取指定id的运输公司信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public TransportCompanyInfo getTransportCompanyById(int id) {
        return transportCompanyInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加运输公司信息到数据库
     *
     * @param transportCompanyInfos
     *
     * @return 数据库是否插入成功
     */
    @Override
    public int addTransportCompanyInfoBatch(List<TransportCompanyInfo> transportCompanyInfos) {

        return transportCompanyInfoMapper.batchInsert(transportCompanyInfos);

    }

    /**
     * 删除运输公司信息到数据库
     *
     * @param id         待删除id
     * @param deleteDate 删除时间
     *
     * @return 成功插入数据库的条数
     */
    @Override
    public int delTransportCompanyInfoById(int id, Date deleteDate) {

        TransportCompanyInfo transportCompanyInfo = transportCompanyInfoMapper.selectByPrimaryKey(id);

        transportCompanyInfo.setEnable(EnableEnum.DISABLE.isValue());
        transportCompanyInfo.setDeleteDate(deleteDate);

        return transportCompanyInfoMapper.updateByPrimaryKey(transportCompanyInfo);
    }

    /**
     * 根据关键词模糊匹配运输公司名
     *
     * @param query 关键词
     *
     * @return
     */
    @Override
    public List<TransportCompanyInfo> getTransportCompanyByKeyword(String query) {

        TransportCompanyInfoExample transportCompanyInfoExample = new TransportCompanyInfoExample();

        transportCompanyInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andNameLike('%' + query + '%');

        return transportCompanyInfoMapper.selectByExample(transportCompanyInfoExample);
    }

}
