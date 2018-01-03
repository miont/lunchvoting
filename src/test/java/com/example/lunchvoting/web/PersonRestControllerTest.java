package com.example.lunchvoting.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.example.lunchvoting.util.PersonTestData.*;
import static org.hamcrest.Matchers.*;
import static com.example.lunchvoting.domain.AbstractBaseEntity.START_SEQ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import javax.annotation.PostConstruct;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@Transactional
public class PersonRestControllerTest {

    private static final String REST_URL = PersonRestController.REST_URL + "/";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER1_ID))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.id", is(USER1_ID)))
        .andExpect(jsonPath("$.username", is(USER1.getUsername())))
        .andExpect(jsonPath("$.email", is(USER1.getEmail())))
        .andExpect(jsonPath("$.firstName", is(USER1.getFirstName())))
        .andExpect(jsonPath("$.lastName", is(USER1.getLastName())));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + NOT_EXISTING_USER_ID))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void TestGetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=", USER1.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(USER1.getUsername())))
                .andExpect(jsonPath("$.email", is(USER1.getEmail())))
                .andExpect(jsonPath("$.firstName", is(USER1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(USER1.getLastName())));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print());
        // TODO: Check fields for equalitys
    }

    @Test
    public void testCreate() throws Exception {
//        mockMvc.perform(post(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(USER_NEW))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username", is(USER1.getUsername())))
//                .andExpect(jsonPath("$.email", is(USER1.getEmail())))
//                .andExpect(jsonPath("$.firstName", is(USER1.getFirstName())))
//                .andExpect(jsonPath("$.lastName", is(USER1.getLastName())));
    }

}
