package com.tatocuervo.springbootstarter.lesson.repository;

import com.tatocuervo.springbootstarter.common.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByCourseId(long courseId);

    Lesson findByIdAndCourseId(long id, long courseId);
}
