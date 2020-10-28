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

    public List<Lesson> getLessonsByCourse(long topicId, long courseId) throws ResourceNotFoundException {
        Optional<Course> optional = courseRepository.findByIdAndTopicId(courseId, topicId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d and topic id %d does not exists", courseId, topicId));

        return lessonRepository.findAllByCourseId(courseId);
    }

    public Lesson getLessonById(long topicId, long courseId, long id) throws ResourceNotFoundException {
        Optional<Course> optional = courseRepository.findByIdAndTopicId(courseId, topicId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d and topic id %d does not exists", courseId, topicId));

        Lesson lesson = lessonRepository.findByIdAndCourseId(id, courseId);
        if (lesson == null)
            throw new ResourceNotFoundException(format("Lesson with id %d and courseId %d does not exists", id, courseId));

        return lesson;
    }

    public void addLesson(long topicId, long courseId, Lesson lesson) throws ResourceNotFoundException {

        Optional<Course> optional = courseRepository.findByIdAndTopicId(courseId, topicId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d and topic id %d does not exists", courseId, topicId));

        Optional<Course> parentCourse = courseRepository.findById(courseId);
        if (parentCourse.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d does not exists", courseId));

        lesson.setCourse(parentCourse.get());
        lessonRepository.save(lesson);
    }

    public void updateLesson(long topicId, long courseId, long id, Lesson lesson) throws ResourceNotFoundException {

        Optional<Course> optional = courseRepository.findByIdAndTopicId(courseId, topicId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d and topic id %d does not exists", courseId, topicId));

        Lesson existingLesson = lessonRepository.findByIdAndCourseId(id, courseId);
        if (existingLesson == null)
            throw new ResourceNotFoundException(format("Lesson with id %d and courseId %d does not exists", id, courseId));

        existingLesson.setDescription(lesson.getDescription());
        existingLesson.setName(lesson.getName());

        lessonRepository.save(existingLesson);
    }

    public void deleteLesson(long topicId, long courseId, long id) throws ResourceNotFoundException {
        Optional<Course> optional = courseRepository.findByIdAndTopicId(courseId, topicId);
        if (optional.isEmpty())
            throw new ResourceNotFoundException(format("Course with id %d and topic id %d does not exists", courseId, topicId));

        Lesson lesson = lessonRepository.findByIdAndCourseId(id, courseId);
        if (lesson == null)
            throw new ResourceNotFoundException(format("Lesson with id %d and courseId %d does not exists", id, courseId));

        lessonRepository.delete(lesson);
    }
}
