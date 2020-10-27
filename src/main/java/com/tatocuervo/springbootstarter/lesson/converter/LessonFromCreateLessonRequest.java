package com.tatocuervo.springbootstarter.lesson.converter;

import com.tatocuervo.springbootstarter.common.model.Lesson;
import com.tatocuervo.springbootstarter.lesson.dto.CreateLessonRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessonFromCreateLessonRequest implements Converter<CreateLessonRequest, Lesson> {
    @Override
    public Lesson convert(CreateLessonRequest createLessonRequest) {
        return Lesson.builder()
                .name(createLessonRequest.getName())
                .description(createLessonRequest.getDescription())
                .build();
    }
}
