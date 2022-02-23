package com.kashitkalaecom.brandmodelmgmt.cache;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.kashitkalaecom.brandmodelmgmt.utilities.PropertyConfig;


public abstract class CacheManager
{
    private static String infispan = "Infinispan";
    private static String redisSingle = "RedisSingle";
    private static String redisCluster = "RedisCluster";
    
   

   
    private static String cacheProvider;

    private static CacheManager cacheManager;

    static
    {
	
	 Properties properties=new Properties();
	    try {
		
			cacheProvider=PropertyConfig.loadProperties().getProperty("CacheProvider");//properties.getProperty("CacheProvider");
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    cacheManager = buildCacheManager(cacheProvider);
    }

    public static CacheManager getCacheManager()
    {

	return cacheManager;
    }

    public abstract void cacheObject(Object object) throws Exception;

    public abstract void cacheObject(String id, Object object) throws Exception;

    public abstract <T> void cacheObjectWithExpiry(String id, T object, int secondsToLive) throws Exception;

    public abstract <T> T getObjectUsingCacheKey(String id, Class<T> className) throws Exception;

    public abstract <T> T getCachedObject(String id, Class<T> className) throws Exception;

    public abstract Long getIncr(String key) throws Exception;


    private static CacheManager buildCacheManager(String cacheProvider)
    {
	if (StringUtils.equals(cacheProvider, redisSingle))
	{
	    cacheManager = RedisSingleCacheManager.getCacheManager();

	} else if (StringUtils.equals(cacheProvider, redisCluster))
	{
	    cacheManager = RedisClusterCacheManager.getCacheManager();

	}
	return cacheManager;

    }

    public abstract void removeAll();

}
