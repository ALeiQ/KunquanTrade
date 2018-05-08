package com.sdut.trade.mapper;

import com.sdut.trade.entity.DealDetails;
import com.sdut.trade.entity.DealDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DealDetailsMapper {
    int countByExample(DealDetailsExample example);

    int deleteByExample(DealDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DealDetails record);

    int insertSelective(DealDetails record);

    List<DealDetails> selectByExample(DealDetailsExample example);

    DealDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DealDetails record, @Param("example") DealDetailsExample example);

    int updateByExample(@Param("record") DealDetails record, @Param("example") DealDetailsExample example);

    int updateByPrimaryKeySelective(DealDetails record);

    int updateByPrimaryKey(DealDetails record);
}