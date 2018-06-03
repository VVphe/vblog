package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.Utils.JedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //获得文章阅读量
    public int getValue(String key, int uri) {
        Jedis jedis = jedisPool.getResource();
        String id = String.valueOf(uri);
        String urlKey = JedisUtil.getClickCountKey(id);
        if(jedis.zscore(key, urlKey) == null) {
            return 0;
        }
        int value = jedis.zscore(key, urlKey).intValue();
        jedis.close();
        return value;
    }

    //获得类目近一周每天的总阅读量
    public Map<String, Integer> getCategoryValue(String key, String category) {
        Jedis jedis = jedisPool.getResource();
//        String urlKey = JedisUtil.getCategoryDayCountKey(category, date) ;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Map<String, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < 7; i ++) {
            calendar.add(Calendar.DATE, -1);
            String day = new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
            String urlKey = JedisUtil.getCategoryDayCountKey(category + day);
            if(jedis.zscore(key, urlKey) == null) {
                map.put(day, 0);
            } else {
                map.put(day, jedis.zscore(key, urlKey).intValue());
            }
        }

        return map;
    }

}
