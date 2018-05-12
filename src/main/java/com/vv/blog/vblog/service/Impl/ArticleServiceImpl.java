package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.Utils.MarkdownUtil;
import com.vv.blog.vblog.dao.ArticleDao;
import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article addArticle(String auth, String title, String content, String description, String category) {
        Article article = new Article();
        article.setAuth(auth);
        article.setTitle(title);
        article.setContent(MarkdownUtil.transfer(content));
        article.setDescription(description);
        article.setCategory(category);
        article.setDate(new Date());

        articleDao.insertArticle(article);

        return article;
    }

    @Override
    public Article deleteArticle(int id) {
        Article article = articleDao.selectArticleById(id);
        if(article != null) {
            articleDao.deleteArticle(id);
        }
        return article;
    }

    @Override
    public List<Article> selectArticleByCategoty(String username, String category) {
        return articleDao.selectArticleByCategory(username, category);
    }

    @Override
    public List<Article> selectPageArticle(int pageid, int limit) {
        return articleDao.selectPageArticle(pageid, limit);
    }

    @Override
    public int getArticleCnt() {
        return articleDao.selectArticleCnt();
    }

    @Override
    public int getCategoryArticleCnt(String category) {
        return articleDao.countOfCategoryArticle(category);
    }

    @Override
    public Article getArticleById(int id) {
        return articleDao.selectArticleById(id);
    }

    @Override
    public List<Article> getNewestArticle(int offset, int limit) {
        return articleDao.selectNewsetArticle(offset, limit);
    }
}
