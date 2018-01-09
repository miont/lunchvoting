package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface DishDao extends GenericDao<Dish> {

    Dish get(long id, long restaurantId);

    List<Dish> getAllForRestaurantBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate);

    Dish save(Dish dish, long restaurantId);

    boolean delete(long id, long restaurantId);

}
