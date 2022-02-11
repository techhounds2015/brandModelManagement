package com.kashitkalaecom.brandmodelmgmt.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.kashitkalaecom.brandmodelmgmt.utilities.JsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisSingleCacheManager extends CacheManager
{
    private static final Logger logger = LoggerFactory.getLogger(RedisSingleCacheManager.class);

    private static final int defaultExpirySec = 120 * 60;

    private static RedisSingleCacheManager cacheManager = new RedisSingleCacheManager();
    private JedisPool jedisPool;
    

    @Value("${cachingPort}")
    private static int port;
    
    @Value("${cachingHost}")
    private static String cachingHost;
    
    
    @Value("${cachingPass}")
    private static String pass;
    
    @Value("${cacheConnectTimeout}")
    private static int cacheConnectTimeout;
    
    

    private RedisSingleCacheManager()
    {


	logger.info("Caching host and caching port {} {}", cachingHost, port);

	JedisPoolConfig poolConfig = buildPoolConfig();

		
	if (StringUtils.isBlank(pass))
	{
	    jedisPool = new JedisPool(poolConfig, cachingHost, port);
	} else
	{
	    jedisPool = new JedisPool(poolConfig, cachingHost, port, cacheConnectTimeout, pass);
	}

    }

    private JedisPoolConfig buildPoolConfig()
    {
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

    public static RedisSingleCacheManager getCacheManager()
    {
	return cacheManager;
    }


    public void cacheObject(String id, Object object) throws Exception
    {
	if (object == null)
	{
	    return;
	}
	String objectJson = JsonUtil.toJson(object);

	if (StringUtils.isBlank(objectJson))
	{
	    return;
	}

	Jedis jedis = null;
	try
	{
	    jedis = jedisPool.getResource();
	    // logger.info("Setting cached object for {} : {}", id,
	    // object.getClass().getName() );

	    String clasName = object.getClass().getSimpleName();

	    id = id + "_" + clasName;

	    jedis.set(id, objectJson);
	    jedis.expire(id, defaultExpirySec);

	} finally
	{

	    jedis.close();

	}

    }

    public <T> void cacheObjectWithExpiry(String id, T object, int secondsToLive) throws Exception
    {

	if (object == null)
	{
	    return;
	}
	String objectJson = JsonUtil.toJson(object);

	if (StringUtils.isBlank(objectJson))
	{
	    return;
	}

	Jedis jedis = null;
	try
	{
	    jedis = jedisPool.getResource();

	    Class clazz = object.getClass();
	    String clasName = clazz.getSimpleName();

	    id = id + "_" + clasName;

	    jedis.set(id, objectJson);
	    jedis.expire(id, secondsToLive);

	} finally
	{
	    jedis.close();
	}

    }

    public <T> T getObjectUsingCacheKey(String id, Class<T> className) throws Exception
    {
	String objectJson = null;
	if (StringUtils.isBlank(id))
	{
	    return null;
	}

	Jedis jedis = null;
	try
	{
	    jedis = jedisPool.getResource();
	    String clasName = className.getSimpleName();

	    id = id + "_" + clasName;

	    objectJson = jedis.get(id);
	} finally
	{
	    jedis.close();
	}

	// logger.info("*****Json***** " + objectJson);

	return (T) JsonUtil.toObject(objectJson, className);

    }

    public <T> T getCachedObject(String id, Class<T> className) throws Exception
    {
	String objectJson = null;
	if (StringUtils.isBlank(id))
	{
	    return null;
	}

	Jedis jedis = null;
	try
	{
	    jedis = jedisPool.getResource();

	    String clasName = className.getSimpleName();

	    id = id + "_" + clasName;

	    objectJson = jedis.get(id);
	} finally
	{
	    jedis.close();
	}

	logger.debug("*****Json***** " + objectJson);

	return (T) JsonUtil.toObject(objectJson, className);

    }

    public Long getIncr(String key)
    {
	Long incr = 0L;

	Jedis jedis = null;
	try
	{
	    jedis = jedisPool.getResource();
	    incr = jedis.incr(key);
	} finally
	{
	    jedis.close();
	}

	return incr;
    }

    @Override
    public void removeAll()
    {
	Jedis jedis = null;
	try
	{
	    jedis = jedisPool.getResource();
	    jedis.flushAll();
	    jedis.flushDB();
	} finally
	{
	    jedis.close();
	}

    }

    public static void main(String[] args)
    {
	RedisSingleCacheManager manager = new RedisSingleCacheManager();
	Jedis jedis = manager.jedisPool.getResource();

	for (int i = 0; i < 10; i++)
	{
	    String id = "id ";
	    String field = "field " + i;
	    String object = "Objet " + i;

	    jedis.hset(id, field, object);
	}

	jedis.close();

    }

	@Override
	public void cacheObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
