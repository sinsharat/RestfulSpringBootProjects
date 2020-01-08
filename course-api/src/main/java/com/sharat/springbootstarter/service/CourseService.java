package com.sharat.springbootstarter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharat.springbootstarter.info.Course;
import com.sharat.springbootstarter.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository corseRepository;
	
	public List<Course> getAllCourses(String topicId) {
		List<Course> courseList = new ArrayList<>();
		Iterable<Course> courses = corseRepository.findByTopicId(topicId);
		if (null != courses) {
			courses.iterator().forEachRemaining(courseList :: add);
		}
		return courseList;
	}
	
	public Course getCourse(String id) {
		 return corseRepository.findById(id).orElse(null);
	}
	
	public void addCourse(Course course) {
		corseRepository.save(course);
	}
	
	public void modifyCourse(Course course) {
		corseRepository.save(course);
	}
	
	public void deleteCourse(String id) {
		corseRepository.deleteById(id);
	}
	
}
