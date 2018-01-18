package com.example.lunchvoting.service;

import com.example.lunchvoting.dto.VoteDto;
import com.example.lunchvoting.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface VoteService {

    VoteDto makeVote(long userId, long restaurantId) throws NotFoundException;

    VoteDto makeVote(VoteDto voteDto) throws NotFoundException;

    List<VoteDto> getAllForRestaurantBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate);

    List<VoteDto> getAllForRestaurant(long restaurantId);

    List<VoteDto> getAllForUserBetweenDates(long userId, LocalDate startDate, LocalDate endDate);

    List<VoteDto> getAllForUser(long userId);
}
