package com.tatocuervo.springbootstarter.topic.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class TopicServiceTest {
    @Mock
    private TopicRepository repository;

    @InjectMocks
    private TopicService tested;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnTopicIfExists() {
        final long topicId = 123123;
        final String name = "random name";
        final String description = "some-description";

        final Optional<Topic> expected = Optional.ofNullable(Topic.builder()
                .id(topicId)
                .name(name)
                .description(description)
                .build());

        // Given
        given(repository.findById(topicId)).willReturn(expected);

        // When
        Topic actual = tested.getTopicById(topicId);

        // Then
        assertThat(expected.isPresent());
        assertThat(actual).isEqualTo(expected.get());
        assertThat(actual.getId()).isEqualTo(topicId);
        assertThat(actual.getName()).isEqualTo(expected.get().getName());
        assertThat(actual.getDescription()).isEqualTo(expected.get().getDescription());
    }

    @Test
    void shouldThrowExceptionIfTopicDoesNotExists() {
        final long topicId = 123123;

        // Given
        given(repository.findById(topicId)).willReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> tested.getTopicById(topicId)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void updateShouldThrowExceptionIfNotPresent() {
        final long topicId = 123123;

        // Given
        given(repository.findById(topicId)).willReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> tested.updateTopic(any(Topic.class), topicId));
    }

    @Test
    void shouldUpdateExistingTopicIfPresent() {
        final long existingTopicId = 123123;
        final String existingTopicName = "Existing Topic Name";
        final String existingTopicDescription = "Existing Topic Description";
        final Optional<Topic> existingTopic = Optional.ofNullable(Topic.builder()
                .id(existingTopicId)
                .name(existingTopicName)
                .description(existingTopicDescription)
                .build());

        final String newTopicName = "New Topic Name";
        final String newTopicDescription = "New Topic Description";
        final Topic newTopic = Topic.builder()
                .id(existingTopicId)
                .name(newTopicName)
                .description(newTopicDescription)
                .build();

        final ArgumentCaptor<Topic> captor = ArgumentCaptor.forClass(Topic.class);


        // Given
        given(repository.findById(existingTopicId)).willReturn(existingTopic);

        // When
        tested.updateTopic(newTopic, existingTopicId);

        // Then
        verify(repository).save(captor.capture());

        final Topic updatedTopic = captor.getValue();
        assertThat(updatedTopic.getId()).isEqualTo(newTopic.getId());
        assertThat(updatedTopic.getName()).isEqualTo(newTopic.getName());
        assertThat(updatedTopic.getDescription()).isEqualTo(newTopic.getDescription());
    }
}