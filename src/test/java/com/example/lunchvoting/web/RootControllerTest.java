package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.util.RestUtil;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static com.example.lunchvoting.util.testdata.PersonTestData.ADMIN_EXAMPLE;
import static com.example.lunchvoting.util.testdata.PersonTestData.USER_NEW;
import static com.example.lunchvoting.util.testdata.PersonTestData.jsonWithPassword;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post(RestUtil.REST_URL_ROOT + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(mapper.map(USER_NEW, PersonDto.class)))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(USER_NEW.getUsername())))
                .andExpect(jsonPath("$.email", is(USER_NEW.getEmail())))
                .andExpect(jsonPath("$.firstName", is(USER_NEW.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(USER_NEW.getLastName())));
    }

}
