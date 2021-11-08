package com.learn.rest.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EventExtendedResponse extends RepresentationModel<RepresentationModel<?>> {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(notes = "Date of the event", name = "dateTime", required = true, example = "2021-04-02")
    private String dateTime;

    @ApiModelProperty(notes = "Type of the event", name = "eventType", required = true, example = "Common")
    private String eventType;

    @ApiModelProperty(notes = "Place number", name = "place", required = true, example = "1")
    private String place;

    @ApiModelProperty(notes = "Name of the speaker", name = "speaker", required = true, example = "Speaker")
    private String speaker;

    @ApiModelProperty(notes = "Title of the event", name = "title", required = true, example = "Some title")
    private String title;
}
