package com.casestudy.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Lesson extends BaseClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String content;
	
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "course_id",nullable = true)
	private Course course;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	private List<Assessment> assessment=new ArrayList<>();
	
	@ManyToMany(mappedBy = "lesson")
	@JsonManagedReference
	private List<Learner> learner = new ArrayList<>();
	

	

}
