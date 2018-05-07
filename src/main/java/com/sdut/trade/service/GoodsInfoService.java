package com.sdut.trade.service;

import java.util.List;

import com.sdut.trade.domain.view.GoodsInfoVO;

/**
 * 类描述：货物信息业务层接口
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
public interface GoodsInfoService {

    List<GoodsInfoVO> getAllGoodsInfo();

}
