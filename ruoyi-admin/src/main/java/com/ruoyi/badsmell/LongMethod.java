package com.ruoyi.badsmell;

public class LongMethod {
	
	private String methodName;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	@Override
	public boolean equals(Object obj) {
		LongMethod m = (LongMethod) obj;
		return m.getMethodName().equalsIgnoreCase(methodName);
	}
	

}
