package com.ruoyi.bean;

import java.util.List;

public class MetricOutput {

	private List<String> blob;
	private List<String> cdsbp;
	private List<String> complexClass;
	private List<String> longMethod;
	private List<String> spaghettiCode;
	
	
	public List<String> getBlob() {
		return blob;
	}
	public void setBlob(List<String> blob) {
		this.blob = blob;
	}
	public List<String> getCdsbp() {
		return cdsbp;
	}
	public void setCdsbp(List<String> cdsbp) {
		this.cdsbp = cdsbp;
	}
	public List<String> getComplexClass() {
		return complexClass;
	}
	public void setComplexClass(List<String> complexClass) {
		this.complexClass = complexClass;
	}
	public List<String> getLongMethod() {
		return longMethod;
	}
	public void setLongMethod(List<String> longMethod) {
		this.longMethod = longMethod;
	}
	public List<String> getSpaghettiCode() {
		return spaghettiCode;
	}
	public void setSpaghettiCode(List<String> spaghettiCode) {
		this.spaghettiCode = spaghettiCode;
	}
}
