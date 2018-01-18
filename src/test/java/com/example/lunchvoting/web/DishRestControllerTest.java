package com.example.lunchvoting.web;

import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.domain.Restaurant;
import com.example.lunchvoting.dto.DishDto;
import com.example.lunchvoting.dto.RestaurantDto;
import com.example.lunchvoting.service.DishService;
import com.example.lunchvoting.web.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

import static com.example.lunchvoting.util.TestUtil.personHttpBasic;
import static com.example.lunchvoting.util.testdata.PersonTestData.ADMIN_EXAMPLE;
import static com.example.lunchvoting.util.testdata.RestaurantTestData.*;
import static com.example.lunchvoting.util.testdata.RestaurantTestData.RESTAURANT1_ID;
import static com.example.lunchvoting.util.testdata.RestaurantTestData.RESTAURANT2;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.REST_URL + "/";
    private static final String REST_URL_ADMIN = DishRestController.REST_URL_ADMIN + "/";

    @Autowired
    DishService dishService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(buildUrl(REST_URL, RESTAURANT1_ID) + RESTAURANT1_DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(RESTAURANT1_DISH1.getName())))
                .andExpect(jsonPath("$.price", is(RESTAURANT1_DISH1.getPrice())))
                .andExpect(jsonPath("$.date", is(RESTAURANT1_DISH1.getDate().toString()))); // TODO: conversion String to LocalDate
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(buildUrl(REST_URL, RESTAURANT1_ID)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].name", is(RESTAURANT1_DISH2.getName())))
                .andExpect(jsonPath("$[0].price", is(RESTAURANT1_DISH2.getPrice())))
                .andExpect(jsonPath("$[0].date", is(RESTAURANT1_DISH2.getDate().toString())));
    }

//    @Test
//    public void testGetAllToday() throws Exception {
//        mockMvc.perform(get(buildUrl(REST_URL, RESTAURANT1_ID) + "?startDate=" + LocalDate.now()))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[0].name", is(RESTAURANT1_DISH6.getName())))
//                .andExpect(jsonPath("$[0].price", is(RESTAURANT1_DISH6.getPrice())))
//                .andExpect(jsonPath("$[0].date", is(RESTAURANT1_DISH6.getDate().toString())));
//    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(post(buildUrl(REST_URL_ADMIN, RESTAURANT1_ID))
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(DISH_NEW, DishDto.class)))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(DISH_NEW.getName())))
                .andExpect(jsonPath("$.price", is(DISH_NEW.getPrice())))
                .andExpect(jsonPath("$.date", is(DISH_NEW.getDate().toString())));
        Assert.assertEquals(dishService.getAll(RESTAURANT1_ID).size(), 7);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(RESTAURANT1_DISH1);
        String updatedName = "burger";
        int updatedPrice = 1000;
        updated.setName(updatedName);
        updated.setPrice(updatedPrice);
        mockMvc.perform(put(buildUrl(REST_URL_ADMIN, RESTAURANT1_ID) + RESTAURANT1_DISH1_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(mapper.map(updated, DishDto.class)))
                .with(csrf()))
                .andExpect(status().isOk());
        DishDto dish  = dishService.get(RESTAURANT1_DISH1_ID, RESTAURANT1_ID);
        Assert.assertEquals(dish.getName(), updatedName);
        Assert.assertEquals(dish.getPrice(), updatedPrice);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(buildUrl(REST_URL_ADMIN, RESTAURANT1_ID) + RESTAURANT1_DISH4_ID)
                .with(personHttpBasic(ADMIN_EXAMPLE))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
        List<DishDto> dishes  = dishService.getAllBetweenDates(RESTAURANT1_ID, LocalDate.now(), LocalDate.now());
        Assert.assertEquals(2, dishes.size());
        Assert.assertEquals(RESTAURANT1_DISH6.getName(), dishes.get(0).getName());
    }

    private String buildUrl(String baseUrl, long restaurantId) {
        return baseUrl.replace("{restaurantId}", Long.toString(restaurantId));
    }
}
