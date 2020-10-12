package com.tatocuervo.springbootstarter.topic.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    //TODO: setup PostgreSQL to perform CRUD operations instead

    public List<Topic> getAllTopics() {
        return null;
    }

    public Topic getTopicById(String Id) throws ResourceNotFoundException {
        return null;
    }

    public void addTopic(Topic topic) {

    }

    public void updateTopic(Topic updatedTopic) {

    }

    public void deleteTopicById(String id) {
        //topics.removeIf(topic -> topic.getId().equalsIgnoreCase(id));
    }
}
