package com.sdut.trade.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sdut.trade.entity.TermsRecord;

/**
 * 类描述：常用名词增删记录Dao层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
@Component
public interface TermsRecordDao {

    /**
     * 查询所有常用名词增删记录
     *
     * @return 常用名词增删记录列表
     */
    List<TermsRecord> getAllEnable();

    /**
     * 分页查询常用名词增删记录（逆序查询，后插入的先查到）
     *
     * @param offset 查询起点
     * @param rows   行数
     *
     * @return 查询页的记录列表
     */
    List<TermsRecord> getAllInRange(int offset, int rows);

    /**
     * 分页查询某类常用名词增删记录（逆序查询，后插入的先查到）
     *
     * @param offset
     * @param rows
     * @param getType
     *
     * @return
     */
    List<TermsRecord> getInRangeByType(int offset, Integer rows, Integer getType);

    /**
     * 获取数据总数
     *
     * @return
     */
    long getCount();

    /**
     * 获取某类的数据总数
     *
     * @param getType
     *
     * @return
     */
    long getCountByType(Integer getType);

    /**
     * 添加多条记录到数据库
     *
     * @param termsRecordList 需添加的记录表
     *
     * @return
     */
    int addTermBatch(List<TermsRecord> termsRecordList);

    /**
     * 添加单条记录到数据库
     *
     * @param termsRecord 需添加的记录表
     *
     * @return
     */
    int addTerm(TermsRecord termsRecord);

}
