package com.tatocuervo.springbootstarter.course.controller;

import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.service.CourseService;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Course getCourse(@PathVariable String id) {
        return null;
    }

    @ApiOperation(value = "Creates new Course for a given topic")
    @PostMapping
    public void addCourse(@PathVariable String topicId, @RequestBody Course course) {

    }

    @ApiOperation(value = "Updates course within a topic")
    @PutMapping
    public void updateCourse(@RequestBody Course course) {

    }

    @ApiOperation(value = "Delete a course by id")
    @DeleteMapping(path = "/{id}")
    public void deleteCourse(@PathVariable String id) {

    }
}
