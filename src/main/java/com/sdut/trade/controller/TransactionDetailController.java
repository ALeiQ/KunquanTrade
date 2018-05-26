/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.request.AddDealRequest;
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
    private TransactionDetailService transactionDetailService;

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

    @ResponseBody
    @RequestMapping(value = "/addDeal", method = RequestMethod.POST)
    public ResponseVO addDeal(String params, String bindLogistics) {

        ResponseVO result = new ResponseVO();

        log.info("addLogisticsDetail start [params={}]", params);

        try {

            if (StringUtils.isEmpty(params)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            JSON.parseArray(bindLogistics);

            AddDealRequest addDealRequest = JSON.parseObject(params, AddDealRequest.class);
            addDealRequest.setBind_logistics_id(bindLogistics);

            result = transactionDetailService.addDeal(addDealRequest);

        } catch (MyException ex) {
            log.error("addLogisticsDetail Known error! ", ex);
        } catch (Exception ex) {
            log.error("addLogisticsDetail UnKnown error! ", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("addLogisticsDetail end [params={}]", params);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updateDeal", method = RequestMethod.POST)
    public ResponseVO updateDeal(int dealId, String params, String bindLogistics) {

        ResponseVO result = new ResponseVO();

        log.info("updateDeal start [params={}]", params);

        try {

            if (StringUtils.isEmpty(params)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            JSON.parseArray(bindLogistics);

            AddDealRequest updateDealRequest = JSON.parseObject(params, AddDealRequest.class);
            updateDealRequest.setBind_logistics_id(bindLogistics);

            result = transactionDetailService.updateDeal(dealId, updateDealRequest);

        } catch (MyException ex) {
            log.error("updateDeal Known error! ", ex);
        } catch (Exception ex) {
            log.error("updateDeal UnKnown error! ", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("updateDeal end [params={}]", params);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delDeal", method = RequestMethod.POST)
    public ResponseVO delDeal(Integer delId) {

        log.info("delDeal start id={}", delId);

        ResponseVO result = new ResponseVO();

        try {

            // 写入的数据不可为空
            if (delId == null) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            result = transactionDetailService.delDealById(delId);

        } catch (MyException ex) {
            log.error("delDeal Known Error!" + ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("delDeal Unknown Error!", ex);
        }

        log.info("delDeal end id={}", delId);

        return result;
    }

}
