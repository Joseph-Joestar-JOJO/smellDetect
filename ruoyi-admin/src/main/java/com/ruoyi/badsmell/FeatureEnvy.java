package com.ruoyi.badsmell;

public class FeatureEnvy {
	
	private String methodName;
	private String className;
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public boolean equals(Object obj) {
		FeatureEnvy f = (FeatureEnvy) obj;
		return (f.getMethodName().equalsIgnoreCase(methodName) && f.getClassName().equalsIgnoreCase(className));
	}

}
