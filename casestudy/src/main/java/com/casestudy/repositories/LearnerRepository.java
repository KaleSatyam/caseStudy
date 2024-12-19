package com.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.entity.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {

}
