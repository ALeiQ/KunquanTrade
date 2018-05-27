package com.sdut.trade.service;

import com.sdut.trade.httpmodel.request.AddDealRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：资金往来业务层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
public interface TransactionDetailService {

    /**
     * 获取所有资金往来记录
     *
     * @return
     */
    ResponseVO getAll();

    /**
     * 添加资金往来记录
     *
     * @param addDealRequest
     *
     * @return
     */
    ResponseVO addDeal(AddDealRequest addDealRequest);

    /**
     * 更新资金往来记录
     *
     * @param dealId
     * @param updateDealRequest
     *
     * @return
     */
    ResponseVO updateDeal(int dealId, AddDealRequest updateDealRequest);

    /**
     * 删除指定id的资金往来
     *
     * @param delId
     *
     * @return
     */
    ResponseVO delDealById(Integer delId);
}
