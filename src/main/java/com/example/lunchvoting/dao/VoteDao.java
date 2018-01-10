package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface VoteDao extends GenericDao<Vote> {

    public Vote save(Vote vote, long userId, long restaurantId);

    public Vote getForUserOnDate(long userId, LocalDate date);

    public List<Vote> getAllForRestaurant(long restaurantId, LocalDate startDate, LocalDate endDate);

    public List<Vote> getAllForUser(long userId, LocalDate startDate, LocalDate endDate);

}
