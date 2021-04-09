package com.itour.common.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
//https://www.jianshu.com/p/8e71737a1101
@Component
public class RedisManager {
	/**
	* 注入自定义的RedisTemplate
	* 注意:泛型的类型一定要一致,否则会注入失败
	*/
	@Autowired
private RedisTemplate<String, Object> redisTemplate;

	@Autowired
private StringRedisTemplate  stringRedisTemplate;

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
			stringRedisTemplate.opsForValue().set(key, value);
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
	 * @param timeout 时间(秒) timeout要大于0 如果timeout小于等于0 将设置无限期
	 * @return true: 成功;false：失败;
	 */
	public boolean set(String key,String value,long timeout) {
		try {
			stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * 获取string（字符串）缓存
	 * @param key
	 * @return 字符串
	 */
	public String get(String key) {
		return StringUtils.isEmpty(key)?"":stringRedisTemplate.opsForValue().get(key);
				
	}
	/**
	 * 根据key 获取过期时间
	 * @param key key不能为null
	 * @return 时间（秒） 0:表示永久有效;-1:如果该值没有设置过期时间;-2:如果没有该值;
	 */
	public long getStrExpire(String key) {
		return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public boolean hasStrKey(String key) {
		return stringRedisTemplate.hasKey(key);
	}
	/**
	 * 删除缓存
	 * @param key 可以传入一个或多个
	 * @return true 删除成功，false删除失败
	 */
	public boolean del(String ...key) {
		if(key!=null&&key.length>0) {
			if(key.length==1) {
				stringRedisTemplate.delete(key[0]);
				return true;
			}else {
				stringRedisTemplate.delete(CollectionUtils.arrayToList(key));
				return true;
			}
		}
		return false;
	}
	 /**
     * 递增
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

	//----------------------------------Hashset--------------------------------------
	/**
	 * HashSet 缓存存放
	 * @param key 键
	 * @param map map
	 * @return true: 成功;false：失败;
	 */
	public  boolean hmset(String key,Map<Object, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * HashSet 缓存存放
	 * @param key 键
	 * @param m map
	 * @return true: 成功;false：失败;
	 */
	public  boolean hmSset(String key,HashMap<String, Object> m) {
		try {
			redisTemplate.opsForHash().putAll(key, m);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * HashSet 缓存存放且添加失效时间
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
	 * 获取Hash
	 * @param key 不能为null
	 * @param item 不能为null
	 * @return
	 */
	public Object hget(String key, String item) {
	        return redisTemplate.opsForHash().get(key, item);
	}
	/**
	 * 获取Hash 获取变量中的键值对
	 * @param key 不能为null
	 * @return
	 */
	public Map<Object,Object> hget(String key) {
		return this.redisTemplate.opsForHash().entries(key);
	}
	/**
	 * 删除hash表中的值
	 * @param key 不能为null
	 * @param hashKeys 可以有多个,不能为null
	 * @return
	 */
	public boolean hdel(String key,Object hashKeys) {
		boolean b=true;
		try {
			redisTemplate.opsForHash().delete(key, hashKeys);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		
		return b;
	}
	/**
	 * 判断hash表中是否有该项的值
	 * @param key 不能为null
	 * @param hashKey 不能为null
	 * @return true 存在 false不存在
	 */
	public boolean hHasKey(String key,Object hashKey) {
		boolean b=true;
		try {
			b= redisTemplate.opsForHash().hasKey(key, hashKey);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}
	//-------------------------------list-----------------------------------------------------
	/**
	 * 将list放入缓存( * 在列表的最左边塞入一个value)
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lpush(String key, String value) {
		try {
			 redisTemplate.opsForList().leftPush(key, value);
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	   
	}
	/**
	 * 将list放入缓存
	 * @param keyleftPush
	 * @param value
	 * @return
	 */
	public boolean lpush(String key,Object value,long timeout) {
		try {
			redisTemplate.opsForList().leftPush(key, value);
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
	public boolean rpush(String key,Object value) {
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
	public boolean rpush(String key,Object value,long timeout) {
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
	 * 获取指定索引位置的值, index为-1时，表示返回的是最后一个；当index大于实际的列表长度时，返回null
	 *
	 * @param key
	 * @param index
	 * @return
	 */
	public Object index(String key, int index) {
		try {
			return redisTemplate.opsForList().index(key, index);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	 /**
	     * 获取list缓存的长度
      * @param key 键
      * @return
      */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
  
    /**
     	* 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 删除list首尾，只保留 [start, end] 之间的值
     *
     * @param key
     * @param start
     * @param end
     */
    public boolean trim(String key, Integer start, Integer end) {
    	try {
    		redisTemplate.opsForList().trim(key, start, end);
    		return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
        
    }
    //---------------------------------------set---------------------------------------
	/**
	 * set 放入缓存
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean sAdd(String key,Object... values) {
		try {
			redisTemplate.opsForSet().add(key, values);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	 * set 放入缓存且设置过期时间
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean sAdd(String key,long timeout,Object... values) {
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
	 * 判断 member 元素是否是集合 key 的成员，在集合中返回True
	 * @param key
	 * @param o
	 * @return
	 */
	public boolean sisMember(String key,Object member) {
		try {
			return redisTemplate.opsForSet().isMember(key, member);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/**
	   * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 返回集合中的所有成员
     * @param key
     * @return
     */
    public Set<Object> smembers(String key) {
    	try {
    		Set<Object> members = redisTemplate.opsForSet().members(key);
    		return members;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    /**
              * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);            
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
