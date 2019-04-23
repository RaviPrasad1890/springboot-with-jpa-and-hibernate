package com.ravi.jpa.hibernate.oneToManyToOne.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="get_all_review",query = "from Review")
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String description;

	/* Review is owner of this relationship, and we want to have one column Course_Id as Foreign Key in Review table, pointing to Course table primary key
	* Note: by default course_id will be the name, if we want to specify specifically column name then  we need to use @JoinColumn
	*
	 * @ManyToOne
	 * @JoinColumn(name = "column_id")
	 * private Course course;
    */
	@ManyToOne//(fetch=FetchType.LAZY)//By default, fetch type is Eager on ManyToOne side of relationship
	private Course course;//

	
	
	protected Review() {
	}

	public Review(String rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Review[%s %s]", rating, description);
	}

}