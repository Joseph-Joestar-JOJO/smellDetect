package com.ruoyi.badsmell;

public class ComplexClass {

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public boolean equals(Object obj) {
		ComplexClass b = (ComplexClass) obj;
		return b.getClassName().equalsIgnoreCase(className);
	}
}
