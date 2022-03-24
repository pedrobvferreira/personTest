package com.pt.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

	
	@Before
    public void setup() {
    }
	
	@Test
	public void getPersonByIdTest() throws Exception {
		Person person = new Person();
		person.setFirstName("Pedro");
        person.setLastName("Ferreira");
        person.setPhoneNumber("982342215");
        person.setDate("2022-03-23");

        when(personService.getPersonById(anyInt())).thenReturn(person);
        //create a mock HTTP request to verify the expected result
        
		mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
        	.andDo(print())
        	.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pedro"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ferreira"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-03-23"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("98234221"))
        	.andExpect(status().isOk());
	}
	
	@Test
	public void savePersonTest() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setFirstName("Filipe");
        person.setLastName("Fernandes");
        person.setPhoneNumber("965852262");
        person.setDate("2022-03-20");
        
        when(personService.saveOrUpdate(any(Person.class))).thenReturn(person);
        
     	//mock request "/user"

        mockMvc.perform(MockMvcRequestBuilders.post("/insert")
        		.content(new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Filipe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Fernandes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-03-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("965852262"));
	}
}
