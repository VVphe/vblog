package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.ArticleTag;
import com.vv.blog.vblog.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleTagDao {

    String TABLE_NAME = "article_tag";
    String FIELD = "tagid, articleid";
    String TAG_FIELD = "tagid, tagname";

    @Insert({"insert into", TABLE_NAME, "(", FIELD, ") values(#{tagid},#{articleid})"})
    void insertArticleTag(ArticleTag articleTag);

    @Select({"select articleid from article_tag natural join tag where tagname=#{tagname}"})
    List<Integer> selectArticlesOfTag(String tagname);

    @Select({"select", TAG_FIELD, "from tag where tagid in (select tagid from article_tag where articleid=#{articleid})"})
    List<Tag> selectTagsOfArticle(int articleid);

}
