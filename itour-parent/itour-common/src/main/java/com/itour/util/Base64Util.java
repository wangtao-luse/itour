package com.itour.util;

import java.util.Base64;

public class Base64Util {
public static String base64Str(String str) {
	String encodeToString = Base64.getEncoder().encodeToString(str.getBytes());
	return encodeToString;
}
}
