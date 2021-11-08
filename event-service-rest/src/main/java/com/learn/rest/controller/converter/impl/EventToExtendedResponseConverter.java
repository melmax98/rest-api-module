package com.learn.rest.controller.converter.impl;

import com.learn.rest.controller.converter.ExtendedResponseConverter;
import com.learn.rest.entity.Event;
import com.learn.rest.entity.EventExtendedResponse;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Stream;

@Component
public class EventToExtendedResponseConverter implements ExtendedResponseConverter<Event, EventExtendedResponse> {

    private static final String ID_EVENT_URI = "/l3/event/{id}";

    @Override
    public EventExtendedResponse convert(Event event) {
        return Stream.of(event)
                .map(e -> {
                    EventExtendedResponse eventExtendedResponse = new EventExtendedResponse(
                            e.getId(),
                            e.getDateTime().toString(),
                            e.getEventType(),
                            e.getPlace().toString(),
                            e.getSpeaker(),
                            e.getTitle()
                    );
                    eventExtendedResponse.add(
                            Link.of(ServletUriComponentsBuilder.fromCurrentContextPath().path(ID_EVENT_URI)
                                    .buildAndExpand(e.getId()).toUriString())
                    );

                    return eventExtendedResponse;
                })
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
