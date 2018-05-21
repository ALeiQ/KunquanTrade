/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.request.AddInvoiceDetailRequest;
import com.sdut.trade.httpmodel.request.AddInvoiceRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票明细部分控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/15
 */
@Controller
@RequestMapping("/invoice")
@Slf4j
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String invoice(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        return "/invoice";
    }

    /**
     * 获取指定流向的发票
     * @param direction
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllByDirection", method = RequestMethod.GET)
    public ResponseVO getAllByDirection(Integer direction) {

        ResponseVO result = new ResponseVO();

        log.info("getByDirection start direction={}", direction);

        try {

            if (direction == null) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            // 0.全查询 1.开进 2.开出 3.中转
            if (direction < 0 || direction > 3) {
                result.setResult(ExceptionEnum.PARAM_ERR);
                throw new MyException(ExceptionEnum.PARAM_ERR.getDesc());
            }

            result = invoiceService.getAllByDirection(direction);

        } catch (MyException ex) {
            log.error("getByDirection Known Error!", ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("getByDirection UnKnown Error!", ex);
        }

        log.info("getByDirection end direction={}", direction);

        return result;
    }

    /**
     * 获取指定ID的开票详情
     * @param queryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getInvoiceDetailsById", method = RequestMethod.GET)
    public ResponseVO getInvoiceDetailsById(Integer queryId) {

        ResponseVO result = new ResponseVO();

        log.info("getInvoiceDetailsById start queryId={}", queryId);

        try {

            if (queryId == null) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            result = invoiceService.getInvoiceDetailsById(queryId);

        } catch (MyException ex) {
            log.error("getInvoiceDetailsById Known Error!", ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("getInvoiceDetailsById UnKnown Error!", ex);
        }

        log.info("getInvoiceDetailsById end queryId={}", queryId);

        return result;
    }

    /**
     * 添加发票信息
     * @param params
     * @param details
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addInvoice", method = RequestMethod.POST)
    public ResponseVO addInvoice(String params, String details) {

        ResponseVO result = new ResponseVO();

        log.info("addInvoice start [params={}], [details={}]", params, details);

        try {

            if (StringUtils.isEmpty(params) || StringUtils.isEmpty(details)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            AddInvoiceRequest addInvoiceRequest = JSON.parseObject(params, AddInvoiceRequest.class);
            List<AddInvoiceDetailRequest> detailList = JSONArray.parseArray(details,
                    AddInvoiceDetailRequest.class);

            result = invoiceService.addInvoice(addInvoiceRequest, detailList);

        } catch (MyException ex) {
            log.info("addInvoice Known error! ", ex);
        } catch (Exception ex) {
            log.info("addInvoice UnKnown error! ", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("addInvoice end [params={}], [details={}]", params, details);

        return result;
    }
}
