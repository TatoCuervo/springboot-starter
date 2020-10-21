package com.tatocuervo.springbootstarter.topic.repository;

import com.tatocuervo.springbootstarter.common.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    void deleteByName(String name);
}
