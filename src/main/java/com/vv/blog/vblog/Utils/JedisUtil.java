package com.vv.blog.vblog.Utils;

public class JedisUtil {
    private static String SPLITE = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";
    private static String BIZ_ARTICLE = "ARTICLE";
    private static String BIZ_CATEGORY = "CATEGORY";
    private static String BIZ_CLICK = "CLICK";
    private static String BIZ_ARTICLE_CLICK = "ARTICLE_CLICK";
    private static String BIZ_EVENTQUEUE = "EVENTQUEUE";

    public static String getClickCountKey(String url){
        return BIZ_CLICK+SPLITE+url+SPLITE+"COUNT";
    }
}
