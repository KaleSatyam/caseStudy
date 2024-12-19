package com.casestudy.services;

import java.util.List;

import com.casestudy.dto.CourseDTO;
import com.casestudy.entity.Course;

public interface CourseService {

	List<Course> getCourses();
	Course createCourse(Course course);
//	Course	editCourse(long courseId,Course course);
//    boolean deleteCourse(long courseId);
	
}
