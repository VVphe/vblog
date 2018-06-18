package com.vv.blog.vblog.dao;


import com.vv.blog.vblog.entity.Event;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EventDao {

    String TABLE_NAME = "event";
    String INSERT_FIELD = "eventid, eventtitle, start, end";
    String SELECT_FIELD = "eventid, eventtitle, start, end";

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELD, ") values(#{eventid},#{eventtitle},#{start},#{end})"})
    void insertEvent(Event event);

    @Select({"select", SELECT_FIELD, "from", TABLE_NAME})
    List<Event> selectEvents();

    @Update({"update", TABLE_NAME, "set start=#{start},end=#{end} where eventid=#{eventid}"})
    void updateEvent(@Param("eventid") String eventid, @Param("start") String start, @Param("end") String end);

    @Update({"update", TABLE_NAME, "set eventtitle=CONCAT(eventtitle,'(finished)') where eventid=#{eventid}"})
    void deleteEvent(@Param("eventid") String eventid);

}
