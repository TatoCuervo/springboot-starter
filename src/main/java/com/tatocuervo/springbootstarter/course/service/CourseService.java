package com.tatocuervo.springbootstarter.course.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.course.repository.CourseRepository;
import com.tatocuervo.springbootstarter.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<Course> getCoursesByTopic(long topicId) {
        topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with id %d does nto exist", topicId)));
        return courseRepository.findByTopicId(topicId);

    }

    public Course getCourseById(long topicId, long id) {
        return courseRepository.findByIdAndTopicId(id, topicId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course with id %d for topic id %d does nto exist", id, topicId)));

    }

    public void addCourse(Course course, long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with id %d does nto exist", topicId)));

        course.setTopic(topic);
        courseRepository.save(course);
    }

    public void updateCourse(long topicId, Course course, long id) {
        Course existingCourse = courseRepository.findByIdAndTopicId(id, topicId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course with id %d for topic id %d does nto exist", id, topicId)));

        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        courseRepository.save(existingCourse);
    }

    public void deleteCourseById(long topicId, long id) {
        courseRepository.findByIdAndTopicId(id, topicId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course with id %d for topic id %d does nto exist", id, topicId)));
        courseRepository.deleteById(id);
    }
}
