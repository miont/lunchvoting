package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.dao.DishDao;
import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.domain.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@Repository
public class DishDaoJpaImpl extends GenericDaoJpaImpl<Dish> implements DishDao {

    public DishDaoJpaImpl() {
        super(Dish.class);
    }


    @Override
    public Dish get(long id, long restaurantId) {
        Dish dish = entityManager.find(Dish.class, id);
        return (dish != null && dish.getRestaurant().getId() == restaurantId) ? dish : null;
    }

    @Override
    public List<Dish> getAllForRestaurantBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate) {
        return entityManager.createNamedQuery(Dish.ALL_FOR_RESTAURANT, Dish.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    @Transactional
    public Dish save(Dish dish, long restaurantId) {
        if(!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(entityManager.getReference(Restaurant.class, restaurantId));
        if(dish.isNew()) {
            entityManager.persist(dish);
            return dish;
        }
        else {
            return entityManager.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(long id, long restaurantId) {
        return entityManager.createNamedQuery(Dish.DELETE, Dish.class)
                .setParameter("id", id)
                .setParameter("restaurantId", id)
                .executeUpdate() > 0;
    }
}
