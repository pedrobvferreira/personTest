package com.pt.person.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.person.model.Person;
import com.pt.person.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(int id) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			return personRepository.findById(id).get();
		}
		return null;
    }

    public Person saveOrUpdatePerson(Person person) {
    	return personRepository.save(person);
    }

    public void deleteUserById(int id) {
    	personRepository.deleteById(id);
    }
}
