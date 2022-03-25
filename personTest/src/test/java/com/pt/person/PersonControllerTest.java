package com.pt.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pt.person.controller.PersonController;
import com.pt.person.model.Person;
import com.pt.person.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Test
	public void getAllPersonsTest() throws Exception {
		var usersList = List.of(new Person("Pedro", "Ferreira", "98234221", "2022-03-23"));
		when(personService.getAllPersons()).thenReturn(usersList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/endpoint/select"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()").value(1))
    	.andExpect(jsonPath("$[0].firstName").value("Pedro"))
    	.andExpect(jsonPath("$[0].lastName").value("Ferreira"))
    	.andExpect(jsonPath("$[0].date").value("2022-03-23"))
    	.andExpect(jsonPath("$[0].phoneNumber").value("98234221"));
	}
	
	@Test
	public void getPersonByIdTest() throws Exception {
		var person = new Person("Pedro", "Ferreira", "98234221", "2022-03-23");
        when(personService.getPersonById(anyInt())).thenReturn(person);
        
        //create a mock HTTP request to verify the expected result
		mockMvc.perform(MockMvcRequestBuilders.get("/endpoint/select/1"))
        	.andDo(print())
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.firstName").value("Pedro"))
        	.andExpect(jsonPath("$.lastName").value("Ferreira"))
        	.andExpect(jsonPath("$.date").value("2022-03-23"))
        	.andExpect(jsonPath("$.phoneNumber").value("98234221"));
	}
	
	@Test
	public void savePersonTest() throws Exception {
		var person = new Person("Filipe", "Fernandes", "965852262", "2022-03-20");
        when(personService.saveOrUpdate(any(Person.class))).thenReturn(person);
        
     	//mock request "/user
        mockMvc.perform(MockMvcRequestBuilders.post("/endpoint/insert")
        	.content(new ObjectMapper().writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.firstName").value("Filipe"))
            .andExpect(jsonPath("$.lastName").value("Fernandes"))
            .andExpect(jsonPath("$.date").value("2022-03-20"))
            .andExpect(jsonPath("$.phoneNumber").value("965852262"));
	}
}