package com.pt.person;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.pt.person.model.Person;
import com.pt.person.repository.PersonRepository;

@DataJpaTest
public class PersonRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void findByNameLikeNativeQueryTest() {
		var person1 = new Person("Fernando", "Ferreira", "98234221", "2022-01-01");
		entityManager.persist(person1);
		var person2 = new Person("Filipe", "Fernandes", "965852262", "2022-01-02");
		entityManager.persist(person2);
		var person3 = new Person("Fernando", "Teixeira", "935851112", "2022-01-03");
		entityManager.persist(person3);
		
		var persons = personRepository.findByNameLike("Fer");
		assertThat(persons).hasSize(2).contains(person1, person3);
	}
}
