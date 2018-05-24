package com.sdut.trade.service;

import java.util.Date;
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
     *
     * @return 货物信息数组
     */
    ResponseVO getAllGoodsInfo();

    /**
     * 根据货物名关键词查询货物名称
     *
     * @param query 关键词
     *
     * @return
     */
    ResponseVO getGoodsNameByKeyword(String query);

    /**
     * 根据型号关键词查询匹配的型号
     *
     * @param query
     * @param goodsName
     *
     * @return
     */
    ResponseVO getGoodsModelByKeyword(String query, String goodsName);

    /**
     * 常用名词添加货物信息
     *
     * @param addTermsRequests 添加的数据组
     *
     * @return 添加结果
     */
    ResponseVO addGoodsInfoBatch(List<AddTermsRequest> addTermsRequests);

    /**
     * 添加单条货物信息
     * @param goodsName
     * @param goodsModel
     * @param createDate
     */
    void addGoodsTerm(String goodsName, String goodsModel, Date createDate);

    /**
     * 删除指定id的货物信息
     *
     * @param id 需要删除的信息的Id
     *
     * @return 删除结果
     */
    ResponseVO delGoodsInfoById(int id);
}
