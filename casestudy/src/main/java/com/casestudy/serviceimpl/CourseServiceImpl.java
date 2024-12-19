package com.casestudy.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;
import com.casestudy.dto.LessonDTO;
import com.casestudy.entity.Course;
import com.casestudy.entity.Learner;
import com.casestudy.entity.Lesson;
import com.casestudy.helper.CourseMapper;
import com.casestudy.repositories.CourseReository;
import com.casestudy.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseReository repo;
	

	@Override
	public List<Course> getCourses() {
		List<Course> all = repo.findAll();
		
		return all;
	}

	@Override
	public Course createCourse(Course course) {
		if(course.getLessons() != null)
		{
			Set<Lesson> collect = course.getLessons().stream().map(lesson -> Lesson.builder()
					.name(lesson.getName())
					.content(lesson.getContent())
					.course(course).build()).collect(Collectors.toSet());
			course.setLessons(collect);
		}
		if (course.getLearner() != null) {
			Set<Learner> collect = course.getLearner().stream().map(learner ->
			{        Learner existingorNewLearner = Learner.builder()
					.name(learner.getName())
					.email(learner.getEmail()).build();
					
			existingorNewLearner.getCourse().add(course);
			return existingorNewLearner;
			
					}).collect(Collectors.toSet());
			course.setLearner(collect);
		}
		
		
		Course save = repo.save(course);
		if (save == null) {
			throw new RuntimeException("CourseNotCreated...");
		}
		return save;
		
	}

//	@Override
//	public CourseDTO editCourse(long courseId, CourseDTO course) {
//		// TODO Auto-generated method stub
//		Course courseOld = repo.findById(courseId)
//				.orElseThrow(() -> new CourseNotFoundException("Course not found for course id : " + courseId));
//		if (course.getLearner() != null) {
//			courseOld.setLearner(course.getLearner());
//		} else if (course.getDescription() != null) {
//			courseOld.setDescription(course.getDescription());
//		}
//
//		return null;
//	}
//
//	@Override
//	public boolean deleteCourse(long courseId) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
