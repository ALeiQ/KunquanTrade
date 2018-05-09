package com.sdut.trade.dao;

import java.util.List;

import com.sdut.trade.entity.TransportCompanyInfo;

/**
 * 类描述：运输公司信息Dao接口层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface TransportCompanyInfoDao {

    /**
     * 获取运输公司信息全部可用（未删除的）数据
     *
     * @return 全部运输公司信息
     */
    List<TransportCompanyInfo> getAllEnable();

    /**
     * 添加运输公司信息到数据库
     *
     * @param transportCompanyInfos 待添加的运输公司列表
     * @return 成功插入数据库的条数
     */
    int addTransportCompanyInfoBatch(List<TransportCompanyInfo> transportCompanyInfos);

    /**
     * 删除运输公司信息到数据库
     *
     * @param id 待删除id
     * @return 成功插入数据库的条数
     */
    int delTransportCompanyInfoById(int id);

}
