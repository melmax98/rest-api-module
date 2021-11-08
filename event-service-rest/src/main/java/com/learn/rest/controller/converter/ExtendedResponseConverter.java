package com.learn.rest.controller.converter;

import org.springframework.hateoas.RepresentationModel;

public interface ExtendedResponseConverter<I, O extends RepresentationModel<RepresentationModel<?>>> {
    O convert(I input);
}
