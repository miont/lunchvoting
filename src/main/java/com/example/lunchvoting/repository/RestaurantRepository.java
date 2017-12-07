package com.example.lunchvoting.repository;

import com.example.lunchvoting.model.Restaurant;

import java.util.List;

/**
 *
 */
public interface RestaurantRepository extends GenericRepository<Restaurant> {

    Restaurant getByAddress(String address);
}
