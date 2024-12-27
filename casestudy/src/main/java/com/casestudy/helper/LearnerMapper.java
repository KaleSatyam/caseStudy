package com.casestudy.helper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.casestudy.dto.LearnerDTO;
import com.casestudy.entity.Learner;

@Mapper(componentModel = "spring" )
public interface LearnerMapper {

    
    
     Learner dtoToLearner(LearnerDTO dto);
     
     LearnerDTO learnertoDto(Learner learner);
}
