package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.sdut.trade.dao.CompanyInfoDao;
import com.sdut.trade.entity.CompanyInfo;
import com.sdut.trade.entity.CompanyInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.CompanyInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：公司信息Dao实现层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class CompanyInfoDaoImp implements CompanyInfoDao {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    /**
     * 获取公司信息全部可用（未删除的）数据
     *
     * @return 全部公司信息
     */
    @Override
    public List<CompanyInfo> getAllEnable() {
        CompanyInfoExample companyInfoExample = new CompanyInfoExample();

        companyInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        companyInfoExample.setOrderByClause("id desc");

        List<CompanyInfo> companyInfos = companyInfoMapper.selectByExample(companyInfoExample);

        return companyInfos;
    }

    /**
     * 添加公司信息到数据库
     *
     * @param companyInfos
     *
     * @return 数据库是否插入成功
     */
    @Override
    public int addCompanyInfoBatch(List<CompanyInfo> companyInfos) {

        return companyInfoMapper.batchInsert(companyInfos);

    }

    /**
     * 删除公司信息到数据库
     *
     * @param id 待删除id
     *
     * @return 成功插入数据库的条数
     */
    @Override
    public int delCompanyInfoById(int id) {

        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(id);

        companyInfo.setEnable(EnableEnum.DISABLE.isValue());

        return companyInfoMapper.updateByPrimaryKey(companyInfo);

    }

}
