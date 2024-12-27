package com.casestudy.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Assessment extends BaseClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tital;
	private List<String> questions;
	private Long maxScore;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	 @ManyToOne
	 @JoinColumn(name = "learner_id")
	 @JsonBackReference
	 private Learner learner;
	

}
