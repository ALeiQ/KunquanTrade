package com.sdut.trade.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.BankInfoDao;
import com.sdut.trade.entity.BankInfo;
import com.sdut.trade.entity.BankInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.BankInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：银行信息Dao实现层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
@Slf4j
public class BankInfoDaoImp implements BankInfoDao {

    @Autowired
    private BankInfoMapper bankInfoMapper;

    /**
     * 获取银行信息全部可用（未删除的）数据
     *
     * @return 全部银行信息
     */
    @Override
    public List<BankInfo> getAllEnable() {
        BankInfoExample bankInfoExample = new BankInfoExample();

        bankInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        bankInfoExample.setOrderByClause("id desc");

        List<BankInfo> bankInfos = bankInfoMapper.selectByExample(bankInfoExample);

        return bankInfos;
    }

    /**
     * 获取指定id的银行信息
     *
     * @param id
     *
     * @return 指定编号的银行信息
     */
    @Override
    public BankInfo getBankInfoById(int id) {
        return bankInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据银行名称关键词模糊查找银行信息
     *
     * @param query
     *
     * @return
     */
    @Override
    public List<BankInfo> getBankInfoByKeywordName(String query) {

        BankInfoExample BankInfoExample = new BankInfoExample();

        BankInfoExample.setOrderByClause("convert(name using gbk) asc");

        BankInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andNameLike('%' + query + '%');

        return bankInfoMapper.selectByExample(BankInfoExample);
    }

    /**
     * 添加银行信息到数据库
     *
     * @param bankInfos
     *
     * @return 数据库是否插入成功
     */
    @Override
    public int addBankInfoBatch(List<BankInfo> bankInfos) {

        return bankInfoMapper.batchInsert(bankInfos);

    }

    /**
     * 删除银行信息到数据库
     *
     * @param id         待删除id
     * @param deleteDate 删除时间
     *
     * @return 成功插入数据库的条数
     */
    @Override
    public int delBankInfoById(int id, Date deleteDate) {

        BankInfo bankInfo = new BankInfo();

        bankInfo.setId(id);
        bankInfo.setEnable(EnableEnum.DISABLE.isValue());
        bankInfo.setDeleteDate(deleteDate);

        return bankInfoMapper.updateByPrimaryKeySelective(bankInfo);

    }

}
