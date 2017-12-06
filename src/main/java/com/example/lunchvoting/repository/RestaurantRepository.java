package com.example.lunchvoting.repository;

import com.example.lunchvoting.model.Restaurant;

import java.util.List;

/**
 *
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(long id);

    Restaurant get(long id);

    List<Restaurant> getAll();

    Restaurant getByAddress(String address);
}
