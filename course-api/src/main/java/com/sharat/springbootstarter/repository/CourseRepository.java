package com.sharat.springbootstarter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sharat.springbootstarter.info.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	
	// find all courses by course name
	public List<Course> findByName(String name);
	
	public List<Course> findByTopicId(String topicId);
	
}
