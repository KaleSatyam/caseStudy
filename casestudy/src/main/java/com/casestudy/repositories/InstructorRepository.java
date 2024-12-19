package com.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
