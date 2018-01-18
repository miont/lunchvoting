package com.example.lunchvoting.service;

import com.example.lunchvoting.dao.PersonDao;
import com.example.lunchvoting.dao.RestaurantDao;
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

import static com.example.lunchvoting.util.ValidationUtil.checkNotFoundWithId;

/**
 *
 */
@Service
public class VoteServiceImpl implements VoteService {

    private static final LocalTime LIMIT_VOTE_TIME = LocalTime.of(11, 00);

    @Autowired
    VoteDao dao;

    @Autowired
    RestaurantDao restaurantDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    Mapper mapper;

    @Transactional
    @Override
    public VoteDto makeVote(long userId, long restaurantId) {
        VoteDto voteDto = new VoteDto(null, userId, restaurantId, LocalDate.now(), LocalTime.now());
        return makeVote(voteDto);
    }

    @Transactional
    @Override
    public VoteDto makeVote(VoteDto voteDto) {
        // set date/time if not defined
        if(voteDto.getDate() == null) voteDto.setDate(LocalDate.now());
        if(voteDto.getTime() == null) voteDto.setTime(LocalTime.now());
        // check user and restaurant exist
        checkNotFoundWithId(personDao.get(voteDto.getUserId()), voteDto.getUserId());
        checkNotFoundWithId(restaurantDao.get(voteDto.getRestaurantId()), voteDto.getRestaurantId());
        // Check if there was already vote from this user on current date
        Vote existedVote = dao.getForUserOnDate(voteDto.getUserId(), voteDto.getDate());
        if(existedVote == null || voteDto.getTime().isBefore(LIMIT_VOTE_TIME)) {
            if(existedVote != null) {
                dao.delete(existedVote.getId());
            }
            Vote vote = dao.save(mapper.map(voteDto, Vote.class), voteDto.getUserId(), voteDto.getRestaurantId());
            voteDto = mapper.map(vote, VoteDto.class);
        }
        else {
            // prepare DTO with unsuccess flag
            voteDto.setSuccessfull(false);
        }
        return voteDto;
    }

    @Override
    public List<VoteDto> getAllForRestaurantBetweenDates(long restaurantId, LocalDate startDate, LocalDate endDate) {
        checkNotFoundWithId(restaurantDao.get(restaurantId), restaurantId);
        startDate = DateTimeUtil.correctStartDateIfNull(startDate);
        endDate = DateTimeUtil.correctEndDateIfNull(endDate);
        return MappingUtil.mapList(mapper, dao.getAllForRestaurant(restaurantId, startDate, endDate), VoteDto.class);
    }

    @Override
    public List<VoteDto> getAllForRestaurant(long restaurantId) {
        return getAllForRestaurantBetweenDates(restaurantId, DateTimeUtil.MIN_DATE, DateTimeUtil.MAX_DATE);
    }

    @Override
    public List<VoteDto> getAllForUserBetweenDates(long userId, LocalDate startDate, LocalDate endDate) {
        checkNotFoundWithId(personDao.get(userId), userId);
        startDate = DateTimeUtil.correctStartDateIfNull(startDate);
        endDate = DateTimeUtil.correctEndDateIfNull(endDate);
        return MappingUtil.mapList(mapper, dao.getAllForUser(userId, startDate, endDate), VoteDto.class);
    }

    @Override
    public List<VoteDto> getAllForUser(long userId) {
        return getAllForUserBetweenDates(userId, DateTimeUtil.MIN_DATE, DateTimeUtil.MAX_DATE);
    }
}
