package ru.dez.spring.controllers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import ru.dez.spring.dao.PersonDAO;
import ru.dez.spring.models.Person;

@ExtendWith(MockitoExtension.class)
class PeopleControllerTest {
    @Mock
    private PersonDAO personDAOMock;

    @InjectMocks
    private PeopleController peopleController;

    private Person person;
    private BindingResult bindingResult;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(4);
        person.setName("John");
        person.setAge(22);
        person.setEmail("john@gmail.com");

        bindingResult = new BeanPropertyBindingResult(person, "person");

        mockMvc = MockMvcBuilders.standaloneSetup(peopleController).build();
    }

    @Test
    void testAddPerson() {
        peopleController.create(person, bindingResult);

        Assertions.assertEquals(4, person.getId());
    }

    @Test
    void testShowPerson() throws Exception {
       mockMvc.perform(get("/people/1")).andExpect((ResultMatcher) jsonPath("$").value(0));
    }
}
