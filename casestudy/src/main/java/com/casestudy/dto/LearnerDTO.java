package com.casestudy.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.casestudy.entity.Assessment;

import lombok.Builder;

@Builder
public record LearnerDTO( Long id,
	    String name,
	    String email,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt) {

}
