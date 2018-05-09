package com.sdut.trade.service;

import java.util.List;

import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：公司信息业务层接口
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface CompanyInfoService {

    /**
     * 常用名词页获取全部公司信息
     * @return 公司信息数组
     */
    ResponseVO getAllCompanyInfo();

    /**
     * 常用名词添加公司信息
     *
     * @param addTermsRequests 添加的数据组
     * @return 添加结果
     */
    ResponseVO addCompanyInfoBatch(List<AddTermsRequest> addTermsRequests);

    /**
     * 删除指定id的公司信息
     *
     * @param id 需要删除的信息的Id
     * @return 删除结果
     */
    ResponseVO delCompanyInfoById(int id);

}
