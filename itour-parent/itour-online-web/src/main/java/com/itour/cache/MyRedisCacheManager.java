package com.itour.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class MyRedisCacheManager implements CacheManager{

	@Override
	public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
		// TODO Auto-generated method stub
		return new MyRedisCache<V, K>(arg0);
	}

}
