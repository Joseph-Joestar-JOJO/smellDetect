package com.ruoyi.system.service;

import com.ruoyi.system.domain.Projcommit;
import generator.ProjcommitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICommitService {
    public List<Projcommit> selectCommitById(int projid);

    public List<Projcommit> selectByExample(ProjcommitExample example);

    public List<Projcommit> selectCommitAll();

    public List<Projcommit> selectCommitList(Projcommit projcommit);

    public int insertCommit(Projcommit record);

    public int updateCommit(Projcommit record, ProjcommitExample example);

    public int updateByExample(@Param("record") Projcommit record, @Param("example") ProjcommitExample example);

    public int updateByExampleSelective(@Param("record") Projcommit record, @Param("example") ProjcommitExample example);

}
