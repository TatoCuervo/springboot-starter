package com.tatocuervo.springbootstarter.topic.converters;

import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.dto.CreateTopicRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TopicFromCreateTopicRequestConverter implements Converter<CreateTopicRequest, Topic> {

    @Override
    public Topic convert(CreateTopicRequest createTopicRequest) {
        return Topic.builder()
                .name(createTopicRequest.getName())
                .description(createTopicRequest.getDescription())
                .build();
    }
}
