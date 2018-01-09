package com.example.lunchvoting.web;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.web.json.JsonUtil;
import org.dozer.Mapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import javax.annotation.PostConstruct;

/**
 *
 */

public class PersonRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = PersonRestController.REST_URL + "/";

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
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username", is(USER1.getUsername())))
                .andExpect(jsonPath("$[0].email", is(USER1.getEmail())))
                .andExpect(jsonPath("$[0].firstName", is(USER1.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(USER1.getLastName())))
                .andExpect(jsonPath("$[1].username", is(USER2.getUsername())))
                .andExpect(jsonPath("$[1].email", is(USER2.getEmail())))
                .andExpect(jsonPath("$[1].firstName", is(USER2.getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(USER2.getLastName())));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(USER_NEW, PersonDto.class))))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(USER_NEW.getUsername())))
                .andExpect(jsonPath("$.email", is(USER_NEW.getEmail())))
                .andExpect(jsonPath("$.firstName", is(USER_NEW.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(USER_NEW.getLastName())));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(USER_INVALID, PersonDto.class))))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Person updatedUser = new Person(USER2);
        mockMvc.perform(put(REST_URL + USER2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(updatedUser, PersonDto.class))))
                .andExpect(status().isOk())
                .andDo(print());
        // TODO: add getting updated user and check its correctness
    }

    // TODO: update duplicate, update invalid

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        // TODO: check remaining users
    }
    // TODO: delete not found

}
