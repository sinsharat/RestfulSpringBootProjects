package com.sharat.springbootstarter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharat.springbootstarter.info.Topic;
import com.sharat.springbootstarter.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics() {
		List<Topic> topicList = new ArrayList<>();
		Iterable<Topic> topics = topicRepository.findAll();
		if (null != topics) {
			topics.iterator().forEachRemaining(topicList :: add);
		}
		return topicList;
	}
	
	public Topic getTopic(String id) {
		 return topicRepository.findById(id).orElse(null);
	}
	
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
	}
	
	public void modifyTopic(Topic topic) {
		topicRepository.save(topic);
	}
	
	public void deleteTopic(String id) {
		topicRepository.deleteById(id);
	}
	
}
