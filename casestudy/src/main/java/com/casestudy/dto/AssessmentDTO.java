package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;


@Builder
public record AssessmentDTO( 
		 Long id,
		 String tital,
		 List<String> questions,
		 Long maxScore,
		 LessonDTO lesson,
		 LearnerDTO learner,
	    InstructorDTO instructorId,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {}
