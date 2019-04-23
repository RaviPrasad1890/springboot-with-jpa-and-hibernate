package com.ravi.jpa.hibernate.oneToManyToOne.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ravi.jpa.hibernate.oneToManyToOne.entity.Course;
import com.ravi.jpa.hibernate.oneToManyToOne.entity.Review;


@Repository
@Transactional
public class ReviewRepository {

	Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	
	/*
	 *
	 */
	public Review findById(long id) {
		return em.find(Review.class, id);
	}
	
	public void deleteByid(long id) {
		Review review=em.find(Review.class, id);
		em.remove(review);
	}
	
	public void findAll() {
		TypedQuery<Review> query = em.createNamedQuery("get_all_review", Review.class);

		List<Review> resultList = query.getResultList();

		logger.info("Select  r  From Review r -> {}", resultList);
	}
	/*
	 * Note: I am not creating any method to insert review, as reviews can only be added to a course, so to 
	 * persist a review, i have implemented functionality of adding reviews from CourseRepository
	 */
	
	
	public void retreiveCourseForReviews(long reviewId) {
		
		Review review= em.find(Review.class,reviewId );
		
		/*
		 * Note: By default for ManyToOne side of relationship, fetch type is eager
		 * Since, here in this case we are keeping functionality as default we are getting eager fetching and below query is being generated,
		 * i.e. while we load review at same time course is also being loaded.
		 * Query generated in case of eager fetching(default for ManyToOne side):
		 * 
		 * select
        	review0_.id as id1_2_0_,
        	review0_.course_id as course_i4_2_0_,
        	review0_.description as descript2_2_0_,
        	review0_.rating as rating3_2_0_,
        	course1_.id as id1_0_1_,
        	course1_.created_date as created_2_0_1_,
        	course1_.last_updated_date as last_upd3_0_1_,
        	course1_.name as name4_0_1_ 
    	from
        	review review0_ 
    	left outer join
        	course course1_ 
            	on review0_.course_id=course1_.id 
    	where
        	review0_.id=?
        	
        	* Note: We can make lazy loading by declaring Fetch Type in Review class as Lazy above course variable. In that case we have 
        	* to make sure that our method comes under transactional block, as in this case or we will get Lazy Type Initialisation Exception.
        	* Here method is transactional because we have declared @Transactional on top of this class.
        	* 
        	
        	In case of Eager Fetch, query generated: 
        	    select
        			review0_.id as id1_2_0_,
        			review0_.course_id as course_i4_2_0_,
        			review0_.description as descript2_2_0_,
        			review0_.rating as rating3_2_0_ 
    				from
        			review review0_ 
    				where
        			review0_.id=?
		 */
		
		Course course=review.getCourse();
		/*
		 * For Lazy Fetch type, below query will be generated after above method call to get Course detail:
		 * select
        	course0_.id as id1_0_0_,
        	course0_.created_date as created_2_0_0_,
        	course0_.last_updated_date as last_upd3_0_0_,
        	course0_.name as name4_0_0_ 
    		from
        	course course0_ 
    		where
        	course0_.id=?
        *
        *
        *
		 */
		logger.info("{}",course);
	}
}
