package com.ravi.jpa.introduction.jpaintroductondemo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="person")//optional,this annotation is hibernate specific and not JPA specific 
@NamedQuery(name="find_all_persons", query="from Person")//select p from person p
//"select p from person p" will also work as query
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")//optional, its hibernate specific and jpa specific
	private String name;
	
	@Column(name="location")//optional
	private String location;
	
	@Column(name="birth_date")//optional
	private Date birthDate;
	
	public Person() {
		
	}

	
	
	public Person(String name, String location, Date birthDate) {
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}



	public Person(int id, String name, String location, Date birthDate) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
	
	
	

}