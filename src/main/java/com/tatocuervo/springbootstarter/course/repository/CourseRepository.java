package com.tatocuervo.springbootstarter.course.repository;

import com.tatocuervo.springbootstarter.common.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTopicId(long topicId);

    Optional<Course> findByIdAndTopicId(long id, long topicId);
}
