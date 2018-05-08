package com.sdut.trade.mapper;

import com.sdut.trade.entity.TransportCompanyInfo;
import com.sdut.trade.entity.TransportCompanyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransportCompanyInfoMapper {
    int countByExample(TransportCompanyInfoExample example);

    int deleteByExample(TransportCompanyInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransportCompanyInfo record);

    int insertSelective(TransportCompanyInfo record);

    List<TransportCompanyInfo> selectByExample(TransportCompanyInfoExample example);

    TransportCompanyInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransportCompanyInfo record, @Param("example") TransportCompanyInfoExample example);

    int updateByExample(@Param("record") TransportCompanyInfo record, @Param("example") TransportCompanyInfoExample example);

    int updateByPrimaryKeySelective(TransportCompanyInfo record);

    int updateByPrimaryKey(TransportCompanyInfo record);
}