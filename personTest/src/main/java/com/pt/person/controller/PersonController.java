package com.pt.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pt.person.model.Person;
import com.pt.person.service.PersonService;

@RestController
@RequestMapping("/endpoint")
public class PersonController {
	@Autowired
    PersonService personService;

    @GetMapping("/select")
    private ResponseEntity<?> getAllPersons() {
    	var listPersons = personService.getAllPersons();
        
        return ResponseEntity.status(HttpStatus.OK).body(listPersons);
    }

    @GetMapping("/select/{id}")
    private Person getPersonById(@PathVariable("id") int id) {
        return personService.getPersonById(id);
    }

	@DeleteMapping("/delete/{id}")
	private ResponseEntity<?> deletePerson(@PathVariable("id") int id) {
		var user = personService.getPersonById(id);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			personService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

    @PostMapping("/insert")
    private ResponseEntity<?> savePerson(@RequestBody Person person) {
    	var user = personService.saveOrUpdate(person);
        return ResponseEntity.status(HttpStatus.OK).body(user.getId());
    }
}
