package com.itour.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SimpleHashUtil {
/**
 * MD5盐值加密
 * @param credential
 * @param salt
 * @return
 */
public static String simpleHashMd5(String credential,String salt) {
	String result = new SimpleHash("MD5", credential, ByteSource.Util.bytes(salt), 1024).toHex();
	return result;
}
/**
 * SHA-1加密
 * @param credential 密码
 * @param salt 盐
 * @return
 */
public static String simpleHashSHA_1(String credential,String salt) {
	String result = new SimpleHash("SHA-1", credential, ByteSource.Util.bytes(salt), 1024).toHex();
	return result;
}
}
