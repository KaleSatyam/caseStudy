package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.casestudy.entity.Course;

import lombok.Builder;

@Builder
public record LessonDTO(  Long id,
	    String name,
	    String content,
	    CourseDTO courseId,
	    List<AssessmentDTO> assessment,
	    List<LearnerDTO> learner,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
