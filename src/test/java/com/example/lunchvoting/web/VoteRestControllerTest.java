package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.util.RestaurantTestData;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static com.example.lunchvoting.util.PersonTestData.USER2;
import static com.example.lunchvoting.util.PersonTestData.USER_NEW;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class VoteRestControllerTest extends AbstractControllerTest {

    @Test
    public void testMakeVote() throws Exception {
        mockMvc.perform(post(RestaurantRestController.REST_URL + RestaurantTestData.RESTAURANT1_ID+ "/votes")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(USER2.getUsername(), USER2.getPassword())))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
