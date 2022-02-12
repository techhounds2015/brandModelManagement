package com.kashitkalaecom.brandmodelmgmt.cache;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.utilities.JsonUtil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisClusterCacheManager extends CacheManager
{
    private static final Logger logger = LoggerFactory.getLogger(RedisClusterCacheManager.class);

    private static RedisClusterCacheManager cacheManager = new RedisClusterCacheManager();
    private JedisCluster jedisCluster;
    private JedisPool jedisPool;


    @Value("${cachingPort}")
    private static int port;
    
    @Value("${cachingHost}")
    private static String cachingHost;
    
    private RedisClusterCacheManager()
    {

 
	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	// add all hosts and ports
	jedisClusterNodes.add(new HostAndPort(cachingHost, port));

	jedisCluster = new JedisCluster(jedisClusterNodes);

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

    public static RedisClusterCacheManager getCacheManager()
    {
	return cacheManager;
    }


    public void cacheObject(String id, Object object) throws Exception
    {

	String objectJson = JsonUtil.toJson(object);

	if (StringUtils.isBlank(objectJson))
	{
	    return;
	}

	String simpleName = object.getClass().getSimpleName();

	id = id + "-" + simpleName;

	jedisCluster.set(id, objectJson);
	// jedisCluster.close();

    }

    public void removeObject(String id, Class<? extends Object> clazz) throws Exception
    {
	if (StringUtils.isBlank(id))
	{
	    return;
	}

	String simpleName = clazz.getSimpleName();

	id = id + "-" + simpleName;

	jedisCluster.del(id);

    }

    public <T> void cacheObjectWithExpiry(String id, T object, int secondsToLive) throws Exception
    {

	String objectJson = JsonUtil.toJson(object);

	if (StringUtils.isBlank(objectJson))
	{
	    return;
	}

	String simpleName = object.getClass().getSimpleName();

	id = id + "-" + simpleName;

	jedisCluster.set(id, objectJson);
	jedisCluster.expire(id, secondsToLive);
	// jedisCluster.close();
	// jedisCluster.hset

    }

   

    public <T> T getObjectUsingCacheKey(String id, Class<T> className) throws Exception
    {
	String objectJson = null;
	if (StringUtils.isBlank(id))
	{
	    return null;
	}

	String simpleName = className.getSimpleName();

	id = id + "-" + simpleName;

	objectJson = jedisCluster.get(id);
	// jedisCluster.close();

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

	String simpleName = className.getSimpleName();

	id = id + "-" + simpleName;

	objectJson = jedisCluster.get(id);
	// jedisCluster.close();

	logger.info("*****Json***** " + objectJson);

	return (T) JsonUtil.toObject(objectJson, className);

    }

    public Long getIncr(String key)
    {
	Long incr = 0L;

	incr = jedisCluster.incr(key);
	jedisCluster.close();

	return incr;
    }

    public static void main(String[] args)
    {

    }

    @Override
    public void removeAll()
    {
	// TODO Auto-generated method stub

    }

	@Override
	public void cacheObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
