package com.vv.blog.vblog.Utils;

import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.service.ArticleService;
import com.vv.blog.vblog.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class LoggerUtil {
//
//    @Pointcut("")
//    public void loginCell() {
//
//    }
    @Autowired
    private ArticleService articleService;

    @Autowired
    private LogService logService;

    @Pointcut("execution(* com.vv.blog.vblog.controller.*.publish*(..))")
    public void publishCell() {
    }

    @AfterReturning(value = "publishCell()", returning = "object")
    public void publishLog(JoinPoint joinPoint, Object object) {
        String methodName = joinPoint.getSignature().getName();

        List<Object> args = new ArrayList<>();
        for(Object info: joinPoint.getArgs()) {
            args.add(info);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);

        String log;

        switch (methodName) {
            case "publishArticle":
                String auth = args.get(0).toString();
                String title = args.get(1).toString();
                String category = args.get(4).toString();

                log = auth + " published article < " + title + " > about " + category + " at " + dateString;
                logService.addLog(log, date);

                break;

            case "publishComment":
                String username = args.get(1).toString();
                int articleId = Integer.parseInt(args.get(2).toString());
                Article article = articleService.getArticleById(articleId);
                String articleTitle = article.getTitle();

                log = username + " replied the article < " + articleTitle + " > at " + dateString;
                logService.addLog(log, date);

                break;

            case "publishEvent":
               String eventTitle = args.get(1).toString();
               String start = args.get(2).toString();

               log = "vv added an event named with < " + eventTitle + " > started from " + start + " at " + dateString;
               logService.addLog(log, date);
        }
    }



}
