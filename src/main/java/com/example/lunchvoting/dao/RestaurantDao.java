package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Restaurant;

/**
 *
 */
public interface RestaurantDao extends GenericDao<Restaurant> {

    Restaurant getByAddress(String address);
}
