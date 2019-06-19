package com.sorin.simplecart.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * redis工具
 *
 * @author LSD
 * @date 2019/06/19
 **/
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return boolean
     * @author LSD
     * @date 2019/6/19
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取剩余有效时间
     *
     * @param key 键
     * @return long 时间(秒) 0代表永久有效
     * @author LSD
     * @date 2019/6/19
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return boolean
     * @author LSD
     * @date 2019/6/19
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @author LSD
     * @date 2019/6/19
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return java.lang.Object
     * @author LSD
     * @date 2019/6/19
     */
    public Object get(String key) {
        return null == key ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间
     * @return boolean
     * @author LSD
     * @date 2019/6/19
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     * @return boolean
     * @author LSD
     * @date 2019/6/19
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
