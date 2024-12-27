package com.casestudy.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;
import com.casestudy.dto.LessonDTO;
import com.casestudy.entity.Course;
import com.casestudy.entity.Course.CourseBuilder;
import com.casestudy.entity.Learner;
import com.casestudy.entity.Lesson;

//@Mapper(componentModel = "spring" )
@Component
public class CourseMapper {
    
    
//    Course courseDTOToCourse(CourseDTO coursedto);
//    
//    CourseDTO courseToCourseDTO(Course course);

    public static Course courseDTOToCourse(CourseDTO coursedto) {
	CourseBuilder course = Course.builder();
	if (coursedto.lessons() != null) {
	    List<Lesson> collect = coursedto.lessons().stream().map(dto -> Lesson.builder()
		    .id(dto.id() != null ? dto.id() : null).name(dto.name()).content(dto.content()).build())
		    .collect(Collectors.toList());
	    course.lessons(collect);

	}

	if (coursedto.learner() != null) {
	    List<Learner> collect = coursedto.learner().stream().map(ldto -> Learner.builder()
		    .id(ldto.id() != null ? ldto.id() : null).name(ldto.name()).email(ldto.email()
			    ).build())
		    .collect(Collectors.toList());
	    course.learner(collect);
	}

	Course course2 = course.id(coursedto.id() != null ? coursedto.id() : null).name(coursedto.name())
		.description(coursedto.description()).build();
	return course2;
    }

    public static CourseDTO courseToCourseDTO(Course course) {

	
	return CourseDTO.builder()
		.id((long) course.getId().intValue())
		.name(course.getName())
		.createdAt(course.getCreatedAt())
		.updatedAt(course.getUpdatedAt())
		.description(course.getDescription())
		.lessons(course.getLessons() != null
			? course.getLessons().stream()
				.map(lesson -> LessonDTO.builder().id(lesson.getId())
					.name(lesson.getName())
					.content(lesson.getContent())
					.createdAt(lesson.getCreatedAt())
					.updatedAt(lesson.getUpdatedAt())
					.build())
				.collect(Collectors.toList())
			: new ArrayList<>())
		.learner(course.getLearner() != null
			? course.getLearner().stream()
				.map(learner -> LearnerDTO
					.builder().id(learner.getId()).name(learner.getName())
					.email(learner.getEmail())
					.createdAt(learner.getCreatedAt())
					.updatedAt(learner.getUpdatedAt())
					.build())
				.collect(Collectors.toList())
			: new ArrayList<>())
		.build();
	
	

    }
    

}
