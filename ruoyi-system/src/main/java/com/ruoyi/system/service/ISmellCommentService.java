package com.ruoyi.system.service;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.domain.Smellcomment;
import com.ruoyi.system.domain.Smellcount;
import generator.SmellcommentExample;

import java.util.List;

public interface ISmellCommentService {

    public List<Smellcomment> selectCommentAll();

    public int insertComment(Smellcomment record);

    public int updateComment(Smellcomment record, SmellcommentExample example);

}
