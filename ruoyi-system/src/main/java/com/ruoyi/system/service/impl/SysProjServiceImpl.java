package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.Project;
import com.ruoyi.system.service.ISysProjService;
import com.ruoyi.system.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysProjServiceImpl implements ISysProjService
{
    @Autowired
    private ProjectMapper projMapper;

//    /**
//     * 查询岗位信息集合
//     *
//     * @param proj 岗位信息
//     * @return 岗位信息集合
//     */
////    @Override
////    public List<Project> selectProjList(Project proj)
////    {
////        ProjectExample example = new ProjectExample();
////        ProjectExample.Criteria criteria = example.createCriteria();
////        criteria.andProjidEqualTo(proj.getProjid());
////        return projMapper.selectByExample(example);
////    }

    @Override
    public List<Project> selectProjList(Project project)
    {
        return projMapper.selectProjList(project);
    }
    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<Project> selectProjAll()
    {
        return projMapper.selectProjAll();
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param projId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public Project selectProjById(int projId)
    {
        return projMapper.selectByPrimaryKey(projId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param projId 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deleteProjById(int projId) throws Exception
    {
        return projMapper.deleteByPrimaryKey(projId);
    }

    /**
     * 新增保存岗位信息
     *
     * @param proj 岗位信息
     * @return 结果
     */
    @Override
    public int insertProj(Project proj)
    {
        return projMapper.insert(proj);
    }

    /**
     * 修改保存岗位信息
     *
     * @param proj 岗位信息
     * @return 结果
     */
    @Override
    public int updateProj(Project proj)
    {
        return projMapper.updateByPrimaryKey(proj);
    }


    /**
     * 校验岗位名称是否唯一
     *
     * @param proj 岗位信息
     * @return 结果
     */
    @Override
    public String checkProjNameUnique(Project proj)
    {
//        Long projId = StringUtils.isNull(proj.getProjId()) ? -1L : proj.getProjId();
//        Project info = projMapper.checkProjNameUnique(proj.getProjName());
//        if (StringUtils.isNotNull(info) && info.getProjId() != projId)
//        {
//            return UserConstants.POST_NAME_NOT_UNIQUE;
//        }
//        return UserConstants.POST_NAME_UNIQUE;
        return "0";
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param proj 岗位信息
     * @return 结果
     */
    @Override
    public String checkProjUrlUnique(Project proj)
    {
//        Long projId = StringUtils.isNull(proj.getProjId()) ? -1L : proj.getProjId();
//        Project info = projMapper.checkProjUrlUnique(proj.getProjUrl());
//        if (StringUtils.isNotNull(info) && info.getProjId() != projId)
//        {
//            return UserConstants.POST_CODE_NOT_UNIQUE;
//        }
//        return UserConstants.POST_CODE_UNIQUE;
        return "0";
    }
}
