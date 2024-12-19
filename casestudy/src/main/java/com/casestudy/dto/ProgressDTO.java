package com.casestudy.dto;

import java.time.LocalDateTime;

public record ProgressDTO(  Long id,
	    Long courseId,
	    Long lessonId,
	    Double completionPercentage,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
