package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.Utils.BlogUtil;
import com.vv.blog.vblog.dao.TokenManager;
import com.vv.blog.vblog.dao.UserDao;
import com.vv.blog.vblog.entity.User;
import com.vv.blog.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassword(BlogUtil.MD5(password + user.getSalt()));
        user.setRole("user");
        user.setUrl(username);
        userDao.insertUser(user);

        return user;
    }

    @Override
    public String login(String username, String password) {
        User user = userDao.selectByUsername(username);
        if(user == null) {
            return "Not Found";
        }

        if(!BlogUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
            return "Error Password";
        }

        String token = tokenManager.createToken(username);

        return token;
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public void deleteByUsername(String username) {
        userDao.deleteByUsername(username);
    }
}
