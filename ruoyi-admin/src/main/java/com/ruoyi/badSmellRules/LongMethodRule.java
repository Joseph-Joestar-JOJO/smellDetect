package com.ruoyi.badSmellRules;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.beans.MethodBean;
import com.ruoyi.metrics.CosineSimilarity;
import com.ruoyi.utility.DataNode;

public class LongMethodRule {

	//�ı�LongMethod��ζ����
	public HashMap<String, Double> isLongMethod(MethodBean pMethod) {
		try {
		CosineSimilarity cosineSimilarity= new CosineSimilarity();
		double sumDouble = 0.0;
		int count = 0;
		String[] segmentStrings = pMethod.getSegmentContent();
		String[] segmentStringsOrigin = pMethod.getsegmentContentOrigin();
		String[] segmentLines = pMethod.getSegmentLine();
		HashMap<String, Double> simiMap = new HashMap<>();
		if (segmentStrings == null) {
			return null;
		}
		for (int i = 0; i < segmentStrings.length; i++) {
			for (int j = i + 1; j < segmentStrings.length; j++) {
				String[] segment1 = new String[2];
				segment1[0] = segmentStringsOrigin[i];
				segment1[1] = segmentStrings[i];

				String[] segment2 = new String[2];
				segment2[0] = segmentStringsOrigin[j];
				segment2[1] = segmentStrings[j];
				double value = cosineSimilarity.computeSimilarity(segment1, segment2);
				simiMap.put(segmentLines[i]+" "+segmentLines[j], value);
				simiMap.put(segmentLines[j]+" "+segmentLines[i], value);
				sumDouble += value;
				count++;
			}
		}
		double avgDouble = 1 - sumDouble / count;
		if(avgDouble > 0.7) {
			//System.out.println(pMethod.getName()+"  "+avgDouble);
			return simiMap;
		}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<DataNode> getNodeList(MethodBean pMethod)
	{
		String[] methodLines = pMethod.getSegmentLine();
		if (methodLines==null)
		{
			return null;
		}
		ArrayList<DataNode> resArrayList = new ArrayList<>();
		for (int i = 0; i < methodLines.length; i++) {
			DataNode node = new DataNode(methodLines[i]);
			resArrayList.add(node);
		}
		return resArrayList;
	}
	
	public boolean isLongMethod(MethodBean pMethod, boolean isStructual) {
		String[] tokenizedTextualContent = pMethod.getTextContent().split("\n");
		
		if( (tokenizedTextualContent.length > 50) && (pMethod.getParameters().size() >= 2) )
			return true;
		
		return false;
	}
}