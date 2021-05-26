package com.ruoyi.system.service;

import com.ruoyi.system.domain.Project;

import java.util.List;

/**
 * 岗位信息 服务层
 *
 * @author ruoyi
 */
public interface ISysProjService
{
    /**
     * 查询岗位信息集合
     *
     * @param proj 岗位信息
     * @return 岗位信息集合
     */
    public List<Project> selectProjList(Project proj);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    public List<Project> selectProjAll();

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param projId 岗位ID
     * @return 角色对象信息
     */
    public Project selectProjById(int projId);

    /**
     * 批量删除岗位信息
     *
     * @param projId 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteProjById(int projId) throws Exception;

    /**
     * 新增保存岗位信息
     *
     * @param proj 岗位信息
     * @return 结果
     */
    public int insertProj(Project proj);

    /**
     * 修改保存岗位信息
     *
     * @param proj 岗位信息
     * @return 结果
     */
    public int updateProj(Project proj);

    /**
     * 校验岗位名称
     *
     * @param proj 岗位信息
     * @return 结果
     */
    public String checkProjNameUnique(Project proj);

    /**
     * 校验岗位编码
     *
     * @param proj 岗位信息
     * @return 结果
     */
    public String checkProjUrlUnique(Project proj);
}
