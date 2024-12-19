package com.casestudy.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Progress extends BaseClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private LocalDateTime completionDate;

	@JsonBackReference
	@ManyToOne(optional = true)
	@JoinColumn(name = "learner_id")
	private Learner learner;

	@JsonBackReference
	@ManyToOne(optional = true)
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;

}
