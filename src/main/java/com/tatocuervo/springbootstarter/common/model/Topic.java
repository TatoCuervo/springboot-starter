package com.tatocuervo.springbootstarter.common.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {
    @Id
    private String name;
    private String description;
}
