package com.tatocuervo.springbootstarter.topic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class CreateTopicRequest {
    String name;
    String description;

    @JsonCreator
    public CreateTopicRequest(@JsonProperty String name, @JsonProperty String description) {
        this.name = name;
        this.description = description;
    }
}
