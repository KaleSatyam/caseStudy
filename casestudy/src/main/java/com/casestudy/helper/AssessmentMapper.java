package com.casestudy.helper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.casestudy.dto.AssessmentDTO;
import com.casestudy.entity.Assessment;

@Mapper(componentModel = "spring")
public interface AssessmentMapper {
    
    Assessment dtoToAssesment(AssessmentDTO assesmentdto);
    
    AssessmentDTO assesmentToDto(Assessment assessment);
}
 

