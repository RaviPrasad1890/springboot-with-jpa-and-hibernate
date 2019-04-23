package com.ravi.database.jpademo.jdbc;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ravi.database.jpademo.entity.Person;

//@SuppressWarnings({"unchecked","rawtypes"})


@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	public List<Person> findAll(){
		return jdbcTemplate.query("SELECT * FROM person",
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findById(int id){
		return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?", 
				 new Object[] {id} , 
				 new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findByIdAndName(int id,String name){
		return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=? and name=?", 
				 new Object[] {id,name} , 
				 new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public int deleteById(int id){
		return jdbcTemplate.update("DELETE FROM person WHERE id=?", 
				 new Object[] {id});
	}
	
	public int insert(Person person){
		return jdbcTemplate.update("INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE ) \n" + 
				"VALUES(?,?,?,?)", 
				 new Object[] {person.getId(),person.getName(),person.getLocation(),
						 new Timestamp(person.getBirthDate().getTime())});
	}
	
	public int update(Person person){
		return jdbcTemplate.update("UPDATE  person SET name= ? , location= ? , birth_date= ?  where id= ?"
				 ,new Object[] {person.getName(),person.getLocation(),
						 new Timestamp(person.getBirthDate().getTime()),person.getId()});
	}
	

}
