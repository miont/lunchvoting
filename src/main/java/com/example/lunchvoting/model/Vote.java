package com.example.lunchvoting.model;

import java.time.LocalDateTime;

/**
 *
 */
public class Vote extends AbstractBaseEntity {

    private User user;

    private Restaurant restaurant;

    private LocalDateTime dateTime;

    public Vote(User user, Restaurant restaurant, LocalDateTime dateTime) {
        this.user = user;
        this.restaurant = restaurant;
        this.dateTime = dateTime;
    }
}
