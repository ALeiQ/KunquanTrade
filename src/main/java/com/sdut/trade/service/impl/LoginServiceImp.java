package com.sdut.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.UserInfoDao;
import com.sdut.trade.entity.UserInfo;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.httpmodel.request.LoginRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.LoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：登陆业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
@Component
@Slf4j
public class LoginServiceImp implements LoginService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 登陆请求
     *
     * @param loginRequest
     *
     * @return
     */
    @Override
    public ResponseVO login(LoginRequest loginRequest) {

        ResponseVO responseVO = new ResponseVO();

        UserInfo userInfo = new UserInfo();
        userInfo.setName(loginRequest.getUsername());
        userInfo.setPassword(loginRequest.getPassword());

        if (!userInfoDao.hasUser(userInfo)) {
            responseVO.setResultMsg("账号或密码错误");
            responseVO.setResultCode(200);
        }

        return responseVO;
    }
}
