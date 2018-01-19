package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.VoteDto;
import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.security.CurrentUser;
import com.example.lunchvoting.service.VoteService;
import com.example.lunchvoting.util.DateTimeUtil;
import com.example.lunchvoting.util.RestUtil;
import com.example.lunchvoting.web.person.PersonRestController;
import com.example.lunchvoting.web.person.ProfileRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    @Autowired
    VoteService service;

    @PostMapping(value = RestUtil.REST_URL_ROOT + "/votes")
    public VoteDto makeVote(@RequestBody VoteDto voteDto,  @CurrentUser AuthorizedPerson authorizedPerson) {
        voteDto.setUserId(authorizedPerson.id());
        return service.makeVote(voteDto);
    }

    @PostMapping(value = RestaurantRestController.REST_URL + "/{id}/votes")
    public VoteDto makeVote(@PathVariable(name = "id") long restaurantId, @CurrentUser AuthorizedPerson authorizedPerson) {
        return service.makeVote(authorizedPerson.id(), restaurantId);
    }

    @GetMapping(value = RestaurantRestController.REST_URL_ADMIN + "/{id}/votes")
    public List<VoteDto> getAllForRestaurant(@PathVariable("id") long restaurantId, @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN) LocalDate startDate, @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN) LocalDate endDate) {
        startDate = DateTimeUtil.correctStartDateIfNull(startDate);
        endDate = DateTimeUtil.correctEndDateIfNull(endDate);
        return service.getAllForRestaurantBetweenDates(restaurantId, startDate, endDate);
    }

    @GetMapping(value = PersonRestController.REST_URL + "/{id}/votes")
    public List<VoteDto> getAllForUser(@PathVariable("id") long userId, @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN) LocalDate startDate, @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN) LocalDate endDate) {
        startDate = DateTimeUtil.correctStartDateIfNull(startDate);
        endDate = DateTimeUtil.correctEndDateIfNull(endDate);
        return service.getAllForUserBetweenDates(userId, startDate, endDate);
    }

    @GetMapping(value = ProfileRestController.REST_URL + "/votes")
    public List<VoteDto> getAllForCurrentUser(@CurrentUser AuthorizedPerson authorizedPerson, @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN) LocalDate startDate, @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN) LocalDate endDate) {
        startDate = DateTimeUtil.correctStartDateIfNull(startDate);
        endDate = DateTimeUtil.correctEndDateIfNull(endDate);
        return service.getAllForUserBetweenDates(authorizedPerson.id(), startDate, endDate);
    }
}
