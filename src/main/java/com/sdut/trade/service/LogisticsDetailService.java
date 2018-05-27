package com.sdut.trade.service;

import java.util.List;

import com.sdut.trade.httpmodel.request.AddLogisticsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：运输明细业务层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
public interface LogisticsDetailService {

    /**
     * 添加单条运输明细数据
     *
     * @param addLogisticsRequest 待处理添加的原生数据（前端传来）
     *
     * @return
     */
    ResponseVO addLogisticsDetail(AddLogisticsRequest addLogisticsRequest);

    /**
     * 查询全部运输明细
     *
     * @return
     */
    ResponseVO getAll();

    /**
     * 查询Ids的运输明细
     *
     * @param ids
     *
     * @return
     */
    ResponseVO getByIds(List<Integer> ids);

    /**
     * 修改给定id的运输明细信息
     *
     * @param id
     * @param addLogisticsRequest
     *
     * @return
     */
    ResponseVO updateLogisticsDetail(int id, AddLogisticsRequest addLogisticsRequest);

    /**
     * 删除单条运输明细信息
     *
     * @param delId
     *
     * @return
     */
    ResponseVO delLogisticsDetail(Integer delId);
}
