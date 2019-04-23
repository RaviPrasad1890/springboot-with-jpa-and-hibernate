package com.ravi.jpa.hibernate.oneToManyToOne;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ravi.jpa.hibernate.oneToManyToOne.entity.Review;
import com.ravi.jpa.hibernate.oneToManyToOne.repository.CourseRepository;
import com.ravi.jpa.hibernate.oneToManyToOne.repository.ReviewRepository;
import com.ravi.jpa.hibernate.oneToManyToOne.repository.StudentRepository;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//studentRepository.saveStudentWithPassport();
		//repository.playWithEntityManager();
		Review review1 = new Review("5", "Great Hands-on Stuff.");	
		Review review2 = new Review("5", "Hatsoff.");
		List<Review> reviews= new ArrayList<Review>();
		reviews.add(review1);
		reviews.add(review2);
		courseRepository.addReviewsForCourse(10003L,reviews);
		
		
		//Test: Eager and Lazy Fetch in OneToMany side of relationship
		courseRepository.retreiveReviewsForCourse(10001L);
		
		//Test: Eager and Lazy Fetch in ManyToOne side of relationship
		reviewRepository.retreiveCourseForReviews(50001L);
		
 
		
	}	
}
