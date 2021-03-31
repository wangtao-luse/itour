package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itour.OnlienApplication;
import com.itour.common.redis.RedisManager;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlienApplication.class)
public class TestRedis {
/***
 * 1.做Java API的测试只需要在方法上加入@Test注解即可;
 */
	@Autowired
	RedisManager redisManager;
	@Test
	public  void  testHello() {
		System.out.println("test......");
	}
	@Test
	public void testStr() {
		boolean set = redisManager.set("name", "wangtao");
		String name = redisManager.get("name");
		System.out.println("name-------------------------->"+name);
		
	}
}
