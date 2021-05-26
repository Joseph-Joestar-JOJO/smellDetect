package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Project;
import generator.ProjectExample;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer projid);

    int insert(Project record);

    int insertSelective(Project record);

    //List<Project> selectByExample(ProjectExample example);

    public List<Project> selectProjList(Project project);

    List<Project> selectProjAll();

    Project selectByPrimaryKey(Integer projid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

}