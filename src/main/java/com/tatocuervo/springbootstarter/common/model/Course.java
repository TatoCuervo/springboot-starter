package com.tatocuervo.springbootstarter.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    private String id;
    private String name;
    private String description;
}
