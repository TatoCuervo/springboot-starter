package com.tatocuervo.springbootstarter.topic.service;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.topic.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository repository;

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }

    public Topic getTopicByName(String name) throws ResourceNotFoundException {
        return repository.findByName(name);
    }

    public void addTopic(Topic topic) {
        repository.save(topic);
    }

    public void updateTopic(Topic updatedTopic) {

    }

    @Transactional
    public void deleteTopicByName(String name) {
        repository.deleteByName(name);
    }
}
