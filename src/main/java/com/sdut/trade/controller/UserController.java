/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.sdut.trade.annotation.LoginRequired;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.request.LoginRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：登录控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/9
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap, HttpServletRequest request) {
        return "/login";
    }

    @LoginRequired
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(ModelMap modelMap, HttpServletRequest request) {
        return "/change_password";
    }

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public ResponseVO doLogin(String param, HttpServletRequest request) {

        ResponseVO result = new ResponseVO();

        log.info("doLogin start param={}", param);

        try {

            if (StringUtils.isEmpty(param)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            LoginRequest loginRequest = JSON.parseObject(param, LoginRequest.class);

            result = userService.login(loginRequest, request);

        } catch (MyException ex) {
            log.error("doLogin Known Error param={}", param, ex);
        } catch (Exception ex) {
            log.error("doLogin UnKnown Error param={}", param, ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("doLogin end param={}", param);

        return result;
    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ResponseVO doLogout(HttpServletRequest request) {

        ResponseVO responseVO = new ResponseVO();

        log.info("doLogout start");

        request.getSession().removeAttribute("token");
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("username");

        log.info("doLogout end");

        return responseVO;
    }

    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
    public ResponseVO doChangePassword(HttpServletRequest request, String oldpasswd, String newpasswd1, String
            newpasswd2) {

        ResponseVO result = new ResponseVO();

        log.info("doChangePassword start oldpasswd={}, newpasswd1={}, newpasswd2={}", oldpasswd, newpasswd1,
                newpasswd2);

        try {

            if (StringUtils.isEmpty(oldpasswd)
                    || StringUtils.isEmpty(newpasswd1)
                    || StringUtils.isEmpty(newpasswd1)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            if (!StringUtils.equals(newpasswd1, newpasswd2)) {
                result.setResultMsg("两次输入的密码不相同");
                result.setResultCode(203);
                throw new MyException("两次输入的密码不相同");
            }

            int userId = (int)request.getSession().getAttribute("userId");
            String token = (String)request.getSession().getAttribute("token");
            result = userService.changePassword(userId, token, oldpasswd, newpasswd1, newpasswd2);

        } catch (MyException ex) {
            log.error("doChangePassword Known ERROR", ex);
        } catch (Exception ex) {
            log.error("doChangePassword UnKnown ERROR", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("doChangePassword end");
        return result;
    }

}
