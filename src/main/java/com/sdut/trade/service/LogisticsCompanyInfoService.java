package com.sdut.trade.service;

import java.util.List;

import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：运输公司信息业务层接口
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface LogisticsCompanyInfoService {

    /**
     * 常用名词页获取全部运输公司信息
     * @return 运输公司信息数组
     */
    ResponseVO getAllLogisticsCompanyInfo();

    /**
     * 常用名词添加运输公司信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    ResponseVO addLogisticsCompanyInfoBatch(List<AddTermsRequest> addTermsRequests);

    /**
     * 删除指定id的运输公司信息
     *
     * @param id 需要删除的信息的Id
     * @return 删除结果
     */
    ResponseVO delLogisticsCompanyInfoById(int id);

    /**
     * 查询与关键字匹配的运输公司名
     * @param query 匹配关键词
     * @return
     */
    ResponseVO getLogisticsCompanyByKeyword(String query);
}
