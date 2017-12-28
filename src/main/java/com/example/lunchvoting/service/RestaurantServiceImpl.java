package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.RestaurantDao;
import com.example.lunchvoting.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 */
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantDao restaurantDao;

    @Override
    public Restaurant create(Restaurant restaurant) {
        restaurantDao.save(restaurant);
        return null;
    }

    @Override
    public Restaurant get(long id) {
        return restaurantDao.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantDao.getAll();
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantDao.save(restaurant);
    }

    @Override
    public void delete(long id) {
//        restaurantDao.delete(id);
    }
}
