package com.ravi.jpa.hibernate.onetoone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="passport")
	Student student;

	/*
	 * >>Mapped By lets us built biderectional relationship, i.e. through passport information we can navigate to student
	 * >>It tells hibernate, that this student is mapped to variable passport in Student entity
	 * >>We put mapped by in non-owner entity of relationship
	 * >>MappedBy annotation tells hibernate that in passport table, no need of creating student id as passport is not owner of this one to one relation
	 */
	protected Passport() {
	}

	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}
	
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}

	

	
}