package com.pt.test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        
		mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pedro"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ferreira"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-03-23"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("98234221"))
        .andExpect(status().isOk());
	}
}
