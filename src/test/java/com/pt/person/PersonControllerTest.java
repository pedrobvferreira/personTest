package com.pt.person;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pt.person.controller.PersonController;
import com.pt.person.model.Person;
import com.pt.person.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	public static String endpoint = "/api";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Test
	public void getAllPersonsTest() throws Exception {
		var usersList = List.of(new Person("Pedro", "Ferreira", "98234221", "2022-03-23"));
		when(personService.getAllPersons()).thenReturn(usersList);
		
		mockMvc.perform(get(endpoint + "/persons"))
			.andExpect(jsonPath("$.size()").value(1))
			.andExpect(jsonPath("$[0].firstName").value("Pedro"))
			.andExpect(jsonPath("$[0].lastName").value("Ferreira"))
			.andExpect(jsonPath("$[0].date").value("2022-03-23"))
			.andExpect(jsonPath("$[0].phoneNumber").value("98234221"))
            .andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	public void getPersonByIdTest() throws Exception {
		var person = new Person("Pedro", "Ferreira", "98234221", "2022-03-23");
        when(personService.getPersonById(anyInt())).thenReturn(person);
        
        //create a mock HTTP request to verify the expected result
		mockMvc.perform(get(endpoint + "/persons/{id}", 1))
        	.andExpect(jsonPath("$.firstName").value("Pedro"))
        	.andExpect(jsonPath("$.lastName").value("Ferreira"))
        	.andExpect(jsonPath("$.date").value("2022-03-23"))
        	.andExpect(jsonPath("$.phoneNumber").value("98234221"))
            .andDo(print())
        	.andExpect(status().isOk())
        	.andReturn();
	}
	
	@Test
	public void savePersonTest() throws Exception {
		var person = new Person("Filipe", "Fernandes", "965852262", "2022-03-20");
        when(personService.saveOrUpdatePerson(any(Person.class))).thenReturn(person);
        
     	//mock request "/user
        mockMvc.perform(post(endpoint + "/insert")
        	.content(new ObjectMapper().writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.firstName").value("Filipe"))
            .andExpect(jsonPath("$.lastName").value("Fernandes"))
            .andExpect(jsonPath("$.date").value("2022-03-20"))
            .andExpect(jsonPath("$.phoneNumber").value("965852262"))
            .andDo(print())
            .andExpect(status().isCreated());
	}
	
	@Test
	public void deletePersonByIdTest() throws Exception {
        mockMvc.perform(delete(endpoint + "/delete/{id}", 1)
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
        	.andDo(print())
        	.andExpect(status().isOk())
        	.andReturn();
	}
	
	@Test
	public void updatePersonByIdTest() throws Exception {
		var person = new Person(1, "Bruno", "Fernandes", "98234221", "2022-03-20");
		when(personService.getPersonById(anyInt())).thenReturn(person);
		
		var updatedPerson = new Person("David", "Landup", "915643456", "2018-03-20");
		when(personService.saveOrUpdatePerson(any(Person.class))).thenReturn(updatedPerson);
        
        //mock update "/user
        mockMvc.perform(put(endpoint + "/update/{id}", 1)
        	.content(new ObjectMapper().writeValueAsString(updatedPerson))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.firstName").value("David"))
            .andExpect(jsonPath("$.lastName").value("Landup"))
            .andExpect(jsonPath("$.date").value("2018-03-20"))
            .andExpect(jsonPath("$.phoneNumber").value("915643456"))
            .andDo(print())
            .andExpect(status().isOk())
        	.andReturn();
	}
	
	@Test
	private void getPersonByIdParamTest() throws Exception {
		String id = "1";
		
		mockMvc.perform(get(endpoint + "/persons/{id}", 1)
				.param("id", id))
		
    	.andExpect(jsonPath("$.firstName").value("Pedro"))
    	.andExpect(jsonPath("$.lastName").value("Ferreira"))
    	.andExpect(jsonPath("$.date").value("2022-03-23"))
    	.andExpect(jsonPath("$.phoneNumber").value("98234221"))
        .andDo(print())
    	.andExpect(status().isOk())
    	.andReturn();
	}
}
