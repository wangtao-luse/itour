package com.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.itour.OnlienApp;
import com.itour.common.redis.RedisManager;
import com.itour.help.DateUtil;
import com.itour.model.travel.Tag;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlienApp.class)
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
		t.setTag("杭州1");
		t.setUid("10001");
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put(t.getTag(), t);
		//boolean hmset = this.redisManager.hmSset("nice", m);
		//System.out.println(hmset);
		Map<Object, Object> hget = this.redisManager.hget("nice");
		
		
	}
	@Test
	public void testSet() {
		Set<Object> set =new HashSet<Object>();
		  set.add("AA");
		  set.add("AA");
		  set.add("BB");
		  set.add("cc");
	 boolean sAdd = this.redisManager.sAdd("testSet", "c");
	 long size = this.redisManager.sGetSetSize("testSet");
	 System.out.println("set size----------------------->"+size);
	 boolean sisMember = this.redisManager.sisMember("testSet", "AA");
	 if(sisMember) {
		 System.out.println("testSet中有对应的成员");
	 }else {
		 System.out.println("testSet中 没有找到对应的成员");
	 }
	 Set<Object> smembers = this.redisManager.smembers("testSet");
	 System.out.println("smembers-------"+smembers);
	}
}
