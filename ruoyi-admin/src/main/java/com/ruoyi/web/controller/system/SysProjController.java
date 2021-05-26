package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.Project;
import com.ruoyi.system.domain.Smellcount;
import com.ruoyi.system.service.ISmellCountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysProjService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 项目信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/project")
public class SysProjController extends BaseController
{
    private String prefix = "system/project";

    @Autowired
    private ISysProjService projService;

    @Autowired
    private ISmellCountService smellCountService;

    @RequiresPermissions("system:project:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/project";
    }

    @RequiresPermissions("system:project:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Project proj)
    {
        startPage();
        List<Project> list = projService.selectProjList(proj);
        return getDataTable(list);
    }

    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:project:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Project proj)
    {
        List<Project> list = projService.selectProjList(proj);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        return util.exportExcel(list, "项目数据");
    }

    @RequiresPermissions("system:project:remove")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(projService.deleteProjById(Integer.parseInt(ids)));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目
     */
    @RequiresPermissions("system:project:add")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Project proj)
    {
//        if (UserConstants.PROJ_NAME_NOT_UNIQUE.equals(projService.checkProjNameUnique(proj)))
//        {
//            return error("新增项目'" + proj.getProjName() + "'失败，项目名称已存在");
//        }
//        else if (UserConstants.PROJ_CODE_NOT_UNIQUE.equals(projService.checkProjUrlUnique(proj)))
//        {
//            return error("新增项目'" + proj.getProjName() + "'失败，项目地址已存在");
//        }
        System.out.println("调用add——————————————————————");
        return toAjax(projService.insertProj(proj));
    }

    /**
     * 修改项目
     */
    @GetMapping("/edit/{projId}")
    public String edit(@PathVariable("projId") int projId, ModelMap mmap)
    {
        mmap.put("proj", projService.selectProjById(projId));
        return prefix + "/edit";
    }

    /**
     * 修改保存项目
     */
    @RequiresPermissions("system:project:edit")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Project proj)
    {
//        if (UserConstants.PROJ_NAME_NOT_UNIQUE.equals(projService.checkProjNameUnique(proj)))
//        {
//            return error("修改项目'" + proj.getProjName() + "'失败，项目名称已存在");
//        }
//        else if (UserConstants.PROJ_URL_NOT_UNIQUE.equals(projService.checkProjUrlUnique(proj)))
//        {
//            return error("修改项目'" + proj.getProjName() + "'失败，项目编码已存在");
//        }
        return toAjax(projService.updateProj(proj));
    }

    /**
     * 校验项目名称
     */
    @PostMapping("/checkProjNameUnique")
    @ResponseBody
    public String checkProjNameUnique(Project proj)
    {
        return projService.checkProjNameUnique(proj);
    }

    /**
     * 校验项目编码
     */
    @PostMapping("/checkProjUrlUnique")
    @ResponseBody
    public String checkProjUrlUnique(Project proj)
    {
        return projService.checkProjUrlUnique(proj);
    }

    /**
     * 查看图表
     */
    @GetMapping("/echarts/{projId}")
    public String echarts(@PathVariable("projId") int projId, ModelMap mmap)
    {
        mmap.put("projurl", projService.selectProjById(projId).getProjurl());
        return prefix + "/echarts";
    }

    @RequestMapping(value = "/readData",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Smellcount> showData(HttpServletRequest request, HttpSession session){
        String  url = request.getParameter("url");
        List<Smellcount> resArrayList = new ArrayList<>();
        resArrayList = smellCountService.selectSmellCountById(url);
        return resArrayList;
    }
}
