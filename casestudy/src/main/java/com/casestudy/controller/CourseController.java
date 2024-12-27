package com.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LessonDTO;
import com.casestudy.entity.Course;
import com.casestudy.services.CourseService;
import com.casestudy.services.LessonService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @Autowired
    private LessonService lessonservice;

    @GetMapping("/")
    public ResponseEntity<?> getAllCourse() {
	List<CourseDTO> courses = service.getCourses();
	if (courses.isEmpty()) {
	    return new ResponseEntity<>("course not found", HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO course) {
	CourseDTO course2 = service.createCourse(course);
	if (course2 == null) {
	    return new ResponseEntity<>("course not created", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(course2, HttpStatus.OK);

    }

    @PutMapping("/{courseld}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseld, @RequestBody CourseDTO course) {
	CourseDTO course2 = service.editCourse(courseld, course);
	if (course2 == null) {
	    return new ResponseEntity<>("course not updated", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(course2, HttpStatus.OK);

    }

    @DeleteMapping("/{courseld}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseld) {
	boolean b = service.deleteCourse(courseld);
	if (b == false) {
	    return new ResponseEntity<>("course not deleted", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>("course deleted successfully", HttpStatus.OK);

    }

    @GetMapping("/{courseld}")
    public ResponseEntity<?> getOneCourse(@PathVariable Long courseld) {
	CourseDTO course2 = service.getCoursebyId(courseld);
	if (course2 == null) {
	    return new ResponseEntity<>("course not found", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(course2, HttpStatus.OK);

    }

    @GetMapping("/{courseid}/lesson")
    public ResponseEntity<?> getAllLessons(@PathVariable long courseid) {
	List<LessonDTO> all = lessonservice.getAll(courseid);
	if (all.isEmpty()) {
	    return new ResponseEntity<>("lesson not found", HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<>(all, HttpStatus.OK);
    }
    
    @PostMapping("/{courseid}/lesson")
    public ResponseEntity<?> createLesson(@PathVariable long courseid ,@RequestBody LessonDTO lessondto) {
	LessonDTO lesson = lessonservice.createLesson(courseid, lessondto);
	
	if (lesson == null) {
	    return new ResponseEntity<>("lesson not created", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(lesson, HttpStatus.OK);

    }
    
    @PutMapping("/{lessonid}/lesson")
    public ResponseEntity<?> updateLesson(@PathVariable Long lessonid, @RequestBody LessonDTO lessondto) {
	LessonDTO updateLesson = lessonservice.updateLesson(lessonid, lessondto);
	if (updateLesson == null) {
	    return new ResponseEntity<>("lesson not updated", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(updateLesson, HttpStatus.OK);

    }

    @DeleteMapping("/{lessonid}/lesson")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonid) {
	boolean deleteLesson = lessonservice.deleteLesson(lessonid);
	if (deleteLesson == false) {
	    return new ResponseEntity<>("lesson not deleted", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>("lesson deleted successfully", HttpStatus.OK);

    }

}
