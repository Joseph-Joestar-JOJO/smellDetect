package com.ruoyi.parser;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.beans.InstanceVariableBean;
import com.ruoyi.beans.MethodBean;
import com.ruoyi.metrics.CosineSimilarity;
import com.ruoyi.metrics.LongMethodDivideImpl;
import com.ruoyi.metrics.finalSegmentModel;
import com.ruoyi.metrics.segmentModel;
import com.ruoyi.utility.TestSmellUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodParser {

	public static MethodBean parse(MethodDeclaration pMethodNode, Collection<InstanceVariableBean> pClassInstanceVariableBeans,CompilationUnit parsed) throws IOException {

		// Instantiate the bean
		MethodBean methodBean = new MethodBean();

		// Set the name
		methodBean.setName(pMethodNode.getName().toString());

		methodBean.setParameters(pMethodNode.parameters());
		methodBean.setReturnType(pMethodNode.getReturnType2());

		// Set the textual content
		methodBean.setTextContent(pMethodNode.toString());
		CosineSimilarity cSimilarity = new CosineSimilarity();
		methodBean.setProcessedText(cSimilarity.normalize(methodBean.getTextContent()));
		
		//�ָ��
		List list = pMethodNode.getBody().statements();
		LongMethodDivideImpl divideImpl = new LongMethodDivideImpl();
		finalSegmentModel resFinalSegmentModel = divideImpl.returnFinalSegment(list);
		int SegmentSize = resFinalSegmentModel.getStatements().size();
		int startLine = pMethodNode.getBody().getStartPosition();
		if (SegmentSize > 1) {
			String[] segmentString = new String[SegmentSize];
			String[] tempString = new String[SegmentSize];
			String[] segmentLine = new String[SegmentSize];
			for (int i = 0; i < SegmentSize; i++) {
				segmentModel model = (segmentModel) resFinalSegmentModel.getStatements().get(i);
				int size = model.getStatements().size();
				String temp = "";
				int end = 0;
				ASTNode node = (ASTNode)list.get((Integer) model.getStatements().get(0));
				int start = node.getStartPosition();
				int length = 0;
				for (int j = 0; j < size; j++) {
					int k = (Integer) model.getStatements().get(j);
					ASTNode nodeTemp = (ASTNode)list.get(k);
					temp += list.get(k);
					length += nodeTemp.getLength();
				}
				int startL = parsed.getLineNumber(start);
				int endL = parsed.getLineNumber(start+length);
				segmentLine[i] =  startL+"/"+ endL;
				tempString[i] = cSimilarity.normalize(temp);
				segmentString[i] = temp;
			}
			methodBean.setSegmentContent(tempString);
			methodBean.setSegmentLine(segmentLine);
			methodBean.setsegmentContentOrigin(segmentString);
		}
		
		// Get the names in the method
		Collection<String> names = new HashSet<String>();
		pMethodNode.accept(new NameVisitor(names));

		// Verify the correspondence between names and instance variables 
		Collection<InstanceVariableBean> usedInstanceVariableBeans = getUsedInstanceVariable(names, pClassInstanceVariableBeans);

		// Set the used instance variables
		methodBean.setUsedInstanceVariables(usedInstanceVariableBeans);

		// Get the invocation names
		Collection<MethodBean> invocations = new HashSet<MethodBean>();
		pMethodNode.accept(new InvocationVisitor(invocations));

		// Get the invocation beans from the invocation names
		Collection<MethodBean> invocationBeans = new Vector<MethodBean>();
		for (MethodBean invocation : invocations){
			invocationBeans.add(invocation);
		}

		// Set the invocations
		methodBean.setMethodCalls(invocationBeans);

		// Return the bean
		return methodBean;

	}

	private static Collection<InstanceVariableBean> getUsedInstanceVariable(Collection<String> pNames, Collection<InstanceVariableBean> pClassInstanceVariableBeans) {

		// Instantiate the collection to return
		Collection<InstanceVariableBean> usedInstanceVariableBeans = new Vector<InstanceVariableBean>();

		// Iterate over the instance variables defined in the class
		for (InstanceVariableBean classInstanceVariableBean : pClassInstanceVariableBeans)

			// If there is a correspondence, add to the returned collection
			if (pNames.remove(classInstanceVariableBean.getName()))
				usedInstanceVariableBeans.add(classInstanceVariableBean);

		// Return the collection
		return usedInstanceVariableBeans;
	}
	
	public static void setOtherInfo(MethodBean pInvocation, ClassBean pTestClass, Vector<ClassBean> pSystem) {
		Collection<MethodBean> toCover = pTestClass.getMethods();
		
		// step 1: finding production class using naming conventions
		ClassBean candidateProductionClass = TestSmellUtilities.findProductionClassUsingNamingConventions(pTestClass, pSystem);
		
		if(candidateProductionClass != null) {
			
			for(MethodBean methodBean: toCover) {
				if(methodBean.getName().equals(pInvocation.getName())) {
					pInvocation.setBelongingClass(methodBean.getBelongingClass());
				}
			}
			
		}
		
		ArrayList<String> imported = new ArrayList<String>();

		
		for(String imp: pTestClass.getImports()) {
			imported.add(imp.substring(imp.indexOf(" ")+1, imp.length()-2));
		}

		for(ClassBean classBean : pSystem) {
			if(imported.contains(classBean.getBelongingPackage()+"."+classBean.getName())) {
				for(MethodBean methodBean : classBean.getMethods()) {
					if(methodBean.getName().equals(pInvocation.getName())) {
						pInvocation.setBelongingClass(methodBean.getBelongingClass());
					}
				}
			}
		}

		if(pInvocation.getBelongingClass()==null) {
			for(ClassBean classBean : pSystem) {
				if(pTestClass.getTextContent().contains(classBean.getName())) {
					for(MethodBean methodBean : classBean.getMethods()) {
						if(methodBean.getName().equals(pInvocation.getName())) {

							int commas = 0;
							for(int i = 0; i < pInvocation.getTextContent().length(); i++) {
								if(pInvocation.getTextContent().charAt(i) == ',') commas++;
							}

							if((commas+1) == methodBean.getParameters().size()) {
								pInvocation.setBelongingClass(methodBean.getBelongingClass());
							}

							pInvocation.setBelongingClass(methodBean.getBelongingClass());
						}
					}
				}
			}
		}
	}
}
