package com.sdut.trade.dao;

import java.util.Date;
import java.util.List;

import com.sdut.trade.entity.BankInfo;

/**
 * 类描述：银行信息Dao接口层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface BankInfoDao {

    /**
     * 获取银行信息全部可用（未删除的）数据
     *
     * @return 全部银行信息
     */
    List<BankInfo> getAllEnable();

    /**
     * 获取指定id的银行信息
     *
     * @param id
     * @return 指定编号的银行信息
     */
    BankInfo getBankInfoById(int id);

    /**
     * 添加银行信息到数据库
     *
     * @param bankInfos 待添加的银行列表
     * @return 成功插入数据库的条数
     */
    int addBankInfoBatch(List<BankInfo> bankInfos);

    /**
     * 删除银行信息到数据库
     *
     * @param id 待删除id
     * @param deleteDate 删除日期
     * @return 成功插入数据库的条数
     */
    int delBankInfoById(int id, Date deleteDate);

}
