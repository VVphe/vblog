package com.vv.blog.vblog.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggerUtil {
//
//    @Pointcut("")
//    public void loginCell() {
//
//    }

    @Pointcut("execution(* com.vv.blog.vblog.controller.*.getArticle(..))")
    public void publishCell() {
    }

    @AfterReturning(value = "publishCell()", returning = "object")
    public void publishLog(JoinPoint joinPoint, Object object) {
        String methodName = joinPoint.getSignature().getName();
        String opContent = getContent(joinPoint.getArgs(), methodName);
        System.out.println(methodName);
        System.out.println(joinPoint.getArgs());
        for(Object info: joinPoint.getArgs()) {
            System.out.println(info);
        }
    }

    public String getContent(Object[] args, String methonName) {
        if(args == null) return null;
        StringBuffer rs = new StringBuffer();
        rs.append(methonName);
        String className = null;
        int index = 1;

        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型:" + className + "，值:");

            Method[] methods = info.getClass().getDeclaredMethods();

            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
//                if (methodName.indexOf("get") == -1) {
//                    continue;// 不处理
//                }
                Object rsValue = null;
                try {

                    rsValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }

                rs.append("(" + methodName + ":" + rsValue + ")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }

}
