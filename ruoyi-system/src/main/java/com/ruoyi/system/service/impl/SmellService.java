package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Smell;
import com.ruoyi.system.mapper.SmellMapper;
import com.ruoyi.system.service.ISmellService;
import generator.SmellExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmellService implements ISmellService {
    @Autowired
    private SmellMapper smellMapper;

    @Override
    public List<Smell> selectByCommit(String projURL, String commitHash, String catalog)
    {
        SmellExample example = new SmellExample();
        SmellExample.Criteria criteria = example.createCriteria();
        criteria.andProjurlEqualTo(projURL);
        criteria.andCommithashEqualTo(commitHash);
        criteria.andCatalogEqualTo(catalog);
        return smellMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Smell> selectByName(String projURL, String commitHash, String name)
    {
        SmellExample example = new SmellExample();
        SmellExample.Criteria criteria = example.createCriteria();
        criteria.andProjurlEqualTo(projURL);
        criteria.andCommithashEqualTo(commitHash);
        criteria.andNameEqualTo(name);
        return smellMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int insertSmell(Smell record)
    {
        return smellMapper.insert(record);
    }

}
