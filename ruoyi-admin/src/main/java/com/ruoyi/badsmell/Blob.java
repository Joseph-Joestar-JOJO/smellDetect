package com.ruoyi.badsmell;

public class Blob {

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public boolean equals(Object obj) {
		Blob b = (Blob) obj;
		return b.getClassName().equalsIgnoreCase(className);
	}
	
}
