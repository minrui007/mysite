package com.mr.mysite.redis;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用Redis来做Mybatis的二级缓存
 * 实现Mybatis的cache接口
 */
public class MybatisRedisCache implements Cache {

    private static  final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    //读写锁
    private final  ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisTemplate<String, Object> redisTemplate = SpringContext.getBean("redisTemplate");

    private String id;

    public MybatisRedisCache(final String id) {
        if(null == id) {
            throw new IllegalArgumentException("Cache instancess require an ID");
        }
        logger.info("Redis Cache id = " + id);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    /**
     * 添加数据
     * @param key
     * @param value
     */
    public void putObject(Object key, Object value) {
        if(null != value) {
            //向Redis中添加数据，有效时间是2天
            redisTemplate.opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
        }
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public Object getObject(Object key) {
        try {
            if(null != key) {
                Object obj = redisTemplate.opsForValue().get(key.toString());
                return obj;
            }
        } catch (Exception e) {
            logger.error("redis get");
        }
        return null;
    }

    /**
     * 移除数据
     * @param key
     * @return
     */
    public Object removeObject(Object key) {
        try {
            if(null != key) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
            logger.error("redis remove");
        }
        return null;
    }

    /**
     * 清空缓存
     */
    public void clear() {
        logger.debug("清空缓存");
        try{
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if(!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        }catch (Exception e) {
            logger.error("redis clear");
        }
    }

    @Override
    public int getSize() {
        Long size = (Long)redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.dbSize();
            }
        });
        return size.intValue();
    }


    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
