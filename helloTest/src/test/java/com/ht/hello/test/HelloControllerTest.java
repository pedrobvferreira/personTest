package com.ht.hello.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ht.hello.controller.HelloController;
import com.ht.hello.service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {
	
	private MockMvc mockMvc;

	@Mock
	private HelloService helloService;
	
	@InjectMocks
    private HelloController helloController;
	
	@Before
    public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void testHelloWorld() throws Exception {

        when(helloService.hello()).thenReturn("hello");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

        verify(helloService).hello();
    }

    @Test
    public void testHelloWorldJson() throws Exception {
        mockMvc.perform(get("/hello/json")
        		.param("message","False Message")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
                .andExpect(jsonPath("$.value", Matchers.is("Hello World")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void testPost() throws Exception {
        String json = "{\n" +
                "  \"title\": \"Greetings\",\n" +
                "  \"value\": \"Hello World\"\n" +
                "}";
        mockMvc.perform(post("/hello/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
                .andExpect(jsonPath("$.value", Matchers.is("Hello World")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
}
