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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String logistics(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        return "/logistics";
    }

    /**
     * 运输明细页面下拉框数据获取
     * @param getType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTypeaheadData", method = RequestMethod.GET)
    public ResponseVO getTypeaheadData(String getType, String query, String goodsName) {

        log.info("getTypeaheadData start [type={}, query={}, goodsName={}]", getType, query, goodsName);

        ResponseVO result = new ResponseVO();

        try {

            if (StringUtils.isEmpty(getType)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            // 根据前端提供的类型，分别进行不同数据表的数据添加。
            switch (getType) {
                case "goodsFrom":
                    result = companyInfoService.getCompanyByKeyword(query);
                    break;
                case "buyerCompany":
                    result = companyInfoService.getCompanyByKeyword(query);
                    break;
                case "transCompany":
                    result = logisticsCompanyInfoService.getLogisticsCompanyByKeyword(query);
                    break;
                case "goodsName":
                    result = goodsInfoService.getGoodsNameByKeyword(query);
                    break;
                case "goodsModel":
                    result = goodsInfoService.getGoodsModelByKeyword(query, goodsName);
                    break;
                default:
                    log.error("addTerms 传参错误！ 没有这个类型");
                    result.setResult(ExceptionEnum.PARAM_ERR);
                    break;
            }

        } catch (MyException ex) {
            log.info("getTermsInfo Known error! [type={}]", getType, ex);
        } catch (Exception ex) {
            log.info("getTermsInfo UnKnown error! [type={}]", getType, ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("getTypeaheadData end [type={}, query={}, goodsName={}]", getType, query, goodsName);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/addLogisticsDetail", method = RequestMethod.POST)
    public ResponseVO addLogisticsDetail(String params) {

        ResponseVO result = new ResponseVO();

        log.info("addTypeaheadData start [params={}]", params);

        try {

            if (StringUtils.isEmpty(params)) {
                result.setResult(ExceptionEnum.PARAM_EMPTY);
                throw new MyException(ExceptionEnum.PARAM_EMPTY.getDesc());
            }

            AddLogisticsRequest addLogisticsRequest = JSON.parseObject(params, AddLogisticsRequest.class);

            result = logisticsDetailService.addLogisticsDetail(addLogisticsRequest);

        } catch (MyException ex) {
            log.info("getTermsInfo Known error! ", ex);
        } catch (Exception ex) {
            log.info("getTermsInfo UnKnown error! ", ex);
            result.setResult(ResultEnum.FAILURE);
        }

        log.info("addTypeaheadData end [params={}]", params);

        return result;
    }

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

    };

}
