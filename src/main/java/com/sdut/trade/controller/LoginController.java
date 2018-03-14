/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类描述：登录控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/9
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap modelMap, HttpServletRequest request) {
        return "/login";
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpServletRequest request) {
        return "/index";
    }

}
