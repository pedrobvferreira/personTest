package com.pt.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pt.person.controller.PersonController;
import com.pt.person.model.Person;
import com.pt.person.service.PersonService;

@SpringBootTest
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Before
    public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new PersonController()).build();
    }
	
	@Test
	public void shouldCreateMockMVC() {
		assertNotNull(mockMvc);
	}
	
	@Test
	public void getAllPersonsTest() throws Exception {
		var usersList = List.of(new Person(1, "Pedro", "Ferreira", "2022-03-23", "98234221"));
		when(personService.getAllPersons()).thenReturn(usersList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/endpoint/select"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Pedro"))
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Ferreira"))
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value("2022-03-23"))
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("98234221"));
	}
	
//	@Test
//	public void getPersonByIdTest() throws Exception {
//		var person = new Person(1, "Pedro", "Ferreira", "2022-03-23", "98234221");
//        when(personService.getPersonById(anyInt())).thenReturn(person);
//        //create a mock HTTP request to verify the expected result
//        
//		mockMvc.perform(MockMvcRequestBuilders.get("/endpoint/select/1"))
//        	.andDo(print())
//        	.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pedro"))
//        	.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Ferreira"))
//        	.andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-03-23"))
//        	.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("98234221"))
//        	.andExpect(status().isOk());
//	}
	
//	@Test
//	public void savePersonTest() throws Exception {
//		Person person = new Person();
//		person.setId(1);
//		person.setFirstName("Filipe");
//        person.setLastName("Fernandes");
//        person.setPhoneNumber("965852262");
//        person.setDate("2022-03-20");
//        
//        when(personService.saveOrUpdate(any(Person.class))).thenReturn(person);
//        
//     	//mock request "/user"
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/insert")
//        		.content(new ObjectMapper().writeValueAsString(person))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Filipe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Fernandes"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2022-03-20"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("965852262"));
//	}
}
