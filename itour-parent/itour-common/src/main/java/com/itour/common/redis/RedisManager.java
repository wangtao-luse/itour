package com.itour.common.redis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisManager {
	@Autowired
private  RedisTemplate<Object, Object> redisTemplate;
	@Autowired
private StringRedisTemplate  StringRedisTemplate;

	/**
	 * 1.Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。
	 * 2.redisTemplate.opsForValue();　　//操作字符串
		 redisTemplate.opsForHash();　　 //操作hash
		 redisTemplate.opsForList();　　 //操作list
		 redisTemplate.opsForSet();　　  //操作set
		 redisTemplate.opsForZSet();　 　//操作有序set
	 */
	/**
	 * 缓存放入 string（字符串)
	 * @param key 键
	 * @param value 值
	 * @return true: 成功;false：失败;
	 */
	public boolean set(String key,String value) {
		try {
			StringRedisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * 缓存放入 string（字符串)
	 * @param key 键
	 * @param value 值
	 * @param timeout 时间(秒) timeout要大于0 如果time小于等于0 将设置无限期
	 * @return true: 成功;false：失败;
	 */
	public boolean set(String key,String value,long timeout) {
		try {
			StringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * HashSet 缓存存放
	 * @param key 键
	 * @param m map
	 * @return true: 成功;false：失败;
	 */
	public boolean hmset(String key,Map<?, ?> m) {
		try {
			redisTemplate.opsForHash().putAll(key, m);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * HashSet 缓存存放
	 * @param key 键
	 * @param m Map
	 * @param timeout 失效时间 秒
	 * @return true: 成功;false：失败;
	 */
	public boolean hmset(String key,Map<?, ?> m,long timeout) {
		try {
			redisTemplate.opsForHash().putAll(key,m );
			if(timeout>0) {
				expire(key, timeout);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * 将list放入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lset(String key,Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * 将list放入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lset(String key,Object value,long timeout) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if(timeout>0) {
				expire(key, timeout);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * set 放入缓存
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean sSet(String key,Object... values) {
		try {
			redisTemplate.opsForSet().add(key, values);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * set 放入缓存
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean sSet(String key,long timeout,Object... values) {
		try {
			redisTemplate.opsForSet().add(key, values);
			if(timeout>0) {
				expire(key, timeout);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	
	/**
	 * 指定缓存失效时间
	 * @param key 键
	 * @param timeout 时间
	 * @return
	 */
	public boolean expire(String key,long timeout) {
		try {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
}
