package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.VoteDto;
import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.service.VoteService;
import com.example.lunchvoting.util.RestUtil;
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
//    static final String REST_URL = RestUtil.REST_URL_ROOT + "/votes";

    @Autowired
    VoteService service;

    @PostMapping(value = RestaurantRestController.REST_URL + "{id}/votes")
    public VoteDto makeVote(@PathVariable(name = "id") long restaurantId) {
        return service.makeVote(AuthorizedPerson.id(), restaurantId);
    }

    @GetMapping(value = RestaurantRestController.REST_URL + "{id}/votes")
    public List<VoteDto> getAllForRestaurant(@PathVariable("id") long restaurantId, @RequestParam(name = "startDate", required = false) LocalDate startDate, @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        correctDatesIfNull(startDate, endDate);
        return service.getAllForRestaurantBetweenDates(restaurantId, startDate, endDate);
    }

    @GetMapping(value = PersonRestController.REST_URL + "{id}/votes")
    public List<VoteDto> getAllForUser(@PathVariable("id") long userId, @RequestParam(name = "startDate", required = false) LocalDate startDate, @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        correctDatesIfNull(startDate, endDate);
        return service.getAllForRestaurantBetweenDates(userId, startDate, endDate);
    }
}
