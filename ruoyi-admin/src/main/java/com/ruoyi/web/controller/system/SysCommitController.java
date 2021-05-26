package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.service.ICommitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 项目信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/commit")
public class SysCommitController extends BaseController {
    private String prefix = "system/commit";

    @Autowired
    private ICommitService commitService;

    @RequiresPermissions("system:commit:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/commit";
    }

    @RequiresPermissions("system:commit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam String url, Projcommit projcommit) {
        startPage();
        projcommit.setProjname(url);
        List<Projcommit> list = commitService.selectCommitList(projcommit);
        return getDataTable(list);
    }


//    @RequestMapping(value = "/checked",method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public void testPost(@RequestBody String[] array) throws IOException {
////        for (String string : array) {
////            System.out.println(string);
////        }
//        return ;
//    }

    @RequestMapping(value = "/checkedCommit",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void showData1(HttpServletRequest request, HttpSession session){
        String  commit = request.getParameter("commit");
        commit = commit.substring(1);
        commit = commit.substring(0, commit.length()-1);
        String[] hash = commit.split(",");
        for (String hashcode : hash)
        {
            System.out.println(hashcode);
        }
        return ;
    }

}