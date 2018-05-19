package com.sdut.trade.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.sdut.trade.dao.LogisticsCompanyInfoDao;
import com.sdut.trade.entity.LogisticsCompanyInfo;
import com.sdut.trade.entity.LogisticsCompanyInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.LogisticsCompanyInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输公司信息Dao实现层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class LogisticsCompanyInfoDaoImp implements LogisticsCompanyInfoDao {

    @Autowired
    private LogisticsCompanyInfoMapper logisticsCompanyInfoMapper;

    /**
     * 获取运输公司信息全部可用（未删除的）数据
     *
     * @return 全部运输公司信息
     */
    @Override
    public List<LogisticsCompanyInfo> getAllEnable() {
        LogisticsCompanyInfoExample logisticsCompanyInfoExample = new LogisticsCompanyInfoExample();

        logisticsCompanyInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        logisticsCompanyInfoExample.setOrderByClause("id desc");

        List<LogisticsCompanyInfo> logisticsCompanyInfos = logisticsCompanyInfoMapper.selectByExample(logisticsCompanyInfoExample);

        return logisticsCompanyInfos;
    }

    /**
     * 获取指定id的运输公司信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public LogisticsCompanyInfo getLogisticsCompanyById(int id) {
        return logisticsCompanyInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加运输公司信息到数据库
     *
     * @param logisticsCompanyInfos
     *
     * @return 数据库是否插入成功
     */
    @Override
    public int addLogisticsCompanyInfoBatch(List<LogisticsCompanyInfo> logisticsCompanyInfos) {

        return logisticsCompanyInfoMapper.batchInsert(logisticsCompanyInfos);

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
    public int delLogisticsCompanyInfoById(int id, Date deleteDate) {

        LogisticsCompanyInfo logisticsCompanyInfo = logisticsCompanyInfoMapper.selectByPrimaryKey(id);

        logisticsCompanyInfo.setEnable(EnableEnum.DISABLE.isValue());
        logisticsCompanyInfo.setDeleteDate(deleteDate);

        return logisticsCompanyInfoMapper.updateByPrimaryKey(logisticsCompanyInfo);
    }

    /**
     * 根据关键词模糊匹配运输公司名
     *
     * @param query 关键词
     *
     * @return
     */
    @Override
    public List<LogisticsCompanyInfo> getLogisticsCompanyByKeyword(String query) {

        LogisticsCompanyInfoExample logisticsCompanyInfoExample = new LogisticsCompanyInfoExample();

        logisticsCompanyInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andNameLike('%' + query + '%');

        return logisticsCompanyInfoMapper.selectByExample(logisticsCompanyInfoExample);
    }

    /**
     * 添加单条运输公司信息
     *
     * @param logisticsCompanyInfo
     *
     * @return
     */
    @Override
    public boolean addLogisticsCompanyInfo(LogisticsCompanyInfo logisticsCompanyInfo) {
        return logisticsCompanyInfoMapper.insert(logisticsCompanyInfo) == 1;
    }

    /**
     * 查询运输公司名是否存在
     *
     * @param transCompany
     *
     * @return
     */
    @Override
    public boolean hasLogisticsCompanyName(String transCompany) {

        LogisticsCompanyInfoExample logisticsCompanyInfoExample = new LogisticsCompanyInfoExample();

        logisticsCompanyInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andNameEqualTo(transCompany);

        return logisticsCompanyInfoMapper.countByExample(logisticsCompanyInfoExample) > 0;
    }

}
