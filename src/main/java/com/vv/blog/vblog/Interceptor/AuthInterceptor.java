package com.vv.blog.vblog.Interceptor;

import com.vv.blog.vblog.dao.TokenManager;

import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(Constants.AUTHORIZATION_HEADER_NAME);

        //String token = auth.substring(7);

//        try {
//            JwtUtil.checkToken(token);
//            return true;
//        }catch (Exception e) {
//            throw new ServletException(e.getMessage());
//        }
        if(manager.getToken(token)) {
            return true;
        }
        return false;
    }
}
