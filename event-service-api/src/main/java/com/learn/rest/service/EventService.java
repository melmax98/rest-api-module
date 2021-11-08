package com.learn.rest.service;

import com.learn.rest.entity.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    Event updateEvent(Event event);

    Event getEvent(Long id);

    void deleteEvent(Long eventId);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);
}