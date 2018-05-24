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

import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TransactionDetailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：资金往来部分控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/15
 */
@Slf4j
@Controller
@RequestMapping("transactionDetail")
public class TransactionDetailController {

    @Autowired
    TransactionDetailService transactionDetailService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String transactionDetailController(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        return "/transaction_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseVO getAll() {

        ResponseVO result = new ResponseVO();

        log.info("getAll start");

        try {

            result = transactionDetailService.getAll();

        } catch (Exception ex) {
            log.error("getAll UnKnown ERROR!!!", ex);
        }

        log.info("getAll end");

        return result;
    }

}
