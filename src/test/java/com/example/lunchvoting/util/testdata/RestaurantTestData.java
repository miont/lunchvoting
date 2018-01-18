package com.example.lunchvoting.util.testdata;

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
    public static final Dish RESTAURANT1_DISH1 = new Dish(RESTAURANT1_DISH1_ID,"Guava BBQ Wings", 1300, RESTAURANT1, LocalDate.now().minusDays(1));
    public static final long RESTAURANT1_DISH2_ID = 2;
    public static final Dish RESTAURANT1_DISH2 = new Dish(RESTAURANT1_DISH2_ID,"Curry Goat Stew Roti", 2200, RESTAURANT1, LocalDate.now().minusDays(1));
    public static final long RESTAURANT1_DISH3_ID = 3;
    public static final Dish RESTAURANT1_DISH3 = new Dish(RESTAURANT1_DISH3_ID,"Quinoa Ital Stew", 1700, RESTAURANT1, LocalDate.now().minusDays(1));
    public static final long RESTAURANT1_DISH4_ID = 4;
    public static final Dish RESTAURANT1_DISH4 = new Dish(RESTAURANT1_DISH4_ID,"Mango Blue Cheese Salad", 1400, RESTAURANT1, LocalDate.now());
    public static final long RESTAURANT1_DISH5_ID = 5;
    public static final Dish RESTAURANT1_DISH5 = new Dish(RESTAURANT1_DISH5_ID,"Jerk Chicken Wings", 1300, RESTAURANT1, LocalDate.now());
    public static final long RESTAURANT1_DISH6_ID = 6;
    public static final Dish RESTAURANT1_DISH6 = new Dish(RESTAURANT1_DISH6_ID,"Curry Shrimp", 2700, RESTAURANT1, LocalDate.now());

    public static final long RESTAURANT2_ID = 2;
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "J B Alberto's Pizza", "1326 W Morse Ave Chicago, IL, 60626", null, null);
    public static final long RESTAURANT2_DISH1_ID = 1;
    public static final Dish RESTAURANT2_DISH1 = new Dish(RESTAURANT2_DISH1_ID,"Fettuccine Alfredo", 1095, RESTAURANT2, LocalDate.now());
    public static final long RESTAURANT2_DISH2_ID = 2;
    public static final Dish RESTAURANT2_DISH2 = new Dish(RESTAURANT2_DISH2_ID,"Broiled Half Chicken Dinner", 950, RESTAURANT2, LocalDate.now());
    public static final long RESTAURANT2_DISH3_ID = 3;
    public static final Dish RESTAURANT2_DISH3 = new Dish(RESTAURANT2_DISH3_ID,"San Pellegrino", 195, RESTAURANT2, LocalDate.now());

    public static final Restaurant RESTAURANT_NEW = new Restaurant(null, "Gabriel Kreuther", "41 W 42nd St, Grace Building, New York City", "www.gknyc.com", null);

    public static final Dish DISH_NEW = new Dish(null, "burger", 500, RESTAURANT1, LocalDate.now());

    static {
        RESTAURANT1.setMenu(Arrays.asList(RESTAURANT1_DISH4, RESTAURANT1_DISH5, RESTAURANT1_DISH6));
        RESTAURANT2.setMenu(Arrays.asList(RESTAURANT2_DISH1, RESTAURANT2_DISH2, RESTAURANT2_DISH3));
    }

}
