package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.VoteDto;
import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.security.CurrentUser;
import com.example.lunchvoting.service.VoteService;
import com.example.lunchvoting.web.person.PersonRestController;
import com.example.lunchvoting.web.person.ProfileRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.lunchvoting.util.DateTimeUtil.correctDatesIfNull;

/**
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    @Autowired
    VoteService service;

    @PostMapping(value = RestaurantRestController.REST_URL + "/{id}/votes")
    public VoteDto makeVote(@PathVariable(name = "id") long restaurantId, @CurrentUser AuthorizedPerson authorizedPerson) {
        return service.makeVote(authorizedPerson.id(), restaurantId);
    }

    @GetMapping(value = RestaurantRestController.REST_URL + "{id}/votes")
    public List<VoteDto> getAllForRestaurant(@PathVariable("id") long restaurantId, @RequestParam(name = "startDate", required = false) LocalDate startDate, @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        correctDatesIfNull(startDate, endDate);
        return service.getAllForRestaurantBetweenDates(restaurantId, startDate, endDate);
    }

    @GetMapping(value = PersonRestController.REST_URL + "{id}/votes")
    public List<VoteDto> getAllForUser(@PathVariable("id") long userId, @RequestParam(name = "startDate", required = false) LocalDate startDate, @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        correctDatesIfNull(startDate, endDate);
        return service.getAllForUserBetweenDates(userId, startDate, endDate);
    }

    @GetMapping(value = ProfileRestController.REST_URL + "/votes")
    public List<VoteDto> getAllForCurrentUser(@CurrentUser AuthorizedPerson authorizedPerson, @RequestParam(name = "startDate", required = false) LocalDate startDate, @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        correctDatesIfNull(startDate, endDate);
        return service.getAllForUserBetweenDates(authorizedPerson.id(), startDate, endDate);
    }
}
