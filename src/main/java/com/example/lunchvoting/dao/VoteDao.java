package com.example.lunchvoting.dao;

import com.example.lunchvoting.domain.Vote;

/**
 *
 */
public interface VoteDao {

    public Vote save(Vote vote, long userId, long restaurantId);

}
