package com.tatocuervo.springbootstarter.topic.controller;

import com.tatocuervo.springbootstarter.common.exception.ResourceNotFoundException;
import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.routes.Routes;
import com.tatocuervo.springbootstarter.topic.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Topics")
@RestController
@RequestMapping(path = Routes.TOPICS)
public class TopicController {

    @Autowired
    private TopicService topicService;

    @ApiOperation(value = "Get all topics")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> getTopics() {
        return topicService.getAllTopics();
    }


    @ApiOperation(value = "Get topic by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Topic getTopic(@PathVariable long id) throws ResourceNotFoundException {
        return topicService.getTopicById(id);
    }


    @ApiOperation(value = "Add new topic")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @ApiOperation(value = "Update topic")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateTopic(@RequestBody Topic topic) {
        topicService.updateTopic(topic);
    }

    //TODO: add PATCH as well to partially update


    @ApiOperation(value = "Delete topic by Id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteTopic(@PathVariable long id) {
        topicService.deleteTopicById(id);
    }

}
