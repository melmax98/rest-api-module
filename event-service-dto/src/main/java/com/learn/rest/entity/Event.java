package com.learn.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@ApiModel("Event Model")
@Data
@Entity
public class Event {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(notes = "Title of the event", name = "title", required = true, example = "Some title")
    @Column
    private String title;

    @ApiModelProperty(notes = "Place number", name = "place", required = true, example = "1")
    @Column
    private Integer place;

    @ApiModelProperty(notes = "Name of the speaker", name = "speaker", required = true, example = "Speaker")
    @Column
    private String speaker;

    @ApiModelProperty(notes = "Type of the event", name = "eventType", required = true, example = "Common")
    @Column
    private String eventType;

    @ApiModelProperty(notes = "Date of the event", name = "dateTime", required = true, example = "2021-04-02")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDate dateTime;
}