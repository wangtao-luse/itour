package com.itour.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SimpleHashUtil {
public static String SimpleHashMd5(String credential,String salt) {
	String result = new SimpleHash("MD5", credential, ByteSource.Util.bytes(salt), 1024).toString();
	return result;
}
}
