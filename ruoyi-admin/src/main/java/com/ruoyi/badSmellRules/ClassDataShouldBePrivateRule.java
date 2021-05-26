package com.ruoyi.badSmellRules;

import com.ruoyi.beans.ClassBean;
import com.ruoyi.metrics.CKMetrics;

public class ClassDataShouldBePrivateRule {

	public boolean isClassDataShouldBePrivate(ClassBean pClass, String pSystemType) {
		
		if(CKMetrics.getNOPA(pClass) > 10)
			return true;
		
		return false;
	}
}
