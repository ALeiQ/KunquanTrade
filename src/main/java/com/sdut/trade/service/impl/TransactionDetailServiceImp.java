package com.sdut.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.bean.DealDetailVO;
import com.sdut.trade.dao.DealDetailDao;
import com.sdut.trade.entity.DealDetail;
import com.sdut.trade.enums.impl.DealTypeEnum;
import com.sdut.trade.enums.impl.DealWayEnum;
import com.sdut.trade.httpmodel.response.ResponseVO;
import com.sdut.trade.service.TransactionDetailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：资金往来业务层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/24
 */
@Component
@Slf4j
public class TransactionDetailServiceImp implements TransactionDetailService {

    @Autowired
    DealDetailDao dealDetailDao;

    /**
     * 获取所有资金往来记录
     *
     * @return
     */
    @Override
    public ResponseVO getAll() {

        ResponseVO responseVO = new ResponseVO();

        List<DealDetail> dealDetailList = dealDetailDao.getAll();
        List<DealDetailVO> dealDetailVOList = new ArrayList<>();

        for (DealDetail dealDetail : dealDetailList) {

            DealDetailVO dealDetailVO = new DealDetailVO();

            dealDetailVO.setId(dealDetail.getId());
            dealDetailVO.setDate(dealDetail.getDate());
            dealDetailVO.setAmount(dealDetail.getAmount());
            dealDetailVO.setType(dealDetail.getType());
            dealDetailVO.setTypeDesc(DealTypeEnum.getDesc(dealDetail.getType()));
            dealDetailVO.setWay(dealDetail.getWay());
            dealDetailVO.setWayDesc(DealWayEnum.getDesc(dealDetail.getWay()));
            dealDetailVO.setCompany(dealDetail.getCompany());
            dealDetailVO.setWechatPayAccount(dealDetail.getWechatPayAccount());
            dealDetailVO.setWechatReceiveAccount(dealDetail.getWechatReceiveAccount());
            dealDetailVO.setBankPayAccount(dealDetail.getBankPayAccount());
            dealDetailVO.setBankReceiveAccount(dealDetail.getBankReceiveAccount());
            dealDetailVO.setBankName(dealDetail.getBankName());
            dealDetailVO.setCheckReceivePeople(dealDetail.getCheckReceivePeople());
            dealDetailVO.setCheckPayPeople(dealDetail.getCheckPayPeople());
            dealDetailVO.setCheckNumber(dealDetail.getCheckNumber());
            dealDetailVO.setCheckDate(dealDetail.getCheckDate());
            dealDetailVO.setCheckDeadLine(dealDetail.getCheckDeadline());

            dealDetailVOList.add(dealDetailVO);
        }

        responseVO.setData(dealDetailVOList);
        return responseVO;
    }
}
