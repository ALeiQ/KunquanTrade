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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sdut.trade.annotation.LoginRequired;
import com.sdut.trade.enums.impl.ExceptionEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.exception.MyException;
import com.sdut.trade.httpmodel.request.AddLogisticsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.CompanyInfoService;
import com.sdut.trade.service.GoodsInfoService;
import com.sdut.trade.service.LogisticsCompanyInfoService;
import com.sdut.trade.service.LogisticsDetailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：运输明细部分控制层
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/15
 */
@Controller
@RequestMapping("/logistics")
@Slf4j
public class LogisticsController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @Autowired
    private LogisticsCompanyInfoService logisticsCompanyInfoService;

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private LogisticsDetailService logisticsDetailService;

    @LoginRequired
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String logistics(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        return "/logistics";
    }

    /**
     * 获取运输明细详情数据
     *
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/getLogisticsDetails", method = RequestMethod.GET)
    public ResponseVO getLogisticsDetails() {

        log.info("getLogisticsDetail start");

        ResponseVO result = new ResponseVO();

        try {
            result = logisticsDetailService.getAll();
        } catch (Exception ex) {
            log.error("getLogisticsDetail UnKnown error!", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("getLogisticsDetail end");

        return result;
    }

    /**
     * 获取id列表的所有运输明细详情数据
     *
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/getLogisticsDetailsByIds", method = RequestMethod.GET)
    public ResponseVO getLogisticsDetailsById(String params) {

        log.info("getLogisticsDetailsById start, params={}", params);

        ResponseVO result = new ResponseVO();

        try {

            if (StringUtils.isEmpty(params)) {
                return result;
            }

            List<Integer> ids = JSON.parseArray(params, Integer.class);

            result = logisticsDetailService.getByIds(ids);
        } catch (Exception ex) {
            log.error("getLogisticsDetailById UnKnown error!, params={}", params, ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("getLogisticsDetailsById end, params={}", params);

        return result;
    }

    /**
     * 添加单条运输明细
     *
     * @param params
     *
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/addLogisticsDetail", method = RequestMethod.POST)
    public ResponseVO addLogisticsDetail(String params) {

        ResponseVO result = new ResponseVO();

        log.info("addLogisticsDetail start [params={}]", params);

        try {

            if (StringUtils.isEmpty(params)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            AddLogisticsRequest addLogisticsRequest = JSON.parseObject(params, AddLogisticsRequest.class);

            result = logisticsDetailService.addLogisticsDetail(addLogisticsRequest);

        } catch (MyException ex) {
            log.error("addLogisticsDetail Known error! ", ex);
        } catch (Exception ex) {
            log.error("addLogisticsDetail UnKnown error! ", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("addLogisticsDetail end [params={}]", params);

        return result;
    }

    /**
     * 更新单条运输明细
     *
     * @param id
     * @param params
     *
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/updateLogisticsDetail", method = RequestMethod.POST)
    public ResponseVO updateLogisticsDetail(int id, String params) {

        ResponseVO result = new ResponseVO();

        log.info("updateLogisticsDetail start [id = {}], [params = {}]", id, params);

        try {

            if (StringUtils.isEmpty(params)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            AddLogisticsRequest addLogisticsRequest = JSON.parseObject(params, AddLogisticsRequest.class);

            result = logisticsDetailService.updateLogisticsDetail(id, addLogisticsRequest);

        } catch (MyException ex) {
            log.error("updateLogisticsDetail Known error! ", ex);
        } catch (Exception ex) {
            log.error("updateLogisticsDetail UnKnown error! ", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("updateLogisticsDetail end [id = {}], [params = {}]", id, params);

        return result;
    }

    /**
     * 删除单条数据
     *
     * @return 数据写入是否成功
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/delLogisticsDetail", method = RequestMethod.POST)
    public ResponseVO delLogisticsDetail(Integer delId) {

        log.info("delLogisticsDetail start");

        ResponseVO result = new ResponseVO();

        try {

            // 写入的数据不可为空
            if (delId == null) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            result = logisticsDetailService.delLogisticsDetail(delId);

        } catch (MyException ex) {
            log.error("delLogisticsDetail Known Error!" + ex);
        } catch (Exception ex) {
            result.setResult(ResultEnum.FAILURE);
            log.error("delLogisticsDetail Unknown Error!", ex);
        }

        log.info("delLogisticsDetail end");

        return result;
    }
}
