package com.vv.blog.vblog.service;

import com.vv.blog.vblog.entity.Event;

import java.util.List;

public interface EventService {

    Event addEvent(String eventid, String eventtitle, String start, String end);

    List<Event> getEvents();

    void updateEvent(String eventid, String start, String end);

    void deleteEvent(String eventid);

}
