package com.vv.blog.vblog.Interceptor;

import com.vv.blog.vblog.Utils.JedisUtil;
import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.service.ArticleService;
import com.vv.blog.vblog.service.Impl.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class CategoryDayCountInterceptor implements HandlerInterceptor{

    @Autowired
    private JedisService jedisService;

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //String uri = httpServletRequest.getServletPath().split("/")[2];
        String uri = httpServletRequest.getParameter("articleid");

        Article article = articleService.getArticleById(Integer.parseInt(uri));

        if(article != null) {

            Calendar calendar = Calendar.getInstance();

            String day = new SimpleDateFormat("yyyy-MM-dd ").format(calendar.getTime());
            String key = article.getCategory() + day;

            String uriKey = JedisUtil.getCategoryDayCountKey(key);

            jedisService.zincrby("categoryCount", uriKey);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
