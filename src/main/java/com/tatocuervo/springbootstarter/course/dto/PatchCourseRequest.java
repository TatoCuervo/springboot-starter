package com.tatocuervo.springbootstarter.course.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class PatchCourseRequest {
    private String name;

    private String description;

    @JsonCreator
    public PatchCourseRequest(@JsonProperty String name, @JsonProperty String description) {
        this.name = name;
        this.description = description;
    }
}
