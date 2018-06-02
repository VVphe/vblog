package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.dao.ArticleDao;
import com.vv.blog.vblog.dao.ArticleTagDao;
import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.entity.ArticleTag;
import com.vv.blog.vblog.entity.Tag;
import com.vv.blog.vblog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Autowired
    private ArticleDao articleDao;

    @Override
    public ArticleTag addArticleTag(int tagid, int articleid) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setTagid(tagid);
        articleTag.setArticleid(articleid);
        articleTagDao.insertArticleTag(articleTag);
        return articleTag;
    }

    @Override
    public List<Article> selectArticlesOfTag(String tagname) {
        List<Integer> articleids = articleTagDao.selectArticlesOfTag(tagname);
        List<Article> articles = new ArrayList<>();
        for(int articleid : articleids) {
            articles.add(articleDao.selectArticleById(articleid));
        }

        return articles;
    }

    @Override
    public List<Tag> selectTagsOfArticle(int articleid) {
        return articleTagDao.selectTagsOfArticle(articleid);
    }
}
