package com.ravi.database.jpademo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ravi.database.jpademo.entity.Person;

//@SuppressWarnings({"unchecked","rawtypes"})


@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//Used inner class because this row mapper will be used only in 
	//this PersonDao class
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Person person= new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			
			return person;

		}
		
	}
	
	
	public List<Person> findAll(){
		return jdbcTemplate.query("SELECT * FROM person",
				new PersonRowMapper());
	}
	
	public Person findById(int id){
		return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?", 
				 new Object[] {id} , 
				 new PersonRowMapper());
	}
	
	public Person findByIdAndName(int id,String name){
		return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=? and name=?", 
				 new Object[] {id,name} , 
				 new PersonRowMapper());
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
