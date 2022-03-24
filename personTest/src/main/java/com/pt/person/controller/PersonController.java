package com.pt.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pt.person.model.Person;
import com.pt.person.service.PersonService;

@RestController
@RequestMapping("/endpoint")
public class PersonController {
	@Autowired
    PersonService personService;

    @GetMapping("/select")
    private List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/select/{id}")
    private Person getPerson(@PathVariable("id") int id) {
        return personService.getPersonById(id);
    }

    @DeleteMapping("/delete/{id}")
    private void deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
    }

    @PostMapping("/insert")
    private int savePerson(@RequestBody Person person) {
        personService.saveOrUpdate(person);
        return person.getId();
    }
}
