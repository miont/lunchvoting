package com.example.lunchvoting.web;

import org.junit.Test;
import org.springframework.http.MediaType;

import static com.example.lunchvoting.util.PersonTestData.USER2;
import static com.example.lunchvoting.util.RestaurantTestData.RESTAURANT1;
import static com.example.lunchvoting.util.RestaurantTestData.RESTAURANT1_ID;
import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
/**
 *
 */
public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(personHttpBasic(USER2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(RESTAURANT1.getName())))
                .andExpect(jsonPath("$.address", is(RESTAURANT1.getAddress())))
                .andExpect(jsonPath("$.url", is(RESTAURANT1.getUrl())))
                /*.andExpect(jsonPath("$.menu", is(RESTAURANT1.getMenu())))*/  // TODO: make comparison for menu
        ;
    }
}
