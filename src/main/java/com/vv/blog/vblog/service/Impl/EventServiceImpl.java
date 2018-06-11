package com.vv.blog.vblog.service.Impl;

import com.vv.blog.vblog.dao.EventDao;
import com.vv.blog.vblog.entity.Event;
import com.vv.blog.vblog.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public Event addEvent(String eventid, String eventtitle, String start, String end) {
        Event event = new Event();
        event.setEventid(eventid);
        event.setEventtitle(eventtitle);
        event.setStart(start);
        event.setEnd(end);
        eventDao.insertEvent(event);
        return event;
    }

    @Override
    public List<Event> getEvents() {
        return eventDao.selectEvents();
    }

    @Override
    public void updateEvent(String eventid, String start, String end) {
        eventDao.updateEvent(eventid, start, end);
    }

    @Override
    public void deleteEvent(String eventid) {
        eventDao.deleteEvent(eventid);
    }
}
