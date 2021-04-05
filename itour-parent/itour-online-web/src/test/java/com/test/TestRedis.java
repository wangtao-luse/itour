package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.itour.OnlienApplication;
import com.itour.common.redis.RedisManager;
import com.itour.model.travel.Tag;
import com.itour.util.DateUtil;
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
	public void testsetStr() {
		boolean set = redisManager.set("name", "wangtao");
		String name = redisManager.get("name");
		System.out.println("name-------------------------->"+name);
		
		
	}
	@Test
	public void testsetJavaBean() {
		Tag t = new Tag();
		t.setCreatedate(DateUtil.currentLongDate());
		t.setId(1L);
		t.setTag("上海");
		t.setUid("10000");
		String jsonString = JSONObject.toJSONString(t);
		this.redisManager.set("shanghai", jsonString);
		String shanghai = this.redisManager.get("shanghai");
		System.out.println("shanghai-------------------------->"+shanghai);
		
		
	}
	@Test
	public void testgetStrExpire() {		
		long strExpire = this.redisManager.getStrExpire("shanghai");
		if(0==strExpire) {
			System.out.println("shanghai----------------》永久有效");
		}else if(-1==strExpire){
			System.out.println("shanghai该值没有设置过期时间");
		}else if(-2==strExpire) {
			System.out.println("shanghai---->没有该值");
		}
		
		
	}
	@Test
	public void testHash() {
		Tag t = new Tag();
		t.setCreatedate(DateUtil.currentLongDate());
		t.setId(1L);
		t.setTag("杭州");
		t.setUid("10001");
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put(t.getTag(), t);
		boolean hmset = this.redisManager.hmSset("nice", m);
		System.out.println(hmset);
		Map<Object, Object> hget = this.redisManager.hget("nice");
		
		
	}
}
