package com.ravi.jpa.hibernate.onetoone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name="get_all_student",query = "from Student")
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	////Student is having passport, student is owner of the relationship
	@OneToOne//(fetch=FetchType.LAZY)//::For Lazy Fetch type, by default its Eager
	private Passport passport;
	//As per DB, Student table will have a foreign key field as PassPort ID, pointing to primary key of Passport table Id's column
	
	protected Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", passport=" + passport + "]";
	}

	
}