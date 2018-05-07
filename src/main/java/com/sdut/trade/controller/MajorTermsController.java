/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdut.trade.domain.view.GoodsInfoVO;
import com.sdut.trade.domain.view.ResponseVO;
import com.sdut.trade.service.GoodsInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：常用名词控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/14
 */
@Controller
@RequestMapping("/majorTerms")
@Slf4j
public class MajorTermsController {

    @Autowired
    GoodsInfoService goodsInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String majorTerms(ModelMap modelMap, HttpServletRequest request) {
        return "/major_terms";
    }

    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.GET)
    public ResponseVO getGoodsInfo() {

        log.info("getGoodsInfo start");

        ResponseVO result = goodsInfoService.getAllGoodsInfo();

        log.info("getGoodsInfo success");

        return result;
    }

}
