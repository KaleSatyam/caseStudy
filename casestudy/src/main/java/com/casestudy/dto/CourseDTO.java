package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
public record CourseDTO( Long id,
	    String name,
	    String description,
	    List<LessonDTO> lessons,
	    List<LearnerDTO> learner,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
