package com.ruoyi.system.service;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.domain.Smellcount;
import generator.ProjcommitExample;
import generator.SmellcountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISmellCountService {

    public List<Smellcount> selectSmellCountById(String projURL);

    public int insertSmell(Smellcount record);

    public List<Smellcount> selectSmellCountByCommit(String projURL, String commitHash);

    public int updateByExampleSelective(@Param("record") Smellcount record, @Param("example") SmellcountExample example);

}
