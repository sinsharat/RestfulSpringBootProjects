package com.sharat.springbootstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharat.springbootstarter.info.Topic;
import com.sharat.springbootstarter.service.TopicService;

@RestController
public class TopicController {
	
	@Autowired
	TopicService topicService;

	@RequestMapping(method = RequestMethod.GET, path = {"/topics"})
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = {"/topics/{id}"})
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, path= {"/topics"})
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path= {"/topics"})
	public void modifyTopic (@RequestBody Topic topic) {
		topicService.modifyTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path= {"topics/{id}"})
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
}
