package com.tatocuervo.springbootstarter.topic.converters;

import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.dto.PatchTopicRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TopicFromPartialUpdateTopicRequest implements Converter<PatchTopicRequest, Topic> {

    @Override
    public Topic convert(PatchTopicRequest patchTopicRequest) {
        return Topic.builder()
                .name(patchTopicRequest.getName())
                .description(patchTopicRequest.getDescription())
                .build();
    }
}
