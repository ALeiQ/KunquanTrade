package com.sdut.trade.mapper;

import com.sdut.trade.entity.CheckDetails;
import com.sdut.trade.entity.CheckDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckDetailsMapper {
    int countByExample(CheckDetailsExample example);

    int deleteByExample(CheckDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckDetails record);

    int insertSelective(CheckDetails record);

    List<CheckDetails> selectByExample(CheckDetailsExample example);

    CheckDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckDetails record, @Param("example") CheckDetailsExample example);

    int updateByExample(@Param("record") CheckDetails record, @Param("example") CheckDetailsExample example);

    int updateByPrimaryKeySelective(CheckDetails record);

    int updateByPrimaryKey(CheckDetails record);
}