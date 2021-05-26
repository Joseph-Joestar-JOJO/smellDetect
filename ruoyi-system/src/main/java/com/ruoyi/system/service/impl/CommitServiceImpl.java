package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.mapper.ProjcommitMapper;
import com.ruoyi.system.service.ICommitService;
import generator.ProjcommitExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitServiceImpl implements ICommitService {
    @Autowired
    private ProjcommitMapper projcommitMapper;

    @Override
    public List<Projcommit> selectCommitById(int projid)
    {
        ProjcommitExample example = new ProjcommitExample();
        ProjcommitExample.Criteria criteria = example.createCriteria();
        criteria.andProjidEqualTo(projid);
        return projcommitMapper.selectByExample(example);
    }

    @Override
    public int insertCommit(Projcommit record)
    {
        return projcommitMapper.insert(record);
    }

    @Override
    public List<Projcommit> selectCommitAll()
    {
        return projcommitMapper.selectCommitAll();
    }

    @Override
    public List<Projcommit> selectCommitList(Projcommit projcommit)
    {
        return projcommitMapper.selectCommitList(projcommit);
    }

    @Override
    public int updateCommit(Projcommit record, ProjcommitExample example)
    {
        return projcommitMapper.updateByExample(record, example);
    }

    public int updateByExample(@Param("record") Projcommit record, @Param("example") ProjcommitExample example)
    {
        return projcommitMapper.updateByExample(record, example);
    }

    @Override
    public List<Projcommit> selectByExample(ProjcommitExample example)
    {
        return projcommitMapper.selectByExample(example);
    }

    public int updateByExampleSelective(@Param("record") Projcommit record, @Param("example") ProjcommitExample example)
    {
        return projcommitMapper.updateByExampleSelective(record, example);
    }
}
