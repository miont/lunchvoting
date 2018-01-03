package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.dao.RestaurantDao;
import com.example.lunchvoting.domain.Restaurant;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class RestaurantDaoJpaImpl extends GenericDaoJpaImpl<Restaurant> implements RestaurantDao {

    public RestaurantDaoJpaImpl() {
        super(Restaurant.class);
    }

    @Override
    public Restaurant getByAddress(String address) {
        return null;
    }
}
