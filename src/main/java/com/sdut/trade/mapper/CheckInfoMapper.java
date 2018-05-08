package com.sdut.trade.mapper;

import com.sdut.trade.entity.CheckInfo;
import com.sdut.trade.entity.CheckInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckInfoMapper {
    int countByExample(CheckInfoExample example);

    int deleteByExample(CheckInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckInfo record);

    int insertSelective(CheckInfo record);

    List<CheckInfo> selectByExample(CheckInfoExample example);

    CheckInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckInfo record, @Param("example") CheckInfoExample example);

    int updateByExample(@Param("record") CheckInfo record, @Param("example") CheckInfoExample example);

    int updateByPrimaryKeySelective(CheckInfo record);

    int updateByPrimaryKey(CheckInfo record);
}