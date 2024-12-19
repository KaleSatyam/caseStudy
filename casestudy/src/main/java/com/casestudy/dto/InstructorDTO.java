package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record InstructorDTO(
		 Long id,
		    String name,
		    String email,
		    String specialization,
		    Set<AssessmentDTO> assessments,
		    LocalDateTime createdAt,
		    LocalDateTime updatedAt) {

}
