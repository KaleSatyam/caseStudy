package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AssessmentDTO( Long id,
	    String title,
	    List<String> questions,
	    Long maxScore,
	    Long lessonId,
	    Long instructorId,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {}
