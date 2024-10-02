package com.pt.person.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pt.person.model.Person;
import com.pt.person.dto.PersonDTO;
import com.pt.person.exception.NotFoundException;
import com.pt.person.repository.PersonRepository;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<PersonDTO> getAllPersons() {
      List<Person> personsOrders = personRepository.findAll().stream()
        .sorted((s1, s2) -> s1.getFirstName().compareTo(s2.getFirstName())
        ).collect(Collectors.toList());

        return personsOrders.stream()
        .map(PersonDTO::toDTO)
        .collect(Collectors.toList());
    }

    public PersonDTO getPersonById(Long id) {
      Person person = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException());
        
      return PersonDTO.toDTO(person);
    }

    public PersonDTO savePerson(PersonDTO personDTO) {
      Person person = personRepository.save(PersonDTO.toEntity(personDTO));
    	return PersonDTO.toDTO(person);
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
      Person existingPerson = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException());

      existingPerson.setFirstName(personDTO.getFirstName());
      existingPerson.setLastName(personDTO.getLastName());
      existingPerson.setPhoneNumber(personDTO.getPhoneNumber());
      existingPerson.setDate(personDTO.getDate());

      personRepository.save(existingPerson);
    	return PersonDTO.toDTO(existingPerson);
    }

    public void deleteUserById(Long id) {
    	Person person = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException());

      personRepository.delete(person);
    }
}
