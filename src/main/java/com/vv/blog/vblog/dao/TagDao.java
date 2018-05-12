package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TagDao {

    String TABLE_NAME = "tag";
    String INSERT_FIELD = "tagname";
    String SELECT_FIELD = "tagid," + INSERT_FIELD;

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME, "where tagname=#{tagname}"})
    Tag selectTagByName(@Param("tagname") String tagname);

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELD, ") values(#{tagname})"})
    void insertTag(Tag tag);

}
