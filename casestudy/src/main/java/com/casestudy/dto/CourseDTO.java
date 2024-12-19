package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public record CourseDTO( Long id,
	    String name,
	    String description,
	    Set<LessonDTO> lessons,
	    Set<LearnerDTO> learners,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
