package com.tatocuervo.springbootstarter.lesson.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.common.model.Lesson;
import com.tatocuervo.springbootstarter.course.repository.CourseRepository;
import com.tatocuervo.springbootstarter.lesson.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Lesson> getLessonsByCourse(long courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }

    public Lesson getLessonById(long courseId, long id) throws ResourceNotFoundException {
        Lesson lesson = lessonRepository.findByIdAndCourseId(id, courseId);
        if (lesson == null)
            throw new ResourceNotFoundException(format("Lesson with id %d and courseId %d does not exists", id, courseId));

        return lesson;
    }

    public void addLesson(long courseId, Lesson lesson) throws ResourceNotFoundException {
        Optional<Course> optional = courseRepository.findById(courseId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d does not exists", courseId));

        lesson.setCourse(optional.get());
        lessonRepository.save(lesson);
    }

    public void updateLesson(long courseId, long id, Lesson lesson) throws ResourceNotFoundException {
        Lesson existingLesson = lessonRepository.findByIdAndCourseId(id, courseId);
        if (existingLesson == null)
            throw new ResourceNotFoundException(format("Lesson with id %d and courseId %d does not exists", id, courseId));

        existingLesson.setDescription(lesson.getDescription());
        existingLesson.setName(lesson.getName());

        lessonRepository.save(existingLesson);
    }

    public void deleteLesson(long id) throws ResourceNotFoundException {
        Optional<Lesson> optional = lessonRepository.findById(id);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Lesson with id %d and does not exists", id));

        lessonRepository.delete(optional.get());
    }
}
