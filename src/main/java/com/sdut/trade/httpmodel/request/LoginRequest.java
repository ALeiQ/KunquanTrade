package com.sdut.trade.httpmodel.request;

import lombok.Data;

/**
 * 类描述：登陆请求类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
@Data
public class LoginRequest {

    private String username;

    private String password;

}
