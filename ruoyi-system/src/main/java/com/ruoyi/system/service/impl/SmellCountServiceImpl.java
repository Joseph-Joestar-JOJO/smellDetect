package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Smellcount;
import com.ruoyi.system.mapper.SmellcountMapper;
import com.ruoyi.system.service.ISmellCountService;
import generator.SmellcountExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmellCountServiceImpl implements ISmellCountService {
    @Autowired
    private SmellcountMapper smellCountMapper;

    @Override
    public List<Smellcount> selectSmellCountById(String projURL)
    {
        SmellcountExample example = new SmellcountExample();
        SmellcountExample.Criteria criteria = example.createCriteria();
        criteria.andProjurlEqualTo(projURL);
        return smellCountMapper.selectByExample(example);
    }

    @Override
    public int insertSmell(Smellcount record)
    {
        return smellCountMapper.insert(record);
    }

    @Override
    public List<Smellcount> selectSmellCountByCommit(String projURL, String commitHash)
    {
        SmellcountExample example = new SmellcountExample();
        SmellcountExample.Criteria criteria = example.createCriteria();
        criteria.andProjurlEqualTo(projURL);
        criteria.andCommithashEqualTo(commitHash);
        return smellCountMapper.selectByExample(example);

    }

    @Override
    public int updateByExampleSelective(@Param("record") Smellcount record, @Param("example") SmellcountExample example)
    {
        return smellCountMapper.updateByExampleSelective(record, example);
    }
}
