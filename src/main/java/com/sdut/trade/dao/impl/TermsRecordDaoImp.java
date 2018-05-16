package com.sdut.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.TermsRecordDao;
import com.sdut.trade.entity.TermsRecord;
import com.sdut.trade.entity.TermsRecordExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.TermsRecordMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：常用名词增删记录表Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/10
 */
@Component
@Slf4j
public class TermsRecordDaoImp implements TermsRecordDao {

    @Autowired
    TermsRecordMapper termsRecordMapper;

    /**
     * 查询所有常用名词增删记录
     *
     * @return 常用名词增删记录列表
     */
    @Override
    public List<TermsRecord> getAllEnable() {

        TermsRecordExample termsRecordExample = new TermsRecordExample();

        termsRecordExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return termsRecordMapper.selectByExample(termsRecordExample);

    }

    /**
     * 分页查询常用名词增删记录
     *
     * @param offset 查询起点
     * @param rows   行数
     *
     * @return 查询页的记录列表
     */
    @Override
    public List<TermsRecord> getAllInRange(int offset, int rows) {

        TermsRecordExample termsRecordExample = new TermsRecordExample();

        termsRecordExample
                .limit(offset, rows)
                .setOrderByClause("id desc");

        termsRecordExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return termsRecordMapper.selectByExample(termsRecordExample);
    }

    /**
     * 获取数据总数
     *
     * @return 分页页码数
     */
    @Override
    public long getCount() {

        TermsRecordExample termsRecordExample = new TermsRecordExample();

        termsRecordExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return termsRecordMapper.countByExample(termsRecordExample);

    }

    /**
     * 添加多条记录到数据库
     *
     * @param termsRecordList 需添加的记录表
     *
     * @return
     */
    @Override
    public int addTermBatch(List<TermsRecord> termsRecordList) {
        return termsRecordMapper.batchInsert(termsRecordList);
    }

    /**
     * 添加单条记录到数据库
     *
     * @param termsRecord 需添加的记录表
     *
     * @return
     */
    @Override
    public int addTerm(TermsRecord termsRecord) {
        return termsRecordMapper.insert(termsRecord);
    }
}
