package com.ruoyi.badSmellRules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.beans.MethodBean;
import com.ruoyi.metrics.CKMetrics;
import com.ruoyi.metrics.CosineSimilarity;
import com.ruoyi.utility.DataNode;

/**
 * 
 * Implementation of DECOR's God Class Rule Card (reported in the following)
 * 
 * RULE_CARD: Blob {
	RULE: Blob {ASSOC: associated FROM: mainClass ONE
		TO: DataClass MANY}
	RULE: mainClass {UNION LargeClassLowCohesion ControllerClass}
	RULE: LargeClassLowCohesion {UNION LargeClass LowCohesion} 
	RULE: LargeClass {(METRIC: NMD + NAD, VERY_HIGH, 20)} 
	RULE: LowCohesion {(METRIC: LCOM5, VERY_HIGH, 20)}
	RULE: ControllerClass {UNION (SEMANTIC: METHODNAME,
		{Process, Control, Command, Manage, Drive, System}), (SEMANTIC: CLASSNAME, {Process, Control, Command, Manage, Drive, System}}
	RULE: DataClass {(STRUCT: METHOD_ACCESSOR, 90)} };
 * 
 * @author fabiopalomba
 *
 */
public class GodClassRule {
	
	//�ı�blob��ζ����
	public HashMap<String, Double> isGodClass(ClassBean pClass) {
		try {
		CosineSimilarity cosineSimilarity= new CosineSimilarity();
		List<MethodBean> methodBeans = new ArrayList<MethodBean>(pClass.getMethods());
		double sumDouble = 0.0;
		int count = 0;
		HashMap<String, Double> simiMap = new HashMap<>();
		for (int i = 0; i < methodBeans.size(); i++) {
			for (int j = i + 1; j < methodBeans.size(); j++) {
				String[] method1 = new String[2];
				method1[0] = methodBeans.get(i).getStartLine()+"/"+methodBeans.get(i).getEndLine();
				method1[1] = methodBeans.get(i).getProcessedText();

				String[] method2 = new String[2];
				method2[0] = methodBeans.get(j).getStartLine()+"/"+methodBeans.get(j).getEndLine();
				method2[1] = methodBeans.get(j).getProcessedText();
				double value = cosineSimilarity.computeSimilarity(method1, method2);
				simiMap.put(method1[0]+" "+method2[0], value);
				simiMap.put(method2[0]+" "+method1[0], value);
				sumDouble+=value;
				count++;
			}
		}
		double res = 1 - (sumDouble / count);
		if(res > 0.9) {
			return simiMap;
		}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<DataNode> getNodeList(ClassBean pClass)
	{
		List<MethodBean> methodBeans = new ArrayList<MethodBean>(pClass.getMethods());
		ArrayList<DataNode> resArrayList = new ArrayList<>();
		for (int i = 0; i < methodBeans.size(); i++) {
			DataNode node = new DataNode(methodBeans.get(i).getStartLine()+"/"+methodBeans.get(i).getEndLine());
			resArrayList.add(node);
		}
		return resArrayList;
	}

	public boolean isGodClass(ClassBean pClass, String pSystemType) {

		if(isControllerClass(pClass) || isLargeClassLowCohesion(pClass, pSystemType)) {
			return true;
		}

		return false;
	}

	private static boolean isLargeClassLowCohesion(ClassBean pClass, String pSystemType) {
		int featureSum = CKMetrics.getNOM(pClass) + CKMetrics.getNOA(pClass);

		if(pSystemType.equals("android")) {
			if( (CKMetrics.getLCOM(pClass) > 175) || (featureSum > 10)) {
				if(CKMetrics.getLOC(pClass) > 500)  
					return true;
			}
		} else {
			if( (CKMetrics.getLCOM(pClass) > 350) || (featureSum > 20)) {
				if(CKMetrics.getLOC(pClass) > 500)  
					return true;
			}
		}
		return false;
	}

	private static boolean isControllerClass(ClassBean pClass) {
		String pClassName = pClass.getName().toLowerCase();

		if( (pClassName.contains("process")) || (pClassName.contains("control") || pClassName.contains("command") 
				|| pClassName.contains("manage") || pClassName.contains("drive") || pClassName.contains("system"))) {
			int featureSum = CKMetrics.getNOM(pClass) + CKMetrics.getNOA(pClass);

			if( (CKMetrics.getLCOM(pClass) > 350) || (featureSum > 20)) {
				if(CKMetrics.getLOC(pClass) > 500)  
					return true;
			}
		}

		return false;
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
}