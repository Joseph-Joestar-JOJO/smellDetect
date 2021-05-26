package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.domain.Smellcomment;

import java.util.List;

import generator.SmellcommentExample;
import org.apache.ibatis.annotations.Param;

public interface SmellcommentMapper {
    long countByExample(SmellcommentExample example);

    int deleteByExample(SmellcommentExample example);

    int deleteByPrimaryKey(Integer commentid);

    int insert(Smellcomment record);

    int insertSelective(Smellcomment record);

    List<Smellcomment> selectByExample(SmellcommentExample example);

    Smellcomment selectByPrimaryKey(Integer commentid);

    int updateByExampleSelective(@Param("record") Smellcomment record, @Param("example") SmellcommentExample example);

    int updateByExample(@Param("record") Smellcomment record, @Param("example") SmellcommentExample example);

    int updateByPrimaryKeySelective(Smellcomment record);

    int updateByPrimaryKey(Smellcomment record);

    List<Smellcomment> selectCommentAll();
}