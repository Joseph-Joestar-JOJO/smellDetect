package com.ruoyi.system.service;

import com.ruoyi.system.domain.Smell;

import java.util.List;

public interface ISmellService {

    public List<Smell> selectByCommit(String projURL, String commitHash, String catalog);
    public int insertSmell(Smell record);
    public List<Smell> selectByName(String projURL, String commitHash, String name);
}
