package com.casestudy.services;

import java.util.List;

import com.casestudy.dto.CourseDTO;
import com.casestudy.entity.Course;

public interface CourseService {

    List<CourseDTO> getCourses();

    CourseDTO createCourse(CourseDTO course);

    CourseDTO editCourse(long courseId, CourseDTO course);

    boolean deleteCourse(long courseId);
    
    CourseDTO getCoursebyId(long courseId);
}
