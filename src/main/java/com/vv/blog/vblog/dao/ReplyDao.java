package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReplyDao {
    String TABLE_NAME = "reply";
    String INSERT_FIELD = "cmid, replyid, replytype, replycontent, fromuser, touser";
    String SELECT_FIELD = "id," + INSERT_FIELD;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELD, ") values(#{cmid}, #{replyid}, #{replytype}, #{replycontent}, #{fromuser}, #{touser})"})
    void insertReply(Reply reply);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where cmid=#{cmid}"})
    List<Reply> selectReply(@Param("cmid") int cmid);
}
