package com.sdut.trade.mapper;

import com.sdut.trade.entity.TransportDetails;
import com.sdut.trade.entity.TransportDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransportDetailsMapper {
    int countByExample(TransportDetailsExample example);

    int deleteByExample(TransportDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransportDetails record);

    int insertSelective(TransportDetails record);

    List<TransportDetails> selectByExample(TransportDetailsExample example);

    TransportDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransportDetails record, @Param("example") TransportDetailsExample example);

    int updateByExample(@Param("record") TransportDetails record, @Param("example") TransportDetailsExample example);

    int updateByPrimaryKeySelective(TransportDetails record);

    int updateByPrimaryKey(TransportDetails record);
}