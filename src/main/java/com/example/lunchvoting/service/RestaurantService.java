package com.example.lunchvoting.service;

import com.example.lunchvoting.domain.Restaurant;
import com.example.lunchvoting.dto.RestaurantDto;
import com.example.lunchvoting.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
public interface RestaurantService {

    RestaurantDto create(RestaurantDto restaurant);

    RestaurantDto get(long id) throws NotFoundException;

    List<RestaurantDto> getAll();

    void update(RestaurantDto restaurant);

    void delete(long id)  throws NotFoundException;

    long getTodayVotesCount(long id);

}
