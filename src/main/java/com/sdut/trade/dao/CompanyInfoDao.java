package com.sdut.trade.dao;

import java.util.Date;
import java.util.List;

import com.sdut.trade.entity.CompanyInfo;

/**
 * 类描述：公司信息Dao接口层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface CompanyInfoDao {

    /**
     * 获取公司信息全部可用（未删除的）数据
     *
     * @return 全部公司信息
     */
    List<CompanyInfo> getAllEnable();

    /**
     * 获取指定id的公司信息
     *
     * @param id
     * @return
     */
    CompanyInfo getCompanyInfoById(int id);

    /**
     * 添加公司信息到数据库
     *
     * @param companyInfos 待添加的公司列表
     * @return 成功插入数据库的条数
     */
    int addCompanyInfoBatch(List<CompanyInfo> companyInfos);

    /**
     * 删除公司信息到数据库
     *
     * @param id 待删除id
     * @param deleteDate 删除日期
     * @return 成功插入数据库的条数
     */
    int delCompanyInfoById(int id, Date deleteDate);

    /**
     * 通过公司名中的关键词查询公司信息
     * @param query 公司名关键词
     * @return
     */
    List<CompanyInfo> getCompanyInfoByKeywordName(String query);
}
