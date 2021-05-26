package com.ruoyi.main;

import com.ruoyi.beans.PackageBean;
import com.ruoyi.beans.SmellInfo;
import com.ruoyi.computation.Computation;

import java.io.File;
import java.util.*;

import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.domain.Smell;
import com.ruoyi.system.domain.Smellcount;
import com.ruoyi.system.service.ICommitService;
import com.ruoyi.system.service.ISmellCountService;
import com.ruoyi.system.service.ISmellService;
import com.ruoyi.utility.Git;
import generator.ProjcommitExample;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Main implements ApplicationContextAware {

	private static ISmellService smellService;

	private static ICommitService commitService;

	private static ISmellCountService smellCountService;

	public static final String stopwordListPath = "D:/毕设/stopword";
	public static HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> resHashMap = new HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>>();
	public static HashMap<String, HashMap<String, HashMap<String, Double>>> tempMisplacedClass = new HashMap<String, HashMap<String, HashMap<String, Double>>>();
	public static HashMap<String, HashMap<String, HashMap<String, Double>>> tempFeatureEnvy = new HashMap<String, HashMap<String, HashMap<String, Double>>>();
	public static HashMap<String, HashMap<String, ArrayList<SmellInfo>>> lastCommitResult = new HashMap<String, HashMap<String, ArrayList<SmellInfo>>>();
	public static ArrayList<String> removeFileList = new ArrayList<String>();
	public static Smellcount LastSmellcount = new Smellcount();

	public void computeByTags(String pFolderPath, String pOutputPath, Vector<PackageBean> packages, String commitHash) {
		Computation computation = new Computation();
		HashMap<String, HashMap<String, ArrayList<SmellInfo>>> copy = new HashMap<String, HashMap<String,ArrayList<SmellInfo>>>();
		resHashMap.put(commitHash, copy);
		if (resHashMap.get(commitHash).isEmpty()) {
			resHashMap.get(commitHash).put("blob", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("misplacedClass", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("featureEnvy", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("longMethod", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("promiscuousPackage", new HashMap<String, ArrayList<SmellInfo>>());
		}
		//blob、misplaced class
		System.out.println("计算 blob、misplaced class");
		computation.computeClassLevelSmellRules(packages, pOutputPath, commitHash);
		//Feature Envy、longMethod
		System.out.println("计算 Feature Envy、longMethod");
		computation.computeMethodLevelSmellRules(packages, pOutputPath, commitHash);
		//promiscuous package
		System.out.println("计算 promiscuous package");
		computation.computePackageLevelSmellRules(packages, pOutputPath, commitHash);
		insertRealTime(resHashMap.get(commitHash), pFolderPath, commitHash);
	}

	public void computeRules(String rootDirectory, String pOutputPath, Vector<PackageBean> packages, ArrayList<String> changedFileList, String commitHash ,boolean isSingle) {
		Computation computation = new Computation();
		HashMap<String, HashMap<String, ArrayList<SmellInfo>>> copy = new HashMap<String, HashMap<String,ArrayList<SmellInfo>>>();
		resHashMap.put(commitHash, copy);
		for (java.util.Map.Entry<String, HashMap<String, ArrayList<SmellInfo>>>  entry : lastCommitResult.entrySet()) {
			HashMap<String, ArrayList<SmellInfo>> subCopy = new HashMap<String, ArrayList<SmellInfo>>();
			for (java.util.Map.Entry<String, ArrayList<SmellInfo>>  subEntry : entry.getValue().entrySet()) {
				if (subEntry.getValue() != null)
				{
					subCopy.put(subEntry.getKey(), new ArrayList<SmellInfo>(subEntry.getValue()));
				}
			}
			copy.put(entry.getKey(), subCopy);
		}

		if (resHashMap.get(commitHash).isEmpty()) {
			resHashMap.get(commitHash).put("blob", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("misplacedClass", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("featureEnvy", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("longMethod", new HashMap<String, ArrayList<SmellInfo>>());
			resHashMap.get(commitHash).put("promiscuousPackage", new HashMap<String, ArrayList<SmellInfo>>());
		}

		if (isSingle) {
			//blob、misplaced class
			System.out.println("计算 blob、misplaced class");
			computation.computeClassLevelSmellRules(packages, pOutputPath, commitHash);
			//Feature Envy、longMethod
			System.out.println("计算 Feature Envy、longMethod");
			computation.computeMethodLevelSmellRules(packages, pOutputPath, commitHash);
			//promiscuous package
			System.out.println("计算 promiscuous package");
			computation.computePackageLevelSmellRules(packages, pOutputPath, commitHash);
			insertRealTime(resHashMap.get(commitHash), rootDirectory, commitHash);
			return;
		}
		//blob、misplaced class
		System.out.println("计算 blob、misplaced class");
		computation.computeClassLevelSmellRulesAppend(packages, pOutputPath, changedFileList, commitHash);
		//Feature Envy、longMethod
		System.out.println("计算 Feature Envy、longMethod");
		computation.computeMethodLevelSmellRulesAppend(packages, pOutputPath, changedFileList, commitHash);
		//promiscuous package
		System.out.println("计算 promiscuous package");
		computation.computePackageLevelSmellRules(packages, pOutputPath, commitHash);
		//保留上一次计算结果

		insertRealTime(resHashMap.get(commitHash), rootDirectory, commitHash);
		lastCommitResult = resHashMap.get(commitHash);

	}
	
	public static int getSmellCount()
	{
		int count = 0;
		for (String commit: resHashMap.keySet()) {
			HashMap<String, HashMap<String, ArrayList<SmellInfo>>> map = resHashMap.get(commit);
			for (String string: map.keySet()) {
				HashMap<String, ArrayList<SmellInfo>> tempMap = map.get(string);
				for (String filePath: tempMap.keySet()) {
					count += tempMap.get(filePath).size();
				}
			}
		}
		return count;
	}
	
	public static int getSmellCountByCommit(String commitHash)
	{
		int count = 0;
		HashMap<String, HashMap<String, ArrayList<SmellInfo>>> map = resHashMap.get(commitHash);
		for (String string: map.keySet()) {
			HashMap<String, ArrayList<SmellInfo>> tempMap = map.get(string);
			for (String filePath: tempMap.keySet()) {
				count += tempMap.get(filePath).size();
			}
		}
		return count;
	}
	
	public static int getSmellCountByCommitByCatalog(String commitHash, String catalog)
	{
		int count = 0;
		HashMap<String, HashMap<String, ArrayList<SmellInfo>>> map = resHashMap.get(commitHash);
		HashMap<String, ArrayList<SmellInfo>> tempMap = map.get(catalog);
		for (String filePath: tempMap.keySet()){
			count += tempMap.get(filePath).size();
		}
		return count;
	}

	public static int getSmellCount(HashMap<String, HashMap<String, ArrayList<SmellInfo>>> map, String catalog)
	{
		int count = 0;
		HashMap<String, ArrayList<SmellInfo>> tempMap = map.get(catalog);
		for (String filePath: tempMap.keySet()){
			if (tempMap.get(filePath) != null)
			{
				count += tempMap.get(filePath).size();
			}
		}
		return count;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.commitService = applicationContext.getBean(ICommitService.class);
		this.smellService = applicationContext.getBean(ISmellService.class);
		this.smellCountService = applicationContext.getBean(ISmellCountService.class);
	}
	public void insertRealTime(HashMap<String, HashMap<String, ArrayList<SmellInfo>>> map,String rootDirectory, String commithash)
	{
		HashMap<String, ArrayList<SmellInfo>> subBlobEntry = map.get("blob");
		HashMap<String, ArrayList<SmellInfo>> subFeatureEntry = map.get("featureEnvy");
		HashMap<String, ArrayList<SmellInfo>> subMisplacedEntry = map.get("misplacedClass");
		HashMap<String, ArrayList<SmellInfo>> subLongMethodEntry = map.get("longMethod");
		HashMap<String, ArrayList<SmellInfo>> subPromiscuousEntry = map.get("promiscuousPackage");
		insertSmell(subBlobEntry, rootDirectory, commithash,"0");
		insertSmell(subFeatureEntry, rootDirectory, commithash,"1");
		insertSmell(subMisplacedEntry, rootDirectory, commithash,"3");
		insertSmell(subLongMethodEntry, rootDirectory, commithash,"2");
		insertSmell(subPromiscuousEntry, rootDirectory, commithash,"4");

		Smellcount sc = new Smellcount();
		sc.setProjurl(rootDirectory);
		sc.setCommithash(commithash);
		sc.setBlobcount(Main.getSmellCount(map,"blob"));
		sc.setFeatureenvycount(Main.getSmellCount(map,"featureEnvy"));
		sc.setMisplacedclasscount(Main.getSmellCount(map,"misplacedClass"));
		sc.setPromiscuouspackagecount(Main.getSmellCount(map,"promiscuousPackage"));
		sc.setLongmethodcount(Main.getSmellCount(map,"longMethod"));
//		if (LastSmellcount.getProjurl() != null)
//		{
//			sc.setBlobchange(sc.getBlobcount() - LastSmellcount.getBlobcount());
//			sc.setFeatureenvychange(sc.getFeatureenvycount() - LastSmellcount.getFeatureenvycount());
//			sc.setMisplacedclasschange(sc.getMisplacedclasscount() - LastSmellcount.getMisplacedclasscount());
//			sc.setPromiscuouspackagechange(sc.getPromiscuouspackagecount() - LastSmellcount.getPromiscuouspackagecount());
//			sc.setLongmethodchange(sc.getLongmethodcount() - LastSmellcount.getLongmethodcount());
//		}
		int smellcount = Main.getSmellCount(map,"blob");
		smellcount += Main.getSmellCount(map,"featureEnvy");
		smellcount += Main.getSmellCount(map,"misplacedClass");
		smellcount += Main.getSmellCount(map,"promiscuousPackage");
		smellcount += Main.getSmellCount(map,"longMethod");
		ProjcommitExample example = new ProjcommitExample();
		ProjcommitExample.Criteria criteria = example.createCriteria();
		criteria.andProjnameEqualTo(rootDirectory);
		criteria.andCommithashEqualTo(commithash);
		List<Projcommit> projcommitList = commitService.selectByExample(example);
		if (commitService.selectByExample(example).size() > 0)
		{
			Projcommit projcommit = commitService.selectByExample(example).get(0);
			projcommit.setStatus("已完成");
			projcommit.setSmellcount(smellcount);
			commitService.updateByExampleSelective(projcommit, example);
			sc.setCommitdate(projcommit.getCommitdate());
		}
		smellCountService.insertSmell(sc);
		//LastSmellcount = sc;
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
				String fileContent = "";
				if (catalog != "4")
				{
					fileContent = Git.gitShow(new File(rootDirectory), filePath,commitHash);
					if (fileContent.equals(""))
					{
						subEntry.put(entry1.getKey(),null);
						continue;
					}
				}
				if (catalog == "1")
				{
					String tarPath = smellInfo.info.split("\n")[2].replace(".","/");
					tarPath += ".java";
					fileContent = Git.gitShow(new File(rootDirectory), filePath,commitHash);
					String tarContent = Git.gitShow(new File(rootDirectory), tarPath,commitHash);
					fileContent += "\n<>\n"+tarContent;
				}
				smell.setContent(fileContent);
				smell.setSmellinfo(smellInfo.info);
				smellService.insertSmell(smell);
			}
		}
	}
}