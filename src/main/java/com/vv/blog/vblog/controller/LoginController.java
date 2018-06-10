package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.Utils.BlogUtil;
import com.vv.blog.vblog.Utils.JwtUtil;
import com.vv.blog.vblog.dao.TokenManager;
import com.vv.blog.vblog.dao.UserDao;
import com.vv.blog.vblog.entity.User;
import com.vv.blog.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.register(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @PostMapping("/exist")
    public boolean exist(String username) {
        if(userService.selectByUsername(username) != null) {
            return true;
        } else
            return false;
    }

    @PostMapping("logout")
    public ResponseEntity logout(String username) {
        tokenManager.deleteToken(username);
        return new ResponseEntity(HttpStatus.OK);
    }

}
