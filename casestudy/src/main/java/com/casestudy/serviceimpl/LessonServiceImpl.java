package com.casestudy.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.Exceptions.CourseNotFoundException;
import com.casestudy.dto.LessonDTO;
import com.casestudy.entity.Course;
import com.casestudy.entity.Lesson;
import com.casestudy.helper.LessonMapper;
import com.casestudy.repositories.CourseReository;
import com.casestudy.repositories.Lessonrepository;
import com.casestudy.services.LessonService;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class LessonServiceImpl implements LessonService {
    
    @Autowired
    private Lessonrepository lessonrepo;
    
    @Autowired
    private CourseReository courserepo;
    
    @Autowired
    private LessonMapper mapper;
    
    

    @Override
    public List<LessonDTO> getAll(long courseid) {
	// TODO Auto-generated method stub
	Course course = courserepo .findById(courseid)
		.orElseThrow(() -> new CourseNotFoundException("course for id " + courseid + " not found"));
	
	if(course.getLessons() == null) {
	    return null;
	}
	
	List<Lesson> all = course.getLessons();
	return all.stream().map(one-> mapper.lessonToDto(one)).collect(Collectors.toList());
    }



    @Override
    public LessonDTO createLesson(long courseid, LessonDTO lessondto) {
	// TODO Auto-generated method stub
	Course course = courserepo .findById(courseid)
		.orElseThrow(() -> new CourseNotFoundException("course for id " + courseid + " not found"));
	Lesson lesson = mapper.dtoToLesson(lessondto);
	
	lesson.setCourse(course);
	
	Lesson save = lessonrepo.save(lesson);
	return mapper.lessonToDto(save);
    }



    @Override
    public LessonDTO updateLesson(long id, LessonDTO lessondto) {
	// TODO Auto-generated method stub
	Lesson lesson = lessonrepo.findById(id).orElseThrow(() -> new RuntimeException("lesson not found"));
	lesson.setContent(lessondto.content());
	lesson.setName(lessondto.name());
	
	Lesson save = lessonrepo.save(lesson);
	return mapper.lessonToDto(save);
    }



    @Override
    public boolean deleteLesson(long id) {
	// TODO Auto-generated method stub
	Lesson lesson = lessonrepo.findById(id).orElseThrow(() -> new RuntimeException("lesson not found"));
	if(lesson == null) {
	 return false;   
	}
	    
	    
	lessonrepo.delete(lesson);
	return true;
    }

   

}
