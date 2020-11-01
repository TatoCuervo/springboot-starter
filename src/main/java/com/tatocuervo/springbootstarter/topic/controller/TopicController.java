package com.tatocuervo.springbootstarter.topic.controller;

import com.tatocuervo.springbootstarter.common.model.Topic;
import com.tatocuervo.springbootstarter.routes.Routes;
import com.tatocuervo.springbootstarter.topic.dto.CreateTopicRequest;
import com.tatocuervo.springbootstarter.topic.dto.PatchTopicRequest;
import com.tatocuervo.springbootstarter.topic.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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

    @Autowired
    private ConversionService conversionService;

    @ApiOperation(value = "Get all topics")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> getTopics() {
        return topicService.getAllTopics();
    }


    @ApiOperation(value = "Get topic by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Topic getTopic(@PathVariable long id) {
        return topicService.getTopicById(id);
    }


    @ApiOperation(value = "Add new topic")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTopic(@RequestBody CreateTopicRequest createTopicRequest) {
        topicService.addTopic(conversionService.convert(createTopicRequest, Topic.class));
    }

    @ApiOperation(value = "Update topic")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{id}")
    public void updateTopic(@RequestBody PatchTopicRequest patchTopicRequest, @PathVariable long id) {
        topicService.updateTopic(conversionService.convert(patchTopicRequest, Topic.class), id);
    }

    @ApiOperation(value = "Delete topic by Id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void deleteTopic(@PathVariable long id) {
        topicService.deleteTopicById(id);
    }

}
