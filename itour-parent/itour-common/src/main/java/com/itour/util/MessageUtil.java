package com.itour.util;

import java.util.Random;

public class MessageUtil {
/**
 * 随机6位数字
 * @return
 */
public static String getCode() {
	StringBuffer sb = new StringBuffer();
	   for (int i = 0; i < 6; i++) {
		   Random r = new Random();
		   int nextInt = r.nextInt(10); 
		   sb.append(nextInt);
	   }
	   return sb.toString();
}

}
