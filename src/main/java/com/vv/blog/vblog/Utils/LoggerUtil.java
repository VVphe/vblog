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

                log = username + "replied the article < " + articleTitle + " > at " + dateString;
                logService.addLog(log, date);

                break;
        }
    }

//    public String getContent(Object[] args, String methonName) {
//        if(args == null) return null;
//        StringBuffer rs = new StringBuffer();
//        rs.append(methonName);
//        String className = null;
//        int index = 1;
//
//        for (Object info : args) {
//            // 获取对象类型
//            className = info.getClass().getName();
//            className = className.substring(className.lastIndexOf(".") + 1);
//            rs.append("[参数" + index + "，类型:" + className + "，值:");
//
//            Method[] methods = info.getClass().getDeclaredMethods();
//
//            for (Method method : methods) {
//                String methodName = method.getName();
//                // 判断是不是get方法
////                if (methodName.indexOf("get") == -1) {
////                    continue;// 不处理
////                }
//                Object rsValue = null;
//                try {
//
//                    rsValue = method.invoke(info);
//                } catch (Exception e) {
//                    continue;
//                }
//
//                rs.append("(" + methodName + ":" + rsValue + ")");
//            }
//            rs.append("]");
//            index++;
//        }
//        return rs.toString();
//    }

}
