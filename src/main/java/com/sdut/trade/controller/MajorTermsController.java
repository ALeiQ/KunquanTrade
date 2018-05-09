/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ListUtils;
import org.thymeleaf.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.BankInfoService;
import com.sdut.trade.service.CompanyInfoService;
import com.sdut.trade.service.GoodsInfoService;
import com.sdut.trade.service.TransportCompanyInfoService;

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

    @Autowired
    BankInfoService bankInfoService;

    @Autowired
    CompanyInfoService companyInfoService;

    @Autowired
    TransportCompanyInfoService transportCompanyInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String majorTerms(ModelMap modelMap, HttpServletRequest request) {
        return "/major_terms";
    }

    /**
     * 获取货物信息
     * @return 货物信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/getTermsInfo", method = RequestMethod.GET)
    public ResponseVO getTermsInfo(String getType) {

        log.info("getGoodsInfo start");

        ResponseVO result = new ResponseVO();

        try {

            if (StringUtils.isEmpty(getType)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            // 根据前端提供的类型，分别进行不同数据表的数据添加。
            switch (getType) {
                case "goodsTable":
                    result = goodsInfoService.getAllGoodsInfo();
                    break;
                case "companysTable":
                    result = companyInfoService.getAllCompanyInfo();
                    break;
                case "transportTable":
                    result = transportCompanyInfoService.getAllTransportCompanyInfo();
                    break;
                case "bankTable":
                    result = bankInfoService.getAllBankInfo();
                    break;
                default:
                    log.error("addTerms 传参错误！ 没有这个类型");
                    result.setResult(ExceptionEnum.PARAM_ERR);
                    break;
            }

        } catch (MyException ex) {
            log.error("getGoodsInfo Known error!", ex);
        } catch (Exception ex) {
            log.error("getGoodsInfo UnKnown error!", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("getGoodsInfo end");

        return result;
    }

    /**
     * 增加多条数据
     * @return 数据写入是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/addTerms", method = RequestMethod.POST)
    public ResponseVO addTerms(String addType, String termsJson) {

        log.info("addTerms start");

        ResponseVO result = new ResponseVO();

        try {

            List<AddTermsRequest> addTermsRequestList = JSONArray.parseArray(termsJson, AddTermsRequest.class);

            // 写入的数据不可为空
            if (StringUtils.isEmpty(addType)
                    || StringUtils.isEmpty(termsJson)
                    || ListUtils.isEmpty(addTermsRequestList)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }


            // 根据前端提供的类型，分别进行不同数据表的数据添加。
            switch (addType) {
                case "goodsTable":
                    result = goodsInfoService.addGoodsInfoBatch(addTermsRequestList);
                    break;
                case "companysTable":
                    result = companyInfoService.addCompanyInfoBatch(addTermsRequestList);
                    break;
                case "transportTable":
                    result = transportCompanyInfoService.addTransportCompanyInfoBatch(addTermsRequestList);
                    break;
                case "bankTable":
                    result = bankInfoService.addBankInfoBatch(addTermsRequestList);
                    break;
                default:
                    log.error("addTerms 传参错误！ 没有这个类型");
                    result.setResult(ExceptionEnum.PARAM_ERR);
                    break;
            }

        } catch (MyException ex) {
            log.error("addTerms Known Error!" + ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("addTerms Unknown Error!" , ex);
        }

        log.info("addTerms end");

        return result;
    }


    /**
     * 删除单条数据
     * @return 数据写入是否成功
     */
    @ResponseBody
    @RequestMapping(value = "/delTerm", method = RequestMethod.POST)
    public ResponseVO delTerm(String delType, String delId) {

        log.info("delTerm start");

        ResponseVO result = new ResponseVO();

        try {

            // 写入的数据不可为空
            if (StringUtils.isEmpty(delType)
                    || StringUtils.isEmpty(delId)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }


            // 根据前端提供的类型，分别进行不同数据表的数据添加。
            switch (delType) {
                case "goodsTable":
                    result = goodsInfoService.delGoodsInfoById(Integer.parseInt(delId));
                    break;
                case "companysTable":
                    result = companyInfoService.delCompanyInfoById(Integer.parseInt(delId));
                    break;
                case "transportTable":
                    result = transportCompanyInfoService.delTransportCompanyInfoById(Integer.parseInt(delId));
                    break;
                case "bankTable":
                    result = bankInfoService.delBankInfoById(Integer.parseInt(delId));
                    break;
                default:
                    log.error("delTerm 传参错误！ 没有这个类型");
                    result.setResult(ExceptionEnum.PARAM_ERR);
                    break;
            }

        } catch (MyException ex) {
            log.error("delTerm Known Error!" + ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("delTerm Unknown Error!" , ex);
        }

        log.info("delTerm end");

        return result;
    }

}


