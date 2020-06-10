package com.itour.common.jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.spring.SpringContextUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class JedisManager {

	private static Logger logger=LoggerFactory.getLogger(JedisManager.class);
	private static JedisPool jedisPool = SpringContextUtil.getBean(JedisPool.class);

	public static void returnResource(Jedis jedis) {
		if (jedis != null){
			jedis.close();
		}
	}
    
	public static byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
		Jedis jedis = null;
		byte[] result = null;
		try {
			jedis = getResource();
			jedis.select(dbIndex);
			result = jedis.get(key);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public static void deleteByKey(int dbIndex, byte[] key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(dbIndex);
			jedis.del(key);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}
	
	
	public static void deleteByKey(int dbIndex, String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(dbIndex);
			jedis.del(key);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(dbIndex);
			jedis.set(key, value);
			if (expireTime > 0)
				jedis.expire(key, expireTime);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}
	
	public static void set(String key,String value) throws Exception {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
//			jedis.expire(key, 12*60*60);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}
	
	public static void set(String key,String value,int indexDb) throws Exception {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			jedis.set(key, value);
//			jedis.expire(key, 12*60*60);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	
	public static void set(String key,String value,int indexDb,int sxtime) throws Exception {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			jedis.setex(key, sxtime, value);
		} catch (Exception e) {
			throw e;
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 获取资源
	 * @return
	 * @throws JedisException
	 */
	public static Jedis getResource() throws JedisException {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (JedisException e) {
			logger.error("getResource.", e);
			returnResource(jedis);
			throw e;
		}
		return jedis;
	}
	
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	public static String get(String key,int indexDb) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			if (jedis.exists(key)) {
				value = jedis.get(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	
	//设置Map
	public static void setMap(Map<String, String> map,String key){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.hmset(key, map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 
	 * @param map
	 * @param key
	 * @param indexDb 指定数据库
	 */
	public static void setMap(Map<String, String> map,String key,int indexDb){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			jedis.hmset(key, map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}
	 
	
	public static Map<String, String> getMap(String key){
		Jedis jedis = null;
		Map<String, String> map=new HashMap<String, String>();
		try {
			jedis = getResource();
			map=jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return map;
	}
	
	/**
	 * 
	 * @param key
	 * @param indexDb
	 * @return
	 */
	public static Map<String, String> getMap(String key,int indexDb){
		Jedis jedis = null;
		Map<String, String> map=new HashMap<String, String>();
		try {
			jedis = getResource();
			jedis.select(indexDb);
			map=jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return map;
	}
	
	
	public static JSONObject getJsonFromRedis(String key,int indexDb,String rowKey){
		Map<String,String> map = getMap(key, indexDb);
		String str= map.get(rowKey);
		return JSON.parseObject(str);
	}
	
	/**
	 * <p>@describe: 删除map指定的记录  </P>
	 * <p>@param mapKey
	 * <p>@param indexDb
	 * <p>@param fieldKey  </P>
	 * <p>@date: 2018年4月12日 上午10:53:13 </P>
	 * <p>@author:  </P>
	 * <p>@remark:    </P>
	 */
	public static void hdel(String mapKey,int indexDb,String fieldKey){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			jedis.hdel(mapKey, fieldKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * <p>@describe: 整数递增  </P>
	 * <p>@param key
	 * <p>@param indexDb
	 * <p>@return  </P>
	 * <p>@date: 2018年4月12日 上午8:22:26 </P>
	 * <p>@author: Zhb </P>
	 * <p>@remark:    </P>
	 */
	public static Long incr(String key,int indexDb){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			return jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return 0L;
	}
	
	/**
	 * <p>@describe: 整数递减  </P>
	 * <p>@param key
	 * <p>@param indexDb
	 * <p>@return  </P>
	 * <p>@date: 2018年4月12日 上午8:22:26 </P>
	 * <p>@author: Zhb </P>
	 * <p>@remark:    </P>
	 */
	public static Long decr(String key,int indexDb){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			return jedis.decr(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return 0L;
	}
	
	/**
	 * <p>@describe: 设置过期时间  </P>
	 * <p>@param key
	 * <p>@param indexDb
	 * <p>@param seconds  </P>
	 * <p>@date: 2018年4月12日 上午9:02:09 </P>
	 * <p>@author: Zhb </P>
	 * <p>@remark:    </P>
	 */
	public static void expire(String key,int indexDb,int seconds){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}
	
	
	/**
	 * <p>@describe: 判断Key是否存在 存在则返回true 否则返回false </P>
	 * <p>@param key
	 * <p>@param indexDb
	 * <p>@return  true/false</P>
	 * <p>@date: 2018年4月12日 上午9:03:57 </P>
	 * <p>@author: Zhb </P>
	 * <p>@remark:    </P>
	 */
	public static boolean exists(String key,int indexDb){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(indexDb);
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return  false;
	}
	
	/**
	 * <p>@describe: 清空缓存库中所有数据  </P>
	 * <p>@param indexDb  </P>
	 * <p>@date: 2018年4月13日 上午11:01:05 </P>
	 * <p>@author: Zhb </P>
	 * <p>@remark: 注意：慎用 ！  </P>
	 */
	public static void flushDB(int indexDb){
		Jedis jedis = JedisManager.getResource();
		jedis.select(indexDb);
		try {
			jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * <p>@describe: 获取缓存仓库中所有数据  </P>
	 * <p>@param indexDb
	 * <p>@return Map<String, String> </P>
	 * <p>@date: 2018年4月13日 上午11:12:09 </P>
	 * <p>@author: Zhb </P>
	 * <p>@remark: 注意：该缓存库的所有值必须为字符串，不能有map类型的数据   </P>
	 */
	public static Map<String, String> getWarehouseAllDataMap(int indexDb) {
		Jedis jedis = JedisManager.getResource();
		jedis.select(indexDb);
		Map<String, String> map = new HashMap<String, String>();
		try {
			Set<String> s = jedis.keys("*");
			Iterator<String> it = s.iterator();
			while (it.hasNext()) {
				String key =  it.next();
				map.put(key, jedis.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return map;
	}
	
	
	
}
