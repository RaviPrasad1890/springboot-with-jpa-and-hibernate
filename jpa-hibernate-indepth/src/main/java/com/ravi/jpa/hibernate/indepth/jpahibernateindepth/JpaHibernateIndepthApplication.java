package com.ravi.jpa.hibernate.indepth.jpahibernateindepth;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ravi.jpa.hibernate.indepth.jpahibernateindepth.entity.Course;
import com.ravi.jpa.hibernate.indepth.jpahibernateindepth.repository.CourseRepository;

@SpringBootApplication
public class JpaHibernateIndepthApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;
	
	private Logger logger= LoggerFactory.
			getLogger(JpaHibernateIndepthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateIndepthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Course with id 101 "+courseRepository.findById(101));
		
		logger.info("-------------------------------------------------------");
		
		courseRepository.deleteById(104);
		logger.info("Course with id 104 is deleted");
		logger.info("-------------------------------------------------------");
		logger.info("Course "+courseRepository.save(new Course("Spring Framework"))
						+" Inserted");
		logger.info("-------------------------------------------------------");
		
		Course courseToUpdate=courseRepository.findById(1);
		courseToUpdate.setName("Spring Framework Deep Dive");
		logger.info("Course "+
		courseRepository.save(courseToUpdate)+" updated");
		logger.info("-------------------------------------------------------");
		
		logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		courseRepository.playWithEntityManager();
		courseRepository.demoRefreshMethod();
		
		logger.info("All Course are: ");
		List<Course> courseList=courseRepository.findAll();
		for (Course course : courseList) {
			logger.info("Course "+course);
		}
		
		logger.info("<----------------JPQL Realted Demos--------------->");
		courseRepository.jpql_basic();
		courseRepository.jpql_typed();
		courseRepository.jpql_where();
		
		logger.info("<----------------Native Query Realted Demos--------------->");
		courseRepository.native_queries_basic();
		courseRepository.native_queries_with_parameter();
		courseRepository.native_queries_with_named_parameter();
		courseRepository.native_queries_to_update();
		
		
	}

}
