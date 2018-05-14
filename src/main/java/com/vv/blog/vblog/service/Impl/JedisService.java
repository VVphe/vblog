package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.Utils.JedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Service
public class JedisService implements InitializingBean {

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("39.108.184.172", 6379);
    }

    public double zincrby(String key,String value){
        Jedis jedis = jedisPool.getResource();
        double result = jedis.zincrby(key,1,value);
        jedis.close();

        return result;
    }

    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.zrevrange(key,start,end);
        jedis.close();
        return set;
    }

    public int getValue(String key, int uri) {
        Jedis jedis = jedisPool.getResource();
        String id = String.valueOf(uri);
        String urlKey = JedisUtil.getClickCountKey(id);
        int value = jedis.zscore(key, urlKey).intValue();
        jedis.close();
        return value;
    }
}
