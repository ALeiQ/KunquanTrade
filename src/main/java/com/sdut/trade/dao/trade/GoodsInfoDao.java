package com.sdut.trade.dao.trade;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sdut.trade.domain.data.GoodsInfoDO;

/**
 * 类描述：货物信息DAO
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/7
 */
@Mapper
@Repository
public interface GoodsInfoDao {

    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "model", column = "MODEL"),
            @Result(property = "createDate", column = "CREATE_DATE"),
            @Result(property = "delete_date", column = "DELETE_DATE"),
            @Result(property = "enable", column = "ENABLE")
    })
    @Select("SELECT *"
            + " FROM"
            + " GOODS_INFO")
    List<GoodsInfoDO> getAll();

}
