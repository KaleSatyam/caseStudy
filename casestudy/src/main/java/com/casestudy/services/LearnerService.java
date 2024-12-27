package com.casestudy.services;

import java.util.List;

import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;

public interface LearnerService {

    List<LearnerDTO> getallLearner();
    
    LearnerDTO createLearner(LearnerDTO dto);
    
    LearnerDTO updateLearner(Long id,LearnerDTO dto);
    
    boolean deleteleaner(Long id);
    
    LearnerDTO getLearnerById(Long learnerId);
    
    //get all courses for learner
    
    List<CourseDTO> getAllCoursesForLearner(Long learnerId);
    
//    assign course to learner
    LearnerDTO assignCourseToLearner(Long courseId,Long lernerId);
    
//    unassign course from learner
    
    LearnerDTO unAssignCourseToLearner(Long courseId,Long lernerId);
    
    
    
}
