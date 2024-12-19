package com.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.entity.Lesson;

@Repository
public interface Lessonrepository extends JpaRepository<Lesson, Long> {

}
