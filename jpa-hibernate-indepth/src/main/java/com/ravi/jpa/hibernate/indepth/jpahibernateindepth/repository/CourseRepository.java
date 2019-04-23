package com.ravi.jpa.hibernate.indepth.jpahibernateindepth.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.jpa.hibernate.indepth.jpahibernateindepth.entity.Course;


@Repository
@Transactional//without making transactional, this repository will not be able to make any change to db, like delete or insert
public class CourseRepository {

	private Logger logger= LoggerFactory.
			getLogger(this.getClass());
	
	/*
	 * Entities are managed by javax.persistence.
	 * EntityManager instance using persistence context.
	 * Each EntityManager instance is associated with a persistence context.
	 * Within the persistence context, the entity instances and their lifecycle 
	 * are managed.
	 * Persistence context defines a scope under which particular entity 
	 * instances are created, persisted, and removed.
	 * A persistence context is like a cache which contains a set of 
	 * persistent entities , 
	 * So once the transaction is finished, 
	 * all persistent objects are detached from the EntityManager's 
	 * persistence context and are no longer managed.
	 */
	//@PersistenceContext//optional: springboot automatically applies it
	@Autowired
	EntityManager entityManager;
	
	

	
	
	
	public Course findById(int id) {
		return entityManager.find(Course.class, id);
	}
	
	public void deleteById(int id) {
		Course course=findById(id);
		entityManager.remove(course);		
	}
	
	public Course save(Course course) {
		if(course.getId()==0) {
			//Insert
			entityManager.persist(course);
		}else {
			//Update
			entityManager.merge(course);
		}
		
		return course;
		
	}
	
	public void playWithEntityManager() {
		
		Course course = new Course("Learn More On Entity Mananger");
		entityManager.persist(course);//with this line we save one new course(Persisted)
		
		
		//once persisted, its state will be maintained
		course.setName("Learn More On EM: Updated");
		
		//we are not required to explicitly merge it like...
		//..entityManager.merge(course)
		//it will be automatically updated in DB, as state is being maintained,
		//
		//:- above property is also due to @Transactional and @PersistanceContext
		
		
		
		/*
		 * flush() vs detach() vs clear() 
		 * 
		 * >>flush(): flushes the data to db , so all the changes till that point
		 * will be saved to db, its like commit statement
		 * 
		 * >>detach(): one u create an object and associate it to EM,
		 * its state is being maintained by EM in transactional scope.
		 * But if we want to detach that objcet and dont want to mainatin its state,
		 * so that any further change will not reflect to db,
		 * dan we should use detach method.
		 * 
		 * >>clear(): similar to detach method, but it will totally clean the
		 * entitymanager , instead of removing any specific object, i.e., all the
		 * associated object will lose its association, and no state will be 
		 * maintained
		 * 
		 * 
		 * 
		 */
		
		Course c1= new Course("demo1");
		entityManager.persist(c1);
		c1.setName("demo1 update");//state of entity will be maintained
		entityManager.flush();//commit
		
		Course c2= new Course("demo2");
		entityManager.persist(c2);
		entityManager.flush();//commit
		
		entityManager.detach(c2);//state of c2 is no longer maintained
		c2.setName("demo2 updated");//change will not reflect in DB
		
		//At this point of time , state is being maintained for c1
		//by entityManager
		Course c3= new Course("demo3");
		entityManager.persist(c3);
		//At this point state of c1 and c2 is being maintained
		
		entityManager.flush();//commit
		
		/*
		 * As we are clearing by entityManager in belowe line,
		 * we must use flush() in every step to store all the changes
		 * in db otherwise it will be lost and nothing 
		 * will stored in db
		 */
		entityManager.clear();
		//All the transactional state is lost
		c1.setName("Demo 1 Update twice");//Change will not refelct in DB
		c3.setName("Change will not reflct in db");
		
		
		//entityManager.flush();
		
	}
	
	public void demoRefreshMethod() {
		/*
		 * >>refresh(): all the data will be sync from DB and changes done in 
		 * java
		 *will be updated with DB values
		 * */
		Course course1= new Course("Refresh Demo 1");
		Course course2= new Course("Refresh Demo 2");
		
		entityManager.persist(course1);
		entityManager.persist(course2);
		
		entityManager.flush();//if we don't commit before refresh then we will get
								//illegal state exception
		
		course1.setName("Refresh Demo 1 Updated");
		course2.setName("Refresh Demo 2 Updated");
		entityManager.refresh(course1);
		logger.info("<------------Course------------>");
		logger.info(" "+course1.getName());//Refresh Demo 1
		entityManager.flush();
		
		
	}
	
	
	//JPQL related demos
	
	public void jpql_basic() {
		Query query = entityManager.createQuery("Select  c  From Course c");
		List resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}",resultList);
	}
	
	//Generic Version 
	public void jpql_typed() {
		TypedQuery<Course> tq= 
				entityManager.
				createQuery("select c from Course c",Course.class);
		
		List<Course> resultList = tq.getResultList();
		
		logger.info("Select  c  From Course c -> {}",resultList);
	
	}
	
	
	public List<Course> findAll() {
		TypedQuery<Course> namedQuery = entityManager.
				createNamedQuery("find_all_courses", Course.class);
		//Need to define this "find_all_courses" query in Course class
		return namedQuery.getResultList();
	}
	
	
	
	
	public void jpql_where() {
		TypedQuery<Course> query = 
				entityManager.createQuery
				("Select  c  From Course c where name like '%spring%'", 
						Course.class);
		
		List<Course> resultList = query.getResultList();
		
		logger.
		info("Select  c  From Course c where name like '%Spring%'-> {}",
				resultList);
	}
	
	
	//Native Queries
	
	
	
	
	public void native_queries_basic() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE  -> {}", resultList);
	}

	
	public void native_queries_with_parameter() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
		query.setParameter(1, 1);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE  where id = ? -> {}", resultList);
		
	}

	
	public void native_queries_with_named_parameter() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
		query.setParameter("id", 101);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE  where id = :id -> {}", resultList);
		
	}
	
	
	//@Transactional//: not needed since we have already made this class transactional
	public void native_queries_to_update() {
		Query query = entityManager.createNativeQuery("Update COURSE set last_updated_date=sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated  -> {}", noOfRowsUpdated);
	}
	
	
	
}
