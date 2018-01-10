package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.VoteDao;
import com.example.lunchvoting.domain.Vote;
import com.example.lunchvoting.dto.VoteDto;
import com.example.lunchvoting.util.DateTimeUtil;
import com.example.lunchvoting.util.mapping.MappingUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 */
@Service
public class VoteServiceImpl implements VoteService {

    private static final LocalTime LIMIT_VOTE_TIME = LocalTime.of(11, 00);

    @Autowired
    VoteDao dao;

    @Autowired
    Mapper mapper;

    @Transactional
    @Override
    public VoteDto makeVote(long userId, long restaurantId) {
        Vote vote = new Vote();
        VoteDto voteDto = new VoteDto();
        // Check if there was already vote from this user on current date
        Vote existedVote = dao.getForUserOnDate(userId, vote.getDate());
        if(existedVote == null || vote.getTime().isBefore(LIMIT_VOTE_TIME)) {
            if(existedVote != null) {
                dao.delete(existedVote.getId());
            }
            vote = dao.save(vote, userId, restaurantId);
            voteDto = mapper.map(vote, VoteDto.class);
        }
        else {
            // prepare DTO with unsuccess flag
            voteDto.setUserId(userId);
            voteDto.setRestaurantId(restaurantId);
            voteDto.setDate(vote.getDate());
            voteDto.setTime(vote.getTime());
            voteDto.setSuccessfull(false);
        }
        return voteDto;
    }

    @Override
    public List<VoteDto> getAllForRestaurantBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate) {
        return MappingUtil.mapList(mapper, dao.getAllForRestaurant(restaurantId, startDate, endDate), VoteDto.class);
    }

    @Override
    public List<VoteDto> getAllForRestaurant(long restaurantId) {
        return getAllForRestaurantBetweenDates(restaurantId, DateTimeUtil.MIN_DATE, DateTimeUtil.MAX_DATE);
    }

    @Override
    public List<VoteDto> getAllForUserBetweenDates(long userId, LocalDate startDate, LocalDate endDate) {
        return MappingUtil.mapList(mapper, dao.getAllForUser(userId, startDate, endDate), VoteDto.class);
    }

    @Override
    public List<VoteDto> getAllForUser(long userId) {
        return getAllForRestaurantBetweenDates(userId, DateTimeUtil.MIN_DATE, DateTimeUtil.MAX_DATE);
    }
}