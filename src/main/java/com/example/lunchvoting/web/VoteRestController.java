package com.example.lunchvoting.web;

import com.example.lunchvoting.dto.VoteDto;
import com.example.lunchvoting.service.VoteService;
import com.example.lunchvoting.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = RestUtil.REST_URL_ROOT + "/votes";

    @Autowired
    VoteService service;

    @PostMapping
    public VoteDto makeVote(@RequestParam(value = "user_id") long userId, @RequestParam(value = "restaurant_id") long restaurantId) {
        return service.makeVote(userId, restaurantId);
    }

//    @GetMapping()
//    public List<VoteDto> getAllForRestaurant() {
//
//    }
}
