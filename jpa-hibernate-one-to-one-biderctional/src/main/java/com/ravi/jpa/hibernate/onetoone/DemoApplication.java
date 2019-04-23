package com.ravi.jpa.hibernate.onetoone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ravi.jpa.hibernate.onetoone.repository.CourseRepository;
import com.ravi.jpa.hibernate.onetoone.repository.PassportRepository;
import com.ravi.jpa.hibernate.onetoone.repository.StudentRepository;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PassportRepository passportRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("*******************One To One Relationship related function Test******************");
		
		studentRepository.saveStudentWithPassport();//hard-coded value for demo purpose of one-to-one relation
		studentRepository.findAll();
		
		//Demo: To get passport details by finding student
		studentRepository.findStudent();
		
		
		//Test of bidirectional
		passportRepository.findPassportAndStudent();
	}	
}
