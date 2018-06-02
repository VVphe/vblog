package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@Mapper
public interface ArticleDao {

    String TABLE_NAME = "article";
    String INSERT_FIELD = "auth, title, content, description, date, commentscnt, category";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELD, ") values (#{auth},#{title},#{content},#{description},#{date},#{commentsCnt},#{category})"})
    void insertArticle(Article article);

    @Delete({"delete from", TABLE_NAME, "where id=#{id}"})
    void deleteArticle(@Param("id") int id);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where id=#{id}"})
    Article selectArticleById(@Param("id") int id);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where title=#{title} and auth=#{auth}"})
    Article selectArticleByTitle(@Param("title") String title, @Param("auth") String auth);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where category=#{category} and auth=#{username}"})
    List<Article> selectArticleByCategory(@Param("username") String username, @Param("category") String category);

    @Select({"select count(id) from", TABLE_NAME, "where category=#{category}"})
    int countOfCategoryArticle(@Param("category") String category);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "order by id desc limit #{offset},#{limit}"})
    List<Article> selectPageArticle(@Param("offset") int offset, @Param("limit") int limit);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where category=#{category} order by id desc limit #{offset},#{limit}"})
    List<Article> selectCategoryPageArticle(@Param("category") String category, @Param("offset") int offset, @Param("limit") int limit);

    @Update({"update", TABLE_NAME, "set commentscnt=#{commentsCnt} where id=#{id}"})
    void updateCommentsCnt(@Param("id") int id, @Param("commentsCnt") int commentsCnt);

    @Select({"select count(id) from", TABLE_NAME})
    int selectArticleCnt();

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "order by date desc limit #{offset},#{limit}"})
    List<Article> selectNewsetArticle(@Param("offset") int offset, @Param("limit") int limit);

}
