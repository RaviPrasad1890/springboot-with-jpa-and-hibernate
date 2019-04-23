package com.ravi.jpa.introduction.jpaintroductondemo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.jpa.introduction.jpaintroductondemo.Entity.Person;

@Repository//optional
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	List<Person> findByName(String name);
	//No implementation required, spring smart enough to understand by name
	List<Person> findByIdAndName(int id,String name);
}
