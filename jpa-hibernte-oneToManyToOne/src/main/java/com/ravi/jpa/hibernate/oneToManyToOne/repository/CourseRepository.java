package com.ravi.jpa.hibernate.oneToManyToOne.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.jpa.hibernate.oneToManyToOne.entity.Course;
import com.ravi.jpa.hibernate.oneToManyToOne.entity.Review;



@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	/*
	 * This course now have review mapped to it in OneToMany relationship, i.e., one course have many reviews
	 * When we try to retrieve a particular course, as by default its lazy fetch type, only course details will be retrieved
	 * For OneToMany relationship, fetch type by default is lazy, for performance issue
	 * So once we get a particular course, we can find reviews attached to that course by using course entity, but remember since its a lazy fetch type,
	 * that method must be in Transactional scope
	 * (See retreiveReviewsForCourse method in this class)
	 * 
	 */
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		Course course1 = new Course("Web Services in 100 Steps");
		em.persist(course1);
		
		Course course2 = findById(10001L);
		
		course2.setName("JPA in 50 Steps - Updated");
		
	}

	public void addReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {}", course.getReviews());
		
		//add 2 reviews to it
		Review review1 = new Review("5", "Great Hands-on Stuff.");	
		Review review2 = new Review("5", "Hatsoff.");
		
		//setting the relationship
		course.addReview(review1);
		review1.setCourse(course);//only adding review will not save the data(course id for respective review in Review table) in DB, 
								//since here Review is in owner side of relationship, we need to set course for review
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to the database
		em.persist(review1);
		em.persist(review2);
	}
	
	//Adding review in more generalised way: Instead of using above method will use this one to add reviews from main driver method
	public void addReviewsForCourse(Long courseId,List<Review> reviews) {

		Course course = findById(courseId);
		logger.info("course.getReviews() -> {}", course.getReviews());
		
		for(Review review: reviews) {
		
		//setting the relationship
		course.addReview(review);
		review.setCourse(course);//only adding review will not save the data(course id for respective review in Review table) in DB, 
								//since here Review is in owner side of relationship, we need to set course for review
				
		//save it to the database
		em.persist(review);

		}
	}
	
	public void retreiveReviewsForCourse(long courseId) {
		
		Course course= findById(courseId);
		/*
		 * Query in case of lazy Fetch:
		 * 
		 *     select
		 *     course0_.id as id1_0_0_,
		 *     course0_.created_date as created_2_0_0_,
		 *     course0_.last_updated_date as last_upd3_0_0_,
		 *     course0_.name as name4_0_0_ 
		 *     from
		 *     course course0_ 
		 *     where
		 *     course0_.id=?
		 * 
		 * 
		 * Query in case of eager fetch:
		 * 
		 * 
		 * select
        course0_.id as id1_0_0_,
        course0_.created_date as created_2_0_0_,
        course0_.last_updated_date as last_upd3_0_0_,
        course0_.name as name4_0_0_,
        reviews1_.course_id as course_i4_2_1_,
        reviews1_.id as id1_2_1_,
        reviews1_.id as id1_2_2_,
        reviews1_.course_id as course_i4_2_2_,
        reviews1_.description as descript2_2_2_,
        reviews1_.rating as rating3_2_2_ 
    	from
        course course0_ 
    	left outer join
        review reviews1_ 
            on course0_.id=reviews1_.course_id 
    	where
        course0_.id=?
		 */
		
		
		List<Review> reviews=course.getReviews();//This line will give error in case of Lazy Fetch Type and non transactional block
		/*
		 * Above line will generate below query in case of lazy fetch:
		 * 
		 *     select
        reviews0_.course_id as course_i4_2_0_,
        reviews0_.id as id1_2_0_,
        reviews0_.id as id1_2_1_,
        reviews0_.course_id as course_i4_2_1_,
        reviews0_.description as descript2_2_1_,
        reviews0_.rating as rating3_2_1_ 
    from
        review reviews0_ 
    where
        reviews0_.course_id=?
		 * 
		 */
		logger.info("{}",reviews);
		/*
		 * Will not get LazyFecthTypeException though we are doing default fetch type for OneToMany, i.e. Lazy fetchTpe because:
		 * 1: Its transactional, this class is itself declared as TRANSACTIONAL
		 * 
		 * Another way of avoiding this exception is to make fetch type as Eager in Course class by defining attribute 'fetch=FecthType=Eager' above reviews
		 * variable
		 */
	}
	
}
