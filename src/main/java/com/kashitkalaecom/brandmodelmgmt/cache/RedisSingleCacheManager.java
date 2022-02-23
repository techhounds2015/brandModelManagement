package com.kashitkalaecom.brandmodelmgmt.cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.utilities.JsonUtil;
import com.kashitkalaecom.brandmodelmgmt.utilities.PropertyConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisSingleCacheManager extends CacheManager {
	private static final Logger logger = LoggerFactory.getLogger(RedisSingleCacheManager.class);

	private static final int defaultExpirySec = 120 * 60;

	private static RedisSingleCacheManager cacheManager;
	private JedisPool jedisPool;

	@Value("${cachingPort}")
	private static int port;

	@Value("${cachingHost}")
	private static String cachingHost;

	@Value("${cachingPass}")
	private static String pass;

	@Value("${cacheConnectTimeout}")
	private static int cacheConnectTimeout;

	public Properties getProperties() {

		Properties properties = new Properties();
		try {
			properties.load(
					new FileInputStream("E:\\test\\brandmodelmgmt\\src\\main\\resources\\application.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private RedisSingleCacheManager() {
		// Properties properties=getProperties(); 
		String cachingHost = null;
		int port = 0;
		String pass = null;
		int cacheConnectTimeout = 0;
		try {
			cachingHost = PropertyConfig.loadProperties().getProperty("cachingHost");
			port = Integer.parseInt(PropertyConfig.loadProperties().getProperty("cachingPort"));// properties.getProperty("cachingPort")
			pass = PropertyConfig.loadProperties().getProperty("cachingPass");// properties.getProperty("cachingPass");
			cacheConnectTimeout = Integer.parseInt(PropertyConfig.loadProperties().getProperty("cachingTimeout"));// properties.getProperty("cachingTimeout")

		} catch (IOException e) {
		}
	
		JedisPoolConfig poolConfig = buildPoolConfig();

		if (StringUtils.isBlank(pass)) {
			jedisPool = new JedisPool(poolConfig, cachingHost, port);
		} else {
			jedisPool = new JedisPool(poolConfig, cachingHost, port, cacheConnectTimeout, pass);
		}

	}

	private JedisPoolConfig buildPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128);
		poolConfig.setMaxIdle(128);
		poolConfig.setMinIdle(16);
		poolConfig.setTestOnBorrow(false);
		poolConfig.setTestOnReturn(false);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setBlockWhenExhausted(true);
		return poolConfig;
	}

	public static RedisSingleCacheManager getCacheManager() {
		if (null == cacheManager) {
			cacheManager = new RedisSingleCacheManager();
		}
		return cacheManager;
	}

	public void cacheObject(String id, Object object) throws Exception {
		if (object == null) {
			return;
		}
		String objectJson = JsonUtil.toJson(object);

		if (StringUtils.isBlank(objectJson)) {
			return;
		}

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// logger.info("Setting cached object for {} : {}", id,
			// object.getClass().getName() );

			String clasName = object.getClass().getSimpleName();

			id = id + "_" + clasName;

			jedis.set(id, objectJson);
			jedis.expire(id, defaultExpirySec);

		} finally {

			jedis.close();

		}

	}

	public <T> void cacheObjectWithExpiry(String id, T object, int secondsToLive) throws Exception {

		if (object == null) {
			return;
		}
		String objectJson = JsonUtil.toJson(object);

		if (StringUtils.isBlank(objectJson)) {
			return;
		}

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			Class clazz = object.getClass();
			String clasName = clazz.getSimpleName();

			id = id + "_" + clasName;

			jedis.set(id, objectJson);
			jedis.expire(id, secondsToLive);

		} finally {
			jedis.close();
		}

	}

	public <T> T getObjectUsingCacheKey(String id, Class<T> className) throws Exception {
		String objectJson = null;
		if (StringUtils.isBlank(id)) {
			return null;
		}

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String clasName = className.getSimpleName();

			id = id + "_" + clasName;

			objectJson = jedis.get(id);
		} finally {
			jedis.close();
		}

		// logger.info("*****Json***** " + objectJson);

		return (T) JsonUtil.toObject(objectJson, className);

	}

	public <T> T getCachedObject(String id, Class<T> className) throws Exception {
		String objectJson = null;
		if (StringUtils.isBlank(id)) {
			return null;
		}

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

			String clasName = className.getSimpleName();

			id = id + "_" + clasName;

			objectJson = jedis.get(id);
		} finally {
			jedis.close();
		}

		logger.debug("*****Json***** " + objectJson);

		return (T) JsonUtil.toObject(objectJson, className);

	}

	public Long getIncr(String key) {
		Long incr = 0L;

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			incr = jedis.incr(key);
		} finally {
			jedis.close();
		}

		return incr;
	}

	@Override
	public void removeAll() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.flushAll();
			jedis.flushDB();
		} finally {
			jedis.close();
		}

	}
	/*
	 * public static void main(String[] args) { RedisSingleCacheManager manager =
	 * new RedisSingleCacheManager(); Jedis jedis = manager.jedisPool.getResource();
	 * 
	 * for (int i = 0; i < 10; i++) { String id = "id "; String field = "field " +
	 * i; String object = "Objet " + i;
	 * 
	 * jedis.hset(id, field, object); }
	 * 
	 * jedis.close();
	 * 
	 * }
	 */

	@Override
	public void cacheObject(Object object) throws Exception {
		// TODO Auto-generated method stub

	}

}
