package com.casestudy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
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
public class Assessment extends BaseClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tital;
	private List<String> questions;
	private Long maxScore;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "lesson_id", nullable = true)
	private Lesson lesson;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "instructor_id", nullable = true)
	private Instructor instructor;

}
