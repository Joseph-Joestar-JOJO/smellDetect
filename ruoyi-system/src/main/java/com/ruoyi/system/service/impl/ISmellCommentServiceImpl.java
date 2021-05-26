package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.domain.Smellcomment;
import com.ruoyi.system.mapper.SmellcommentMapper;
import com.ruoyi.system.mapper.SmellcountMapper;
import com.ruoyi.system.service.ISmellCommentService;
import com.ruoyi.system.service.ISmellCountService;
import com.ruoyi.system.service.ISmellService;
import generator.SmellcommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISmellCommentServiceImpl implements ISmellCommentService {

    @Autowired
    private SmellcommentMapper smellCommentMapper;

    @Override
    public List<Smellcomment> selectCommentAll()
    {
        return smellCommentMapper.selectCommentAll();
    }

    @Override
    public int insertComment(Smellcomment record) {
        return smellCommentMapper.insert(record);
    }

    @Override
    public int updateComment(Smellcomment record, SmellcommentExample example) {
        return smellCommentMapper.updateByExample(record, example);
    }

}
