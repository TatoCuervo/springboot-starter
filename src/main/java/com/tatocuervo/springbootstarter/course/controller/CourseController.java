package com.tatocuervo.springbootstarter.course.controller;

import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.routes.Routes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(Routes.COURSES)
public class CourseController {

    @GetMapping
    public List<Course> getAllCourses(@PathVariable String topicId) {
        return null;
    }

    @GetMapping("/{Id}")
    public Course getCourse(@PathVariable String Id) {
        return null;
    }

    @PostMapping
    public void addCourse(@PathVariable String topicId, @RequestBody Course course) {

    }

    @PutMapping
    public void updateCourse(@RequestBody Course course) {

    }

    @DeleteMapping("/{Id}")
    public void deleteCourse(@PathVariable String Id) {

    }
}
