package com.sdut.trade.dao;

import java.util.Date;
import java.util.List;

import com.sdut.trade.entity.LogisticsCompanyInfo;

/**
 * 类描述：运输公司信息Dao接口层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface LogisticsCompanyInfoDao {

    /**
     * 获取运输公司信息全部可用（未删除的）数据
     *
     * @return 全部运输公司信息
     */
    List<LogisticsCompanyInfo> getAllEnable();

    /**
     * 获取指定id的运输公司信息
     *
     * @param id
     *
     * @return
     */
    LogisticsCompanyInfo getLogisticsCompanyById(int id);

    /**
     * 添加运输公司信息到数据库
     *
     * @param logisticsCompanyInfos 待添加的运输公司列表
     *
     * @return 成功插入数据库的条数
     */
    int addLogisticsCompanyInfoBatch(List<LogisticsCompanyInfo> logisticsCompanyInfos);

    /**
     * 删除运输公司信息到数据库
     *
     * @param id         待删除id
     * @param deleteDate 删除时间
     *
     * @return 成功插入数据库的条数
     */
    int delLogisticsCompanyInfoById(int id, Date deleteDate);

    /**
     * 根据关键词模糊匹配运输公司名
     *
     * @param query 关键词
     *
     * @return
     */
    List<LogisticsCompanyInfo> getLogisticsCompanyByKeyword(String query);

    /**
     * 添加单条运输公司信息
     *
     * @param logisticsCompanyInfo
     *
     * @return
     */
    boolean addLogisticsCompanyInfo(LogisticsCompanyInfo logisticsCompanyInfo);

    /**
     * 查询运输公司名是否存在
     *
     * @param transCompany
     *
     * @return
     */
    boolean hasLogisticsCompanyName(String transCompany);
}
