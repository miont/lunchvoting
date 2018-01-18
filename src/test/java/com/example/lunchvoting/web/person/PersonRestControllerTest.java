package com.example.lunchvoting.web.person;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Role;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.web.AbstractControllerTest;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.EnumSet;

import static com.example.lunchvoting.util.testdata.PersonTestData.*;
import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */

public class PersonRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = PersonRestController.REST_URL + "/";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER1_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE)))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(USER1_ID.intValue())))   // unsafe conversion long to int
        .andExpect(jsonPath("$.username", is(USER1.getUsername())))
        .andExpect(jsonPath("$.email", is(USER1.getEmail())))
        .andExpect(jsonPath("$.firstName", is(USER1.getFirstName())))
        .andExpect(jsonPath("$.lastName", is(USER1.getLastName())));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + NOT_EXISTING_USER_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void TestGetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + USER2.getEmail())
                .with(personHttpBasic(ADMIN_EXAMPLE)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(USER2_ID.intValue())))
                .andExpect(jsonPath("$.username", is(USER2.getUsername())))
                .andExpect(jsonPath("$.email", is(USER2.getEmail())))
                .andExpect(jsonPath("$.firstName", is(USER2.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(USER2.getLastName())));
    }

    @Test
    public void testGetUnauthorized() throws Exception{
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void testGetForbidden() throws Exception{
        mockMvc.perform(get(REST_URL)
                .with(personHttpBasic(USER_EXAMPLE)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(personHttpBasic(ADMIN_EXAMPLE)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(3)))
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
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(mapper.map(USER_NEW, PersonDto.class)))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(3)))   // Doesn't work due to another test which runs before this one
                .andExpect(jsonPath("$.username", is(USER_NEW.getUsername())))
                .andExpect(jsonPath("$.email", is(USER_NEW.getEmail())))
                .andExpect(jsonPath("$.firstName", is(USER_NEW.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(USER_NEW.getLastName())));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        mockMvc.perform(post(REST_URL)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(mapper.map(USER_INVALID, PersonDto.class)))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testCreateDuplicateUsername() throws Exception {
        Person duplicatePerson = new Person(null, "casualnerd", "goto@yandex.com", "12345", "", "", Role.USER);
        mockMvc.perform(post(REST_URL)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(mapper.map(duplicatePerson, PersonDto.class)))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdate() throws Exception {
        Person updatedUser = new Person(USER2);
        String updatedFirstName = "John";
        String updatedLastName = "Cena";
        updatedUser.setFirstName(updatedFirstName);
        updatedUser.setLastName(updatedLastName);
        mockMvc.perform(put(REST_URL + USER2_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(mapper.map(updatedUser, PersonDto.class)))
                .with(csrf()))
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL + USER2_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(USER2_ID.intValue())))   // unsafe conversion long to int
                .andExpect(jsonPath("$.username", is(USER2.getUsername())))
                .andExpect(jsonPath("$.email", is(USER2.getEmail())))
                .andExpect(jsonPath("$.firstName", is(updatedFirstName)))
                .andExpect(jsonPath("$.lastName", is(updatedLastName)));
    }

    // TODO: update duplicate, update invalid

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER2_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get(REST_URL)
                .with(personHttpBasic(ADMIN_EXAMPLE)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(USER1.getId().intValue())))
                .andExpect(jsonPath("$[0].username", is(USER1.getUsername())))
                .andExpect(jsonPath("$[0].email", is(USER1.getEmail())))
                .andExpect(jsonPath("$[0].firstName", is(USER1.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(USER1.getLastName())));
    }
    // TODO: delete not found
}
