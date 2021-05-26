package com.ruoyi.metrics;
import org.eclipse.jdt.core.dom.*;
import java.util.*;

public class LongMethodDivideImpl implements LongMethodsDivide {

    List<segmentModel> segmentSynSList=new LinkedList<segmentModel>();
    List<segmentModel> segmentDFCList=new LinkedList<segmentModel>();
    List<segmentModel> segmentESWIFTList=new LinkedList<segmentModel>();
    public List<segmentModel> getSegmentSynSList(){ return segmentSynSList;}
    public List<segmentModel> getSegmentDFCList(){ return segmentDFCList;}
    public List<segmentModel> getSegmentESWIFTList(){ return segmentESWIFTList;}

    public finalSegmentModel returnFinalSegment(List statements){
        finalSegmentModel finalSegmentModel=new finalSegmentModel(segmentTypeEnum.FINAL,new ArrayList());
        returnFinalBlocksList(statements);
        if (segmentSynSList.size()>0){
            for (int i = 0; i < segmentSynSList.size(); i++) {
                finalSegmentModel.getStatements().add(segmentSynSList.get(i));
            }
        }
        if (segmentDFCList.size()>0){
            for (int i = 0; i < segmentDFCList.size(); i++) {
                finalSegmentModel.getStatements().add(segmentDFCList.get(i));
            }
        }
        if (segmentESWIFTList.size()>0){
            for (int i = 0; i < segmentESWIFTList.size(); i++) {
                finalSegmentModel.getStatements().add(segmentESWIFTList.get(i));
            }
        }
        segmentSynSList=null;segmentDFCList=null;segmentESWIFTList=null;
        Collections.sort(finalSegmentModel.getStatements());
        return finalSegmentModel;
    }

    //调用�?下四个方法，完成块的自动划分
    private void returnFinalBlocksList(List statements){
    	if (statements.isEmpty()) {
			return;
		}
        IdentifyInitialBlocks(statements);
        RemoveBlockOverLap(statements);
        RedefineSynsBlocks(statements);
        mergeShortBlock(statements);
    }
    /**
     * statements是一个集合，里面存放了一个方法体{}里的�?有代码块
     * 即分成多个list集合
     * @param statements
     */
    //划分statements
    private void IdentifyInitialBlocks(List statements) {
        splitSynSBlocks(statements);
        splitDFCBlocks(statements);
        splitESWIFTBlocks(statements);

    }
    //去除重叠�?
    private void RemoveBlockOverLap(List statements) {
        int sizeSynS=segmentSynSList.size(),sizeDFC=segmentDFCList.size(),sizeESWIFT=segmentESWIFTList.size();
        //先判断DFClist中有无overlap statement,先处理DFC-DFC块之间的overlap
        if(segmentDFCList.size()>1){
            for (int i = 1; i < segmentDFCList.size(); i++) {
                List list1 = segmentDFCList.get(i - 1).getStatements();
                List list2 = segmentDFCList.get(i).getStatements();
                if (Integer.parseInt(list1.get(list1.size()-1).toString())==Integer.parseInt(list2.get(0).toString())){
                    int k= Integer.parseInt(list2.get(0).toString());
                    if (isInit(statements.get(k))){
                        list1.remove(list1.size()-1);
                    }else{
                        //可优化提�?
                        for (int j = list1.size()-1; j >=0; j--) {
                            list2.add(0,list1.get(j));
                        }
                        segmentDFCList.remove(i-1);
                        i--;
                    }
                }else{
                    continue;
                }
            }
        }
        //判断syns与dfc的overlap
        if(segmentDFCList.size()>0){
            ok:
            for (int i = 0; i < segmentSynSList.size(); i++) {
                segmentModel segmentModelSynS = segmentSynSList.get(i);
                if(segmentModelSynS.getStatements().size()<=0) continue ;
                for (int j = 0; j < segmentDFCList.size(); j++) {
                    segmentModel segmentModelDFC = segmentDFCList.get(j);
                    if (!Collections.disjoint(segmentModelDFC.getStatements(),segmentModelDFC.getStatements())){
                        int dfcFirst= Integer.parseInt(segmentModelDFC.getStatements().get(0).toString());
                        int dfcLast=dfcFirst+segmentModelDFC.getStatements().size()-1;
                        int synsFirst= Integer.parseInt(segmentModelSynS.getStatements().get(0).toString());
                        int synsLast=synsFirst+segmentModelSynS.getStatements().size()-1;
                        //DFC块包含SynS�?
                        if (dfcFirst<=synsFirst && dfcLast>=synsLast){
                            segmentModelSynS.getStatements().clear();
                            segmentSynSList.remove(i);
                            i--;
                            continue ok ;
                        }
                        //SynS块包含DFC�?
                        else if (synsFirst <=dfcFirst && synsLast>=dfcLast){
                            //int index=segmentSynSList.indexOf(segmentModelSynS);
                            segmentModel segmentModel1=new segmentModel(segmentTypeEnum.SYNS,new ArrayList());
                            for (int a = 0;  a< dfcFirst-synsFirst; a++) {
                                segmentModel1.getStatements().add(segmentModelSynS.getStatements().get(a));
                            }
                            segmentModel segmentModel2=new segmentModel(segmentTypeEnum.SYNS,new ArrayList());
                            for (int a = segmentModelSynS.getStatements().size()-synsLast+dfcLast; a < segmentModelSynS.getStatements().size(); a++) {
                                segmentModel2.getStatements().add(segmentModelSynS.getStatements().get(a));
                            }
                            segmentSynSList.add(i,segmentModel2);
                            segmentSynSList.add(i,segmentModel1);
                            segmentModelSynS.getStatements().clear();
                            segmentSynSList.remove(i+2);
                            break;
                        }
                        //SynS与DFC,syns块中list的size必大�?2，因为与dfc块有交集，若�?1的话会在第一个if块中被吸收，因此必大�?2
                        else if (synsLast==dfcFirst){
                            Object statement1 = statements.get(synsLast);
                            Object statement2 = statements.get(synsLast - 1);
                            if (isHighSimilar(statement1,statement2)){
                                segmentModelDFC.getStatements().remove(0);
                            }else{
                                segmentModelSynS.getStatements().remove(segmentModelSynS.getStatements().size()-1);
                            }
                        }
                        //DFC与SynS
                        else if (dfcLast==synsFirst){
                            Object statement1 = statements.get(synsFirst);
                            Object statement2 = statements.get(synsFirst + 1);
                            if (isHighSimilar(statement1,statement2)){
                                segmentModelDFC.getStatements().remove(segmentModelDFC.getStatements().size()-1);
                            }else{
                                segmentModelSynS.getStatements().remove(0);
                            }
                        }
                    }else{
                        continue;
                    }
                }
            }
        }
        //判断syns与SWIFT之间的overlap
        if (segmentESWIFTList.size()>0){
            ok1:
            for (int i = 0; i < segmentSynSList.size(); i++) {
                segmentModel segmentModelSynS = segmentSynSList.get(i);
                for (int j = 0; j <  segmentESWIFTList.size(); j++) {
                    segmentModel segmentModelSwift = segmentESWIFTList.get(j);
                    if (!Collections.disjoint(segmentModelSynS.getStatements(),segmentModelSwift.getStatements())){
                        if (segmentModelSynS.getStatements().size()<=1) {
                                segmentModelSynS.getStatements().clear();
                                segmentSynSList.remove(i);
                                i--;
                                continue ok1 ;
                        }
                        Object statement1 = segmentModelSynS.getStatements().get(segmentModelSynS.getStatements().size()-1);
                        Object statement2 = segmentModelSynS.getStatements().get(segmentModelSynS.getStatements().size()-1);
                        if (isHighSimilar(statement1,statement2)){
                            segmentModelSwift.getStatements().remove(0);
                        }else{
                            segmentModelSynS.getStatements().remove(segmentModelSynS.getStatements().size()-1);
                        }
                    }
                    else {
                        if (i== segmentSynSList.size()-1 && j==segmentESWIFTList.size()-1){
                            break  ok1;
                        }else  continue  ;
                    }

                }
            }
        }
        //判断DFC块与swift块之间的overlap
        if (segmentDFCList.size()>0 && segmentESWIFTList.size()>0){
            for (int i = 0; i < segmentDFCList.size(); i++) {
                segmentModel segmentModelDFC = segmentDFCList.get(i);
                for (int j = 0; j < segmentESWIFTList.size(); j++) {
                    segmentModel segmentModelSwift = segmentESWIFTList.get(j);
                    if (!Collections.disjoint(segmentModelDFC.getStatements(),segmentModelSwift.getStatements())){
                        segmentModelSwift.getStatements().remove(0);
                    }else{
                        continue;
                    }
                }
            }
        }

    }
    //重分割SynS�?
    public void RedefineSynsBlocks(List statements) {
        //删除上一步产生的空块并重新分割SynS�?
        for (int i = 0; i < segmentSynSList.size(); i++) {
            if (segmentSynSList.get(i).getStatements().size()==0){
                segmentSynSList.remove(i);
                i--;
                continue;
            }
            if (segmentSynSList.get(i).getStatements().size()>3){
                for (int j = 2; j < segmentSynSList.get(i).getStatements().size(); j++) {
                    int similaritylevel1=similarityLevel(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(j-2).toString())),statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(j-1).toString())));
                    int similaritylevel2=similarityLevel(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(j-1).toString())),statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(j).toString())));
                    if (similaritylevel1==similaritylevel2){
//                    	segmentModel segmentModel=new segmentModel(segmentTypeEnum.SYNS,new ArrayList());
//                    	for (int k = 0; k < 3; k++) {
//                        	segmentModel.getStatements().add(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
//                            segmentSynSList.get(i).getStatements().remove(0);
//						}
//                    	segmentSynSList.add(i,segmentModel);
                        continue;
                    }else{
                        segmentModel segmentModel=new segmentModel(segmentTypeEnum.SYNS,new ArrayList());
                        if (similaritylevel1<similaritylevel2){
                            for (int k = 0; k < j-1; k++) {
                                segmentModel.getStatements().add(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
                                segmentSynSList.get(i).getStatements().remove(0);
                            }
                            segmentSynSList.add(i,segmentModel);
                            continue;

                        }else if (similaritylevel1>similaritylevel2){
                            for (int k = 0; k < j; k++) {
                                segmentModel.getStatements().add(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
                                segmentSynSList.get(i).getStatements().remove(0);
                            }
                            segmentSynSList.add(i,segmentModel);
                            continue;
                        }
                    }
                }
            }
        }

    }
    //聚合小SynS�?
    public void mergeShortBlock(List statements) {
        if (segmentSynSList.size()>=2){
            for (int i = 1; i < segmentSynSList.size(); i++) {
                if (segmentSynSList.get(i).getStatements().size()==1){
                    if (isInit(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString())))){
                        Object statement1=statements.get(Integer.parseInt(segmentSynSList.get(i-1).getStatements().get(segmentSynSList.get(i-1).getStatements().size()-1).toString()));
                        Object statement2=statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
                        if (isDeclare(statement1) ||isAssignment(statement1)){
                            if (isAssignment(statement1)){
                                Expression initializer = ((VariableDeclarationFragment) ((VariableDeclarationStatement) statement2).fragments().get(0)).getInitializer();
                                Expression rightHandSide = ((Assignment) ((ExpressionStatement) statement1).getExpression()).getRightHandSide();
                                String name1 = ((VariableDeclarationFragment) ((VariableDeclarationStatement) statement2).fragments().get(0)).getName().toString();
                                String name2 = ((Assignment) ((ExpressionStatement) statement1).getExpression()).getLeftHandSide().toString();
                                if (isSameCategory(initializer,rightHandSide) || ID_Similarity.IDsimilarity(name1,name2)){
                                    segmentSynSList.get(i-1).getStatements().add(segmentSynSList.get(i).getStatements().get(0));
                                    segmentSynSList.remove(i);
                                    i-=1;
                                    continue;
                                }
                            }
                            if (isDeclare(statement1)){
                                if (((VariableDeclarationStatement)statement1).getType().toString().equals(((VariableDeclarationStatement)statement2).getType().toString())){
                                    segmentSynSList.get(i-1).getStatements().add(segmentSynSList.get(i).getStatements().get(0));
                                    segmentSynSList.remove(i);
                                    i-=1;
                                    continue;
                                }
                            }
                        }
                    }
                    if (isDeclare(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString())))){
                        Object statement1=statements.get(Integer.parseInt(segmentSynSList.get(i-1).getStatements().get(segmentSynSList.get(i-1).getStatements().size()-1).toString()));
                        Object statement2=statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
                        if (isInit(statement1)){
                            if (((VariableDeclarationStatement)statement1).getType().toString().equals(((VariableDeclarationStatement)statement2).getType().toString())){
                                segmentSynSList.get(i-1).getStatements().add(segmentSynSList.get(i).getStatements().get(0));
                                segmentSynSList.remove(i);
                                i-=1;
                                continue;
                            }
                        }
                    }
                    if (isAssignment(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString())))){
                        Object statement1=statements.get(Integer.parseInt(segmentSynSList.get(i-1).getStatements().get(segmentSynSList.get(i-1).getStatements().size()-1).toString()));
                        Object statement2=statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
                        if (isInit(statement1)){
                            Expression initializer = ((VariableDeclarationFragment) ((VariableDeclarationStatement) statement1).fragments().get(0)).getInitializer();
                            Expression rightHandSide = ((Assignment) ((ExpressionStatement) statement2).getExpression()).getRightHandSide();
                            String name1 = ((VariableDeclarationFragment) ((VariableDeclarationStatement) statement1).fragments().get(0)).getName().toString();
                            String name2 = ((Assignment) ((ExpressionStatement) statement2).getExpression()).getLeftHandSide().toString();
                            if (isSameCategory(initializer,rightHandSide) || ID_Similarity.IDsimilarity(name1,name2)){
                                segmentSynSList.get(i-1).getStatements().add(segmentSynSList.get(i).getStatements().get(0));
                                segmentSynSList.remove(i);
                                i-=1;
                                continue;
                            }
                        }
                    }
                    if (isMethodCall(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString())))||
                    isObjectMethodCall(statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString())))){
                        Object statement1=statements.get(Integer.parseInt(segmentSynSList.get(i-1).getStatements().get(segmentSynSList.get(i-1).getStatements().size()-1).toString()));
                        Object statement2=statements.get(Integer.parseInt(segmentSynSList.get(i).getStatements().get(0).toString()));
                        if (isObjectMethodCall(statement1) || isMethodCall(statement1)){
                            SimpleName name1 = ( (MethodInvocation)((ExpressionStatement)statement1).getExpression()).getName();
                            SimpleName name2 = ( (MethodInvocation)((ExpressionStatement)statement2).getExpression()).getName();
                            List<String> strings1 = Arrays.asList(((MethodInvocation) ((ExpressionStatement) statement1).getExpression()).arguments().toString().split(","));
                            List<String> strings2 = Arrays.asList(((MethodInvocation) ((ExpressionStatement) statement2).getExpression()).arguments().toString().split(","));
                            if (ID_Similarity.IDsimilarity(name1.toString(),name2.toString()) || (!Collections.disjoint(strings1,strings2) && strings1.size()>0 &&strings2.size()>0)){
                                segmentSynSList.get(i-1).getStatements().add(segmentSynSList.get(i).getStatements().get(0));
                                segmentSynSList.remove(i);
                                i-=1;
                                continue;
                            }
                        }
                    }
                }
            }
        }

    }



    private int similarityLevel(Object statement1,Object statement2){
        int k=0;
        if (isInit(statement1) && isInit(statement2)){
            VariableDeclarationStatement statement11 = (VariableDeclarationStatement) statement1;
            VariableDeclarationStatement statement22 = (VariableDeclarationStatement) statement2;
            if (statement11.getType().toString().equals(statement22.getType().toString()) || RHSSimilarity(statement11,statement22)) {
                k += 1;
            }
            SimpleName name1 = ((VariableDeclarationFragment) statement11.fragments().get(0)).getName();
            SimpleName name2 = ((VariableDeclarationFragment) statement22.fragments().get(0)).getName();
            if ((statement11.getType().toString().equals(statement22.getType().toString()) && ID_Similarity.IDsimilarity(name1.toString(),name2.toString()))||
                    (statement11.getType().toString().equals(statement22.getType().toString()) && RHSSimilarity(statement11,statement22))){
                k+=1;
            }
            if (ID_Similarity.IDsimilarity(name1.toString(),name2.toString()) && RHSSimilarity(statement11,statement22) && statement11.getType().toString().equals(statement22.getType().toString())){
                k+=1;
            }

        }
        if (isDeclare(statement1) && isDeclare(statement2)){
            VariableDeclarationStatement statement11 = (VariableDeclarationStatement) statement1;
            VariableDeclarationStatement statement22 = (VariableDeclarationStatement) statement2;
            if (statement11.getType().toString().equals(statement22.getType().toString())) {
                k += 1;
            }
        }
        if (isAssignment(statement1) && isAssignment(statement2)){
            String name1 = ((Assignment) ((ExpressionStatement) statement1).getExpression()).getLeftHandSide().toString();
            String name2 = ((Assignment) ((ExpressionStatement) statement2).getExpression()).getLeftHandSide().toString();
            if (ID_Similarity.IDsimilarity(name1,name2) || RHSSimilarity(statement1,statement2))
                k+=1;
            if (ID_Similarity.IDsimilarity(name1,name2) && RHSSimilarity(statement1,statement2))
                k+=1;
        }
        if (isMethodCall(statement1) && isMethodCall(statement2)){
            SimpleName name1 = ((MethodInvocation) (((ExpressionStatement) statement1).getExpression())).getName();
            SimpleName name2 = ((MethodInvocation) (((ExpressionStatement) statement2).getExpression())).getName();
            if (name1.toString().equals(name2.toString()))
                k+=1;
        }
        if (isObjectMethodCall(statement1) && isObjectMethodCall(statement2)){
            String object1 = returnMethodInvocationSimpleName(((ExpressionStatement) statement1).getExpression());
            String object2 = returnMethodInvocationSimpleName(((ExpressionStatement) statement2).getExpression());
            String methodName1 = ((MethodInvocation) ((ExpressionStatement) statement1).getExpression()).getName().toString();
            String methodName2 = ((MethodInvocation) ((ExpressionStatement) statement1).getExpression()).getName().toString();
            if (object1.equals(object2) || methodName1.equals(methodName2))
                k+=1;
            if (object1.equals(object2) && methodName1.equals(methodName2))
                k+=1;
        }

        return k;
    }
    //判断syns块中与overlap相连的statement的相似度
    private boolean isHighSimilar(Object statement1,Object statement2){
        boolean flag=false;
        if (isInit(statement1) && isInit(statement2)){
            VariableDeclarationStatement statement11 = (VariableDeclarationStatement) statement1;
            VariableDeclarationStatement statement22 = (VariableDeclarationStatement) statement2;
            int k=0;
            if (statement11.getType().toString().equals(statement22.getType().toString())){
                k+=1;
                SimpleName name1 = ((VariableDeclarationFragment) statement11.fragments().get(0)).getName();
                SimpleName name2 = ((VariableDeclarationFragment) statement22.fragments().get(0)).getName();
                if (ID_Similarity.IDsimilarity(name1.toString(),name2.toString()) || RHSSimilarity(statement11,statement22)){
                    k+=1;
                }
                if (k>=2) flag=true;
            }
        }
        if (isDeclare(statement1) && isDeclare(statement2)){
            VariableDeclarationStatement statement11 = (VariableDeclarationStatement) statement1;
            VariableDeclarationStatement statement22 = (VariableDeclarationStatement) statement2;
            int k=0;
            if (statement11.getType().toString().equals(statement22.getType().toString())) {
                k += 1;
            }
            if (k==1) flag=true;
        }
        if (isAssignment(statement1) && isAssignment(statement2)){
            String name1 = ((Assignment) ((ExpressionStatement) statement1).getExpression()).getLeftHandSide().toString();
            String name2 = ((Assignment) ((ExpressionStatement) statement2).getExpression()).getLeftHandSide().toString();
            if (ID_Similarity.IDsimilarity(name1,name2) && RHSSimilarity(statement1,statement2)) flag=true;
        }
        if (isMethodCall(statement1) && isMethodCall(statement2)){
            SimpleName name1 = ((MethodInvocation) (((ExpressionStatement) statement1).getExpression())).getName();
            SimpleName name2 = ((MethodInvocation) (((ExpressionStatement) statement2).getExpression())).getName();
            if (name1.toString().equals(name2.toString())) flag=true;
        }
        if (isObjectMethodCall(statement1) && isObjectMethodCall(statement2)){
            String object1 = returnMethodInvocationSimpleName(((ExpressionStatement) statement1).getExpression());
            String object2 = returnMethodInvocationSimpleName(((ExpressionStatement) statement2).getExpression());
            String methodName1 = ((MethodInvocation) ((ExpressionStatement) statement1).getExpression()).getName().toString();
            String methodName2 = ((MethodInvocation) ((ExpressionStatement) statement1).getExpression()).getName().toString();
            if (object1.equals(object2) && methodName1.equals(methodName2)) flag=true;
        }
        return flag;
    }
    private boolean RHSSimilarity(Object statement1,Object statement2){
        boolean flag=false;
        if (isInit(statement1)){
            Expression initializer1 = ((VariableDeclarationFragment) ((VariableDeclarationStatement) statement1).fragments().get(0)).getInitializer();
            Expression initializer2 = ((VariableDeclarationFragment) ((VariableDeclarationStatement) statement2).fragments().get(0)).getInitializer();
            return isSameCategory(initializer1,initializer2);
        }else if (isAssignment(statement1)){
            Expression rightHandSide1 = ((Assignment) ((ExpressionStatement) statement1).getExpression()).getRightHandSide();
            Expression rightHandSide2 = ((Assignment) ((ExpressionStatement) statement1).getExpression()).getRightHandSide();
            return isSameCategory(rightHandSide1,rightHandSide2);
        }
        return flag;
    }
    private boolean isSameCategory(Expression expression1,Expression expression2){
        if(expression1 instanceof ClassInstanceCreation && expression1 instanceof  ClassInstanceCreation){
            return true;
        }else if(expression1 instanceof MethodInvocation && expression2 instanceof MethodInvocation){
            SimpleName name1 = ((MethodInvocation) expression1).getName();
            SimpleName name2 = ((MethodInvocation) expression1).getName();
            return ID_Similarity.IDsimilarity(name1.toString(),name2.toString());
        }else if (expression1 instanceof ArrayCreation && expression1 instanceof  ArrayCreation){
            return true;
        }else if (expression1 instanceof InfixExpression && expression1 instanceof  InfixExpression){
            return true;
        }else if (expression1 instanceof PostfixExpression && expression1 instanceof  PostfixExpression){
            return true;
        }else if (expression1 instanceof PrefixExpression && expression1 instanceof  PrefixExpression){
            return true;
        }else if (expression1 instanceof CastExpression && expression1 instanceof  CastExpression){
            return true;
        }else if (expression1 instanceof QualifiedName && expression1 instanceof  QualifiedName){
            return true;
        }else if (expression1 instanceof StringLiteral && expression1 instanceof  StringLiteral){
            return true;
        }else if (expression1 instanceof BooleanLiteral && expression1 instanceof  BooleanLiteral){
            return true;
        }else if (expression1 instanceof NumberLiteral && expression1 instanceof  NumberLiteral){
            return true;
        }else if (expression1 instanceof CharacterLiteral && expression1 instanceof  CharacterLiteral){
            return true;
        }
        return false;

    }


    //将集合分割成多个segment，每�?个segment都是�?个SynS�?
    private void splitSynSBlocks(List statements){
        for (int i = 0; i < statements.size(); i++) {
            segmentModel segmentModel=new segmentModel(segmentTypeEnum.SYNS,new ArrayList());
            Object statement = statements.get(i);
            if (i==statements.size()-1 && statements.get(statements.size()-1) instanceof ReturnStatement){
                segmentModel.getStatements().add(statements.size()-1);
                segmentSynSList.add(segmentModel);
                break;
            }
            if (isInit(statement)){
                while(isInit(statements.get(i))){
                    segmentModel.getStatements().add(i);
                    i++;
                    if (i==statements.size()){
                        break;
                    }
                }
                i--;
                segmentSynSList.add(segmentModel);
            }else if (isDeclare(statement)){
                while(isDeclare(statements.get(i))){
                    segmentModel.getStatements().add(i);
                    i++;
                    if (i==statements.size()){
                        break;
                    }
                }
                i--;
                segmentSynSList.add(segmentModel);
            }else if ( isAssignment(statement)){
                while(isAssignment(statements.get(i))){
                    segmentModel.getStatements().add(i);
                    i++;
                    if (i==statements.size()){
                        break;
                    }
                }
                i--;
                segmentSynSList.add(segmentModel);
            }else if ( isMethodCall(statement)){
                while(isMethodCall(statements.get(i))){
                    segmentModel.getStatements().add(i);
                    i++;
                    if (i==statements.size()){
                        break;
                    }
                }
                i--;
                segmentSynSList.add(segmentModel);
            }else if ( isObjectMethodCall(statement)){
                while(isObjectMethodCall(statements.get(i))){
                    segmentModel.getStatements().add(i);
                    i++;
                    if (i==statements.size()){
                        break;
                    }
                }
                i--;
                segmentSynSList.add(segmentModel);
            }
            else if(isSWIFT(statement)){
                continue;
            }
            else{
                while( isOther(statements.get(i))){
                    segmentModel.getStatements().add(i);
                    i++;
                    if (i==statements.size()){
                        break;
                    }
                }
                i--;
                segmentSynSList.add(segmentModel);
            }
        }

    }
    //将集合分割成多个segment，每�?个segment都是�?个DFC�?
    private void splitDFCBlocks(List statements){
        ok:
        for (int i = 0; i < statements.size(); i++){
            if (i<statements.size()-1){
                segmentModel segmentModel=new segmentModel(segmentTypeEnum.DFC,new ArrayList());
                int k=i;
                //注，有i=k-1语句的表明从该DFC快的�?后一句重新判断DFC�?
                if (isInit(statements.get(i))){
                    //返回初始化对象的simpleName
                    SimpleName name = returnVariableDeclaretionSimpleName(statements.get(i));
                    List<String> tempNameList=new ArrayList<String>();
                    //Initializer-n-use-n
                    while(statements.get(k+1)!=null&&isInit(statements.get(k+1))){
                        String str=((VariableDeclarationFragment) ((VariableDeclarationStatement) statements.get(k+1)).fragments().get(0)).getInitializer().toString();
                        List<String> strings = Arrays.asList(OperationStringUtils.clearStr(str).split(" "));
                        if (!strings.contains(name.toString())) {
                            tempNameList.add(returnVariableDeclaretionSimpleName(statements.get(k+1)).toString());
                            k++;
                        }else {
                            //避免接下来的while循环里出现了重合的多init语句
                            if (tempNameList.size()>0){
                                boolean flag=true;
                                for (int j  = 0; j < tempNameList.size(); j++) {
                                    if (!strings.contains(tempNameList.get(j))){
                                        flag=false;
                                    }
                                }
                                if (flag==true){
                                    segmentModel.getStatements().add(k);
                                    segmentModel.getStatements().add(k+1);
                                    for (int j = 0; j < tempNameList.size(); j++) {
                                        segmentModel.getStatements().add(k-1-j);
                                    }
                                    i=k;
                                    segmentDFCList.add(segmentModel);
                                    continue ok;
                                }else{
                                    continue ok;
                                }
                            }
                            else {
                                break ;
                            }
                        }
                    }
                    //Initialize-access
                    while(statements.get(k+1)!=null){
                        List<String> strings = Arrays.asList(OperationStringUtils.clearStr(statements.get(k + 1).toString()).split(" "));
                        if (isSWIFT(statements.get(k+1)) || isDeclare(statements.get(k+1)) ||(statements.get(k+1) instanceof ReturnStatement)){
                            if (segmentModel.getStatements().size()>0){
                                segmentModel.getStatements().add(0,i);
                                segmentDFCList.add(segmentModel);
                                i=k-1 ;
                                break ;
                            }else{
                                break ;
                            }
                        }
                        if (strings.contains(name.toString())){
//                            segmentModel.getStatements().add(k);
                            segmentModel.getStatements().add(k+1);
                            k++;
                            if (k==statements.size()-1){
                                break;
                            }
                        }else{
                            if (segmentModel.getStatements().size()>0){
                                segmentModel.getStatements().add(0,i);
                                segmentDFCList.add(segmentModel);
                                i=k-1;
                                break;
                            }else{
                                break;
                            }
                        }
                    }

                }
                //Define-use     Define-define
                else if (isAssignment(statements.get(i)) || isObjectMethodCall(statements.get(i))){
                    String name=" =D-u D-D";
                    if (isAssignment(statements.get(i))){
                        name=((Assignment)((ExpressionStatement)statements.get(i)).getExpression()).getLeftHandSide().toString();
                    }else if (isObjectMethodCall(statements.get(i))){
                        name=returnMethodInvocationSimpleName(((ExpressionStatement)statements.get(i)).getExpression());
                    }
//                    List<String> strings = Arrays.asList(OperationStringUtils.clearStr(statements.get(i + 1).toString()).split(" "));
                    while (statements.get(k+1)!=null && !isSWIFT(statements.get(k+1)) && Arrays.asList(OperationStringUtils.clearStr(statements.get(k + 1).toString()).split(" ")).contains(name)){
                        segmentModel.getStatements().add(k);
                        k++;
                        if (k==statements.size()-1){
                            break;
                        }
                    }
                    if (segmentModel.getStatements().size()>0){
                        segmentModel.getStatements().add(k);
                        segmentDFCList.add(segmentModel);
                        i=k-1;
                    }

                }
                else{
//                    System.out.println(111);
                    continue ;
                }
            }

        }
    }
    //将集合分割成多个segment,每一个segment都是�?个E-SWIFT�?
    private void splitESWIFTBlocks(List statements){
        int k=0;
        while (statements.get(k)!=null && isSWIFT(statements.get(k))){
            segmentModel segmentModel=new segmentModel(segmentTypeEnum.E_SWIFT,new ArrayList());
            segmentModel.getStatements().add(k);
            segmentESWIFTList.add(segmentModel);
            k++;
            if (k==statements.size()){
                break;
            }
        }
        for (int i = k+1; i < statements.size(); i++) {
            if (isSWIFT(statements.get(i))){
                segmentModel segmentModel=new segmentModel(segmentTypeEnum.E_SWIFT,new ArrayList());
                String name="E-SWIFT";
                if (isInit(statements.get(i - 1))) {
                    name=returnVariableDeclaretionSimpleName(statements.get(i-1)).toString();
                } else if (isAssignment(statements.get(i - 1))) {
                    name=((Assignment)((ExpressionStatement)statements.get(i-1)).getExpression()).getLeftHandSide().toString();
                } else if (isObjectMethodCall(statements.get(i - 1))) {
                    name=returnMethodInvocationSimpleName(((ExpressionStatement)statements.get(i-1)).getExpression());
                }
                /**
                 *            IfStatement || ForStatement ||
                 *            ryStatement || EnhancedForStatement ||
                 *            WhileStatement || SynchronizedStatement ||
                 *            DoStatement || SwitchStatement
                 */
                //IFStatement
                if (statements.get(i) instanceof IfStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((IfStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)) {
//                        Arrays.asList((OperationStringUtils.clearStr(((IfStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }
                //ForStatement
                if (statements.get(i) instanceof ForStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((ForStatement) statements.get(i)).getExpression()+" "+((ForStatement) statements.get(i)).initializers())).split(" ")).contains(name)){
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }
                //WhileStatement
                if (statements.get(i) instanceof WhileStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((WhileStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)){
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }
                //EnhancedForStatement
                if (statements.get(i) instanceof EnhancedForStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((EnhancedForStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)){
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }
                //WhileStatement
                if (statements.get(i) instanceof TryStatement){
                    segmentModel.getStatements().add(i);
                    segmentESWIFTList.add(segmentModel);
                }
                //SynchronizedStatement
                if (statements.get(i) instanceof SynchronizedStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((SynchronizedStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)){
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }
                //DoStatement
                if (statements.get(i) instanceof DoStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((DoStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)){
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }
                //SwitchStatement
                if (statements.get(i) instanceof SwitchStatement){
                    if(Arrays.asList((OperationStringUtils.clearStr(((SwitchStatement)statements.get(i)).getExpression().toString())).split(" ")).contains(name)){
                        segmentModel.getStatements().add(i-1);
                        segmentModel.getStatements().add(i);
                    }else {
                        segmentModel.getStatements().add(i);
                    }
                    segmentESWIFTList.add(segmentModel);
                }

            }
        }
    }

    /**
     * 返回init或�?�assignment的右�?
     * @param statement
     * @return
     */
    private String returnRHS(Object statement){
        StringBuilder sb=new StringBuilder();
        if (isInit(statement) || isAssignment(statement)){
            if (isInit(statement)) {
                sb.append(((VariableDeclarationFragment) ((VariableDeclarationStatement) statement).fragments().get(0)).getInitializer());
            }else {
                sb.append(((Assignment)((ExpressionStatement)statement).getExpression()).getRightHandSide());
            }
        }
        return sb.toString();
    }
    /**
     * 返回方法调用对象
     * @param expression
     */
    public String returnMethodInvocationSimpleName(Expression expression){
        if (expression instanceof SimpleName) {
            return expression.toString();
        } else if (expression instanceof ArrayAccess) {
            return (((ArrayAccess) expression).getArray()).toString();
        } else if (expression instanceof MethodInvocation) {
            return returnMethodInvocationSimpleName(((MethodInvocation) expression).getExpression());
        }else if (expression==null){
            return "null";
        }
        else {
            return expression.toString();
        }

    }
    /**
     * 返回方法的参数对�?
     * @param expression
     * @return
     */
    public String returnMethodInvocationArgumrntsSimple(Expression expression){
        if (expression instanceof MethodInvocation){
            List arguments = ((MethodInvocation) expression).arguments();
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < arguments.size(); i++) {
                if (arguments.get(i) instanceof  Expression){
                    sb.append(" "+returnMethodInvocationArgumrntsSimple((Expression) arguments.get(i)));
                }
            }
            return sb.toString();
        }else if (expression instanceof  ClassInstanceCreation){
            List arguments = ((ClassInstanceCreation) expression).arguments();
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < arguments.size(); i++) {
                if (arguments.get(i) instanceof  Expression){
                    sb.append(returnMethodInvocationArgumrntsSimple((Expression) arguments.get(i))+" ");
                }
            }
            return sb.toString();
        }else{
            return expression.toString();
        }
    }

    //必须在经过判定的方法执行后才能调用此方法
    private SimpleName returnVariableDeclaretionSimpleName(Object statement){
        List fragments = ((VariableDeclarationStatement) statement).fragments();
        VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) fragments.get(0);
        return variableDeclarationFragment.getName();
    }

    /**
     * 为了判断DFC块中的date-flow
     * @return
     */
    private boolean isInitialized(Object statement){
        if (isInit(statement) || isDeclare(statement)){
            return true;
        }
        return false;
    }
    private boolean isDefined(Object statement){
        if (isAssignment(statement) || isObjectMethodCall(statement))
            return true;
        return false;
    }
    
    /**
     * 以下几个判断函数是为了判断SynS中的category
     * @param statement
     * @return
     */
    private boolean isVariableDeclarationStatement(Object statement){
        return statement instanceof VariableDeclarationStatement;
    }
    private boolean isInit(Object statement){
        if(isVariableDeclarationStatement(statement)){
            List fragments = ((VariableDeclarationStatement) statement).fragments();
            Object obj = fragments.get(0);
            if(obj instanceof VariableDeclarationFragment){
                return ((VariableDeclarationFragment) obj).getInitializer()!=null;
            }
        }
        return false;
    }
    private boolean isDeclare(Object statement){
        if(isVariableDeclarationStatement(statement)){
            List fragments = ((VariableDeclarationStatement) statement).fragments();
            Object obj = fragments.get(0);
            if(obj instanceof VariableDeclarationFragment){
                return ((VariableDeclarationFragment) obj).getInitializer()==null;
            }
        }
        return false;
    }
    private boolean isExpressionStatement(Object statement){
        return statement instanceof ExpressionStatement;
    }
    private boolean isAssignment(Object statement){
        if (isExpressionStatement(statement)){
            Expression expression = ((ExpressionStatement)statement).getExpression();
            return expression instanceof  Assignment;
        }
        return false;
    }
    private boolean isMethodCall(Object statement){
        if (isExpressionStatement(statement)){
            Expression expression = ((ExpressionStatement)statement).getExpression();
            if (expression instanceof MethodInvocation){
                Expression expression1 = ((MethodInvocation) expression).getExpression();
                return expression1==null;
            }
        }
        return false;
    }
    private boolean isObjectMethodCall(Object statement){
        if (isExpressionStatement(statement)){
            Expression expression = ((ExpressionStatement)statement).getExpression();
            if (expression instanceof MethodInvocation){
                Expression expression1 = ((MethodInvocation) expression).getExpression();
                return expression1!=null;
            }
        }
        return false;
    }
    private boolean isOther(Object statement){
        if(!isInit(statement) && !isDeclare(statement) && !isAssignment(statement)&&
           !isMethodCall(statement) && !isObjectMethodCall(statement) && !(statement instanceof ReturnStatement)) {
               return true;
        }
        return false;
    }
    private boolean isSWIFT(Object statement){
        if(statement instanceof IfStatement || statement instanceof ForStatement ||
           statement instanceof TryStatement || statement instanceof EnhancedForStatement ||
           statement instanceof WhileStatement || statement instanceof SynchronizedStatement ||
           statement instanceof DoStatement || statement instanceof SwitchStatement)
               return true;
        return false ;
    }

}
