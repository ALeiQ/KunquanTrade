package com.sdut.trade.domain.view;

import lombok.Data;

/**
 * 类描述：controller层返回对象
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
@Data
public class ResponseVO {

    /**
     * 返回结果
     */
    private Object data;

    /**
     * 数据总数（用于后端分页）
     */
    private int dataLength;

    /**
     * 结果码 0正常 -1异常
     */
    private int resultCode;

}
