package com.tatocuervo.springbootstarter.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    private String name;
    private String description;
    private String topicId; //TODO: should it hold object or just Id it's fine ?
}
