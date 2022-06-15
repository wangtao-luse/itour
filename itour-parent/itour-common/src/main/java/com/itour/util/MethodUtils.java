package com.itour.util;

import org.apache.commons.lang.StringUtils;

public class MethodUtils {
private static final String SET_PREFIX ="set";	
private static final String GET_PREFIX ="get";	
public static String capitalize(String name) {
	if(StringUtils.isEmpty(name)) {
		return name;
	}
	return name.substring(0,1).toUpperCase()+name.substring(1);
}
public static String setMethodName(String name) {
	return SET_PREFIX + capitalize(name);
}
public static String getMethodName(String name) {
	return GET_PREFIX + capitalize(name);
}
}
