package com.pt.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pt.person.model.Person;
import com.pt.person.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
    PersonService personService;

    @GetMapping
    private ResponseEntity<?> getAllPersons() {
    	var listPersons = personService.getAllPersons();
        return ResponseEntity.status(HttpStatus.OK).body(listPersons);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {
		var user = personService.getPersonById(id);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> savePerson(@RequestBody Person person) {
    	var user = personService.saveOrUpdatePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deletePersonById(@PathVariable("id") Long id) {
		var user = personService.getPersonById(id);
		if (user != null) {
			personService.deleteUserById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
	@PutMapping("/{id}")
    private ResponseEntity<?> updatePersonById(@PathVariable("id") Long id, 
		@RequestBody Person newPerson) {
    	var person = personService.getPersonById(id);
    	if (person != null) {
    		person.setFirstName(newPerson.getFirstName());
    		person.setLastName(newPerson.getLastName());
    		person.setPhoneNumber(newPerson.getPhoneNumber());
    		person.setDate(newPerson.getDate());
    		
    		personService.saveOrUpdatePerson(person);
    		return ResponseEntity.status(HttpStatus.OK).body(person);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
