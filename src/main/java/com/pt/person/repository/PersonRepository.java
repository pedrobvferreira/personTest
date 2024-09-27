package com.pt.person.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pt.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	Person findByPhoneNumber(String phoneNumber);
	
	@Query(value = "select * from person as u where u.first_name like :name%", nativeQuery = true)
	List<Person> findByNameLike(@Param("name") String name);
}
