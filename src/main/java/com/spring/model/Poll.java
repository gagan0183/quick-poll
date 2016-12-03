package com.spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Poll {
	@Id
	@GeneratedValue
	@Column(name = "poll_id")
	private Long id;

	@NotEmpty
	@Column(name = "question")
	private String question;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "poll_id")
	@OrderBy
	@Size(min = 2, max = 6)
	private Set<Option> options;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}
}
