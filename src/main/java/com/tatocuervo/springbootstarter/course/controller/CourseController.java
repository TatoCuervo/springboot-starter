package com.tatocuervo.springbootstarter.course.controller;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.dto.CreateCourseRequest;
import com.tatocuervo.springbootstarter.course.dto.PatchCourseRequest;
import com.tatocuervo.springbootstarter.course.service.CourseService;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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

    @Autowired
    private ConversionService conversionService;

    @ApiOperation(value = "Get all courses within a topic")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAllCoursesByTopic(@PathVariable long topicId) throws ResourceNotFoundException {
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
    public void addCourse(@PathVariable long topicId, @RequestBody CreateCourseRequest createCourseRequest) throws ResourceNotFoundException {
        service.addCourse(conversionService.convert(createCourseRequest, Course.class), topicId);
    }

    @ApiOperation(value = "Updates course within a topic")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{id}")
    public void patchCourse(@PathVariable long topicId, @PathVariable long id, @RequestBody PatchCourseRequest patchCourseRequest) throws ResourceNotFoundException {
        Course updatedCourse = conversionService.convert(patchCourseRequest, Course.class);
        service.updateCourse(topicId, updatedCourse, id);
    }

    @ApiOperation(value = "Delete a course by id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteCourse(@PathVariable long topicId, @PathVariable long id) throws ResourceNotFoundException {
        service.deleteCourseById(topicId, id);
    }
}
