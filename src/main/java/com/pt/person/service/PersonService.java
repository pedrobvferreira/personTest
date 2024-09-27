package com.pt.person.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
        return personRepository.findAll().stream()
        .map(PersonDTO::toDTO)
        .collect(Collectors.toList());
    }

    public PersonDTO getPersonById(Long id) {
      Person person = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException());
        
      return PersonDTO.toDTO(person);
    }

    public PersonDTO saveOrUpdatePerson(PersonDTO personDTO) {
      Person person = personRepository.save(PersonDTO.toEntity(personDTO));
    	return PersonDTO.toDTO(person);
    }

    public void deleteUserById(Long id) {
    	Person person = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException());

      personRepository.delete(person);
    }
}
