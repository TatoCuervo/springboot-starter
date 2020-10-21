package com.tatocuervo.springbootstarter.course.service;

import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> getCoursesByTopic(long topicId) {
        return repository.findByTopicId(topicId);
    }
}
