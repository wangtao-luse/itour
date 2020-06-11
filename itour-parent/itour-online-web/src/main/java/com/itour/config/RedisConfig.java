package com.itour.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
//@Configuration
public class RedisConfig {
/**
 * 连接池中的最大空闲连接 
 * */
@Value("${spring.redis.jedis.pool.max-idle}")
private int maxIdle;
/**
 *连接池中的最小空闲连接 
 */
@Value("{spring.redis.jedis.pool.min-idle}")
private int minIdle;
/**
 * 连接池最大阻塞等待时间（使用负值表示没有限制）
 */
@Value("${spring.redis.jedis.pool.max-wait}")
private long maxWaitMillis;
/**
 * 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
 */

private boolean blockWhenExhausted = true;
/**
 *  是否启用pool的jmx管理功能, 默认true
 */
private boolean jmxEnabled = true;
/**
 * 端口
 * */
@Value("${spring.redis.host}")
private String host;
/**
 *  Redis服务器连接端口 
 *  */
@Value("${spring.redis.port}")
private int port;
/**
 *  连接超时时间（毫秒）
 *   */
@Value("${spring.redis.timeout}")
private int timeout;
/**
 * Redis服务器连接密码（默认为空）
 * */
@Value("${spring.redis.password}")
private String password;

public JedisPool redisPoolFactory() {
	 JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	 jedisPoolConfig.setMaxIdle(maxIdle);
	 jedisPoolConfig.setMinIdle(minIdle);
	 jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
	 jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
	 jedisPoolConfig.setJmxEnabled(jmxEnabled);
	 JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);	 
	return jedisPool;
}

}
