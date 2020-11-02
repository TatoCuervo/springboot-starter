package com.tatocuervo.springbootstarter.topic.converters;

import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.dto.CreateTopicRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TopicFromCreateTopicRequestConverterTest {

    @Test
    void shouldConvertTopicFromCreateTopicRequest() {
        final String topicName = "New Topic";
        final String topicDescription = "New Topic Description";
        final CreateTopicRequest createTopicRequest = CreateTopicRequest.builder()
                .name(topicName)
                .description(topicDescription)
                .build();

        // When
        Topic actual = new TopicFromCreateTopicRequestConverter().convert(createTopicRequest);

        // Then
        assertThat(actual.getName()).isEqualTo(topicName);
        assertThat(actual.getDescription()).isEqualTo(topicDescription);
    }
}