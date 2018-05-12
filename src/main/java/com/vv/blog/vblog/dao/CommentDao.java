package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    String TABLE_NAME = "comments";
    String INSERT_FIELD = "cmcontent, cmdate, username, articleid, stars";
    String SELECT_FIELD = "cmid," + INSERT_FIELD;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELD, ") values(#{cmcontent},#{cmdate},#{username},#{articleid},#{stars})"})
    Comment insertComment(Comment comment);

    @Update({"update", TABLE_NAME, "set stars=#{stars} where cmid=#{cmid}"})
    void updateCommentsCnt(@Param("cmid") int cmid, @Param("stars") int stars);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where articleid=#{articleid}"})
    List<Comment> selectCommentByArticleid(@Param("articleid") int articleid);

    @Select({"select count(id) from", TABLE_NAME, "where articleid=#{articleid}"})
    int selectCmCntByArticleid(@Param("articleid") int articleid);

}
