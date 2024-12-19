package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record LessonDTO(  Long id,
	    String name,
	    String content,
	    Long courseId,
	    List<AssessmentDTO> assessmentIds,
	    List<ProgressDTO> progress,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
