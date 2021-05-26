package com.ruoyi.badsmell;

public class MisplacedClass {

	private String className;
	private String packageName;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	@Override
	public boolean equals(Object obj) {
		MisplacedClass m = (MisplacedClass) obj;
		return (m.getClassName().equalsIgnoreCase(className) && m.getPackageName().equalsIgnoreCase(packageName));
	}
}
