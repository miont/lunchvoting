package com.example.lunchvoting.dao.jpa;

import com.example.lunchvoting.dao.VoteDao;
import com.example.lunchvoting.domain.Dish;
import com.example.lunchvoting.domain.Person;
import com.example.lunchvoting.domain.Restaurant;
import com.example.lunchvoting.domain.Vote;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

import static com.example.lunchvoting.util.ValidationUtil.getArgumentIsNullMsg;

/**
 *
 */
@Repository
public class VoteDaoJpaImpl extends GenericDaoJpaImpl<Vote> implements VoteDao {

    public VoteDaoJpaImpl() {
        super(Vote.class);
    }

    @Override
    public Vote save(Vote vote, long userId, long restaurantId) {
        Assert.notNull(vote, getArgumentIsNullMsg("vote"));
        vote.setPerson(entityManager.getReference(Person.class, userId));
        vote.setRestaurant(entityManager.getReference(Restaurant.class, restaurantId));
        if(vote.isNew()) {
            entityManager.persist(vote);
            return vote;
        }
        else {
            return entityManager.merge(vote);
        }
    }

    @Override
    public Vote getForUserOnDate(long userId, LocalDate date) {
        Query query = entityManager.createNamedQuery(Vote.GET_FOR_USER_ON_DATE);
        query.setParameter("personId", userId)
                .setParameter("date", date);
        List<Vote> votes = query.getResultList();

        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getAllForRestaurant(long restaurantId, LocalDate startDate, LocalDate endDate) {
        return entityManager.createNamedQuery(Vote.ALL_FOR_RESTAURANT)
                .setParameter("restaurantId", restaurantId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public List<Vote> getAllForUser(long userId, LocalDate startDate, LocalDate endDate) {
        return entityManager.createNamedQuery(Vote.ALL_FOR_RESTAURANT)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public Long getCountForRestaurantOnDate(long restaurantId, LocalDate date) {
        Query query = entityManager.createNamedQuery(Vote.COUNT_FOR_RESTAURANT_ON_DATE)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date);
        Object result = query.getSingleResult();
        return result instanceof Long ? (Long) result : null;
    }
}
