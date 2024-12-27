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
import com.casestudy.dto.LearnerDTO;
import com.casestudy.services.LearnerService;

@RestController
@RequestMapping("/learner")
public class LearnerController {
    
    @Autowired
    private LearnerService service;
    
    @GetMapping("/")
    public ResponseEntity<?> getAllLearner() {
	List<LearnerDTO> Learners = service.getallLearner();
	if (Learners.isEmpty()) {
	    return new ResponseEntity<>("Learner not found", HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<>(Learners, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createLearner(@RequestBody LearnerDTO learner) {
	LearnerDTO learner2 = service.createLearner(learner);
	if (learner2 == null) {
	    return new ResponseEntity<>("Learner not created", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(learner2, HttpStatus.OK);

    }

    @PutMapping("/{learnerId}")
    public ResponseEntity<?> updateLearner(@PathVariable Long learnerId, @RequestBody LearnerDTO learner) {
	LearnerDTO learner2 = service.updateLearner(learnerId, learner);
	if (learner2 == null) {
	    return new ResponseEntity<>("Learner not updated", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(learner2, HttpStatus.OK);

    }

    @DeleteMapping("/{learnerId}")
    public ResponseEntity<?> deleteLearner(@PathVariable Long learnerId) {
	boolean b = service.deleteleaner(learnerId);
	if (b == false) {
	    return new ResponseEntity<>("Learner not deleted", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>("Learner deleted successfully", HttpStatus.OK);

    }
    
    
    @GetMapping("/{learnerId}/courses")
    public ResponseEntity<?> getAllCoursesForLearner(@PathVariable Long learnerId) {
	
	List<CourseDTO> allCoursesForLearner = service.getAllCoursesForLearner(learnerId);
	if (allCoursesForLearner.isEmpty()) {
	    return new ResponseEntity<>("course not found for this learner", HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity<>(allCoursesForLearner, HttpStatus.OK);
	
    }
    
    @PutMapping("/{learnerId}/ascourses/{courseId}")
    public ResponseEntity<?> assignCoursestoLearner(@PathVariable Long learnerId, @PathVariable Long courseId) {
	 LearnerDTO assignCourseToLearner = service.assignCourseToLearner(learnerId, courseId);
	if (assignCourseToLearner == null) {
	    return new ResponseEntity<>("Learner not updated", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(assignCourseToLearner, HttpStatus.OK);

    }
    
    @PutMapping("/{learnerId}/uncourses/{courseId}")
    public ResponseEntity<?> unAssignCoursestoLearner(@PathVariable Long learnerId, @PathVariable Long courseId) {
	 LearnerDTO assignCourseToLearner = service.unAssignCourseToLearner(learnerId, courseId);
	if (assignCourseToLearner == null) {
	    return new ResponseEntity<>("Learner not updated", HttpStatus.BAD_REQUEST);
	}

	return new ResponseEntity<>(assignCourseToLearner, HttpStatus.OK);

    }


}
