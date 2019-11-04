package com.yukon.demoDB.demo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yukon.demoDB.demo.person.Person;
import com.yukon.demoDB.demo.person.PersonDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@ActiveProfiles("test")
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PersonControllerTest {
private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
private MockMvc mockMvc;

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
    private WebApplicationContext wac;
	

	@Before
	public void setup() throws ParseException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
        Person p2 = new Person();
        p2.setFullName("Green");
        Date d2 = df.parse("1985-08-12");
        p2.setDateOfBirth(d2);
        personDAO.save(p2);
    }
	
	@Test
	public void verifyAllToDoList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")
				.accept(MediaType.TEXT_PLAIN))
		.andExpect(status().isOk())
		.andExpect(content().string("Green<br>"))
		.andDo(print());
	}


}
