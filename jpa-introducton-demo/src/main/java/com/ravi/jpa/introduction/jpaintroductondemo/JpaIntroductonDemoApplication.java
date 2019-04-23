package com.ravi.jpa.introduction.jpaintroductondemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ravi.jpa.introduction.jpaintroductondemo.Entity.Person;
import com.ravi.jpa.introduction.jpaintroductondemo.Repository.CustomPersonRepository;
import com.ravi.jpa.introduction.jpaintroductondemo.Repository.PersonRepository;

@SpringBootApplication
@ComponentScan("com.ravi.jpa.introduction.jpaintroductondemo")
public class JpaIntroductonDemoApplication implements CommandLineRunner{

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	CustomPersonRepository customPersonRepository;
	
	private Logger logger= LoggerFactory.getLogger("JpaIntroductonDemoApplication.class");
	
	public static void main(String[] args) {
		SpringApplication.run(JpaIntroductonDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("****************************Through inbuilt JPA repsoitory**********************");
		logger.info("All Person List: "+personRepository.findAll());
		logger.info("--------------------------------------------------");
		logger.info("Person of id 10001: "+personRepository.findById(10001));
		logger.info("--------------------------------------------------");
		logger.info("Person by name :"+personRepository.findByName("Ranga"));
		logger.info("--------------------------------------------------");
		logger.info("Search by name and id: "+personRepository.findByIdAndName(10001, "Ravi"));
		logger.info("--------------------------------------------------");
		personRepository.deleteById(10003);
		logger.info("After deleating, records are: "+personRepository.findAll());
		personRepository.save(new Person("Akshay","Khiladi",new Date()));
		logger.info("After Inserting, records are: "+personRepository.findAll());
		//Similarly we can update a specific person by getting it and dan seting 
		//by setter method and saving the entity
		
		logger.info("****************************Through Own Created JPA repsoitory**********************");
		
	
		logger.info("User id 10001 -> {}", customPersonRepository.findById(10001));
		
		logger.info("Inserting -> {}", 
				customPersonRepository.insert(new Person("Sachin", "India", new Date())));
		
		logger.info("Update 10003 -> {}", 
				customPersonRepository.update(new Person(10003, "Rohit", "Mumbai", new Date())));
		
		customPersonRepository.deleteById(10002);

		logger.info("All users -> {}", customPersonRepository.findAll());
	
	}

}