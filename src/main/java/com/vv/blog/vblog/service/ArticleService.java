package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.entity.ArticleTag;

import java.util.List;

public interface ArticleService {
    Article addArticle(String auth, String title, String content, String description, String category);

    Article deleteArticle(int id);

    List<Article> selectArticleByCategoty(String username, String category);

    List<Article> selectPageArticle(int pageid, int limit);

    int getArticleCnt();

    int getCategoryArticleCnt(String category);

    Article getArticleById(int id);

    Article getArticleByTitle(String title, String auth);

    List<Article> getNewestArticle(int offset, int limit);

    void updateCommentsCnt(int id, int commentsCnt);

    List<Article> searchArticleByName(String key);
}
