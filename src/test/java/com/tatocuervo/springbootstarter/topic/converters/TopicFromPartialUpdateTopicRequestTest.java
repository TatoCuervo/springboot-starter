package com.tatocuervo.springbootstarter.topic.converters;

import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.dto.PatchTopicRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TopicFromPartialUpdateTopicRequestTest {

    @Test
    void shouldConvertTopicFromPatchTopicRequest() {
        final String topicName = "Topic Name";
        final String topicDescription = "Topic Description";
        final PatchTopicRequest patchTopicRequest = PatchTopicRequest.builder()
                .name(topicName)
                .description(topicDescription)
                .build();

        // When
        Topic actual = new TopicFromPartialUpdateTopicRequest().convert(patchTopicRequest);

        // Then
        assertThat(actual.getName()).isEqualTo(topicName);
        assertThat(actual.getDescription()).isEqualTo(topicDescription);
    }

}