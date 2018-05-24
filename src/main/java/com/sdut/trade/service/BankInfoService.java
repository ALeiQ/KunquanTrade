package com.sdut.trade.service;

import java.util.List;

import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：银行信息业务层接口
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/9
 */
public interface BankInfoService {

    /**
     * 常用名词页获取全部银行信息
     *
     * @return 银行信息数组
     */
    ResponseVO getAllBankInfo();

    /**
     * 常用名词添加银行信息
     *
     * @param addTermsRequests 添加的数据组
     *
     * @return 添加结果
     */
    ResponseVO addBankInfoBatch(List<AddTermsRequest> addTermsRequests);

    /**
     * 删除指定id的银行信息
     *
     * @param id 需要删除的信息的Id
     *
     * @return 删除结果
     */
    ResponseVO delBankInfoById(int id);

    /**
     * 通过关键词模糊查询银行名称
     *
     * @param query 查询关键字
     *
     * @return
     */
    ResponseVO getBankByKeyword(String query);
}
