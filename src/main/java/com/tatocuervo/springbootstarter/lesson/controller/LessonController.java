package com.tatocuervo.springbootstarter.lesson.controller;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Lesson;
import com.tatocuervo.springbootstarter.lesson.dto.CreateLessonRequest;
import com.tatocuervo.springbootstarter.lesson.dto.PatchLessonRequest;
import com.tatocuervo.springbootstarter.lesson.service.LessonService;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Lessons")
@RestController
@RequestMapping(path = Routes.LESSONS)
public class LessonController {

    @Autowired
    private LessonService service;

    @Autowired
    private ConversionService conversionService;

    @ApiOperation(value = "Get all lessons within a course")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lesson> getLessons(@PathVariable long courseId) {
        return service.getLessonsByCourse(courseId);
    }

    @ApiOperation("Get lesson within a course and topic")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lesson getLessonById(@PathVariable long courseId, @PathVariable long id) throws ResourceNotFoundException {
        return service.getLessonById(courseId, id);
    }

    @ApiOperation("Creates new lesson within a course and topic")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addLesson(@PathVariable long courseId, @RequestBody CreateLessonRequest createLessonRequest) throws ResourceNotFoundException {
        service.addLesson(courseId, conversionService.convert(createLessonRequest, Lesson.class));
    }

    @ApiOperation("Updates a lesson within a course and topic")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLesson(@PathVariable long courseId, @PathVariable long id, @RequestBody PatchLessonRequest patchLessonRequest) throws ResourceNotFoundException {
        service.updateLesson(courseId, id, conversionService.convert(patchLessonRequest, Lesson.class));
    }

    @ApiOperation("Deletes a lesson within a course and topic")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteLesson(@PathVariable long id) throws ResourceNotFoundException {
        service.deleteLesson(id);
    }
}
