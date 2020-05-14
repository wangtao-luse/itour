package com.itour.util;

import java.util.Random;

public class MessageUtil {
/**
 * 随机6位数字
 * @return
 */
public static String getCode() {
	Random r = new Random();
    int nextInt = r.nextInt(999999)+100000;    
    String sendCode = String.valueOf(nextInt);
    return sendCode;
}
}
