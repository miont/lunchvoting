package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.service.RestaurantService;
import com.example.lunchvoting.util.RestUtil;
import com.example.lunchvoting.util.testdata.RestaurantTestData;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.example.lunchvoting.util.testdata.PersonTestData.*;
import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static com.example.lunchvoting.util.testdata.RestaurantTestData.RESTAURANT1;
import static com.example.lunchvoting.util.testdata.RestaurantTestData.RESTAURANT2;
import static com.example.lunchvoting.util.testdata.VoteTestData.VOTE_AFTER_11;
import static com.example.lunchvoting.util.testdata.VoteTestData.VOTE_BEFORE_11;
import static com.example.lunchvoting.util.testdata.VoteTestData.VOTE_NEW;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class VoteRestControllerTest extends AbstractControllerTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testVote() throws Exception {
        mockMvc.perform(post(RestUtil.REST_URL_ROOT + "/votes")
                .with(personHttpBasic(USER3))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(VOTE_NEW))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(USER3.getId().intValue())))
                .andExpect(jsonPath("$.restaurantId", is(RESTAURANT1.getId().intValue())))
                .andExpect(jsonPath("$.successfull", is(true)));
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT1.getId()) == 3);
    }

    @Test
    public void testVoteChangeOk() throws Exception {    // TODO: vote new
        mockMvc.perform(post(RestUtil.REST_URL_ROOT + "/votes")
                .with(personHttpBasic(USER2))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(VOTE_BEFORE_11))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(USER2.getId().intValue())))
                .andExpect(jsonPath("$.restaurantId", is(RESTAURANT2.getId().intValue())))
                .andExpect(jsonPath("$.successfull", is(true)));
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT1.getId()) == 1);
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT2.getId()) == 1);

    }

    @Test
    public void testVoteChangeLate() throws Exception {
        mockMvc.perform(post(RestUtil.REST_URL_ROOT + "/votes")
                .with(personHttpBasic(USER2))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(VOTE_AFTER_11))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(USER2.getId().intValue())))
                .andExpect(jsonPath("$.restaurantId", is(RESTAURANT2.getId().intValue())))
                .andExpect(jsonPath("$.successfull", is(false)));
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT1.getId()) == 2);
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT2.getId()) == 0);
    }

    @Test
    public void testVoteNow() throws Exception {
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT2.getId()) == 0);
        mockMvc.perform(post(RestaurantRestController.REST_URL + "/" + RestaurantTestData.RESTAURANT2_ID + "/votes")
                .with(personHttpBasic(USER3))
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId", is(USER3.getId().intValue())))
                .andExpect(jsonPath("$.restaurantId", is(RESTAURANT2.getId().intValue())))
                .andExpect(jsonPath("$.successfull", is(true)));
        Assert.assertTrue(restaurantService.getTodayVotesCount(RESTAURANT2.getId()) == 1);
    }


    @Test
    public void testVoteRestaurantNotFound() throws Exception {
        mockMvc.perform(post(RestaurantRestController.REST_URL + "/" + 3L + "/votes")
                .with(personHttpBasic(USER3))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
