/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
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

    /**
     * 获取货物信息
     * @return 货物信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.GET)
    public ResponseVO getGoodsInfo() {

        log.info("getGoodsInfo start");

        ResponseVO result = new ResponseVO();

        try {
            result = goodsInfoService.getAllGoodsInfo();
        } catch (Exception ex) {
            log.error("getGoodsInfo error!", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("getGoodsInfo end");

        return result;
    }

    /**
     * 增加数据
     * @return 数据写入是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/addTerms", method = RequestMethod.POST)
    public ResponseVO addTerms(String addType, String termsJson) {

        log.info("addTerms start");

        ResponseVO result = new ResponseVO();

        try {

            // 写入的数据不可为空
            if (StringUtils.isEmpty(addType) || StringUtils.isEmpty(termsJson)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            List<AddTermsRequest> addTermsRequestList = JSON.parseArray(termsJson, AddTermsRequest.class);

            // 根据前端提供的类型，分别进行不同数据表的数据添加。
            switch (addType) {
                case "goodsTable":
                    result = goodsInfoService.addGoodsInfo(addTermsRequestList);
                    break;
                case "companysTable":
                    break;
                case "transportTable":
                    break;
                case "bankTable":
                    break;
                default:
                    log.error("add Terms 传参错误！ 没有这个类型");
                    result.setResult(ExceptionEnum.PARAM_ERR);
                    break;
            }

        } catch (MyException ex) {
            log.error("addTerms Error!" + ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("addTerms Error!" , ex);
        }

        log.info("addTerms end");

        return result;
    }

}


