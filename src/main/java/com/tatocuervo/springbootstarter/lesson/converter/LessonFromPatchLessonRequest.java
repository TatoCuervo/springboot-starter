package com.tatocuervo.springbootstarter.lesson.converter;

import com.tatocuervo.springbootstarter.common.model.Lesson;
import com.tatocuervo.springbootstarter.lesson.dto.PatchLessonRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessonFromPatchLessonRequest implements Converter<PatchLessonRequest, Lesson> {
    @Override
    public Lesson convert(PatchLessonRequest patchLessonRequest) {
        return Lesson.builder()
                .name(patchLessonRequest.getName())
                .description(patchLessonRequest.getDescription())
                .build();
    }
}
