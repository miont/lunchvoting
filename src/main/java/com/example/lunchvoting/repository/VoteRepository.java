package com.example.lunchvoting.repository;

import com.example.lunchvoting.model.Vote;

/**
 *
 */
public interface VoteRepository {

    public Vote save(Vote vote, long userId, long restaurantId);

}
