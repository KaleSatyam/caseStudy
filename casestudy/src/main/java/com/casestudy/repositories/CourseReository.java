package com.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.entity.Course;


@Repository
public interface CourseReository extends JpaRepository<Course, Long> {

}
