package com.casestudy.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.Exceptions.CourseNotFoundException;
import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;
import com.casestudy.entity.Course;
import com.casestudy.entity.Learner;
import com.casestudy.helper.CourseMapper;
import com.casestudy.helper.LearnerMapper;
import com.casestudy.repositories.CourseReository;
import com.casestudy.repositories.LearnerRepository;
import com.casestudy.services.LearnerService;

@Service
public class LearnerserviceImpl implements LearnerService {
    
    @Autowired
    private LearnerRepository learnerRepo;
    
    @Autowired
    private CourseReository courseRpo;
    
    @Autowired
    private LearnerMapper learnermapper;
    
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<LearnerDTO> getallLearner() {
	// TODO Auto-generated method stub
	List<LearnerDTO> collect = learnerRepo.findAll().stream().map( learnermapper :: learnertoDto)
	.collect(Collectors.toList());
	return collect;
    }

    @Override
    public LearnerDTO createLearner(LearnerDTO dto) {
	// TODO Auto-generated method stub
	Learner learner = learnermapper.dtoToLearner(dto);
	Learner save = learnerRepo.save(learner) ;
	 LearnerDTO learnertoDto = learnermapper.learnertoDto(save);
	return learnertoDto;
    }

    @Override
    public LearnerDTO updateLearner(Long id, LearnerDTO dto) {
	Learner learner = learnerRepo.findById(id).orElseThrow( ()-> new RuntimeException("Learner Id not found"));
	if(dto.name() != null) {
	    learner.setName(dto.name());
	}
	if(dto.email() != null) {
	    learner.setEmail(dto.email());
	}
	
	Learner save = learnerRepo.save(learner);
	 LearnerDTO learnertoDto = learnermapper.learnertoDto(save);
	return learnertoDto;
    }

    @Override
    public boolean deleteleaner(Long id) {
	Learner orElseThrow = learnerRepo.findById(id)
	.orElseThrow(() -> new RuntimeException("Learner not found for id"));
	if (orElseThrow == null) {
	    return false;
	}

	learnerRepo.delete(orElseThrow);
	return true;

    }

    @Override
    public List<CourseDTO> getAllCoursesForLearner(Long learnerId) {
	// TODO Auto-generated method stub
	Learner learner = learnerRepo.findById(learnerId)
		.orElseThrow(() -> new RuntimeException("Learner not found for id"));
	List<Course> course = learner.getCourse();
	
	
	
	List<CourseDTO> courseDTOs = course.stream()
		    .map(CourseMapper::courseToCourseDTO)
		    .peek(courseDTO -> {
		        // Filter learners inside each courseDTO
		        List<LearnerDTO> filteredLearners = courseDTO.learner().stream()
		            .filter(l -> l.id().equals(learner.getId()))
		            .collect(Collectors.toList());
		        
		        // Replace learners with filtered list (Optional - if modifying DTO is allowed)
		        courseDTO.learner().clear();
		        courseDTO.learner().addAll(filteredLearners);
		    })
		    .collect(Collectors.toList());
	
	return courseDTOs;
    }

    @Override
   public LearnerDTO assignCourseToLearner(Long lernerId,Long  courseId){ 
       Course course = courseRpo.findById(courseId)
		.orElseThrow(() -> new CourseNotFoundException("course for id " + courseId + " not found"));
       Learner learner = learnerRepo.findById(lernerId)
		.orElseThrow(() -> new RuntimeException("Learner not found for id"));
       
       boolean anyMatch = learner.getCourse().stream().anyMatch(c -> c.getId().equals(course.getId() ));
        
       if(anyMatch)
       {
	   throw new RuntimeException("Course is already assigned to Learner");
       }
       learner.getCourse().add(course);
       Learner save = learnerRepo.save(learner);
       LearnerDTO learnertoDto = learnermapper.learnertoDto(save);
      	return learnertoDto;
    }
    
    @Override
    public LearnerDTO unAssignCourseToLearner(Long lernerId,Long  courseId) {
	 Course course = courseRpo.findById(courseId)
			.orElseThrow(() -> new CourseNotFoundException("course for id " + courseId + " not found"));
	       Learner learner = learnerRepo.findById(lernerId)
			.orElseThrow(() -> new RuntimeException("Learner not found for id"));
	       
	       boolean anyMatch = learner.getCourse().stream().anyMatch(c -> c.getId().equals(course.getId() ));
	       
	        
	       if(anyMatch)
	       {
		   List<Course> collect = learner.getCourse().stream()
		   .filter(c -> !c.getId().equals(course.getId()))
		   .collect(Collectors.toList());
		   
		   
		   learner.setCourse(collect);
		   
	       }else {
		   throw new RuntimeException("Learner havnt enrolled in this course yet");
	       }
	       Learner save = learnerRepo.save(learner);
	       LearnerDTO learnertoDto = learnermapper.learnertoDto(save);
	      	return learnertoDto;
    }

    @Override
    public LearnerDTO getLearnerById(Long learnerId) {
	 Learner learner = learnerRepo.findById(learnerId)
			.orElseThrow(() -> new RuntimeException("Learner not found for id"));
	 
	 LearnerDTO learnertoDto = learnermapper.learnertoDto(learner);
	 
	 return learnertoDto;
    }
   
}
