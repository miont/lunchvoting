package com.example.lunchvoting.service;

import com.example.lunchvoting.domain.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant get(long id);

    List<Restaurant> getAll();

    void update(Restaurant restaurant);

    void delete(long id);

}
