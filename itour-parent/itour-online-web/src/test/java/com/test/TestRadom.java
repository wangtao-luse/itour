package com.test;

import java.util.Random;

import org.junit.Test;

public class TestRadom {
public static void main(String[] args) {
	
}
@Test
public void testRandomInt() {
	//1.nextInt() 返回下一个伪随机数
	//2.nextInt(int bound) 返回[0,bound);
	Random random = new Random();
	for (int i = 0; i < 10; i++) {
		int nextInt = random.nextInt(8);	
		System.out.println(nextInt);
	}
}
}
