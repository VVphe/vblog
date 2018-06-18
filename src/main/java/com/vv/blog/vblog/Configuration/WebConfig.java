package com.vv.blog.vblog.Configuration;

import com.vv.blog.vblog.Interceptor.ArticleHotInterceptor;
import com.vv.blog.vblog.Interceptor.AuthInterceptor;
import com.vv.blog.vblog.Interceptor.CategoryDayCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private ArticleHotInterceptor articleHotInterceptor;

    @Autowired
    private CategoryDayCountInterceptor categoryDayCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        //patterns.add("/login");
        patterns.add("/article/publish");
        patterns.add("/article/delete");
        patterns.add("/event/publish");
        patterns.add("/event/update");
        patterns.add("/event/delete");

        registry.addInterceptor(authInterceptor).addPathPatterns(patterns);
        registry.addInterceptor(articleHotInterceptor).addPathPatterns("/article/readarticle");
        registry.addInterceptor(categoryDayCountInterceptor).addPathPatterns("/article/readarticle");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
