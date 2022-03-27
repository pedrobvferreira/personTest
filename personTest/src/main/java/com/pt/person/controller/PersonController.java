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
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
    PersonService personService;

    @GetMapping("/select")
    private ResponseEntity<?> getAllPersons() {
    	var listPersons = personService.getAllPersons();
        
        return ResponseEntity.status(HttpStatus.OK).body(listPersons);
    }

    @GetMapping("/select/{id}")
    private ResponseEntity<?> getPersonById(@PathVariable("id") int id) {
    	var user =  personService.getPersonById(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/insert")
    private ResponseEntity<?> savePerson(@RequestBody Person person) {
    	var user = personService.saveOrUpdatePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<?> deletePersonById(@PathVariable("id") int id) {
		var user = getPersonById(id);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		personService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
    
	@PutMapping("/update/{id}")
    private ResponseEntity<?> updatePersonById(@PathVariable("id") int id, @RequestBody Person person) {
    	var user = personService.getPersonById(id);
    	if (user == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    	user.setFirstName(person.getFirstName());
		user.setLastName(person.getLastName());
		user.setPhoneNumber(person.getPhoneNumber());
		user.setDate(person.getDate());
		
		var savedUser = personService.saveOrUpdatePerson(user);
		return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
}
