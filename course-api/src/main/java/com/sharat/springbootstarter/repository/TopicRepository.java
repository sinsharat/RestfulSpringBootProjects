package com.sharat.springbootstarter.repository;

import org.springframework.data.repository.CrudRepository;

import com.sharat.springbootstarter.info.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {
	
}
