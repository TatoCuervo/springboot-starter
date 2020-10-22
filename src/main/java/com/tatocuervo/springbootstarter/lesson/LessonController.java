package com.tatocuervo.springbootstarter.lesson;

import com.tatocuervo.springbootstarter.common.model.Lesson;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "Lessons")
@RestController
@RequestMapping(path = Routes.LESSONS)
public class LessonController {

    @ApiOperation(value = "Get all lessons within a course")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lesson> getLessons(@PathVariable long topicId, @PathVariable long courseId) {
        return new ArrayList<>();
    }

    @ApiOperation("Get lesson within a course and topic")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Lesson getLessonById(@PathVariable long topicId, @PathVariable long courseId, @PathVariable long id) {
        return null;
    }

    @ApiOperation("Creates new lesson within a course and topic")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addLesson(@PathVariable long topicId, @PathVariable long courseId, @RequestBody Lesson lesson) {

    }

    @ApiOperation("Updates a lesson within a course and topic")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLesson(@PathVariable long topicId, @PathVariable long courseId, @PathVariable long id, @RequestBody Lesson lesson) {

    }

    @ApiOperation("Deletes a lesson within a course and topic")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteLesson(@PathVariable long topicId, @PathVariable long courseId, @PathVariable long id) {

    }
}
