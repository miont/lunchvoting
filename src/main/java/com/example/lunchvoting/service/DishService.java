package com.example.lunchvoting.service;

import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.dto.DishDto;
import com.example.lunchvoting.dto.RestaurantDto;
import com.example.lunchvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface DishService {

    DishDto create(DishDto dish, long restaurantId);

    DishDto get(long id, long restaurantId) throws NotFoundException;

    List<DishDto> getAll(long restaurantId);

    List<DishDto> getAllBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate);

    void update(DishDto dish, long restaurantId) throws NotFoundException;

    void delete(long id, long restaurantId)  throws NotFoundException;

    List<Dish> getTodayMenu(long restaurantId);

    List<DishDto> getTodayMenuDto(long restaurantId);
}
