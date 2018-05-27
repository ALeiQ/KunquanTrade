package com.sdut.trade.service;

import javax.servlet.http.HttpServletRequest;

import com.sdut.trade.httpmodel.request.LoginRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：登陆业务层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
public interface UserService {

    /**
     * 登陆请求
     *
     * @param loginRequest
     * @param request
     *
     * @return
     */
    ResponseVO login(LoginRequest loginRequest, HttpServletRequest request);

}
