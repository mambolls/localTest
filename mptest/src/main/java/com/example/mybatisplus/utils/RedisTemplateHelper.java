package com.example.mybatisplus.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;


@Component
public class RedisTemplateHelper {
    /**
     * 初始化
     */
    private static RedisTemplateHelper redisTemplateHelper;
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplateHelper = this;
        redisTemplateHelper.redisTemplate = this.redisTemplate;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key, Object value) {
        boolean result = false;
        try {
            @SuppressWarnings("unchecked")
            ValueOperations<Serializable, Object> operations = redisTemplateHelper.redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplateHelper.redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplateHelper.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public static void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public static void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplateHelper.redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplateHelper.redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static void remove(String key) {
        if (exists(key)) {
            redisTemplateHelper.redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean exists(String key) {
        return redisTemplateHelper.redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Object result = null;
        @SuppressWarnings("unchecked")
        ValueOperations<Serializable, Object> operations = redisTemplateHelper.redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public static void hmSet(String key, Object hashKey, Object value) {
        @SuppressWarnings("unchecked")
        HashOperations<String, Object, Object> hash = redisTemplateHelper.redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public static Object hmGet(String key, Object hashKey) {
        @SuppressWarnings("unchecked")
        HashOperations<String, Object, Object> hash = redisTemplateHelper.redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public static void lPush(String k, Object v) {
        @SuppressWarnings("unchecked")
        ListOperations<String, Object> list = redisTemplateHelper.redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public static List<Object> lRange(String k, long l, long l1) {
        @SuppressWarnings("unchecked")
        ListOperations<String, Object> list = redisTemplateHelper.redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public static void add(String key, Object value) {
        @SuppressWarnings("unchecked")
        SetOperations<String, Object> set = redisTemplateHelper.redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public static Set<Object> setMembers(String key) {
        @SuppressWarnings("unchecked")
        SetOperations<String, Object> set = redisTemplateHelper.redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public static void zAdd(String key, Object value, double scoure) {
        @SuppressWarnings("unchecked")
        ZSetOperations<String, Object> zset = redisTemplateHelper.redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public static Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        @SuppressWarnings("unchecked")
        ZSetOperations<String, Object> zset = redisTemplateHelper.redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}
