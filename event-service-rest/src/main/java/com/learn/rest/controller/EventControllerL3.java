package com.learn.rest.controller;

import com.learn.rest.controller.converter.ExtendedResponseConverter;
import com.learn.rest.entity.Event;
import com.learn.rest.entity.EventExtendedResponse;
import com.learn.rest.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/l3/event")
@RequiredArgsConstructor
public class EventControllerL3 {
    private static final String EVENT_URI = "/l3/event";

    private final EventService eventService;
    private final ExtendedResponseConverter<Event, EventExtendedResponse> extendedResponseConverter;

    @ApiOperation(value = "Gets all events")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = EventExtendedResponse.class, responseContainer = "List"),
    })
    @GetMapping
    public List<EventExtendedResponse> getAllEvents() {
        return eventService.getAllEvents()
                .stream()
                .map(extendedResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Gets an event by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class)
    })
    @GetMapping("/{eventId}")
    public EventExtendedResponse getEvent(@PathVariable Long eventId) {
        return extendedResponseConverter.convert(eventService.getEvent(eventId));
    }

    @ApiOperation(value = "Gets events by title")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class, responseContainer = "List")
    })
    @GetMapping("/title/{title}")
    public List<EventExtendedResponse> getEventsByTitle(@PathVariable String title) {
        return eventService.getAllEventsByTitle(title)
                .stream()
                .map(extendedResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Creates an event")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class),
            @ApiResponse(code = 403, message = "Event from request already exists or contains ID")
    })
    @PostMapping
    public EventExtendedResponse createEvent(Event event) {
        return extendedResponseConverter.convert(eventService.createEvent(event));
    }

    @ApiOperation(value = "Updates an event by ID")
    @ApiParam
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Event.class)
    })
    @PutMapping("/{eventId}")
    public EventExtendedResponse updateEvent(@PathVariable Long eventId, Event event) {
        event.setId(eventId);
        return extendedResponseConverter.convert(eventService.updateEvent(event));
    }

    @ApiOperation(value = "Deletes an event by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    @DeleteMapping("/{eventId}")
    public RepresentationModel<RepresentationModel<?>> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return new RepresentationModel<>(
                Link.of(ServletUriComponentsBuilder.fromCurrentContextPath().path(EVENT_URI).build().toUriString())
        );
    }
}
