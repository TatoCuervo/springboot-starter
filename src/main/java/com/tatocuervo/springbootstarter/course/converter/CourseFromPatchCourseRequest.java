package com.tatocuervo.springbootstarter.course.converter;

import com.tatocuervo.springbootstarter.common.model.Course;
import com.tatocuervo.springbootstarter.course.dto.PatchCourseRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseFromPatchCourseRequest implements Converter<PatchCourseRequest, Course> {
    @Override
    public Course convert(PatchCourseRequest patchCourseRequest) {
        return Course.builder()
                .name(patchCourseRequest.getName())
                .description(patchCourseRequest.getDescription())
                .build();
    }
}
