package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.DishDao;
import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.dto.DishDto;
import com.example.lunchvoting.util.DateTimeUtil;
import com.example.lunchvoting.util.exception.NotFoundException;
import com.example.lunchvoting.util.mapping.MappingUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.example.lunchvoting.util.ValidationUtil.checkNotFoundWithId;

/**
 *
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishDao dao;

    @Autowired
    Mapper mapper;

    @Override
    public DishDto get(long id, long restaurantId) throws NotFoundException {
        return mapper.map(
                checkNotFoundWithId(dao.get(id, restaurantId), id),
                DishDto.class);
    }

    @Override
    public List<DishDto> getAll(long restaurantId) {
        return getAllBetweenDates(restaurantId, DateTimeUtil.MIN_DATE, DateTimeUtil.MAX_DATE);
    }

    @Override
    public List<DishDto> getAllBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");
        List<Dish> dishes = dao.getAllForRestaurantBetweenDates(restaurantId, startDate, endDate);
        return dishes != null ? MappingUtil.mapList(mapper, dishes, DishDto.class) : null;
    }


    @Override
    public DishDto create(DishDto dish, long restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return mapper.map(
                dao.save(mapper.map(dish, Dish.class), restaurantId),
                DishDto.class);
    }

    @Override
    public void update(DishDto dish, long restaurantId) throws NotFoundException {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dao.save(mapper.map(dish, Dish.class), restaurantId), dish.getId());
    }

    @Override
    public void delete(long id, long restaurantId) throws NotFoundException {
        checkNotFoundWithId(dao.delete(id, restaurantId), id);
    }

    @Override
    public List<Dish> getTodayMenu(long restaurantId) {
        // Get menu for today
        List<Dish> dishes;
        dishes = dao.getAllForRestaurantBetweenDates(restaurantId, LocalDate.now(), LocalDate.now());

        //If empty try to get previous menu
        if(dishes.isEmpty()) {
            dishes = dao.getAllForRestaurantBetweenDates(restaurantId, LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));
            dishes.forEach(dish -> {dish.setDate(LocalDate.now()); dao.save(dish);});
        }

        return dishes;
    }

    @Override
    public List<DishDto> getTodayMenuDto(long restaurantId) {
        return MappingUtil.mapList(mapper, getTodayMenu(restaurantId), DishDto.class);
    }
}
