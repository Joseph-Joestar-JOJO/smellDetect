package com.ruoyi.metrics;

public class ID_Similarity {
    public static boolean IDsimilarity(String strName1,String strName2){
        boolean flag=false;
        String[] strings1 = splitCamel(strName1);
        String[] strings2 = splitCamel(strName2);
        if (strings1.length==1 && strings1.length==strings2.length){
            flag=true;
        }else if (strings1.length==strings2.length){
            for (int i = 0; i < strings1.length; i++) {
                if (strings1[i].equals(strings2[i])){
                    flag=true;
                }
            }
        }else if (strings1.length-strings2.length==1 || strings2.length-strings1.length==1){
            if(strings1[0].equals(strings2[0]) ||strings1[strings1.length-1].equals(strings2[strings2.length-1])){
                flag=true;
            }
        }
        return flag;
    }
    private static String[] splitCamel(String str){
        return str.replace("_"," ").replaceAll("\\d+"," ").split("(?<!^)(?=[A-Z])");
    }
}
