package com.vv.blog.vblog.Configuration;

import com.vv.blog.vblog.Interceptor.ArticleHotInterceptor;
import com.vv.blog.vblog.Interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private ArticleHotInterceptor articleHotInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(authInterceptor).excludePathPatterns("/login");
        registry.addInterceptor(articleHotInterceptor).addPathPatterns("/article/readarticle");
    }
}
