package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Projcommit;
import generator.ProjcommitExample;
import generator.ProjcommitKey;
import org.apache.ibatis.annotations.Param;

public interface ProjcommitMapper {
    long countByExample(ProjcommitExample example);

    int deleteByExample(ProjcommitExample example);

    int deleteByPrimaryKey(ProjcommitKey key);

    int insert(Projcommit record);

    int insertSelective(Projcommit record);

    List<Projcommit> selectByExample(ProjcommitExample example);

    Projcommit selectByPrimaryKey(ProjcommitKey key);

    int updateByExampleSelective(@Param("record") Projcommit record, @Param("example") ProjcommitExample example);

    int updateByExample(@Param("record") Projcommit record, @Param("example") ProjcommitExample example);

    int updateByPrimaryKeySelective(Projcommit record);

    int updateByPrimaryKey(Projcommit record);
    List<Projcommit> selectCommitAll();
    List<Projcommit> selectCommitList(Projcommit record);


}