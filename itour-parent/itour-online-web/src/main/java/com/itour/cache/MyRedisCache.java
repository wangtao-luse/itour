package com.itour.cache;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class MyRedisCache<V, K> implements Cache<K, V>{
	@Autowired
private RedisTemplate<String, Object> redisTemplate;
private String cacheName;
public MyRedisCache() {}
public MyRedisCache(String cacheName) {
	this.cacheName = cacheName;
}


	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		redisTemplate.delete(this.cacheName);
	}

	@Override
	public V get(K key) throws CacheException {
		// TODO Auto-generated method stub
		V v = (V)redisTemplate.opsForHash().get(this.cacheName, key.toString());
		return v;
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		Set keys = redisTemplate.opsForHash().keys(this.cacheName);
		return keys;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().put(this.cacheName, key, value);
		return null;
	}

	@Override
	public V remove(K key) throws CacheException {
		// TODO Auto-generated method stub
		Long delete = redisTemplate.opsForHash().delete(this.cacheName, key);
		return (V) delete;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		Long size = redisTemplate.opsForHash().size(this.cacheName);
		return Math.toIntExact(size);
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		List values = redisTemplate.opsForHash().values(this.cacheName);
		return values;
	}

}
