package com.ruoyi.metrics;

public class OperationStringUtils {
    public static String clearStr(String str){
        str = str.replace("<", " ");
        str = str.replace(">", " ");
        str = str.replace("</", " ");
        str = str.replace("/>", " ");
        str = str.replace("+", " ");
        str = str.replace("-", " ");
        str = str.replace("~", " ");
        str = str.replace("/", " ");
        str = str.replace("*", " ");
        str = str.replace("=", " ");
        str = str.replace("@", " ");
        str = str.replace("\n", " ");
        str = str.replace("\r", " ");
        str = str.replace("\t", " ");
        str = str.replace("\"", " ");
        str = str.replace("_", " ");
        str = str.replace("?", " ");
        str = str.replace("{", " ");
        str = str.replace("}", " ");
        str = str.replace("(", " ");
        str = str.replace(")", " ");
        str = str.replace(";", " ");
        str = str.replace("'", " ");
        str = str.replace(".", " ");
        str = str.replace(",", " ");
        str = str.replace("[", " ");
        str = str.replace("]", " ");
        str = str.replace("&", " ");
        str = str.replace("|", " ");
        str = str.replace("\n", " ");
        str = str.replace(":", " ");
        str = str.replace("#", " ");
        str = str.replace("^", " ");
        str = str.replace("%", " ");
        str = str.replace("`", " ");
        str = str.replace("!", " ");
        str = str.replace("$", " ");
        str = str.replace("\\", " ");
        str = str.replaceAll("\\d+"," ");

//        str=str.replaceAll("[\\u4e00-\\u9fa5]", " ").toLowerCase();
        return str;
    }
}
