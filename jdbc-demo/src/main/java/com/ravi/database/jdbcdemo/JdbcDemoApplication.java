package com.ravi.database.jdbcdemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ravi.database.jdbcdemo.entity.Person;
import com.ravi.database.jdbcdemo.jdbc.PersonJdbcDao;

@SpringBootApplication
public class JdbcDemoApplication implements CommandLineRunner {

	private Logger logger=LoggerFactory.getLogger(JdbcDemoApplication.class);
	
	@Autowired
	PersonJdbcDao personJdbcDao;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All Persons: "+personJdbcDao.findAll());
		logger.info("<-------------------------------------------------->");
		logger.info("Person with id 10001: "+personJdbcDao.findById(10001));
		logger.info("<-------------------------------------------------->");
		logger.info("Just Testing for multiple parameters: "+personJdbcDao.findByIdAndName(10001,"Ravi"));
		logger.info("<-------------------------------------------------->");
		logger.info("Delete person with id 10003: No of rows effected "+personJdbcDao.deleteById(10003));
		logger.info("<-------------------------------------------------->");
		
		logger.info("Inserting 10004 -> {}", 
				personJdbcDao.insert(new Person(10004, "Munna", "Bihar", new Date())));
		logger.info("<-------------------------------------------------->");
		
		logger.info("Update 10004 -> {}", 
				personJdbcDao.update(new Person(10004, "Triwedy", "Sacred Games", new Date())));
	}

}
