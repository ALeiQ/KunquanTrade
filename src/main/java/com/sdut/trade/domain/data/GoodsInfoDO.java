package com.sdut.trade.domain.data;

import java.util.Date;

import lombok.Data;

/**
 * 类描述：货物信息持久对象
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
@Data
public class GoodsInfoDO {

    /**
     * 编号
     */
    private int id;

    /**
     * 货物名称
     */
    private String name;

    /**
     * 货物型号
     */
    private String model;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 逻辑删除日期
     */
    private Date deleteDate;

    /**
     * 是否被逻辑删除
     * 1.可用
     * 2.被删除
     */
    private boolean enable;
}
