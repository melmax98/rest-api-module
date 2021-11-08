package com.learn.rest.controller;

import com.learn.rest.entity.Event;
import com.learn.rest.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "Gets all events")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @ApiOperation(value = "Gets an event by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class)
    })
    @GetMapping("/{eventId}")
    public Event getEvent(@PathVariable Long eventId) {
        return eventService.getEvent(eventId);
    }

    @ApiOperation(value = "Gets events by title")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class, responseContainer = "List")
    })
    @GetMapping("/title/{title}")
    public List<Event> getEventsByTitle(@PathVariable String title) {
        return eventService.getAllEventsByTitle(title);
    }

    @ApiOperation(value = "Creates an event")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class),
            @ApiResponse(code = 403, message = "Event from request already exists or contains ID")
    })
    @PostMapping
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @ApiOperation(value = "Updates an event by ID")
    @ApiParam
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class)
    })
    @PutMapping("/{eventId}")
    public Event updateEvent(@PathVariable Long eventId, Event event) {
        event.setId(eventId);
        return eventService.updateEvent(event);
    }

    @ApiOperation(value = "Deletes an event by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }
}
