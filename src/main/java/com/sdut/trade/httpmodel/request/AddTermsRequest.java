package com.sdut.trade.httpmodel.request;

import lombok.Data;

/**
 * 类描述：常用名词页面增加数据请求类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
@Data
public class AddTermsRequest {

    /**
     * 名称
     */
    private String name;

    /**
     * 型号
     */
    private String model = "";

}
