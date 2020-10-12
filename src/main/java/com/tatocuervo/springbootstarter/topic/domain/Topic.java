package com.tatocuervo.springbootstarter.topic.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Topic {
    private String id;
    private String name;
    private String description;

}
