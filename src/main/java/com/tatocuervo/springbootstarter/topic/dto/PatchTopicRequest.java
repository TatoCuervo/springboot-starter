package com.tatocuervo.springbootstarter.topic.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
public class PatchTopicRequest {
    String name;
    String description;

    @JsonCreator
    public PatchTopicRequest(@JsonProperty String name, @JsonProperty String description) {
        this.name = name;
        this.description = description;
    }
}
