package com.tatocuervo.springbootstarter.topic.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.dto.CreateTopicRequest;
import com.tatocuervo.springbootstarter.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class TopicService {
    @Autowired
    private TopicRepository repository;

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }

    public Topic getTopicById(long id) throws ResourceNotFoundException {
        Optional<Topic> topic = repository.findById(id);
        if (topic.isEmpty())
            throw new ResourceNotFoundException(format("Topic with Id %d does not exists", id));

        return topic.get();
    }

    public void addTopic(Topic topic) {
        repository.save(topic);
    }

    public void updateTopic(Topic updatedTopic, long id) throws ResourceNotFoundException {
        Optional<Topic> topic = repository.findById(id);
        if (topic.isEmpty())
            throw new ResourceNotFoundException(format("Topic with Id %d does not exists", id));

        Topic existingTopic = topic.get();
        existingTopic.setName(updatedTopic.getName());
        existingTopic.setDescription(updatedTopic.getDescription());
        repository.save(existingTopic);
    }

    public void deleteTopicById(long id) throws ResourceNotFoundException {
        Optional<Topic> topic = repository.findById(id);
        if (topic.isEmpty())
            throw new ResourceNotFoundException(format("Topic with Id %d does not exists", id));
        repository.deleteById(id);
    }
}
