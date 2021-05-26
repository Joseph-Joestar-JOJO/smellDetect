package com.ruoyi.bean;

import java.io.Serializable;
import java.util.List;

public class Storage implements Serializable{
	
	private static final long serialVersionUID = -5894682485213742171L;
	
	public static final String BLOB = "blob";
	public static final String CDSBP = "cdsbp";
	public static final String COMPLEXCLASS = "complex_class";
	public static final String LONGMETHOD = "long_method";
	public static final String SPAGHETTICODE = "spaghetti_code";
	
	
	private String type;
	private int numCommit;
	private List<String> content;
	
	public String getContentLine(int line){
		return content.get(line);
	}
	
	public void setContentLine(int line, String contentLine){
		content.set(line, contentLine);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumCommit() {
		return numCommit;
	}
	public void setNumCommit(int numCommit) {
		this.numCommit = numCommit;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
}
