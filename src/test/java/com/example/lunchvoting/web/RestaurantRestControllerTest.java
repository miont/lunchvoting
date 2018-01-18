package com.example.lunchvoting.web;

import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Restaurant;
import com.example.lunchvoting.dto.PersonDto;
import com.example.lunchvoting.dto.RestaurantDto;
import com.example.lunchvoting.service.RestaurantService;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static com.example.lunchvoting.util.testdata.PersonTestData.*;
import static com.example.lunchvoting.util.testdata.RestaurantTestData.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private static final String REST_URL_ADMIN = RestaurantRestController.REST_URL_ADMIN + "/";

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(RESTAURANT1.getName())))
                .andExpect(jsonPath("$.address", is(RESTAURANT1.getAddress())))
                .andExpect(jsonPath("$.url", is(RESTAURANT1.getUrl())))
                .andExpect(jsonPath("$.menu", hasSize(RESTAURANT1.getMenu().size())))
                .andExpect(jsonPath("$.menu[0].name", is(RESTAURANT1.getMenu().get(0).getName())))
                .andExpect(jsonPath("$.menu[0].price", is(RESTAURANT1.getMenu().get(0).getPrice())))
                .andExpect(jsonPath("$.menu[1].name", is(RESTAURANT1.getMenu().get(1).getName())))
                .andExpect(jsonPath("$.menu[1].price", is(RESTAURANT1.getMenu().get(1).getPrice())))
                .andExpect(jsonPath("$.menu[2].name", is(RESTAURANT1.getMenu().get(2).getName())))
                .andExpect(jsonPath("$.menu[2].price", is(RESTAURANT1.getMenu().get(2).getPrice())));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(RESTAURANT2.getName())))  // sorted by name
                .andExpect(jsonPath("$[0].address", is(RESTAURANT2.getAddress())))
                /*.andExpect(jsonPath("$[0].url", is(RESTAURANT2.getUrl())))*/
                .andExpect(jsonPath("$[1].name", is(RESTAURANT1.getName())))
                .andExpect(jsonPath("$[1].address", is(RESTAURANT1.getAddress())));
                /*.andExpect(jsonPath("$[1].url", is(RESTAURANT1.getUrl())));*/
    }

    @Test
    public void testGetAllCache() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post(REST_URL_ADMIN)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(RESTAURANT_NEW, RestaurantDto.class)))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(RESTAURANT_NEW.getName())))
                .andExpect(jsonPath("$.address", is(RESTAURANT_NEW.getAddress())))
                .andExpect(jsonPath("$.url", is(RESTAURANT_NEW.getUrl())));
        Assert.assertEquals(restaurantService.getAll().size(), 3);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT2);
        String updatedAddress = "633 N Saint Clair St, Chicago";
        String updatedUrl = "http://www.thecapitalgrille.com";
        updated.setAddress(updatedAddress);
        updated.setUrl(updatedUrl);
        mockMvc.perform(put(REST_URL_ADMIN + RESTAURANT2_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(updated, RestaurantDto.class)))
                .with(csrf()))
                .andExpect(status().isOk());
        List<RestaurantDto> restaurants  = restaurantService.getAll();
        Assert.assertEquals(restaurants.size(), 2);
        RestaurantDto restaurant  = restaurantService.get(2);
        Assert.assertEquals(restaurant.getName(), RESTAURANT2.getName());
        Assert.assertEquals(restaurant.getAddress(), updatedAddress);
        Assert.assertEquals(restaurant.getUrl(), updatedUrl);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL_ADMIN + RESTAURANT1_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
        List<RestaurantDto> restaurants  = restaurantService.getAll();
        Assert.assertEquals(restaurants.size(), 1);
        Assert.assertEquals(restaurants.get(0).getName(), RESTAURANT2.getName());
    }

}
