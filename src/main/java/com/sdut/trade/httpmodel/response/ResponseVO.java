package com.sdut.trade.httpmodel.response;

import com.sdut.trade.enums.ResponseEnum;
import com.sdut.trade.enums.impl.ResultEnum;

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
     * 异常原因
     */
    private String resultMsg = ResultEnum.SUCCESS.getDesc();

    /**
     * 结果码 0正常 -1异常
     */
    private int resultCode = ResultEnum.SUCCESS.getValue();

    public void setResult(int code, String msg) {
        this.setResultCode(code);
        this.setResultMsg(msg);
    }

    public void setResult(ResponseEnum responseEnum) {
        this.setResultCode(responseEnum.getValue());
        this.setResultMsg(responseEnum.getDesc());
    }

}
