package com.casestudy.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Learner extends BaseClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "course_lerner", joinColumns = @JoinColumn(name = "learner_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> course = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "learner_lesson", joinColumns = @JoinColumn(name = "learner_id"), inverseJoinColumns = @JoinColumn(name = "lesson_id"))
	@JsonBackReference
	private List<Lesson> lesson = new ArrayList<>();

	@OneToMany(mappedBy = "learner",fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Assessment> assessments = new ArrayList<>();

}
