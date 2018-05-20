package com.sdut.trade.service;

import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：开票明细业务层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
public interface InvoiceService {

    /**
     * 根据开票流向获取全数据
     * @param direction 0.全部 1.进项 2.销项 3.中转
     * @return
     */
    ResponseVO getAllByDirection(Integer direction);

    /**
     * 根据票据id获取票据详细内容
     * @param queryId
     * @return
     */
    ResponseVO getInvoiceDetailsById(Integer queryId);
}
