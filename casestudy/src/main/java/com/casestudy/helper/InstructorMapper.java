package com.casestudy.helper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.casestudy.dto.InstructorDTO;
import com.casestudy.entity.Instructor;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    
    Instructor dtoToInstructor(InstructorDTO assesmentdto);
    
    InstructorDTO instructorToDto(Instructor assessment);

}
