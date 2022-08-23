package com.itour.help;

import java.util.UUID;

public class StringHelper {
/***
 * 返回原始UUID
 * @return
 */
public static String getUUID() {
	String randomUUID = UUID.randomUUID().toString();
	return randomUUID;
}
/***
 * 返回原始处理过的UUID 
 * @return
 */
public static String getUuid() {
	String randomUUID = UUID.randomUUID().toString().replace("-", "");
	return randomUUID;
}
public static boolean isEmpty(Object obj) {
	return obj == null || obj == "" || "null".equals(obj);
}

}
