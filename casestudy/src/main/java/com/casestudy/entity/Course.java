/**
 * 
 */
package com.casestudy.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Course extends BaseClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Lesson> lessons = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "course" , fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Learner> learner=new HashSet<>();

}
