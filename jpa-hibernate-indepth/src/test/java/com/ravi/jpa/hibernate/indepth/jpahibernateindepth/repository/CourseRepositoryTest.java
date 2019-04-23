package com.ravi.jpa.hibernate.indepth.jpahibernateindepth.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.ravi.jpa.hibernate.indepth.jpahibernateindepth.JpaHibernateIndepthApplication;
import com.ravi.jpa.hibernate.indepth.jpahibernateindepth.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateIndepthApplication.class)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository repository;
	
	@Test
	public void findByIdBasicTestCase() {
		Course course=repository.findById(101);
		assertEquals("Springboot",course.getName());
	}

	/*
	 * Suppose in delete test case i am using id 101, and same id is being used
	 * in find test case.
	 * Now if delete test case is executed earlier, then find test 
	 * case would fail.
	 * Hence we should use @DirtiesContext
	 * 
	 * Note 1: any way after all test execution, data's will be stored for
	 * normal application.
	 * 
	 * Note 2: Which test case will be executed first, i.e. execution order
	 * is dependent on JVM
	 * 
	 */
	
	@Test
	@DirtiesContext//all the data will be reset after execution of test case
	public void deleteByIdBasicTestCase() {
		repository.deleteById(101);
		assertNull(repository.findById(101));
	}
	
	
	@Test
	@DirtiesContext
	public void saveCourseBasicTestCase() {
		//get a course
		Course course=repository.findById(101);
		assertEquals("Springboot",course.getName());
		
		//update it
		course.setName("SpringBoot in simple steps");
		repository.save(course);
		
		//Check the value
		Course course1=repository.findById(101);
		assertEquals("SpringBoot in simple steps",course1.getName());
	}

}
