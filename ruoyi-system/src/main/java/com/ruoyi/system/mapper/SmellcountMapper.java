package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Smellcount;
import generator.SmellcountExample;
import generator.SmellcountKey;
import org.apache.ibatis.annotations.Param;

public interface SmellcountMapper {
    long countByExample(SmellcountExample example);

    int deleteByExample(SmellcountExample example);

    int deleteByPrimaryKey(SmellcountKey key);

    int insert(Smellcount record);

    int insertSelective(Smellcount record);

    List<Smellcount> selectByExample(SmellcountExample example);

    List<Smellcount> selectByPrimaryKey(SmellcountKey key);

    int updateByExampleSelective(@Param("record") Smellcount record, @Param("example") SmellcountExample example);

    int updateByExample(@Param("record") Smellcount record, @Param("example") SmellcountExample example);

    int updateByPrimaryKeySelective(Smellcount record);

    int updateByPrimaryKey(Smellcount record);
}