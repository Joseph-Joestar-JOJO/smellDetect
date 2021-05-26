package com.ruoyi.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.*;

import com.ruoyi.beans.*;
import com.ruoyi.system.domain.Projcommit;
import com.ruoyi.system.domain.Project;
import com.ruoyi.system.domain.Smell;
import com.ruoyi.system.service.ICommitService;
import com.ruoyi.system.service.ISmellService;
import com.ruoyi.system.service.ISysProjService;
import com.ruoyi.utility.*;
import generator.ProjcommitExample;
import it.unisa.bsic.algorithm.FilteringBadSmells;
import it.unisa.bsic.versioning.GitRepository;
import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import it.unisa.bsic.analyzer.BlobAnalyzer;
import it.unisa.bsic.analyzer.CDSBPAnalyzer;
import it.unisa.bsic.analyzer.CSV;
import it.unisa.bsic.analyzer.ComplexClassAnalyzer;
import it.unisa.bsic.analyzer.LongMethodAnalyzer;
import it.unisa.bsic.analyzer.MisplacedClassAnalyzer;
import it.unisa.bsic.analyzer.SpaghettiCodeAnalyzer;
import it.unisa.bsic.bean.Change;
import it.unisa.bsic.bean.Commit;
import it.unisa.bsic.bean.badsmell.Blob;
import it.unisa.bsic.bean.badsmell.CDSBP;
import it.unisa.bsic.bean.badsmell.ComplexClass;
import it.unisa.bsic.bean.badsmell.FeatureEnvy;
import it.unisa.bsic.bean.badsmell.LongMethod;
import it.unisa.bsic.bean.badsmell.MisplacedClass;
import it.unisa.bsic.bean.badsmell.SpaghettiCode;
import com.ruoyi.computation.Computation;
import com.ruoyi.main.Main;
import com.ruoyi.parser.ClassParser;
import com.ruoyi.parser.CodeParser;
import com.ruoyi.parser.MethodParser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Process implements ApplicationContextAware {


	GitRepository gitRepository;


	List<Blob> blobs;
	List<FeatureEnvy> featureEnvys;
	List<LongMethod> longMethods;
	List<MisplacedClass> misplacedClasses;
	List<CDSBP> CDSBPs;
	List<ComplexClass> complexClasses;
	List<SpaghettiCode> spaghettiCodes;

	private static ISysProjService sysProjService;
	private static ICommitService commitService;
	private static ISmellService smellService;

	public void initGitRepository(String directoryPath){
		gitRepository = new GitRepository();
		File directory = new File(directoryPath);
		gitRepository.setDirectory(directory);
		System.out.println("Init Repository");
		gitRepository.initialize();
		System.out.println("End init repository");
		System.out.println("Num of commits: "+gitRepository.getCommits().size());
		//System.out.println("\nCommits: \n\n");
		//gitRepository.printAllCommits();
	}
	public HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> calculateByTag(String path, File workingDirectory) throws IOException {
		Main metric = new Main();
		Vector<PackageBean> packages = new Vector<PackageBean>();
		ArrayList<String> tagCommitList = Git.gitShowTagCommit(workingDirectory);
		for (String commitHash : tagCommitList)
		{
			Git.gitCheckout(workingDirectory,commitHash,workingDirectory);
			try {
				packages = FolderToJavaProjectConverter.convert(workingDirectory.getAbsolutePath());
				metric.computeByTags(path, "",  packages, commitHash);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return Main.resHashMap;
	}

	public boolean isNeedContinue(String path)
	{
		List<Projcommit> resArrayList = new ArrayList<>();
		ProjcommitExample example = new ProjcommitExample();
		ProjcommitExample.Criteria criteria = example.createCriteria();
		criteria.andProjnameEqualTo(path);
		criteria.andStatusEqualTo("已完成");
		resArrayList = commitService.selectByExample(example);
		if (resArrayList.size() < gitRepository.getCommits().size() && resArrayList.size() > 0)
		{
			return true;
		}
		return false;
	}

	public HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> calculateByCommitList(File directory, File workingDirectory, ArrayList<String> commitList) throws IOException {
		Main metric = new Main();
		Vector<PackageBean> packages = new Vector<PackageBean>();
		for (String commitHash : commitList)
		{
			FileUtils.cleanDirectory(workingDirectory);
			Git.gitCheckout(directory,commitHash,workingDirectory);
			try {
				packages = FolderToJavaProjectConverter.convert(workingDirectory.getAbsolutePath());
				System.out.println("开始计算-----------------------》");
				metric.computeByTags(workingDirectory.getAbsolutePath(), "",  packages, commitHash);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return Main.resHashMap;
	}

	public HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> calculateCurrent(String path, File workingDirectory, String output) throws IOException {
		Main metric = new Main();
		try {
			FileUtils.cleanDirectory(new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<PackageBean> packages = new Vector<PackageBean>();
//		if (gitRepository.getCommits().size() == 0) {
			try {
				packages = FolderToJavaProjectConverter.convert(workingDirectory.getAbsolutePath());
			} catch (CoreException e) {
				e.printStackTrace();
			}
			Projcommit projcommit = new Projcommit();
			projcommit.setCommithash("0");
			projcommit.setProjname(path);
			projcommit.setStatus("进行中");
			commitService.insertCommit(projcommit);
			metric.computeRules(path, output, packages, new ArrayList<String>(), "0",true);
			return null;
//		}
//		return null;
	}

	public HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> calculateLatest(String path, File workingDirectory, String output, String commitHash) throws IOException{
		Main metric = new Main();
		try {
			FileUtils.cleanDirectory(new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<PackageBean> packages = new Vector<PackageBean>();
		try {
			packages = FolderToJavaProjectConverter.convert(workingDirectory.getAbsolutePath());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		ProjcommitExample example1 = new ProjcommitExample();
		ProjcommitExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andProjnameEqualTo(path);
		criteria1.andCommithashEqualTo(commitHash);
		Projcommit projcommit = commitService.selectByExample(example1).get(0);
		projcommit.setStatus("进行中");
		commitService.updateCommit(projcommit, example1);

		metric.computeRules(path, output, packages, new ArrayList<String>(), commitHash,true);
		return null;
	}

	public HashMap<String, HashMap<String, HashMap<String, ArrayList<SmellInfo>>>> calculateByCommit(String path, File workingDirectory, String output, int startCommit, String type) throws IOException{
		List<Integer> skippedSnapshot = new ArrayList<Integer>();
		Main metric = new Main();
		try {
			FileUtils.cleanDirectory(new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<PackageBean> packages = new Vector<PackageBean>();
		if (gitRepository.getCommits().size() == 0) {
			try {
				packages = FolderToJavaProjectConverter.convert(workingDirectory.getAbsolutePath());
			} catch (CoreException e) {
				e.printStackTrace();
			}
			Projcommit projcommit = new Projcommit();
			projcommit.setCommithash("0");
			projcommit.setProjname(path);
			projcommit.setStatus("进行中");
			commitService.insertCommit(projcommit);
			metric.computeRules(path, output, packages, new ArrayList<String>(), "0",true);
			return null;
		}
		List<Projcommit> resArrayList = new ArrayList<>();
		ProjcommitExample example = new ProjcommitExample();
		ProjcommitExample.Criteria criteria = example.createCriteria();
		criteria.andProjnameEqualTo(path);
		criteria.andStatusEqualTo("已完成");
		resArrayList = commitService.selectByExample(example);
		if (resArrayList.size() > 0 && resArrayList.size() < gitRepository.getCommits().size())
		{
			List<Commit> commitArrayList = gitRepository.getCommits();
			int i = 0;
			for(; i < commitArrayList.size(); i++)
			{
				Commit commit = commitArrayList.get(i);
				boolean isComputed = false;
				for(Projcommit projcommit : resArrayList)
				{
					if (commit.getCommitHash().equals(projcommit.getCommithash()))
					{
						isComputed = true;
						break;
					}
				}
				if(!isComputed)
				{
					startCommit = i;
					Git.gitReset(new File(path),commit.getCommitHash());
					try {
						packages = FolderToJavaProjectConverter.convert(workingDirectory.getAbsolutePath());
					} catch (CoreException e) {
						e.printStackTrace();
					}
					metric.computeRules(path, output, packages, new ArrayList<String>(), commit.getCommitHash(),true);
					break;
				}
			}
		}

		boolean isChangeJavaFile = false;
		for(int index = startCommit + 1; index<gitRepository.getCommits().size(); index++){
			System.out.println("snapshot: "+index+"/"+gitRepository.getCommits().size());
			Commit commit = gitRepository.getCommits().get(index);
			ArrayList<String> changedFileList = new ArrayList<String>();
			isChangeJavaFile = false;
			Main.removeFileList.clear();
			CodeParser codeParser = new CodeParser();
			for(Change change : commit.getChanges()){
				if(change.getFile().getPath().endsWith(".java")){							
					try {
						isChangeJavaFile = true;
						String filePath = change.getFile().getPath();
						filePath = filePath.substring(0, filePath.lastIndexOf('.')).replaceAll("/", ".");
						changedFileList.add(filePath);
						File javaFile = new File(output+"/"+change.getFile().getPath());
//						javaFile.getParentFile().mkdirs();
//						FileWriter fw = null;
//						fw = new FileWriter(javaFile);
//						PrintWriter pw = new PrintWriter(fw);
						String contentString = Git.gitShow(gitRepository.getDirectory(), change.getFile(), commit);
						if (contentString == "") {
							Main.removeFileList.add(filePath);
						}
//						pw.print(contentString);
//						//javaFile.createNewFile();
//						pw.flush();
//						pw.close();
//						fw.close();	
						
							System.out.println(change.getFile().getPath());
							//Get file Path
							//String filePath = projectDirectory.toURI().relativize(javaFile.toURI()).getPath();
							//filePath = filePath.substring(0, filePath.lastIndexOf('.'));
							//String filePath1 = javaFile.getAbsolutePath();
							CompilationUnit parsed;
							try {
								parsed = codeParser.createParser(contentString);
								int classNum = parsed.types().size();
								if (classNum == 0) {
									//ɾ��class
									Iterator<PackageBean> iterator1 = packages.iterator();
									while (iterator1.hasNext()) {
										PackageBean packageBean = iterator1.next();
										if (packageBean.getClasses().size() == 0) {
											iterator1.remove();
											continue;
										}
										Iterator<ClassBean> iterator = packageBean.getClasses().iterator();
										while (iterator.hasNext()) {
											ClassBean classBean = iterator.next();
											if (classBean.getFilePath().equals(filePath)) {
												iterator.remove();
											}
										}
									}
								}

								for (int i = 0; i < classNum; i++) {
									TypeDeclaration typeDeclaration = (TypeDeclaration)parsed.types().get(i);
									
									Vector<String> imports = new Vector<String>();

									for(Object importedResource: parsed.imports())
										imports.add(importedResource.toString());
									
									if(! FolderToJavaProjectConverter.isAlreadyCreated(
											parsed.getPackage().getName().getFullyQualifiedName(), packages)) {
										
										PackageBean packageBean = new PackageBean();
										packageBean.setName(parsed.getPackage().getName().getFullyQualifiedName());

										ClassBean classBean = ClassParser.parse(typeDeclaration, packageBean.getName(), imports, filePath,parsed);
										int startLine = typeDeclaration.getStartPosition();
										int endLine = startLine + typeDeclaration.getLength();
										int startL = parsed.getLineNumber(startLine);
										int endL = parsed.getLineNumber(endLine);
										classBean.setStartLine(startL);
										classBean.setEndLine(endL);
										packageBean.addClass(classBean);
										packages.add(packageBean);
																		
									} else {
										PackageBean packageBean = FolderToJavaProjectConverter.getPackageByName(
												parsed.getPackage().getName().getFullyQualifiedName(), packages);
										ClassBean classBean = ClassParser.parse(typeDeclaration, packageBean.getName(), imports, filePath,parsed);
										int startLine = typeDeclaration.getStartPosition();
										int endLine = startLine + typeDeclaration.getLength();
										int startL = parsed.getLineNumber(startLine);
										int endL = parsed.getLineNumber(endLine);
										classBean.setStartLine(startL);
										classBean.setEndLine(endL);
										packageBean.addClass(classBean);
										Iterator<ClassBean> it_c=packageBean.getClasses().iterator();
										while(it_c.hasNext()){
											ClassBean classBean1=it_c.next();
											if (classBean1.getName().equals(classBean.getName())) {
												it_c.remove();
											}
										}


										packageBean.addClass(classBean);
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							} catch (NullPointerException e) {
								// do nothing
							} catch (IndexOutOfBoundsException e) {
								// do nothing
							}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Vector<ClassBean> classes = new Vector<ClassBean>();
				for(PackageBean pb: packages) {
					StringBuilder textualContent=new StringBuilder();
					StringBuilder processsedContent=new StringBuilder();
					for(ClassBean cb: pb.getClasses()) {
						classes.add(cb);
						textualContent.append(cb.getTextContent());
						processsedContent.append(cb.getProcessedText());
					}
					pb.setTextContent(textualContent.toString());
					pb.setProcessedText(processsedContent.toString());
				}
				for(PackageBean packageBean: packages) {
					for(ClassBean classBean: packageBean.getClasses()) {
						for(MethodBean methodBean: classBean.getMethods()) {
							
							for(MethodBean invoked: methodBean.getMethodCalls()) {
								MethodParser.setOtherInfo(invoked, classBean, classes);
							}
						}
					}
				}
			}
			metric.computeRules(path, output, packages, changedFileList, commit.getCommitHash(),false);
			//System.out.println(Main.getSmellCountByCommit(commit.getCommitHash()));
//			try {
//				FileUtils.cleanDirectory(workingDirectory);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			String status = index+" / "+gitRepository.getCommits().size();
			updateStatusRealTime(path, status);
		}
		String status = "已完成";
		updateStatusRealTime(path, status);
		System.out.println("Skipped Snapshot: "+skippedSnapshot.size());
		return Main.resHashMap;
	}

	public void updateStatusRealTime(String path, String status)
	{
		Project project = new Project();
		project.setProjurl(path);
		project.setProjname(path);
		Project proj = sysProjService.selectProjList(project).get(0);
		proj.setStatus(status);
		sysProjService.updateProj(proj);
	}

	private void renameFiles(String output) {
		File file = new File(output);
		File[] files = file.listFiles();

		for(File fileArtefact : files){
			BufferedReader br = null;
			String line;
			try{
				br = new BufferedReader(new FileReader(fileArtefact));
				boolean trueFile = false;
				while ((line = br.readLine()) != null) {
					if(line.contains(";true;")){
						//br.close();
						if((! fileArtefact.getAbsolutePath().contains("_false")) && (! fileArtefact.getAbsolutePath().contains("_true"))) {
							String newFileName = fileArtefact.getAbsolutePath().replace(".csv", "_true.csv");
							if(! new File(newFileName).exists()) {
								FileUtils.moveFile(fileArtefact, new File(newFileName));
								trueFile = true;
								break;
							}
						}
					}
				}

				br.close();
				if(!trueFile){
					if((! fileArtefact.getAbsolutePath().contains("_false")) && (! fileArtefact.getAbsolutePath().contains("_true"))) {
						String newFileName = fileArtefact.getAbsolutePath().replace(".csv", "_false.csv");
						if(! new File(newFileName).exists()) {
							FileUtils.moveFile(fileArtefact, new File(newFileName));
						}
					}

				}

			} catch(Exception e){
				System.out.println("Error in rename");
				e.printStackTrace();
			}
		}
	}

	private List<String> readArtefacts(String file){
		List<String> artefacts = new ArrayList<String>();
		BufferedReader br = null;
		String line = "";

		try {
			try{
				br = new BufferedReader(new FileReader(file));
			} catch(Exception e){
				System.out.println("Missing file!");
			}
			boolean firstLine = true;
			while ((line = br.readLine()) != null) {
				if(firstLine){
					firstLine=false;
				} else{
					String[] info = line.split(";");
					artefacts.add(info[0]);
				}
			}

			br.close();
		}catch(Exception e){
			System.out.println("Error during initializing snapshots");
			e.printStackTrace();
		}

		return artefacts;
	}

	private void appendPreviousInformation(String previousFile, String currentFile, List<String> files, Commit commit){
		BufferedReader br = null;
		String line = "";

		try {

			FileWriter fw = null;
			fw = new FileWriter(currentFile, true);
			PrintWriter pw = new PrintWriter(fw);

			try{
				br = new BufferedReader(new FileReader(previousFile));
			} catch(Exception e){
				System.out.println("Missing file!");
			}
			boolean firstLine = true;
			while ((line = br.readLine()) != null) {
				if(firstLine){
					firstLine=false;
				} else{
					String[] info = line.split(";");
					if(!files.contains(info[0])){
						String toPrint = "";
						for(int i=0; i<info.length-1; i++){
							toPrint += info[i]+";";
						}
						toPrint += commit.getCommitHash();
						pw.println(toPrint);
					}
				}
			}

			//Closing outputFile
			pw.flush();
			pw.close();
			fw.close();
			br.close();
		}catch(Exception e){
			System.out.println("Error during initializing snapshots");
			e.printStackTrace();
		}
	}


	public void analyzeTestSmells_old(File workingDirectory, String output){
		Computation metrics = new Computation();
		List<Commit> history = new ArrayList<Commit>();

		for(int index = 0; index<gitRepository.getCommits().size(); index++){
			System.out.println("snapshot: "+index+"/"+gitRepository.getCommits().size());
			Commit commit = gitRepository.getCommits().get(index);

			//Checkout changed files
			checkoutChangedFiles(workingDirectory, commit);

			//Check for Test Cases changed in the current commit
			List<ClassBean> testCases = TestSmellUtilities.extractTestCases(workingDirectory);

			if(testCases.size()>0){
				//Checkout complete system
				Git.gitCheckout(gitRepository.getDirectory(), commit, workingDirectory);

				//Read classes
				Vector<ClassBean> system = FolderToJavaProjectConverter.extractClasses(workingDirectory.getAbsolutePath());

				//Attempt to find the production classes
				List<TestClassBean> linkedTestCases = new ArrayList<TestClassBean>();
				for(ClassBean tc : testCases){
					ClassBean pc = null;//TestSmellUtilities.findProductionClass(tc, system);

					if(pc != null){
						//����
						//TestClassBean ltc = new TestClassBean(tc, pc);
						//linkedTestCases.add(ltc);
					}
				}

				//Compute Test Smells Rules (only linked test cases)
				for(TestClassBean testCase : linkedTestCases){
					//����
					//metrics.computeTestSmellRules(testCase, output, commit.getAuthorTime(), commit.getCommitterTime(), commit.getCommitHash(), "", history);
				}

			}

			history.add(commit);

			try {
				FileUtils.cleanDirectory(workingDirectory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	private void checkoutChangedFiles(File workingDirectory, Commit commit) {
		for(Change change : commit.getChanges()){
			if(change.getFile().getPath().endsWith(".java")){
				try {				
					File javaFile = new File(workingDirectory.getAbsolutePath()+"/"+change.getFile().getPath());
					javaFile.getParentFile().mkdirs();
					FileWriter fw = null;
					fw = new FileWriter(javaFile);
					PrintWriter pw = new PrintWriter(fw);
					pw.print(Git.gitShow(gitRepository.getDirectory(), change.getFile(), commit));

					pw.flush();
					pw.close();
					fw.close();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}



	public void analyzeBadSmells(String inputPath, String outputPath){
		int numberOfCommit = gitRepository.getCommits().size();
		System.out.println("Blob Analysis...");
		blobs = BlobAnalyzer.analyze(inputPath+"/blob_", numberOfCommit, outputPath+"/analyzed_blobs.csv");
		System.out.println("Long Method Analysis...");
		longMethods = LongMethodAnalyzer.analyze(inputPath+"/longMethod_", numberOfCommit, outputPath+"/analyzed_longMethods.csv");
		System.out.println("Misplaced Class Analysis...");
		misplacedClasses = MisplacedClassAnalyzer.analyze(inputPath+"/mispacedClass_", numberOfCommit, outputPath+"/analyzed_misplacedClasses.csv");
		System.out.println("CDSBP Analysis...");
		CDSBPs = CDSBPAnalyzer.analyze(inputPath+"/cdsbp_", numberOfCommit, outputPath+"/analyzed_misplacedCDSBPs.csv");
		System.out.println("Complex Class Analysis...");
		complexClasses = ComplexClassAnalyzer.analyze(inputPath+"/complexClass_", numberOfCommit, outputPath+"/analyzed_complexClasses.csv");
		System.out.println("Spaghetti Code Analysis...");
		spaghettiCodes = SpaghettiCodeAnalyzer.analyze(inputPath+"/spaghettiCode_", numberOfCommit, outputPath+"/analyzed_spaghettiCodes.csv");
		//System.out.println("Feature Envy Analysis...");
		//featureEnvys = FeatureEnvyAnalyzer.analyze(inputPath+"/featureEnvy_", numberOfCommit, outputPath+"/analyzed_featureEnvys.csv");
	}

	public void analyzeBadSmellTrends(String inputPath, String outputPath){
		int numberOfCommit = gitRepository.getCommits().size();
		System.out.println("Blob Trend Analysis...");
		BlobAnalyzer.analyzeTrend(blobs, numberOfCommit, inputPath+"/blob_", outputPath+"/trend_blobs_");
		System.out.println("Long Method Trend Analysis...");
		LongMethodAnalyzer.analyzeTrend(longMethods, numberOfCommit, inputPath+"/longMethod_", outputPath+"/trend_longMethods_");
		System.out.println("Misplaced Class Trend Analysis...");
		MisplacedClassAnalyzer.analyzeTrend(misplacedClasses, numberOfCommit, inputPath+"/mispacedClass_", outputPath+"/trend_misplacedClasses_");
		System.out.println("CDSBP Trend Analysis...");
		CDSBPAnalyzer.analyzeTrend(CDSBPs, numberOfCommit, inputPath+"/cdsbp_", outputPath+"/trend_cdsbp_");
		System.out.println("Complex Class Trend Analysis...");
		ComplexClassAnalyzer.analyzeTrend(complexClasses, numberOfCommit, inputPath+"/complexClass_", outputPath+"/trend_complexClass_");
		System.out.println("Spaghetti Code Trend Analysis...");
		SpaghettiCodeAnalyzer.analyzeTrend(spaghettiCodes, numberOfCommit, inputPath+"/spaghettiCode_", outputPath+"/trend_spaghettiCode_");
		//System.out.println("Feature Envy Trend Analysis...");
		//FeatureEnvyAnalyzer.analyzeTrend(featureEnvys, numberOfCommit, inputPath+"/featureEnvy_", outputPath+"/trend_featureEnvys_");
	}

	public void initClasses(String inputPath){
		int numberOfCommit = gitRepository.getCommits().size();
		List<String> classes = CSV.initArtefacts(inputPath+"/blob_", numberOfCommit, 0);
		gitRepository.setClasses(classes);
	}


	public void initMethods(String inputPath){
		int numberOfCommit = gitRepository.getCommits().size();
		List<String> methods = CSV.initArtefacts(inputPath+"/longMethod_", numberOfCommit, 0);
		gitRepository.setMethods(methods);
	}

	public void analyzeTrend(String inputPath, String outputPath){
		int numberOfCommit = gitRepository.getCommits().size();
		List<String> classes = gitRepository.getClasses();
		List<String> methods = gitRepository.getMethods();

		System.out.println("Blob Trend Analysis...");
		CSV.analyzeTrend(classes, numberOfCommit, inputPath+"/blob_", outputPath+"/trend_blobs_", 0,10);
		System.out.println("Long Method Trend Analysis...");
		CSV.analyzeTrend(methods, numberOfCommit, inputPath+"/longMethod_", outputPath+"/trend_longMethods_", 0, 5);
		System.out.println("CDSBP Trend Analysis...");
		CSV.analyzeTrend(classes, numberOfCommit, inputPath+"/cdsbp_", outputPath+"/trend_cdsbp_", 0, 11);
		System.out.println("Complex Class Trend Analysis...");
		CSV.analyzeTrend(classes, numberOfCommit, inputPath+"/complexClass_", outputPath+"/trend_complexClass_", 0, 10);
		System.out.println("Spaghetti Code Trend Analysis...");
		CSV.analyzeTrend(classes, numberOfCommit, inputPath+"/spaghettiCode_", outputPath+"/trend_spaghettiCode_", 0, 10);
	}


	public void saveGitRepository(String filePath){
		gitRepository.save(filePath);
	}

	public void initGitRepositoryFromFile(String filePath){
		try
		{
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			gitRepository = (GitRepository) in.readObject();
			System.out.println("Num commit: "+gitRepository.getCommits().size());
			in.close();
			fileIn.close();
		}catch(Exception i){
			i.printStackTrace();
			return;
		}
	}


	public GitRepository getGitRepository() {
		return gitRepository;
	}


	public void setGitRepository(GitRepository gitRepository) {
		this.gitRepository = gitRepository;
	}


	public void addInfo(String inputPath) {
		List<Commit> commits = gitRepository.getCommits();

		CSV.addInfo(inputPath+"/blob_", commits);
		CSV.addInfo(inputPath+"/longMethod_", commits);
		CSV.addInfo(inputPath+"/cdsbp_", commits);
		CSV.addInfo(inputPath+"/complexClass_", commits);
		CSV.addInfo(inputPath+"/spaghettiCode_", commits);
	}

	public void filterBadSmells(String inputPath) {
		List<Commit> commits = gitRepository.getCommits();
		FilteringBadSmells.filteringBadSmells(inputPath, commits);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.sysProjService = applicationContext.getBean(ISysProjService.class);
		this.commitService = applicationContext.getBean(ICommitService.class);
		this.smellService = applicationContext.getBean(ISmellService.class);
	}
}
