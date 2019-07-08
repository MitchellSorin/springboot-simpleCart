package com.sorin.simplecart.utils.redis;

import org.hibernate.event.spi.PostUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具
 *
 * @author LSD
 * @date 2019/06/19
 **/
@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 设置失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return boolean
     * @author LSD
     * @date 2019/6/19
     */
    public static boolean expire(String key, long time) {
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
    public static long getExpire(String key) {
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
    public static boolean hasKey(String key) {
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
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 删除模糊匹配到的所有键
     *
     * @param regex
     * @return void
     * @author LSD
     * @date 2019/7/8
     */
    public static void delByRegex(String regex) {
        Set<String> keys = getKeys(regex);
        Iterator<String> iterator = keys.iterator();
        if (iterator.hasNext()) {
            del(iterator.next());
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
    public static Object get(String key) {
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
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
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
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 模糊查询所有的key
     *
     * @param reg
     * @return java.util.Set<java.lang.String>
     * @author LSD
     * @date 2019/7/8
     */
    public static Set<String> getKeys(String reg) {
        return redisTemplate.keys(reg);
    }


}
