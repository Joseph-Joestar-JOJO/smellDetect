package com.ruoyi.web.controller.system;

import it.unisa.bsic.bean.Commit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.beans.SmellInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.main.Main;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.ruoyi.utility.Git;
import generator.ProjcommitExample;
import generator.SmellcommentExample;
import generator.SmellcountExample;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 角色信息
 *
 * @author ruoyi
 */
@Controller
public class SmellCountController extends BaseController
{
    @Autowired
    private ISmellCommentService smellCommentService;

    @Autowired
    private ISmellCountService smellCountService;

    @Autowired
    private ISmellService smellService;

    @Autowired
    private ICommitService commitService;

    @Autowired
    private ISysProjService sysProjService;

    public static com.ruoyi.algorithm.Process process = new com.ruoyi.algorithm.Process();

    @PostMapping("/system/comment/list")
    @ResponseBody
    public TableDataInfo list()
    {
        startPage();
        List<Smellcomment> list = smellCommentService.selectCommentAll();
        return getDataTable(list);
    }

    @GetMapping("/system/comment/add")
    public String add()
    {
        return "system/project/addComment";
    }

    /**
     * 新增评论
     */
    @RequiresPermissions("system:comment:add")
    @Log(title = "评论添加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Smellcomment smellcomment)
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
        return toAjax(smellCommentService.insertComment(smellcomment));
    }

    @GetMapping("/system/showCode2")
    public String showCode2(@RequestParam String url,@RequestParam String commit,@RequestParam String filePath,@RequestParam String name, Model model)throws IOException
    {
        model.addAttribute("commit",commit);
        model.addAttribute("url",url);
        model.addAttribute("name",name);
        model.addAttribute("filePath",filePath);
        List<Smell> resArrayList = new ArrayList<>();
        resArrayList = smellService.selectByName(url,commit,name);
        String content = resArrayList.get(0).getContent().split("<>\n")[1];
        model.addAttribute("content",content);
        return "system/project/showCode2";
    }

    @GetMapping("/system/showCode")
    public String showCode(@RequestParam String url,@RequestParam String commit,@RequestParam String catalog, Model model)throws IOException
    {
        model.addAttribute("commit",commit);
        model.addAttribute("url",url);
        model.addAttribute("catalog",catalog);
        List<Smell> resArrayList = new ArrayList<>();
        resArrayList = smellService.selectByCommit(url,commit,catalog);
        for(Smell smell : resArrayList)
        {
            switch (smell.getCatalog())
            {
                case "0":
                    smell.setCatalog("blob");
                    break;
                case "1":
                    smell.setCatalog("feature Envy");
                    break;
                case "2":
                    smell.setCatalog("long Method");
                    break;
                case "3":
                    smell.setCatalog("misplaced Class");
                    break;
                case "4":
                    smell.setCatalog("promiscuous Package");
                    break;
            }
            //codeFormat format = new codeFormat();
            smell.setContent(smell.getContent());
        }
        List<Smellcomment> resArrayList2 = new ArrayList<>();
        resArrayList2 = smellCommentService.selectCommentAll();
        model.addAttribute("list",resArrayList);
        model.addAttribute("list2",resArrayList2);
        return "system/project/showCode";
    }

    @GetMapping("/system/smellcountByCommit")
    public String smellcountByCommit(@RequestParam String url,@RequestParam String commit, Model model)throws IOException
    {
        model.addAttribute("commit",commit);
        model.addAttribute("url",url);
        return "system/project/echartsSingle";
    }

    public static String timeStamp2Date(String seconds) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    @GetMapping("/system/smellcountLast")
    public String smellcountLast(@RequestParam String id, @RequestParam String path, Model model)throws IOException
    {
        //查询所有提交
        List<Projcommit> resArrayList = new ArrayList<>();
        ProjcommitExample example = new ProjcommitExample();
        ProjcommitExample.Criteria criteria = example.createCriteria();
        criteria.andProjnameEqualTo(path);
        example.setOrderByClause("commitDate");
        resArrayList = commitService.selectByExample(example);

        if (resArrayList.size() == 0) {
            //重新检索，保存到数据库
            List<Projcommit> commitList = Git.extractCommitSimple(new File(path), false, Integer.parseInt(id), path);
            for (Projcommit projcommit: commitList)
            {
                projcommit.setCommitdate(timeStamp2Date(projcommit.getCommitdate()));
                commitService.insertCommit(projcommit);
            }
            ArrayList<String> tagList = Git.gitShowTag(new File(path));
            ArrayList<String> tagCommitList = Git.gitShowTagCommit(new File(path));
            for (int i = 0 ; i < tagList.size(); i++)
            {
                ProjcommitExample example1 = new ProjcommitExample();
                ProjcommitExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andProjnameEqualTo(path);
                criteria1.andCommithashEqualTo(tagCommitList.get(i));
                Projcommit projcommit = commitService.selectByExample(example1).get(0);
                projcommit.setTag(tagList.get(i));
                commitService.updateCommit(projcommit, example1);
            }
        }

        resArrayList = commitService.selectByExample(example);
        String commitHash = resArrayList.get(resArrayList.size()-1).getCommithash();
        List<Smellcount> resArrayList1 = new ArrayList<>();
        resArrayList1 = smellCountService.selectSmellCountByCommit(path,commitHash);
        if (resArrayList1.size() == 0)
        {
            resetToLatest(path);
            File Directory = new File(path);
            String rootDirectory = path;
            String outputDirectory = "C:\\Users\\wkx\\Desktop\\output5";
            FileUtils.cleanDirectory(new File(outputDirectory));
            Main.resHashMap.clear();
            FileUtils.cleanDirectory(new File(outputDirectory));
            Directory.mkdirs();
            //Calculate metrics...
            //HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = process.calculateByTag(Directory, badSmellDirectory);
            HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = process.calculateLatest(rootDirectory, Directory, outputDirectory, commitHash);
        }
        model.addAttribute("commit",commitHash);
        model.addAttribute("url",path);
        return "system/project/echartsSingle";
    }

    @GetMapping("/system/commitShow")
    public String commitShow(@RequestParam String id, @RequestParam String path, Model model)throws IOException
    {
        //查询所有提交
        List<Projcommit> resArrayList = new ArrayList<>();
        ProjcommitExample example = new ProjcommitExample();
        ProjcommitExample.Criteria criteria = example.createCriteria();
        criteria.andProjnameEqualTo(path);
        resArrayList = commitService.selectByExample(example);

        if (resArrayList.size() == 0)
        {
            //重新检索，保存到数据库
            List<Projcommit> commitList = Git.extractCommitSimple(new File(path), false, Integer.parseInt(id), path);
            for (Projcommit projcommit: commitList)
            {
                projcommit.setCommitdate(timeStamp2Date(projcommit.getCommitdate()));
                commitService.insertCommit(projcommit);
            }
            ArrayList<String> tagList = Git.gitShowTag(new File(path));
            ArrayList<String> tagCommitList = Git.gitShowTagCommit(new File(path));
            for (int i = 0 ; i < tagList.size(); i++)
            {
                ProjcommitExample example1 = new ProjcommitExample();
                ProjcommitExample.Criteria criteria1 = example1.createCriteria();
                criteria.andProjnameEqualTo(path);
                criteria.andCommithashEqualTo(tagCommitList.get(i));
                List<Projcommit> projcommitList = commitService.selectByExample(example1);
                Projcommit projcommit = commitService.selectByExample(example1).get(0);
                projcommit.setTag(tagList.get(i));
                commitService.updateCommit(projcommit, example1);
            }
        }

        model.addAttribute("url",path);
        return "system/project/commitCheck";
    }

    public  void resetToLatest(String path)
    {
        List<Projcommit> resArrayList = new ArrayList<>();
        ProjcommitExample example = new ProjcommitExample();
        ProjcommitExample.Criteria criteria = example.createCriteria();
        criteria.andProjnameEqualTo(path);
        example.setOrderByClause("commitDate");
        resArrayList = commitService.selectByExample(example);
        if (resArrayList.size() > 0) {
            String lastCommitHash = resArrayList.get(resArrayList.size() - 1).getCommithash();
            Git.gitReset(new File(path), lastCommitHash);
        }
    }


    @GetMapping("/system/smellcount")
    public String smellcount(@RequestParam String id,@RequestParam String path, Model model)throws IOException
    {
        List<Project> resArray = new ArrayList<>();
        Project project = new Project();
        project.setStatus("已完成");
        project.setProjurl(path);
        resArray = sysProjService.selectProjList(project);
        if (resArray.size() > 0)
        {
            model.addAttribute("url",path);
            return "system/project/echarts";
        }
        //resetToLatest(path);
        File Directory = new File(path);
        //process.initGitRepository(path);
//        if(process.isNeedContinue(path))
//        {
//            String rootDirectory = path;
//            String outputDirectory = "C:\\Users\\wkx\\Desktop\\output5";
//            FileUtils.cleanDirectory(new File(outputDirectory));
//            Main.resHashMap.clear();
//            HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = init(rootDirectory, outputDirectory, "a");
//            resetToLatest(path);
//            model.addAttribute("url",path);
//            return "system/project/echarts";
//        }

        List<Smellcount> resArrayList = new ArrayList<>();
        resArrayList = smellCountService.selectSmellCountById(path);

        if (resArrayList.isEmpty())
        {
            //如果没有数据，需要计算
            String rootDirectory = path;
            String outputDirectory = "C:\\Users\\wkx\\Desktop\\output5";
            FileUtils.cleanDirectory(new File(outputDirectory));
            Main.resHashMap.clear();

            //插入Commit数据
            //查询所有提交
            List<Projcommit> resArrayList1 = new ArrayList<>();
            ProjcommitExample example = new ProjcommitExample();
            ProjcommitExample.Criteria criteria = example.createCriteria();
            criteria.andProjnameEqualTo(path);
            resArrayList1 = commitService.selectByExample(example);

            if (resArrayList1.size() == 0)
            {
                //重新检索，保存到数据库

                ArrayList<String> tagList = Git.gitShowTag(new File(path));
                ArrayList<String> tagCommitList = Git.gitShowTagCommit(new File(path));

                for (int i = 0 ; i < tagList.size(); i++)
                {
                    Projcommit projcommit = new Projcommit();
                    projcommit.setProjname(path);
                    projcommit.setCommithash(tagCommitList.get(i));
                    projcommit.setTag(tagList.get(i));
                    commitService.insertCommit(projcommit);
                }
//                for (int i = 0 ; i < tagList.size(); i++)
//                {
//                    ProjcommitExample example1 = new ProjcommitExample();
//                    ProjcommitExample.Criteria criteria1 = example1.createCriteria();
//                    criteria1.andProjnameEqualTo(path);
//                    criteria1.andCommithashEqualTo(tagCommitList.get(i));
//                    if (commitService.selectByExample(example1).size() > 0)
//                    {
//                        Projcommit projcommit = commitService.selectByExample(example1).get(0);
//                        projcommit.setTag(tagList.get(i));
//                        commitService.updateCommit(projcommit, example1);
//                    }
//                }
            }

            //开始计算
            HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = init(rootDirectory, outputDirectory, "a");
//            for(Map.Entry<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> entry: resHashMap.entrySet())
//            {
//                Smellcount sc = new Smellcount();
//                sc.setProjurl(rootDirectory);
//                sc.setCommithash(entry.getKey());
//                HashMap<String, HashMap<String, ArrayList<SmellInfo>>> subEntry = entry.getValue();
//                sc.setBlobcount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"blob"));
//                sc.setFeatureenvycount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"featureEnvy"));
//                sc.setMisplacedclasscount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"misplacedClass"));
//                sc.setPromiscuouspackagecount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"promiscuousPackage"));
//                sc.setLongmethodcount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"longMethod"));
//                smellCountService.insertSmell(sc);
//
//                HashMap<String, ArrayList<SmellInfo>> subBlobEntry = subEntry.get("blob");
//                HashMap<String, ArrayList<SmellInfo>> subFeatureEntry = subEntry.get("featureEnvy");
//                HashMap<String, ArrayList<SmellInfo>> subMisplacedEntry = subEntry.get("misplacedClass");
//                HashMap<String, ArrayList<SmellInfo>> subLongMethodEntry = subEntry.get("longMethod");
//                HashMap<String, ArrayList<SmellInfo>> subPromiscuousEntry = subEntry.get("promiscuousPackage");
//
//                insertSmell(subBlobEntry, rootDirectory, entry.getKey(),"0");
//                insertSmell(subFeatureEntry, rootDirectory, entry.getKey(),"1");
//                insertSmell(subMisplacedEntry, rootDirectory, entry.getKey(),"3");
//                insertSmell(subLongMethodEntry, rootDirectory, entry.getKey(),"2");
//                insertSmell(subPromiscuousEntry, rootDirectory, entry.getKey(),"4");
//            }
        }
        //显示图表
        model.addAttribute("url",path);
        return "system/project/echarts";
    }

    public void insertSmell(HashMap<String, ArrayList<SmellInfo>> subEntry, String rootDirectory, String commitHash, String catalog)
    {
        for (Map.Entry<String, ArrayList<SmellInfo>> entry1 : subEntry.entrySet())
        {
            for (SmellInfo smellInfo : entry1.getValue())
            {
                Smell smell = new Smell();
                smell.setProjurl(rootDirectory);
                smell.setCatalog(catalog);
                smell.setFilepath(entry1.getKey());
                smell.setCommithash(commitHash);
                smell.setName(smellInfo.name);
                String filePath = entry1.getKey().replace(".","/");
                filePath += ".java";
                smell.setContent(Git.gitShow(new File(rootDirectory), filePath,commitHash));
                smell.setSmellinfo(smellInfo.info);
                smellService.insertSmell(smell);
            }
        }
    }

    @RequestMapping(value = "/readDataByCommit",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Smellcount showDataByCommit(HttpServletRequest request, HttpSession session){
        String  commit = request.getParameter("commit");
        String  url = request.getParameter("url");
        //显示图表
        List<Smellcount> resArrayList = new ArrayList<>();
        resArrayList = smellCountService.selectSmellCountByCommit(url, commit);
        return resArrayList.get(0);
    }

    @RequestMapping(value = "/readData",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Smellcount> showData(HttpServletRequest request, HttpSession session){
        String  url = request.getParameter("url");
        List<Smellcount> resArrayList = new ArrayList<>();
        List<Smellcount> resArrayList2 = new ArrayList<>();
        //查询所有提交
        List<Projcommit> resArrayList1 = new ArrayList<>();
        ProjcommitExample example1 = new ProjcommitExample();
        ProjcommitExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andProjnameEqualTo(url);
        resArrayList1 = commitService.selectByExample(example1);

        resArrayList = smellCountService.selectSmellCountById(url);
        ArrayList<String> tagCommitList = Git.gitShowTagCommit(new File(url));

        for (int i = 0; i < resArrayList.size(); i++)
        {
            for (int j = 0; j < resArrayList1.size(); j++)
            {
                if (resArrayList.get(i).getCommithash().equals(resArrayList1.get(j).getCommithash()))
                {
                    resArrayList.get(i).setTag(resArrayList1.get(j).getTag());
                    break;
                }
            }
        }
        for (int i = 1; i < resArrayList.size(); i++)
        {
            Smellcount cur = resArrayList.get(i);
            Smellcount pre = resArrayList.get(i-1);
            cur.setBlobchange(cur.getBlobcount() - pre.getBlobcount());
            cur.setLongmethodchange(cur.getLongmethodcount() - pre.getLongmethodcount());
            cur.setFeatureenvychange(cur.getFeatureenvycount() - pre.getFeatureenvycount());
            cur.setMisplacedclasschange(cur.getMisplacedclasscount() - pre.getMisplacedclasscount());
            cur.setPromiscuouspackagechange(cur.getPromiscuouspackagecount() - pre.getPromiscuouspackagecount());
            SmellcountExample example = new SmellcountExample();
            SmellcountExample.Criteria criteria = example.createCriteria();
            criteria.andProjurlEqualTo(cur.getProjurl());
            criteria.andCommithashEqualTo(cur.getCommithash());
            smellCountService.updateByExampleSelective(cur, example);
        }
        int index = 0;
        for (String hash : tagCommitList)
        {
            for (Smellcount smellcount : resArrayList)
            {
                if (hash.equals(smellcount.getCommithash()))
                {
                    resArrayList2.add(smellcount);
                }
            }
        }
        return resArrayList2;
    }

//    @GetMapping("/computeSmell")
//    public String computeSmell() throws IOException {
//        String rootDirectory = "D:\\毕设\\test-project\\structer-test\\RuoYi";
//        String outputDirectory = "C:\\Users\\wkx\\Desktop\\output5";
//        HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = init(rootDirectory, outputDirectory, "a");
//        for(Map.Entry<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> entry: resHashMap.entrySet())
//        {
//            Smellcount sc = new Smellcount();
//            sc.setProjurl(rootDirectory);
//            sc.setCommithash(entry.getKey());
//            HashMap<String, HashMap<String, ArrayList<SmellInfo>>> subEntry = entry.getValue();
//            sc.setBlobcount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"blob"));
//            sc.setFeatureenvycount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"featureEnvy"));
//            sc.setMisplacedclasscount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"misplacedClass"));
//            sc.setPromiscuouspackagecount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"promiscuousPackage"));
//            sc.setLongmethodcount(Main.getSmellCountByCommitByCatalog(entry.getKey(),"longMethod"));
//            smellCountService.insertSmell(sc);
//        }
//        return "redirect:/system/smellcount";
//    }

    public static HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> init(String repositoryPath, String outputDirectory, String type) throws IOException{
        File Directory = new File(repositoryPath);
        FileUtils.cleanDirectory(new File(outputDirectory));
        File badSmellDirectory = new File(outputDirectory+"/bad_smell/");
        Directory.mkdirs();
        badSmellDirectory.mkdirs();

        //Calculate metrics...
        HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = process.calculateByTag(repositoryPath, Directory);
        //HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = process.calculateByCommit(repositoryPath, Directory, outputDirectory,0,"");
        return resHashMap;
    }
}