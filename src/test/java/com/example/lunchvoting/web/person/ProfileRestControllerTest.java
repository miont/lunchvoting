package com.example.lunchvoting.web.person;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.service.PersonService;
import com.example.lunchvoting.web.AbstractControllerTest;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.util.List;

import static com.example.lunchvoting.util.testdata.PersonTestData.*;
import static com.example.lunchvoting.util.testdata.PersonTestData.USER2;
import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL + "/";

    @Autowired
    PersonService personService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(personHttpBasic(USER_EXAMPLE)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(USER2_ID.intValue())))
                .andExpect(jsonPath("$.username", is(USER2.getUsername())))
                .andExpect(jsonPath("$.email", is(USER2.getEmail())))
                .andExpect(jsonPath("$.firstName", is(USER2.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(USER2.getLastName())));
    }

    @Test
    public void testUpdate() throws Exception {
        PersonDto updated = new PersonDto(null, "jimmy", "newemail@yahoo.com", "newpassword", "Tom", "York", USER_EXAMPLE.getRoles());
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updated))
                .with(personHttpBasic(USER_EXAMPLE))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(updated.getUsername(), updated.getPassword())))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(USER2_ID.intValue())))
                .andExpect(jsonPath("$.username", is(updated.getUsername())))
                .andExpect(jsonPath("$.email", is(updated.getEmail())))
                .andExpect(jsonPath("$.firstName", is(updated.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updated.getLastName())));
    }

    @Test
    public void testGetUnauthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized())   // TODO: how it works
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(personHttpBasic(USER_EXAMPLE))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());

        List<PersonDto> users = personService.getAll();
        Assert.assertTrue(users.size() == 2);
        Assert.assertEquals(users.get(0).getUsername(), USER1.getUsername());
    }


}
