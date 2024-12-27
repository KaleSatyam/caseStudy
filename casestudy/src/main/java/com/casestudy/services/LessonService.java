package com.casestudy.services;

import java.util.List;

import com.casestudy.dto.LessonDTO;


public interface LessonService {

    List<LessonDTO> getAll(long courseid);
    LessonDTO createLesson(long courseid,LessonDTO lessondto);
    LessonDTO updateLesson(long id, LessonDTO lessondto);
    boolean deleteLesson(long id);
    
}
