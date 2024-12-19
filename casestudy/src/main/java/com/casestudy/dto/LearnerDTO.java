package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Builder;

@Builder
public record LearnerDTO( Long id,
	    String name,
	    String email,
	    Set<CourseDTO> course,
	    List<ProgressDTO> progress,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
