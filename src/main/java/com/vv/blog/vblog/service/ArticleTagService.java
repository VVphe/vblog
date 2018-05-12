package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Article;
import com.vv.blog.vblog.entity.ArticleTag;
import com.vv.blog.vblog.entity.Tag;

import java.util.List;

public interface ArticleTagService {

    ArticleTag addArticleTag(int tagid, int articleid);

    List<Article> selectArticlesOfTag(String tagname);

    List<Tag> selectTagsOfArticle(int articleid);
}
