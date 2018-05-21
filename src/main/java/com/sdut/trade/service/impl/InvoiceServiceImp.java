package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.sdut.trade.bean.InvoiceDetailVO;
import com.sdut.trade.bean.InvoiceInfoVO;
import com.sdut.trade.dao.InvoiceDao;
import com.sdut.trade.dao.InvoiceDetailDao;
import com.sdut.trade.entity.InvoiceDetail;
import com.sdut.trade.entity.InvoiceInfo;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.enums.impl.InvoiceInfoDircetionEnum;
import com.sdut.trade.enums.impl.InvoiceInfoTypeEnum;
import com.sdut.trade.enums.impl.ResultEnum;
import com.sdut.trade.httpmodel.request.AddInvoiceDetailRequest;
import com.sdut.trade.httpmodel.request.AddInvoiceRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票明细业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Component
@Slf4j
public class InvoiceServiceImp implements InvoiceService {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    InvoiceDetailDao invoiceDetailDao;

    /**
     * 根据开票流向获取全数据
     *
     * @param direction 0.全部 1.进项 2.销项 3.中转
     *
     * @return
     */
    @Override
    public ResponseVO getAllByDirection(Integer direction) {

        ResponseVO responseVO = new ResponseVO();

        List<InvoiceInfoVO> invoiceInfoVOS = new ArrayList<>();

        List<InvoiceInfo> invoiceInfoList;

        if (direction == 0) {
            invoiceInfoList = invoiceDao.getAll();
        } else {
            invoiceInfoList = invoiceDao.getAllByDirection(direction);
        }

        for (InvoiceInfo invoiceInfo : invoiceInfoList) {

            InvoiceInfoVO invoiceInfoVO = new InvoiceInfoVO();

            invoiceInfoVO.setId(invoiceInfo.getId());
            invoiceInfoVO.setNumber(invoiceInfo.getNumber());
            invoiceInfoVO.setAmount(invoiceInfo.getAmount());
            invoiceInfoVO.setMakeDate(invoiceInfo.getMakeDate());
            invoiceInfoVO.setUseDate(invoiceInfo.getUseDate());
            invoiceInfoVO.setPayCompany(invoiceInfo.getPayCompany());
            invoiceInfoVO.setReceiveCompany(invoiceInfo.getReceiveCompany());
            invoiceInfoVO.setRemark(invoiceInfo.getRemark());
            invoiceInfoVO.setDirection(invoiceInfo.getDirection());
            invoiceInfoVO.setType(invoiceInfo.getType());
            invoiceInfoVO.setDirectionDesc(InvoiceInfoDircetionEnum.getDesc(invoiceInfo.getDirection()));
            invoiceInfoVO.setTypeDesc(InvoiceInfoTypeEnum.getDesc(invoiceInfo.getType()));

            invoiceInfoVOS.add(invoiceInfoVO);
        }

        responseVO.setData(invoiceInfoVOS);
        return responseVO;
    }

    /**
     * 根据票据id获取票据详细内容
     *
     * @param queryId
     *
     * @return
     */
    @Override
    public ResponseVO getInvoiceDetailsById(Integer queryId) {

        ResponseVO responseVO = new ResponseVO();

        List<InvoiceDetailVO> invoiceDetailVOS = new ArrayList<>();
        List<InvoiceDetail> invoiceDetailList = invoiceDetailDao.getAllByInvoiceId(queryId);

        for (InvoiceDetail invoiceDetail : invoiceDetailList) {

            InvoiceDetailVO invoiceDetailVO = new InvoiceDetailVO();

            invoiceDetailVO.setId(invoiceDetail.getId());
            invoiceDetailVO.setGoodsName(invoiceDetail.getGoodsName());
            invoiceDetailVO.setGoodsModel(invoiceDetail.getGoodsModel());
            invoiceDetailVO.setNumber(invoiceDetail.getNumber());
            invoiceDetailVO.setUnitPrice(invoiceDetail.getUnitPrice());
            invoiceDetailVO.setSumPrice(invoiceDetail.getSumPricet());
            invoiceDetailVO.setTax(invoiceDetail.getTax());

            invoiceDetailVOS.add(invoiceDetailVO);
        }

        responseVO.setData(invoiceDetailVOS);
        return responseVO;
    }

    /**
     * 添加开票信息
     *
     * @param addInvoiceRequest 开票信息
     * @param detailList 票据明细
     *
     * @return
     */
    @Override
    public ResponseVO addInvoice(AddInvoiceRequest addInvoiceRequest, List<AddInvoiceDetailRequest> detailList) {

        ResponseVO responseVO = new ResponseVO();

        log.info("addInvoice start [addInvoiceRequest={}], [detailList={}]", addInvoiceRequest, detailList);

        Date createDate = new Date();

        InvoiceInfo invoiceInfo = parseRequsetToModel(addInvoiceRequest, createDate);

        int id = invoiceDao.addInvoiceInfo(invoiceInfo);

        List<InvoiceDetail> invoiceDetailList = parseRequsetDetailToModel(id, detailList, createDate);

        int addNum = invoiceDetailDao.addInvoiceDetails(invoiceDetailList);

        if (addNum != invoiceDetailList.size()) {
            responseVO.setResult(ResultEnum.FAILURE);
            responseVO.setResultMsg("名次添加失败！"
                    + "[需要添加: " + Integer.toString(invoiceDetailList.size()) +" 条]"
                    + "[实际添加: " + Integer.toString(addNum) + " 条]");

            log.error("addInvoice add to DB less than need! [need = {}][real = {}]",
                    invoiceDetailList.size(), addNum);

            return responseVO;
        }

        log.info("addInvoice end [addInvoiceRequest={}], [detailList={}]", addInvoiceRequest, detailList);

        return responseVO;
    }

    /**
     * 添加开票信息附属详情
     * @param id
     * @param detailList
     * @param createDate
     * @return
     */
    private List<InvoiceDetail> parseRequsetDetailToModel(int id, List<AddInvoiceDetailRequest> detailList, Date createDate) {

        List<InvoiceDetail> invoiceDetailList = new ArrayList<>();

        for (AddInvoiceDetailRequest addInvoiceDetailRequest : detailList) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();

            invoiceDetail.setUnitPrice(addInvoiceDetailRequest.getUnitPrice());
            invoiceDetail.setTax(addInvoiceDetailRequest.getTax());
            invoiceDetail.setSumPricet(addInvoiceDetailRequest.getSumPrice());
            invoiceDetail.setNumber(addInvoiceDetailRequest.getNumber());
            invoiceDetail.setInvoiceId(id);
            invoiceDetail.setGoodsName(addInvoiceDetailRequest.getGoodsName());
            invoiceDetail.setGoodsModel(addInvoiceDetailRequest.getGoodsModel());
            invoiceDetail.setEnable(EnableEnum.ENABLE.isValue());
            invoiceDetail.setCreateDate(createDate);

            invoiceDetailList.add(invoiceDetail);
        }

        return invoiceDetailList;
    }

    /**
     * 解析开票请求到票务信息对象
     * @param addInvoiceRequest
     * @param createDate
     * @return
     */
    private InvoiceInfo parseRequsetToModel(AddInvoiceRequest addInvoiceRequest, Date createDate) {

        InvoiceInfo invoiceInfo = new InvoiceInfo();

        String pay = addInvoiceRequest.getPayCompany();
        String receive = addInvoiceRequest.getReceiveCompany();

        if (StringUtils.isEmpty(pay)) {
            if (StringUtils.isEmpty(receive)) {
                // 均空
                invoiceInfo.setDirection(InvoiceInfoDircetionEnum.TRANSFER.getValue());
            } else {
                // 仅有受票方
                invoiceInfo.setDirection(InvoiceInfoDircetionEnum.OUTPUT.getValue());
            }
        } else if(StringUtils.isEmpty(receive)){
            // 仅有开票方
            invoiceInfo.setDirection(InvoiceInfoDircetionEnum.INPUT.getValue());
        } else {
            // 均有
            invoiceInfo.setDirection(InvoiceInfoDircetionEnum.TRANSFER.getValue());
        }

        invoiceInfo.setUseDate(addInvoiceRequest.getUseDate());
        invoiceInfo.setType(addInvoiceRequest.getType());
        invoiceInfo.setRemark(addInvoiceRequest.getRemark());
        invoiceInfo.setReceiveCompany(addInvoiceRequest.getReceiveCompany());
        invoiceInfo.setPayCompany(addInvoiceRequest.getPayCompany());
        invoiceInfo.setNumber(addInvoiceRequest.getNumber());
        invoiceInfo.setMakeDate(addInvoiceRequest.getMakeDate());
        invoiceInfo.setEnable(EnableEnum.ENABLE.isValue());
        invoiceInfo.setCreateDate(createDate);
        invoiceInfo.setAmount(addInvoiceRequest.getAmount());

        return invoiceInfo;
    }
}
