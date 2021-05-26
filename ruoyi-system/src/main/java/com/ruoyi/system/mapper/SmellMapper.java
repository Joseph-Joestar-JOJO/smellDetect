package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Smell;
import generator.SmellExample;
import generator.SmellKey;
import org.apache.ibatis.annotations.Param;

public interface SmellMapper {
    long countByExample(SmellExample example);

    int deleteByExample(SmellExample example);

    int deleteByPrimaryKey(SmellKey key);

    int insert(Smell record);

    int insertSelective(Smell record);

    List<Smell> selectByExampleWithBLOBs(SmellExample example);

    List<Smell> selectByExample(SmellExample example);

    Smell selectByPrimaryKey(SmellKey key);

    int updateByExampleSelective(@Param("record") Smell record, @Param("example") SmellExample example);

    int updateByExampleWithBLOBs(@Param("record") Smell record, @Param("example") SmellExample example);

    int updateByExample(@Param("record") Smell record, @Param("example") SmellExample example);

    int updateByPrimaryKeySelective(Smell record);

    int updateByPrimaryKeyWithBLOBs(Smell record);

    int updateByPrimaryKey(Smell record);
}