package com.vv.blog.vblog.dao.Impl;

import com.vv.blog.vblog.Utils.JwtUtil;
import com.vv.blog.vblog.dao.TokenManager;
import com.vv.blog.vblog.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate redis;

    @Autowired
    @Qualifier("redisTemplate")
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Autowired
    private UserService userService;

    @Override
    public String createToken(String username) {
        String token = JwtUtil.getToken(username);
        redis.boundValueOps(username).set(token, 72, TimeUnit.HOURS);
        return token;
    }

    @Override
    public boolean getToken(String authentication){
        try {
            Claims claims = Jwts.parser().setSigningKey("base64EncodedSecretKey").parseClaimsJws(authentication).getBody();
            String username = claims.getSubject();
            String role = userService.selectByUsername(username).getRole();
            if (role.equals("admin")) {
                redis.boundValueOps(username).expire(72, TimeUnit.HOURS);
                return true;
            }
        }catch (ExpiredJwtException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean checkToken(String token) {
//        if(token == null) {
//            return false;
//        }
//
//        String tk = (String) redis.boundValueOps(username).get();
//        if(tk == null || !tk.equals(token)) {
//            return false;
//        }


//        try {
//            JwtUtil.checkToken(token);
//            redis.boundValueOps(username).expire(72, TimeUnit.HOURS);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;

//        try {
//            JwtUtil.checkToken(token);
//
//            redis.boundValueOps(userName).expire(72, TimeUnit.HOURS);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return false;
    }

    @Override
    public void deleteToken(String username) {
        redis.delete(username);
    }
}
