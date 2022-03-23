package com.pt.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pt.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
