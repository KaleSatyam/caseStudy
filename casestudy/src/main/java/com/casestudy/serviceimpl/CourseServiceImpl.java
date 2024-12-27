package com.casestudy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.Exceptions.CourseNotFoundException;
import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;
import com.casestudy.entity.Course;
import com.casestudy.entity.Learner;
import com.casestudy.entity.Lesson;
import com.casestudy.helper.CourseMapper;
import com.casestudy.repositories.CourseReository;
import com.casestudy.repositories.LearnerRepository;
import com.casestudy.repositories.Lessonrepository;
import com.casestudy.services.CourseService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseReository repo;
    
    @Autowired
    private LearnerRepository learnerRepo;
    
    @Autowired
    private CourseMapper mapper;


    @Override
    public List<CourseDTO> getCourses() {

	List<Course> all = repo.findAll();

	return all.stream().map(CourseMapper :: courseToCourseDTO).collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
	 Course course = CourseMapper.courseDTOToCourse(courseDTO);

	    // Handle lessons if provided
	    if (courseDTO.lessons() != null && !courseDTO.lessons().isEmpty()) {
	        List<Lesson> lessons = courseDTO.lessons().stream()
	            .map(lessonDTO -> Lesson.builder()
	                .name(lessonDTO.name())
	                .content(lessonDTO.content())
	                .course(course)  // Establish bi-directional relationship
	                .build())
	            .collect(Collectors.toList());
	        course.setLessons(lessons);
	    }

	    // Handle learners if provided
	    if (courseDTO.learner() != null && !courseDTO.learner().isEmpty()) {
		 List<Learner> learners = new ArrayList<>();
		 
		 for (LearnerDTO learnerDTO : courseDTO.learner()) {
		 Learner byEmail = learnerRepo.findByEmail(learnerDTO.email());
		 	if(byEmail == null) {
		 	   Learner newLearner = Learner.builder()
		                    .name(learnerDTO.name())
		                    .email(learnerDTO.email())
		                    .course(new ArrayList<>())  // Initialize to prevent NPE
		                    .build();
		 	   
		 	  newLearner.getCourse().add(course);
		 	 learners.add(newLearner);
		 	 continue;
		 	}
		 	
		 	 if (!byEmail.getCourse().contains(course)) {
		 	    byEmail.getCourse().add(course);
			        }
		 	 
		 	 
		 	learners.add(byEmail);
		 }
		    
		    course.setLearner(learners);
	    }

	    Course savedCourse = repo.save(course);
	    if (savedCourse == null || savedCourse.getId() == null) {
	        throw new RuntimeException("CourseNotCreated...");
	    }

	    return CourseMapper.courseToCourseDTO(savedCourse);

    }

    @Override
    public CourseDTO editCourse(long courseId, CourseDTO courseDTO) {
	// TODO Auto-generated method stub
	Course courseOld = repo.findById(courseId)
		.orElseThrow(() -> new CourseNotFoundException("Course not found for course id : " + courseId));

	// Update fields if not null
	if (courseDTO.name() != null) {
	    courseOld.setName(courseDTO.name());
	}
	if (courseDTO.description() != null) {
	    courseOld.setDescription(courseDTO.description());
	}
	if (courseDTO.learner() != null && !courseDTO.learner().isEmpty()) {
	    List<Learner> updatedLearners = courseDTO.learner().stream()
		    .map(dto -> Learner.builder().id(dto.id() != null ? dto.id() : null) // Update existing learner if
											 // ID is present
			    .name(dto.name()).email(dto.email()).build())
		    .collect(Collectors.toList());
	    courseOld.setLearner(updatedLearners);
	}
	if (courseDTO.lessons() != null && !courseDTO.lessons().isEmpty()) {
	    List<Lesson> updatedLessons = courseDTO.lessons().stream()
		    .map(dto -> Lesson.builder().id(dto.id() != null ? dto.id() : null) // Update existing lesson if ID
											// is present
			    .name(dto.name()).content(dto.content()).build())
		    .collect(Collectors.toList());
	    courseOld.setLessons(updatedLessons);
	}

	Course updatedCourse = repo.save(courseOld);

	return CourseMapper.courseToCourseDTO(updatedCourse);
    }

    @Override
    public boolean deleteCourse(long courseId) {
	// TODO Auto-generated method stub

	Course course = repo.findById(courseId)
		.orElseThrow(() -> new CourseNotFoundException("course for id " + courseId + " not found"));
	if (course == null) {
	    return false;
	}

	repo.delete(course);

	return true;
    }

    @Override
    public CourseDTO getCoursebyId(long courseId) {
	Course course = repo.findById(courseId)
		.orElseThrow(() -> new CourseNotFoundException("course for id " + courseId + " not found"));
	
	return CourseMapper.courseToCourseDTO(course);
    }

}
