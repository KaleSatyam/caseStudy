package com.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.dto.CourseDTO;
import com.casestudy.entity.Course;
import com.casestudy.services.CourseService;


@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@GetMapping("/getcourse")
	public ResponseEntity<?> getAllCourse(){
			List<Course> courses = service.getCourses();
			if(courses.isEmpty()) {
				return new ResponseEntity<>("course not found",HttpStatus.NOT_FOUND);
			}
		
		return new ResponseEntity<>(courses,HttpStatus.OK);
	}
	
	@PostMapping("/createcourse")
	public ResponseEntity<?> createCourse(@RequestBody Course course){
		Course savedCourse = service.createCourse(course);
		if(savedCourse == null) {
			return new ResponseEntity<>("course not created",HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(course,HttpStatus.OK);
		
	}
	

}
