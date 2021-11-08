package com.learn.rest.service.impl;

import com.learn.rest.entity.Event;
import com.learn.rest.exceptions.EventNotFoundException;
import com.learn.rest.exceptions.EventWithIdException;
import com.learn.rest.repository.EventRepository;
import com.learn.rest.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        if (event.getId() != null) {
            throw new EventWithIdException();
        }

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        eventRepository.findById(event.getId())
                .orElseThrow(EventNotFoundException::new);
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.delete(eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findAllByTitle(title);
    }
}
