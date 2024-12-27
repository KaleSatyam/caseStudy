package com.casestudy.helper;

import org.mapstruct.Mapper;


import com.casestudy.dto.LessonDTO;
import com.casestudy.entity.Lesson;

@Mapper(componentModel = "spring" )
public interface LessonMapper {
    
    Lesson dtoToLesson(LessonDTO lessondto);
    
    LessonDTO lessonToDto(Lesson lesson);
}
