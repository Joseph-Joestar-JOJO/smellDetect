package com.ruoyi.badSmellRules;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.metrics.CKMetrics;

public class ComplexClassRule {

	public boolean isComplexClass(ClassBean pClass, String pSystemType) {

		if(pSystemType.equals("android")) {
			if(CKMetrics.getWMC(pClass) > 100)
				return true;
		} else {
			if(CKMetrics.getWMC(pClass) > 200)
				return true;
		}

		return false;
	}
}