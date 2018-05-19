package com.sdut.trade.dao;

import java.util.Date;
import java.util.List;

import com.sdut.trade.entity.GoodsInfo;

/**
 * 类描述：货物信息Dao接口层
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/8
 */
public interface GoodsInfoDao {

    /**
     * 获取货物信息全部可用（未删除的）数据
     *
     * @return 全部货物信息
     */
    List<GoodsInfo> getAllEnable();

    /**
     * 获取指定id的货物信息
     *
     * @param id
     * @return 匹配的货物
     */
    GoodsInfo getGoodsInfoById(int id);

    /**
     * 添加货物信息到数据库
     *
     * @param goodsInfos 待添加的货物列表
     * @return 成功插入数据库的条数
     */
    int addGoodsInfoBatch(List<GoodsInfo> goodsInfos);

    /**
     * 删除货物信息到数据库
     *
     * @param id 待删除id
     * @param deleteDate 删除时间
     * @return 成功插入数据库的条数
     */
    int delGoodsInfoById(int id, Date deleteDate);

    /**
     * 通过获取名称关键词查询货物信息
     * @param query 货物名称关键字
     * @return 匹配的货物
     */
    List<GoodsInfo> getGoodsInfoByNameKeyword(String query);

    /**
     * 通过已填的货物名称和模糊匹配的型号关键词查询匹配的型号
     * @param query
     * @param goodsName
     * @return
     */
    List<GoodsInfo> getGoodsInfoByModelKeyword(String query, String goodsName);

    /**
     * 查询库中是否有该货品信息
     * @param goodsName
     * @param goodsModel
     * @return
     */
    boolean hasGoods(String goodsName, String goodsModel);

    /**
     * 插入单条货品信息到库中
     * @param goodsInfo
     * @return
     */
    boolean addGoodsInfo(GoodsInfo goodsInfo);
}
