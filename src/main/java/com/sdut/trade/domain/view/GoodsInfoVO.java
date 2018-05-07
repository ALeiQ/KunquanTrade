package com.sdut.trade.domain.view;

import lombok.Data;

/**
 * 类描述：货物信息展示对象
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
@Data
public class GoodsInfoVO {

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

}
