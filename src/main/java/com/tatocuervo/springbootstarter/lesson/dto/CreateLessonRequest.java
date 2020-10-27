package com.tatocuervo.springbootstarter.lesson.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class CreateLessonRequest {
    private String name;

    private String description;

    @JsonCreator
    public CreateLessonRequest(@JsonProperty String name, @JsonProperty String description) {
        this.name = name;
        this.description = description;
    }
}
