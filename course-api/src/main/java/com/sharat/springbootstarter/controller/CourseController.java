package com.sharat.springbootstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharat.springbootstarter.info.Course;
import com.sharat.springbootstarter.info.Topic;
import com.sharat.springbootstarter.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;

	@RequestMapping(method = RequestMethod.GET, path = {"/topics/{id}/courses"})
	public List<Course> getAllTopics(@PathVariable String id) {
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = {"/topics/{topicId}/courses/{courseId}"})
	public Course getCourse(@PathVariable String topicId, @PathVariable String courseId) {
		return courseService.getCourse(courseId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path= {"/topics/{topicId}/courses"})
	public void addTopic(@RequestBody Course course, @PathVariable String topicId) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.addCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path= {"/topics/{topicId}/courses"})
	public void modifyTopic (@RequestBody Course course, @PathVariable String topicId) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.modifyCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path= {"/topics/{topicId}/courses/{courseId}"})
	public void deleteTopic(@PathVariable String courseId) {
		courseService.deleteCourse(courseId);
	}
}
