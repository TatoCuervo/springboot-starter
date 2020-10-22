package com.tatocuervo.springbootstarter.course.controller;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.service.CourseService;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Courses")
@RestController
@RequestMapping(path = Routes.COURSES)
public class CourseController {

    @Autowired
    private CourseService service;

    @ApiOperation(value = "Get all courses within a topic")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAllCoursesByTopic(@PathVariable long topicId) {
        return service.getCoursesByTopic(topicId);
    }

    @ApiOperation(value = "Get a course by id within a topic")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course getCourse(@PathVariable long topicId, @PathVariable long id) throws ResourceNotFoundException {
        return service.getCourseById(topicId, id);
    }

    @ApiOperation(value = "Creates new Course for within topic")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCourse(@PathVariable long topicId, @RequestBody Course course) throws ResourceNotFoundException {
        service.addCourse(course, topicId);
    }

    @ApiOperation(value = "Updates course within a topic")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}")
    public void updateCourse(@PathVariable long topicId, @PathVariable long id, @RequestBody Course course) throws ResourceNotFoundException {
        service.updateCourse(topicId, course, id);
    }

    @ApiOperation(value = "Delete a course by id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteCourse(@PathVariable long topicId, @PathVariable long id) throws ResourceNotFoundException {
        service.deleteCourseById(topicId, id);
    }
}
