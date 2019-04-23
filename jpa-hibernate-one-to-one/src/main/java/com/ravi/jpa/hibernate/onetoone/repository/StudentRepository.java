package com.ravi.jpa.hibernate.onetoone.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ravi.jpa.hibernate.onetoone.entity.Passport;
import com.ravi.jpa.hibernate.onetoone.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void findAll() {
		TypedQuery<Student> query = em.createNamedQuery("get_all_student", Student.class);

		List<Student> resultList = query.getResultList();

		logger.info("Select  s  From Student s -> {}", resultList);
	}

	public void saveStudentWithPassport() {
		Passport passport= new Passport("P12345");
		em.persist(passport);
		
		Student student= new Student("Ravi Prasad");
		student.setPassport(passport);
		em.persist(student);
		
	}
	
	//---------------------------------------------------//
	public void findStudent() {
		Student student=em.find(Student.class, 20001L);
		/*Below Query is fired internally by hibernate because by default its Eager Fetch, and when we are getting
		 * Student details at that time only Hibernate will get Passport details too.
		 * 
		 * 
		 *  select student0_.id as id1_3_0_,
        	student0_.name as name2_3_0_,
        	student0_.passport_id as passport3_3_0_,
        	passport1_.id as id1_1_1_,
        	passport1_.number as number2_1_1_ 
    		from
        	student student0_ 
    		left outer join
        	passport passport1_ 
            on student0_.passport_id=passport1_.id 
    		where
        	student0_.id=?
        *
        *
		*/
		logger.info(" -------->"+student);
		logger.info("------->"+student.getPassport());
		/*
		 * However, we can make this process Lazy fetch and increase the performance of our application:
		 * 
		 * Step1: In Student Class, we need to put @OneToOne(fetch=FetchType.LAZY) above Passport variable
		 * Step2: Make our method findStudent() transactional by putting annotaion @Transactional
		 * Note: If our method will not be transactional, then EntityManager instance will get Student and forget it. So now 
		 * when we will call for passport details by student.getPassport() with that student then we will get LazyInitailizationException
		 * Note: In this case transactional annotation is not required because we had put Transactional annotaion above our class
		 * 
		 * Query generated inn Case of Lazy Fetch type:
		 * em.find(Student.class, 20001L):---> select
        										student0_.id as id1_3_0_,
        										student0_.name as name2_3_0_,
        										student0_.passport_id as passport3_3_0_ 
    											from
        										student student0_ 
    											where
        										student0_.id=?
         *
         *
         *student.getPassport():---->     select
        									passport0_.id as id1_1_0_,
        									passport0_.number as number2_1_0_ 
    										from
        									passport passport0_ 
    										where
        									passport0_.id
		 *
		 *To generate above query em should be transactional. 
		 */
		
	}
	@Transactional//Though its not required since this whole class is transactional as we have used it on class level
	public void someTest() {
		/*
		 * >>The moment we declare transactional, automatically @PersistanceContext gets activated
		 */
		//Operation 1: Retrieve student
		Student student=em.find(Student.class, 20002L);
		//Persistance Context(student):=if lazy fetch 
		logger.info("Student "+student);
		
		//Operation2: Retrieve passport
		Passport passport=student.getPassport();
		//Persistance Context(student,passport)
		logger.info("Passport "+passport);//due to persistance context and transactional, even if it will be LazyFetch type we will get the passport details
		
		//Operation3: Update Passport
		passport.setNumber("Upadted"+passport);//due to persistance context, updated passport value will be stored in DB
		//Persistance Context(student,passport++)
		
		
		//Operation4: Update Student
		student.setName("Updated");
		//Persistance Context(student++,passport++)
		/*
		 * >>Student gets updated and value will be saved in DB due to persistance context
		 * >>If by somehow, this operation fails, dan all above operations will be rollbacked from DB due to @Transactional
		 * >>Persistance Context will be created at the start of transaction and killed as soon as transactional
		 * methods gets completed
		 * 
		 * >>If we don't declare transaction, than each operation will be executed as a single transaction, and state will be not maintained
		 */
		

		
	}
}
