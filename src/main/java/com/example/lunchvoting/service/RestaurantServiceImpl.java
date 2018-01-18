package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.DishDao;
import com.example.lunchvoting.dao.RestaurantDao;
import com.example.lunchvoting.dao.VoteDao;
import com.example.lunchvoting.domain.Restaurant;
import com.example.lunchvoting.dto.RestaurantDto;
import com.example.lunchvoting.util.exception.NotFoundException;
import com.example.lunchvoting.util.mapping.MappingUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.example.lunchvoting.util.ValidationUtil.*;

/**
 *
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private VoteDao voteDao;

    @Autowired
    private DishService dishService;

    @Autowired
    private Mapper mapper;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public RestaurantDto create(RestaurantDto restaurant) {
        Assert.notNull(restaurant, "restaurant argument should be not null");
        return mapper.map(restaurantDao.save(mapper.map(restaurant, Restaurant.class)), RestaurantDto.class);
    }

    @Override
    public RestaurantDto get(long id)  throws NotFoundException {

        Restaurant restaurant = restaurantDao.get(id);
        if(restaurant == null) {
            throw new NotFoundException("not found restaurant with id = " + id);
        }

        if(restaurant.getMenu().isEmpty()) {   // Set if empty otherwise not needed
            restaurant.setMenu(dishService.getTodayMenu(id));
        }

        RestaurantDto restaurantDto = mapper.map(restaurant, RestaurantDto.class); // TODO: probably doesn't work with menu
        restaurantDto.setVotesTodayCount(getTodayVotesCount(id));

        return restaurantDto;
    }

    @Cacheable("restaurants")
    @Override
    public List<RestaurantDto> getAll() {

        List<Restaurant> restaurants = restaurantDao.getAll();
        if(restaurants == null) {
            return null;
        }

        restaurants.forEach(restaurant -> {restaurant.setMenu(dishService.getTodayMenu(restaurant.getId()));});

        List<RestaurantDto> restaurantsDto = MappingUtil.mapList(mapper, restaurants, RestaurantDto.class);
        restaurantsDto.forEach(restaurant -> {restaurant.setVotesTodayCount(getTodayVotesCount(restaurant.getId()));});

        return restaurantsDto;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public void update(RestaurantDto restaurant) {
        Assert.notNull(restaurant, "argument must not be null");
        restaurantDao.save(mapper.map(restaurant, Restaurant.class));
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Override
    public void delete(long id) {
        checkNotFoundWithId(restaurantDao.delete(id), id);
    }


    public long getTodayVotesCount(long id) {
        Long count = voteDao.getCountForRestaurantOnDate(id, LocalDate.now());
        if(count == null) throw new RuntimeException("Error during counting votes for restaurant");
        return count;
    }
}
