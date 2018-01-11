package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.util.RestaurantTestData;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static com.example.lunchvoting.util.PersonTestData.*;
import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
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
    public void testVote() throws Exception {
        mockMvc.perform(post(RestaurantRestController.REST_URL + "/" + RestaurantTestData.RESTAURANT2_ID + "/votes")
                        .with(personHttpBasic(USER2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testVoteRestaurantNotFound() throws Exception {
        mockMvc.perform(post(RestaurantRestController.REST_URL + "/" + 3L + "/votes")
                .with(personHttpBasic(USER2)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
