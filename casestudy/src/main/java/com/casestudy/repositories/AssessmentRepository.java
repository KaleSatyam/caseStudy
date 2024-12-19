package com.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.entity.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}
