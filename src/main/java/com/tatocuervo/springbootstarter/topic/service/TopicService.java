package com.tatocuervo.springbootstarter.topic.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class TopicService {
    @Autowired
    private TopicRepository repository;

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }

    public Topic getTopicById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with Id %d does not exists", id)));
    }

    public void addTopic(Topic topic) {
        repository.save(topic);
    }

    public void updateTopic(Topic updatedTopic, long id) {
        Topic topic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with Id %d does not exists", id)));

        topic.setName(updatedTopic.getName());
        topic.setDescription(updatedTopic.getDescription());
        repository.save(topic);
    }

    public void deleteTopicById(long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic with Id %d does not exists", id)));
        repository.deleteById(id);
    }
}
