package com.vv.blog.vblog.dao;

import com.vv.blog.vblog.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LogDao {

    String TABLE_NAME = "log";
    String INSERT_FIELD = "logcontent, logdate";
    String SELECT_FIELD = "logid," + INSERT_FIELD;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELD, ") values (#{logcontent},#{logdate})"})
    void insertLog(Log log);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME})
    List<Log> selectLogs();

}
