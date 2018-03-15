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
 * 类描述：资金往来部分控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/15
 */
@Controller
@RequestMapping("transactionDetails")
public class TransactionDetailsController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String TransactionDetailsController(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        return "/transaction_details";
    }

}
