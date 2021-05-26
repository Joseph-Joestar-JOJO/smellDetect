package com.ruoyi.computation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.ruoyi.badSmellRules.FeatureEnvyRule;
import com.ruoyi.beans.SmellInfo;
import com.ruoyi.utility.DBScan;
import com.ruoyi.utility.DataNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.ruoyi.badSmellRules.ClassDataShouldBePrivateRule;
import com.ruoyi.badSmellRules.ComplexClassRule;
import com.ruoyi.badSmellRules.FunctionalDecompositionRule;
import com.ruoyi.badSmellRules.GodClassRule;
import com.ruoyi.badSmellRules.LongMethodRule;
import com.ruoyi.badSmellRules.PromiscuousPackageRule;
import com.ruoyi.badSmellRules.SpaghettiCodeRule;
import com.ruoyi.badSmellRules.MispacedClassRule;
import com.ruoyi.beans.ClassBean;
import com.ruoyi.beans.MethodBean;
import com.ruoyi.beans.PackageBean;
import com.ruoyi.main.Main;
import com.ruoyi.metrics.CosineSimilarity;
import com.ruoyi.parser.CodeParser;
import com.ruoyi.utility.FileUtility;

public class Computation {
	private GodClassRule godClassRule;
	private ComplexClassRule complexClassRule;
	private SpaghettiCodeRule spaghettiCodeRule;
	private ClassDataShouldBePrivateRule classDataShouldBePrivateRule;
	private FunctionalDecompositionRule functionalDecompositionRule;
	private LongMethodRule longMethodRule;
	private FeatureEnvyRule featureEnvyRule;
	private PromiscuousPackageRule promiscuousPackageRule;
	private MispacedClassRule misplacedClassRule;

	public Computation() {
		this.godClassRule = new GodClassRule();
		this.complexClassRule = new ComplexClassRule();
		this.spaghettiCodeRule = new SpaghettiCodeRule();
		this.classDataShouldBePrivateRule = new ClassDataShouldBePrivateRule();
		this.longMethodRule = new LongMethodRule();
		this.functionalDecompositionRule = new FunctionalDecompositionRule();
		this.featureEnvyRule = new FeatureEnvyRule();
		this.promiscuousPackageRule = new PromiscuousPackageRule();
		this.misplacedClassRule = new MispacedClassRule();
	}

	public void computeMethodLevelSmellRulesAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
		computeFeatureEnvy(pPackages, pOutputPath, commitHash);
		computeLongMethodAppend(pPackages, pOutputPath, changedFileList, commitHash);
	}

	public void computeFeatureEnvyAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		int featureEnvyCount = 0;
		int curMethod = 0;
		for(ClassBean classBean: system) {
			ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
				if (!changedFileList.contains(classBean.getFilePath())) {
					continue;
				}
			for(MethodBean methodBean: classBean.getMethods()) {
				curMethod++;
				System.out.println("当期计算的方法是： "+ curMethod);
				double maxSimi = 0.0;
				String maxSimiClass = "";
				double belongSimi = computeSimilarity(methodBean, classBean);

				String methodName = methodBean.getName();
				String className = classBean.getBelongingPackage()+"/"+classBean.getName();
				String classPath = classBean.getFilePath();
				if (!Main.tempFeatureEnvy.containsKey(classPath)) {
					Main.tempFeatureEnvy.put(classPath, new HashMap<String, HashMap<String,Double>>());
				}
				Main.tempFeatureEnvy.get(classPath).put(className+"/"+methodName, new HashMap<String, Double>());

				for(ClassBean targetClass: system) {
					if (targetClass.getName() == classBean.getName()) {
						continue;
					}
					double similarityWithCandidateEnvyPackage = computeSimilarity(methodBean, targetClass);
					if (similarityWithCandidateEnvyPackage > maxSimi)
					{
						maxSimi = similarityWithCandidateEnvyPackage;
						maxSimiClass = targetClass.getFilePath();
					}
				}
				if(maxSimi <= belongSimi)
				{
					continue;
				}
				double simiDiff = maxSimi - belongSimi;
				if (simiDiff > 0.5) {
					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName()+"/"+methodBean.getName(), methodBean.getStartLine()+"\n"+methodBean.getEndLine()+"\n"+maxSimiClass);
					resArrayList.add(smellInfo);
					featureEnvyCount++;
				}
			}
			if (resArrayList.size() > 0) {
				Main.resHashMap.get(commitHash).get("featureEnvy").put(classBean.getFilePath(), resArrayList);
			}
		}
			for (String string : Main.removeFileList) {
				Main.resHashMap.get(commitHash).get("featureEnvy").put(string, new ArrayList<SmellInfo>());
				Main.tempFeatureEnvy.remove(string);
			}
		System.out.println("featureEnvyCount f"
				+ "inally: "+featureEnvyCount);
	}

	public void computeLongMethodAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		int longMethodCount = 0;
		int curMethod = 0;
		int totalMethod = 0;
		System.out.println("packages: "+pPackages.size());
		for(ClassBean classBean: system) {
			//System.out.println("当前class: "+ classBean.getName() );
			ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
			if (!changedFileList.contains(classBean.getFilePath())) {
				continue;
			}
			for(MethodBean methodBean: classBean.getMethods()) {
				curMethod++;
				System.out.println("当期计算的方法是： "+ curMethod +"  总数： "+totalMethod);
				HashMap<String, Double> simiMap = this.longMethodRule.isLongMethod(methodBean);
				boolean isLongMethodStr = this.longMethodRule.isLongMethod(methodBean,true);
				List<DataNode> NodeList = this.longMethodRule.getNodeList(methodBean);
				if (simiMap != null && isLongMethodStr && NodeList != null) {
					curMethod++;
					DBScan dbScan = new DBScan(simiMap);
					dbScan.cluster(NodeList);
					String simiInfo = "建议修改代码段：";
					boolean hasContent = false;
					for (DataNode node : NodeList) {
						//simiInfo += node.name+":"+node.getCategory()+";";
						if (node.getCategory() == -1) {
							simiInfo += "\n";
							simiInfo += node.name;
							hasContent = true;
						}
					}
					if (!hasContent){
						simiInfo = "";
					}
					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage() + "/" + classBean.getName()+"/"+methodBean.getName(), methodBean.getStartLine()+"\n"+methodBean.getEndLine()+"\n"+simiInfo);
					resArrayList.add(smellInfo);
					//Main.resHashMap.get(commitHash).get("longMethod").put(classBean.getFilePath(), resArrayList);
				}
			}

//			for(MethodBean methodBean: classBean.getMethods()) {
//				//System.out.println("当前method: "+ methodBean.getName() );
//				curMethod++;
//				System.out.println("当期计算的方法是： "+ curMethod +"  总数： "+totalMethod);
//				Double isLongMethod = this.longMethodRule.isLongMethod(methodBean);
//				boolean isLongMethodStr = this.longMethodRule.isLongMethod(methodBean,true);
//				if (isLongMethod > 0.7 && isLongMethodStr) {
//					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName()+"/"+methodBean.getName(), isLongMethod.toString());
//					resArrayList.add(smellInfo);
//					longMethodCount++;
//				}
//			}
			if (resArrayList.size() > 0) {
				Main.resHashMap.get(commitHash).get("longMethod").put(classBean.getFilePath(), resArrayList);
			}
		}
		for (String string : Main.removeFileList) {
			Main.resHashMap.get(commitHash).get("longMethod").put(string, new ArrayList<SmellInfo>());
		}
		System.out.println("longMethodCount f"
				+ "inally: "+longMethodCount);
	}

	public void computeBlobAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		int curClass = 0;
		int totalClass = system.size();
		int blobNum = 0;
		for(ClassBean classBean: system) {
			curClass++;
			if (!changedFileList.contains(classBean.getFilePath())) {
				continue;
			}
			if(classBean.getMethods().size() <= 2) continue;
			System.out.println("当期计算的类是： "+ curClass +"  总数： "+totalClass);
			HashMap<String, Double> simiMap = this.godClassRule.isGodClass(classBean);
			List<DataNode> NodeList = this.godClassRule.getNodeList(classBean);
			if (simiMap != null && NodeList != null) {
				blobNum++;
				ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
				DBScan dbScan = new DBScan(simiMap);
				dbScan.cluster(NodeList);
				String simiInfo ="建议修改以下几个方法：";
				boolean hasContent = false;
				for (DataNode node : NodeList)
				{
					//simiInfo += node.name+":"+node.getCategory()+";";
					if (node.getCategory() == -1)
					{
						hasContent = true;
						simiInfo += "\n";
						simiInfo += node.name;
					}
				}
				if(!hasContent)
				{
					simiInfo = "";
				}
				SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName(), classBean.getStartLine()+"\n"+classBean.getEndLine()+"\n"+simiInfo);
				resArrayList.add(smellInfo);
				Main.resHashMap.get(commitHash).get("blob").put(classBean.getFilePath(), resArrayList);
			}
		}
		for (String string : Main.removeFileList) {
			Main.resHashMap.get(commitHash).get("blob").put(string, new ArrayList<SmellInfo>());
		}
		System.out.println("BlobClassCount finally: "+blobNum);
	}

	public void computeMisplacedClassAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}

		int MisplacedClassCount = 0;
		int curClass = 0;
		int totalClass = system.size();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
				curClass++;
				System.out.println("当期计算的方法是： "+ curClass +"  总数： "+totalClass);
				double maxSimi = 0.0;
				String maxSimiPackage = "";
				double belongSimi = computeSimilarity(classBean, packageBean);
				for(PackageBean targetPackageBean: pPackages) {
					if (targetPackageBean.getName() == packageBean.getName()) {
						continue;
					}
					double similarityWithCandidateEnvyPackage = computeSimilarity(classBean, targetPackageBean);
					if (similarityWithCandidateEnvyPackage > maxSimi)
					{
						maxSimi = similarityWithCandidateEnvyPackage;
						maxSimiPackage = targetPackageBean.getName();
					}
				}
				if(maxSimi <= belongSimi)
				{
					continue;
				}
				double simiDiff = maxSimi - belongSimi;
				System.out.println(simiDiff);
				if (simiDiff > 0.5) {
					MisplacedClassCount++;
					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName(), classBean.getStartLine()+"\n"+classBean.getEndLine()+"\n"+maxSimiPackage);
					resArrayList.add(smellInfo);
					Main.resHashMap.get(commitHash).get("misplacedClass").put(classBean.getFilePath(), resArrayList);
					//System.out.println("MisplacedClass detected : "+ classBean.getFilePath());
				}
			}
		}
		System.out.println("MisplacedClassCount finally: "+MisplacedClassCount);
	}

	public void computeClassLevelSmellRulesAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
		computeBlobAppend(pPackages, pOutputPath , changedFileList, commitHash);
		computeMisplacedClass(pPackages, pOutputPath , commitHash);
	}

	public void computePackageLevelSmellRulesAppend(Vector<PackageBean> pPackages, String pOutputPath, ArrayList<String> changedFileList, String commitHash) {
//		int promiscuousPackageNum = 0;
//		int totalPackageNum = pPackages.size();
//		int currPackage = 0;
//		ArrayList<String> changedPackageArrayList = getChangedPackageFromPath(changedFileList);
//		for(PackageBean packageBean: pPackages) {
//			ArrayList<String> resArrayList = new ArrayList<String>();
//			if (!changedPackageArrayList.contains(packageBean.getName())) {
//				continue;
//			}
//			currPackage++;
//			System.out.println("当前计算第 "+currPackage+" 个包，总数 "+totalPackageNum);
//			boolean isPromiscuousPackage = this.promiscuousPackageRule.isPromiscuousPackage(packageBean);
//			if (isPromiscuousPackage) {
//				promiscuousPackageNum++;
//				resArrayList.add(packageBean.getName());
//				Main.resHashMap.get(commitHash).get("promiscuousPackage").put(packageBean.getName(), resArrayList);
//				System.out.println(" promiscuousPackage  detected : "+ packageBean.getName()+"  num: "+promiscuousPackageNum);
//			}
//		}
//		for (String string : Main.removeFileList) {
//			Main.resHashMap.get(commitHash).get("promiscuousPackage").put(string, new ArrayList<String>());
//		}
//		System.out.println("promiscuousPackageCount finally: "+promiscuousPackageNum);
	}

	//以下是全量计算

	public void computeMethodLevelSmellRules(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		computeFeatureEnvy(pPackages, pOutputPath, commitHash);
		computeLongMethod(pPackages, pOutputPath, commitHash);
	}

	public void computeClassLevelSmellRules(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		computeBlob(pPackages, pOutputPath , commitHash);
		computeMisplacedClass(pPackages, pOutputPath , commitHash);
	}

	public void computeFeatureEnvy(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		int featureEnvyCount = 0;
		int curMethod = 0;
		for(ClassBean classBean: system) {
			ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
			for(MethodBean methodBean: classBean.getMethods()) {
				curMethod++;
				System.out.println("当期计算的方法是： "+ curMethod);
				double maxSimi = 0.0;
				double belongSimi = computeSimilarity(methodBean, classBean);
				String maxSimiClass = "";
				String methodName = methodBean.getName();
				String className = classBean.getBelongingPackage()+"/"+classBean.getName();
				String classPath = classBean.getFilePath();
				if (!Main.tempFeatureEnvy.containsKey(classPath)) {
					Main.tempFeatureEnvy.put(classPath, new HashMap<String, HashMap<String,Double>>());
				}
				Main.tempFeatureEnvy.get(classPath).put(className+"/"+methodName, new HashMap<String, Double>());

				for(ClassBean targetClass: system) {
					if (targetClass.getName() == classBean.getName()) {
						continue;
					}
					double similarityWithCandidateEnvyPackage = computeSimilarity(methodBean, targetClass);
					if (similarityWithCandidateEnvyPackage > maxSimi)
					{
						maxSimi = similarityWithCandidateEnvyPackage;
						maxSimiClass = targetClass.getFilePath();
					}
				}
				if(maxSimi <= belongSimi)
				{
					continue;
				}
				double simiDiff = maxSimi - belongSimi;
				if (simiDiff > 0.5) {
					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName()+"/"+methodBean.getName(), methodBean.getStartLine()+"\n"+methodBean.getEndLine()+"\n"+maxSimiClass);
					resArrayList.add(smellInfo);
					featureEnvyCount++;
				}
			}
			if (resArrayList.size() > 0) {
				Main.resHashMap.get(commitHash).get("featureEnvy").put(classBean.getFilePath(), resArrayList);
			}

		}
//			for (String string : Main.removeFileList) {
//				Main.resHashMap.get(commitHash).get("featureEnvy").put(string, new ArrayList<String>());
//				Main.tempFeatureEnvy.remove(string);
//			}
		System.out.println("featureEnvyCount f"
				+ "inally: "+featureEnvyCount);
	}

	public void computeLongMethod(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		int longMethodCount = 0;
		int curMethod = 0;
		int totalMethod = 0;
		System.out.println("packages: "+pPackages.size());
		for(ClassBean classBean: system) {
			//System.out.println("当前class: "+ classBean.getName() );
			ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
			for(MethodBean methodBean: classBean.getMethods()) {
				curMethod++;
				System.out.println("当期计算的方法是： "+ curMethod +"  总数： "+totalMethod);
				HashMap<String, Double> simiMap = this.longMethodRule.isLongMethod(methodBean);
				boolean isLongMethodStr = this.longMethodRule.isLongMethod(methodBean,true);
				List<DataNode> NodeList = this.longMethodRule.getNodeList(methodBean);
				if (simiMap != null && isLongMethodStr ) {
					curMethod++;
					DBScan dbScan = new DBScan(simiMap);
					dbScan.cluster(NodeList);
					String simiInfo = "";
					for (DataNode node : NodeList) {
						simiInfo += node.name+":"+node.getCategory()+";";
					}
					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage() + "/" + classBean.getName()+"/"+methodBean.getName(), methodBean.getStartLine()+"\n"+methodBean.getEndLine()+"\n"+simiInfo);
					resArrayList.add(smellInfo);
					//Main.resHashMap.get(commitHash).get("longMethod").put(classBean.getFilePath(), resArrayList);
				}
			}
			if (resArrayList.size() > 0) {
				Main.resHashMap.get(commitHash).get("longMethod").put(classBean.getFilePath(), resArrayList);
			}
		}
		for (String string : Main.removeFileList) {
			Main.resHashMap.get(commitHash).get("longMethod").put(string, new ArrayList<SmellInfo>());
		}
		System.out.println("longMethodCount f"
				+ "inally: "+longMethodCount);
	}

	public void computeBlob(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		for(ClassBean classBean: system) {
			if(classBean.getMethods().size() <= 2) continue;
			HashMap<String, Double> simiMap = this.godClassRule.isGodClass(classBean);
			List<DataNode> NodeList = this.godClassRule.getNodeList(classBean);
			if (simiMap != null && NodeList != null) {
				ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
				DBScan dbScan = new DBScan(simiMap);
				dbScan.cluster(NodeList);
				String simiInfo ="";
				for (DataNode node : NodeList)
				{
					simiInfo += node.name+":"+node.getCategory()+";";
				}
				SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName(), classBean.getStartLine()+"\n"+classBean.getEndLine()+"\n"+simiInfo);
				resArrayList.add(smellInfo);
				Main.resHashMap.get(commitHash).get("blob").put(classBean.getFilePath(), resArrayList);
			}
		}
	}

	public void computeMisplacedClass(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		Vector<ClassBean> system = new Vector<ClassBean>();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				system.add(classBean);
			}
		}
		int MisplacedClassCount = 0;
		int curClass = 0;
		int totalClass = system.size();
		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
				curClass++;
				System.out.println("当期计算的方法是： "+ curClass +"  总数： "+totalClass);
				double maxSimi = 0.0;
				String maxSimiPackage = "";
				double belongSimi = computeSimilarity(classBean, packageBean);
				for(PackageBean targetPackageBean: pPackages) {
					if (targetPackageBean.getName() == packageBean.getName()) {
						continue;
					}
					double similarityWithCandidateEnvyPackage = computeSimilarity(classBean, targetPackageBean);
					if (similarityWithCandidateEnvyPackage > maxSimi)
					{
						maxSimi = similarityWithCandidateEnvyPackage;
						maxSimiPackage = targetPackageBean.getName();
					}
				}
				if(maxSimi <= belongSimi)
				{
					continue;
				}
				double simiDiff = maxSimi - belongSimi;
				System.out.println(simiDiff);
				if (simiDiff > 0.5) {
					MisplacedClassCount++;
					SmellInfo smellInfo = new SmellInfo(classBean.getBelongingPackage()+"/"+classBean.getName(), classBean.getStartLine()+"\n"+classBean.getEndLine()+"\n"+maxSimiPackage);
					resArrayList.add(smellInfo);
					Main.resHashMap.get(commitHash).get("misplacedClass").put(classBean.getFilePath(), resArrayList);
					//System.out.println("MisplacedClass detected : "+ classBean.getFilePath());
				}
			}
		}
		System.out.println("MisplacedClassCount finally: "+MisplacedClassCount);
	}

	public void computePackageLevelSmellRules(Vector<PackageBean> pPackages, String pOutputPath, String commitHash) {
		int promiscuousPackageNum = 0;
		int totalPackageNum = pPackages.size();
		int currPackage = 0;
		for (PackageBean packageBean : pPackages) {
			currPackage++;
			System.out.println("当前计算第 " + currPackage + " 个包，总数 " + totalPackageNum);
			HashMap<String, Double> simiMap = this.promiscuousPackageRule.isPromiscuousPackage(packageBean);
			List<DataNode> NodeList = this.promiscuousPackageRule.getNodeList(packageBean);
			if (simiMap != null && NodeList != null) {
				promiscuousPackageNum++;
				ArrayList<SmellInfo> resArrayList = new ArrayList<SmellInfo>();
				DBScan dbScan = new DBScan(simiMap);
				dbScan.cluster(NodeList);
				String simiInfo = "";
				for (DataNode node : NodeList) {
					//simiInfo += node.name+":"+node.getCategory()+";";
					simiInfo += node.name+".java"+":"+node.getCategory()+";";
				}
				SmellInfo smellInfo = new SmellInfo(packageBean.getName(), simiInfo);
				resArrayList.add(smellInfo);
				Main.resHashMap.get(commitHash).get("promiscuousPackage").put(packageBean.getName(), resArrayList);
			}
		}
		System.out.println("promiscuousPackageCount finally: " + promiscuousPackageNum);
	}

	private double computeSimilarity(MethodBean pMethod, ClassBean pClass) {
		CosineSimilarity cosineSimilarity= new CosineSimilarity();

		String[] methodInfo=new String[2];
		methodInfo[0] = pMethod.getName();
		methodInfo[1] = pMethod.getProcessedText();

		String[] belongingClassInfo=new String[2];
		belongingClassInfo[0] = pClass.getName();
		belongingClassInfo[1] = pClass.getProcessedText();

		try {
			return cosineSimilarity.computeSimilarity(methodInfo, belongingClassInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0.0;
	}

	private double computeSimilarity(ClassBean pClass, PackageBean pPackage) {
		CosineSimilarity cosineSimilarity= new CosineSimilarity();

		String[] classInfo=new String[2];
		classInfo[0] = pClass.getName();
		classInfo[1] = pClass.getProcessedText();

		String[] belongingPackageInfo=new String[2];
		belongingPackageInfo[0] = pPackage.getName();
		belongingPackageInfo[1] = pPackage.getProcessedText();

		try {
			return cosineSimilarity.computeSimilarity(classInfo, belongingPackageInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0.0;
	}

	private void writeFile(File pFile, String pContent) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(pFile, true);
			PrintWriter writer = new PrintWriter(fileWriter);

			writer.println(pContent);
			fileWriter.close();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getChangedClassFromPath(ArrayList<String> changedFileList)
	{
		ArrayList<String> resArrayList = new ArrayList<String>();
		for (String string : changedFileList) {
			//Get file Path
			String filePath = string;
			CodeParser codeParser = new CodeParser();
			CompilationUnit parsed;
			try {
				parsed = codeParser.createParser(FileUtility.readFile(filePath));
				PackageDeclaration packetDec = parsed.getPackage();
				int ClassNum = parsed.types().size();
				for (int i = 0; i < ClassNum; i++) {
					TypeDeclaration typeDeclaration = (TypeDeclaration)parsed.types().get(i);
					//get Class Name
					String className = typeDeclaration.getName().toString();
					resArrayList.add(packetDec.getName()+"/"+className);
				}
			} catch (Exception e) {
				// do nothing
			}
		}
		return resArrayList;
	}

	public ArrayList<String> getChangedMethodFromPath(ArrayList<String> changedFileList)
	{
		ArrayList<String> resArrayList = new ArrayList<String>();
		for (String string : changedFileList) {
			//Get file Path
			String filePath = string;
			CodeParser codeParser = new CodeParser();
			CompilationUnit parsed;
			try {
				parsed = codeParser.createParser(FileUtility.readFile(filePath));
				PackageDeclaration packetDec = parsed.getPackage();
				int ClassNum = parsed.types().size();
				for (int i = 0; i < ClassNum; i++) {
					TypeDeclaration typeDeclaration = (TypeDeclaration)parsed.types().get(i);
					//get Class Name
					String className = typeDeclaration.getName().toString();
					//get Method Declaration
					MethodDeclaration methodDec[] = typeDeclaration.getMethods();
					for (MethodDeclaration method : methodDec) {
						resArrayList.add(packetDec.getName()+"/"+className+"/"+method.getName());
					}
				}
			} catch (Exception e) {
				// do nothing
			}
		}
		return resArrayList;
	}

	public ArrayList<String> getChangedPackageFromPath(ArrayList<String> changedFileList)
	{
		ArrayList<String> resArrayList = new ArrayList<String>();
		for (String string : changedFileList) {
			//Get file Path
			String filePath = string;
			CodeParser codeParser = new CodeParser();
			CompilationUnit parsed;
			try {
				parsed = codeParser.createParser(FileUtility.readFile(filePath));
				PackageDeclaration packetDec = parsed.getPackage();
				if (!resArrayList.contains(packetDec.getName().toString())) {
					resArrayList.add(packetDec.getName().toString());
				}
			} catch (Exception e) {
				// do nothing
			}
		}
		return resArrayList;
	}
}