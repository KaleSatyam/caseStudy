package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Builder;

@Builder
public record InstructorDTO(
		 Long id,
		    String name,
		    String email,
		    String specialization,
		    List<AssessmentDTO> assessments,
		    LocalDateTime createdAt,
		    LocalDateTime updatedAt) {

}
