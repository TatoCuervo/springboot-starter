package com.tatocuervo.springbootstarter.lesson.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Lesson;
import com.tatocuervo.springbootstarter.lesson.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getLessonsByCourse(long courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }

    public Lesson getLessonById(long courseId, long id) throws ResourceNotFoundException {
        Lesson lesson = lessonRepository.findByIdAndCourseId(id, courseId);
        if (lesson == null)
            throw new ResourceNotFoundException(format("Lesson with id %d and courseId %d does not exists", id, courseId));

        return lesson;
    }

    public void addLesson(long courseId, Lesson lesson) {
        //TODO
    }
}
