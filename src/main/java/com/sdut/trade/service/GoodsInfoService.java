package com.sdut.trade.service;

import java.util.List;

import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：货物信息业务层接口
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
public interface GoodsInfoService {

    /**
     * 常用名词页获取全部货物信息
     * @return 货物信息数组
     */
    ResponseVO getAllGoodsInfo();

    /**
     * 常用名词添加货物信息
     * @return 添加结果
     */
    ResponseVO addGoodsInfo(List<AddTermsRequest> addTermsRequests);

}
