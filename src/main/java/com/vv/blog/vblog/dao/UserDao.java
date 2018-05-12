package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    String TABLE_NAEM = " user ";
//    String INSERT_FIELDS = " username, password, salt, head_url ,role ";
    String INSERT_FIELDS = " username, password, salt, role, url ";
    @Insert({"insert into", TABLE_NAEM, "(", INSERT_FIELDS, ") values (#{username},#{password},#{salt},#{role},#{url})"})
    void insertUser(User user);

    @Select({"select", INSERT_FIELDS, "from", TABLE_NAEM, "where username=#{username}"})
    User selectByUsername(@Param("username") String username);

    @Delete({"delete from", TABLE_NAEM, "where username=#{username}"})
    void deleteByUsername(@Param("username") String username);

}
