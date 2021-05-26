package com.ruoyi.badSmellRules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.beans.MethodBean;
import com.ruoyi.beans.PackageBean;
import com.ruoyi.metrics.CosineSimilarity;
import com.ruoyi.utility.DataNode;

public class PromiscuousPackageRule {
	//�ı�PromiscuousPackage��ζ����
	public HashMap<String, Double> isPromiscuousPackage(PackageBean packageBean) {
		try {
		CosineSimilarity cosineSimilarity= new CosineSimilarity();
		List<ClassBean> classBeans = new ArrayList<ClassBean>(packageBean.getClasses());
		double sumDouble = 0.0;
		int count = 0;
		HashMap<String, Double> simiMap = new HashMap<>();
		for (int i = 0; i < classBeans.size(); i++) {
			for (int j = i + 1; j < classBeans.size(); j++) {
				String[] class1 = new String[2];
				class1[0] = classBeans.get(i).getName();
				class1[1] = classBeans.get(i).getProcessedText();

				String[] class2 = new String[2];
				class2[0] = classBeans.get(j).getName();
				class2[1] = classBeans.get(j).getProcessedText();
				double value = cosineSimilarity.computeSimilarity(class1, class2);
				simiMap.put(class1[0]+" "+class2[0], value);
				simiMap.put(class2[0]+" "+class1[0], value);
				sumDouble += value;
				count++;
			}
		}
		double avgDouble = 1 - sumDouble / count;
		if(avgDouble > 0.7) {
			return simiMap;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<DataNode> getNodeList(PackageBean packageBean)
	{
		List<ClassBean> classBeans = new ArrayList<ClassBean>(packageBean.getClasses());
		ArrayList<DataNode> resArrayList = new ArrayList<>();
		for (int i = 0; i < classBeans.size(); i++) {
			DataNode node = new DataNode(classBeans.get(i).getName());
			resArrayList.add(node);
		}
		return resArrayList;
	}
}
