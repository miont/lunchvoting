package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.dao.RestaurantDao;
import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Restaurant;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@Repository
public class RestaurantDaoJpaImpl extends GenericDaoJpaImpl<Restaurant> implements RestaurantDao {

    public RestaurantDaoJpaImpl() {
        super(Restaurant.class);
    }

    @Override
    public List<Restaurant> getAll() {
        return entityManager.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant getByAddress(String address) {

        Query query = entityManager.createQuery("SELECT restaurant FROM Restaurant restaurant WHERE restaurant.address = :address");
        List<Restaurant> restaurants = query.setParameter("address", address).getResultList();

        return DataAccessUtils.singleResult(restaurants);
    }
}
