package com.ravi.jpa.hibernate.indepth.jpahibernateindepth.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
/*
 * If we want to use more then one query, then instead of @NamedQuery, we should use
 * @NamedQueries
 * For eg:
 * @NamedQueries(
 * value={@NamedQuery(name="xyz",query="abc"),
 * 		  @NAmedQuery(name="pqr",query="def")}
 * 				)
 */
//@NamedQuery(name="find_all_courses", query="from Course")
@Entity//Entity is specialization of bean
@NamedQuery(name="find_all_courses", query="from Course")
public class Course {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@UpdateTimestamp//Hibernate specifc annotation(i.e. its not JPA annotation,HIbernate implements JPA)
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;
	
	public Course() {}
	
	public Course(String name) {
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
	
}
