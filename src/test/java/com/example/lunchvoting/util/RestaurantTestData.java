package com.example.lunchvoting.util;

import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.domain.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 */
public class RestaurantTestData {

    public static final long RESTAURANT1_ID = 1;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Negril Village", "70 West 3rd St, New York, NY 10012, USA", "http://negrilvillageatl.com", null);
    public static final long RESTAURANT1_DISH1_ID = 1;
    public static final Dish RESTAURANT1_DISH1 = new Dish(RESTAURANT1_DISH1_ID,"Guava BBQ Wings", 13, RESTAURANT1, LocalDate.of(2017, 1, 8));
    public static final long RESTAURANT1_DISH2_ID = 2;
    public static final Dish RESTAURANT1_DISH2 = new Dish(RESTAURANT1_DISH2_ID,"Curry Goat Stew Rotid", 22, RESTAURANT1, LocalDate.of(2017, 1, 8));
    public static final long RESTAURANT1_DISH3_ID = 3;
    public static final Dish RESTAURANT1_DISH3 = new Dish(RESTAURANT1_DISH3_ID,"Quinoa Ital Stew", 17, RESTAURANT1, LocalDate.of(2017, 1, 8));
    public static final long RESTAURANT1_DISH4_ID = 4;
    public static final Dish RESTAURANT1_DISH4 = new Dish(RESTAURANT1_DISH4_ID,"Mango Blue Cheese Salad", 14, RESTAURANT1, LocalDate.of(2017, 1, 8));
    public static final long RESTAURANT1_DISH5_ID = 5;
    public static final Dish RESTAURANT1_DISH5 = new Dish(RESTAURANT1_DISH5_ID,"Jerk Chicken Wings", 13, RESTAURANT1, LocalDate.of(2017, 1, 8));
    public static final long RESTAURANT1_DISH6_ID = 6;
    public static final Dish RESTAURANT1_DISH6 = new Dish(RESTAURANT1_DISH6_ID,"Curry Shrimp", 27, RESTAURANT1, LocalDate.of(2017, 1, 8));

    static {
        RESTAURANT1.setMenu(Arrays.asList(RESTAURANT1_DISH4, RESTAURANT1_DISH5, RESTAURANT1_DISH6));
    }

}
