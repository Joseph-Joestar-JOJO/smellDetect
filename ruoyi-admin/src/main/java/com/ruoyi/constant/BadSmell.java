package com.ruoyi.constant;

import java.util.ArrayList;
import java.util.List;

public class BadSmell {

	public static final String BLOB = "blob";
	public static final String CDSBP = "cdsbp";
	public static final String COMPLEXCLASS = "complex_class";
	public static final String LONGMETHOD = "long_method";
	public static final String SPAGHETTICODE = "spaghetti_code";
	public static final String FD = "fd";
	
	
	public static final String BLOB_SUFF = "_blob_trend_true.csv";
	public static final String CDSBP_SUFF = "_cdsbp_trend_true.csv";
	public static final String COMPLEXCLASS_SUFF = "_complexClass_trend_true.csv";
	public static final String SPAGHETTICODE_SUFF = "_spaghettiCode_trend_true.csv";
	public static final String FD_SUFF = "_fd_trend_true.csv";
	
	public static List<String> getAllBadSmell(){
		List<String> badSmells = new ArrayList<String>();
		badSmells.add(BLOB);
		badSmells.add(CDSBP);
		badSmells.add(COMPLEXCLASS);
		badSmells.add(LONGMETHOD);
		badSmells.add(SPAGHETTICODE);
		
		return badSmells;
	}
	
	public static List<String> getAllClassBadSmell(){
		List<String> badSmells = new ArrayList<String>();
		badSmells.add(BLOB);
		badSmells.add(CDSBP);
		badSmells.add(COMPLEXCLASS);
		badSmells.add(SPAGHETTICODE);
		
		return badSmells;
	}
}
