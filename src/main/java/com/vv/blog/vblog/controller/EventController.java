package com.vv.blog.vblog.controller;

import com.vv.blog.vblog.entity.Event;
import com.vv.blog.vblog.entity.EventFormat;
import com.vv.blog.vblog.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/publish")
    public Event publish(@RequestParam("eventid") String eventid, @RequestParam("eventtitle") String eventtitle, @RequestParam("start") String start, @RequestParam("end") String end) {
        return eventService.addEvent(eventid, eventtitle, start, end);
    }

    @GetMapping("/events")
    public List<EventFormat> getEvents() {
        List<Event> events = eventService.getEvents();
        List<EventFormat> eventFormats = new ArrayList<>();
        for(Event event: events) {
            EventFormat eventFormat = new EventFormat();
            eventFormat.setId(event.getEventid());
            eventFormat.setTitle(event.getEventtitle());
            eventFormat.setStart(event.getStart());
            eventFormat.setEnd(event.getEnd());
            eventFormats.add(eventFormat);
        }

        return eventFormats;
    }

    @PostMapping("/update")
    public void updateEvent(@RequestParam("eventid") String eventid, @RequestParam("start") String start, @RequestParam("end") String end) {
        eventService.updateEvent(eventid, start, end);
    }

    @PostMapping("/delete")
    public void deleteEvent(@RequestParam("eventid") String eventid) {
        eventService.deleteEvent(eventid);
    }

}
