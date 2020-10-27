package com.tatocuervo.springbootstarter.course.converter;

import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.dto.CreateCourseRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseFromCreateCourseRequest implements Converter<CreateCourseRequest, Course> {

    @Override
    public Course convert(CreateCourseRequest createCourseRequest) {
        return Course.builder()
                .name(createCourseRequest.getName())
                .description(createCourseRequest.getDescription())
                .build();
    }
}
