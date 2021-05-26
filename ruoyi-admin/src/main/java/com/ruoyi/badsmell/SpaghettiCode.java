package com.ruoyi.badsmell;

public class SpaghettiCode {

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public boolean equals(Object obj) {
		SpaghettiCode b = (SpaghettiCode) obj;
		return b.getClassName().equalsIgnoreCase(className);
	}
}
