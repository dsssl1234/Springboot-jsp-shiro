package com.example.shiro.cache;

import com.example.util.ApplicationContextUtils;
import com.example.util.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

//自定义redis Cache实现
public class RedisCache<K,V> implements Cache<K,V> {

    @Autowired
    RedisUtil redisUtil;

    private String cacheName;

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }
    public RedisCache() {
    }
    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key:"+k);
//        return (V) redisUtil.get(k.toString());
        return (V) getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key:"+k);
        System.out.println("put value:"+v);
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);
//        redisUtil.set(k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
