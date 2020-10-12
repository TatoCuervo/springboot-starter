package com.tatocuervo.springbootstarter.topic.service;

import com.tatocuervo.springbootstarter.topic.domain.Topic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    private List<Topic> topics = new ArrayList<>(List.of(Topic.builder()
            .id("1")
            .name("First Topic")
            .description("Description")
            .build(), Topic.builder().id("2").name("Second Topic").description("Description")
            .build(), Topic.builder().id("3").name("Third Topic").description("Description")
            .build(), Topic.builder().id("4").name("Fourth Topic").description("Description")
            .build(), Topic.builder().id("5").name("Fifth Topic").description("Description")
            .build()));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopicById(String Id) {
        //TODO: validate topic does not exists
        return topics.stream()
                .filter(topic -> topic.getId().equals(Id))
                .findFirst().orElse(null);
    }

    public void addTopic(Topic topic) {
        //TODO: validate provided topic ID does not already exists
        topics.add(topic);
    }

    public void updateTopic(Topic updatedTopic) {
        //TODO: validate if not found
        for (int i = 0; i < topics.size(); i++) {
            Topic temp = topics.get(i);
            if (temp.getId().equalsIgnoreCase(updatedTopic.getId())) {
                topics.set(i, updatedTopic);
                return;
            }
        }
    }

    public void deleteTopicById(String id) {
        //TODO: validate if not found
        topics.removeIf(topic -> topic.getId().equalsIgnoreCase(id));
    }
}
