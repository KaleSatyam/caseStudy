package com.casestudy.helper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;
import com.casestudy.dto.LessonDTO;
import com.casestudy.entity.Course;
import com.casestudy.entity.Learner;
import com.casestudy.entity.Lesson;

@Component
public class CourseMapper {
	
	public static Course convertCourseDtoToCourse(CourseDTO coursedto) {
		Course course = Course.builder()
				.name(coursedto.name())
				.description(coursedto.description())
				.lessons(coursedto.lessons().stream().map(dto-> Lesson.builder()
						.name(dto.name())
						.content(dto.content()).build())
						.collect(Collectors.toSet()))
				.learner(coursedto.learners().stream().map(ldto -> Learner.builder()
						.name(ldto.name())
						.email(ldto.email()).build()).collect(Collectors.toSet())).build();
		return course;
	}
	
	public static CourseDTO convertCourseToCourseDto(Course course) {
		 return CourseDTO.builder()
			        .name(course.getName())
			        .description(course.getDescription())
			        .lessons(course.getLessons().stream()
			            .map(lesson -> LessonDTO.builder()
			                .id(lesson.getId()) // Include `id` if necessary
			                .name(lesson.getName())
			                .content(lesson.getContent())
			                .build())
			            .collect(Collectors.toSet()))
			        .learners(course.getLearner().stream()
			            .map(learner -> LearnerDTO.builder()
			                .id(learner.getId()) // Include `id` if necessary
			                .name(learner.getName())
			                .email(learner.getEmail())
			                .build())
			            .collect(Collectors.toSet()))
			        .build();
		
	}
	

}
