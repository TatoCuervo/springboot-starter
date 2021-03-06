package com.tatocuervo.springbootstarter.course.controller;

import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.dto.CreateCourseRequest;
import com.tatocuervo.springbootstarter.course.dto.PatchCourseRequest;
import com.tatocuervo.springbootstarter.course.service.CourseService;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer jwt_token")
    public List<Course> getAllCoursesByTopic(@PathVariable long topicId) {
        return service.getCoursesByTopic(topicId);
    }

    @ApiOperation(value = "Get a course by id within a topic")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer jwt_token")
    public Course getCourse(@PathVariable long topicId, @PathVariable long id) {
        return service.getCourseById(topicId, id);
    }

    @ApiOperation(value = "Creates new Course for within topic")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer jwt_token")
    public void addCourse(@PathVariable long topicId, @RequestBody CreateCourseRequest createCourseRequest) {
        service.addCourse(conversionService.convert(createCourseRequest, Course.class), topicId);
    }

    @ApiOperation(value = "Updates course within a topic")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer jwt_token")
    public void patchCourse(@PathVariable long topicId, @PathVariable long id, @RequestBody PatchCourseRequest patchCourseRequest) {
        Course updatedCourse = conversionService.convert(patchCourseRequest, Course.class);
        service.updateCourse(topicId, updatedCourse, id);
    }

    @ApiOperation(value = "Delete a course by id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer jwt_token")
    public void deleteCourse(@PathVariable long topicId, @PathVariable long id) {
        service.deleteCourseById(topicId, id);
    }
}
