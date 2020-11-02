package com.tatocuervo.springbootstarter.lesson.controller;

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
    public List<Lesson> getLessons(@PathVariable long topicId, @PathVariable long courseId) {
        return service.getLessonsByTopicAndCourse(topicId, courseId);
    }

    @ApiOperation("Get lesson within a course and topic")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lesson getLessonById(@PathVariable long topicId, @PathVariable long courseId, @PathVariable long id) {
        return service.getLessonById(topicId, courseId, id);
    }

    @ApiOperation("Creates new lesson within a course and topic")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addLesson(@PathVariable long topicId, @PathVariable long courseId, @RequestBody CreateLessonRequest createLessonRequest) {
        service.addLesson(topicId, courseId, conversionService.convert(createLessonRequest, Lesson.class));
    }

    @ApiOperation("Updates a lesson within a course and topic")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLesson(@PathVariable long topicId, @PathVariable long courseId, @PathVariable long id, @RequestBody PatchLessonRequest patchLessonRequest) {
        service.updateLesson(topicId, courseId, id, conversionService.convert(patchLessonRequest, Lesson.class));
    }

    @ApiOperation("Deletes a lesson within a course and topic")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteLesson(@PathVariable long topicId, @PathVariable long courseId, @PathVariable long id) {
        service.deleteLesson(topicId, courseId, id);
    }
}
