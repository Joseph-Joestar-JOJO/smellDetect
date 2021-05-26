package com.ruoyi.badsmell;

public class CDSBP {

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public boolean equals(Object obj) {
		CDSBP b = (CDSBP) obj;
		return b.getClassName().equalsIgnoreCase(className);
	}
}
